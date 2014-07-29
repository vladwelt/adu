package adu.modelo;

class Urbanizacion {

    private String nombre;
    private double largo;
    private double ancho;
    private int cantidad_lotes;
    private Lote[] lotes;

    public Urbanizacion(String _nombre, double _largo, double _ancho,
            int _cantidad_lotes) {
        this.nombre = _nombre;
        this.largo = _largo;
        this.ancho = _ancho;
        this.cantidad_lotes = _cantidad_lotes;
        lotes = new Lote[_cantidad_lotes];
    }
    
    public String getNombre() {
        return this.nombre;
    }

    public int getLargo() {
        return this.largo;
    }

    public int getAncho() {
        return this.ancho;
    }

    public int getCantidadLotes() {
        return this.cantidad_lotes;
    }

    public Lote[] getLotes() {
        return lotes;
    }
}