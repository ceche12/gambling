package gambling.gambling;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

public class App {
	/**
	 * escribir en un json una lista de apuestas
	 * 
	 * @param apuestas
	 */
	// deberia convertir un sorteo
	public void convertirApuestasToJson(List<Apuesta> apuestas) {
		File f = new File("apuestas.json");
		try {
			// creación del flujo de salida
			PrintWriter printWriter = new PrintWriter(new FileWriter(f));
			ObjectMapper om = new ObjectMapper();
			om.setSerializationInclusion(JsonInclude.Include.NON_NULL);
			String json2write = om.writeValueAsString(apuestas);
			printWriter.print(json2write);
			printWriter.flush();
			printWriter.close();
		} catch (IOException ex) {
			System.out.println("Error: " + ex.getLocalizedMessage());
		}
	}

	public void convertirSorteoToJson(Sorteo sorteo) {
		File f = new File("apuestas.json");
		try {
			// creación del flujo de salida
			PrintWriter printWriter = new PrintWriter(new FileWriter(f));
			ObjectMapper om = new ObjectMapper();
			om.setSerializationInclusion(JsonInclude.Include.NON_NULL);
			String json2write = om.writeValueAsString(sorteo);
			printWriter.print(json2write);
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
	public List<Apuesta> jsonToLista(String fichero) {

		List<Apuesta> apuestas = new ArrayList();
		try {
			apuestas = (List<Apuesta>) new ObjectMapper().readValue(fichero, new TypeReference<List<Apuesta>>() {
			});

		} catch (IOException ex) {
			System.out.println("Error: " + ex.getLocalizedMessage());
		}
//		if (apuestas != null) {
//			System.out.println(apuestas);
//		}

		return apuestas;
	}

	public void InsertarLista(Connection conex, List<Apuesta> apuestas) throws SQLException {
		GamblingHelper gambling = new GamblingHelper();
		for (Apuesta apuesta : apuestas) {
			gambling.insertarApuesta(conex, apuesta);
		}
	}

	public static void main(String[] args) throws Exception {
		GamblingHelper gambling = new GamblingHelper();
		App app = new App();
		Connection conex = gambling.crearConexion();

		Sorteo sorteo = new Sorteo(1, "2020-01-01", "2020-01-02", "2023-06-01 12:34:56", "prueb", "PRIMITIVA", null);
		Jugador jugador = new Jugador("jugador1@example.com", "1234", "123Y", "123999", 45);
		Primitiva prim = new Primitiva("2020-01-01", "10 20 30", 10, 5, 1, jugador, 7, 33);
		Quiniela quin = new Quiniela("2020-01-01", "10 20 30", 10, 5, 1, jugador);
		Gordo gordo = new Gordo("2022-01-01", "123456", 10.0, 0.0, 1, jugador, 7);
		Euromillon euromillon = new Euromillon("2023-06-01", "10-15-20-25-30", 2.0, 0.0, 1, jugador, "2-4");
		Loteria loteria = new Loteria("2023-05-15", "5-10-15-20-25", 2.5, 0.0, 1, jugador, 3);

		gambling.insertarApuesta(conex, prim);
		gambling.insertarApuesta(conex, quin);
		gambling.insertarApuesta(conex, gordo);
		gambling.insertarApuesta(conex, euromillon);
		gambling.insertarApuesta(conex, loteria);
		List<Apuesta> apuestas = gambling.seleccionarApuestas(conex);
		System.out.println(apuestas);
		apuestas.add(prim);
		app.convertirApuestasToJson(apuestas);
		gambling.cerrarConexion(conex);
		apuestas = app.jsonToLista("apuestas.json");
		System.out.println(apuestas);
	}
}
