package adu.modelo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Urbanizacion {

    private int id;
    private String nombre;
    private double largo;
    private double ancho;
    private int cantidad_lotes;
    private ArrayList<Lote> lotes;

    public Urbanizacion(String _nombre, double _largo, double _ancho,
            int _cantidad_lotes) {
        this.nombre = _nombre;
        this.largo = _largo;
        this.ancho = _ancho;
        this.cantidad_lotes = _cantidad_lotes;
        lotes = new ArrayList(cantidad_lotes);
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

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return this.nombre; //To change body of generated methods, choose Tools | Templates.
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

    public ArrayList<Lote> getLotes() {
        ArrayList<Lote> res = new ArrayList<>();
        try {
            String query = "select * from lote where urbanizacion_id=" + this.id + ";";
            System.out.println(query);
            Connection connection = Conexion.getConexion().getConnection();
            ResultSet resultSet = connection.createStatement().executeQuery(query);
            int id;
            int numero_lote;
            String descripcion;
            double ancho;
            double largo;
            double precio;
            Lote lote;
            while (resultSet.next()) {
                id = resultSet.getInt("id");
                numero_lote = resultSet.getInt("numero_lote");
                descripcion = resultSet.getString("descripcion");
                ancho = resultSet.getDouble("ancho");
                largo = resultSet.getDouble("largo");
                precio = resultSet.getDouble("precio");
                lote = new Lote(id, largo, ancho, precio, numero_lote);
                lote.setDescripcion(descripcion);
                res.add(lote);
            }
//        connection.close();
        } catch (SQLException ex) {
        }
        System.out.println("cantidad de lotes : " + res.size());
        return res;
    }

}
