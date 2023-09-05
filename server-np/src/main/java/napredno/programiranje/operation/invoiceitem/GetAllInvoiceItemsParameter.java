package napredno.programiranje.operation.invoiceitem;

import java.util.List;

import javafx.util.Pair;
import napredno.programiranje.domain.InvoiceItem;
import napredno.programiranje.operation.AbstractGenericOperation;

public class GetAllInvoiceItemsParameter extends AbstractGenericOperation{

    List<InvoiceItem> invoiceItems;
    
    @Override
    protected void preconditions(Object param) throws Exception {
        if (param == null || !(param instanceof Pair)) {
            throw new Exception("Invalid data for invoice item.");
        }
    }

    @Override
    protected void executeOperation(Object param) throws Exception {
        invoiceItems = repository.getAllByCriteria(new InvoiceItem(), param);
    }

    public List<InvoiceItem> getInvoiceItems() {
        return invoiceItems;
    }
    
}
