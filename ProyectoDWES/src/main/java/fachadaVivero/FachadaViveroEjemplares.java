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
		if (gestionEjemplares == null)
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
			System.out.println("0 - Volver");

			try {
				opcion = in.nextInt();
				in.nextLine();
				if (opcion < 0 || opcion > 3) {
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
				}

			} catch (InputMismatchException e) {
				System.out.println("ERROR - Ingresa un numero valido");
				in.nextLine();
			}
		} while (opcion != 0);
	}

	public void registrarEjemplar() {
//	    if (portal.getCredenciales() == null) {
//	        System.out.println("Debes iniciar sesión para registrar un ejemplar.");
//	        return;
//	    }
		System.out.println("Seleccione la planta de la cual desea registrar un ejemplar nuevo");
		System.out.println();
		portal.menuPlantas();
		System.out.println();

		String codigo;
		boolean codigoValid;

		do {
			System.out.println("Código de planta:");
			codigo = in.nextLine().trim();
			codigoValid = Controlador.getServicios().getServiciosPlanta().validarPlanta(codigo);
			if (!codigoValid) {
				System.out.println("Codigo incorrecto, vuelva a introducirlo.");
			}
		} while (!codigoValid);

		Planta p = Controlador.getServicios().getServiciosPlanta().findByCod(codigo);
		Controlador.getServicios().getServiciosEjemplar().registrarEjemplar(p,
				portal.getCredenciales().getfk_idPersona());

		System.out.println("Ejemplar registrado correctamente.");
	}

	public void verMensajes() {
	    System.out.println("Seleccione el ejemplar del cual desea mostrar sus mensajes");
	    System.out.println();
	    Controlador.getServicios().getServiciosEjemplar().mostrarEjemplares();  // Ya muestra los nombres
	    System.out.println();

	    String nombreEjemplar;
	    Ejemplar ej = null;
	    boolean valid = false;

	    do {
	        System.out.print("Nombre del ejemplar (ej. ROSA_4): ");
	        nombreEjemplar = in.nextLine().trim();

	        ej = Controlador.getServicios().getServiciosEjemplar().findByNombre(nombreEjemplar);
	        if (ej != null) {
	            valid = true;
	        } else {
	            System.out.println("Ejemplar no encontrado. Intenta con otro nombre.");
	        }

	    } while (!valid);

	    Controlador.getServicios().getServiciosEjemplar().verMensajes(ej.getId());
	}

	
	public void filtrarEjemplares() {
		Scanner in = new Scanner(System.in);

		System.out.println("Introduce el nombre del tipo de planta que deseas buscar:");
		String nombrePlanta = in.nextLine().trim().toLowerCase();

		List<Ejemplar> ejemplares = servEjem.findAll();

		boolean encontrado = false;

		for (Ejemplar e : ejemplares) {
			Planta p = Controlador.getServicios().getServiciosPlanta().findByCod(e.getfk_planta());
			if (p.getNombreComun().toLowerCase().contains(nombrePlanta)) {
				System.out.println("Id: " + e.getId() + " - Nombre Comun: " + p.getNombreComun() + " ("
						+ p.getNombreCientifico() + ")");
				encontrado = true;
			}
		}

		if (!encontrado) {
			System.out.println("No se encontraron ejemplares con plantas que coincidan con ese nombre.");
		}
	}

}
