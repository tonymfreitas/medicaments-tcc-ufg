package br.com.ufg.tcc.medicamentos.establishments;

import org.apache.commons.csv.CSVRecord;

import lombok.Data;

@Data
public class EstablishmentCsv {

    private String cnes;

    private String cnpj;

    private String name;

    private String street;

    private String number;

    private String complement;

    private String district;

    private String cep;

    private String type_id;

    private String phone;

    private String state_id;

    public EstablishmentCsv(String cnes,
                            String cnpj,
                            String name,
                            String street,
                            String number,
                            String complement,
                            String district,
                            String cep,
                            String type_id,
                            String phone,
                            String state_id) {
        this.cnes = cnes;
        this.cnpj = cnpj;
        this.name = name;
        this.street = street;
        this.number = number;
        this.complement = complement;
        this.district = district;
        this.cep = cep;
        this.type_id = type_id;
        this.phone = phone;
        this.state_id = state_id;
    }

    public static EstablishmentCsv from(final CSVRecord csvRecord) {
        return new EstablishmentCsv(
                csvRecord.get(EstablishmentFieldsCsv.CO_CNES.getIndex()),
                csvRecord.get(EstablishmentFieldsCsv.NU_CNPJ_MANTENEDORA.getIndex()),
                csvRecord.get(EstablishmentFieldsCsv.NO_FANTASIA.getIndex()),
                csvRecord.get(EstablishmentFieldsCsv.NO_LOGRADOURO.getIndex()),
                csvRecord.get(EstablishmentFieldsCsv.NU_ENDERECO.getIndex()),
                csvRecord.get(EstablishmentFieldsCsv.NO_COMPLEMENTO.getIndex()),
                csvRecord.get(EstablishmentFieldsCsv.NO_BAIRRO.getIndex()),
                csvRecord.get(EstablishmentFieldsCsv.CO_CEP.getIndex()),
                csvRecord.get(EstablishmentFieldsCsv.CO_REGIAO_SAUDE.getIndex()),
                csvRecord.get(EstablishmentFieldsCsv.NU_TELEFONE.getIndex()),
                csvRecord.get(EstablishmentFieldsCsv.CO_ESTADO_GESTOR.getIndex())
        );
    }


}
