
package conexion;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

import com.mysql.cj.jdbc.MysqlDataSource;

public class ConexionBBDD {

	private static String URL;
	private static String USUARIO;
	private static String PASS;

	private static void cargarConfiguracion() {

		Properties propiedades = new Properties();
	    try (InputStream inputStream = ConexionBBDD.class.getClassLoader().getResourceAsStream("db.properties")) {
	        if (inputStream == null) {
	            System.out.println("Archivo de configuración no encontrado en el classpath");
	            return;
	        }
	        propiedades.load(inputStream);
	        
	        URL = propiedades.getProperty("url");
	        USUARIO = propiedades.getProperty("usuario");
	        PASS = propiedades.getProperty("password");

	    } catch (IOException e) {
	        e.printStackTrace();
	    }
	}

	public static Connection getConnection() throws SQLException {
		
		MysqlDataSource m = new MysqlDataSource();

		ConexionBBDD.cargarConfiguracion();
		m.setUrl(URL);
		m.setUser(USUARIO);
		m.setPassword(PASS);
		return m.getConnection();
	}

}
