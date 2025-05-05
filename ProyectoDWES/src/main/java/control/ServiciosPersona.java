package control;

import java.util.List;

import conexion.ConexionBBDD;
import dao.PersonaDao;
import modelo.Credenciales;
import modelo.Persona;

public class ServiciosPersona {

	private ConexionBBDD con;
	private PersonaDao PersonaDao;
	
	public ServiciosPersona() {
		con=ConexionBBDD.getInstance();
		PersonaDao=(PersonaDao) con.getPersonaDao();
	}
	
	public boolean validarPersona(Persona pe) {
		if(this.findByEmail(pe.getEmail())== null) {
			return true;
		}else {
			return false;
		}
	}
	
	public int insertarPersona(Persona pe) {
		return PersonaDao.insertarPersona(pe);
	}

	public Persona findByEmail(String email) {
		return PersonaDao.findByEmail(email);
	}
	
	public Persona findById(Long id) {
		return PersonaDao.findById(id);
	}

	public List<Persona> findAll() {
		return PersonaDao.findAll();
	}
	
	public void registrarNuevaPersona(String nombre, String email) {
		
		List<Persona> listaPersonas = this.findAll();
		Persona pe = new Persona(nombre,email);
		
		this.insertarPersona(pe);
	}
}
