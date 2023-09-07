package napredno.programiranje.controller;

import static org.junit.jupiter.api.Assertions.*;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javafx.util.Pair;
import napredno.programiranje.domain.City;
import napredno.programiranje.domain.Customer;
import napredno.programiranje.domain.GenericEntity;
import napredno.programiranje.domain.Invoice;
import napredno.programiranje.domain.InvoiceItem;
import napredno.programiranje.domain.InvoiceReceptionType;
import napredno.programiranje.domain.Producer;
import napredno.programiranje.domain.Product;
import napredno.programiranje.domain.User;

class ControllerTest {

	private Controller controller;
	private Connection connection;

	@BeforeEach
	void setUp() throws Exception {
		controller = Controller.getInstance();
		connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/test_napredno_programiranje", "root", "");
	}

	@AfterEach
	void tearDown() throws Exception {
		controller = null;
		connection.close();
	}

	@Test
	public void testLoginValidUser() throws Exception {
		User testUser = new User();
		testUser.setFirstName("Marija");
		testUser.setLastName("Markovic");
		testUser.setUsername("masa");
		testUser.setPassword("masa");
		controller.AddUser(testUser);

		User loggedInUser = controller.login("masa", "masa");

		assertNotNull(loggedInUser);
		assertEquals("masa", loggedInUser.getUsername());

		String sql = "DELETE FROM user WHERE username = 'masa'";
		PreparedStatement preparedStatement = connection.prepareStatement(sql);
		preparedStatement.executeUpdate();
		preparedStatement.close();

	}

	@Test
	public void testLoginInvalidUser() {
		assertThrows(Exception.class, () -> controller.login("miona", "miona"));
	}

	@Test
	public void testLoginNullUser() {
		assertThrows(Exception.class, () -> controller.login(null, null));
	}

	@Test
	public void testAddUserSuccess() throws Exception {
		User userToAdd = new User();
		userToAdd.setFirstName("Anastasija");
		userToAdd.setLastName("Stambolic");
		userToAdd.setUsername("anastasija");
		userToAdd.setPassword("anastasija");

		controller.AddUser(userToAdd);

		boolean exists;
		String sql = "SELECT COUNT(*) FROM user WHERE username = ?";

		PreparedStatement preparedStatement = connection.prepareStatement(sql);
		preparedStatement.setString(1, "anastasija");
		ResultSet resultSet = preparedStatement.executeQuery();
		resultSet.next();
		int userCount = resultSet.getInt(1);
		if (userCount > 0) {
			exists = true;
		} else {
			exists = false;
		}

		assertTrue(exists);

		String sql2 = "DELETE FROM user WHERE username = 'anastasija'";
		PreparedStatement preparedStatement2 = connection.prepareStatement(sql2);
		preparedStatement2.executeUpdate();
		preparedStatement2.close();
	}

	@Test
	public void testAddUserAlreadyExists() {
		User existingUser = new User();
		existingUser.setUsername("isidora");
		existingUser.setPassword("isidora");

		assertThrows(Exception.class, () -> controller.AddUser(existingUser));
	}

	@Test
	public void testAddUserInvalidData() {
		assertThrows(Exception.class, () -> controller.AddUser(null));
	}

	@Test
	public void testGetAllProducts() throws Exception {
		List<GenericEntity> products = controller.getAllProducts();

		assertNotNull(products);

		assertEquals(2, products.size());
	}

	@Test
	public void testAddProduct() throws Exception {
		Product testProduct = new Product();
		testProduct.setProductName("Telefon");
		testProduct.setQuantity(10);
		testProduct.setMeasurementUnit("PSC");
		testProduct.setPurchasePrice(100.0);
		testProduct.setSellingPrice(150.0);
		testProduct.setProducer(new Producer(1, "Apple"));

		Object result = controller.addProduct(testProduct);

		assertNotNull(result);
		assertEquals("Uspešno dodat proizvod.", result);

		List<GenericEntity> products = controller.getAllProducts();

		boolean found = false;
		for (GenericEntity entity : products) {
			if (((Product) entity).getProductName().equals("Telefon")) {
				found = true;
				break;
			}
		}

		assertTrue(found);

		controller.deleteProduct(testProduct);
	}

	@Test
	public void testAddProductInvalidData() {
		assertThrows(Exception.class, () -> controller.addProduct(null));
	}

