package dao;

import java.sql.PreparedStatement;
import java.sql.SQLException;

import conexion.ConexionBBDD;
import modelo.Planta;

public class PlantaDao {

	public static void añadirPlanta (Planta planta) {
		String sql = "INSERT INTO plantas(codigo, nombrecomun, nombrecientifico) VALUES (?, ?, ?)";
		try (PreparedStatement pstmt = ConexionBBDD.getConnection().prepareStatement(sql)) {
		    pstmt.setString(1, planta.getCodigo()); 
		    pstmt.setString(2, planta.getNombrecomun());
		    pstmt.setString(3, planta.getNombrecientifico());
		    
		    pstmt.executeUpdate(); 
		} catch (SQLException e) {
		    e.printStackTrace(); 
		}
	}
}
