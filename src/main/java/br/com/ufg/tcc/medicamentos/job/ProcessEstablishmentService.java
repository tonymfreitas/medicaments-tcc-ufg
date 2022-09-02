package br.com.ufg.tcc.medicamentos.job;

import br.com.ufg.tcc.medicamentos.establishments.EstablishmentCsv;
import br.com.ufg.tcc.medicamentos.establishments.EstablishmentService;
import br.com.ufg.tcc.medicamentos.outbox.OutBoxRepository;
import br.com.ufg.tcc.medicamentos.outbox.OutboxEntity;
import br.com.ufg.tcc.medicamentos.outbox.OutboxEventType;
import br.com.ufg.tcc.medicamentos.outbox.OutboxStatus;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

import static br.com.ufg.tcc.medicamentos.outbox.OutboxEventType.REGISTER_ESTABLISHMENT;
import static br.com.ufg.tcc.medicamentos.outbox.OutboxStatus.CREATED;
import static br.com.ufg.tcc.medicamentos.outbox.OutboxStatus.ERROR;
import static br.com.ufg.tcc.medicamentos.outbox.OutboxStatus.PROCESSING;
import static java.util.Objects.nonNull;

@Service
public class ProcessEstablishmentService {

    @Autowired
    private EstablishmentService establishmentService;

    @Autowired
    private OutBoxRepository outBoxRepository;

    @Autowired
    private ObjectMapper objectMapper;

    public void execute() {
        final var outboxs = outBoxRepository.listEventsByType(REGISTER_ESTABLISHMENT.name(), CREATED.name());

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


}
