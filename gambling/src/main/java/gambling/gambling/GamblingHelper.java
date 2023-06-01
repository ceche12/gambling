package gambling.gambling;

import java.io.FileReader;
import java.io.FileWriter;
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
			// TODO: handle exception
		}

		return apuestas;
	}

	// Carlos
	public void insertarApuesta(Connection conex, Apuesta apuesta) {

		if (apuesta instanceof Loteria) {
			String sql = "Insert into apuesta (`id`,`fecha_apuesta`,`combinacion`,`tipo`,`precio`,"
					+ "`ganado`,`reintegro`,`estrellas`,`num_clave`,`complementario`,`id_sorteo`,`correo_jugador`) VALUES(?,?,?,?,?,?,) ";
		} else if (apuesta instanceof Gordo) {
			String sql = "Insert into apuesta (`id`,`fecha_apuesta`,`combinacion`,`tipo`,`precio`,"
					+ "`ganado`,`reintegro`,`estrellas`,`num_clave`,`complementario`,`id_sorteo`,`correo_jugador`) VALUES(?,?,?,?,?,?,) ";
		} else if (apuesta instanceof Primitva) {
			String sql = "Insert into apuesta (`id`,`fecha_apuesta`,`combinacion`,`tipo`,`precio`,"
					+ "`ganado`,`reintegro`,`estrellas`,`num_clave`,`complementario`,`id_sorteo`,`correo_jugador`) VALUES(?,?,?,?,?,?,) ";
		} else if (apuesta instanceof Sorteo) {
			String sql = "Insert into apuesta (`id`,`fecha_apuesta`,`combinacion`,`tipo`,`precio`,"
					+ "`ganado`,`reintegro`,`estrellas`,`num_clave`,`complementario`,`id_sorteo`,`correo_jugador`) VALUES(?,?,?,?,?,?,) ";
		} else if (apuesta instanceof Quiniela) {
			String sql = "Insert into apuesta (`id`,`fecha_apuesta`,`combinacion`,`tipo`,`precio`,"
					+ "`ganado`,`reintegro`,`estrellas`,`num_clave`,`complementario`,`id_sorteo`,`correo_jugador`) VALUES(?,?,?,?,?,?,) ";
		}

	}

}
