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
	
	public static void menuPrincipal() {
		
		Scanner scanner = new Scanner(System.in);
        int opcion = 0;

        while (opcion != 4) {
            System.out.println("=== Menú Principal ===");
            System.out.println("1. Menú Plantas");
            System.out.println("2. Menú Personas");
            System.out.println("3. Menú Ejemplares");
            System.out.println("3. Menú Mensajes");
            System.out.println("4. Salir");

            if (scanner.hasNextInt()) {
                opcion = scanner.nextInt();

                switch (opcion) {
                    case 1:
                        menuPlantas();
                        break;
                    case 2:
                        menuPersonas();
                        break;
                    case 3:
                        menuEjemplares();
                        break;
                    case 4:
                        menuEjemplares();
                        break;
                    case 5:
                        System.out.println("Saliendo del programa. ¡Hasta luego!");
                        break;
                    default:
                        System.out.println("Opción no válida. Por favor, elige entre 1 y 4.\n");
                }
            } else {
                System.out.println("Entrada no válida. Por favor, ingresa un número.\n");
                scanner.next(); 
            }
        }

        scanner.close();
    }
	
	public static void menuPlantas() {
		
		Scanner scanner = new Scanner(System.in);
        int opcion = 0;

        while (opcion != 4) {
            System.out.println("=== Menú Plantas ===");
            System.out.println("1. Insertar Nueva Planta");
            System.out.println("2. Modificar Planta");
            System.out.println("3. Eliminar Planta Existente");
            System.out.println("4. Salir");

            if (scanner.hasNextInt()) {
                opcion = scanner.nextInt();

                switch (opcion) {
                    case 1:
                        System.out.println("Has seleccionado la Opción 1.\n");
                        break;
                    case 2:
                        System.out.println("Has seleccionado la Opción 2.\n");
                        break;
                    case 3:
                        System.out.println("Has seleccionado la Opción 3.\n");
                        break;
                    case 4:
                        System.out.println("Saliendo del programa. ¡Hasta luego!");
                        break;
                    default:
                        System.out.println("Opción no válida. Por favor, elige entre 1 y 4.\n");
                }
            } else {
                System.out.println("Entrada no válida. Por favor, ingresa un número.\n");
                scanner.next(); 
            }
        }

        scanner.close();
    }
	
	
	Connection con;
	MysqlDataSource m = new MysqlDataSource();
	Properties prop = null;
	FileInputStream fis;
	
	String url;
	String usuario;
	
	
	}

