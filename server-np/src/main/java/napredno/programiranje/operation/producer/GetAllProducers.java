package napredno.programiranje.operation.producer;

import java.util.List;
import napredno.programiranje.domain.GenericEntity;
import napredno.programiranje.domain.Producer;
import napredno.programiranje.operation.AbstractGenericOperation;

public class GetAllProducers extends AbstractGenericOperation{

	private List<GenericEntity> producers;

    @Override
    protected void preconditions(Object param) throws Exception {

    }

    @Override
    protected void executeOperation(Object param) throws Exception {
    	producers = repository.getAll((Producer) param);
    }

    public List<GenericEntity> getProducers() {
        return producers;
    }
}
