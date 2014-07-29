/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

import java.sql.*;

public class Conexion {
    private static Conexion conexion;
    
    private Connection conn;
    private String driver = "com.mysql.jdbc.Driver";
    private String connectString = "jdbc:mysql://localhost:3306/adu";
    private String user = "root";
    private String password = "root";
 
    private Conexion() {
        try {
            Class.forName(driver);
            conn = DriverManager.getConnection(connectString, user, password);
            System.out.println("conexion establecida");
        } catch (ClassNotFoundException | SQLException e) {
            System.out.println("fallo la conexxion");
            System.exit(0);
        }
    }

    public static Conexion getConexion() {
        if (conexion == null)
           conexion = new Conexion();
        return conexion;
    }

    public void cerrar() {
        try {
            conn.close();
            System.out.println("conexion cerrada con exito");
        } catch (Exception e) {
            System.out.println("no existe ninguna conexion abierta");
        }
    }
    
    public static void main(String[] args) {
        System.out.println(conexion.getConexion());
        System.out.println(conexion.getConexion());
        System.out.println(conexion.getConexion());
    }
    
}
