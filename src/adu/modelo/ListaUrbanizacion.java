
package adu.modelo;

import adu.modelo.Conexion;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class ListaUrbanizacion {
    private ArrayList<Urbanizacion> urbanizaciones ;

    public ListaUrbanizacion() {
        this.urbanizaciones = new ArrayList<>();
    }
    
    private void agregar(Urbanizacion urbanizacion) {
        this.urbanizaciones.add(urbanizacion);
    }
    
    public static ListaUrbanizacion getUrbanizaciones() throws SQLException {
        ListaUrbanizacion res = new ListaUrbanizacion();
        String query = "select * from urbanizacion;";
        Connection connection = Conexion.getConexion().getConnection();
        ResultSet resultSet = connection.createStatement().executeQuery(query);
        int id; String nombre; double ancho; double largo;int cantidad_lotes; 
        String direccion; int fijo; int celular;
        Urbanizacion urbanizacion;
        while(resultSet.next()) {
            id = resultSet.getInt("id");
            nombre = resultSet.getString("nombre");
            ancho = resultSet.getDouble("ancho");
            largo = resultSet.getDouble("largo");
            cantidad_lotes = resultSet.getInt("cantidad_lotes");
            urbanizacion = new Urbanizacion(nombre, largo, ancho, cantidad_lotes);
            urbanizacion.setId(id);
            res.agregar(urbanizacion);
        }
//        connection.close();
        return res;
    }

    public int longitud() {
        return urbanizaciones.size();
    }

    public Urbanizacion getCliente(int i) {
        return urbanizaciones.get(i);
    }

    public static void main(String[] args) throws SQLException {
        System.out.println(ListaUrbanizacion.getUrbanizaciones().urbanizaciones.size());
       
    }
    
}
