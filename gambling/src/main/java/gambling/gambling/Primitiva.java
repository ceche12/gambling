package gambling.gambling;

public class Primitiva extends Apuesta {

	int complementario;
	int reintegro;

	public Primitiva(int id, String fechaApuesta, String combinacion, double precio, double ganado, int sorteo,
			Jugador jugador, int complementario, int reintegro) {
		super(id, fechaApuesta, combinacion, precio, ganado, sorteo, jugador);
		this.complementario = complementario;
		this.reintegro = reintegro;
	}

	public Primitiva(String fechaApuesta, String combinacion, double precio, double ganado, int sorteo, Jugador jugador,
			int complementario, int reintegro) {
		super(fechaApuesta, combinacion, precio, ganado, sorteo, jugador);
		this.complementario = complementario;
		this.reintegro = reintegro;
	}

	public int getComplementario() {
		return complementario;
	}

	public void setComplementario(int complementario) {
		this.complementario = complementario;
	}

	public int getReintegro() {
		return reintegro;
	}

	public void setReintegro(int reintegro) {
		this.reintegro = reintegro;
	}

	@Override
	public String toString() {
		return "Primitiva [complementario=" + complementario + ", reintegro=" + reintegro + ", id=" + id
				+ ", fechaApuesta=" + fechaApuesta + ", combinacion=" + combinacion + ", precio=" + precio + ", ganado="
				+ ganado + ", sorteo=" + sorteo + ", jugador=" + jugador + "]";
	}

}
