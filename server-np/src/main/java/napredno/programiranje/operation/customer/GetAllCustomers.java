package napredno.programiranje.operation.customer;

import java.util.List;

import napredno.programiranje.domain.Customer;
import napredno.programiranje.domain.GenericEntity;
import napredno.programiranje.operation.AbstractGenericOperation;

public class GetAllCustomers extends AbstractGenericOperation {

    private List<GenericEntity> customers;

    @Override
    protected void preconditions(Object param) throws Exception {

    }

    @Override
    protected void executeOperation(Object param) throws Exception {
        customers = repository.getAll((Customer) param);
    }

    public List<GenericEntity> getCustomers() {
        return customers;
    }

}
