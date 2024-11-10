package modelo;

public class Planta {
	
	private String codigo;
	private String nombreComun;
	private String nombreCientifico;
	
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

	public String getnombreComun() {
		return nombreComun;
	}

	public void setnombreComun(String nombreComun) {
		this.nombreComun = nombreComun;
	}

	public String getnombreCientifico() {
		return nombreCientifico;
	}

	public void setnombreCientifico(String nombreCientifico) {
		this.nombreCientifico = nombreCientifico;
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