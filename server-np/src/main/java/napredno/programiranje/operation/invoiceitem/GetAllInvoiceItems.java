package napredno.programiranje.operation.invoiceitem;

import java.util.List;

import napredno.programiranje.domain.GenericEntity;
import napredno.programiranje.domain.InvoiceItem;
import napredno.programiranje.operation.AbstractGenericOperation;

public class GetAllInvoiceItems extends AbstractGenericOperation{

    List<GenericEntity> invoiceItems;
    
    @Override
    protected void preconditions(Object param) throws Exception {
        
    }

    @Override
    protected void executeOperation(Object param) throws Exception {
        invoiceItems = repository.getAll((InvoiceItem) param);
    }
    
    public List<GenericEntity> getInvoiceItems() {
        return invoiceItems;
    }
}
