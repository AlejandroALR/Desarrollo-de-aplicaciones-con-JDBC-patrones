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

	public EjemplarDao(Connection con) {
		this.con = con;
	}

	public long insertarEjemplar(Ejemplar ej) {
		try {
			ps = con.prepareStatement("INSERT INTO ejemplares (nombre, fk_planta) VALUES (?, ?)",
					PreparedStatement.RETURN_GENERATED_KEYS);
			ps.setString(1, ej.getNombre());
			ps.setString(2, ej.getfk_planta());

			int filasAfectadas = ps.executeUpdate();
			if (filasAfectadas == 0) {
				throw new SQLException("No se pudo insertar el ejemplar.");
			}

			try (ResultSet generatedKeys = ps.getGeneratedKeys()) {
				if (generatedKeys.next()) {
					long idGenerado = generatedKeys.getLong(1);
					ej.setId(idGenerado);
					return idGenerado;
				} else {
					throw new SQLException("No se generó ningún ID para el ejemplar.");
				}
			}

		} catch (SQLException e) {
			System.out.println("Error al insertar en ejemplar: " + e.getMessage());
		}
		return 0;
	}

	public Ejemplar findById(Long id) {
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
	
	public Ejemplar findByNombre(String nombre) {
	    try {
	        ps = con.prepareStatement("SELECT * FROM ejemplares WHERE nombre = ?");
	        ps.setString(1, nombre);
	        rs = ps.executeQuery();

	        if (rs.next()) {
	            return new Ejemplar(
	                rs.getLong("id"),
	                rs.getString("nombre"),
	                rs.getString("fk_planta")
	            );
	        }

	    } catch (SQLException e) {
	        System.out.println("Error al buscar ejemplar por nombre: " + e.getMessage());
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

			ps = con.prepareStatement(
					"SELECT * FROM ejemplares INNER JOIN plantas ON ejemplares.fk_planta = plantas.codigo WHERE plantas.codigo IN (?)");
			ps.setString(1, tipo);
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

	public int actualizar(Ejemplar ej) {
		try {

			ps = con.prepareStatement("UPDATE ejemplares SET nombre = ?, fk_planta = ? WHERE id = ?");
			ps.setLong(3, ej.getId());
			ps.setString(1, ej.getNombre());
			ps.setString(2, ej.getfk_planta());
			return ps.executeUpdate();

		} catch (SQLException e) {
			System.out.println("Error al insertar en ejemplar" + e.getMessage());
		}
		return 0;

	}

	public int calcularIdAcordeAltipoDePlanta(String getfk_codPlanta) {
		List<Ejemplar> ejemplaresdetipoCodPlanta = this.findByTipo(getfk_codPlanta);

		return ejemplaresdetipoCodPlanta.size();
	}

}
