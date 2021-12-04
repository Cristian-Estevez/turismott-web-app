package persistence.commons;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class ProveedorDeConeccion {

	private static String url;
	private static Connection coneccion;
	
	static {
		Properties properties = new Properties();
		try {
			properties.load(ProveedorDeConeccion.class.getResourceAsStream("/env.properties"));
		} catch (IOException e) {
			System.err.println("Error en Proveedor de conección.");
		}
		url = properties.getProperty("datasource");
	}
	
	public static Properties readPropertiesFile(String fileName) {
		FileInputStream fis = null;
		Properties prop = null;
		try {
			fis = new FileInputStream(fileName);
			prop = new Properties();
			prop.load(fis);
		} catch (FileNotFoundException fnfe) {
			System.err.println("No se encontró el archivo para la funcion readPropertiesFile");
		} catch (IOException ioe) {
			System.err.println("error en funcion readProperties");
		} finally {
			try {
				fis.close();
			} catch (IOException e) {
				e.printStackTrace();				
			}
		}
		return prop;
	}
	
	public static Connection getConeccion() throws SQLException {
		try {
			Class.forName("org.sqlite.JDBC");
		} catch (ClassNotFoundException e) {
			throw new SQLException(e);
		}
		if (coneccion == null) {
			coneccion = DriverManager.getConnection(url);
		}
		return coneccion;
	}
	
}
