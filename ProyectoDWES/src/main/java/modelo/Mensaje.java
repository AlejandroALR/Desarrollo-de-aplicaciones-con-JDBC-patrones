package modelo;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class Mensaje {
    private Long id;
    private LocalDate fechaHora;
    private String mensaje;
    private int persona;
    private int ejemplar;
    
	public Mensaje() {}

    public Mensaje(Long id, String mensaje, int persona, int ejemplar) {
        this.id = id;
        this.mensaje = mensaje;
        this.persona = persona;
        this.ejemplar = ejemplar;
        this.fechaHora = fechaHora;
    }

    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDate getFechaHora() {
        return fechaHora;
    }

    public void setFechaHora(LocalDate fechaHora) {
        this.fechaHora = fechaHora;
    }

    public String getMensaje() {
        return mensaje;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }

    public int getPersona() {
        return persona;
    }

    public void setPersona(int persona) {
        this.persona = persona;
    }

    public int getEjemplar() {
        return ejemplar;
    }

    public void setEjemplar(int ejemplar) {
        this.ejemplar = ejemplar;
    }
}

