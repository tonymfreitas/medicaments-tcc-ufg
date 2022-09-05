package br.com.ufg.tcc.medicamentos.address;

import br.com.ufg.tcc.medicamentos.establishments.EstablishmentCsv;

import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

import lombok.Data;

@Entity(name = "addresses")
@Data
public class AddressEntity {

    @Id
    private UUID id;

    private String street;

    private String number;

    private String complement;

    private String district;

    private String cep;

    @Column(name = "id_state")
    private String idState;

    public static AddressEntity from(final EstablishmentCsv e) {

        var address = new AddressEntity();
        address.setId(UUID.randomUUID());
        address.setCep(e.getCep());
        address.setComplement(e.getComplement());
        address.setStreet(e.getStreet());
        address.setDistrict(e.getDistrict());
        address.setNumber(e.getNumber());
        address.setIdState(e.getState_id());

        return address;
    }

}
