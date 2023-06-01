package gambling.gambling;

public class Apuesta {
    private int id;
    private String fechaApuesta;
    private String combinacion;
    private String tipo;
    private BigDecimal precio;
    private BigDecimal ganado;
    private Sorteo sorteo;
    private Jugador jugador;
    
    // Constructor
    public Apuesta(int id, String fechaApuesta, String combinacion, String tipo, BigDecimal precio, BigDecimal ganado, Sorteo sorteo, Jugador jugador) {
        this.id = id;
        this.fechaApuesta = fechaApuesta;
        this.combinacion = combinacion;
        this.tipo = tipo;
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
    
    public String getTipo() {
        return tipo;
    }
    
    public void setTipo(String tipo) {
        this.tipo = tipo;
    }
    
    public BigDecimal getPrecio() {
        return precio;
    }
    
    public void setPrecio(BigDecimal precio) {
        this.precio = precio;
    }
    
    public BigDecimal getGanado() {
        return ganado;
    }
    
    public void setGanado(BigDecimal ganado) {
        this.ganado = ganado;
    }
    
    public Sorteo getSorteo() {
        return sorteo;
    }
    
    public void setSorteo(Sorteo sorteo) {
        this.sorteo = sorteo;
    }
    
    public Jugador getJugador() {
        return jugador;
    }
    
    public void setJugador(Jugador jugador) {
        this.jugador = jugador;
    }
}
