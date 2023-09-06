package napredno.programiranje.operation.product;

import napredno.programiranje.domain.Product;
import napredno.programiranje.operation.AbstractGenericOperation;

/**
 * Konkretna implementacija genericke operacije za izmenu podataka o proizvodu iz baze podataka.
 * 
 * Operacija zahteva proveru preduslova da se obezbedi validnost podataka o proizvodu, a zatim
 * vrsi izmenu tih podataka u bazi podataka.
 * 
 * @author Isidora Vidojevic
 * 
 */

public class EditProduct extends AbstractGenericOperation {

	/**
     * Broj azuriranih proizvoda u bazi podataka.
     */
    int number = -1;
    
    /**
     * {@inheritDoc}
     * 
     * Proverava da li je parametar validan i da li je instanca klase Product.
     * 
     * @param param Parametar operacije koji predstavlja proizvod za izmenu
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
     * Ova implementacija metode vrsi izmenu podataka o proizvodu u bazi podataka.
     * 
     * @param param Parametar operacije koji predstavlja proizvod za izmenu
     * @throws Exception Ukoliko operacija ne uspe
     */
    @Override
    protected void executeOperation(Object param) throws Exception {
        number = repository.edit((Product)param);
    }

    /**
     * Vraca broj azuriranih proizvoda u bazi podataka.
     * 
     * @return Broj azuriranih proizvoda
     */
    public int getNumber() {
        return number;
    }

}
