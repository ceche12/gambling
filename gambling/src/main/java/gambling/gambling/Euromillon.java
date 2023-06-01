package gambling.gambling;

public class Euromillon extends Apuesta {

	String estrellas;

	public Euromillon(int id, String fechaApuesta, String combinacion, String tipo, double precio, boolean ganado,
			Sorteo sorteo, Jugador jugador, String estrellas) {
		super(id, fechaApuesta, combinacion, tipo, precio, ganado, sorteo, jugador);
		this.estrellas = estrellas;
	}

	public String getEstrellas() {
		return estrellas;
	}

	public void setEstrellas(String estrellas) {
		this.estrellas = estrellas;
	}

	@Override
	public String toString() {
		return "Euromillon [estrellas=" + estrellas + "]";
	}

}
