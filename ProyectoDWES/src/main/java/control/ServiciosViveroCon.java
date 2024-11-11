package control;

public class ServiciosViveroCon {

	public static ServiciosViveroCon servicios;

	public static ServiciosViveroCon getServicios() {
		if (servicios == null)
				servicios = new ServiciosViveroCon();
		return servicios;
	}
	
	public ServiciosCredenciales getServiciosCredencial() {
		return new ServiciosCredenciales();
	}

	public ServiciosEjemplar getServiciosEjemplar() {
		return new ServiciosEjemplar();
	}
	
	public ServiciosMensaje getServiciosMensaje() {
		return new ServiciosMensaje();
	}
	
	public ServiciosPersona getServiciosPersona() {
		return new ServiciosPersona();
	}
	
	public ServiciosPlanta getServiciosPlanta() {
		return new ServiciosPlanta();
	}
	
}
