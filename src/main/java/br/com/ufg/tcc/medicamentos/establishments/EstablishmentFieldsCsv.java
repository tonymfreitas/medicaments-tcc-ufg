package br.com.ufg.tcc.medicamentos.establishments;

import java.util.ArrayList;
import java.util.List;

public enum EstablishmentFieldsCsv {

    CO_CNES(1),
    NU_CNPJ_MANTENEDORA(2),
    NO_FANTASIA(6),
    NO_LOGRADOURO(7),
    NU_ENDERECO(8),
    NO_COMPLEMENTO(9),
    NO_BAIRRO(10),
    CO_CEP(11),
    CO_REGIAO_SAUDE(12),
    NU_TELEFONE(16),
    CO_ESTADO_GESTOR(30);

    private final int index;

    EstablishmentFieldsCsv(int index) {
        this.index = index;
    }

    public int getIndex() {
        return index;
    }

    public static String[] fields() {
        List<String> columns = new ArrayList<>();
        for (EstablishmentFieldsCsv column : values()) {
            columns.add(column.name());
        }
        return columns.toArray(new String[] {});
    }

}
