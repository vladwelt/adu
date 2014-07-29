/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

import java.sql.*;

public class Conexion {
    private Connection conn;
    private String driver = "com.mysql.jdbc.Driver";
    private String connectString = "jdbc:mysql://localhost:3306/adu";
    private String user = "root";
    private String password = "root";

    public Conexion() {
    }

    public boolean conectar() {
        boolean estado;
        try {
            Class.forName(driver);
            conn = DriverManager.getConnection(connectString, user, password);
            System.out.println("conexion establecida");
            estado = true;
        } catch (ClassNotFoundException | SQLException e) {
            System.out.println("fallo la conexxion");
            System.exit(0);
            estado = false;
        }
        return estado;
    }

    public Connection getConexion() {
        return conn;
    }

    public void cerrar() {
        try {
            conn.close();
            System.out.println("conexion cerrada con exito");
        } catch (Exception e) {
            System.out.println("no existe ninguna conexion abierta");
        }
    }
    
}
