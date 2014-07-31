package adu.modelo;

import java.sql.Connection;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Lote {

    public static ArrayList<Pago> getPagos(int ci, int lote) {
        ArrayList<Pago> res = new ArrayList<Pago>();
        try {
            String query = "select * from pago, venta where venta_id=venta.id and cliente_id=" + ci + " and lote_id=" + lote + " order by fecha_pago asc;";
            Connection connection = Conexion.getConexion().getConnection();
            ResultSet resultSet = connection.createStatement().executeQuery(query);
            int id;
            int venta_id;
            Date fecha_pago;
            double monto;
            Pago pago;
            while (resultSet.next()) {
                id = resultSet.getInt("pago.id");
                venta_id = resultSet.getInt("venta_id");
                fecha_pago = resultSet.getDate("fecha_pago");
                monto = resultSet.getDouble("monto");
                pago = new Pago(id, venta_id, fecha_pago, monto);
                res.add(pago);
            }
//        connection.close();
        } catch (SQLException ex) {
            Logger.getLogger(Lote.class.getName()).log(Level.SEVERE, null, ex);
        }
        return res;
    }

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

    //select *  from venta,cliente where cliente_id=ci and lote_id=4;
    public Cliente getCliente() {
        Cliente cliente = null;
        try {
            String query = "select *  from venta,cliente where cliente_id=ci and lote_id=" + id + ";";
            Connection connection = Conexion.getConexion().getConnection();
            ResultSet resultSet = connection.createStatement().executeQuery(query);
            int ci;
            String nombre;
            String paterno;
            String materno;
            String direccion;
            int fijo;
            int celular;
            if (resultSet.next()) {
                ci = resultSet.getInt("ci");
                nombre = resultSet.getString("nombre");
                paterno = resultSet.getString("apellido_paterno");
                materno = resultSet.getString("apellido_materno");
                direccion = resultSet.getString("direccion");
                fijo = resultSet.getInt("telefono_fijo");
                celular = resultSet.getInt("telefono_celular");
                cliente = new Cliente(ci, nombre, paterno, materno, direccion, fijo, celular);
            }
//        connection.close();
        } catch (SQLException ex) {
            Logger.getLogger(Lote.class.getName()).log(Level.SEVERE, null, ex);
        }
        return cliente;
    }

}
