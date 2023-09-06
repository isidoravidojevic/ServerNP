package napredno.programiranje.operation.product;

import napredno.programiranje.domain.Product;
import napredno.programiranje.operation.AbstractGenericOperation;

/**
 * Konkretna implementacija genericke operacije za brisanje proizvoda iz baze podataka.
 * 
 * Operacija zahteva proveru preduslova da se obezbedi validnost podataka o proizvodu, a zatim
 * vrsi brisanje proizvoda iz baze podataka.
 * 
 * @author Isidora Vidojevic
 * 
 */

public class DeleteProduct extends AbstractGenericOperation {

	/**
     * Broj obrisanih proizvoda iz baze podataka.
     */
    int number = -1;
    
    /**
     * {@inheritDoc}
     * 
     * Proverava da li je parametar validan i da li je instanca klase Product.
     * 
     * @param param Parametar operacije koji predstavlja proizvod za brisanje
     * @throws Exception Ukoliko parametar nije validan
     */
    @Override
    protected void preconditions(Object param) throws Exception {
        if(param == null || !(param instanceof Product)){
            throw new Exception("Invalid product data!");
        }
    }

    /**
     * {@inheritDoc}
     * 
     * Ova implementacija metode vrsi brisanje proizvoda iz baze podataka.
     * 
     * @param param Parametar operacije koji predstavlja proizvod za brisanje
     * @throws Exception Ukoliko operacija ne uspe
     */
    @Override
    protected void executeOperation(Object param) throws Exception {
        number = repository.delete((Product)param);
    }

    /**
     * Vraca broj obrisanih proizvoda iz baze podataka.
     * 
     * @return Broj obrisanih proizvoda
     */
    public int getNumber() {
        return number;
    }
}
