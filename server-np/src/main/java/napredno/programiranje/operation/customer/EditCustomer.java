package napredno.programiranje.operation.customer;

import napredno.programiranje.domain.Customer;
import napredno.programiranje.operation.AbstractGenericOperation;

/**
 * Konkretna implementacija genericke operacije za izmenu podataka o kupcu iz baze podataka.
 * 
 * Operacija zahteva proveru preduslova da se obezbedi validnost podataka o kupcu, a zatim
 * vrsi izmenu tih podataka u bazi podataka.
 * 
 * @author Isidora Vidojevic
 * 
 */

public class EditCustomer extends AbstractGenericOperation {

	/**
     * Broj azuriranih kupaca u bazi podataka.
     */
    int number = -1;
    
    /**
     * {@inheritDoc}
     * 
     * Proverava da li je parametar validan i da li je instanca klase Customer.
     * 
     * @param param Parametar operacije koji predstavlja kupca za izmenu
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
     * Ova implementacija metode vrsi izmenu podataka o kupcu u bazi podataka.
     * 
     * @param param Parametar operacije koji predstavlja kupca za izmenu
     * @throws Exception Ukoliko operacija ne uspe
     */
    @Override
    protected void executeOperation(Object param) throws Exception {
        number = repository.edit((Customer)param);
    }
    
    /**
     * Vraca broj azuriranih kupaca u bazi podataka.
     * 
     * @return Broj azuriranih kupaca
     */
    public int getNumber() {
        return number;
    }
}