	@Test
	public void testEditProductSuccess() throws Exception {
		List<GenericEntity> products = controller.getAllProducts();
		Product originalProduct = null;

		for (int i = 0; i < products.size(); i++) {
			if (((Product) products.get(i)).getProductID() == 11) {
				originalProduct = (Product) products.get(i);
			}
		}

		assertNotNull(originalProduct);

		Product productToUpdate = new Product();
		productToUpdate.setProductID(11L);
		productToUpdate.setProductName("Nova vrednost");
		productToUpdate.setQuantity(50);
		productToUpdate.setMeasurementUnit("KG");
		productToUpdate.setPurchasePrice(20.0);
		productToUpdate.setSellingPrice(30.0);
		productToUpdate.setProducer(new Producer(1, "Apple"));

		int numberOfUpdatedProducts = (int) controller.editProduct(productToUpdate);

		assert numberOfUpdatedProducts > 0;

		int returnedToOriginal = (int) controller.editProduct(originalProduct);
		assert returnedToOriginal > 0;
	}

	@Test
	public void testEditProductInvalidProduct() {
		assertThrows(Exception.class, () -> controller.editProduct(null));
	}

	@Test
	public void testEditNonExistingProduct() throws Exception {
		Product productToUpdate = new Product();
		productToUpdate.setProductID(-5L);
		productToUpdate.setProductName("Nova vrednost");
		productToUpdate.setQuantity(50);
		productToUpdate.setMeasurementUnit("KG");
		productToUpdate.setPurchasePrice(20.0);
		productToUpdate.setSellingPrice(30.0);
		productToUpdate.setProducer(new Producer(1, "Apple"));
		int numberOfUpdatedProducts = (int) controller.editProduct(productToUpdate);

		assertEquals(0, numberOfUpdatedProducts);
	}

	@Test
	public void testDeleteProductSuccess() throws Exception {
		Product testProduct = new Product();
		testProduct.setProductName("Mis");
		testProduct.setQuantity(10);
		testProduct.setMeasurementUnit("KG");
		testProduct.setPurchasePrice(20.0);
		testProduct.setSellingPrice(30.0);
		testProduct.setProducer(new Producer(1, "Apple"));
		controller.addProduct(testProduct);

		int deletedProductCount = (int) controller.deleteProduct(testProduct);
		assertEquals(1, deletedProductCount);
	}

	@Test
	public void testDeleteNonExistingProduct() throws Exception {
		Product nonExistingProduct = new Product();
		nonExistingProduct.setProductID(-5);

		int numberOfDeletedProducts = (int) controller.deleteProduct(nonExistingProduct);

		assertEquals(0, numberOfDeletedProducts);
	}

	@Test
	public void testDeleteProductInvalidInput() {
		assertThrows(Exception.class, () -> controller.deleteProduct(null));
	}

	@Test
	public void testGetAllCustomers() throws Exception {
		List<GenericEntity> customers = controller.getAllCustomers();

		assertNotNull(customers);

		assertEquals(2, customers.size());
	}

	@Test
	public void testAddCustomer() throws Exception {
		Customer testCustomer = new Customer();
		testCustomer.setCustomerName("Metalpan");
		testCustomer.setAddress("Krusevacka 24");
		testCustomer.setVATnumber("333333333");
		testCustomer.setCompanyNumber("55555555");
		testCustomer.setCity(new City(1, "Aleksandrovac", "37230"));

		Object result = controller.addCustomer(testCustomer);

		assertNotNull(result);
		assertEquals("Uspešno dodat kupac.", result);

		List<GenericEntity> customers = controller.getAllCustomers();

		boolean found = false;
		for (GenericEntity entity : customers) {
			if (((Customer) entity).getCustomerName().equals("Metalpan")) {
				found = true;
				break;
			}
		}

		assertTrue(found);

		controller.deleteCustomer(testCustomer);
	}

	@Test
	public void testAddCustomerInvalidData() {
		assertThrows(Exception.class, () -> controller.addCustomer(null));
	}

	@Test
	public void testEditCustomerSuccess() throws Exception {
		List<GenericEntity> customers = controller.getAllCustomers();
		Customer originalCustomer = null;

		for (int i = 0; i < customers.size(); i++) {
			if (((Customer) customers.get(i)).getCustomerID() == 15) {
				originalCustomer = (Customer) customers.get(i);
			}
		}

		assertNotNull(originalCustomer);

		Customer customerToUpdate = new Customer();
		customerToUpdate.setCustomerID(15L);
		customerToUpdate.setCustomerName("Metalpan");
		customerToUpdate.setAddress("Krusevacka 24");
		customerToUpdate.setVATnumber("333333333");
		customerToUpdate.setCompanyNumber("55555555");
		customerToUpdate.setCity(new City(1, "Aleksandrovac", "37230"));

		int numberOfUpdatedCustomers = (int) controller.editCustomer(customerToUpdate);

		assert numberOfUpdatedCustomers > 0;

		int returnedToOriginal = (int) controller.editCustomer(originalCustomer);
		assert returnedToOriginal > 0;
	}

