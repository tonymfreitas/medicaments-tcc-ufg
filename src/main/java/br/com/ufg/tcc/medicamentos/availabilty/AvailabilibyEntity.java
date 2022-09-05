package br.com.ufg.tcc.medicamentos.availabilty;

import lombok.Data;
import org.springframework.data.geo.Point;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.UUID;

@Entity(name = "availability")
@Data
public class AvailabilibyEntity {

    @Id
    private UUID id;

    @Column(name = "id_medicament")
    private UUID idMedicament;

    @Column(name = "code_cnes")
    private String codeCnes;

    private BigInteger quantity;

    private String restriction;

}
