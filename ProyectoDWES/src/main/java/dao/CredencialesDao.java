package dao;

import java.sql.*;

import modelo.Credenciales;

public class CredencialesDao {
    private Connection connection;

    public CredencialesDao(Connection connection) {
        this.connection = connection;
    }

    public void insertarCredenciales(Credenciales credenciales, int personaId) throws SQLException {
        String sql = "INSERT INTO Credenciales (persona_id, usuario, password) VALUES (?, ?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, personaId);
            stmt.setString(2, credenciales.getUsuario());
            stmt.setString(3, credenciales.getPassword());
            stmt.executeUpdate();
        }
    }

    public Credenciales obtenerCredencialesPorPersonaId(int personaId) throws SQLException {
        String sql = "SELECT * FROM Credenciales WHERE persona_id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, personaId);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return new Credenciales(null, rs.getString("usuario"), rs.getString("password"));
            }
        }
        return null;
    }

    public void eliminarCredenciales(int personaId) throws SQLException {
        String sql = "DELETE FROM Credenciales WHERE persona_id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, personaId);
            stmt.executeUpdate();
        }
    }
}

