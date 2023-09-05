package napredno.programiranje.operation.invoice;

import napredno.programiranje.domain.Invoice;
import napredno.programiranje.operation.AbstractGenericOperation;

public class AddInvoice extends AbstractGenericOperation {

    long id;
    
    @Override
    protected void preconditions(Object param) throws Exception {
        if (param == null || !(param instanceof Invoice)) {
            throw new Exception("Invalid invoice data!");
        }
    }

    @Override
    protected void executeOperation(Object param) throws Exception {
        id = repository.add((Invoice)param);
    }

    public long getId() {
        return id;
    }

    
}
