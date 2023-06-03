package gambling.gambling;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonProcessingException;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;

/**
 * 
 * @author Carlos & Manuel
 *
 */
public class App {
	/**
	 * CONVERTIR UN GRUPO DE APUESTAS A UN JSON
	 * 
	 * @param apuestas
	 */
	public String convertirApuestasToJson(List<Apuesta> apuestas) {
		File f = new File("apuestas.json");
		ObjectMapper om = new ObjectMapper();
		ObjectNode jsonPrincipal = om.createObjectNode();
		String devuelto = null;
		try {
			// creación del flujo de salida
			PrintWriter printWriter = new PrintWriter(new FileWriter(f));
			om.setSerializationInclusion(JsonInclude.Include.NON_NULL);
			// ObjectNode jsonPrincipal = om.createObjectNode();
			ObjectNode jsonApuestas = om.createObjectNode();

			ArrayNode arrayLoteria = om.createArrayNode();
			ArrayNode arrayQuiniela = om.createArrayNode();
			ArrayNode arrayGordo = om.createArrayNode();
			ArrayNode arrayEuromillon = om.createArrayNode();
			ArrayNode arrayPrimitiva = om.createArrayNode();

			for (Apuesta apuesta : apuestas) {
				if (apuesta instanceof Loteria) {
					ObjectNode loteria = om.valueToTree(apuesta);
					arrayLoteria.add(loteria);

				} else if (apuesta instanceof Gordo) {

					ObjectNode gordo = om.valueToTree(apuesta);
					arrayGordo.add(gordo);

				} else if (apuesta instanceof Primitiva) {

					ObjectNode primi = om.valueToTree(apuesta);
					arrayPrimitiva.add(primi);

				} else if (apuesta instanceof Euromillon) {

					ObjectNode euroM = om.valueToTree(apuesta);
					arrayEuromillon.add(euroM);

				} else if (apuesta instanceof Quiniela) {

					ObjectNode quini = om.valueToTree(apuesta);
					arrayQuiniela.add(quini);
				}

				// Agregar los arrays de apuestas al objeto JSON principal

				jsonApuestas.set("Loteria", arrayLoteria);

				jsonApuestas.set("Quiniela", arrayQuiniela);

				jsonApuestas.set("Gordo", arrayGordo);

				jsonApuestas.set("Euromillon", arrayEuromillon);

				jsonApuestas.set("Primitiva", arrayPrimitiva);

				// Agregar el objeto JSON de apuestas al objeto JSON principal
				jsonPrincipal.set("Apuesta", jsonApuestas);

			}

			// printWriter.print(jsonPrincipal);
			devuelto = om.writeValueAsString(jsonPrincipal);

			printWriter.flush();
			printWriter.close();
		} catch (IOException ex) {
			System.out.println("Error: " + ex.getLocalizedMessage());
		}
		return devuelto;
	}

	/**
	 * CONVERTIR A JSON UNA LISTA DE SORTEOS , CADA SORTEO TIENE SU LISTA DE
	 * APUESTAS Y CADA APUESTA TIENE EL JUGADOR QUE LA HA REALIZADO
	 * 
	 * @param sorteos
	 */
	public void SorteoToJson(List<Sorteo> sorteos) {
		File f = new File("sorteos.json");
		ObjectMapper objectMapper = new ObjectMapper();
		try {
			String json = objectMapper.writeValueAsString(sorteos);

			for (Sorteo sorteo : sorteos) {

				List<Apuesta> apuestas = sorteo.getApuestas();
				String nombre = sorteo.getTipo();

				String jsonApuestas = convertirApuestasToJson(apuestas);

				objectMapper.writeValue(new File("apuestas" + nombre + ".json"), jsonApuestas);
				System.out.println("generado json de apuestas");

			}
			PrintWriter printWriter = new PrintWriter(new FileWriter(f));

			printWriter.print(json);

			printWriter.flush();
			System.out.println("generado json de sorteos");
			printWriter.close();

		} catch (IOException ex) {
			System.out.println("Error: " + ex.getLocalizedMessage());
		}
	}

