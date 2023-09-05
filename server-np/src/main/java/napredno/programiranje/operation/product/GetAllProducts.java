package napredno.programiranje.operation.product;

import java.util.List;

import napredno.programiranje.domain.GenericEntity;
import napredno.programiranje.domain.Product;
import napredno.programiranje.operation.AbstractGenericOperation;

public class GetAllProducts extends AbstractGenericOperation {

    private List<GenericEntity> products;
    
    @Override
    protected void preconditions(Object param) throws Exception {
    }

    @Override
    protected void executeOperation(Object param) throws Exception {
        products = repository.getAll((Product) param);
    }
    
    public List<GenericEntity> getProducts() {
        return products;
    }

}
