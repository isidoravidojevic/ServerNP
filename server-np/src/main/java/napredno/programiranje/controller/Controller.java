package napredno.programiranje.controller;

import java.util.List;

import javafx.util.Pair;
import napredno.programiranje.domain.City;
import napredno.programiranje.domain.Customer;
import napredno.programiranje.domain.GenericEntity;
import napredno.programiranje.domain.Invoice;
import napredno.programiranje.domain.InvoiceItem;
import napredno.programiranje.domain.InvoiceReceptionType;
import napredno.programiranje.domain.Product;
import napredno.programiranje.domain.User;
import napredno.programiranje.operation.AbstractGenericOperation;
import napredno.programiranje.operation.city.GetAllCities;
import napredno.programiranje.operation.customer.AddCustomer;
import napredno.programiranje.operation.customer.DeleteCustomer;
import napredno.programiranje.operation.customer.EditCustomer;
import napredno.programiranje.operation.customer.GetAllCustomers;
import napredno.programiranje.operation.invoice.AddInvoice;
import napredno.programiranje.operation.invoice.CancelInvoice;
import napredno.programiranje.operation.invoice.GetAllInvoices;
import napredno.programiranje.operation.invoice.GetAllInvoicesParameter;
import napredno.programiranje.operation.invoiceitem.AddInvoiceItem;
import napredno.programiranje.operation.invoiceitem.GetAllInvoiceItems;
import napredno.programiranje.operation.invoiceitem.GetAllInvoiceItemsParameter;
import napredno.programiranje.operation.invoicereceptiontype.AddInvoiceReceptionType;
import napredno.programiranje.operation.product.AddProduct;
import napredno.programiranje.operation.product.DeleteProduct;
import napredno.programiranje.operation.product.EditProduct;
import napredno.programiranje.operation.product.GetAllProducts;
import napredno.programiranje.operation.user.Login;
import napredno.programiranje.repository.Repository;
import napredno.programiranje.repository.db.impl.RepositoryDbGeneric;
import napredno.programiranje.operation.user.AddUser;

public class Controller {

	private final Repository repositoryDbGeneric;
    
    private static Controller controller;
    
    private Controller() {
        this.repositoryDbGeneric = new RepositoryDbGeneric();
    }
    
    public static Controller getInstance() {
        if (controller == null) {
            controller = new Controller();
        }
        return controller;
    }
    
    public User login(String username, String password) throws Exception {
        AbstractGenericOperation operation = new Login();
        operation.execute(new User());
        List<User> users = ((Login) operation).getUsers();
        for (User user : users) {
            if (user.getUsername().equals(username) && user.getPassword().equals(password)) {
                System.out.println("User found!");
                return user;
            }
        }
        throw new Exception("Korisnik ne postoji!");
    }
    
    public void AddUser(User user) throws Exception {
        AbstractGenericOperation operation = new AddUser();
        operation.execute(user);
    }
    
    public List<GenericEntity> getAllProducts() throws Exception {
        AbstractGenericOperation getAllProducts = new GetAllProducts();
        getAllProducts.execute(new Product());
        return ((GetAllProducts) getAllProducts).getProducts();
    }
    
    public Object addProduct(Product product) throws Exception {
        AbstractGenericOperation saveProduct = new AddProduct();
        saveProduct.execute(product);
        return "Uspešno dodat proizvod.";
    }
    
    public Object editProduct(Product product) throws Exception {
        AbstractGenericOperation editProduct = new EditProduct();
        editProduct.execute(product);
        return ((EditProduct) editProduct).getNumber();
    }
    
    public Object deleteProduct(Product product) throws Exception {
        AbstractGenericOperation deleteProduct = new DeleteProduct();
        deleteProduct.execute(product);
        return ((DeleteProduct) deleteProduct).getNumber();  
    }
    
    public List<GenericEntity> getAllCustomers() throws Exception {
        AbstractGenericOperation operation  = new GetAllCustomers();
        operation.execute(new Customer());
        return ((GetAllCustomers) operation).getCustomers();
    }
    
    public Object addCustomer(Customer customer) throws Exception {
        AbstractGenericOperation saveCustomer = new AddCustomer();
        saveCustomer.execute(customer);
        return "Uspešno dodat kupac.";
    }
    
    public Object editCustomer(Customer customer) throws Exception {
        AbstractGenericOperation editCustomer = new EditCustomer();
        editCustomer.execute(customer);
        return ((EditCustomer) editCustomer).getNumber();
    }
    
    public Object deleteCustomer(Customer customer) throws Exception {
        AbstractGenericOperation deleteCustomer = new DeleteCustomer();
        deleteCustomer.execute(customer);
        return ((DeleteCustomer) deleteCustomer).getNumber();
    }
    
    public Object addInvoice(Invoice invoice) throws Exception {
        AbstractGenericOperation saveInvoice = new AddInvoice();
        saveInvoice.execute(invoice);
        return ((AddInvoice) saveInvoice).getId();
    }
    
    public Object cancelInvoice(Invoice invoice) throws Exception {
        AbstractGenericOperation cancelInvoice = new CancelInvoice();
        cancelInvoice.execute(invoice);
        return ((CancelInvoice) cancelInvoice).getNumber();
    }
    
    public List<GenericEntity> getAllInvoices() throws Exception {
        AbstractGenericOperation getAllInvoices = new GetAllInvoices();
        getAllInvoices.execute(new Invoice());
        return ((GetAllInvoices) getAllInvoices).getInvoices();
    }
    
    public List<Invoice> getAllInvoicesParameter(Pair pair) throws Exception {
        AbstractGenericOperation operation = new GetAllInvoicesParameter();
        operation.execute(pair);
        return ((GetAllInvoicesParameter) operation).getInvoices();
    }
    
    public Object addInvoiceReceptionType(InvoiceReceptionType invoiceReceptionType) throws Exception {
        AbstractGenericOperation operation = new AddInvoiceReceptionType();
        operation.execute(invoiceReceptionType);
        return "Uspešno dodat tip prijema fakture.";
    }
    
    public Object addInvoiceItem(InvoiceItem invoiceitem) throws Exception {
        AbstractGenericOperation operation = new AddInvoiceItem();
        operation.execute(invoiceitem);
        return "Uspešno dodata stavka fakture.";
    }
    
    public List<InvoiceItem> getAllInvoiceItemsParameter(Pair pair) throws Exception {
        AbstractGenericOperation operation = new GetAllInvoiceItemsParameter();
        operation.execute(pair);
        return ((GetAllInvoiceItemsParameter) operation).getInvoiceItems();
    }
    
    public List<GenericEntity> getAllInvoiceItems() throws Exception {
        AbstractGenericOperation operation = new GetAllInvoiceItems();
        operation.execute(new InvoiceItem());
        return ((GetAllInvoiceItems) operation).getInvoiceItems();
    }
    
    public List<GenericEntity> getAllCities() throws Exception {
        AbstractGenericOperation operation  = new GetAllCities();
        operation.execute(new City());
        return ((GetAllCities) operation).getCities();
    }
}
