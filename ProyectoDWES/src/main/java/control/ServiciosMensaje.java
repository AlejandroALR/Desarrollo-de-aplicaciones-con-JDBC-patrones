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
	
	public List <Mensaje> findByEjemplar(Long idEjemplar) {
		return MensajeDao.findByEjemplar(idEjemplar);
	}
	
	public List <Mensaje> findAll() {
		return MensajeDao.findAll();
	}
	
	public void registrarMensaje(Long id, Long fk_idPersona, String mensaje) {
		Mensaje m = new Mensaje(mensaje, LocalDateTime.now(), fk_idPersona, id);
		this.insertarMensaje(m);
	}
	
	public int contarMensajesPorEjemplar(Long id) {
	    return MensajeDao.contarMensajesPorEjemplar(id);
	}

	public LocalDateTime obtenerUltimaFechaMensaje(Long id) {
	    return MensajeDao.obtenerUltimaFechaMensaje(id);
	}

	
	public List<Mensaje> findByNombrePersona(String nombre) {
	    return MensajeDao.findByNombrePersona(nombre);
	}

	public List<Mensaje> findByEntreFechas(LocalDateTime inicio, LocalDateTime fin) {
	    return MensajeDao.findByEntreFechas(inicio, fin);
	}

	public List<Mensaje> findByCodigoPlanta(String codigo) {
	    return MensajeDao.findByCodigoPlanta(codigo);
	}

}
