package gambling.gambling;

import java.util.List;

public class Sorteo {
    private int id;
    private String fechaApertura;
    private String fechaCierre;
    private String fechaHoraCelebracion;
    private String resultado;
    private String tipo;
    private List<Apuesta> apuestas;
    
    // Constructor
    public Sorteo(int id, String fechaApertura, String fechaCierre, String fechaHoraCelebracion, String resultado, String tipo, List<Apuesta> apuestas) {
        this.id = id;
        this.fechaApertura = fechaApertura;
        this.fechaCierre = fechaCierre;
        this.fechaHoraCelebracion = fechaHoraCelebracion;
        this.resultado = resultado;
        this.tipo = tipo;
        this.apuestas = apuestas;
    }
    
    // Getters y setters
    public int getId() {
        return id;
    }
    
    public void setId(int id) {
        this.id = id;
    }
    
    public String getFechaApertura() {
        return fechaApertura;
    }
    
    public void setFechaApertura(String fechaApertura) {
        this.fechaApertura = fechaApertura;
    }
    
    public String getFechaCierre() {
        return fechaCierre;
    }
    
    public void setFechaCierre(String fechaCierre) {
        this.fechaCierre = fechaCierre;
    }
    
    public String getFechaHoraCelebracion() {
        return fechaHoraCelebracion;
    }
    
    public void setFechaHoraCelebracion(String fechaHoraCelebracion) {
        this.fechaHoraCelebracion = fechaHoraCelebracion;
    }
    
    public String getResultado() {
        return resultado;
    }
    
    public void setResultado(String resultado) {
        this.resultado = resultado;
    }
    
    public String getTipo() {
        return tipo;
    }
    
    public void setTipo(String tipo) {
        this.tipo = tipo;
    }
    
    public List<Apuesta> getApuestas() {
        return apuestas;
    }
    
    public void setApuestas(List<Apuesta> apuestas) {
        this.apuestas = apuestas;
    }
}