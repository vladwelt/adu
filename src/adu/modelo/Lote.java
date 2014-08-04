package adu.modelo;

import java.sql.Connection;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.PreparedStatement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Lote {

    public static ArrayList<Pago> getPagos(Urbanizacion urbanizacion, int ci, int nroLote) {
        ArrayList<Lote> lotes = urbanizacion.getLotes();
        Lote buscado = null;
        for (Lote lote : lotes) {
            if (lote.getNumero_lote() == nroLote) {
                buscado = lote;
            }
        }
        if (buscado == null) {
            return null;
        }
        return buscado.getPagos();
    }

    private int id;
    private int manzano_id;
    private int numero_lote;
    private String descripcion;
    private double largo;
    private double ancho;
    private double precio;

    public Lote(int _manzano_id, double _largo, double _ancho,
            double _precio, int _numero_lote, String _descripcion) {
        this.manzano_id = _manzano_id;
        this.largo = _largo;
        this.ancho = _ancho;
        this.precio = _precio;
        this.numero_lote = _numero_lote;
        this.descripcion = _descripcion;
    }

    public Lote(int _id, int _manzano_id, double _largo, double _ancho,
            double _precio, int _numero_lote, String _descripcion) {
        this.id = _id;
        this.manzano_id = _manzano_id;
        this.largo = _largo;
        this.ancho = _ancho;
        this.precio = _precio;
        this.numero_lote = _numero_lote;
        this.descripcion = _descripcion;
    }

    public int getId() {
        return this.id;
//        int myid = -1;
//        try {
//            String query = "select id from lote where numero_lote = " + this.numero_lote + ";";
//            Connection connection = Conexion.getConexion().getConnection();
//            ResultSet resultSet = connection.createStatement().executeQuery(query);
//            if (resultSet.next()) {
//                myid = resultSet.getInt("id");
//            }
//
//        } catch (SQLException ex) {
//            Logger.getLogger(Urbanizacion.class.getName()).log(Level.SEVERE, null, ex);
//        }
//        return myid;
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
            String query = "select *  from venta,cliente where cliente_id=cliente.id and lote_id=" + this.id + ";";
            System.out.println(query);
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
                ci = resultSet.getInt("cliente.id");
                nombre = resultSet.getString("cliente.nombre");
                paterno = resultSet.getString("cliente.apellido_paterno");
                materno = resultSet.getString("cliente.apellido_materno");
                direccion = resultSet.getString("cliente.direccion");
                fijo = resultSet.getInt("cliente.telefono_fijo");
                celular = resultSet.getInt("cliente.celular");
                cliente = new Cliente(ci, nombre, paterno, materno, direccion, fijo, celular);
            }
//        connection.close();
        } catch (SQLException ex) {
            Logger.getLogger(Lote.class.getName()).log(Level.SEVERE, null, ex);
        }
        return cliente;
    }

    public double getSumaPagos() {
        ArrayList<Pago> pagos = getPagos();
        double res = 0;
        for (Pago pago : pagos) {
            res = res + pago.getMonto();
        }
        return res;
    }

    private ArrayList<Pago> getPagos() {
        ArrayList<Pago> pagos = new ArrayList<>();
        Pago pago = null;
        try {
            String query = "select * from pago,venta where venta_id=venta.id and lote_id='" + this.id + "';";
            System.out.println(query);
            Connection connection = Conexion.getConexion().getConnection();
            ResultSet resultSet = connection.createStatement().executeQuery(query);
            int id;
            int venta_id;
            Date fecha_pago;
            double monto;
            while (resultSet.next()) {
                id = resultSet.getInt("pago.id");
                venta_id = resultSet.getInt("pago.venta_id");
                fecha_pago = resultSet.getDate("fecha_pago");
                monto = resultSet.getDouble("monto");
                pago = new Pago(id, venta_id, fecha_pago, monto);
                pagos.add(pago);
            }
//        connection.close();
        } catch (SQLException ex) {
            Logger.getLogger(Lote.class.getName()).log(Level.SEVERE, null, ex);
        }
        return pagos;
    }

    public void save() throws SQLException {
        Conexion conexion = Conexion.getConexion();
        Connection connection = conexion.getConnection();

        String query = "insert into lote(manzano_id,numero_lote,descripcion,ancho,largo,precio) values(?,?,?,?,?,?);";

        PreparedStatement prepareStatement = connection.prepareStatement(query);
        prepareStatement.setInt(1, manzano_id);
        prepareStatement.setInt(2, numero_lote);
        prepareStatement.setString(3, descripcion);
        prepareStatement.setDouble(4, ancho);
        prepareStatement.setDouble(5, largo);
        prepareStatement.setDouble(6, precio);
        prepareStatement.execute();

        this.id = getId();
    }

    public void pagarCuota(double monto, Date fecha_pago) throws SQLException {
        Conexion conexion = Conexion.getConexion();
        Connection connection = conexion.getConnection();
        String query = "insert into pago(venta_id,fecha_pago,monto) values(?,?,?);";
        PreparedStatement prepareStatement = connection.prepareStatement(query);
        int id_venta = getIdVenta();
        prepareStatement.setInt(1, id_venta);
        prepareStatement.setDate(2, fecha_pago);
        prepareStatement.setDouble(3, monto);
        prepareStatement.execute();
    }

    private int getIdVenta() {
        int myid = -1;
        try {
            String query = "select * from venta where lote_id=" + this.id + ";";
            System.out.println(query);
            Connection connection = Conexion.getConexion().getConnection();
            ResultSet resultSet = connection.createStatement().executeQuery(query);
            if (resultSet.next()) {
                myid = resultSet.getInt("id");
            }

        } catch (SQLException ex) {
            Logger.getLogger(Urbanizacion.class.getName()).log(Level.SEVERE, null, ex);
        }
        return myid;
    }

    public boolean vender(int cliente_id, Date date,
            int cantidad_cuotas) throws SQLException {
        try {
            Conexion conexion = Conexion.getConexion();
            Connection connection = conexion.getConnection();

            String query = "insert into venta(lote_id,cliente_id,cantidad_cuotas,fecha_venta) values(?,?,?,?);";
            PreparedStatement prep = connection.prepareStatement(query);

            prep.setInt(1, this.getId());
            prep.setInt(2, cliente_id);
            prep.setInt(3, cantidad_cuotas);
            prep.setDate(4, date);
            System.out.println(prep);
            prep.execute();

        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }

        return true;
    }

    public double getDeuda() {
        return getPrecio() - getSumaPagos();
    }

}
