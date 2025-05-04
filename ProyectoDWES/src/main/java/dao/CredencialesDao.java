package dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import modelo.Credenciales;

public class CredencialesDao {
    Connection con;
    private PreparedStatement ps;
	private ResultSet rs;

    public CredencialesDao(Connection con) {
        this.con = con;
    }
 
	public int insertarCredenciales(Credenciales c) {
		try {
			ps = con.prepareStatement("INSERT INTO credenciales (usuario, password, fk_idPersona) VALUES (?,?,?)");
			ps.setString(1, c.getUsuario());
			ps.setString(2, c.getPassword());
			ps.setLong(3, c.getfk_idPersona());
			return ps.executeUpdate();
		} catch (SQLException e) {
			System.out.println("Error al insertar los credenciales " + e.getMessage());
		}
		return 0;
	}
	

	public Credenciales findByUsu(String usuario) {
		try {
			ps = con.prepareStatement("SELECT * FROM credenciales where usuario=?");
			ps.setString(1, usuario);
			rs = ps.executeQuery();
			if (rs.next()) {
				return new Credenciales(rs.getString(2), rs.getString(3),rs.getLong(1));
			}
		} catch (SQLException e) {
			System.out.println("Error al buscar usuario." + e.getMessage());
		}
		return null;
		}
	
	public List<Credenciales> findAll() {
		List<Credenciales> listaCredenciales = new ArrayList<Credenciales>();
		try {
			ps = con.prepareStatement("SELECT * FROM credenciales");
			rs = ps.executeQuery();

			while (rs.next()) {
				listaCredenciales.add(new Credenciales(rs.getString("usuario"), rs.getString(3), rs.getLong(1)));
			}
			return listaCredenciales;
		} catch (SQLException e) {
			System.out.println("Error al consultar " + e.getMessage());

		}
		return null;
	}

	}
    
//	public boolean validarCredenciales(String usuario, String password) {
//        String consulta = "SELECT COUNT(*) FROM credenciales WHERE usuario = ? AND contrasena = ?";
//        try (PreparedStatement ps= con.prepareStatement(consulta)) {
//        	
//            ps.setString(1, usuario);
//            ps.setString(2, password);
//            ResultSet rs = ps.executeQuery();
//            
//            if (rs.next()) {
//                return true;
//            }
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//        return false; 
//	}
//	
//    public void eliminarCredenciales(int personaId) throws SQLException {
//        String sql = "DELETE FROM Credenciales WHERE persona_id = ?";
//        try (PreparedStatement stmt = con.prepareStatement(sql)) {
//            stmt.setInt(1, personaId);
//            stmt.executeUpdate();
//        }
//    }

//public long insertarCredenciales(String usuario, String password, Long idPersona) {
//int filas = 0;
//String insertarCredenciales = "INSERT INTO credenciales (usuario, password, idPersona) VALUES (?, ?, ?)";
//try (
//	PreparedStatement ps = con.prepareStatement(insertarCredenciales,
//	PreparedStatement.RETURN_GENERATED_KEYS)) {
//	
//	ps.setString(1, usuario);
//	ps.setString(2, password);
//	ps.setLong(3, idPersona);
//	filas = ps.executeUpdate();
//	
//} catch (SQLException e) {
//	System.out.println("Error al insertar las credenciales, vuelva a intentarlo.");
//}
//return filas;
//
//}



