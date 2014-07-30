package adu.modelo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Urbanizacion {
    private int id;
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

    public double getLargo() {
        return this.largo;
    }

    public double getAncho() {
        return this.ancho;
    }

    public int getCantidadLotes() {
        return this.cantidad_lotes;
    }

    public Lote[] getLotes() {
        return lotes;
    }

    public void setId(int id) {
        this.id = id;
    }
    
    public void save() throws SQLException {
        Conexion conexion = Conexion.getConexion();
        Connection connection = conexion.getConnection();
        String query = "insert into urbanizacion(nombre,ancho,largo,cantidad_lotes)values(?,?,?,?);";
        PreparedStatement prepareStatement = connection.prepareStatement(query);
        prepareStatement.setString(1, nombre);
        prepareStatement.setDouble(2, ancho);
        prepareStatement.setDouble(3, largo);
        prepareStatement.setInt(4, cantidad_lotes);
        prepareStatement.execute();
    }
    
}
