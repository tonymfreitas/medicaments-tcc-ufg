package br.com.ufg.tcc.medicamentos.config.database;

import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionException;
import org.springframework.transaction.TransactionStatus;

public class CustomTransactionManager implements PlatformTransactionManager {

    private final PlatformTransactionManager wrapped;

    public CustomTransactionManager(final PlatformTransactionManager wrapped) {
        this.wrapped = wrapped;
    }

    @Override
    public TransactionStatus getTransaction(final TransactionDefinition definition) throws TransactionException {
        RoutingDataSource.setReadonlyDataSource(definition.isReadOnly());
        return wrapped.getTransaction(definition);
    }

    @Override
    public void commit(final TransactionStatus status) throws TransactionException {
        wrapped.commit(status);
    }

    @Override
    public void rollback(final TransactionStatus status) throws TransactionException {
        wrapped.rollback(status);
    }
}
