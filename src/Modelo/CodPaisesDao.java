/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JComboBox;

/**
 *
 * @author alfredo
 */
public class CodPaisesDao {
    Conexion cn = new Conexion();
    Connection con;
    PreparedStatement ps;
    ResultSet rs;

    public void ConsularPais(JComboBox pais){
        String sql = "SELECT Pais FROM codPaises";
        try{
            con = cn.getConnection();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while(rs.next()){
                pais.addItem(rs.getString("Pais"));
            }
        }catch(SQLException e){
            System.out.println(e.toString());
        }
    }
    
    public String BuscarPais(String pais){
        CodigosPaises codPais = new CodigosPaises();
        String sql = "SELECT * FROM codPaises WHERE Pais = ?";
        try{
            con = cn.getConnection();
            ps = con.prepareStatement(sql);
            ps.setString(1,pais);
            rs = ps.executeQuery();
            if(rs.next()){
                codPais.setCodigo(rs.getString("Codigo"));
            }
        }catch(SQLException e){
            System.out.println(e.toString());
        }
        return codPais.getCodigo();
    }
}
