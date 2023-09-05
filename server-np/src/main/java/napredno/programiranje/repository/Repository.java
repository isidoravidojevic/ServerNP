package napredno.programiranje.repository;

import java.util.List;

public interface Repository<T, K> {

	long add(T param) throws Exception;

    int edit(T param) throws Exception;

    int delete(T param) throws Exception;

    List<T> getAll(T param) throws Exception;

    T getOne(T param) throws Exception;
    
    List<T> getAllByCriteria(T param,K criteria) throws Exception;
	
}
