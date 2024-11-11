package fachadaVivero;

import java.util.InputMismatchException;
import java.util.Scanner;

import control.Controlador;
import control.ServiciosCredenciales;
import control.ServiciosEjemplar;
import control.ServiciosMensaje;
import control.ServiciosPersona;
import control.ServiciosPlanta;
import control.ServiciosViveroCon;

public class FachadaViveroAdmin {

	Scanner in = new Scanner(System.in);
		
		private static FachadaViveroAdmin admin;
		
		private static FachadaViveroPrincipal portal = FachadaViveroPrincipal.getPortal();
		private static FachadaViveroEjemplares gestionEjemplares = FachadaViveroEjemplares.getPortal();
		private static FachadaViveroMensajes gestionMensajes = FachadaViveroMensajes.getPortal();
		private static FachadaViveroPlantas gestionPlantas = FachadaViveroPlantas.getPortal();
		
		ServiciosViveroCon conServicios = ServiciosViveroCon.getServicios();
	
		ServiciosCredenciales servCreden = conServicios.getServiciosCredencial();
		ServiciosEjemplar servEjem = conServicios.getServiciosEjemplar();
		ServiciosMensaje servMens = conServicios.getServiciosMensaje();
		ServiciosPersona servPers = conServicios.getServiciosPersona();
		ServiciosPlanta servPlan = conServicios.getServiciosPlanta();
		
		public static FachadaViveroAdmin getPortal() {
			if (admin==null)
					admin = new FachadaViveroAdmin();
			return admin;
		}
	
	public void menuAdmin() {
			
	        int opcion = 0;
	        do {
	        	System.out.println("-+-MENU ADMINISTRADOR GENERAL-+-");
	    		System.out.println("Seleccione una opcion:");
	    		System.out.println("1 - Ver Plantas");
	    		System.out.println("2 - Registrar Persona");
	    		System.out.println("3 - Gestion Plantas");
	    		System.out.println("4 - Gestion Ejemplares");
	    		System.out.println("5 - Gestion Mensajes");
	    		System.out.println("6 - Cerrar Sesion");
	
	            try {
	            	opcion = in.nextInt();
	            	
	            	  if (opcion < 1 || opcion > 6) {
	                      System.out.println("Opción incorrecta.");
	                      continue;
	            }
	            
	            switch (opcion) {
	            	case 1:
	            		portal.menuPlantas();
	            		break;
	            	case 2:
	            		this.registroPersona();
	            		break;
	            	case 3:
	            		gestionPlantas.menuGestionPlantas();
	            		break;
	            	case 4:
	            		gestionEjemplares.menuGestionEjemplares();
	            		break;
	            	case 5:
	            		gestionMensajes.menuGestionMensajes();
	            		break;
	            	case 6:
	            		portal.menuPrincipal();
	            		break;
	            }
	            } catch (InputMismatchException e) {
	    			System.out.println("ERROR. Ingrese un número positivo valido.");
	    			in.nextLine();
	            }
	            } while(opcion != 6);
	    	}
	
	public void registroPersona() {
		
		in.nextLine();
		
		System.out.println("-+-REGISTRO DE NUEVO USUARIO-+-");
		System.out.println("Introduce los datos del nuevo usuario.");
		System.out.println();
		System.out.println("Nombre: ");
		String nombre = in.nextLine();
		System.out.println("Email: ");
		String email = in.nextLine();
		
		Controlador.getServicios().getServiciosPersona().registrarNuevaPersona(nombre, email);	
		
		System.out.println("Usuario: ");
		String usuario = in.next();
		System.out.println("Contraseña: ");
		String contraseña = in.next();
		
		Controlador.getServicios().getServiciosCredenciales().registrarNuevoCredencial(usuario, contraseña, email);
	}
}

