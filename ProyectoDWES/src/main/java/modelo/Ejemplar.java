package modelo;

public class Ejemplar {
    private Long id;
    private String nombre;
    private String fk_planta;

    
    public Ejemplar(Long id, String nombre, String fk_planta) {
        this.id = id;
        this.nombre = nombre;
        this.fk_planta = fk_planta;
    }
    
	public Ejemplar(String nombre, String fk_planta) {
		this.nombre = nombre;
		this.fk_planta = fk_planta;
	}

	public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getfk_planta() {
        return fk_planta;
    }

    public void setfk_planta(String fk_planta) {
        this.fk_planta = fk_planta;
    }
    
	@Override
	public String toString() {
		String ret ="";
			ret ="EJEMPLAR";
			ret += "\tId: " + this.id;
			ret += "\tNombre: " + this.nombre;
			ret += "\tfk_planta: " + this.fk_planta;
		return ret;
	}
    
}