	@Test
	public void testEditCustomerInvalidProduct() {
		assertThrows(Exception.class, () -> controller.editCustomer(null));
	}

	@Test
	public void testEditNonExistingCustomer() throws Exception {
		Customer customerToUpdate = new Customer();
		customerToUpdate.setCustomerID(-15L);
		customerToUpdate.setCustomerName("Metalpan");
		customerToUpdate.setAddress("Krusevacka 24");
		customerToUpdate.setVATnumber("333333333");
		customerToUpdate.setCompanyNumber("55555555");
		customerToUpdate.setCity(new City(1, "Aleksandrovac", "37230"));
		int numberOfUpdatedCustomers = (int) controller.editCustomer(customerToUpdate);

		assertEquals(0, numberOfUpdatedCustomers);
	}

	@Test
	public void testDeleteCustomerSuccess() throws Exception {
		Customer testCustomer = new Customer();
		testCustomer.setCustomerName("Metalpan");
		testCustomer.setAddress("Krusevacka 24");
		testCustomer.setVATnumber("333333333");
		testCustomer.setCompanyNumber("55555555");
		testCustomer.setCity(new City(1, "Aleksandrovac", "37230"));
		controller.addCustomer(testCustomer);

		int deletedCustomerCount = (int) controller.deleteCustomer(testCustomer);
		assertEquals(1, deletedCustomerCount);
	}

	@Test
	public void testDeleteNonExistingCustomer() throws Exception {
		Customer nonExistingCustomer = new Customer();
		nonExistingCustomer.setCustomerID(-5);

		int numberOfDeletedCustomers = (int) controller.deleteCustomer(nonExistingCustomer);

		assertEquals(0, numberOfDeletedCustomers);
	}

	@Test
	public void testDeleteCustomerInvalidInput() {
		assertThrows(Exception.class, () -> controller.deleteCustomer(null));
	}

	@Test
	public void testAddInvoice() throws Exception {
		Customer testCustomer = new Customer();
		testCustomer.setCustomerID(15L);
		testCustomer.setCustomerName("Tehnomanija");
		testCustomer.setAddress("Zicka 1");
		testCustomer.setVATnumber("123456789");
		testCustomer.setCompanyNumber("12345678");
		testCustomer.setCity(new City(2, "Beograd", "11000"));
		Invoice invoice = new Invoice();
		invoice.setProcessed(true);
		invoice.setCanceled(false);
		invoice.setIssueDate(LocalDate.of(2020, 12, 17));
		invoice.setPaymentDeadline(LocalDate.of(2021, 12, 15));
		invoice.setVAT(20.0);
		invoice.setRebate(2.0);
		invoice.setAccountingBasis(1300.0);
		invoice.setTotalValue(BigDecimal.valueOf(2300.0));
		invoice.setCustomer(testCustomer);

		Object result = controller.addInvoice(invoice);

		assertNotNull(result);

		List<GenericEntity> invoices = controller.getAllInvoices();

		boolean found = false;
		for (GenericEntity entity : invoices) {
			if (((Invoice) entity).getInvoiceNumber() == (long) result) {
				found = true;
				break;
			}
		}

		assertTrue(found);

		String sql2 = "DELETE FROM invoice WHERE invoiceNumber = " + (long) result;
		PreparedStatement preparedStatement2 = connection.prepareStatement(sql2);
		preparedStatement2.executeUpdate();
		preparedStatement2.close();
	}

	@Test
	public void testAddInvoiceInvalidData() {
		assertThrows(Exception.class, () -> controller.addInvoice(null));
	}

