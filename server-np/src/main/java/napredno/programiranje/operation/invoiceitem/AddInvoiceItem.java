package napredno.programiranje.operation.invoiceitem;

import napredno.programiranje.domain.InvoiceItem;
import napredno.programiranje.operation.AbstractGenericOperation;

public class AddInvoiceItem extends AbstractGenericOperation{

    @Override
    protected void preconditions(Object param) throws Exception {
        if (param == null || !(param instanceof InvoiceItem)) {
            throw new Exception("Invalid invoice item data!");
        }
    }

    @Override
    protected void executeOperation(Object param) throws Exception {
        repository.add((InvoiceItem) param);
    }
    
}