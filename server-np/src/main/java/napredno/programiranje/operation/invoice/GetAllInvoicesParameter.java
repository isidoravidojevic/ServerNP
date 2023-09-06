package napredno.programiranje.operation.invoice;

import java.util.List;

import javafx.util.Pair;
import napredno.programiranje.domain.Invoice;
import napredno.programiranje.operation.AbstractGenericOperation;

/**
 * Konkretna implementacija genericke operacije za dobijanje svih faktura iz baze podataka na osnovu zadatih kriterijuma pretrage.
 * 
 * Operacija zahteva proveru preduslova da se obezbedi validnost parametara pretrage, a zatim
 * vrsi pretragu i dovlacenje svih faktura iz baze podataka koje odgovaraju zadatim kriterijumima.
 * 
 * @author Isidora Vidojevic
 * 
 */

public class GetAllInvoicesParameter extends AbstractGenericOperation{

	/**
     * Lista faktura koje su pronadjene na osnovu kriterijuma pretrage.
     */
    List<Invoice> invoices;
    
    /**
     * {@inheritDoc}
     * 
     * Proverava da li je parametar validan i da li je instanca klase Pair.
     * 
     * @param param Parametar operacije koji predstavlja kriterijume pretrage faktura
     * @throws Exception Ukoliko parametar nije validan
     */
    @Override
    protected void preconditions(Object param) throws Exception {
        if (param == null || !(param instanceof Pair)) {
            throw new Exception("Invalid data for invoice.");
        }
    }

    /**
     * {@inheritDoc}
     * 
     * Ova implementacija metode vrsi pretragu i dovlacenje svih faktura iz baze podataka na osnovu
     * zadatih kriterijuma pretrage.
     * 
     * @param param Parametar operacije koji predstavlja kriterijume pretrage faktura
     * @throws Exception Ukoliko operacija ne uspe
     */
    @Override
    protected void executeOperation(Object param) throws Exception {
       invoices = repository.getAllByCriteria(new Invoice(), param);
    }

    /**
     * Vraca listu faktura koje su pronadjene na osnovu kriterijuma pretrage.
     * 
     * @return Lista faktura koje odgovaraju zadatim kriterijumima pretrage
     */
    public List<Invoice> getInvoices() {
        return invoices;
    }
    
}
