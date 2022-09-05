package br.com.ufg.tcc.medicamentos.job;

import br.com.ufg.tcc.medicamentos.establishments.EstablishmentCsv;
import br.com.ufg.tcc.medicamentos.establishments.EstablishmentFieldsCsv;
import br.com.ufg.tcc.medicamentos.establishments.EstablishmentService;
import br.com.ufg.tcc.medicamentos.outbox.OutBoxRepository;
import br.com.ufg.tcc.medicamentos.outbox.OutboxEntity;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.apache.commons.csv.CSVFormat;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.nio.file.Files;
import java.time.Instant;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

import io.micrometer.core.instrument.MeterRegistry;

import static br.com.ufg.tcc.medicamentos.outbox.OutboxEventType.REGISTER_ESTABLISHMENT;
import static br.com.ufg.tcc.medicamentos.outbox.OutboxEventType.SYNC_ESTABLISHMENT_DATA;
import static br.com.ufg.tcc.medicamentos.outbox.OutboxStatus.CREATED;
import static br.com.ufg.tcc.medicamentos.outbox.OutboxStatus.ERROR;
import static br.com.ufg.tcc.medicamentos.outbox.OutboxStatus.PROCESSING;
import static java.util.Objects.nonNull;

@Service
public class ProcessEstablishmentService {

    private static final String BASE_URL_FTP = "ftp://ftp.datasus.gov.br/cnes/BASE_DE_DADOS_CNES_%s.ZIP";
    private static final String DADOS_ZIP = "dados.zip";

    @Autowired
    private EstablishmentService establishmentService;

    @Autowired
    private OutBoxRepository outBoxRepository;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private MeterRegistry meterRegistry;

    public void execute() {

        final var outboxs = outBoxRepository.listEventsByType(REGISTER_ESTABLISHMENT.name(), CREATED.name())
                                            .stream()
                                            .filter(outbox -> outbox.getExecutionDate()
                                                                    .isBefore(Instant.now()))
                                            .toList();

        if (nonNull(outboxs) && !outboxs.isEmpty()) {
            for (OutboxEntity outbox : outboxs) {
                outBoxRepository.updateStatus(outbox.getId(), PROCESSING.name());
                try {
                    final var establishmentCsv = objectMapper.readValue(
                            outbox.getData(),
                            new TypeReference<List<EstablishmentCsv>>() {});
                    establishmentService.processEstablishment(establishmentCsv);
                    outBoxRepository.delete(outbox);
                } catch (Exception e) {
                    outBoxRepository.updateStatus(outbox.getId(), ERROR.name());
                    e.printStackTrace();
                }
            }

        }
    }

    public void syncData() {
        final var outboxs = outBoxRepository.listEventsByType(SYNC_ESTABLISHMENT_DATA.name(), CREATED.name())
                                            .stream()
                                            .filter(outbox -> outbox.getExecutionDate()
                                                                    .isBefore(Instant.now()))
                                            .toList();

        if (nonNull(outboxs) && !outboxs.isEmpty()) {
            for (OutboxEntity outbox : outboxs) {
                outBoxRepository.updateStatus(outbox.getId(), PROCESSING.name());
                try {

                    String ftpUrl = getFileName(outbox);

                    final var file = new File(DADOS_ZIP);

                    if (file.exists()) {
                        file.delete();
                    }

                    URLConnection urlConnection = new URL(ftpUrl).openConnection();
                    InputStream inputStream = urlConnection.getInputStream();

                    final var result = Files.copy(inputStream, file.toPath());

                    if (result > 0) {
                        filterFileAndSaveOutbox(file);
                        file.delete();
                    }

                    inputStream.close();

                    outBoxRepository.updateData(
                            outbox.getId(),
                            String.valueOf(Integer.valueOf(outbox.getData()) + 1),
                            CREATED.name()
                    );

                } catch (Exception e) {
                    outBoxRepository.updateStatus(outbox.getId(), ERROR.name());
                    meterRegistry.counter("error_sync_data_establishment", "reason", e.getMessage())
                                 .increment();
                    e.printStackTrace();
                }
            }
        }
    }

    private String getFileName(final OutboxEntity outboxEntity) {
        return String.format(BASE_URL_FTP, outboxEntity.getData());
    }

    private void filterFileAndSaveOutbox(final File fileZip) {
        try {
            byte[] buffer = new byte[1024];
            ZipInputStream zis = new ZipInputStream(new FileInputStream(fileZip));
            ZipEntry zipEntry = zis.getNextEntry();
            while (zipEntry != null) {
                if (zipEntry.getName()
                            .startsWith("tbEstabelecimento")) {
                    File newFile = new File(zipEntry.getName());

                    if (fileZip.exists()) {
                        fileZip.delete();
                    }


                    FileOutputStream fos = new FileOutputStream(newFile);
                    int len;
                    while ((len = zis.read(buffer)) > 0) {
                        fos.write(buffer, 0, len);
                    }

                    fos.close();

                    var reader = new InputStreamReader(new FileInputStream(newFile));

                    final var csvBulider = CSVFormat.Builder.create(CSVFormat.DEFAULT)
                                                            .setHeader(EstablishmentFieldsCsv.fields())
                                                            .setIgnoreSurroundingSpaces(true)
                                                            .setDelimiter(";")
                                                            .setSkipHeaderRecord(true)
                                                            .build();


                    final var csvRecords = csvBulider.parse(reader);

                    final var establishmentCsvs = csvRecords.stream()
                                                            .map(EstablishmentCsv::from)
                                                            .toList();

                    establishmentService.saveOutboxSynEstablishment(establishmentCsvs);
                    newFile.delete();

                }


                zipEntry = zis.getNextEntry();
            }
            zis.closeEntry();
            zis.close();

        } catch (Exception e) {
            throw new RuntimeException("Error unzipping file " + e.getMessage());
        }
    }

}
