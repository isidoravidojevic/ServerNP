package napredno.programiranje.operation.customer;

import napredno.programiranje.domain.Customer;
import napredno.programiranje.operation.AbstractGenericOperation;

public class DeleteCustomer extends AbstractGenericOperation {
    
    int number = -1;
    
    @Override
    protected void preconditions(Object param) throws Exception {
        if(param == null || !(param instanceof Customer)){
            throw new Exception("Invalid customer data!");
        }
    }
    
    @Override
    protected void executeOperation(Object param) throws Exception {
        number = repository.delete((Customer) param);
    }
    
    public int getNumber() {
        return number;
    }
}
