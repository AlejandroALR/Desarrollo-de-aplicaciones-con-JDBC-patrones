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

public class FachadaViveroMensajes {

	private static FachadaViveroMensajes gestionMensajes;

	Scanner in = new Scanner(System.in);
		
	private static FachadaViveroPrincipal portal = FachadaViveroPrincipal.getPortal();


	ServiciosViveroCon conServicios = ServiciosViveroCon.getServicios();
	
	ServiciosCredenciales servCreden = conServicios.getServiciosCredencial();
	ServiciosEjemplar servEjem = conServicios.getServiciosEjemplar();
	ServiciosMensaje servMens = conServicios.getServiciosMensaje();
	ServiciosPersona servPers = conServicios.getServiciosPersona();
	ServiciosPlanta servPlan = conServicios.getServiciosPlanta();
	
	public static FachadaViveroMensajes getPortal() {
		if(gestionMensajes == null)
			gestionMensajes = new FachadaViveroMensajes();
		return gestionMensajes;
	}
	
	public void menuGestionMensajes() {
		int opcion = 0;
		
		do {
        	System.out.println("-+-MENU DE GESTION DE MENSAJES-+-");
    		System.out.println("1 - Registrar Nuevo Mensaje");
    		System.out.println("2 - Filtrar un Nuevo Mensaje");
    		System.out.println("3 - Cerrar Sesion");
    		
    		try {
    			opcion = in.nextInt();
    			if (opcion < 1 || opcion > 3) {
    				System.out.println("Opcion no valida. Ingresa un numero valido");
    				continue;
    			}
    			
                switch (opcion) {
            	case 1:
            		this.registrarMensaje();
            		break;
            	case 2:
            		this.menuFiltrarMensajes();
            		break;
            	case 3:
            		break;
                }
                
    		} catch (InputMismatchException e) {
				System.out.println("ERROR - Ingresa un numero valido");
				in.nextLine();
	        }
			    } while(opcion != 3);
			}
	
	public void registrarMensaje() {
		
		System.out.println("Introduce el codigo de ejemplar");
		System.out.println();
		System.out.println("Codigo de Ejemplar: ");
		int id = in.nextInt();
		in.nextLine();
		System.out.println();
		System.out.println("Mensaje: ");
		String mensaje = in.nextLine();
		
		Controlador.getServicios().getServiciosMensaje().registrarMensaje(id, portal.getCredenciales().getfk_idPersona(), mensaje);
		
	}
	
	public void menuFiltrarMensajes() {
		int opcion = 0;
		
		do {
        	System.out.println("-+-MENU DE FILTRAR MENSAJES-+-");
    		System.out.println("1 - Filtrar un Nuevo Mensaje");
    		System.out.println("2 - Cerrar Sesion");
    		
    		try {
    			opcion = in.nextInt();
    			if (opcion < 1 || opcion > 2) {
    				System.out.println("Opcion no valida. Ingresa un numero valido");
    				continue;
    			}
    			
                switch (opcion) {
            	case 1:
            		this.filtrarMensajes();
            		break;
            	case 2:
            		break;
                }
                
    		} catch (InputMismatchException e) {
				System.out.println("ERROR - Ingresa un numero valido");
				in.nextLine();
	        }
			    } while(opcion != 2);
			}
	
	public void filtrarMensajes(){}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
