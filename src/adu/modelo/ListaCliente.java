
package adu.modelo;

import adu.modelo.Conexion;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class ListaCliente {
    private ArrayList<Cliente> clientes ;

    public ListaCliente() {
        this.clientes = new ArrayList<>();
    }
    
    private void agregar(Cliente cliente) {
        this.clientes.add(cliente);
    }
    
    public static ListaCliente getClientes() throws SQLException {
        ListaCliente res = new ListaCliente();
        String query = "select * from cliente";
        Connection connection = Conexion.getConexion().getConnection();
        ResultSet resultSet = connection.createStatement().executeQuery(query);
        int ci; String nombre; String paterno; String materno; 
        String direccion; int fijo; int celular;
        Cliente cliente;
        while(resultSet.next()) {
            ci = resultSet.getInt("id");
            nombre = resultSet.getString("nombre");
            paterno = resultSet.getString("apellido_paterno");
            materno = resultSet.getString("apellido_materno");
            direccion = resultSet.getString("direccion");
            fijo = resultSet.getInt("telefono_fijo");
            celular = resultSet.getInt("celular");
            cliente = new Cliente(ci, nombre, paterno, materno, direccion, fijo, celular);
            res.agregar(cliente);
        }
        System.out.println(res.longitud());
//        connection.close();
        return res;
    }

    public int longitud() {
        return clientes.size();
    }

    public Cliente getCliente(int i) {
        return clientes.get(i);
    }

    
    
}
