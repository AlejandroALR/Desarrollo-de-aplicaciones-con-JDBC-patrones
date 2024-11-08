package dao;

import java.sql.*;
import java.util.ArrayList;

import modelo.Credenciales;

public class CredencialesDao {
    Connection con;
    private PreparedStatement ps;
	private ResultSet rs;

    public CredencialesDao(Connection connection) {
        this.con = connection;
    }

    public long insertarCredenciales(String usuario, String password, Long idPersona) {
    	int filas = 0;
		String insertarCredenciales = "INSERT INTO credenciales (usuario, password, idPersona) VALUES (?, ?, ?)";
		try (
			PreparedStatement ps = con.prepareStatement(insertarCredenciales,
			PreparedStatement.RETURN_GENERATED_KEYS)) {
			
			ps.setString(1, usuario);
			ps.setString(2, password);
			ps.setLong(3, idPersona);
			filas = ps.executeUpdate();
			
		} catch (SQLException e) {
			System.out.println("Error al insertar las credenciales, vuelva a intentarlo.");
		}
		return filas;

	}

	public boolean usuarioExist(String usuario) {
		String usuarioExist = "SELECT usuario FROM CREDENCIALES";
		ArrayList<String> usuariosExist = new ArrayList<String>();
		try {
			ps = con.prepareStatement(usuarioExist);
			rs = ps.executeQuery();
			while (rs.next()) {
				usuariosExist.add(rs.getString(1));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		if (usuarioExist.contains(usuario)) {
			return true;
		} else {
			return false;
		}

	}
    
	public boolean validarCredenciales(String usuario, String password) {
        String consulta = "SELECT COUNT(*) FROM credenciales WHERE usuario = ? AND contrasena = ?";
        try (PreparedStatement ps= con.prepareStatement(consulta)) {
        	
            ps.setString(1, usuario);
            ps.setString(2, password);
            ResultSet rs = ps.executeQuery();
            
            if (rs.next()) {
                return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false; 
	}
	
//    public void eliminarCredenciales(int personaId) throws SQLException {
//        String sql = "DELETE FROM Credenciales WHERE persona_id = ?";
//        try (PreparedStatement stmt = con.prepareStatement(sql)) {
//            stmt.setInt(1, personaId);
//            stmt.executeUpdate();
//        }
//    }


}

