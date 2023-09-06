package napredno.programiranje.operation.invoiceitem;

import java.util.List;

import napredno.programiranje.domain.GenericEntity;
import napredno.programiranje.domain.InvoiceItem;
import napredno.programiranje.operation.AbstractGenericOperation;

/**
 * Konkretna implementacija genericke operacije za dobijanje svih stavki fakture iz baze podataka.
 * 
 * Operacija ne zahteva dodatne preduslove i jednostavno dovlaci sve stavke iz baze podataka.
 * 
 * @author Isidora Vidojevic
 * 
 */

public class GetAllInvoiceItems extends AbstractGenericOperation{

	/**
	 * Lista sa svim stavkama iz baze podataka
	 */
    List<GenericEntity> invoiceItems;
    
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
     * Ova implementacija metode dovlaci sve stavke iz baze podataka.
     */
    @Override
    protected void executeOperation(Object param) throws Exception {
        invoiceItems = repository.getAll((InvoiceItem) param);
    }
    
    /**
	 * Vraca listu stavki dobijenu iz baze podataka
	 * 
	 * @return lista stavki
	 */
    public List<GenericEntity> getInvoiceItems() {
        return invoiceItems;
    }
}
