package gambling.gambling;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

public abstract class Apuesta {
	protected int id;
	protected String fechaApuesta;
	protected String combinacion;
//	protected String tipo;
	protected double precio;
	protected double ganado;
	// se ignora el sorteo por que el sorteo ya contiene apuestas y seria redundante
	@JsonIgnore
	protected int sorteo;
	protected Jugador jugador;

	// Constructor
	public Apuesta(int id, String fechaApuesta, String combinacion, double precio, double ganado, int sorteo,
			Jugador jugador) {
		this.id = id;
		this.fechaApuesta = fechaApuesta;
		this.combinacion = combinacion;
		// this.tipo = tipo;
		this.precio = precio;
		this.ganado = ganado;
		this.sorteo = sorteo;
		this.jugador = jugador;
	}

	public Apuesta(String fechaApuesta, String combinacion, double precio, double ganado, int sorteo, Jugador jugador) {
		this.fechaApuesta = fechaApuesta;
		this.combinacion = combinacion;
		// this.tipo = tipo;
		this.precio = precio;
		this.ganado = ganado;
		this.sorteo = sorteo;
		this.jugador = jugador;
	}

	// Getters y setters
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getFechaApuesta() {
		return fechaApuesta;
	}

	public void setFechaApuesta(String fechaApuesta) {
		this.fechaApuesta = fechaApuesta;
	}

	public String getCombinacion() {
		return combinacion;
	}

	public void setCombinacion(String combinacion) {
		this.combinacion = combinacion;
	}

//	public String getTipo() {
//		return tipo;
//	}
//
//	public void setTipo(String tipo) {
//		this.tipo = tipo;
//	}

	public double getPrecio() {
		return precio;
	}

	public void setPrecio(double precio) {
		this.precio = precio;
	}

	public double getGanado() {
		return ganado;
	}

	public void setGanado(double ganado) {
		this.ganado = ganado;
	}

	public int getSorteo() {
		return sorteo;
	}

	public void setSorteo(int sorteo) {
		this.sorteo = sorteo;
	}

	public Jugador getJugador() {
		return jugador;
	}

	public void setJugador(Jugador jugador) {
		this.jugador = jugador;
	}
}
