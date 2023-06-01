package gambling.gambling;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
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
	
	
	
	
	
	
	
	
	
	

}
