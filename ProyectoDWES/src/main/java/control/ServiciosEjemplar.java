package control;

import conexion.ConexionBBDD;
import dao.EjemplarDao;
import modelo.Ejemplar;

public class ServiciosEjemplar {
	
		private ConexionBBDD con;
		private EjemplarDao ejemplarDAO;
		
		public ServiciosEjemplar() {
			con=ConexionBBDD.getInstance();
			ejemplarDAO = (EjemplarDao) con.getEjemplarDao();
		}
		
		public long insertarEjemplar(Ejemplar ej) {
			return ejemplarDAO.insertarEjemplar(ej);
		}
		
		public Ejemplar findById(Ejemplar ej) {
			return ejemplarDAO.findById(ej);
		}
		
		public static boolean validarEjemplar(Ejemplar ej) {
	        boolean ret = false;
	        if(ej.getcodPlanta().isEmpty()) 
	        	return false;
	        if(ej.getcodPlanta().length()<3 || ej.getcodPlanta().length()>20)
	        return false;
	        
	        return true;
		}
}
