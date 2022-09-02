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

    @Column(name = "id_classification_atc")
    private UUID idclassificationatc;

    @Column(name = "code_atc")
    private String codeAtc;

    private String name;

    private String unity;

    @Column(name = "id_pharmaceutical_form")
    private UUID idPhamaceuticalForm;

    @Enumerated(EnumType.STRING)
    private MedicamentComponent component;

    private String via;

    private String use;

}
