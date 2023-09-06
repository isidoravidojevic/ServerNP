package napredno.programiranje.operation.producer;

import java.util.List;
import napredno.programiranje.domain.GenericEntity;
import napredno.programiranje.domain.Producer;
import napredno.programiranje.operation.AbstractGenericOperation;

/**
 * Konkretna implementacija genericke operacije za dobijanje svih proizvodjaca iz baze podataka.
 * 
 * Operacija ne zahteva dodatne preduslove i jednostavno dovlaci sve proizvodjace iz baze podataka.
 * 
 * @author Isidora Vidojevic
 * 
 */

public class GetAllProducers extends AbstractGenericOperation{

	/**
	 * Lista sa svim proizvodjacima iz baze podataka
	 */
	private List<GenericEntity> producers;

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
     * Ova implementacija metode dovlaci sve proizvodjace iz baze podataka.
     */
    @Override
    protected void executeOperation(Object param) throws Exception {
    	producers = repository.getAll((Producer) param);
    }

    /**
	 * Vraca listu proizvodjaca dobijenu iz baze podataka
	 * 
	 * @return lista proizvodjaca
	 */
    public List<GenericEntity> getProducers() {
        return producers;
    }
}
