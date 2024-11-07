package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashSet;

import com.google.protobuf.Timestamp;
import com.mysql.cj.x.protobuf.MysqlxCrud.Collection;

import conexion.ConexionBBDD;
import modelo.Ejemplar;
import modelo.Mensaje;
import modelo.Persona;

public class MensajeDao {
	private Connection con;
	private PreparedStatement ps;
	private ResultSet rs;

	public MensajeDao(Connection con) {

		if (this.con == null) {
			this.con = con;
		}
	}

	public long insertarMensaje(Mensaje m) {

		try {

			String sql = "INSERT INTO mensajes(id, mensaje, idEjemplar, idPersona) values (?,?,?,?,?)";
			ps = con.prepareStatement(sql);

			ps.setLong(1, m.getId());
			ps.setLocalDateTime(2, LocalDateTime.now((m.getFechaHora())));
			ps.setString(3, m.getMensaje());
			ps.setLong(4, Ejemplar.getI());
			ps.setPersona(5, m.getPersona());

			return ps.executeUpdate();

		} catch (SQLException e) {
			System.out.println("Error al insertar en mensaje" + e.getMessage());
		}

		return 0;
	}
	
    public int insetarMensaje (Mensaje m) {
    	try {
    		ps = con.prepareStatement("insert into mensaje (id, fechahora, mensaje, persona, ejemplar) values(?,?,?,?,?)");
    		ps.setLong(1, m.getId());
    		ps.setLong(3, m.getId());
    		ps.setLong(4, m.getId());
    		return ps.executeUpdate();
    	}catch (SQLException e) {
    		System.out.println("Error al insertar en persona" + e.getMessage());
    	}
    	return 0;
    }

	public boolean eliminarMensaje(Mensaje m) {
		return false;
	}

	public boolean modificarMensaje(Mensaje m) {
		return false;
	}

	public HashSet <Mensaje> findAll() {
		String sql = "SELECT * FROM mensajes";
		HashSet <Mensaje> mensajes = new HashSet<>();

		try {
			if (this.con == null || this.con.isClosed()) {
				this.con = ConexionBBDD.realizaConexion();
			}

			PreparedStatement ps = con.prepareStatement(sql);

			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				Mensaje mensaje = new Mensaje(
						rs.getLong("id"), 
						rs.getTimestamp("fechahora").toLocalDateTime(),
						rs.getString("mensaje"),
						rs.getLong("Ejemplar"),
						rs.getLong("Persona"));
				mensajes.add(mensaje);
			}
			ConexionBBDD.cerrarConexion();

		} catch (SQLException e) {
			System.out.println("Error al ver los mensajes" + e.getMessage());
		}

		return mensajes;

	}

	public Mensaje findById(long id) {
		return null;
	}
}
