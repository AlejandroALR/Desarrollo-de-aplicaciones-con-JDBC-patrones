package modelo;

public class Ejemplar {
    private Long id;
    private String nombre;
    private String fk_codPlanta;

    
    public Ejemplar(Long id, String nombre, String fk_codPlanta) {
        this.id = id;
        this.nombre = nombre;
        this.fk_codPlanta = fk_codPlanta;
    }
    
	public Ejemplar(String nombre, String fk_codPlanta) {
		this.nombre = nombre;
		this.fk_codPlanta = fk_codPlanta;
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

    public String getfk_codPlanta() {
        return fk_codPlanta;
    }

    public void setfk_codPlanta(String fk_codPlanta) {
        this.fk_codPlanta = fk_codPlanta;
    }
    
	@Override
	public String toString() {
		String ret ="";
			ret ="EJEMPLAR";
			ret += "\tId: " + this.id;
			ret += "\tNombre: " + this.nombre;
			ret += "\tfk_codPlanta " + this.fk_codPlanta;
		return ret;
	}
    
}

