package br.com.ufg.tcc.medicamentos.classificationatc;

import br.com.ufg.tcc.medicamentos.common.JdbcNamedTemplateOperations;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.support.TransactionTemplate;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

import static br.com.ufg.tcc.medicamentos.common.Sql.FIND_CLASSIFICATION_ATC_BY_CODE;
import static br.com.ufg.tcc.medicamentos.common.Sql.FIND_CLASSIFICATION_ATC_BY_ID;
import static br.com.ufg.tcc.medicamentos.common.Sql.FIND_CLASSIFICATION_ATC_BY_PARENT;
import static br.com.ufg.tcc.medicamentos.common.Sql.LIST_ALL_CLASSIFICATION_ATC;
import static br.com.ufg.tcc.medicamentos.common.Sql.SAVE_CLASSIFICATION_ATC;
import static br.com.ufg.tcc.medicamentos.common.Sql.UPDATE_CLASSIFICATION_ATC;

@Repository
public class ClassificationAtcRepository {


    private final JdbcNamedTemplateOperations jdbcTemplate;
    private final TransactionTemplate readTransactionTemplate;

    public ClassificationAtcRepository(JdbcNamedTemplateOperations jdbcTemplate,
                                       TransactionTemplate readTransactionTemplate) {
        this.jdbcTemplate = jdbcTemplate;
        this.readTransactionTemplate = readTransactionTemplate;
    }



    public List<ClassificationAtc> listAll() {
        return jdbcTemplate.query(LIST_ALL_CLASSIFICATION_ATC.sql(), Map.of(), new ClassificationAtcRowMapper());
    }

    public void save(final ClassificationAtc classificationAtc) {
        jdbcTemplate.saveOrUpdate(
                SAVE_CLASSIFICATION_ATC.sql(),
                new ClassificationAtcMapParameter(classificationAtc).sourceToSqlParameter());
    }

    public List<ClassificationAtc> findByCodeAtcParent(final String codeAtc) {
        return jdbcTemplate.query(
                FIND_CLASSIFICATION_ATC_BY_PARENT.sql(),
                Map.of("code_atc_parent", codeAtc),
                new ClassificationAtcRowMapper());
    }

    public Optional<ClassificationAtc> findById(final UUID id) {
        return jdbcTemplate.querySingleResult(
                FIND_CLASSIFICATION_ATC_BY_ID.sql(),
                Map.of("id", id),
                new ClassificationAtcRowMapper());
    }

    public Optional<ClassificationAtc> findByCode(final String codeAtc) {
        return jdbcTemplate.querySingleResult(
                FIND_CLASSIFICATION_ATC_BY_CODE.sql(),
                Map.of("code_atc", codeAtc),
                new ClassificationAtcRowMapper());
    }

    public void update(final UUID id, final String name) {
        jdbcTemplate.saveOrUpdate(
                UPDATE_CLASSIFICATION_ATC.sql(),
                ClassificationAtcMapParameter.sourteToUpdate(id, name));
    }
}
