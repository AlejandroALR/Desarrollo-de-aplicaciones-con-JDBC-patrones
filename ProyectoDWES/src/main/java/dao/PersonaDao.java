package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashSet;

import com.mysql.cj.x.protobuf.MysqlxCrud.Collection;

import conexion.ConexionBBDD;
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
    
    public boolean modificarPersona(Persona p) {
		try {

			String sql = "UPDATE persona SET email = ?, admin = ?, WHERE id = ?";
			PreparedStatement ps = con.prepareStatement(sql);

			ps.setString(1, p.getEmail());
			ps.setString(2, p.getNombre());
			ps.setLong(3, p.getId());

			return ps.executeUpdate() > 0;
		} catch (SQLException e) {
			System.out.println("Error al modificar en persona" + e.getMessage());
		}
		return false;

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
    
}
