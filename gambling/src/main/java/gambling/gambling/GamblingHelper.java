package gambling.gambling;

import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class GamblingHelper {
	public Connection crearConexion() throws Exception {
		Connection conexion = null;
		try {
			Properties props = new Properties();
			props.load(new FileReader("files/db.properties"));
			String driver = props.getProperty("database.driver");
			String url = props.getProperty("database.url");
			String usuario = props.getProperty("database.user");
			String pass = props.getProperty("database.password");

			Class.forName(driver);
			conexion = DriverManager.getConnection(url, usuario, pass);
			conexion.setAutoCommit(false);
			System.out.println("conexion creada");
		} catch (IOException | SQLException | ClassNotFoundException e) {
			e.printStackTrace();
			throw e;
		}
		return conexion;
	}

	public void cerrarConexion(Connection conex) {

		try {
			if (conex != null)
				conex.close();
			System.out.println("conexion cerrada");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public List<Apuesta> seleccionar(Connection conexion) {
		List<Apuesta> apuestas = new ArrayList<>();
		String sql = "select * from apuesta where tipo='LOTERIA'";
		PreparedStatement ps = null;
		ResultSet resultado = null;
		try {
			ps = conexion.prepareStatement(sql);
			resultado = ps.executeQuery();
			while (resultado.next()) {

			}
		} catch (Exception e) {
		}

		return apuestas;
	}

	// Carlos
	public void insertarApuesta(Connection conex, Apuesta apuesta) throws SQLException {

		String sql = null;
		PreparedStatement sentencia = null;
		ResultSet genKeys = null;
		try {

			if (apuesta instanceof Loteria) {
				sql = "Insert into apuesta (`fecha_apuesta`,`combinacion`,`tipo`,`precio`,`ganado`,`reintegro`,`id_sorteo`,`correo_jugador`) VALUES(?,?,?,?,?,?,?,?) ";
				sentencia = conex.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
				sentencia.setString(3, "LOTERIA");
				sentencia.setInt(6, ((Loteria) apuesta).getReintegro());
				sentencia.setInt(7, apuesta.getSorteo().getId());
				sentencia.setString(8, apuesta.getJugador().getCorreoElectronico());

			} else if (apuesta instanceof Gordo) {
				sql = "Insert into apuesta (`fecha_apuesta`,`combinacion`,`tipo`,`precio`,`ganado`,`num_clave`,`id_sorteo`,`correo_jugador`) VALUES(?,?,?,?,?,?,?,?) ";
				sentencia = conex.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
				sentencia.setString(3, "GORDO");
				sentencia.setInt(6, ((Gordo) apuesta).getnClave());
				sentencia.setInt(7, apuesta.getSorteo().getId());
				sentencia.setString(8, apuesta.getJugador().getCorreoElectronico());

			} else if (apuesta instanceof Primitva) {
				sql = "Insert into apuesta (`fecha_apuesta`,`combinacion`,`tipo`,`precio`,`ganado`,`reintegro`,`complementario`,`id_sorteo`,`correo_jugador`) VALUES(?,?,?,?,?,?,?,?,?) ";
				sentencia = conex.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
				sentencia.setString(3, "PRIMITIVA");

				sentencia.setInt(6, ((Primitva) apuesta).getReintegro());
				sentencia.setInt(7, ((Primitva) apuesta).getComplementario());
				sentencia.setInt(8, apuesta.getSorteo().getId());
				sentencia.setString(9, apuesta.getJugador().getCorreoElectronico());

			} else if (apuesta instanceof Euromillon) {
				sql = "Insert into apuesta (`fecha_apuesta`,`combinacion`,`tipo`,`precio`,`ganado`,`estrellas`,`id_sorteo`,`correo_jugador`) VALUES(?,?,?,?,?,?,?,?) ";
				sentencia = conex.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
				sentencia.setString(3, "EUROMILLON");
				sentencia.setString(6, ((Euromillon) apuesta).getEstrellas());
				sentencia.setInt(7, apuesta.getSorteo().getId());
				sentencia.setString(8, apuesta.getJugador().getCorreoElectronico());

			} else if (apuesta instanceof Quiniela) {

				sql = "Insert into apuesta (`fecha_apuesta`,`combinacion`,`tipo`,`precio`,`ganado`,`id_sorteo`,`correo_jugador`) VALUES(?,?,?,?,?,?,?,?) ";
				sentencia = conex.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
				sentencia.setString(3, "QUINIELA");

				sentencia.setInt(6, apuesta.getSorteo().getId());
				sentencia.setString(7, apuesta.getJugador().getCorreoElectronico());
			}

			sentencia.setString(1, apuesta.getFechaApuesta());
			sentencia.setString(2, apuesta.getCombinacion());
			sentencia.setDouble(4, apuesta.getPrecio());
			sentencia.setDouble(5, apuesta.getGanado());

			if (sentencia.executeUpdate() > 0) {
				genKeys = sentencia.getGeneratedKeys();

				while (genKeys.next()) {
					int id = genKeys.getInt(1);
					apuesta.setId(id);
				}
			}

			conex.commit();
			System.out.println("apuesta añadida");

		} catch (SQLException e) {
			conex.rollback();
			e.printStackTrace();
			throw e;
		}

	}

}
