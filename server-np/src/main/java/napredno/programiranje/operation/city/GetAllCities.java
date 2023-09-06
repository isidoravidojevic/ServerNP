package napredno.programiranje.operation.city;

import java.util.List;

import napredno.programiranje.domain.City;
import napredno.programiranje.domain.GenericEntity;
import napredno.programiranje.operation.AbstractGenericOperation;

/**
 * Konkretna implementacija genericke operacije za dobijanje svih gradova iz baze podataka.
 * 
 * Operacija ne zahteva dodatne preduslove i jednostavno dovlaci sve gradove iz baze podataka.
 * 
 * @author Isidora Vidojevic
 * 
 */

public class GetAllCities extends AbstractGenericOperation{

	/**
	 * Lista sa svim gradovima iz baze podataka
	 */
	private List<GenericEntity> cities;

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
     * Ova implementacija metode dovlaci sve gradove iz baze podataka.
     */
    @Override
    protected void executeOperation(Object param) throws Exception {
        cities = repository.getAll((City) param);
    }

    /**
	 * Vraca listu gradova dobijenu iz baze podataka
	 * 
	 * @return lista gradova
	 */
    public List<GenericEntity> getCities() {
        return cities;
    }

}
