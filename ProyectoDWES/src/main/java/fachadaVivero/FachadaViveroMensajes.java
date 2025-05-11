package fachadaVivero;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
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
import modelo.Mensaje;
import modelo.Planta;

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
		if (gestionMensajes == null)
			gestionMensajes = new FachadaViveroMensajes();
		return gestionMensajes;
	}

	public void menuGestionMensajes() {
		int opcion = 0;

		do {
			System.out.println("-+-MENU DE GESTION DE MENSAJES-+-");
			System.out.println("1 - Registrar Nuevo Mensaje");
			System.out.println("2 - Filtrar un Nuevo Mensaje");
			System.out.println("0 - VOLVER");

			try {
				opcion = in.nextInt();
				if (opcion < 0 || opcion > 2) {
					System.out.println("Opcion no valida. Ingresa un numero valido");
					continue;
				}

				switch (opcion) {
				case 1:
					this.registrarMensaje();
					break;
				case 2:
					this.filtrarMensajes();
					break;
				}

			} catch (InputMismatchException e) {
				System.out.println("ERROR - Ingresa un numero Valido");
				in.nextLine();
			}
		} while (opcion != 0);
	}

	public void registrarMensaje() {
	    List<Ejemplar> lista = servEjem.findAll();

	    System.out.println("Ejemplares disponibles:");
	    for (Ejemplar e : lista) {
	        System.out.println("ID: " + e.getId() + " - Nombre: " + e.getNombre() + " (Tipo: " + e.getfk_planta() + ")");
	    }

	    Long idEjemplar = null;
	    boolean idValid;

	    do {
	        System.out.println("Introduce el ID del ejemplar:");
	        in = new Scanner(System.in);
	        try {
	            idEjemplar = Long.valueOf(in.nextLine().trim());
	            idValid = servEjem.existeEjemplar(idEjemplar);
	            if (!idValid) {
	                System.out.println("El ejemplar no existe. Intenta con otro ID.");
	            }
	        } catch (NumberFormatException e) {
	            System.out.println("ERROR - Ingresa un número válido.");
	            idValid = false;
	        }
	    } while (!idValid);

	    String mensaje;
	    do {
	        System.out.println("Mensaje: ");
	        mensaje = in.nextLine();
	        if (mensaje.trim().isEmpty()) {
	            System.out.println("El mensaje no puede estar vacío. Intenta de nuevo.");
	        }
	    } while (mensaje.trim().isEmpty());

	    Long idPersona = portal.getCredenciales().getfk_idPersona();
	    if (idPersona == null) {
	        System.out.println("Error: no hay usuario autenticado.");
	        return;
	    }

	    Controlador.getServicios().getServiciosMensaje().registrarMensaje(idEjemplar, idPersona, mensaje);
	    System.out.println("Mensaje registrado correctamente.");
	}
	
	public void filtrarMensajes() {
	    Scanner in = new Scanner(System.in);
	    boolean salir = false;

	    while (!salir) {
	        System.out.println("-+- FILTRAR MENSAJES -+-");
	        System.out.println("1 - Por persona");
	        System.out.println("2 - Por rango de fechas");
	        System.out.println("3 - Por tipo de planta");
	        System.out.println("0 - VOLVER");
	        System.out.print("Selecciona una opción: ");

	        String opcion = in.nextLine();

	        switch (opcion) {
	            case "1":
	                System.out.print("Introduce el nombre de la persona: ");
	                String nombrePersona = in.nextLine().trim();
	                List<Mensaje> mensajesPorPersona = servMens.findByNombrePersona(nombrePersona);
	                mostrarMensajes(mensajesPorPersona);
	                break;

	            case "2":
	                System.out.print("Fecha inicio (YYYY-MM-DD): ");
	                String fechaInicioStr = in.nextLine().trim();
	                System.out.print("Fecha fin (YYYY-MM-DD): ");
	                String fechaFinStr = in.nextLine().trim();
	                try {
	                    LocalDate fechaInicio = LocalDate.parse(fechaInicioStr);
	                    LocalDate fechaFin = LocalDate.parse(fechaFinStr);
	                    List<Mensaje> mensajesEntreFechas = servMens.findByEntreFechas(
	                        fechaInicio.atStartOfDay(), fechaFin.atTime(23, 59, 59));
	                    mostrarMensajes(mensajesEntreFechas);
	                } catch (DateTimeParseException e) {
	                    System.out.println("Formato de fecha incorrecto.");
	                }
	                break;

	            case "3":
	                System.out.print("Introduce el código de la planta: ");
	                String codigoPlanta = in.nextLine().trim();
	                List<Mensaje> mensajesPorPlanta = servMens.findByCodigoPlanta(codigoPlanta);
	                mostrarMensajes(mensajesPorPlanta);
	                break;

	            case "0":
	                salir = true;
	                break;

	            default:
	                System.out.println("Opción no válida.");
	        }
	    }
	}
	
	private void mostrarMensajes(List<Mensaje> mensajes) {
	    if (mensajes == null || mensajes.isEmpty()) {
	        System.out.println("No se encontraron mensajes.");
	        return;
	    }

	    for (Mensaje m : mensajes) {
	        System.out.println("ID: " + m.getId() + " | Fecha: " + m.getfechaHora() + " | Mensaje: " + m.getMensaje());
	    }
	}


}

//	public void filtrarMensajes() {
//		Scanner in = new Scanner(System.in);
//		String palabraClave;
//		boolean continuar = true;
//
//		do {
//			System.out.println("Introduce una palabra clave para buscar en los mensajes: ");
//			palabraClave = in.nextLine().trim().toLowerCase();
//
//			List<Mensaje> mensajes = servMens.findAll();
//			
//	        if (mensajes == null) {
//	            System.out.println("Error al recuperar los mensajes. Intenta más tarde.");
//	            return;
//	        }
//	        
//			boolean encontrado = false;
//
//			for (Mensaje m : mensajes) {
//				if (m.getMensaje().toLowerCase().contains(palabraClave)) {
//					System.out.println("Id: " + m.getId() + " - Mensaje: " + m.getMensaje());
//					encontrado = true;
//				}
//			}
//
//			if (!encontrado) {
//				System.out.println("No se encontraron mensajes que contengan la palabra introducida.");
//			}
//
//			System.out.println("¿Deseas buscar otra palabra clave? (s/n)");
//			String respuesta = in.nextLine().trim().toLowerCase();
//			if (!respuesta.equals("s")) {
//				continuar = false;
//			}
//		} while (continuar);
//	}
