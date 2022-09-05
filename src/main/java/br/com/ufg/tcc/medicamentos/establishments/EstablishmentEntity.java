package br.com.ufg.tcc.medicamentos.establishments;


import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

import lombok.Data;

@Entity(name = "establishments")
@Data
public class EstablishmentEntity {


    @Id
    @Column(name = "code_cnes")
    private String codeCnes;

    private String cnpj;

    private String name;

    private String phone;

    @Column(name = "type_id")
    private String typeId;

    @Column(name = "id_address")
    private UUID idAddress;

    public static EstablishmentEntity from(final EstablishmentCsv e, final UUID addressId) {

        var establishment = new EstablishmentEntity();
        establishment.setCnpj(e.getCnpj());
        establishment.setName(e.getName());
        establishment.setPhone(e.getPhone());
        establishment.setCodeCnes(e.getCnes());
        establishment.setTypeId(e.getType_id());
        establishment.setIdAddress(addressId);

        return establishment;
    }

}
