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

	public void convertirJson(List<Apuesta> apuestas) {

		File f = new File("apuestas.json");
		try {
			// creación del flujo de salida
			PrintWriter printWriter = new PrintWriter(new FileWriter(f));
			ObjectMapper om = new ObjectMapper();
			om.setSerializationInclusion(JsonInclude.Include.NON_NULL);
			String json2write = om.writeValueAsString(json2write);
			String json3write = om.writeValueAsString();
			printWriter.print("[");
			printWriter.print(json2write + ",");
			printWriter.print(json3write);
			printWriter.print("]");
			printWriter.flush();
			printWriter.close();
		} catch (IOException ex) {
			System.out.println("Error: " + ex.getLocalizedMessage());
		}
	}

	public static void main(String[] args) throws Exception {
		GamblingHelper bbdd = new GamblingHelper();
		Connection conex = bbdd.crearConexion();
		bbdd.cerrarConexion(conex);

	}
}
