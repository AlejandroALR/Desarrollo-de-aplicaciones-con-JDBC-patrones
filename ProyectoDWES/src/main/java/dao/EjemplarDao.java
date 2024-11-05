package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Collection;

import modelo.Ejemplar;

public class EjemplarDao {
	private Connection con;
	private ResultSet rs;
	private Statement st;
	private PreparedStatement ps;

	
	public EjemplarDao (Connection con) {
			this.con = con;
	}
	
	public long insertarEjemplar (Ejemplar ej) {
		try {
			ps = con.prepareStatement("insert into ejemplar (nombre, planta) values (?,?)");
			ps.setString(1, ej.getNombre());
			ps.setString(2, ej.getPlanta());
			return ps.executeUpdate();
		} catch (SQLException e) {
			System.out.println("Error al insertar en ejemplar" + e.getMessage());
		}
		return 0;
	}
	
	public int modificarEjemplar (Ejemplar ej) {
		return 0;
	}

	public boolean eliminarEjemplar (Ejemplar ej) {
		try {
			String sql = "DELETE FROM ejemplar WHERE id=?";
			
			ps = con.prepareStatement(sql);
			ps.setLong(1, ej.getId());
			
		} catch (SQLException e) {
			System.out.println("Error al borrar en ejemplar" + e.getMessage());
			e.printStackTrace();
		}
		return false;

	}

	public Collection <Ejemplar> verTodas() {
		return null;
	}

	public Ejemplar findbyId(long id) {
		return null;
	}
}
