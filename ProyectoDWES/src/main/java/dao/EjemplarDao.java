package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import modelo.Ejemplar;

public class EjemplarDao {
	Connection con;
	private ResultSet rs;
	private PreparedStatement ps;

	
	public EjemplarDao (Connection con) {
			this.con = con;
	}
	
	public long insertarEjemplar (Ejemplar ej) {
		try {
			
			ps = con.prepareStatement("insert into ejemplar (id, nombre, fk_codPlanta) values (?,?,?)");
			ps.setLong(1, ej.getId());
			ps.setString(2, ej.getNombre());
			ps.setString(3, ej.getfk_codPlanta());
			return ps.executeUpdate();
			
		} catch (SQLException e) {
			System.out.println("Error al insertar en ejemplar" + e.getMessage());
		}
		return 0;
	}
	
	public Ejemplar findById(int id) {
		try {
			
			ps = con.prepareStatement("SELECT * FROM ejemplares");
			rs = ps.executeQuery();
			
			if (rs.next()) {
				return new Ejemplar(rs.getString(2), rs.getString(3));
			}
			
		} catch (SQLException e) {
			System.out.println("Error al consultar por tipo " + e.getMessage());

		}
		return null;
	}
	
	public int findLastId() {
		try {
			
			ps = con.prepareStatement("SELECT LAST(id) FROM ejemplares");
			rs = ps.executeQuery();

			if (rs.next()) {
				return rs.getInt(1);
			}
			
		} catch (SQLException e) {
			System.out.println("Error al consultar por tipo " + e.getMessage());

		}
		return (Integer) null;
	}

	public List<Ejemplar> findAll() {
		List<Ejemplar> listaEjemplares = new ArrayList<Ejemplar>();
		try {
			
			ps = con.prepareStatement("SELECT * FROM ejemplares");
			rs = ps.executeQuery();

			while (rs.next()) {
				listaEjemplares.add(new Ejemplar(rs.getLong(1), rs.getString(2), rs.getString(3)));
			}
			return listaEjemplares;
			
		} catch (SQLException e) {
			System.out.println("Error al consultar por tipo " + e.getMessage());

		}
		return null;
	}
	
	public List<Ejemplar> findByTipo(String tipo) {
		List<Ejemplar> listaEjemplares = new ArrayList<Ejemplar>();
		try {
			
			ps = con.prepareStatement("SELECT * FROM ejemplares INNER JOIN plantas ON ejemplares.fk_codPlanta = plantas.codigo WHERE plantas.codigo IN (?)");
			ps.setString(1, tipo);
			rs = ps.executeQuery();

			while (rs.next()) {
				listaEjemplares.add(new Ejemplar(rs.getString(2), rs.getString(3)));
			}
			return listaEjemplares;
			
		} catch (SQLException e) {
			System.out.println("Error al consultar por tipo " + e.getMessage());

		}
		return null;
	}
	
}
