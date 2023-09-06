package napredno.programiranje.operation.invoice;

import java.util.List;

import napredno.programiranje.domain.GenericEntity;
import napredno.programiranje.domain.Invoice;
import napredno.programiranje.operation.AbstractGenericOperation;

/**
 * Konkretna implementacija genericke operacije za dobijanje svih faktura iz baze podataka.
 * 
 * Operacija ne zahteva dodatne preduslove i jednostavno dovlaci sve fakture iz baze podataka.
 * 
 * @author Isidora Vidojevic
 * 
 */

public class GetAllInvoices extends AbstractGenericOperation {

	/**
	 * Lista sa svim fakturama iz baze podataka
	 */
    private List<GenericEntity> invoices;

    /**
	 * {@inheritDoc}
	 * 
	 */
    @Override
    protected void preconditions(Object param) throws Exception {
    }

    /**
     * {@inheritDoc}
     * 
     * Ova implementacija metode dovlaci sve fakture iz baze podataka.
     */
    @Override
    protected void executeOperation(Object param) throws Exception {
        invoices = repository.getAll((Invoice) param);
    }

    /**
	 * Vraca listu faktura dobijenu iz baze podataka
	 * 
	 * @return lista faktura
	 */
    public List<GenericEntity> getInvoices() {
        return invoices;
    }

}
