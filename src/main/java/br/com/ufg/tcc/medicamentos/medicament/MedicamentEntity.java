package br.com.ufg.tcc.medicamentos.medicament;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.UUID;

@Entity(name = "medicaments")
@Data
public class MedicamentEntity {

    @Id
    private UUID id;

    private UUID idclassificationatc;

    @Column(name = "codeatc")
    private String codeAtc;

    private String name;

    private String unity;

    @Column(name = "idpharmaceuticalform")
    private UUID idPhamaceuticalForm;

    @Enumerated(EnumType.STRING)
    private MedicamentComponent component;

    private String via;

    private String use;

    private String restriction;

}