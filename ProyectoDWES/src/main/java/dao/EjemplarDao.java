package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Collection;

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
			ps = con.prepareStatement("insert into ejemplar (id, nombre, codPlanta) values (?,?)");
			
			ps.setLong(1, ej.getId());
			ps.setString(2, ej.getNombre());
			ps.setString(3, ej.getcodPlanta());
			
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

	public Ejemplar findById(Ejemplar ej) {
		Ejemplar ejemplar = null;
        String consulta = "SELECT * FROM ejemplares WHERE id = ?";
        try (
        	PreparedStatement ps = con.prepareStatement(consulta)) {
            ps.setLong(1, ej.getId());
            try (
            	ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    ejemplar = new Ejemplar();
                    ejemplar.setId(rs.getLong("id"));
                    ejemplar.setNombre(rs.getString("nombre"));
                    ejemplar.setcodPlanta(rs.getString("codigoPlanta"));
                }
            }
        } catch (SQLException e) {
            System.err.println("Error al obtener el " + ej.getId() + ": " + e.getMessage());
        }

        return ejemplar;
	}
	
	public Collection <Ejemplar> verTodas() {
		return null;
	}
}
