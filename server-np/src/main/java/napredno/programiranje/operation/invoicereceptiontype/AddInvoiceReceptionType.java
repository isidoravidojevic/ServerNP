package napredno.programiranje.operation.invoicereceptiontype;

import napredno.programiranje.domain.InvoiceReceptionType;
import napredno.programiranje.operation.AbstractGenericOperation;

public class AddInvoiceReceptionType extends AbstractGenericOperation{

    @Override
    protected void preconditions(Object param) throws Exception {
        if (param == null || !(param instanceof InvoiceReceptionType)) {
            throw new Exception("Invalid invoice reception type data!");
        }
    }

    @Override
    protected void executeOperation(Object param) throws Exception {
        repository.add((InvoiceReceptionType) param);
    }
    
}