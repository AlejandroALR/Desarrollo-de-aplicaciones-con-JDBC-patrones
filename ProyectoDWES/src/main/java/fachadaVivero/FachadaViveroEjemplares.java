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

public class FachadaViveroEjemplares {
	
private static FachadaViveroEjemplares gestionEjemplares;

	Scanner in = new Scanner(System.in);
		
	private static FachadaViveroPrincipal portal = FachadaViveroPrincipal.getPortal();


	ServiciosViveroCon conServicios = ServiciosViveroCon.getServicios();
	
	ServiciosCredenciales servCreden = conServicios.getServiciosCredencial();
	ServiciosEjemplar servEjem = conServicios.getServiciosEjemplar();
	ServiciosMensaje servMens = conServicios.getServiciosMensaje();
	ServiciosPersona servPers = conServicios.getServiciosPersona();
	ServiciosPlanta servPlan = conServicios.getServiciosPlanta();
	
	public static FachadaViveroEjemplares getPortal() {
		if(gestionEjemplares == null)
			gestionEjemplares = new FachadaViveroEjemplares();
		return gestionEjemplares;
	}
	
	public void menuGestionEjemplares() {
		int opcion = 0;
		
		do {
        	System.out.println("-+-MENU GESTION EJEMPLARES-+-");
    		System.out.println("1 - Registrar Nuevo Ejemplar");
    		System.out.println("2 - Filtrar Ejemplares por Tipo");
    		System.out.println("3 - Ver Mensajes de Seguimiento");
    		System.out.println("4 - Cerrar Sesion");
    		
    		try {
    			opcion = in.nextInt();
    			if (opcion < 1 || opcion > 4) {
    				System.out.println("Opcion no valida. Ingresa un numero valido");
    				continue;
    			}
    			
                switch (opcion) {
            	case 1:
            		this.registrarEjemplar();
            		break;
            	case 2:
            		this.filtrarEjemplares();
            		break;
            	case 3:
            		this.verMensajes();
            		break;
            	case 4:
            		break;
                }
                
    		} catch (InputMismatchException e) {
				System.out.println("ERROR - Ingresa un numero valido");
				in.nextLine();
	        }
			    } while(opcion != 4);
			}
	
	public void registrarEjemplar() {
		
		System.out.println("Seleccione la planta de la cual desea registrar un ejemplar nuevo");
		System.out.println();
		portal.menuPlantas();
		System.out.println();
		System.out.println("Codigo de planta: ");
		in.nextLine();
		String codigo = in.nextLine();
		
		while(!Controlador.getServicios().getServiciosPlanta().validarPlanta(codigo)) {
			System.out.println("Codigo incorrecto, vuelva a introducirlo");
			in.nextLine();
			codigo = in.nextLine();
		}
		Planta p = Controlador.getServicios().getServiciosPlanta().findByCod(codigo);
		Controlador.getServicios().getServiciosEjemplar().registrarEjemplar(p, portal.getCredenciales().getfk_idPersona());
				
	}
	
	public void verMensajes() {
		
		System.out.println("Seleccione la planta de la cual desea mostrar sus mensajes");
		System.out.println();
		Controlador.getServicios().getServiciosEjemplar().mostrarEjemplares();
		System.out.println();
		System.out.println("Codigo de ejemplar: ");		
		Long idEjemplar = Long.valueOf(in.nextLong());
		Controlador.getServicios().getServiciosEjemplar().verMensajes(idEjemplar);
				
	}
	
	public void filtrarEjemplares() {

		
	}
	
		
	}


