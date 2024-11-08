package modelo;

public class Ejemplar {
    private Long id;
    private String nombre;
    private String codPlanta;

    public Ejemplar() {}
    
    public Ejemplar(Long id, String nombre, String codPlanta) {
        this.id = id;
        this.nombre = nombre;
        this.codPlanta = codPlanta;
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

    public String getcodPlanta() {
        return codPlanta;
    }

    public void setcodPlanta(String codPlanta) {
        this.codPlanta = codPlanta;
    }
    
}

