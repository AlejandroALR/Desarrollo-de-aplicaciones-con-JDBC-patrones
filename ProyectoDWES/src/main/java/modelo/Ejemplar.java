package modelo;

import java.util.ArrayList;
import java.util.List;

public class Ejemplar {
    private Long id;
    private String nombre;
    private String planta;
    private List<Persona> seguidores;
    private List<Mensaje> mensajes;

    public Ejemplar(Long id, String nombre, String planta) {
        this.id = id;
        this.nombre = nombre;
        this.planta = planta;
        this.seguidores = new ArrayList<>();
        this.mensajes = new ArrayList<>();
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

    public String getPlanta() {
        return planta;
    }

    public void setPlanta(String planta) {
        this.planta = planta;
    }

    public List<Persona> getSeguidores() {
        return seguidores;
    }

    public void addSeguidor(Persona persona) {
        this.seguidores.add(persona);
    }

    public List<Mensaje> getMensajes() {
        return mensajes;
    }

    public void addMensaje(Mensaje mensaje) {
        this.mensajes.add(mensaje);
    }
    
    
}

