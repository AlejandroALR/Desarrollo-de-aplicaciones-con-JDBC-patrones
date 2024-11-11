package control;

import java.time.LocalDateTime;
import java.util.List;

import conexion.ConexionBBDD;
import dao.MensajeDao;
import modelo.Mensaje;

public class ServiciosMensaje {

	private ConexionBBDD con;
	private MensajeDao MensajeDao;
	
	public ServiciosMensaje() {
		con=ConexionBBDD.getInstance();
		MensajeDao = con.getMensajeDao();
	}
	
	public int insertarMensaje(Mensaje m) {
			return MensajeDao.insertarMensaje(m);
	}
	
	public List <Mensaje> findByTipo(String tipo) {
			return MensajeDao.findByTipo(tipo);
	}
	
	public List <Mensaje> findByEjemplar(int idEjemplar) {
		return MensajeDao.findByEjemplar(idEjemplar);
	}
	
	public List <Mensaje> findAll() {
		return MensajeDao.findAll();
	}
	
	public void registrarMensaje(int id, int fk_idPersona, String mensaje) {
		Mensaje m = new Mensaje(mensaje, LocalDateTime.now(), fk_idPersona, id);
		this.insertarMensaje(m);
	}
}
