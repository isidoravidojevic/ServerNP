package napredno.programiranje.operation.user;

import java.util.List;

import napredno.programiranje.domain.User;
import napredno.programiranje.operation.AbstractGenericOperation;

/**
 * Konkretna implementacija genericke operacije za prijavljivanje korisnika.
 * 
 * Ova operacija ne zahteva posebne preduslove, vec samo dovlaci listu svih korisnika
 * iz baze podataka.
 * 
 * @author Isidora Vidojevic
 * 
 */

public class Login extends AbstractGenericOperation{

	/**
     * Lista svih korisnika iz baze podataka.
     */
    List<User> users;
    
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
     * Ova implementacija metode dovlaci sve korisnike iz baze podataka.
     * 
     * @param param Parametar operacije koji se ne koristi u ovom slucaju
     * @throws Exception Ukoliko operacija ne uspe
     */
    @Override
    protected void executeOperation(Object param) throws Exception {
        users = repository.getAll(new User());
    }

    /**
     * Vrati listu svih korisnika iz baze podataka.
     * 
     * @return Lista korisnika
     */
    public List<User> getUsers() {
        return users;
    }
}
