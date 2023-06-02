package gambling.gambling;


public class Loteria extends Apuesta {

	private int reintegro;

	public Loteria(int id, String fechaApuesta, String combinacion, double precio, double ganado, int sorteo,
			Jugador jugador, int reintegro) {
		super(id, fechaApuesta, combinacion, precio, ganado, sorteo, jugador);
		this.reintegro = reintegro;
	}

	public Loteria(String fechaApuesta, String combinacion, double precio, double ganado, int sorteo, Jugador jugador,
			int reintegro) {
		super(fechaApuesta, combinacion, precio, ganado, sorteo, jugador);
		this.reintegro = reintegro;
	}

	public Loteria() {
	}

	public int getReintegro() {
		return reintegro;
	}

	public void setReintegro(int reintegro) {
		this.reintegro = reintegro;
	}

	@Override
	public String toString() {
		return "Loteria [reintegro=" + reintegro + ", id=" + id + ", fechaApuesta=" + fechaApuesta + ", combinacion="
				+ combinacion + ", precio=" + precio + ", ganado=" + ganado + ", sorteo=" + sorteo + ", jugador="
				+ jugador + "]";
	}

}
