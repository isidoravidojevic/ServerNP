package napredno.programiranje.repository.db;

import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Properties;

import napredno.programiranje.constants.ServerConstants;

public class DbConnectionFactory {

	private Connection connection;
	private static DbConnectionFactory instance;
	private static final String ENVIRONMENT = "test";

	private DbConnectionFactory() {
	}

	public static DbConnectionFactory getInstance() {
		if (instance == null) {
			instance = new DbConnectionFactory();
		}
		return instance;
	}

	public Connection getConnection() throws Exception {
		if (connection == null || connection.isClosed()) {
			String url = "";
			String username = "";
			String password = "";

			if (ENVIRONMENT.equals("test")) {
				url = "jdbc:mysql://localhost:3306/test_napredno_programiranje";
				username = "root";
				password = "";
			} else if (ENVIRONMENT.equals("dev")) {
				Properties properties = new Properties();
				properties.load(new FileInputStream(ServerConstants.FILE_PATH));
				url = properties.getProperty(ServerConstants.DB_CONFIG_URL);
				username = properties.getProperty(ServerConstants.DB_CONFIG_USERNAME);
				password = properties.getProperty(ServerConstants.DB_CONFIG_PASSWORD);
			} else {
				throw new Exception("Nepoznato okruzenje!");
			}

			connection = DriverManager.getConnection(url, username, password);
			connection.setAutoCommit(false);
		}
		return connection;
	}
}
