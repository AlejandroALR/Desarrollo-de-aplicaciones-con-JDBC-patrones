package control;

import java.util.List;

import conexion.ConexionBBDD;
import dao.CredencialesDao;
import modelo.Credenciales;

public class ServiciosCredenciales {

		private ConexionBBDD con;
		private CredencialesDao CredencialesDao;
		
		public ServiciosCredenciales() {
			con=ConexionBBDD.getInstance();
			CredencialesDao= con.getCredencialesDao();
		}
		
		public boolean validarNuevaCredencial(Credenciales c) {
			if(this.findByUsuario(c.getUsuario()) == null) {
				return true;
			}else {
				return false;
			}
		}
		
		public boolean validarCredenciales(Credenciales c) {
			if(this.findByUsuario(c.getUsuario()) == null) {
				return false;
			}
			else if(this.findByUsuario(c.getUsuario()).getPassword().equals(c.getPassword())){
				return true;
				}else {
					return false;
				}
		}
		
		public String validarTipoUsu(Credenciales c) { 
			if(c.getUsuario().equals("invitado") && c.getPassword().equals("invitado")) {
				return "invitado";
			}
			else if(c.getUsuario().equals("admin") && c.getPassword().equals("admin")) {
				return "admin";
			} else {
					return "personal";
			}
		}
		
		
		public int insertar(Credenciales c) {
			return CredencialesDao.insertarCredenciales(c);
		}

		public Credenciales findByUsuario(String usuario) {
			return CredencialesDao.findByUsu(usuario);
		}

		public List<Credenciales> findAll(){
			return CredencialesDao.findAll();
		}
		
		public void registrarNuevoCredencial(String usuario, String password, String email) {
			
			int id = Controlador.getServicios().getServiciosPersona().findByEmail(email).getId();
			Credenciales c = new Credenciales(usuario, password, id);
			
		this.insertar(c);
		}
		
//		public long insertarCredencial(String usuario, String password, Long idPersona) {
//			return credencialesDao.insertarCredenciales(usuario, password, idPersona);
//
//		}
//		
//		public boolean usuarioExist(String usuario) {
//			return credencialesDao.usuarioExist(usuario);
//		}
		
		
}
