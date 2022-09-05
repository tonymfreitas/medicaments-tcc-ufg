package br.com.ufg.tcc.medicamentos.availabilty;

import br.com.ufg.tcc.medicamentos.common.JdbcNamedTemplateOperations;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.support.TransactionTemplate;

import java.util.List;
import java.util.Map;

import static br.com.ufg.tcc.medicamentos.common.QueryFileReader.getContentFromQueryFile;

@Repository
public class AvailabilibyRepository {

    private static final String SAVE_AVAILABILITY = getContentFromQueryFile(
            "/save-availability.sql");

    private static final String LIST_ALL_AVAILABILITY = getContentFromQueryFile(
            "/list-all-availability.sql");

    private static final String LIST_AVAILABILITY_BY_STATE = getContentFromQueryFile(
            "/list-availability-by-state.sql");

    private final JdbcNamedTemplateOperations jdbcTemplate;
    private final TransactionTemplate readTransactionTemplate;

    public AvailabilibyRepository(JdbcNamedTemplateOperations jdbcTemplate,
                                  TransactionTemplate readTransactionTemplate) {
        this.jdbcTemplate = jdbcTemplate;
        this.readTransactionTemplate = readTransactionTemplate;
    }



    public void save(final Availabiliby availabiliby) {
        jdbcTemplate.saveOrUpdate(
                SAVE_AVAILABILITY,
                new AvailabilityMapParameter(availabiliby).sourceToSqlParameter());
    }

    public List<Availabiliby> getAll() {
        return jdbcTemplate.query(LIST_ALL_AVAILABILITY, Map.of(), new AvailabilityRowMapper());
    }

    public List<Availabiliby> findByState(final String state) {
        return jdbcTemplate.query(LIST_AVAILABILITY_BY_STATE, Map.of("state", state), new AvailabilityRowMapper());
    }
}
