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
 
    
    public long insertarCredenciales(Credenciales c) {
        try {
            ps = con.prepareStatement(
                "INSERT INTO credenciales (usuario, password, fk_idPersona) VALUES (?, ?, ?)",
                PreparedStatement.RETURN_GENERATED_KEYS
            );
            ps.setString(1, c.getUsuario());
            ps.setString(2, c.getPassword());
            ps.setLong(3, c.getfk_idPersona());

            int filasAfectadas = ps.executeUpdate();

            if (filasAfectadas == 0) {
                throw new SQLException("No se pudo insertar la credencial.");
            }

            try (ResultSet rs = ps.getGeneratedKeys()) {
                if (rs.next()) {
                    return rs.getLong(1);
                } else {
                    throw new SQLException("No se generó ningún ID para la credencial.");
                }
            }

        } catch (SQLException e) {
            System.out.println("Error al insertar las credenciales: " + e.getMessage());
        }
        return -1;
    }
	
	public boolean usuarioExiste(String usuario) {
	    try {
	        ps = con.prepareStatement("SELECT COUNT(*) FROM credenciales WHERE usuario = ?");
	        ps.setString(1, usuario);
	        rs = ps.executeQuery();

	        if (rs.next()) {
	            return rs.getInt(1) > 0;
	        }

	    } catch (SQLException e) {
	        System.out.println("Error al verificar si el usuario existe: " + e.getMessage());
	    }
	    return false;
	}

	

	public Credenciales findByUsu(String usuario) {
		try {
			ps = con.prepareStatement("SELECT * FROM credenciales where usuario=?");
			ps.setString(1, usuario);
			rs = ps.executeQuery();
			if (rs.next()) {
				return new Credenciales(rs.getLong(1),rs.getString(2), rs.getString(3),rs.getLong(4));
			}
		} catch (SQLException e) {
			System.out.println("Error al buscar usuario." + e.getMessage());
		}
		return null;
		}
	
	
	public Credenciales findById(Long id) {
		try {
			ps = con.prepareStatement("SELECT * FROM credenciales where id=?");
			ps.setLong(1, id);
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