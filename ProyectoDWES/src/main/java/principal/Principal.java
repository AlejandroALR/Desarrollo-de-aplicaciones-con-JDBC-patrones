package principal;

import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;
import java.util.Scanner;

import com.mysql.cj.jdbc.MysqlDataSource;

import control.Controlador;
import control.ServiciosCredenciales;
import control.ServiciosEjemplar;
import control.ServiciosMensaje;
import control.ServiciosPersona;
import control.ServiciosPlanta;
import dao.PlantaDao;
import modelo.Planta;

public class Principal {
	
	public static void main(String[] args) {
		
		
		
		
		
		System.out.println("INI");
	
	
		Scanner teclado = new Scanner(System.in);
		System.out.println("Dame el codigo de una nueva Planta");
		String codigo = teclado.nextLine();
		
		System.out.println("Dame el nombre comun de una nueva Planta");
		String nombrecomun = teclado.nextLine();
		
		System.out.println("Dame el nombre cientifico de una nueva Planta");
		String nombrecientifico = teclado.nextLine();
		
		Planta planta = new Planta(codigo, nombrecomun, nombrecientifico);
		
		ServiciosPlanta servPlanta = new ServiciosPlanta();
		
		
		if(!Controlador.getServicios().getServiciosPlanta().validarPlanta(planta)){
			System.out.println("Los valores introduciods para la planta son incorrectos. Repita el proceso.");
		}             
		
		int resultado = Controlador.getServicios().getServiciosPlanta().insertar(planta);


	}
	
	Connection con;
	MysqlDataSource m = new MysqlDataSource();
	Properties prop = null;
	FileInputStream fis;
	
	String url;
	String usuario;
	
	
	}

