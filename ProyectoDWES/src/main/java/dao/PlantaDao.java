package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashSet;
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
    
    public boolean modificarPlanta(Planta p) {
    	try {
			String sql = "UPDATE planta SET nombrecomun = ?WHERE codigo = ?";
			PreparedStatement ps = con.prepareStatement(sql);
			
			ps.setString(1, p.getCodigo());
			ps.setString(2, p.getNombrecomun());
			ps.setString(3, p.getNombrecientifico());
			return ps.executeUpdate() > 0;
		} catch (SQLException e) {
			System.out.println("Error al modificar en planta" + e.getMessage());
		}
		return false;

	}
    
    public int eliminarPlanta(Planta p) {
    	try {
    		ps = con.prepareStatement("delete from plantas where codigo=?");
    		ps.setString(1, p.getCodigo());
    		return ps.executeUpdate();
    	}catch(SQLException e) {
    		System.out.println("Error al eliminar la planta " + e.getMessage());
    	}
    	return 0;
    }
    
    public Planta findByCodigo(String codigo) throws SQLException {
        String query = "SELECT * FROM plantas WHERE codigo = ?";
        try (PreparedStatement statement = con.prepareStatement(query)) {
            statement.setString(1, codigo);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                return new Planta(
                    resultSet.getString("codigo"),
                    resultSet.getString("nombrecomun"),
                    resultSet.getString("nombrecientifico")
                );
            }
        }
        return null;
    }
    
    public HashSet<Planta> findAll(){
		String sql = "SELECT * FROM plantas";
		HashSet<Planta> plantas = new HashSet<>();

		try {
			if (this.con == null || this.con.isClosed()) {
				this.con = ConexionBBDD.realizaConexion();
			}

			PreparedStatement ps = con.prepareStatement(sql);

			ResultSet res = ps.executeQuery();
			while (res.next()) {
				Planta planta = new Planta(
						res.getString("codigo"), 
						res.getString("nombrecomun"),
						res.getString("nombrecientifico"));
				plantas.add(planta);
			}
			ConexionBBDD.cerrarConexion();

		} catch (SQLException e) {
			System.out.println("Error al ver las plantas" + e.getMessage());
		}

		return plantas;
	}

    
    public Planta findByNombreComun(String nombrecomun) throws SQLException{
        String query = "SELECT * FROM plantas WHERE codigo = ?";
        try (PreparedStatement statement = con.prepareStatement(query)) {
            statement.setString(2, nombrecomun);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                return new Planta(
                    resultSet.getString("codigo"),
                    resultSet.getString("nombrecomun"),
                    resultSet.getString("nombrecientifico")
                );
            }
        }
    	return null;
	}
    
    public Planta findByNombreCientifico(String nombrecientifico) throws SQLException{
        String query = "SELECT * FROM plantas WHERE codigo = ?";
        try (PreparedStatement statement = con.prepareStatement(query)) {
            statement.setString(3, nombrecientifico);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                return new Planta(
                    resultSet.getString("codigo"),
                    resultSet.getString("nombrecomun"),
                    resultSet.getString("nombrecientifico")
                );
            }
        }
    	return null;
	}
    
}
