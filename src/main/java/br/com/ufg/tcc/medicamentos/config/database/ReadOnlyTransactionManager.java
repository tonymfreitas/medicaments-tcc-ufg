package br.com.ufg.tcc.medicamentos.config.database;

import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.TransactionDefinition;

import javax.sql.DataSource;

public class ReadOnlyTransactionManager extends DataSourceTransactionManager {

    public ReadOnlyTransactionManager(final DataSource dataSource) {
        super(dataSource);
        setEnforceReadOnly(true);
    }

    @Override
    protected void doBegin(final Object transaction, final TransactionDefinition definition) {
        RoutingDataSource.setReadRoute();
        super.doBegin(transaction, definition);
    }

    @Override
    protected void doCleanupAfterCompletion(final Object transaction) {
        RoutingDataSource.setWriteRoute();
        super.doCleanupAfterCompletion(transaction);
    }
}

