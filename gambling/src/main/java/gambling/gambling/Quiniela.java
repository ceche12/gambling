package gambling.gambling;

public class Quiniela extends Apuesta {

	public Quiniela(int id, String fechaApuesta, String combinacion,  double precio, double ganado,
			Sorteo sorteo, Jugador jugador) {
		super(id, fechaApuesta, combinacion,  precio, ganado, sorteo, jugador);
	}

	public Quiniela(String fechaApuesta, String combinacion,  double precio, double ganado, Sorteo sorteo,
			Jugador jugador) {
		super(fechaApuesta, combinacion, precio, ganado, sorteo, jugador);
	}

}
