/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelo;
import java.util.List;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.PreparedStatement;
import java.util.ArrayList;

/**
 *
 * @author alfredo
 */
public class ProveedorDao {
    Connection con;
    Conexion cn = new Conexion();
    PreparedStatement ps;
    ResultSet rs;
    
    public boolean RegistrarProveedor(Proveedor pr){
        String sql = "INSERT INTO proveedor (rut , nombre , telefono , direccion ) VALUES (?,?,?,?)";
        try{
            con = cn.getConnection();
            ps = con.prepareStatement(sql);
            ps.setString(1,pr.getRUT());
            ps.setString(2,pr.getNombre());
            ps.setInt(3, pr.getTelefono());
            ps.setString(4,pr.getDireccion());
            ps.execute();
            return true;
        }catch(SQLException e){
            System.out.println(e.toString());
            return false;
        }finally{
            try{
                con.close();
            }catch(Exception e){
                System.out.println(e.toString());
            }
        }
    }

    public List ListarProveedor(){
        List<Proveedor> Listapr = new ArrayList();
        String sql = "SELECT * FROM proveedor";
        try{
            con = cn.getConnection();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while(rs.next()){
             Proveedor pr = new Proveedor();
             pr.setId(rs.getInt("id"));
             pr.setRUT(rs.getString("rut"));
             pr.setNombre(rs.getString("nombre"));
             pr.setTelefono(rs.getInt("telefono"));
             pr.setDireccion(rs.getString("direccion"));
             Listapr.add(pr);
            }
        }
        catch(SQLException e){
            System.out.println(e.toString());
        }
        return Listapr;
    }
    
    public boolean EliminarProveedor(int id){
        String sql = "DELETE FROM proveedor WHERE id = ?";
        try{
            con = cn.getConnection();
            ps = con.prepareStatement(sql);
            ps.setInt(1, id);
            ps.execute();
            return true;
        }catch(SQLException e){
            System.out.println(e.toString());
            return false;
        }finally{
            try{
                con.close();
            }catch(SQLException e){
                System.out.println(e.toString());
            return false;
            }
        }
    }
    
    public boolean ModificarProveedor(Proveedor pr){
        String sql = "UPDATE proveedor SET rut = ?, nombre = ?, telefono = ?, direccion=? WHERE id = ?";
        try{
            con = cn.getConnection();
            ps = con.prepareStatement(sql);
            ps.setString(1, pr.getRUT());
            ps.setString(2,pr.getNombre());
            ps.setInt(3, pr.getTelefono());
            ps.setString(4, pr.getDireccion());
            ps.setInt(5,pr.getId());
            ps.execute();
            return true;
        }catch(SQLException e){
            System.out.println(e.toString());
            return false; 
        }finally{
            try{
                con.close();
            }catch(SQLException e){
                System.out.println(e.toString());
            }
        }
    }
}
