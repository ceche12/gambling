package gambling.gambling;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;

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
			ObjectNode jsonPrincipal = om.createObjectNode();
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

			// String json2write = new ObjectMapper().writeValueAsString(e);
			printWriter.print(jsonPrincipal);

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
		File file = new File(fichero);

		ObjectMapper objectMapper = new ObjectMapper();
		JsonNode jsonNode;
		List<Apuesta> apuestaList = new ArrayList<>();
		try {

			JsonNode rootNode = objectMapper.readTree(file);
			ArrayNode apuestasNode = (ArrayNode) rootNode.get("Apuesta");

//			List<Apuesta> Apu = objectMapper.readValue(jsonNode.get("Apuesta").toString(),
//					new TypeReference<List<Apuesta>>() {
//					});

			// Deserializar cada tipo de apuesta por separado
//			List<Loteria> loteriaList = objectMapper.readValue(jsonNode.get("Loteria").toString(),
//					new TypeReference<List<Loteria>>() {
//					});
//			List<Quiniela> quinielaList = objectMapper.readValue(jsonNode.get("Quiniela").toString(),
//					new TypeReference<List<Quiniela>>() {
//					});
//			List<Gordo> gordoList = objectMapper.readValue(jsonNode.get("Gordo").toString(),
//					new TypeReference<List<Gordo>>() {
//					});
//			List<Euromillon> euromillonList = objectMapper.readValue(jsonNode.get("Euromillon").toString(),
//					new TypeReference<List<Euromillon>>() {
//					});
//			List<Primitiva> primitivaList = objectMapper.readValue(jsonNode.get("Primitiva").toString(),
//					new TypeReference<List<Primitiva>>() {
//					});
			// Combinar las listas de apuestas en una lista general

//			apuestaList.addAll(loteriaList);
//			apuestaList.addAll(quinielaList);
//			apuestaList.addAll(gordoList);
//			apuestaList.addAll(euromillonList);
//			apuestaList.addAll(primitivaList);

			// Imprimir la lista de apuestas
			for (Apuesta apuesta : apuestaList) {
				System.out.println(apuesta);
			}
		} catch (JsonMappingException e) {

			e.printStackTrace();
		} catch (JsonProcessingException e) {

			e.printStackTrace();
		} catch (IOException e) {

			e.printStackTrace();
		}

		return apuestaList;
	}

	public List<Apuesta> jsonToListaPurebas(String fichero) {
		File file = new File(fichero);
		List<Apuesta> apuestas = new ArrayList<>();

		ObjectMapper objectMapper = new ObjectMapper();
		File jsonFile = new File(fichero);

//		try {
//			JsonNode rootNode = objectMapper.readTree(jsonFile);
//
//			for (JsonNode node : rootNode) {
//				System.out.println(node);
//				Loteria loteriaList = objectMapper.treeToValue(node, Loteria.class);
//				System.out.println(loteriaList);
//			}
//
//			// Imprimir el contenido del array
//			for (Apuesta apu : apuestas) {
//				System.out.println(apu);
//			}
//		} catch (IOException e) {
//			e.printStackTrace();
//		}

		try {
			JsonNode rootNode = objectMapper.readTree(file);
			List<Loteria> loterias = new ArrayList<>();

			JsonNode loteriaNode = rootNode.get(0).get("Loteria");
			if (loteriaNode != null && loteriaNode.isArray()) {
				for (JsonNode node : loteriaNode) {
					System.out.println(node);
					Loteria loteria =  objectMapper.readValue(node.toString(), Loteria.class);
					System.out.println(loteria);
					System.out.println(loterias);
				}
			}

			// La lista de objetos Loteria está lista para su uso
			for (Loteria loteria : loterias) {
				System.out.println(loteria.getId());
				System.out.println(loteria.getFechaApuesta());
				// Imprimir otros atributos de la Loteria si es necesario
			}

		} catch (IOException e) {
			e.printStackTrace();
		}

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

//		Sorteo sorteo = new Sorteo(1, "2020-01-01", "2020-01-02", "2023-06-01 12:34:56", "prueb", "PRIMITIVA", null);
//		Jugador jugador = new Jugador("jugador1@example.com", "1234", "123Y", "123999", 45);
//		Primitiva prim = new Primitiva("2020-01-01", "10 20 30", 10, 5, 1, jugador, 7, 33);
//		Quiniela quin = new Quiniela("2020-01-01", "10 20 30", 10, 5, 1, jugador);
//		Gordo gordo = new Gordo("2022-01-01", "123456", 10.0, 0.0, 1, jugador, 7);
//		Euromillon euromillon = new Euromillon("2023-06-01", "10-15-20-25-30", 2.0, 0.0, 1, jugador, "2-4");
//		Loteria loteria = new Loteria("2023-05-15", "5-10-15-20-25", 2.5, 0.0, 1, jugador, 3);
//
//		gambling.insertarApuesta(conex, prim);
//		gambling.insertarApuesta(conex, quin);
//		gambling.insertarApuesta(conex, gordo);
//		gambling.insertarApuesta(conex, euromillon);
//		gambling.insertarApuesta(conex, loteria);
		List<Apuesta> apuestas = gambling.seleccionarApuestas(conex);
		// System.out.println(apuestas);

		gambling.cerrarConexion(conex);
//		 app.convertirApuestasToJson(apuestas);

		apuestas = app.jsonToListaPurebas("apuestas.json");
		System.out.println(apuestas);
	}
}