	/**
	 * lee un json que de debe contener una lista de apuestas de cualquier tipo y
	 * devuelve la lista con esas apuestas
	 * 
	 * @param fichero
	 */
	public List<Apuesta> jsonApuestaToLista(String fichero) {

		ObjectMapper objectMapper = new ObjectMapper();
		List<Apuesta> apuestas = new ArrayList<>();
		try {
			File file = new File(fichero);
			JsonNode rootNode = objectMapper.readTree(file);
			JsonNode apuestaNode = rootNode.path("Apuesta");

			for (JsonNode loteriaNode : apuestaNode.get("Loteria")) {

				Loteria lot = objectMapper.treeToValue(loteriaNode, Loteria.class);
				apuestas.add(lot);
			}

			for (JsonNode quinielaNode : apuestaNode.get("Quiniela")) {

				Quiniela lot = objectMapper.treeToValue(quinielaNode, Quiniela.class);
				apuestas.add(lot);
			}

			for (JsonNode gordoNode : apuestaNode.get("Gordo")) {

				Gordo lot = objectMapper.treeToValue(gordoNode, Gordo.class);
				apuestas.add(lot);
			}

			for (JsonNode euromillonNode : apuestaNode.get("Euromillon")) {

				Euromillon lot = objectMapper.treeToValue(euromillonNode, Euromillon.class);
				apuestas.add(lot);
			}

			for (JsonNode primitivaNode : apuestaNode.get("Primitiva")) {

				Primitiva lot = objectMapper.treeToValue(primitivaNode, Primitiva.class);
				apuestas.add(lot);
			}

			for (Apuesta apuesta : apuestas) {

				System.out.println(apuesta.toString());
			}
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		return apuestas;
	}

	/**
	 * lee un json el cual debe contener un array de sorteos , cada sorteo tiene sus
	 * apuestas y cada apuesta su jugador
	 * 
	 * @param fichero
	 * @return
	 */
	public List<Sorteo> sorteosJsonToLista(String fichero) {

		ObjectMapper objectMapper = new ObjectMapper();
		List<Sorteo> sorteos = new ArrayList<>();
		List<Apuesta> apuestas = new ArrayList<>();
		try {
			File file = new File(fichero);
			JsonNode rootNode = objectMapper.readTree(file);

			for (JsonNode node : rootNode) {

				String fechaApertura = node.get("fechaApertura").asText();
				String fechaCierre = node.get("fechaCierre").asText();
				String fechaHoraCelebracion = node.get("fechaHoraCelebracion").asText();
				String resultado = node.get("resultado").asText();
				String tipo = node.get("tipo").asText();
				JsonNode apuestaNode = node.get("apuestas");

				if (tipo.equals("Loteria")) {
					for (JsonNode loteriaNode : apuestaNode) {

						Loteria lot = objectMapper.treeToValue(loteriaNode, Loteria.class);
						apuestas.add(lot);
					}
				}
				if (tipo.equals("Quiniela")) {
					for (JsonNode loteriaNode : apuestaNode) {

						Quiniela lot = objectMapper.treeToValue(loteriaNode, Quiniela.class);
						apuestas.add(lot);
					}
				}
				if (tipo.equals("Gordo")) {
					for (JsonNode loteriaNode : apuestaNode) {

						Gordo lot = objectMapper.treeToValue(loteriaNode, Gordo.class);
						apuestas.add(lot);
					}
				}
				if (tipo.equals("Euromillon")) {
					for (JsonNode loteriaNode : apuestaNode) {

						Euromillon lot = objectMapper.treeToValue(loteriaNode, Euromillon.class);
						apuestas.add(lot);
					}
				}
				if (tipo.equals("Primitiva")) {
					for (JsonNode loteriaNode : apuestaNode) {

						Primitiva lot = objectMapper.treeToValue(loteriaNode, Primitiva.class);
						apuestas.add(lot);
					}
				}

				Sorteo sorteo = new Sorteo(fechaApertura, fechaCierre, fechaHoraCelebracion, resultado, tipo, apuestas);
				sorteos.add(sorteo);
			}

		} catch (JsonProcessingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		return sorteos;
	}

	/**
	 * metodo main
	 * 
	 * @param args
	 * @throws Exception
	 */

	public static void main(String[] args) throws Exception {

		App app = new App();

		app.Iniciar();

	}

	/**
	 * metodo para iniciar la aplicion , la cual Inserta un sorteo con varias
	 * apuestas y cada apuesta tiene su jugador. una vez inserta estos datos en la
	 * base de datos los lee y los pasa a un Json. Posteriormente usa otro json a
	 * traves del cual inserta otro sorteo en la base de datos.
	 * 
	 * @throws Exception
	 */
	public void Iniciar() throws Exception {
		App app = new App();
		GamblingHelper gambling = new GamblingHelper();
		Connection conex = gambling.crearConexion();

		Sorteo sorteo = new Sorteo("2010-01-01", "2010-01-02", "2010-01-03 12:14:56", null, "PRIMITIVA & QUINIELA");
		Sorteo sorteo2 = new Sorteo("2020-08-01", "2020-08-05", "2020-08-10 15:34:56", "ganador : 2",
				"EUROMILLON & GORDO");

		Jugador jugador = new Jugador("jugador1@example.com", "1234", "123Y", "123999", 45);
		Jugador jugador2 = new Jugador("correo@example.com", "password", "12345678A", "123456789", 100.0);

		Primitiva prim = new Primitiva("2020-01-01", "10 20 30", 10, 5, 1, jugador, 7, 33);
		Quiniela quin = new Quiniela("2020-01-01", "10 20 30", 10, 5, 1, jugador2);
		Gordo gordo = new Gordo("2022-01-01", "123456", 10.0, 0.0, 1, jugador, 7);
		Euromillon euromillon = new Euromillon("2023-06-01", "10-15-20-25-30", 2.0, 0.0, 1, jugador2, "2-4");

		// esta loteria no se crea por que se añade desde un Json
		// Loteria loteria = new Loteria("2023-05-15", "5-10-15-20-25", 2.5, 0.0, 1,
		// jugador, 3);

		// añadir primitiva y quiniela al sorteo
		sorteo.getApuestas().add(prim);
		sorteo.getApuestas().add(quin);

		// añadir euromillon y gordo al sorteo
		sorteo2.getApuestas().add(euromillon);
		sorteo2.getApuestas().add(gordo);

		// añadir jugadores
		gambling.insertarJugador(conex, jugador);
		gambling.insertarJugador(conex, jugador2);

		// añadir sorteos y con ellos sus apuestas
		gambling.insertarSorteo(conex, sorteo);
		gambling.insertarSorteo(conex, sorteo2);

		// obtener todos los sorteos de la base de datos
		List<Sorteo> sorteos = gambling.ObtenerSorteos(conex);

		// almacenarlos en un json
		app.SorteoToJson(sorteos);

		// guardar los nuevos sorteos pasados por el json en una lista de sorteos
		List<Sorteo> sorteosNuevo = app.sorteosJsonToLista("sorteosNuevos.json");

		// insertar sorteos
		for (Sorteo sorteoN : sorteosNuevo) {
			gambling.insertarSorteo(conex, sorteoN);
		}
		System.out.println("Los nuevos sorteos se han añadido correctamente");

		gambling.cerrarConexion(conex);
	}
}
