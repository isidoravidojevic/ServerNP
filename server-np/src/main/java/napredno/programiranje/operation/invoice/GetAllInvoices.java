package napredno.programiranje.operation.invoice;

import java.util.List;

import napredno.programiranje.domain.GenericEntity;
import napredno.programiranje.domain.Invoice;
import napredno.programiranje.operation.AbstractGenericOperation;

public class GetAllInvoices extends AbstractGenericOperation {

    private List<GenericEntity> invoices;

    @Override
    protected void preconditions(Object param) throws Exception {
    }

    @Override
    protected void executeOperation(Object param) throws Exception {
        invoices = repository.getAll((Invoice) param);
    }

    public List<GenericEntity> getInvoices() {
        return invoices;
    }

}
