package napredno.programiranje.operation.invoiceitem;

import napredno.programiranje.domain.InvoiceItem;
import napredno.programiranje.operation.AbstractGenericOperation;

/**
 * Konkretna implementacija genericke operacije za dodavanje nove stavke fakture u bazu podataka.
 * 
 * Operacija zahteva proveru preduslova da se obezbedi validnost podataka o stavki, a zatim
 * dodaje novu stavku u bazu podataka.
 * 
 * @author Isidora Vidojevic
 * 
 */

public class AddInvoiceItem extends AbstractGenericOperation{

	/**
     * {@inheritDoc}
     * 
     * Proverava da li je parametar validan i da li je instanca klase InvoiceItem.
     * 
     * @param param Parametar operacije koji predstavlja stavku za dodavanje
     * @throws Exception Ukoliko parametar nije validan
     */
    @Override
    protected void preconditions(Object param) throws Exception {
        if (param == null || !(param instanceof InvoiceItem)) {
            throw new Exception("Invalid invoice item data!");
        }
    }

    /**
     * {@inheritDoc}
     * 
     * Ova implementacija metode dodaje novu stavku u bazu podataka.
     * 
     * @param param Parametar operacije koji predstavlja stavku za dodavanje
     * @throws Exception Ukoliko operacija ne uspe
     */
    @Override
    protected void executeOperation(Object param) throws Exception {
        repository.add((InvoiceItem) param);
    }
    
}