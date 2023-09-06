package napredno.programiranje.operation.invoiceitem;

import java.util.List;

import javafx.util.Pair;
import napredno.programiranje.domain.InvoiceItem;
import napredno.programiranje.operation.AbstractGenericOperation;

/**
 * Konkretna implementacija genericke operacije za dobijanje svih stavki fakture iz baze podataka na osnovu zadatih kriterijuma pretrage.
 * 
 * Operacija zahteva proveru preduslova da se obezbedi validnost parametara pretrage, a zatim
 * vrsi pretragu i dovlacenje svih stavki fakture iz baze podataka koje odgovaraju zadatim kriterijumima.
 * 
 * @author Isidora Vidojevic
 */

public class GetAllInvoiceItemsParameter extends AbstractGenericOperation{

	/**
     * Lista stavki fakture koje su pronadjene na osnovu kriterijuma pretrage.
     */
    List<InvoiceItem> invoiceItems;
    
    /**
     * {@inheritDoc}
     * 
     * Proverava da li je parametar validan i da li je instanca klase Pair.
     * 
     * @param param Parametar operacije koji predstavlja kriterijume pretrage stavki fakture
     * @throws Exception Ukoliko parametar nije validan
     */
    @Override
    protected void preconditions(Object param) throws Exception {
        if (param == null || !(param instanceof Pair)) {
            throw new Exception("Invalid data for invoice item.");
        }
    }

    /**
     * {@inheritDoc}
     * 
     * Ova implementacija metode vrsi pretragu i dovlacenje svih stavki fakture iz baze podataka na osnovu
     * zadatih kriterijuma pretrage.
     * 
     * @param param Parametar operacije koji predstavlja kriterijume pretrage stavki fakture
     * @throws Exception Ukoliko operacija ne uspe
     */
    @Override
    protected void executeOperation(Object param) throws Exception {
        invoiceItems = repository.getAllByCriteria(new InvoiceItem(), param);
    }

    /**
     * Vraca listu stavki fakture koje su pronadjene na osnovu kriterijuma pretrage.
     * 
     * @return Lista stavki fakture koje odgovaraju zadatim kriterijumima pretrage
     */
    public List<InvoiceItem> getInvoiceItems() {
        return invoiceItems;
    }
    
}
