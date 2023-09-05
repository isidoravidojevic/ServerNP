package napredno.programiranje.operation;

import napredno.programiranje.repository.db.DbRepository;
import napredno.programiranje.repository.db.impl.RepositoryDbGeneric;

public abstract class AbstractGenericOperation {

	protected final DbRepository repository;

    public AbstractGenericOperation() {
        this.repository = new RepositoryDbGeneric();
    }

    public final void execute(Object param) throws Exception {
        try {
            preconditions(param);
            startTransaction();
            executeOperation(param);
            commit();
        } catch (Exception ex) {
            ex.printStackTrace();
            rollback();
            throw ex;
        } finally {
            disconnect();
        }
    }

    // Operation-specific method
    protected abstract void preconditions(Object param) throws Exception;

    // Operation-specific method
    protected abstract void executeOperation(Object param) throws Exception;

    private void startTransaction() throws Exception {
        ((DbRepository) repository).connect();
    }

    private void commit() throws Exception {
        ((DbRepository) repository).commit();
    }

    private void rollback() throws Exception {
        ((DbRepository) repository).rollback();
    }

    private void disconnect() throws Exception {
        ((DbRepository) repository).disconnect();
    }
}
