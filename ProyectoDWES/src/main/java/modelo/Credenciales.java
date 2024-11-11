package modelo;

public class Credenciales {
	private int id;
    private String usuario;
    private String password;
    private int fk_idPersona;

    public Credenciales( String usuario, String password, int fk_idPersona) {
        this.usuario = usuario;
        this.password = password;
        this.fk_idPersona = fk_idPersona;
    }

	public Credenciales(String usuario, String password) {
		this.usuario = usuario;
		this.password = password;
	}
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	
    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    
	public int getfk_idPersona() {
		return fk_idPersona;
	}

	public void setfk_idPersona(int fk_idPersona) {
		this.fk_idPersona = fk_idPersona;
	}
	
	@Override
	public String toString() {
		String ret ="";
			ret ="CREDENCIALES";
			ret += "\tID: " + this.id;
			ret += "\tUsuario: " + this.usuario;
			ret += "\tPassword: " + this.password;
			ret += "\tfk_idPersona: " + this.fk_idPersona;
		return ret;
	}
}
