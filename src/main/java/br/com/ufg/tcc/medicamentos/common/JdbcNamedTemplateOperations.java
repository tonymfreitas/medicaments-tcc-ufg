package br.com.ufg.tcc.medicamentos.common;


import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Component
public class JdbcNamedTemplateOperations {

    private final NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    public JdbcNamedTemplateOperations(NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
        this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
    }

    public <T> List<T> query(final String query, final Map<String, ?> parameters, final RowMapper<T> mapper) {
        return this.namedParameterJdbcTemplate.query(query, parameters, mapper);
    }

    public <T> List<T> query(final String query, final RowMapper<T> mapper) {
        return this.namedParameterJdbcTemplate.query(query, mapper);
    }

    public <T> Set<T> queryForSet(final String query, final Map<String, ?> parameters, final RowMapper<T> mapper) {
        return this.namedParameterJdbcTemplate.queryForStream(query, parameters, mapper)
                                              .collect(Collectors.toSet());
    }

    public <T> Optional<T> query(final String query,
                                 final Map<String, ?> parameters,
                                 final ResultSetExtractor<T> resultSetExtractor) {
        return Optional.ofNullable(this.namedParameterJdbcTemplate.query(query, parameters, resultSetExtractor));
    }

    public <T> List<T> query(final String query, final ResultSetExtractor<List<T>> resultSetExtractor) {
        return this.namedParameterJdbcTemplate.query(query, resultSetExtractor);
    }

    public <T> List<T> query(final String query, final Map<String, ?> parameters, final Class<T> result) {
        return this.namedParameterJdbcTemplate.queryForList(query, parameters, result);
    }

    public <T> Optional<T> querySingleResult(final String query,
                                             final Map<String, ?> parameters,
                                             final Class<T> result) {
        try {
            return Optional.ofNullable(this.namedParameterJdbcTemplate.queryForObject(query, parameters, result));
        } catch (EmptyResultDataAccessException ex) {
            return Optional.empty();
        }

    }

    public <T> Optional<T> querySingleResult(final String query,
                                             final Map<String, ?> parameters,
                                             final RowMapper<T> mapper) {
        return this.query(query, parameters, mapper)
                   .stream()
                   .findFirst();
    }

    public <T> Optional<T> querySingleResult(final String query, final RowMapper<T> mapper) {
        return this.query(query, mapper)
                   .stream()
                   .findFirst();
    }

    public void saveOrUpdate(final String query, final SqlParameterSource parameters) {
        this.namedParameterJdbcTemplate.update(query, parameters);
    }

    public void saveOrUpdateMap(final String query, final Map<String, ?> parameters) {
        this.namedParameterJdbcTemplate.update(query, parameters);
    }

    public void saveOrUpdateBatch(final String query, final SqlParameterSource[] parameters) {
        this.namedParameterJdbcTemplate.batchUpdate(query, parameters);
    }

}
