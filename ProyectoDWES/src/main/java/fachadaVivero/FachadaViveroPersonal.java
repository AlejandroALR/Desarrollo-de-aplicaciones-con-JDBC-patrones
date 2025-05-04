package fachadaVivero;

import java.util.InputMismatchException;
import java.util.Scanner;

import control.ServiciosCredenciales;
import control.ServiciosEjemplar;
import control.ServiciosMensaje;
import control.ServiciosPersona;
import control.ServiciosPlanta;
import control.ServiciosViveroCon;

public class FachadaViveroPersonal {

	private static FachadaViveroPersonal gestionPersonal;

	Scanner in = new Scanner(System.in);
		
	private static FachadaViveroPrincipal portal = FachadaViveroPrincipal.getPortal();
	private static FachadaViveroEjemplares gestionEjemplares = FachadaViveroEjemplares.getPortal();
	private static FachadaViveroMensajes gestionMensajes = FachadaViveroMensajes.getPortal();


	ServiciosViveroCon conServicios = ServiciosViveroCon.getServicios();
	
	ServiciosCredenciales servCreden = conServicios.getServiciosCredencial();
	ServiciosEjemplar servEjem = conServicios.getServiciosEjemplar();
	ServiciosMensaje servMens = conServicios.getServiciosMensaje();
	ServiciosPersona servPers = conServicios.getServiciosPersona();
	ServiciosPlanta servPlan = conServicios.getServiciosPlanta();
	
	public static FachadaViveroPersonal getPortal() {
		if(gestionPersonal == null)
			gestionPersonal = new FachadaViveroPersonal();
		return gestionPersonal;
	}
	
	public void menuPersonal() {
		System.out.println();
		 int opcion = 0;
	        do {
	        	System.out.println("-+-MENU PERSONAL-+-");
	    		System.out.println("1 - Mostrar Plantas");
	    		System.out.println("2 - Gestion de los Ejemplares");
		        System.out.println("3 - Gestion de los Mensajes");
	    		System.out.println("0 - Volver");
	            
	    		try {
	    		opcion = in.nextInt();
	            if (opcion < 1 || opcion > 4) {
	                System.out.println("Opción incorrecta.");
	                continue;
	            }
	            switch (opcion) {
	            	case 1:
	            		portal.menuPlantas();
	            		break;
	            	case 2:
	            		gestionEjemplares.menuGestionEjemplares();
	            		break;
	            	case 3:
	            		gestionMensajes.menuGestionMensajes();
	            		break;
	            	case 4:
	            		portal.menuPrincipal();
	            		break;
	            	case 0: System.out.println("Hasta la proxima!");
	        			break;
	            }
	    		  } catch (InputMismatchException e) {
	    				System.out.println("ERROR - Ingresa un numero valido");
	    				in.nextLine();
	    	        }
	        } while(opcion != 0);
		}

	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