	@Test
	public void testCancelInvoiceSuccess() throws Exception {
		Customer testCustomer = new Customer();
		testCustomer.setCustomerID(15L);
		testCustomer.setCustomerName("Tehnomanija");
		testCustomer.setAddress("Zicka 1");
		testCustomer.setVATnumber("123456789");
		testCustomer.setCompanyNumber("12345678");
		testCustomer.setCity(new City(2, "Beograd", "11000"));
		Invoice invoice = new Invoice();
		invoice.setProcessed(true);
		invoice.setCanceled(false);
		invoice.setIssueDate(LocalDate.of(2020, 12, 17));
		invoice.setPaymentDeadline(LocalDate.of(2021, 12, 15));
		invoice.setVAT(20.0);
		invoice.setRebate(2.0);
		invoice.setAccountingBasis(1300.0);
		invoice.setTotalValue(BigDecimal.valueOf(2300.0));
		invoice.setCustomer(testCustomer);

		long id = (long) controller.addInvoice(invoice);
		invoice.setInvoiceNumber(id);

		invoice.setCanceled(true);

		int numberOfCanceledInvoices = (int) controller.cancelInvoice(invoice);

		assert numberOfCanceledInvoices > 0;

		String sql2 = "DELETE FROM invoice WHERE invoiceNumber = " + id;
		PreparedStatement preparedStatement2 = connection.prepareStatement(sql2);
		preparedStatement2.executeUpdate();
		preparedStatement2.close();
	}

	@Test
	public void testCancelInvoiceInvalidProduct() {
		assertThrows(Exception.class, () -> controller.cancelInvoice(null));
	}

	@Test
	public void testCancelNonExistingInvoice() throws Exception {
		Invoice invoice = new Invoice();
		invoice.setInvoiceNumber(-5L);
		int numberOfCanceledInvoices = (int) controller.cancelInvoice(invoice);

		assertEquals(0, numberOfCanceledInvoices);
	}

	@Test
	public void testGetAllInvoices() throws Exception {
		List<GenericEntity> invoices = controller.getAllInvoices();

		assertNotNull(invoices);

		assertEquals(2, invoices.size());
	}

	@Test
	public void testGetAllInvoicesByCriteria() throws Exception {
		Pair criteria = new Pair("invoiceNumber", "23");

		List<Invoice> result = controller.getAllInvoicesParameter(criteria);

		assertNotNull(result);
		assertTrue(result.size() > 0);
	}

	@Test
	public void testGetAllInvoicesByInvalidCriteria() {
		assertThrows(Exception.class, () -> {
			Pair invalidCriteria = new Pair("customer", "12");
			controller.getAllInvoicesParameter(invalidCriteria);
		});
	}

	@Test
	public void testGetAllInvoicesByEmptyCriteria() {
		assertThrows(Exception.class, () -> {
			Pair emptyCriteria = new Pair("", "");
			controller.getAllInvoicesParameter(emptyCriteria);
		});
	}
	
	@Test
	public void testGetAllInvoicesByCriteriaNull() {
		assertThrows(Exception.class, () -> {
			controller.getAllInvoicesParameter(null);
		});
	}
	
	@Test
	public void testAddInvoiceReceptionType() throws Exception {
		Customer testCustomer = new Customer();
		testCustomer.setCustomerID(15L);
		testCustomer.setCustomerName("Tehnomanija");
		testCustomer.setAddress("Zicka 1");
		testCustomer.setVATnumber("123456789");
		testCustomer.setCompanyNumber("12345678");
		testCustomer.setCity(new City(2, "Beograd", "11000"));
		Invoice invoice = new Invoice();
		invoice.setInvoiceNumber(23L);
		invoice.setProcessed(true);
		invoice.setCanceled(true);
		invoice.setIssueDate(LocalDate.of(2005, 5, 5));
		invoice.setPaymentDeadline(LocalDate.of(2012, 12, 25));
		invoice.setVAT(20.0);
		invoice.setRebate(2.0);
		invoice.setAccountingBasis(16170000.0);
		invoice.setTotalValue(BigDecimal.valueOf(19404000.0));
		invoice.setCustomer(testCustomer);
		InvoiceReceptionType invoiceReceptionType = new InvoiceReceptionType();
		invoiceReceptionType.setInvoice(invoice);
		invoiceReceptionType.setCustomer(testCustomer);
		invoiceReceptionType.setInvoiceReceptionMeans("LICNO");

		Object result = controller.addInvoiceReceptionType(invoiceReceptionType);

		assertNotNull(result);
		assertEquals("Uspešno dodat tip prijema fakture.", result);

		String sql2 = "DELETE FROM invoicereceptiontype WHERE invoiceNumber = " + 23L + " AND customerID = " + 15L;
		PreparedStatement preparedStatement2 = connection.prepareStatement(sql2);
		preparedStatement2.executeUpdate();
		preparedStatement2.close();
	}
	
