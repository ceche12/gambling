package gambling.gambling;

public class Jugador {
    private String correoElectronico;
    private String contrasena;
    private String dni;
    private String telefono;
    private BigDecimal dinero;
    
    // Constructor
    public Jugador(String correoElectronico, String contrasena, String dni, String telefono, BigDecimal dinero) {
        this.correoElectronico = correoElectronico;
        this.contrasena = contrasena;
        this.dni = dni;
        this.telefono = telefono;
        this.dinero = dinero;
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
    
    public BigDecimal getDinero() {
        return dinero;
    }
    
    public void setDinero(BigDecimal dinero) {
        this.dinero = dinero;
    }
}