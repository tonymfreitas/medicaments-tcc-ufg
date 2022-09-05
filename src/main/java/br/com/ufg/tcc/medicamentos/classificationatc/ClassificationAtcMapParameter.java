package br.com.ufg.tcc.medicamentos.classificationatc;

import br.com.ufg.tcc.medicamentos.common.MapParameter;

import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class ClassificationAtcMapParameter extends MapParameter<ClassificationAtc> {

    protected ClassificationAtcMapParameter(ClassificationAtc source) {
        super(source);
    }

    @Override
    public SqlParameterSource sourceToSqlParameter() {
        final Map<String, Object> params = new HashMap<>();
        params.put("id", source.getId());
        params.put("code_atc", source.getCodeAtc());
        params.put("code_atc_parent", source.getCodeAtcParent());
        params.put("level", source.getLevel());
        params.put("name", source.getName());
        return new MapSqlParameterSource(params);
    }

    public static SqlParameterSource sourteToUpdate(final UUID id, final String name) {
        final Map<String, Object> params = new HashMap<>();
        params.put("id", id);
        params.put("name", name);
        return new MapSqlParameterSource(params);
    }
}
