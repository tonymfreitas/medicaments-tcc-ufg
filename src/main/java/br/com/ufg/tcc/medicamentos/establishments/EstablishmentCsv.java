package br.com.ufg.tcc.medicamentos.establishments;

import com.opencsv.bean.CsvBindByName;

public class EstablishmentCsv {

    @CsvBindByName(column = "CNES", required = true)
    private String cnes;

    @CsvBindByName(column = "CNPJ")
    private String cnpj;

    @CsvBindByName(column = "NAME")
    private String name;

    @CsvBindByName(column = "STREET")
    private String street;

    @CsvBindByName(column = "NUMBER")
    private String number;

    @CsvBindByName(column = "COMPLEMENT")
    private String complement;

    @CsvBindByName(column = "DISTRICT")
    private String district;

    @CsvBindByName(column = "CEP")
    private String cep;

    @CsvBindByName(column = "TYPE_ID")
    private String type_id;

    @CsvBindByName(column = "PHONE")
    private String phone;

    @CsvBindByName(column = "STATE_ID")
    private String state_id;

    public String getCnes() {
        return cnes;
    }

    public String getCnpj() {
        return cnpj;
    }

    public String getName() {
        return name;
    }

    public String getStreet() {
        return street;
    }

    public String getNumber() {
        return number;
    }

    public String getComplement() {
        return complement;
    }

    public String getDistrict() {
        return district;
    }

    public String getCep() {
        return cep;
    }

    public String getType_id() {
        return type_id;
    }

    public String getPhone() {
        return phone;
    }

    public String getState_id() {
        return state_id;
    }
}
