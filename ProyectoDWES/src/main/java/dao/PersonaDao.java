package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

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
    		ps = con.prepareStatement("insert into persona (nombre, email) values(?,?)");
    		ps.setString(1, pe.getNombre());
    		ps.setString(2, pe.getEmail());
    		return ps.executeUpdate();
    	}catch (SQLException e) {
    		System.out.println("Error al insertar en persona" + e.getMessage());
    	}
    	return 0;
    }
    
    public int eliminarPersona (Persona pe) {
    	try {
    		ps = con.prepareStatement("delete from persona where id=?");
    		ps.setLong(1, pe.getId());
    		return ps.executeUpdate();
    	}catch(SQLException e) {
    		System.out.println("Error al eliminar la persona " + e.getMessage());
    	}
    	return 0;
    }
	
    public ArrayList <Persona> findById(Long id){
    	return null;
	}
    
}
