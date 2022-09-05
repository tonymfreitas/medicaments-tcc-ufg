package br.com.ufg.tcc.medicamentos.availabilty;

import java.util.UUID;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Availabiliby {

    private UUID id;

    private UUID idMedicament;

    private String codeCnes;

    private Integer quantity;

    private String restriction;

}
