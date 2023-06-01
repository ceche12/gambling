package gambling.gambling;

public class Gordo extends Apuesta {

	int nClave;

	public Gordo(int id, String fechaApuesta, String combinacion, String tipo, double precio, boolean ganado,
			Sorteo sorteo, Jugador jugador, int nClave) {
		super(id, fechaApuesta, combinacion, tipo, precio, ganado, sorteo, jugador);
		this.nClave = nClave;
	}

	public int getnClave() {
		return nClave;
	}

	public void setnClave(int nClave) {
		this.nClave = nClave;
	}

	@Override
	public String toString() {
		return "Gordo [nClave=" + nClave + "]";
	}

}
