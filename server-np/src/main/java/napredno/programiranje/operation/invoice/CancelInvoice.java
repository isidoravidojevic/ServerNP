package napredno.programiranje.operation.invoice;

import napredno.programiranje.domain.Invoice;
import napredno.programiranje.operation.AbstractGenericOperation;

public class CancelInvoice extends AbstractGenericOperation{

    int number = -1;
    
    @Override
    protected void preconditions(Object param) throws Exception {
        if (param == null || !(param instanceof Invoice)) {
            throw new Exception("Invalid invoice data!");
        }
    }

    @Override
    protected void executeOperation(Object param) throws Exception {
        number = repository.edit((Invoice)param);
    }

    public int getNumber() {
        return number;
    }
    
}
