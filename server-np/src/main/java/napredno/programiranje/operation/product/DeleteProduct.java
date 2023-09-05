package napredno.programiranje.operation.product;

import napredno.programiranje.domain.Product;
import napredno.programiranje.operation.AbstractGenericOperation;

public class DeleteProduct extends AbstractGenericOperation {

    int number = -1;
    
    @Override
    protected void preconditions(Object param) throws Exception {
        if(param == null || !(param instanceof Product)){
            throw new Exception("Invalid product data!");
        }
    }

    @Override
    protected void executeOperation(Object param) throws Exception {
        number = repository.delete((Product)param);
    }

    public int getNumber() {
        return number;
    }
}
