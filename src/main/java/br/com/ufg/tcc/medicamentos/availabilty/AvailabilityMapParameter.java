package br.com.ufg.tcc.medicamentos.availabilty;

import br.com.ufg.tcc.medicamentos.common.MapParameter;

import org.springframework.jdbc.core.namedparam.SqlParameterSource;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class AvailabilityMapParameter extends MapParameter<Availabiliby> {

    protected AvailabilityMapParameter(Availabiliby source) {
        super(source);
    }

    @Override
    public SqlParameterSource sourceToSqlParameter() {
        final Map<String, Object> params = new HashMap<>();
        params.put("id", UUID.randomUUID());
        params.put("id_medicament", source.getIdMedicament());
        params.put("code_cnes", source.getCodeCnes());
        params.put("quantity", source.getQuantity());
        params.put("restriction", source.getRestriction());
        return null;
    }

}
