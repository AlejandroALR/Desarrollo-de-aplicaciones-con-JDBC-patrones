package control;

import java.time.LocalDateTime;
import java.util.List;

import conexion.ConexionBBDD;
import dao.EjemplarDao;
import modelo.Ejemplar;
import modelo.Mensaje;
import modelo.Planta;

public class ServiciosEjemplar {
	
		private ConexionBBDD con;
		private EjemplarDao EjemplarDao;
		
		public ServiciosEjemplar() {
			con=ConexionBBDD.getInstance();
			EjemplarDao = (EjemplarDao) con.getEjemplarDao();
		}

		public void registrarEjemplar(Planta p, Long fk_codPlanta) {

			Ejemplar ej = new Ejemplar(p.getCodigo() + "_", p.getCodigo());
			this.insertarEjemplar(ej);

			int nuevoId = this.calcularIdAcordeAltipoDePlanta(ej.getfk_codPlanta());
			ej.setNombre(ej.getNombre()+nuevoId);
			this.actualizarEjemplar(ej);
			
			
			
			Mensaje m = new Mensaje("mensaje ", LocalDateTime.now(), fk_codPlanta, ej.getId());
			
			Controlador.getServicios().getServiciosMensaje().insertarMensaje(m);
		}
		
		private int actualizarEjemplar(Ejemplar ej) {
			return EjemplarDao.actualizar(ej);
		}

		private int calcularIdAcordeAltipoDePlanta(String getfk_codPlanta) {
			return EjemplarDao.calcularIdAcordeAltipoDePlanta(getfk_codPlanta) + 1;
			
		}

		
		public void filtrarEjemplares(String codigos) {
			List<Ejemplar> Ejemplares = Controlador.getServicios().getServiciosEjemplar().findByTipo(codigos);
			
			for(Ejemplar e : Ejemplares) {
				System.out.println();
				List<Mensaje> Mensajes = Controlador.getServicios().getServiciosMensaje().findByEjemplar(e.getId());
				
				int numMensajes = Mensajes.size();
				String ultFecha = Mensajes.get(0).getfechaHora().toString();
				
				System.out.println("Ejemplar: "+e.getNombre()+", Numero de mensajes: "+numMensajes+", Ultimo mensaje: "+ultFecha);
			}
		}
		
		public void mostrarEjemplares() {
			System.out.println();
			System.out.println("Estos son los ejemplares: ");
		 	List<Ejemplar> Ejemplares = Controlador.getServicios().getServiciosEjemplar().findAll();
			
			int cont = 1;
			
		 	for(Ejemplar e : Ejemplares) {
		 		System.out.println(cont + ": " + e.toString());
		 		cont++;
		 	}
		 	System.out.println();
		}
		
		public void verMensajes(Long id_ej) {
			System.out.println();
			System.out.println("Mensajes para el ejemplar "+id_ej+": ");
			
			List<Mensaje> listaMensajes = Controlador.getServicios().getServiciosMensaje().findByEjemplar(id_ej);
		 	if(listaMensajes == null || listaMensajes.isEmpty()) {
		 		System.out.println("No hay mensajes");
		 	}else {
				for(Mensaje m : listaMensajes) {
			 		System.out.println(m.toString());
			 	}
			 	System.out.println();
		 	}
		}
		
		
		public long insertarEjemplar(Ejemplar ej) {
			return EjemplarDao.insertarEjemplar(ej);
		}

		public List<Ejemplar> findByTipo(String tipos) {
			return EjemplarDao.findByTipo(tipos);
		}
		
		public List<Ejemplar> findAll() {
			return EjemplarDao.findAll();
		}
		
		public Ejemplar findById(int id) {
			return EjemplarDao.findById(id);
		}
		
		public int findLastId() {
			return EjemplarDao.findLastId();
		}
}
