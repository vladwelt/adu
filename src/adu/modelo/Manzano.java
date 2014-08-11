package adu.modelo;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author umss
 */
public class Manzano {

    public static boolean existe(Urbanizacion urbanizacion, int manzano_id) throws SQLException {
        boolean res = false;
        String query = "select * from manzano where urbanizacion_id=" + urbanizacion.getId()+ " and numero_manzano="+manzano_id+";";
        System.out.println(query);
        Connection connection = Conexion.getConexion().getConnection();
        ResultSet resultSet = connection.createStatement().executeQuery(query);
        if (resultSet.next()) {
            res = true;
        }
        return res;
    }

    public static int getId(Urbanizacion urbanizacion, int manzano_id) throws SQLException {
        int res = -1;
        String query = "select * from manzano where urbanizacion_id=" + urbanizacion.getId()+ " and numero_manzano="+manzano_id+";";
        System.out.println(query);
        Connection connection = Conexion.getConexion().getConnection();
        ResultSet resultSet = connection.createStatement().executeQuery(query);
        if (resultSet.next()) {
            res = resultSet.getInt("id");
        }
        return res;
    }

    public static int getNumeroManzano(int manzano_id) 
        throws SQLException{

        int res = -1;
        String query = "SELECT numero_manzano FROM manzano where id="+manzano_id +";";
        System.out.println(query);
        Connection connection = Conexion.getConexion().getConnection();
        ResultSet resultSet = connection.createStatement().executeQuery(query);
        if(resultSet.next()) {
            res = resultSet.getInt("numero_manzano");
        }
        return res;
    }
    private int id;
    private String nombre;

    public Manzano(int id, String nombre) {
        this.id = id;
        this.nombre = nombre;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

}
