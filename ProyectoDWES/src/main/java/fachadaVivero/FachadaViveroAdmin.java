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
import modelo.Credenciales;
import modelo.Persona;

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
		if (admin == null)
			admin = new FachadaViveroAdmin();
		return admin;
	}

	public void menuAdmin() {

		int opcion = 0;
		do {
			System.out.println("-+- MENU ADMINISTRADOR -+-");
			System.out.println("Seleccione una opcion:");
			System.out.println("1 - Ver Plantas");
			System.out.println("2 - Registrar Persona");
			System.out.println("3 - Gestion Plantas");
			System.out.println("4 - Gestion Ejemplares");
			System.out.println("5 - Gestion Mensajes");
			System.out.println("0 - Cerrar Sesion");

			try {
				opcion = in.nextInt();

				if (opcion < 0 || opcion > 7) {
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
            	case 0: System.out.println("Hasta la proxima!");
        			break;
				}
			} catch (InputMismatchException e) {
				System.out.println("ERROR. Ingrese un número positivo valido.");
				in.nextLine();
			}
		} while (opcion != 0);
	}

	public void registroPersona() {
		Scanner in = new Scanner(System.in);
		String nombre, email, usuario, password;

		System.out.println("-+-REGISTRO DE NUEVO USUARIO-+-");

		boolean valido = false;
		do {
			System.out.println("Nombre:");
			nombre = in.nextLine().trim();
			if (nombre.isEmpty() || nombre.length() <3 || nombre.length()>40) {
				System.out.println("El nombre no puede estar vacio.");
			}
			else
			valido = true;
		} while (!valido);

		do {
			System.out.println("Email:");
			email = in.nextLine().trim();
			if (email.isEmpty() || !email.matches("^[\\w.-]+@[\\w.-]+\\.[a-zA-Z]{2,}$")) {
				System.out.println("Introduce un email valido.");
			}
		} while (email.isEmpty() || !email.matches("^[\\w.-]+@[\\w.-]+\\.[a-zA-Z]{2,}$"));
		
	    do {
	        System.out.println("Nombre de usuario:");
	        usuario = in.nextLine().trim();
	        if (usuario.isEmpty() || usuario.length() < 3 || usuario.length() > 20) {
	            System.out.println("El nombre de usuario debe tener entre 3 y 20 caracteres.");
	        } else if (Controlador.getServicios().getServiciosCredenciales().usuarioExiste(usuario)) {
	            System.out.println("Ese nombre de usuario ya está en uso.");
	            usuario = "";
	        }
	    } while (usuario.isEmpty());

	    do {
	        System.out.println("Password:");
	        password = in.nextLine().trim();
	        if (password.isEmpty() || password.length() < 4) {
	            System.out.println("La password debe tener al menos 4 caracteres.");
	        }
	    } while (password.isEmpty() || password.length() < 4);

		Controlador.getServicios().getServiciosPersona().registrarNuevaPersona(nombre, email);
		Persona per = Controlador.getServicios().getServiciosPersona().findByEmail(email);
		
		Credenciales c = new Credenciales();
		c.setfk_idPersona(per.getId());
		c.setPassword(password);
		c.setUsuario(usuario);
		long idCredenciales = Controlador.getServicios().getServiciosCredenciales().insertarCredenciales(c);
		Controlador.getServicios().getServiciosPersona().actualizarFkCredenciales(per.getId(), idCredenciales);


		System.out.println("Persona registrada correctamente.");
	}
}