	@Test
	public void testAddInvoiceReceptionTypeInvalidData() {
		assertThrows(Exception.class, () -> controller.addInvoiceReceptionType(null));
	}
	
	@Test
	public void testGetAllProducers() throws Exception {
		List<GenericEntity> producers = controller.getAllProducers();

		assertNotNull(producers);

		assertEquals(6, producers.size());
	}
	
	@Test
	public void testGetAllCities() throws Exception {
		List<GenericEntity> cities = controller.getAllCities();

		assertNotNull(cities);

		assertEquals(4, cities.size());
	}
	
	@Test
	public void testGetAllInvoiceItems() throws Exception {
		List<GenericEntity> invoiceItems = controller.getAllInvoiceItems();

		assertNotNull(invoiceItems);

		assertEquals(5, invoiceItems.size());
	}
	
	@Test
	public void testGetAllInvoiceItemsByCriteria() throws Exception {
		Pair criteria = new Pair("invoiceitem.invoiceNumber", "23");

		List<InvoiceItem> result = controller.getAllInvoiceItemsParameter(criteria);

		assertNotNull(result);
		assertTrue(result.size() > 0);
	}
	
	@Test
	public void testGetAllInvoiceItemsByInvalidCriteria() {
		assertThrows(Exception.class, () -> {
			Pair invalidCriteria = new Pair("city", "12");
			controller.getAllInvoiceItemsParameter(invalidCriteria);
		});
	}

	@Test
	public void testGetAllInvoiceItemsByEmptyCriteria() {
		assertThrows(Exception.class, () -> {
			Pair emptyCriteria = new Pair("", "");
			controller.getAllInvoiceItemsParameter(emptyCriteria);
		});
	}
	
	@Test
	public void testGetAllInvoiceItemsByCriteriaNull() {
		assertThrows(Exception.class, () -> {
			controller.getAllInvoiceItemsParameter(null);
		});
	}
	
	@Test
	public void testAddInvoiceItem() throws Exception {
		Customer testCustomer = new Customer();
		testCustomer.setCustomerID(15L);
		testCustomer.setCustomerName("Tehnomanija");
		testCustomer.setAddress("Zicka 1");
		testCustomer.setVATnumber("123456789");
		testCustomer.setCompanyNumber("12345678");
		testCustomer.setCity(new City(2, "Beograd", "11000"));
		Invoice invoice = new Invoice();
		invoice.setInvoiceNumber(23L);
		invoice.setProcessed(true);
		invoice.setCanceled(true);
		invoice.setIssueDate(LocalDate.of(2005, 5, 5));
		invoice.setPaymentDeadline(LocalDate.of(2012, 12, 25));
		invoice.setVAT(20.0);
		invoice.setRebate(2.0);
		invoice.setAccountingBasis(16170000.0);
		invoice.setTotalValue(BigDecimal.valueOf(19404000.0));
		invoice.setCustomer(testCustomer);
		Product product = new Product(11L, "iPhone", 50, "PSC", 70000.0, 100000.0, new Producer(1L, "Apple"));
		InvoiceItem invoiceItem = new InvoiceItem();
		invoiceItem.setInvoice(invoice);
		invoiceItem.setProduct(product);
		invoiceItem.setQuantity(20);
		invoiceItem.setDescription("opis");
		invoiceItem.setItemPrice(300.0);

		Object result = controller.addInvoiceItem(invoiceItem);

		assertNotNull(result);
		assertEquals("Uspešno dodata stavka fakture.", result);
		
		List<GenericEntity> invoiceItems = controller.getAllInvoiceItems();
		assertNotNull(invoiceItems);
		
		invoiceItems.forEach(item -> {
			if(((InvoiceItem)item).getInvoice().equals(invoiceItem.getInvoice()) && ((InvoiceItem)item).getDescription().equals(invoiceItem.getDescription())
					&& ((InvoiceItem)item).getProduct().equals(invoiceItem.getProduct())) {
				invoiceItem.setNumber(((InvoiceItem)item).getNumber());
			}
		});

		String sql2 = "DELETE FROM invoiceitem WHERE invoiceNumber = " + 23L + " AND number = " + invoiceItem.getNumber();
		PreparedStatement preparedStatement2 = connection.prepareStatement(sql2);
		preparedStatement2.executeUpdate();
		preparedStatement2.close();
	}
	
	@Test
	public void testAddInvoiceItemInvalidData() {
		assertThrows(Exception.class, () -> controller.addInvoiceItem(null));
	}
	
}
