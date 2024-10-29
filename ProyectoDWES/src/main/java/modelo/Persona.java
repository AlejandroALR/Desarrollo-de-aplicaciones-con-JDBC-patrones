package modelo;

import java.util.ArrayList;
import java.util.List;

public class Persona {
    private Long id;
    private String nombre;
    private String email;
    private Credenciales credenciales;
    private List<Ejemplar> ejemplaresSeguidos;

    public Persona(Long id, String nombre, String email) {
        this.id = id;
        this.nombre = nombre;
        this.email = email;
        this.ejemplaresSeguidos = new ArrayList<>();
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

    public Credenciales getCredenciales() {
        return credenciales;
    }

    public void setCredenciales(Credenciales credenciales) {
        this.credenciales = credenciales;
    }

    public List<Ejemplar> getEjemplaresSeguidos() {
        return ejemplaresSeguidos;
    }

    public void addEjemplarSeguido(Ejemplar ejemplar) {
        this.ejemplaresSeguidos.add(ejemplar);
    }
}

