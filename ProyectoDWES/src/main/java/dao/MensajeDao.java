package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import modelo.Mensaje;

public class MensajeDao {
	private Connection con;
	private PreparedStatement ps;
	private ResultSet rs;

	public MensajeDao(Connection con) {
		this.con = con;
	}

	public int insertarMensaje(Mensaje m) {

		try {

			String sql = "INSERT INTO mensajes(fechaHora, mensaje, fk_idEjemplar, fk_idPersona) values (?,?,?,?)";
			ps = con.prepareStatement(sql);

			ps.setTimestamp(1, Timestamp.valueOf(m.getfechaHora()));
			ps.setString(2, m.getMensaje());
			ps.setLong(3, m.getfk_idEjemplar());
			ps.setLong(4, m.getfk_idPersona());

			return ps.executeUpdate();

		} catch (SQLException e) {
			System.out.println("Error al insertar en mensajes" + e.getMessage());
		}

		return 0;
	}

	public List<Mensaje> findByEjemplar(Long idEjemplar) {
	    List<Mensaje> listaMensajes = new ArrayList<>();
	    try {
	        ps = con.prepareStatement(
	            "SELECT mensajes.id, mensajes.mensaje, mensajes.fechaHora, mensajes.fk_idPersona, mensajes.fk_idEjemplar " +
	            "FROM mensajes INNER JOIN ejemplares ON mensajes.fk_idEjemplar = ejemplares.id " +
	            "WHERE ejemplares.id = ?"
	        );
	        ps.setLong(1, idEjemplar);
	        rs = ps.executeQuery();

	        while (rs.next()) {
	            Mensaje m = new Mensaje(
	                rs.getLong("id"),
	                rs.getString("mensaje"),
	                rs.getTimestamp("fechaHora").toLocalDateTime(),
	                rs.getLong("fk_idPersona"),
	                rs.getLong("fk_idEjemplar")
	            );
	            listaMensajes.add(m);
	        }

	        return listaMensajes;

	    } catch (SQLException e) {
	        System.out.println("Error al consultar por ejemplar " + e.getMessage());
	    }
	    return null;
	}

	
	
	public List<Mensaje> findByTipo(String tipo) { 
		List<Mensaje> listaMensajes = new ArrayList<Mensaje>();
		try {
			ps = con.prepareStatement("SELECT * FROM mensajes INNER JOIN plantas ON mensajes.fk_idEjemplar = plantas.codigo WHERE plantas.nombreComun =?"); 
			ps.setString(1, tipo);
			rs = ps.executeQuery();

			while (rs.next()) {
				listaMensajes.add(new Mensaje(rs.getString(3), rs.getTimestamp(2).toLocalDateTime(), rs.getLong(4), rs.getLong(5)));
			}
			return listaMensajes;
		} catch (SQLException e) {
			System.out.println("Error al consultar por tipo " + e.getMessage());
	
		}
		return null;
	}


	public List<Mensaje> findAll() {
	    List<Mensaje> lista = new ArrayList<>();
	    try {
	        ps = con.prepareStatement("SELECT id, mensaje, fechaHora, fk_idPersona, fk_idEjemplar FROM mensajes");
	        rs = ps.executeQuery();

	        while (rs.next()) {
	            Mensaje m = new Mensaje(
	                rs.getLong("id"),
	                rs.getString("mensaje"),
	                rs.getTimestamp("fechaHora").toLocalDateTime(),
	                rs.getLong("fk_idPersona"),
	                rs.getLong("fk_idEjemplar")
	            );
	            lista.add(m);
	        }
	    } catch (SQLException e) {
	        System.out.println("Error al consultar todos los mensajes: " + e.getMessage());
	    }
	    return lista;
	}
	
	public int contarMensajesPorEjemplar(Long idEjemplar) {
	    try {
	        ps = con.prepareStatement("SELECT COUNT(*) FROM mensajes WHERE fk_idEjemplar = ?");
	        ps.setLong(1, idEjemplar);
	        rs = ps.executeQuery();
	        if (rs.next()) {
	            return rs.getInt(1);
	        }
	    } catch (SQLException e) {
	        System.out.println("Error al contar mensajes: " + e.getMessage());
	    }
	    return 0;
	}

