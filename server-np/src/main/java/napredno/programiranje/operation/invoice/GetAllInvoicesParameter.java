package napredno.programiranje.operation.invoice;

import java.util.List;

import javafx.util.Pair;
import napredno.programiranje.domain.Invoice;
import napredno.programiranje.operation.AbstractGenericOperation;

public class GetAllInvoicesParameter extends AbstractGenericOperation{

    List<Invoice> invoices;
    
    @Override
    protected void preconditions(Object param) throws Exception {
        if (param == null || !(param instanceof Pair)) {
            throw new Exception("Invalid data for invoice.");
        }
    }

    @Override
    protected void executeOperation(Object param) throws Exception {
       invoices = repository.getAllByCriteria(new Invoice(), param);
    }

    public List<Invoice> getInvoices() {
        return invoices;
    }
    
}
