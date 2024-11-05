package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
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

    public int insertar(Planta p) {
    	try {
    		ps = con.prepareStatement("insert into plantas (codigo, nombrecomun, nombrecientifico) values(?,?,?)");
    		ps.setString(1, p.getCodigo());
    		ps.setString(2, p.getNombrecomun());
    		ps.setString(3, p.getNombrecientifico());
    		return ps.executeUpdate();
    	}catch (SQLException e) {
    		System.out.println("Error al insertar en plantas" + e.getMessage());
    	}
    	return 0;
    }
    
    public int modificar(Planta p) {
    	return 0;
    }
    
    public int eliminar(Planta p) {
    	try {
    		ps = con.prepareStatement("delete from plantas where codigo=?");
    		ps.setString(1, p.getCodigo());
    		return ps.executeUpdate();
    	}catch(SQLException e) {
    		System.out.println("Error al eliminar la planta " + e.getMessage());
    	}
    	return 0;
    }
    
    public Planta findByCodigo(String cod) {
    	return null;
    }
    
    public List<Planta> findAll(){
    	return null;
    }
    
    public ArrayList <Planta> findByNombreComun(String nombre){
    	return null;
	}
    
    public ArrayList <Planta> findByNombreCientifico(String nombre){
    	return null;
	}
    
}
