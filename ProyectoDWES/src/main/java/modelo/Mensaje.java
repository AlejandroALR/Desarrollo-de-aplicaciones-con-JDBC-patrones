package modelo;

import java.time.LocalDateTime;

public class Mensaje {
    private Long id;
    private LocalDateTime fechaHora;
    private String mensaje;
    private Long fk_idPersona;
    private Long fk_idEjemplar;
    
    
    public Mensaje(String mensaje, LocalDateTime fechahora, Long fk_idPersona, Long fk_idEjemplar) {
        this.mensaje = mensaje;
        this.fk_idPersona = fk_idPersona;
        this.fk_idEjemplar = fk_idEjemplar;
        this.fechaHora = fechaHora;
    }

    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDateTime getFechaHora() {
        return fechaHora;
    }

    public void setFechaHora(LocalDateTime fechaHora) {
        this.fechaHora = fechaHora;
    }

    public String getMensaje() {
        return mensaje;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }

    public Long getfk_idPersona() {
        return fk_idPersona;
    }

    public void setfk_idPersona(Long fk_idPersona) {
        this.fk_idPersona = fk_idPersona;
    }

    public Long getfk_idEjemplar() {
        return fk_idEjemplar;
    }

    public void setfk_idEjemplar(Long fk_idEjemplar) {
        this.fk_idEjemplar = fk_idEjemplar;
    }
    
	@Override
	public String toString() {
		String ret ="";
			ret ="MENSAJE";
			ret += "\tId: " + this.id;
			ret += "\tFecha y Hora: " + this.fechaHora;
			ret += "\tMensaje: " + this.mensaje;
			ret += "\tfk_idPersona: " + this.fk_idPersona;
			ret += "\tfk_idEjemplar: " + this.fk_idEjemplar;
		return ret;
	}
}

