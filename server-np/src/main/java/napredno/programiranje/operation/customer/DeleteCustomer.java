package napredno.programiranje.operation.customer;

import napredno.programiranje.domain.Customer;
import napredno.programiranje.operation.AbstractGenericOperation;

/**
 * Konkretna implementacija genericke operacije za brisanje kupca iz baze podataka.
 * 
 * Operacija zahteva proveru preduslova da se obezbedi validnost podataka o kupcu, a zatim
 * vrsi brisanje kupca iz baze podataka.
 * 
 * @author Isidora Vidojevic
 * 
 */

public class DeleteCustomer extends AbstractGenericOperation {
    
	/**
     * Broj obrisanih kupaca iz baze podataka.
     */
    int number = -1;
    
    /**
     * {@inheritDoc}
     * 
     * Proverava da li je parametar validan i da li je instanca klase Customer.
     * 
     * @param param Parametar operacije koji predstavlja kupca za brisanje
     * @throws Exception Ukoliko parametar nije validan
     */
    @Override
    protected void preconditions(Object param) throws Exception {
        if(param == null || !(param instanceof Customer)){
            throw new Exception("Invalid customer data!");
        }
    }
    
    /**
     * {@inheritDoc}
     * 
     * Ova implementacija metode vrsi brisanje kupca iz baze podataka.
     * 
     * @param param Parametar operacije koji predstavlja kupca za brisanje
     * @throws Exception Ukoliko operacija ne uspe
     */
    @Override
    protected void executeOperation(Object param) throws Exception {
        number = repository.delete((Customer) param);
    }
    
    /**
     * Vraca broj obrisanih kupaca iz baze podataka.
     * 
     * @return Broj obrisanih kupaca
     */
    public int getNumber() {
        return number;
    }
}
