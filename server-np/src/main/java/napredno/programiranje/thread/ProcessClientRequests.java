package napredno.programiranje.thread;

import java.net.Socket;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javafx.util.Pair;
import napredno.programiranje.communication.Receiver;
import napredno.programiranje.communication.Request;
import napredno.programiranje.communication.Response;
import napredno.programiranje.communication.Sender;
import napredno.programiranje.controller.Controller;
import napredno.programiranje.domain.City;
import napredno.programiranje.domain.Customer;
import napredno.programiranje.domain.GenericEntity;
import napredno.programiranje.domain.Invoice;
import napredno.programiranje.domain.InvoiceItem;
import napredno.programiranje.domain.InvoiceReceptionType;
import napredno.programiranje.domain.Product;
import napredno.programiranje.domain.User;
import napredno.programiranje.server.Server;

public class ProcessClientRequests extends Thread {

	Socket socket;
    Sender sender;
    Receiver receiver;
    Server server;
    boolean signal = true;
    User user;

    public ProcessClientRequests(Server server, Socket socket) {
        this.socket = socket;
        this.server = server;
        sender = new Sender(socket);
        receiver = new Receiver(socket);
    }

    @Override
    public void run() {

        while (signal) {
            try {
                Request request = (Request) receiver.receive();
                Response response = null;
                try {
                    switch (request.getOperation()) {
                        case LOGIN:
                            User u = (User) request.getArgument();
                            user = Controller.getInstance().login(u.getUsername(), u.getPassword());
                            int number = server.addNewUser(user);
                            if(number == 0){
                                response = new Response(user, null);
                                server.addUser(this);
                            } else if(number == -1){
                                response = new Response(null, new Exception("Korisnik je već prijavljen!"));
                            }
                            break;
                        case LOGOUT:
                            server.removeUser(this);
                            response = new Response("Uspešno ste se odjavili!", null);
                            signal = false;
                            break;
                        case ADD_PRODUCT:
                            Product productInsert = (Product) request.getArgument();
                            response = new Response(Controller.getInstance().addProduct(productInsert), null);
                            break;
                        case GET_ALL_PRODUCTS:
                            List<GenericEntity> products = Controller.getInstance().getAllProducts();
                            response = new Response(products, null);
                            break;
                        case EDIT_PRODUCT:
                            Product productEdit = (Product) request.getArgument();
                            response = new Response(Controller.getInstance().editProduct(productEdit), null);
                            break;
                        case DELETE_PRODUCT:
                            Product productDelete = (Product) request.getArgument();
                            response = new Response(Controller.getInstance().deleteProduct(productDelete), null);
                            break;
                        case ADD_CUSTOMER:
                            Customer customerInsert = (Customer) request.getArgument();
                            response = new Response(Controller.getInstance().addCustomer(customerInsert), null);
                            break;
                        case DELETE_CUSTOMER:
                            Customer customerDelete = (Customer) request.getArgument();
                            response = new Response(Controller.getInstance().deleteCustomer(customerDelete), null);
                            break;
                        case GET_ALL_CUSTOMERS:
                            List<GenericEntity> customers = Controller.getInstance().getAllCustomers();
                            response = new Response(customers, null);
                            break;
                        case EDIT_CUSTOMER:
                            Customer customerEdit = (Customer) request.getArgument();
                            response = new Response(Controller.getInstance().editCustomer(customerEdit), null);
                            break;
                        case ADD_INVOICE:
                            Invoice invoiceInsert = (Invoice) request.getArgument();
                            response = new Response(Controller.getInstance().addInvoice(invoiceInsert), null);
                            break;
                        case CANCEL_INVOICE:
                            Invoice invoice = (Invoice) request.getArgument();
                            response = new Response(Controller.getInstance().cancelInvoice(invoice), null);
                            break;
                        case GET_ALL_INVOICES:
                            List<GenericEntity> invoices = Controller.getInstance().getAllInvoices();
                            response = new Response(invoices, null);
                            break;
                        case GET_ALL_INVOICES_PARAMETER:
                            List<Invoice> invoicesParameter = Controller.getInstance().getAllInvoicesParameter((Pair)request.getArgument());
                            response = new Response(invoicesParameter, null);
                            break;
                        case ADD_INVOICE_ITEM:
                            InvoiceItem invoiceItemInsert = (InvoiceItem) request.getArgument();
                            response = new Response(Controller.getInstance().addInvoiceItem(invoiceItemInsert), null);
                            break;                       
                        case GET_ALL_INVOICE_ITEMS:
                            List<GenericEntity> invoiceItems = Controller.getInstance().getAllInvoiceItems();
                            response = new Response(invoiceItems, null);
                            break;
                        case GET_ALL_INVOICE_ITEMS_PARAMETER:
                            List<InvoiceItem> invoiceItemsParameter = Controller.getInstance().getAllInvoiceItemsParameter((Pair)request.getArgument());
                            response = new Response(invoiceItemsParameter, null);
                            break;
                        case ADD_INVOICE_RECEPTION_TYPE:
                            InvoiceReceptionType invoiceTypeInsert = (InvoiceReceptionType) request.getArgument();
                            response = new Response(Controller.getInstance().addInvoiceReceptionType(invoiceTypeInsert), null);
                            break;
                        case GET_ALL_CITIES:
                        	List<GenericEntity> cities = Controller.getInstance().getAllCities();
                        	response = new Response(cities, null);
                        	break;
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                    response = new Response(null, e);
                }
                sender.send(response);
            } catch (Exception ex) {
                Logger.getLogger(ProcessClientRequests.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
    public User getUser(){
        return user;
    }
	
}
