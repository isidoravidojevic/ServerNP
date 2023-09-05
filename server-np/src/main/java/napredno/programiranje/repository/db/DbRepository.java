package napredno.programiranje.repository.db;

import napredno.programiranje.repository.Repository;

public interface DbRepository<T, K> extends Repository<T, K> {

	default void connect() throws Exception {
        DbConnectionFactory.getInstance().getConnection();
    }
    
    default void disconnect() throws Exception {
        DbConnectionFactory.getInstance().getConnection().close();
    }
    
    default void commit() throws Exception {
        DbConnectionFactory.getInstance().getConnection().commit();
    }
    
    default void rollback() throws Exception {
        DbConnectionFactory.getInstance().getConnection().rollback();
    }
}
