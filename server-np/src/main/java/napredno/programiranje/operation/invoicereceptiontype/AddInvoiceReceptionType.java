package napredno.programiranje.operation.invoicereceptiontype;

import napredno.programiranje.domain.InvoiceReceptionType;
import napredno.programiranje.operation.AbstractGenericOperation;

/**
 * Konkretna implementacija genericke operacije za dodavanje novog tipa prijema fakture u bazu podataka.
 * 
 * Operacija zahteva proveru preduslova da se obezbedi validnost podataka o tipu prijema fakture, a zatim
 * vrsi dodavanje tog tipa u bazu podataka.
 * 
 * @author Isidora Vidojevic
 * 
 */

public class AddInvoiceReceptionType extends AbstractGenericOperation{

	/**
     * {@inheritDoc}
     * 
     * Proverava da li je parametar validan i da li je instanca klase InvoiceReceptionType.
     * 
     * @param param Parametar operacije koji predstavlja tip prijema fakture za dodavanje
     * @throws Exception Ukoliko parametar nije validan
     */
    @Override
    protected void preconditions(Object param) throws Exception {
        if (param == null || !(param instanceof InvoiceReceptionType)) {
            throw new Exception("Invalid invoice reception type data!");
        }
    }

    /**
     * {@inheritDoc}
     * 
     * Ova implementacija metode dodaje nov tip prijema fakture u bazu podataka.
     * 
     * @param param Parametar operacije koji predstavlja tip prijema fakture za dodavanje
     * @throws Exception Ukoliko operacija ne uspe
     */
    @Override
    protected void executeOperation(Object param) throws Exception {
        repository.add((InvoiceReceptionType) param);
    }
    
}