	public LocalDateTime obtenerUltimaFechaMensaje(Long idEjemplar) {
	    try {
	        ps = con.prepareStatement(
	            "SELECT fechaHora FROM mensajes WHERE fk_idEjemplar = ? ORDER BY fechaHora DESC LIMIT 1"
	        );
	        ps.setLong(1, idEjemplar);
	        rs = ps.executeQuery();
	        if (rs.next()) {
	            return rs.getTimestamp("fechaHora").toLocalDateTime();
	        }
	    } catch (SQLException e) {
	        System.out.println("Error al obtener última fecha de mensaje: " + e.getMessage());
	    }
	    return null;
	}

	
	
	public List<Mensaje> findByNombrePersona(String nombre) {
	    List<Mensaje> lista = new ArrayList<>();
	    try {
	        ps = con.prepareStatement(
	            "SELECT m.id, m.mensaje, m.fechaHora, m.fk_idPersona, m.fk_idEjemplar " +
	            "FROM mensajes m " +
	            "JOIN personas p ON m.fk_idPersona = p.id " +
	            "WHERE LOWER(p.nombre) = LOWER(?)"
	        );
	        ps.setString(1, nombre);
	        rs = ps.executeQuery();

	        while (rs.next()) {
	            lista.add(new Mensaje(
	                rs.getLong("id"),
	                rs.getString("mensaje"),
	                rs.getTimestamp("fechaHora").toLocalDateTime(),
	                rs.getLong("fk_idPersona"),
	                rs.getLong("fk_idEjemplar")
	            ));
	        }
	    } catch (SQLException e) {
	        System.out.println("Error al buscar mensajes por persona: " + e.getMessage());
	    }
	    return lista;
	}

	public List<Mensaje> findByEntreFechas(LocalDateTime inicio, LocalDateTime fin) {
	    List<Mensaje> lista = new ArrayList<>();
	    try {
	        ps = con.prepareStatement(
	            "SELECT * FROM mensajes WHERE fechaHora BETWEEN ? AND ?"
	        );
	        ps.setTimestamp(1, Timestamp.valueOf(inicio));
	        ps.setTimestamp(2, Timestamp.valueOf(fin));
	        rs = ps.executeQuery();

	        while (rs.next()) {
	            lista.add(new Mensaje(
	                rs.getLong("id"),
	                rs.getString("mensaje"),
	                rs.getTimestamp("fechaHora").toLocalDateTime(),
	                rs.getLong("fk_idPersona"),
	                rs.getLong("fk_idEjemplar")
	            ));
	        }
	    } catch (SQLException e) {
	        System.out.println("Error al buscar mensajes por fechas: " + e.getMessage());
	    }
	    return lista;
	}

	public List<Mensaje> findByCodigoPlanta(String codigoPlanta) {
	    List<Mensaje> lista = new ArrayList<>();
	    try {
	        ps = con.prepareStatement(
	            "SELECT m.id, m.mensaje, m.fechaHora, m.fk_idPersona, m.fk_idEjemplar " +
	            "FROM mensajes m " +
	            "JOIN ejemplares e ON m.fk_idEjemplar = e.id " +
	            "JOIN plantas p ON e.fk_planta = p.codigo " +
	            "WHERE LOWER(p.codigo) = LOWER(?) OR LOWER(p.nombreComun) = LOWER(?)"
	        );
	        ps.setString(1, codigoPlanta);
	        ps.setString(2, codigoPlanta);
	        rs = ps.executeQuery();

	        while (rs.next()) {
	            lista.add(new Mensaje(
	                rs.getLong("id"),
	                rs.getString("mensaje"),
	                rs.getTimestamp("fechaHora").toLocalDateTime(),
	                rs.getLong("fk_idPersona"),
	                rs.getLong("fk_idEjemplar")
	            ));
	        }
	    } catch (SQLException e) {
	        System.out.println("Error al buscar mensajes por planta: " + e.getMessage());
	    }
	    return lista;
	}
	
	


}
