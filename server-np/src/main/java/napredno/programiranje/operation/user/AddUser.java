package napredno.programiranje.operation.user;

import java.util.List;

import napredno.programiranje.domain.User;
import napredno.programiranje.operation.AbstractGenericOperation;

/**
 * Konkretna implementacija genericke operacije za dodavanje novog korisnika u bazu podataka.
 * 
 * Operacija zahteva proveru preduslova da se obezbedi validnost podataka o korisniku, kao i da
 * ne postoji korisnik sa istim podacima u bazi podataka, a zatim vrsi dodavanje korisnika u bazu.
 * 
 * @author Isidora Vidojevic
 * 
 */

public class AddUser extends AbstractGenericOperation {

	/**
     * Lista svih korisnika u bazi podataka.
     */
    List<User> users;

    /**
     * {@inheritDoc}
     * 
     * Proverava da li su parametri validni i da li vec postoji korisnik sa istim podacima u bazi.
     * 
     * @param param Parametar operacije koji predstavlja podatke o korisniku koji se dodaje
     * @throws Exception Ukoliko parametri nisu validni ili postoji korisnik sa istim podacima
     */
    @Override
    protected void preconditions(Object param) throws Exception {
        users = repository.getAll(new User());
        User u = (User) param;
        if (param == null || !(param instanceof User)) {
            throw new Exception("Invalid user data!");
        }
        if (users.contains(u)) {
            throw new Exception("There is already the same user!");
        }
    }

    /**
     * {@inheritDoc}
     * 
     * Ova implementacija metode vrsi dodavanje novog korisnika u bazu podataka.
     * 
     * @param param Parametar operacije koji predstavlja podatke o korisniku koji se dodaje
     * @throws Exception Ukoliko operacija ne uspe
     */
    @Override
    protected void executeOperation(Object param) throws Exception {
        repository.add((User) param);
    }

}
