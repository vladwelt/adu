package modelo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Cliente {

    private int ci;//Carnet de identidad
    private String nombre;
    private String apellido_paterno;
    private String apellido_materno;
    private String direccion;
    private int telefono_fijo;
    private int telefono_celular;

    public Cliente(int _ci, String _nombre, String _ap, String _am,
            String _direccion, int _tf, int _tc) {
        this.ci = _ci;
        this.nombre = _nombre;
        this.apellido_paterno = _ap;
        this.apellido_materno = _am;
        this.direccion = _direccion;
        this.telefono_fijo = _tf;
        this.telefono_celular = _tc;
    }

    public Cliente(int _ci, String _nombre, String _ap, String _am,
            String _direccion, int _tc) {
        this(_ci, _nombre, _ap, _am, _direccion,0, _tc);
    }

    public Cliente(int _ci, String _nombre, String _ap,
            String _direccion, int _tc) {
        this(_ci, _nombre, _ap, "", _direccion,0, _tc);
    }
    
    public int getCi() {
        
        return this.ci;
    }

    public String getNombre() {
        return this.nombre;
    }

    public String getApellidoPaterno() {
        return this.apellido_paterno;
    }

    public String getApellidoMaterno() {
        return this.apellido_materno;
    }

    public String getDireccion() {
        return this.direccion;
    }

    public int getTelefonoFijo() {
        return this.telefono_fijo;
    }

    public int getTelefonoCelular() {
        return this.telefono_celular;
    }

    public void setCi(int _ci) {
        this.ci = _ci;
    }

    public void setNombre(String _nombre) {
        this.nombre = _nombre;
    }

    public void setApellidoPaterno(String _ap) {
        this.apellido_paterno = _ap;
    }

    public void setApellidoMaterno(String _am) {
        this.apellido_materno = _am;
    }

    public void setDireccion(String _direccion) {
        this.direccion = _direccion;
    }

    public void setTelefonoFijo(int _tf) {
        this.telefono_fijo = _tf;
    }

    public void setTelefonoCelular(int _tc) {
        this.telefono_celular = _tc;
    }

    public void save() throws SQLException {
        Conexion conexion = Conexion.getConexion();
        Connection connection = conexion.getConnection();
        String query = "insert into cliente (ci,nombre,apellido_paterno,apellido_materno,direccion,telefono_fijo,telefono_celular)values(?,?,?,?,?,?,?);";
        PreparedStatement prepareStatement = connection.prepareStatement(query);
        prepareStatement.setInt(1, ci);
        prepareStatement.setString(2, nombre);
        prepareStatement.setString(3, apellido_paterno);
        prepareStatement.setString(4, apellido_materno);
        prepareStatement.setString(5, direccion);
        prepareStatement.setInt(6, telefono_fijo);
        prepareStatement.setInt(7, telefono_celular);
        prepareStatement.execute();
//        connection.close();
    }
}
