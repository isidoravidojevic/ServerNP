package napredno.programiranje.operation;

import napredno.programiranje.repository.db.DbRepository;
import napredno.programiranje.repository.db.impl.RepositoryDbGeneric;

/**
 * Apstraktna klasa koja predstavlja genericku operaciju nad bazom podataka.
 * Sadrzi osnovne metode za upravljanje transakcijom i izvrsavanje specificnih operacija.
 * Implementacije konkretnih operacija trebaju prosiriti ovu klasu i implementirati
 * apstraktne metode za preduslove specificne za svaku operaciju.
 * 
 * @author Isidora Vidojevic
 * 
 */

public abstract class AbstractGenericOperation {

	/** 
	 * Repozitorijum za pristup bazi podataka. 
	 */
	protected final DbRepository repository;

	/**
     * Konstruktor koji inicijalizuje repozitorijum za pristup bazi podataka.
     */
    public AbstractGenericOperation() {
        this.repository = new RepositoryDbGeneric();
    }

    /**
     * Metoda za izvrsavanje genericke operacije.
     *
     * @param param Parametar operacije (specifican za svaku operaciju)
     * @throws Exception Ukoliko operacija ne uspe
     */
    public final void execute(Object param) throws Exception {
        try {
            preconditions(param);
            startTransaction();
            executeOperation(param);
            commit();
        } catch (Exception ex) {
            ex.printStackTrace();
            rollback();
            throw ex;
        } finally {
            disconnect();
        }
    }

    /**
     * Apstraktna metoda koja definise preduslove za izvrsavanje operacije.
     *
     * @param param Parametar operacije (specifican za svaku operaciju)
     * @throws Exception Ukoliko preduslovi nisu ispunjeni
     */
    protected abstract void preconditions(Object param) throws Exception;

    /**
     * Apstraktna metoda koja definise izvrsavanje specificne operacije.
     *
     * @param param Parametar operacije (specifican za svaku operaciju)
     * @throws Exception Ukoliko operacija ne uspe
     */
    protected abstract void executeOperation(Object param) throws Exception;

    /**
     * Pokrece transakciju povezivanjem sa repozitorijumom za pristup bazi podataka.
     *
     * @throws Exception Ukoliko nije moguce uspostaviti vezu sa bazom podataka
     */
    private void startTransaction() throws Exception {
        ((DbRepository) repository).connect();
    }

    /**
     * Potvrdjuje transakciju, cime se cuvaju promene u bazi podataka.
     *
     * @throws Exception Ukoliko nije moguce potvrditi transakciju
     */
    private void commit() throws Exception {
        ((DbRepository) repository).commit();
    }

    /**
     * Ponistava transakciju i odbacuje promene u bazi podataka.
     *
     * @throws Exception Ukoliko nije moguce ponistiti transakciju
     */
    private void rollback() throws Exception {
        ((DbRepository) repository).rollback();
    }

    /**
     * Prekida vezu sa repozitorijumom za pristup bazi podataka.
     *
     * @throws Exception Ukoliko nije moguce prekinuti vezu
     */
    private void disconnect() throws Exception {
        ((DbRepository) repository).disconnect();
    }
}
