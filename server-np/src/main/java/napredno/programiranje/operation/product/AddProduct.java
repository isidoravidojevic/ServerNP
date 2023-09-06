package napredno.programiranje.operation.product;

import napredno.programiranje.domain.Product;
import napredno.programiranje.operation.AbstractGenericOperation;

/**
 * Konkretna implementacija genericke operacije za dodavanje novog proizvoda u bazu podataka.
 * 
 * Operacija zahteva proveru preduslova da se obezbedi validnost podataka o proizvodu, a zatim
 * dodaje novi proizvod u bazu podataka.
 * 
 * @author Isidora Vidojevic
 * 
 */

public class AddProduct extends AbstractGenericOperation {
    
	/**
     * {@inheritDoc}
     * 
     * Proverava da li je parametar validan i da li je instanca klase Product.
     * 
     * @param param Parametar operacije koji predstavlja proizvod za dodavanje
     * @throws Exception Ukoliko parametar nije validan
     */
    @Override
    protected void preconditions(Object param) throws Exception {
        if (param == null || !(param instanceof Product)) {
            throw new Exception("Invalid product data!");
        }
    }

    /**
     * {@inheritDoc}
     * 
     * Ova implementacija metode dodaje nov proizvod u bazu podataka.
     * 
     * @param param Parametar operacije koji predstavlja proizvod za dodavanje
     * @throws Exception Ukoliko operacija ne uspe
     */
    @Override
    protected void executeOperation(Object param) throws Exception {
        repository.add((Product) param);
    }
    
}
