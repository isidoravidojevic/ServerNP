package napredno.programiranje.operation.customer;

import napredno.programiranje.domain.Customer;
import napredno.programiranje.operation.AbstractGenericOperation;

/**
 * Konkretna implementacija genericke operacije za dodavanje novog kupca u bazu podataka.
 * 
 * Operacija zahteva proveru preduslova da se obezbedi validnost podataka o kupcu, a zatim
 * dodaje novog kupca u bazu podataka.
 * 
 * @author Isidora Vidojevic
 * 
 */

public class AddCustomer extends AbstractGenericOperation {

	/**
     * {@inheritDoc}
     * 
     * Proverava da li je parametar validan i da li je instanca klase Customer.
     * 
     * @param param Parametar operacije koji predstavlja kupca za dodavanje
     * @throws Exception Ukoliko parametar nije validan
     */
    @Override
    protected void preconditions(Object param) throws Exception {
        if (param == null || !(param instanceof Customer)) {
            throw new Exception("Invalid customer data!");
        }
    }

    /**
     * {@inheritDoc}
     * 
     * Ova implementacija metode dodaje novog kupca u bazu podataka.
     * 
     * @param param Parametar operacije koji predstavlja kupca za dodavanje
     * @throws Exception Ukoliko operacija ne uspe
     */
    @Override
    protected void executeOperation(Object param) throws Exception {
        repository.add((Customer)param);
    }
    
    
}
