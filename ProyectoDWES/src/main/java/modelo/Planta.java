package modelo;

import java.util.ArrayList;
import java.util.List;

public class Planta {
	
	private String codigo;
	private String nombreComun;
	private String nombreCientifico;
	private List<Ejemplar> ejemplares = new ArrayList<Ejemplar>() ;
	
	public Planta(String codigo, String nombreComun, String nombreCientifico) {

		this.codigo = codigo;
		this.nombreComun = nombreComun;
		this.nombreCientifico = nombreCientifico;
	}

	public String getCodigo() {
		return codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

	public String getNombreComun() {
		return nombreComun;
	}

	public void setNombreComun(String nombreComun) {
		this.nombreComun = nombreComun;
	}

	public String getNombreCientifico() {
		return nombreCientifico;
	}

	public void setNombreCientifico(String nombreCientifico) {
		this.nombreCientifico = nombreCientifico;
	}

	
	public List<Ejemplar> getEjemplares() {
		return ejemplares;
	}

	public void setEjemplares(List<Ejemplar> ejemplares) {
		this.ejemplares = ejemplares;
	}

	@Override
	public String toString() {
		String ret ="";
			ret ="PLANTA";
			ret += "\tCódigo: " + this.codigo;
			ret += "\tNombre común: " + this.nombreComun;
			ret += "\tNombre científico: " + this.nombreCientifico;
		return ret;
	}

}