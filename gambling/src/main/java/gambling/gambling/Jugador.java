package gambling.gambling;

public class Jugador {
	private String correoElectronico;
	private String contrasena;
	private String dni;
	private String telefono;
	private double dinero;

	// Constructor
	public Jugador(String correoElectronico, String contrasena, String dni, String telefono, double dinero) {
		this.correoElectronico = correoElectronico;
		this.contrasena = contrasena;
		this.dni = dni;
		this.telefono = telefono;
		this.dinero = dinero;
	}

	public Jugador() {
		// TODO Auto-generated constructor stub
	}

	// Getters y setters
	public String getCorreoElectronico() {
		return correoElectronico;
	}

	public void setCorreoElectronico(String correoElectronico) {
		this.correoElectronico = correoElectronico;
	}

	public String getContrasena() {
		return contrasena;
	}

	public void setContrasena(String contrasena) {
		this.contrasena = contrasena;
	}

	public String getDni() {
		return dni;
	}

	public void setDni(String dni) {
		this.dni = dni;
	}

	public String getTelefono() {
		return telefono;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}

	public double getDinero() {
		return dinero;
	}

	public void setDinero(double dinero) {
		this.dinero = dinero;
	}
}