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
import modelo.Credenciales;
import modelo.Planta;

public class FachadaViveroPrincipal {

	Scanner in = new Scanner(System.in);
		
	private static FachadaViveroPrincipal portal;
	private static FachadaViveroPersonal personal = FachadaViveroPersonal.getPortal();
	private static FachadaViveroAdmin admin = FachadaViveroAdmin.getPortal();

	private Credenciales credenciales;


	ServiciosViveroCon conServicios = ServiciosViveroCon.getServicios();
	
	ServiciosCredenciales servCreden = conServicios.getServiciosCredencial();
	ServiciosEjemplar servEjem = conServicios.getServiciosEjemplar();
	ServiciosMensaje servMens = conServicios.getServiciosMensaje();
	ServiciosPersona servPers = conServicios.getServiciosPersona();
	ServiciosPlanta servPlan = conServicios.getServiciosPlanta();
	
	public static FachadaViveroPrincipal getPortal() {
		if(portal == null)
			portal = new FachadaViveroPrincipal();
		return portal;
	}
	
	public void menuPrincipal() {
		
		System.out.println();
		System.out.println("_____________________________________________");
		System.out.println("Bienvenido al vivero, ¿En que puedo ayudarle?");
		
        int opcion = 0;
        do {
    		System.out.println("1 - Ver Plantas");
    		System.out.println("2 - Login");
    		
    		try {
	            opcion = in.nextInt();
	            if (opcion < 1 || opcion > 2) {
	                System.out.println("Opción no valida. Use uno de los numeros indicados");
	                continue;
            }
            switch (opcion) {
            	case 1:
            		portal.menuPlantas();
            		break;
            	case 2:
            		portal.menuLogin();
            		break;
            }
    		} catch (InputMismatchException e) {
				System.out.println("ERROR - Ingresa un numero valido");
				in.nextLine();
	        }
        } while(opcion != 2);
	}
	
	public void menuLogin() {
		
		Credenciales c = this.pedirCredenciales();
		
		if(c.getUsuario().equals("admin") && c.getPassword().equals("admin")) {
			System.out.println("Bienvenido admin");
			FachadaViveroPrincipal.admin.menuAdmin();	
		}
		else {
			boolean loginCorrecto = false;
			while(!loginCorrecto) {
				if(!Controlador.getServicios().getServiciosCredenciales().validarCredenciales(c)) {
					System.out.println("Usuario o Contraseña incorrectos");
					System.out.println();
					c = this.pedirCredenciales();
				} else {
					loginCorrecto = true;
					credenciales = Controlador.getServicios().getServiciosCredenciales().findByUsuario(c.getUsuario());
					System.out.println("outprint login"+credenciales);
				}
			
			}
			System.out.println("Hola "+c.getUsuario());
			FachadaViveroPrincipal.personal.menuPersonal();		
		}
	}
	
	public Credenciales pedirCredenciales() {
		
		System.out.println("Usuario: ");
		String usuario = in.next();
		System.out.println("Contraseña: ");
		String contraseña = in.next();
		
		Credenciales c = new Credenciales(usuario, contraseña);
		
		return c;
	}
	
	public void menuPlantas() {
		System.out.println();
		System.out.println("Estas son nuestras plantas: ");
	 	List<Planta> listaPlantas = Controlador.getServicios().getServiciosPlanta().findAll();
		
		int cont = 1;
	 	for(Planta p : listaPlantas) {
	 		System.out.println(cont + ": " + p.toString());
	 		cont++;
	 	}
	 	System.out.println();
	}
	
	public Credenciales getCredenciales() {
		return credenciales;
	}
	
	public void setCredenciales(Credenciales credenciales) {
		this.credenciales = credenciales;
	}
}
