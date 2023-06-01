package gambling.gambling;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.ObjectMapper;

public class App {
	/**
	 * escribir en un json una lista de apuestas
	 * 
	 * @param apuestas
	 */
	public void convertirToJson(List<Apuesta> apuestas) {
		File f = new File("apuestas.json");
		try {
			// creación del flujo de salida
			PrintWriter printWriter = new PrintWriter(new FileWriter(f));
			ObjectMapper om = new ObjectMapper();
			om.setSerializationInclusion(JsonInclude.Include.NON_NULL);
			// String json2write = om.writeValueAsString(json2write);
			// String json3write = om.writeValueAsString();
			printWriter.print("[");
			// printWriter.print(json2write + ",");
			// printWriter.print(json3write);
			printWriter.print("]");
			printWriter.flush();
			printWriter.close();
		} catch (IOException ex) {
			System.out.println("Error: " + ex.getLocalizedMessage());
		}
	}

	/**
	 *
	 * @param fichero
	 */
	public void jsonToObjeto(String fichero) {

	}

	public static void main(String[] args) throws Exception {
		GamblingHelper gambling = new GamblingHelper();
		Connection conex = gambling.crearConexion();

		Sorteo sorteo = new Sorteo(1, "2020-01-01", "2020-01-02", "2023-06-01 12:34:56", "prueb", "PRIMITIVA", null);
		Jugador jugador = new Jugador("jugador1@example.com", "1234", "123Y", "123999", 45);

		gambling.insertarApuesta(conex, new Primitva("2020-01-01", "10 20 30", 10, 5, sorteo, jugador, 7, 33));

		gambling.cerrarConexion(conex);
	}
}
