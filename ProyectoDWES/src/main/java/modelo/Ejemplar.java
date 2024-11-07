package modelo;

import java.util.ArrayList;
import java.util.List;

public class Ejemplar {
    private Long id;
    private String nombre;
    private Planta planta;

    public Ejemplar(Long id, Planta planta) {
        this.id = id;
        this.planta = planta;
        this.nombre = planta.getCodigo() + "_" + id;
    }

    public static Long getId() {
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

    public Planta getPlanta() {
        return planta;
    }

    public void setPlanta(Planta planta) {
        this.planta = planta;
    }

    
}

