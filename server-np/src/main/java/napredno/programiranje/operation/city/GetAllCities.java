package napredno.programiranje.operation.city;

import java.util.List;

import napredno.programiranje.domain.City;
import napredno.programiranje.domain.GenericEntity;
import napredno.programiranje.operation.AbstractGenericOperation;

public class GetAllCities extends AbstractGenericOperation{

	private List<GenericEntity> cities;

    @Override
    protected void preconditions(Object param) throws Exception {

    }

    @Override
    protected void executeOperation(Object param) throws Exception {
        cities = repository.getAll((City) param);
    }

    public List<GenericEntity> getCities() {
        return cities;
    }

}
