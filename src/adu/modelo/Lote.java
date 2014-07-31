package adu.modelo;

public class Lote {

    private int id;
    private String descripcion;
    private int numero_lote;
    private double largo;
    private double ancho;
    private double precio;

    public Lote(int _id, double _largo, double _ancho,
            double _precio, int numero_lote) {
        this.id = _id;
        this.largo = _largo;
        this.ancho = _ancho;
        this.precio = _precio;
        this.numero_lote = numero_lote;
    }

    public int getId() {
        return this.id;
    }

    public double getLargo() {
        return this.id;
    }

    public double getAncho() {
        return this.id;
    }

    public double getPrecio() {
        return this.precio;
    }

    public void setId(int _id) {
        this.id = _id;
    }

    public void setLargo(double _largo) {
        this.largo = _largo;
    }

    public void setAncho(double _ancho) {
        this.ancho = _ancho;
    }

    public void setPrecio(double _precio) {
        this.precio = _precio;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public int getNumero_lote() {
        return numero_lote;
    }

    public void setNumero_lote(int numero_lote) {
        this.numero_lote = numero_lote;
    }

    public Cliente getCliente() {
        Cliente res = null;
        
        return res;
    }
    
}
