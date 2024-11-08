package control;

import conexion.ConexionBBDD;
import dao.CredencialesDao;
import modelo.Credenciales;

public class ServiciosCredenciales {

		private ConexionBBDD con;
		private CredencialesDao credencialesDao;
		
		public ServiciosCredenciales() {
			con=ConexionBBDD.getInstance();
			credencialesDao=(CredencialesDao) con.getCredencialesDao();
		}
		
		
		public boolean validarCredencial(String usuario, String password) {
			return credencialesDao.validarCredenciales(usuario, password);

		}
		
		public long insertarCredencial(String usuario, String password, Long idPersona) {
			return credencialesDao.insertarCredenciales(usuario, password, idPersona);

		}
		
		public boolean usuarioExist(String usuario) {
			return credencialesDao.usuarioExist(usuario);
		}
		
		
}
