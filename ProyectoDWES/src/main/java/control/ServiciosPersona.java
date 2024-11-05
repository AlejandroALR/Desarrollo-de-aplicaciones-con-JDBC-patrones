package control;

import conexion.ConexionBBDD;
import dao.PersonaDao;
import modelo.Persona;

public class ServiciosPersona {

	private ConexionBBDD con;
	private PersonaDao PersonaDao;
	
	public ServiciosPersona() {
		con=ConexionBBDD.getInstance();
		PersonaDao=(PersonaDao) con.getPersonaDao();
	}
	
	public boolean validarPersona(Persona pe) {
		boolean ret = false;
		if(pe.getNombre().isEmpty())return false;
		if(pe.getNombre().length()<2 || pe.getNombre().length()>20)return false;
		return true;
	}
	
	public int insertarPersona(Persona pe) {
		return PersonaDao.insertarPersona(pe);
	}
}
