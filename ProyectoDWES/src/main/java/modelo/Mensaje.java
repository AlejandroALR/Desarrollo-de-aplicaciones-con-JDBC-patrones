package modelo;

import java.time.LocalDateTime;

public class Mensaje {
    private Long id;
    private LocalDateTime fechaHora;
    private String mensaje;
    private Persona persona;
    private Ejemplar ejemplar;

    public Mensaje(Long id, LocalDateTime fechaHora, String mensaje, Persona persona, Ejemplar ejemplar) {
        this.id = id;
        this.fechaHora = fechaHora;
        this.mensaje = mensaje;
        this.persona = persona;
        this.ejemplar = ejemplar;
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

    public Persona getPersona() {
        return persona;
    }

    public void setPersona(Persona persona) {
        this.persona = persona;
    }

    public Ejemplar getEjemplar() {
        return ejemplar;
    }

    public void setEjemplar(Ejemplar ejemplar) {
        this.ejemplar = ejemplar;
    }
}

