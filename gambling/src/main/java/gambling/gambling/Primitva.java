package gambling.gambling;

public class Primitva extends Apuesta {

	int complementario;
	int reintegro;

	public Primitva(int id, String fechaApuesta, String combinacion, String tipo, double precio, boolean ganado,
			Sorteo sorteo, Jugador jugador, int complementario, int reintegro) {
		super(id, fechaApuesta, combinacion, tipo, precio, ganado, sorteo, jugador);
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
		return "Primitva [complementario=" + complementario + ", reintegro=" + reintegro + "]";
	}

}
