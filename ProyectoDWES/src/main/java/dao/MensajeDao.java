package dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashSet;

import com.mysql.cj.x.protobuf.MysqlxCrud.Collection;

import conexion.ConexionBBDD;
import modelo.Ejemplar;
import modelo.Mensaje;
import modelo.Persona;

public class MensajeDao {
	private Connection con;
	private PreparedStatement ps;
	private ResultSet rs;

	public MensajeDao(Connection con) {

		if (this.con == null) {
			this.con = con;
		}
	}

	public long insertarMensaje(Mensaje m) {

		try {

			String sql = "INSERT INTO mensajes(id, mensaje, idEjemplar, idPersona) values (?,?,?,?,?)";
			ps = con.prepareStatement(sql);

			ps.setLong(1, m.getId());
			ps.setDate(2, Date.valueOf(m.getFechaHora()));
			ps.setString(3, m.getMensaje());
			ps.setInt(4, m.getEjemplar());
			ps.setInt(5, m.getPersona());

			return ps.executeUpdate();

		} catch (SQLException e) {
			System.out.println("Error al insertar en mensaje" + e.getMessage());
		}

		return 0;
	}
	
    public int insetarMensaje (Mensaje m) {
    	try {
    		ps = con.prepareStatement("insert into mensaje (id, fechahora, mensaje, persona, ejemplar) values(?,?,?,?,?)");
    		ps.setLong(1, m.getId());
    		ps.setLong(3, m.getId());
    		ps.setLong(4, m.getId());
    		return ps.executeUpdate();
    	}catch (SQLException e) {
    		System.out.println("Error al insertar en persona" + e.getMessage());
    	}
    	return 0;
    }

	public boolean eliminarMensaje(Mensaje m) {
		return false;
	}

	public boolean modificarMensaje(Mensaje m) {
		return false;
	}


	public Mensaje findById(long id) {
		return null;
	}
}
