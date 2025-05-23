package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import conexion.ConexionBBDD;
import modelo.Planta;

public class PlantaDao {
	private Connection con;
	private PreparedStatement ps;
	private ResultSet rs;
	
    public PlantaDao(Connection con) {
    	this.con = con;
    }

    public int insertarPlanta(Planta p) {
    	try {
    		
    		ps = con.prepareStatement("INSERT INTO plantas (codigo, nombreComun, nombreCientifico) values(?,?,?)");
    		ps.setString(1, p.getCodigo());
    		ps.setString(2, p.getNombreComun());
    		ps.setString(3, p.getNombreCientifico());
    		return ps.executeUpdate();
    		
    	}catch (SQLException e) {
    		System.out.println("Error al insertar en planta" + e.getMessage());
    	}
    	return 0;
    }
    
    public int modificarPlanta(Planta p) {
    	try {
    		
    		ps = con.prepareStatement("UPDATE plantas SET nombreComun=?, nombreCientifico=? WHERE codigo=?");
    		ps.setString(1, p.getNombreComun());
    		ps.setString(2, p.getNombreCientifico());
    		ps.setString(3, p.getCodigo());
			return ps.executeUpdate();
			
		} catch (SQLException e) {
			System.out.println("Error al modificar en planta" + e.getMessage());
		}
		return 0;

	}
    
    public int eliminarPlanta(Planta p) {
    	try {
    		
    		ps = con.prepareStatement("DELETE FROM plantas WHERE codigo=?");
    		ps.setString(1, p.getCodigo());
    		return ps.executeUpdate();
    		
    	}catch(SQLException e) {
    		System.out.println("Error al eliminar la planta " + e.getMessage());
    	}
    	return 0;
    }
    
	public Planta findByCodigo(String codigo) {
		try {
			
			ps = con.prepareStatement("SELECT * FROM plantas WHERE codigo=?");
			ps.setString(1, codigo);
			rs = ps.executeQuery();
			if (rs.next())
				return new Planta(rs.getString(1), rs.getString(2), rs.getString(3));

		} catch (SQLException e) {
			System.out.println("Error al consultar por código " + e.getMessage());

		}
		return null;
	}
    
	public List<Planta> findAll() {
		List<Planta> Plantas = new ArrayList<Planta>();
		try {
			
			ps = con.prepareStatement("SELECT * FROM plantas ORDER BY codigo ASC");
			rs = ps.executeQuery();
			
			while (rs.next()) {
				Plantas.add(new Planta(rs.getString(1), rs.getString(2), rs.getString(3)));
			}
			return Plantas;

		} catch (SQLException e) {
			System.out.println("Error al obtener todas las plantas " + e.getMessage());

		}
		return null;
	}

    
}
