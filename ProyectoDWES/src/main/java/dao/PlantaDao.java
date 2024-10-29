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
	private Connection connection;
	
    public void PlantaDAO(Connection connection) {
    	this.connection = connection;
    }

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
	
    public void actualizarPlanta(Planta planta) throws SQLException {
        String sql = "UPDATE Plantas SET nombre_comun = ?, nombre_cientifico = ? WHERE codigo = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, planta.getNombrecomun());
            stmt.setString(2, planta.getNombrecientifico());
            stmt.setString(3, planta.getCodigo());
            stmt.executeUpdate();
        }
    }
	
    public void eliminarPlanta(int id) throws SQLException {
        String sql = "DELETE FROM Plantas WHERE id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
        }
    }
    
    public List<Planta> obtenerTodasLasPlantas() throws SQLException {
        List<Planta> plantas = new ArrayList<>();
        String sql = "SELECT * FROM Plantas";
        try (Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                plantas.add(new Planta(
                    rs.getString("codigo"),
                    rs.getString("nombrecomun"),
                    rs.getString("nombrecientifico")
                ));
            }
        }
        return plantas;
    }
}
