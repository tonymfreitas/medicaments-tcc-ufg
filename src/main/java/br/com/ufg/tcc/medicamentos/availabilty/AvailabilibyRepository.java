package br.com.ufg.tcc.medicamentos.availabilty;

import br.com.ufg.tcc.medicamentos.common.JdbcNamedTemplateOperations;

import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

import static br.com.ufg.tcc.medicamentos.common.Sql.LIST_ALL_AVAILABILITY;
import static br.com.ufg.tcc.medicamentos.common.Sql.LIST_AVAILABILITY_BY_STATE;
import static br.com.ufg.tcc.medicamentos.common.Sql.SAVE_AVAILABILITY;

@Repository
public class AvailabilibyRepository {

    private final JdbcNamedTemplateOperations jdbcTemplate;

    public AvailabilibyRepository(JdbcNamedTemplateOperations jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public void save(final Availabiliby availabiliby) {
        jdbcTemplate.saveOrUpdate(
                SAVE_AVAILABILITY.sql(),
                new AvailabilityMapParameter(availabiliby).sourceToSqlParameter());
    }

    public List<Availabiliby> getAll() {
        return jdbcTemplate.query(LIST_ALL_AVAILABILITY.sql(), Map.of(), new AvailabilityRowMapper());
    }

    public List<Availabiliby> findByState(final String state) {
        return jdbcTemplate.query(
                LIST_AVAILABILITY_BY_STATE.sql(),
                Map.of("state", state),
                new AvailabilityRowMapper());
    }
}
