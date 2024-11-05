package control;

public class Controlador {
	private static Controlador servicios;
	
	private ServiciosPlanta servPlanta;
	private ServiciosEjemplar servEjemplar;
	private ServiciosPersona servPersona;
	private ServiciosMensaje servMensaje;
	
	public static Controlador getServicios() {
		if(servicios == null)
			servicios = new Controlador();
		return servicios;
	}
	
	private Controlador() {
		servPlanta = new ServiciosPlanta();
		servEjemplar = new ServiciosEjemplar();
		servPersona = new ServiciosPersona();
		servMensaje = new ServiciosMensaje();
	}
	
	public ServiciosPlanta getServiciosPlanta() {
		return servPlanta;
	}
	
	public ServiciosEjemplar getServiciosEjemplar() {
		return servEjemplar;
	}
	
	public ServiciosPersona getServiciosPersona() {
		return servPersona;
	}
	
	public ServiciosMensaje getServiciosMensaje() {
		return servMensaje;
	}

}
