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
	
//	public void filtrarMensajes() {
//	    Scanner in = new Scanner(System.in);
//	    boolean continuar = true;
//
//	    do {
//	        System.out.println("\n--- Filtro de Mensajes ---");
//	        System.out.println("1 - Filtrar por persona");
//	        System.out.println("2 - Filtrar por rango de fechas");
//	        System.out.println("3 - Filtrar por tipo de planta");
//	        System.out.println("0 - Salir");
//	        System.out.print("Elige una opción: ");
//	        String opcion = in.nextLine().trim();
//
//	        List<Mensaje> mensajesFiltrados = new ArrayList<>();
//	        List<Mensaje> mensajes = servMens.findAll();
//
//	        if (mensajes == null) {
//	            System.out.println("Error al recuperar los mensajes.");
//	            return;
//	        }
//
//	        switch (opcion) {
//	            case "1": // Filtrar por persona
//	                System.out.print("Introduce el nombre de usuario: ");
//	                String nombreUsuario = in.nextLine().trim().toLowerCase();
//	                for (Mensaje m : mensajes) {
//	                    String nombre = Controlador.getServicios()
//	                        .getServiciosCredenciales()
//	                        .nombreUsuario(m.getfk_idPersona());
//	                    if (nombre != null && nombre.toLowerCase().contains(nombreUsuario)) {
//	                        mensajesFiltrados.add(m);
//	                    }
//	                }
//	                break;
//
//	            case "2": // Filtrar por fechas
//	                System.out.print("Introduce la fecha de inicio (yyyy-MM-dd): ");
//	                String inicioStr = in.nextLine();
//	                System.out.print("Introduce la fecha de fin (yyyy-MM-dd): ");
//	                String finStr = in.nextLine();
//	                try {
//	                    LocalDateTime inicio = LocalDate.parse(inicioStr).atStartOfDay();
//	                    LocalDateTime fin = LocalDate.parse(finStr).atTime(23, 59, 59);
//	                    for (Mensaje m : mensajes) {
//	                        if (m.getfechaHora().isAfter(inicio) && m.getfechaHora().isBefore(fin)) {
//	                            mensajesFiltrados.add(m);
//	                        }
//	                    }
//	                } catch (DateTimeParseException e) {
//	                    System.out.println("Formato de fecha incorrecto.");
//	                }
//	                break;
//
//	            case "3": // Filtrar por tipo de planta
//	                System.out.print("Introduce el código de la planta (ej: ROSA): ");
//	                String codPlanta = in.nextLine().trim().toUpperCase();
//	                for (Mensaje m : mensajes) {
//	                    Ejemplar ej = Controlador.getServicios().getServiciosEjemplar().findById(m.getfk_idEjemplar());
//	                    if (ej != null && ej.getfk_planta().equalsIgnoreCase(codPlanta)) {
//	                        mensajesFiltrados.add(m);
//	                    }
//	                }
//	                break;
//
//	            case "0":
//	                continuar = false;
//	                continue;
//
//	            default:
//	                System.out.println("Opción no válida.");
//	                continue;
//	        }
//
//	        if (mensajesFiltrados.isEmpty()) {
//	            System.out.println("No se encontraron mensajes con ese criterio.");
//	        } else {
//	            for (Mensaje m : mensajesFiltrados) {
//	                System.out.println("Id: " + m.getId() + " - Mensaje: " + m.getMensaje() + " - Fecha: " + m.getfechaHora());
//	            }
//	        }
//
//	    } while (continuar);
//	}


	public void filtrarMensajes() {
		Scanner in = new Scanner(System.in);
		String palabraClave;
		boolean continuar = true;

		do {
			System.out.println("Introduce una palabra clave para buscar en los mensajes: ");
			palabraClave = in.nextLine().trim().toLowerCase();

			List<Mensaje> mensajes = servMens.findAll();
			
	        if (mensajes == null) {
	            System.out.println("Error al recuperar los mensajes. Intenta más tarde.");
	            return;
	        }
	        
			boolean encontrado = false;

			for (Mensaje m : mensajes) {
				if (m.getMensaje().toLowerCase().contains(palabraClave)) {
					System.out.println("Id: " + m.getId() + " - Mensaje: " + m.getMensaje());
					encontrado = true;
				}
			}

			if (!encontrado) {
				System.out.println("No se encontraron mensajes que contengan la palabra introducida.");
			}

			System.out.println("¿Deseas buscar otra palabra clave? (s/n)");
			String respuesta = in.nextLine().trim().toLowerCase();
			if (!respuesta.equals("s")) {
				continuar = false;
			}
		} while (continuar);
	}
	
}
