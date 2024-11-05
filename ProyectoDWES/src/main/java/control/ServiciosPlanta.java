package control;

import conexion.ConexionBBDD;
import dao.PlantaDao;
import modelo.Planta;

public class ServiciosPlanta {
	
	private ConexionBBDD con;
	private PlantaDao PlantaDao;
	
	public ServiciosPlanta() {
		con=ConexionBBDD.getInstance();
		PlantaDao=(PlantaDao) con.getPlantaDao();
	}
	
	public boolean validarPlanta(Planta p) {
		boolean ret = false;
		if(p.getCodigo().isEmpty())return false;
		if(p.getCodigo().length()<3 || p.getCodigo().length()>20)return false;
		
		return true;
	}
	
	public int insertar(Planta e) {
		return PlantaDao.insertar(e);
	}	
	
}