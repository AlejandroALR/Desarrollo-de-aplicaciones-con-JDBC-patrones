package conexion;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;

import com.mysql.cj.jdbc.MysqlDataSource;

import dao.CredencialesDao;
import dao.EjemplarDao;
import dao.MensajeDao;
import dao.PersonaDao;
import dao.PlantaDao;

public class ConexionBBDD {

	private static Connection con;
	
	private static ConexionBBDD f;
	
	public static ConexionBBDD getInstance() {
		if (f == null)
			f = new ConexionBBDD();
		return f;
	}
	
	
	// Patron factory conexion 
	
	public static Connection realizaConexion() {
		Properties prop = new Properties();
		MysqlDataSource m = new MysqlDataSource();
		FileInputStream fis;
		
		try {
			fis = new FileInputStream("src/main/resources/db.properties");
			
			prop.load(fis);
			
			m.setUrl(prop.getProperty("url"));
			m.setPassword(prop.getProperty("password"));
			m.setUser(prop.getProperty("usuario"));
			
			con = m.getConnection();
			
		} catch (FileNotFoundException e) {
			System.out.println("Error al acceder al fichero properties" + e.getMessage());
		}catch (IOException e) {
			System.out.println("Error al leer las propiedades del fiechro properties" + e.getMessage());
		}catch (SQLException e) {
			System.out.println("Error al conectar a la base de datos: usuarios, password....");
		}
		return con;
	}
	
	public static void cerrarConexion() {
		try {
			if (con != null && !con.isClosed()) {
				con.close();
			}
		} catch (SQLException e) {
			System.out.println("Se ha producido una SQLException: " + e.getMessage());
			e.printStackTrace();
		}
	}
	
	public PlantaDao getPlantaDao() {
		return new PlantaDao(ConexionBBDD.realizaConexion());
	}
	
	public PersonaDao getPersonaDao() {
		return new PersonaDao(ConexionBBDD.realizaConexion());
	}
	
	public CredencialesDao getCredencialesDao() {
		return new CredencialesDao(ConexionBBDD.realizaConexion());
	}
	
	public EjemplarDao getEjemplarDao() {
		return new EjemplarDao(ConexionBBDD.realizaConexion());
	}
	
	
	public MensajeDao getMensajeDao() {
		return new MensajeDao(ConexionBBDD.realizaConexion());
	}

}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
/*
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
*/

