package napredno.programiranje.operation.customer;

import napredno.programiranje.domain.Customer;
import napredno.programiranje.operation.AbstractGenericOperation;

public class AddCustomer extends AbstractGenericOperation {

    @Override
    protected void preconditions(Object param) throws Exception {
        if (param == null || !(param instanceof Customer)) {
            throw new Exception("Invalid customer data!");
        }
    }

    @Override
    protected void executeOperation(Object param) throws Exception {
        repository.add((Customer)param);
    }
    
    
}
