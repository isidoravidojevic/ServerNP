package napredno.programiranje.operation.product;

import java.util.List;

import napredno.programiranje.domain.GenericEntity;
import napredno.programiranje.domain.Product;
import napredno.programiranje.operation.AbstractGenericOperation;

/**
 * Konkretna implementacija genericke operacije za dobijanje svih proizvoda iz baze podataka.
 * 
 * Operacija ne zahteva dodatne preduslove i jednostavno dovlaci sve proizvode iz baze podataka.
 * 
 * @author Isidora Vidojevic
 * 
 */


public class GetAllProducts extends AbstractGenericOperation {

	/**
	 * Lista sa svim proizvodima iz baze podataka
	 */
    private List<GenericEntity> products;
    
    /**
	 * {@inheritDoc}
	 * 
	 */
    @Override
    protected void preconditions(Object param) throws Exception {
    }

    /**
     * {@inheritDoc}
     * 
     * Ova implementacija metode dovlaci sve proizvode iz baze podataka.
     */
    @Override
    protected void executeOperation(Object param) throws Exception {
        products = repository.getAll((Product) param);
    }
    
    /**
	 * Vraca listu proizvoda dobijenu iz baze podataka
	 * 
	 * @return lista proizvoda
	 */
    public List<GenericEntity> getProducts() {
        return products;
    }

}
