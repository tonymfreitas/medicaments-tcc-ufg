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

    private String address;

    private BigDecimal latitude;

    private BigDecimal longitude;

    private BigInteger quantity;

}
