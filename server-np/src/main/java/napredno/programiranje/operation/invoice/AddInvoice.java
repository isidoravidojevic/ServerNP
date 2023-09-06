package napredno.programiranje.operation.invoice;

import napredno.programiranje.domain.Invoice;
import napredno.programiranje.operation.AbstractGenericOperation;

/**
 * Konkretna implementacija genericke operacije za dodavanje nove fakture u bazu podataka.
 * 
 * Operacija zahteva proveru preduslova da se obezbedi validnost podataka o fakturi, a zatim
 * dodaje novu fakturu u bazu podataka.
 * 
 * @author Isidora Vidojevic
 * 
 */

public class AddInvoice extends AbstractGenericOperation {

	/**
     * ID nove dodate fakture u bazi podataka.
     */
    long id;
    
    /**
     * {@inheritDoc}
     * 
     * Proverava da li je parametar validan i da li je instanca klase Invoice.
     * 
     * @param param Parametar operacije koji predstavlja fakturu za dodavanje
     * @throws Exception Ukoliko parametar nije validan
     */
    @Override
    protected void preconditions(Object param) throws Exception {
        if (param == null || !(param instanceof Invoice)) {
            throw new Exception("Invalid invoice data!");
        }
    }

    /**
     * {@inheritDoc}
     * 
     * Ova implementacija metode vrsi dodavanje nove fakture u bazu podataka.
     * 
     * @param param Parametar operacije koji predstavlja fakturu za dodavanje
     * @throws Exception Ukoliko operacija ne uspe
     */
    @Override
    protected void executeOperation(Object param) throws Exception {
        id = repository.add((Invoice)param);
    }

    /**
     * Vraca ID nove dodate fakture u bazi podataka.
     * 
     * @return ID nove dodate fakture
     */
    public long getId() {
        return id;
    }

    
}
