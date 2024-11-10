package control;

import java.util.List;

import conexion.ConexionBBDD;
import dao.PlantaDao;
import modelo.Persona;
import modelo.Planta;

public class ServiciosPlanta {
	
	private ConexionBBDD con;
	private PlantaDao PlantaDao;
	
	public ServiciosPlanta() {
		con=ConexionBBDD.getInstance();
		PlantaDao=(PlantaDao) con.getPlantaDao();
	}
	
	public boolean validarPlanta(String codigo) {
		if(this.findByCod(codigo)== null) {
			return true;
		}else {
			return false;
		}
	}
	
	public int insertar(Planta p) {
		return PlantaDao.insertarPlanta(p);
	}

	public int modificar(Planta p) {
		return PlantaDao.modificarPlanta(p);
	}

	public Planta findByCod(String codigo) {
		return PlantaDao.findByCodigo(codigo);
	}
	
	public List<Planta> findAll() {
		return PlantaDao.findAll();
	}
	

	
}