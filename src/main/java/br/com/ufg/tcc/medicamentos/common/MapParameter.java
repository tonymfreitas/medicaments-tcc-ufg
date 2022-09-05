package br.com.ufg.tcc.medicamentos.common;

import org.springframework.jdbc.core.namedparam.SqlParameterSource;

public abstract class MapParameter<T> {

    protected MapParameter(T source) {
        this.source = source;
    }

    protected T source;

    public abstract SqlParameterSource sourceToSqlParameter();

}

