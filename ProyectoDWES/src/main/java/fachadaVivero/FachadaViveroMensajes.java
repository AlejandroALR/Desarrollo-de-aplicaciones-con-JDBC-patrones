package fachadaVivero;

import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

import control.Controlador;
import control.ServiciosCredenciales;
import control.ServiciosEjemplar;
import control.ServiciosMensaje;
import control.ServiciosPersona;
import control.ServiciosPlanta;
import control.ServiciosViveroCon;
import modelo.Ejemplar;

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
				List<Ejemplar> lista = servEjem.findAll();

				System.out.println("Ejemplares disponibles:");
				for (Ejemplar e : lista) {
					System.out.println("ID: " + e.getId() + " - Nombre: " + e.getNombre());
				}

				Long id = null;
				boolean idValid;

				do {
					System.out.println("Introduce el codigo de ejemplar");
					System.out.println();
					System.out.println("Codigo de Ejemplar: ");
					in = new Scanner(System.in);
					id = Long.valueOf(in.nextInt());

					idValid = false;
					for (Ejemplar e : lista) {
						if (e.getId() == id) {
							idValid = true;
							break;
						}
					}

					if (!idValid) {
						System.out.println("Id de ejemplar no valido, introduce un valor valido.");
					}
				} while (!idValid);

				String mensaje = "";

				do {
					System.out.println();
					System.out.println("Mensaje: ");
					in = new Scanner(System.in);
					mensaje = in.nextLine();

					if (mensaje.trim().isEmpty()) {
						System.out.println("El mensaje no puede estar vacio, introduce algun mensaje.");
					}
				} while (mensaje.trim().isEmpty());

				Controlador.getServicios().getServiciosMensaje().registrarMensaje(id,
						portal.getCredenciales().getfk_idPersona(), mensaje);

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
