package modelo;


public class Persona {
    private Long id;
    private String nombre;
    private String email;
    private Long fk_idCredenciales;

    public Persona(Long id, String nombre, String email, Long fk_idCredenciales) {
        this.id = id;
        this.nombre = nombre;
        this.email = email;
        this.fk_idCredenciales = fk_idCredenciales;
    }
    
	public Persona(String nombre, String email) {
		this.nombre = nombre;
		this.email = email;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
    
	public Long getfk_idCredenciales() {
		return fk_idCredenciales;
	}

	public void setfk_idCredenciales(Long fk_idCredenciales) {
		this.fk_idCredenciales = fk_idCredenciales;
	}

	@Override
	public String toString() {
		String ret ="";
			ret ="PERSONA";
			ret += "\tId: " + this.id;
			ret += "\tNombre: " + this.nombre;
			ret += "\tEmail: " + this.email;
			ret += "\tfk_idCredenciales: " + this.fk_idCredenciales;
		return ret;
	}

}
