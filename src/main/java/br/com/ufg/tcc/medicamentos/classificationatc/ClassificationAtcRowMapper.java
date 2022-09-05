package br.com.ufg.tcc.medicamentos.classificationatc;

import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.UUID;

public class ClassificationAtcRowMapper implements RowMapper<ClassificationAtc> {

    @Override
    public ClassificationAtc mapRow(ResultSet resultSet, int i) throws SQLException {

        return ClassificationAtc.builder()
                                .id(UUID.fromString(resultSet.getString("id")))
                                .codeAtc(resultSet.getString("code_atc"))
                                .codeAtcParent(resultSet.getString("code_atc_parent"))
                                .level(resultSet.getInt("level"))
                                .name(resultSet.getString("name"))
                                .build();
    }

}
