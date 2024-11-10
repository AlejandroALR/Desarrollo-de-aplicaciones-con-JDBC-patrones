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
import modelo.Planta;

public class FachadaViveroPlantas {

	private static FachadaViveroPlantas gestionPlantas;

	Scanner in = new Scanner(System.in);
		
	private static FachadaViveroPrincipal portal = FachadaViveroPrincipal.getPortal();


	ServiciosViveroCon conServicios = ServiciosViveroCon.getServicios();
	
	ServiciosCredenciales servCreden = conServicios.getServiciosCredencial();
	ServiciosEjemplar servEjem = conServicios.getServiciosEjemplar();
	ServiciosMensaje servMens = conServicios.getServiciosMensaje();
	ServiciosPersona servPers = conServicios.getServiciosPersona();
	ServiciosPlanta servPlan = conServicios.getServiciosPlanta();
	
	public static FachadaViveroPlantas getPortal() {
		if(gestionPlantas == null)
			gestionPlantas = new FachadaViveroPlantas();
		return gestionPlantas;
	}
	
	public void menuGestionPlantas() {
		int opcion = 0;
		
		do {
        	System.out.println("-+-MENU GESTION PLANTAS-+-");
    		System.out.println("1 - Registrar Nueva Planta");
    		System.out.println("2 - Modificar Planta");
    		System.out.println("3 - Cerrar Sesion");
    		
    		try {
    			opcion = in.nextInt();
    			if (opcion < 1 || opcion > 3) {
    				System.out.println("Opcion no valida. Ingresa un numero valido");
    				continue;
    			}
    			
                switch (opcion) {
            	case 1:
            		this.registrarPlantas();
            		break;
            	case 2:
            		this.modificarPlantas();
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
	
	public void registrarPlantas() {
		
		System.out.println("Registra los datos de la planta nueva");
		System.out.println();
		System.out.println("Codigo de Planta: ");
		String codigo = in.next();
		
		while(Controlador.getServicios().getServiciosPlanta().validarPlanta(codigo)) {
			
			System.out.println("El codigo de planta que has introducido ya existe, introduce otro numero: ");
			in.nextLine();
			codigo = in.next();
		}
		
		System.out.println("Codigo valido");
		System.out.println();
		System.out.println("Nombre Comun: ");
		String nombreComun = in.next();
		System.out.println("Nombre Cientifico: ");
		String nombreCientifico = in.next();
		
		Planta p = new Planta(codigo, nombreComun, nombreCientifico);
		
		Controlador.getServicios().getServiciosPlanta().insertar(p);
	}
	
	public void modificarPlantas() {
		
		System.out.println("Introduce el codigo de planta que quiere modificar");
		System.out.println();
		System.out.println("Codigo de planta: ");
		in.nextLine();
		String codigo = in.nextLine();
		
		in.nextLine();
		
		System.out.println();
		System.out.println("Introduce los nuevos valores para los campos que desea modificar");
		System.out.println();
		System.out.println("Nombre Comun: ");
		String nombreComun = in.next();
		System.out.println("Nombre Cientifico: ");
		String nombreCientifico = in.next();
		
		Planta p = new Planta(codigo, nombreComun, nombreCientifico);

		Controlador.getServicios().getServiciosPlanta().modificar(p);
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
