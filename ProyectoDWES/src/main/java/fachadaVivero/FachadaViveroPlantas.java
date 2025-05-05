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
		if (gestionPlantas == null)
			gestionPlantas = new FachadaViveroPlantas();
		return gestionPlantas;
	}

	public void menuGestionPlantas() {
		int opcion = 0;

		do {
			System.out.println("-+-MENU GESTION PLANTAS-+-");
			System.out.println("1 - Registrar Nueva Planta");
			System.out.println("2 - Modificar Planta");
			System.out.println("0 - VOLVER");

			try {
				opcion = in.nextInt();
				if (opcion < 0 || opcion > 2) {
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
				}

			} catch (InputMismatchException e) {
				System.out.println("ERROR - Ingresa un numero valido");
				in.nextLine();
			}
		} while (opcion != 0);
	}

	public void registrarPlantas() {
		List<Planta> lista = servPlan.findAll();

		System.out.println("Plantas disponibles: ");
		for (Planta p : lista) {
			System.out.println("Id: " + p.getCodigo() + " - Nombre Comun: " + p.getNombreComun()
					+ " - Nombre Cientifico: " + p.getNombreCientifico());
		}

		String codigo;
		boolean codigoValid = false;
		System.out.println("Registra los datos de la planta nueva");
		do {

			System.out.println("Por favor, introduce CODIGO de la nueva planta.");
			in= new Scanner (System.in);
			codigo = in.nextLine();
			codigoValid = true;
			for (Planta p : lista) {
				if (p.getCodigo().equals(codigo)) {
					System.out.println("Este codigo ya existe en la lista de plantas.");
					codigoValid = false;
					break;
				}
			}
		} while (!codigoValid);

		String nombreComun;

		do {
			System.out.println("Introduce el nombre comun:");
			nombreComun = in.nextLine().trim();
			if (nombreComun.isEmpty()) {
				System.out.println("El nombre comun no puede estar vacio.");
			}
		} while (nombreComun.isEmpty());

		String nombreCientifico;

		do {
			System.out.println("Introduce el nombre cientifico:");
			nombreCientifico = in.nextLine().trim();
			if (nombreCientifico.isEmpty()) {
				System.out.println("El nombre cientifico no puede estar vacio.");
			}
		} while (nombreCientifico.isEmpty());

		Planta p = new Planta(codigo, nombreComun, nombreCientifico);
		Controlador.getServicios().getServiciosPlanta().insertar(p);

		System.out.println("Planta registrada correctamente.");
	}

	public void modificarPlantas() {

		List<Planta> lista = servPlan.findAll();

		System.out.println("Plantas disponibles: ");
		for (Planta p : lista) {
			System.out.println("Id: " + p.getCodigo() + " - Nombre Comun: " + p.getNombreComun()
					+ " - Nombre Cientifico: " + p.getNombreCientifico());
		}

		String codigo;
		boolean existe = false;
		Planta plantaExiste = null;

		do {
			in= new Scanner(System.in);
			System.out.println("Introduce el codigo de planta que quiere modificar");
			codigo = in.nextLine().trim();

			for (Planta p : lista) {
				if (p.getCodigo().equals(codigo)) {
					existe = true;
					plantaExiste = p;
					break;
				}
			}

			if (!existe) {
				System.out.println("No se encontro ninguna planta con ese codigo. Intentalo de nuevo.");
			}
		} while (!existe);

		String nombreComun;
		do {
			System.out.println("Introduce el nuevo nombre comun:");
			nombreComun = in.nextLine().trim();
			if (nombreComun.isEmpty()) {
				System.out.println("El nombre comun no puede estar vacio.");
			}
		} while (nombreComun.isEmpty());

		String nombreCientifico;
		do {
			System.out.println("Introduce el nuevo nombre cientifico:");
			nombreCientifico = in.nextLine().trim();
			if (nombreCientifico.isEmpty()) {
				System.out.println("El nombre cientifico no puede estar vacio.");
			}
		} while (nombreCientifico.isEmpty());

		System.out.println("\nVas a modificar la planta con codigo: " + codigo);
		System.out.println("Nombre comun: " + plantaExiste.getNombreComun() + " -> " + nombreComun);
		System.out.println("Nombre cientifico: " + plantaExiste.getNombreCientifico() + " -> " + nombreCientifico);
		System.out.print("¿Confirmas los cambios? (s/n): ");
		String confirmacion = in.nextLine().trim();

		if (confirmacion.equalsIgnoreCase("s")) {
			Planta pModificada = new Planta(codigo, nombreComun, nombreCientifico);
			Controlador.getServicios().getServiciosPlanta().modificar(pModificada);
			System.out.println("Planta modificada correctamente.");
		} else {
			System.out.println("Modificacion cancelada.");
		}
	}
}
