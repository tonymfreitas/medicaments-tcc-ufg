package br.com.ufg.tcc.medicamentos.medicament.pharmaceuticalform;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.UUID;

@Entity(name = "pharmaceuticalform")
@Data
public class PharmaceuticalFormEntity {

    @Id
    private UUID id;

    private String code;

    private String description;

}
