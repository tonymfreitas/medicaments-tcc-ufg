package br.com.ufg.tcc.medicamentos.establishments;

import br.com.ufg.tcc.medicamentos.address.AddressEntity;
import br.com.ufg.tcc.medicamentos.address.AddressService;
import br.com.ufg.tcc.medicamentos.outbox.OutBoxRepository;
import br.com.ufg.tcc.medicamentos.outbox.OutboxEntity;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.Reader;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

import javax.transaction.Transactional;

import static br.com.ufg.tcc.medicamentos.outbox.OutboxEventType.REGISTER_ESTABLISHMENT;
import static br.com.ufg.tcc.medicamentos.outbox.OutboxEventType.SYNC_ESTABLISHMENT_DATA;
import static br.com.ufg.tcc.medicamentos.outbox.OutboxStatus.CREATED;

@Service
public class EstablishmentService {

    @Autowired
    private EstablishmentRepository establishmentRepository;

    @Autowired
    private AddressService addressService;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private OutBoxRepository outBoxRepository;


    public void saveRequestSyncData() {

        final var outbox = outBoxRepository.findEventByType(SYNC_ESTABLISHMENT_DATA.name());

        if (Objects.nonNull(outbox)) {
            outbox.setStatus(CREATED.name());
            outbox.setExecutionDate(Instant.now());

            outBoxRepository.save(outbox);
        }

    }

    public void saveOutboxSynEstablishment(final List<EstablishmentCsv> establishmentCsvs) {

        try {
            final var json = objectMapper.writeValueAsString(establishmentCsvs);

            final var outbox = new OutboxEntity();
            outbox.setId(UUID.randomUUID());
            outbox.setData(json);
            outbox.setStatus(CREATED.name());
            outbox.setEvent(REGISTER_ESTABLISHMENT.name());
            outbox.setExecutionDate(Instant.now());

            outBoxRepository.save(outbox);

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void processEstablishment(final List<EstablishmentCsv> establishmentCsvs) {
        establishmentCsvs.forEach(e -> {
            var address = AddressEntity.from(e);
            var establishment = EstablishmentEntity.from(e, address.getId());

            addressService.save(address);
            establishmentRepository.save(establishment);
        });
    }

    @Transactional
    private void process(final EstablishmentCsv e) {
        var address = new AddressEntity();
        address.setId(UUID.randomUUID());
        address.setCep(e.getCep());
        address.setComplement(e.getComplement());
        address.setStreet(e.getStreet());
        address.setDistrict(e.getDistrict());
        address.setNumber(e.getNumber());
        address.setIdState(e.getState_id());

        addressService.save(address);

        var establishment = new EstablishmentEntity();
        establishment.setCnpj(e.getCnpj());
        establishment.setName(e.getName());
        establishment.setPhone(e.getPhone());
        establishment.setCodeCnes(e.getCnes());
        establishment.setTypeId(e.getType_id());
        establishment.setIdAddress(address.getId());

        establishmentRepository.save(establishment);
    }


    public static <T> List<T> convertToModel(MultipartFile file, Class<T> responseType) {
        List<T> models;
        try (Reader reader = new BufferedReader(new InputStreamReader(file.getInputStream()))) {
            CsvToBean<?> csvToBean = new CsvToBeanBuilder<>(reader)
                    .withType(responseType)
                    .withSeparator(';')
                    .withIgnoreLeadingWhiteSpace(true)
                    .withIgnoreEmptyLine(true)
                    .build();
            models = (List<T>) csvToBean.parse();
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
            throw new IllegalArgumentException(ex.getCause()
                                                 .getMessage());
        }
        return models;
    }

}
