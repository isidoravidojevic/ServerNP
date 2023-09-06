package napredno.programiranje.operation.customer;

import java.util.List;

import napredno.programiranje.domain.Customer;
import napredno.programiranje.domain.GenericEntity;
import napredno.programiranje.operation.AbstractGenericOperation;

/**
 * Konkretna implementacija genericke operacije za dobijanje svih kupaca iz baze podataka.
 * 
 * Operacija ne zahteva dodatne preduslove i jednostavno dovlaci sve kupce iz baze podataka.
 * 
 * @author Isidora Vidojevic
 * 
 */

public class GetAllCustomers extends AbstractGenericOperation {

	/**
	 * Lista sa svim kupcima iz baze podataka
	 */
    private List<GenericEntity> customers;

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
     * Ova implementacija metode dovlaci sve kupce iz baze podataka.
     */
    @Override
    protected void executeOperation(Object param) throws Exception {
        customers = repository.getAll((Customer) param);
    }

    /**
	 * Vraca listu kupaca dobijenu iz baze podataka
	 * 
	 * @return lista kupaca
	 */
    public List<GenericEntity> getCustomers() {
        return customers;
    }

}
