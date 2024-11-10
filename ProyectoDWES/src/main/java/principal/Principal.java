//package principal;
//
//import java.io.FileInputStream;
//import java.io.FileNotFoundException;
//import java.io.IOException;
//import java.sql.Connection;
//import java.sql.PreparedStatement;
//import java.sql.SQLException;
//import java.util.Properties;
//import java.util.Scanner;
//
//import com.mysql.cj.jdbc.MysqlDataSource;
//
//import control.Controlador;
//import control.ServiciosCredenciales;
//import control.ServiciosEjemplar;
//import control.ServiciosMensaje;
//import control.ServiciosPersona;
//import control.ServiciosPlanta;
//import dao.PlantaDao;
//import modelo.Planta;
//
//public class Principal {
//	
//	public static void main(String[] args) {
//		
//		System.out.println("INI");
//	
//		Scanner teclado = new Scanner(System.in);
//		System.out.println("Dame el codigo de una nueva Planta");
//		String codigo = teclado.nextLine();
//		
//		System.out.println("Dame el nombre comun de una nueva Planta");
//		String nombrecomun = teclado.nextLine();
//		
//		System.out.println("Dame el nombre cientifico de una nueva Planta");
//		String nombrecientifico = teclado.nextLine();
//		
//		Planta nueva = new Planta(codigo, nombrecomun, nombrecientifico);
//		
//		Connection con;
//		MysqlDataSource m = new MysqlDataSource();
//		Properties prop = null;
//		FileInputStream fis;
//		
//		String url;
//		String usuario;
//		String password;
//		
//		try {
//			
//			fis = new FileInputStream("src/main/resources/db.properties");
//			prop.load(fis);
//			url = prop.getProperty("url");
//			usuario = prop.getProperty("usuario");
//			password = prop.getProperty("password");
//			m.setUrl(url);
//			m.setUser(usuario);
//			m.setPassword(password);
//			
//			con = m.getConnection();
//						
//			String sql = "INSERT INTO plantas(codigo, nombrecomun, nombrecientifico) VALUES(?,?,?)";
//
//			
//			PreparedStatement ps = con.prepareStatement(sql);
//			ps.setString(1, nueva.getCodigo());
//			ps.setString(2, nueva.getnombreComun());
//			ps.setString(3, nueva.getnombreCientifico());
//			
//			
//			ps.executeUpdate();
//			ps.execute();
//			con.close();
//			
//		} catch (SQLException e) {
//			System.out.println("Se ha producido una SQLException : " +e.getLocalizedMessage());
//			e.printStackTrace();
//		} catch (FileNotFoundException e) {
//			System.out.println("Se ha producido una FileNotFoundException : " +e.getLocalizedMessage());			e.printStackTrace();
//		} catch (IOException e) {
//			System.out.println("Se ha producido una IOException : " +e.getLocalizedMessage());	
//			e.printStackTrace();
//		}
//		}
//	}

package principal;

import java.util.Scanner;

import fachadaVivero.FachadaViveroPrincipal;
import conexion.ConexionBBDD;

public class Principal {

	public static void main(String[] args) {
		
		FachadaViveroPrincipal portal = FachadaViveroPrincipal.getPortal();
		
		Scanner in = new Scanner (System.in);
		
		System.out.println("Programa de gestión de un invernadero");
		
		portal.menuPrincipal();

	}

}

