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
    public Connection getConnection(){
        try{
            try {
                Class.forName("com.mysql.jdbc.Driver");
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(Conexion.class.getName()).log(Level.SEVERE, null, ex);
            }
            String myBd = "jdbc:postgresql://aws-0-us-west-1.pooler.supabase.com:5432/postgres?user=postgres.rxwtksozllsfigkhqxji&password=ALOGAR15032024";
            con = DriverManager.getConnection(myBd,"postgres.rxwtksozllsfigkhqxji","ALOGAR15032024");
            return con;
        }catch (SQLException e){
            System.out.println(e.toString());
        }
        return null;
    }
}



