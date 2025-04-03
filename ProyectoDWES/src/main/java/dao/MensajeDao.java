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

			String sql = "INSERT INTO mensajes(fechaHora, mensaje, idEjemplar, idPersona) values (?,?,?,?)";
			ps = con.prepareStatement(sql);

			ps.setTimestamp(1, Timestamp.valueOf(m.getFechaHora()));
			ps.setString(2, m.getMensaje());
			ps.setLong(3, m.getfk_idEjemplar());
			ps.setLong(4, m.getfk_idPersona());

			return ps.executeUpdate();

		} catch (SQLException e) {
			System.out.println("Error al insertar en mensajes" + e.getMessage());
		}

		return 0;
	}

	public List<Mensaje> findByEjemplar(int idEjemplar) { 
		List<Mensaje> listaMensajes = new ArrayList<Mensaje>();
		try {
			
			ps = con.prepareStatement("SELECT * FROM mensajes INNER JOIN ejemplares ON mensajes.fk_idEjemplar = ejemplares.id WHERE ejemplares.id=?"); 
			ps.setInt(1, idEjemplar);
			rs = ps.executeQuery();

			while (rs.next()) {
				listaMensajes.add(new Mensaje( rs.getString(2), rs.getTimestamp(3).toLocalDateTime(), rs.getLong(4), rs.getLong(5)));
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
		List<Mensaje> listaMensajes = new ArrayList<Mensaje>();
		try {
			
			ps = con.prepareStatement("SELECT * FROM mensajes"); 
			rs = ps.executeQuery();

			while (rs.next()) {
				listaMensajes.add(new Mensaje(rs.getString(2), rs.getTimestamp(3).toLocalDateTime(), rs.getLong(4), rs.getLong(5)));
			}
			return listaMensajes;
			
		} catch (SQLException e) {
			System.out.println("Error al consultar por ejemplar " + e.getMessage());
		}
		return null;
	}
}
