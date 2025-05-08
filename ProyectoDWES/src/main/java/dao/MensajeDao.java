package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import modelo.Mensaje;

public class MensajeDao {
	private Connection con;
	private PreparedStatement ps;
	private ResultSet rs;

	public MensajeDao(Connection con) {
		this.con = con;
	}

	public int insertarMensaje(Mensaje m) {

		try {

			String sql = "INSERT INTO mensajes(fechaHora, mensaje, fk_idEjemplar, fk_idPersona) values (?,?,?,?)";
			ps = con.prepareStatement(sql);

			ps.setTimestamp(1, Timestamp.valueOf(m.getfechaHora()));
			ps.setString(2, m.getMensaje());
			ps.setLong(3, m.getfk_idEjemplar());
			ps.setLong(4, m.getfk_idPersona());

			return ps.executeUpdate();

		} catch (SQLException e) {
			System.out.println("Error al insertar en mensajes" + e.getMessage());
		}

		return 0;
	}

	public List<Mensaje> findByEjemplar(Long idEjemplar) {
	    List<Mensaje> listaMensajes = new ArrayList<>();
	    try {
	        ps = con.prepareStatement(
	            "SELECT mensajes.id, mensajes.mensaje, mensajes.fechaHora, mensajes.fk_idPersona, mensajes.fk_idEjemplar " +
	            "FROM mensajes INNER JOIN ejemplares ON mensajes.fk_idEjemplar = ejemplares.id " +
	            "WHERE ejemplares.id = ?"
	        );
	        ps.setLong(1, idEjemplar);
	        rs = ps.executeQuery();

	        while (rs.next()) {
	            Mensaje m = new Mensaje(
	                rs.getLong("id"),
	                rs.getString("mensaje"),
	                rs.getTimestamp("fechaHora").toLocalDateTime(),
	                rs.getLong("fk_idPersona"),
	                rs.getLong("fk_idEjemplar")
	            );
	            listaMensajes.add(m);
	        }

	        return listaMensajes;

	    } catch (SQLException e) {
	        System.out.println("Error al consultar por ejemplar " + e.getMessage());
	    }
	    return null;
	}

	
	
	public List<Mensaje> findByTipo(String tipo) { 
		List<Mensaje> listaMensajes = new ArrayList<Mensaje>();
		try {
			ps = con.prepareStatement("SELECT * FROM mensajes INNER JOIN plantas ON mensajes.fk_idEjemplar = plantas.codigo WHERE plantas.nombreComun =?"); 
			ps.setString(1, tipo);
			rs = ps.executeQuery();

			while (rs.next()) {
				listaMensajes.add(new Mensaje(rs.getString(3), rs.getTimestamp(2).toLocalDateTime(), rs.getLong(4), rs.getLong(5)));
			}
			return listaMensajes;
		} catch (SQLException e) {
			System.out.println("Error al consultar por tipo " + e.getMessage());
	
		}
		return null;
	}


	public List<Mensaje> findAll() {
	    List<Mensaje> lista = new ArrayList<>();
	    try {
	        ps = con.prepareStatement("SELECT id, mensaje, fechaHora, fk_idPersona, fk_idEjemplar FROM mensajes");
	        rs = ps.executeQuery();

	        while (rs.next()) {
	            Mensaje m = new Mensaje(
	                rs.getLong("id"),
	                rs.getString("mensaje"),
	                rs.getTimestamp("fechaHora").toLocalDateTime(),
	                rs.getLong("fk_idPersona"),
	                rs.getLong("fk_idEjemplar")
	            );
	            lista.add(m);
	        }
	    } catch (SQLException e) {
	        System.out.println("Error al consultar todos los mensajes: " + e.getMessage());
	    }
	    return lista;
	}
	
	public List<Mensaje> findByCodigoPlanta() {
		return null;
		}
	
	public List<Mensaje> findByEntreFechas(){
		return null;
		}
	
	public List<Mensaje> findByNombrePersona(){
		return null;
		}

}
