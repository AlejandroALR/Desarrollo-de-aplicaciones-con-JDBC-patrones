package control;

import conexion.ConexionBBDD;
import dao.CredencialesDao;
import modelo.Credenciales;

public class ServiciosCredenciales {

		private ConexionBBDD con;
		private CredencialesDao CredencialesDao;
		
		public ServiciosCredenciales() {
			con=ConexionBBDD.getInstance();
			CredencialesDao=(CredencialesDao) con.getCredencialesDao();
		}
		
		public boolean validarCredenciales(Credenciales c) {
			boolean ret = false;
			if(c.getUsuario().isEmpty())return false;
			if(c.getPassword().length()<2 || c.getPassword().length()>20)return false;
			return true;
		}
}
