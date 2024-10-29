package modelo;

import java.util.ArrayList;
import java.util.List;

public class Planta {
	
	private String codigo;
	private String nombrecomun;
	private String nombrecientifico;
	private List<Ejemplar> ejemplares;
	
	public Planta() {}
	
	public Planta(String codigo, String nombrecomun, String nombrecientifico) {
		super();
		this.codigo = codigo;
		this.nombrecomun = nombrecomun;
		this.nombrecientifico = nombrecientifico;
		this.ejemplares = new ArrayList<>();
	}

	public String getCodigo() {
		return codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

	public String getNombrecomun() {
		return nombrecomun;
	}

	public void setNombrecomun(String nombrecomun) {
		this.nombrecomun = nombrecomun;
	}

	public String getNombrecientifico() {
		return nombrecientifico;
	}

	public void setNombrecientifico(String nombrecientifico) {
		this.nombrecientifico = nombrecientifico;
	}
	
    public List<Ejemplar> getEjemplares() {
        return ejemplares;
    }

    public void addEjemplar(Ejemplar ejemplar) {
        this.ejemplares.add(ejemplar);
    }

	@Override
	public String toString() {
		return "Planta [codigo=" + codigo + ", nombrecomun=" + nombrecomun + ", nombrecientifico=" + nombrecientifico
				+ "]";
	}

	public int getId() {
		// TODO Auto-generated method stub
		return 0;
	}
	
	
	
	
	
	

}