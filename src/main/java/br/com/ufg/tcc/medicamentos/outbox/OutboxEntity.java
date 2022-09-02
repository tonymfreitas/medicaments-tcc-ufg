package br.com.ufg.tcc.medicamentos.outbox;

import java.util.UUID;

import javax.persistence.Entity;
import javax.persistence.Id;

import lombok.Data;

@Entity(name = "outbox")
@Data
public class OutboxEntity {

    @Id
    private UUID id;

    private String event;

    private String status;

    private String data;


}
