package br.com.ufg.tcc.medicamentos.availabilty;

import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.UUID;

public class AvailabilityRowMapper implements RowMapper<Availabiliby> {

    @Override
    public Availabiliby mapRow(ResultSet resultSet, int i) throws SQLException {

        return Availabiliby.builder()
                           .id(UUID.fromString(resultSet.getString("id")))
                           .idMedicament(UUID.fromString(resultSet.getString("id_medicament")))
                           .quantity(resultSet.getInt("quantity"))
                           .restriction(resultSet.getString("restriction"))
                           .codeCnes(resultSet.getString("code_cnes"))
                           .build();
    }

}
