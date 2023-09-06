package napredno.programiranje.operation.invoice;

import napredno.programiranje.domain.Invoice;
import napredno.programiranje.operation.AbstractGenericOperation;

/**
 * Konkretna implementacija genericke operacije za storniranje fakture iz baze podataka.
 * 
 * Operacija zahteva proveru preduslova da se obezbedi validnost podataka o fakturi, a zatim
 * vrsi storniranje fakture u bazi podataka.
 * 
 * @author Isidora Vidojevic
 * 
 */

public class CancelInvoice extends AbstractGenericOperation{

	/**
     * Broj azuriranih (storniranih) faktura u bazi podataka.
     */
    int number = -1;
    
    /**
     * {@inheritDoc}
     * 
     * Proverava da li je parametar validan i da li je instanca klase Invoice.
     * 
     * @param param Parametar operacije koji predstavlja fakturu za storniranje
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
     * Ova implementacija metode vrsi storniranje fakture u bazi podataka.
     * 
     * @param param Parametar operacije koji predstavlja fakturu za storniranje
     * @throws Exception Ukoliko operacija ne uspe
     */
    @Override
    protected void executeOperation(Object param) throws Exception {
        number = repository.edit((Invoice)param);
    }

    /**
     * Vraca broj storniranih faktura u bazi podataka.
     * 
     * @return Broj storniranih faktura
     */
    public int getNumber() {
        return number;
    }
    
}
