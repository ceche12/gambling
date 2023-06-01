package gambling.gambling;

public class Loteria extends Apuesta {

	private int reintegro;

	public Loteria(int id, String fechaApuesta, String combinacion,  double precio, double ganado,
			Sorteo sorteo, Jugador jugador, int reintegro) {
		super(id, fechaApuesta, combinacion,  precio, ganado, sorteo, jugador);
		this.reintegro = reintegro;
	}

	public Loteria(String fechaApuesta, String combinacion,  double precio, double ganado, Sorteo sorteo,
			Jugador jugador, int reintegro) {
		super(fechaApuesta, combinacion, precio, ganado, sorteo, jugador);
		this.reintegro = reintegro;
	}

	public int getReintegro() {
		return reintegro;
	}

	public void setReintegro(int reintegro) {
		this.reintegro = reintegro;
	}

	@Override
	public String toString() {
		return "Loteria [reintegro=" + reintegro + "]";
	}

}
