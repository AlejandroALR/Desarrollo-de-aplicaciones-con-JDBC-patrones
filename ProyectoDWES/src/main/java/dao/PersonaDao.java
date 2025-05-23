package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import modelo.Persona;

public class PersonaDao {
	private Connection con;
	private PreparedStatement ps;
	private ResultSet rs;
	
    public PersonaDao(Connection con) {
    	this.con = con;
    }

    public int insertarPersona (Persona pe) {
    	try {
    		
    		ps = con.prepareStatement("INSERT INTO personas (nombre, email) VALUES (?,?)");
    		ps.setString(1, pe.getNombre());
    		ps.setString(2, pe.getEmail());
    		return ps.executeUpdate();
    		
    	}catch (SQLException e) {
    		System.out.println("Error al insertar en personas" + e.getMessage());
    	}
    	return 0;
    }
    
    
	public Persona findById(Long id) {
		try {
			
			ps = con.prepareStatement("SELECT * FROM personas WHERE id=?");
			ps.setLong(1, id);
			rs = ps.executeQuery();
			if (rs.next())
				
				 return new Persona(rs.getLong(1), rs.getString(2), rs.getString(3), rs.getLong(4));

		} catch (SQLException e) {
			System.out.println("Error al consultar por id " + e.getMessage());

		}
		return null;
	}
    
	public Persona findByEmail(String email) {
		try {
			
			ps = con.prepareStatement("SELECT * FROM personas WHERE email=?");
			ps.setString(1, email);
			rs = ps.executeQuery();
			if (rs.next())
				return new Persona(rs.getLong(1),rs.getString(2), rs.getString(3), rs.getLong(4));

		} catch (SQLException e) {
			System.out.println("Error al consultar por email " + e.getMessage());

		}
		return null;
	}
    
	public List<Persona> findAll() {
		List<Persona> listaPersonas = new ArrayList<Persona>();
		try {
			
			ps = con.prepareStatement("SELECT * FROM personas");
			rs = ps.executeQuery();
			while (rs.next()) {
				listaPersonas.add(new Persona(rs.getLong(1), rs.getString(2), rs.getString(3), rs.getLong(4)));
			}
			return listaPersonas;
			
		} catch (SQLException e) {
			System.out.println("Error al consultar" + e.getMessage());

		}
		return null;
	}
	
	public void actualizarFkCredenciales(long idPersona, long idCredenciales) {
	    try {
	        ps = con.prepareStatement("UPDATE personas SET fk_idCredenciales = ? WHERE id = ?");
	        ps.setLong(1, idCredenciales);
	        ps.setLong(2, idPersona);
	        ps.executeUpdate();
	    } catch (SQLException e) {
	        System.out.println("Error al actualizar fk_idCredenciales: " + e.getMessage());
	    }
	}
}