/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelo;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Conexion {
    Connection con;
    
    public Connection getConnection() {
        try {
            // Cargar el driver de MySQL
            Class.forName("com.mysql.jdbc.Driver");
            
            // URL de conexión a la base de datos
            String myBd = "jdbc:mysql://localhost:3306/ALOGAR";
            
            // Establecer la conexión con la base de datos
            con = DriverManager.getConnection(myBd, "root", "");
            return con;
        } catch (ClassNotFoundException | SQLException e) {
            // Manejo de excepciones
            System.out.println(e.toString());
        }
        return null;
    }
}



