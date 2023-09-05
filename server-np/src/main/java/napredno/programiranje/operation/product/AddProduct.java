package napredno.programiranje.operation.product;

import napredno.programiranje.domain.Product;
import napredno.programiranje.operation.AbstractGenericOperation;

public class AddProduct extends AbstractGenericOperation {
    
    @Override
    protected void preconditions(Object param) throws Exception {
        if (param == null || !(param instanceof Product)) {
            throw new Exception("Invalid product data!");
        }
//        Product product = (Product) param;
//        if (product.getPrice().compareTo(new BigDecimal("0.00")) <= 0) {
//            throw new Exception("Price must be greater than zero!");
//        }
        //TODO Validation (Validator)
    }

    @Override
    protected void executeOperation(Object param) throws Exception {
        repository.add((Product) param);
    }
    
}
