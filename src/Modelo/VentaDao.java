/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;


public class VentaDao {
    Connection con;
    Conexion cn = new Conexion();
    PreparedStatement ps;
    ResultSet rs;
    int r;

    public int IdVenta(){
        int id = 0;
        String sql = "SELECT MAX(id) FROM ventas";
        try{
            con = cn.getConnection();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            if(rs.next()){
                id = rs.getInt(1);
            }
        }
        catch(SQLException e){
            System.out.println(e.toString());
        }
        return id;
    }
    
    public int RegistrarVenta(Venta v){
        String sql = "INSERT INTO ventas (total,fecha_venta) VALUES (?,TO_DATE(?,'YYYY-MM-DD'))";
        try{
            con = cn.getConnection();
            ps = con.prepareStatement(sql);
            ps.setDouble(1, v.getTotal());
            ps.setString(2,v.getFecha_venta());
            ps.execute();
        }catch(SQLException e){
            System.out.println(e.toString());
        }return r;
    }
    
    public ProductosVentas BuscarProducto(String Producto, int precio){
        String sql = "SELECT * FROM productos_ventas WHERE producto = ? AND precio_prod = CAST(? AS VARCHAR)";
        ProductosVentas Pv = new ProductosVentas();
        try{
            con = cn.getConnection();
            ps = con.prepareStatement(sql);
            ps.setString(1,Producto);
            ps.setInt(2, precio);
            rs = ps.executeQuery();
            if(rs.next()){
                Pv.setProductos(Producto);
                Pv.setCantidad(rs.getInt("cantidad"));
                Pv.setPrecio_producto(precio);
            }else{
                return null;
            }
        }catch(SQLException e){
            System.out.println(e.toString());
        }
        return Pv;
    }
    
    public int RegistrarProductosVenta(ProductosVentas pv){
        String sql = "INSERT INTO productos_ventas (cliente, producto, cantidad, precio_prod, fecha) VALUES (?,?,?,?,TO_DATE(?,'YYYY-MM-DD'))";
        try{
            con = cn.getConnection();
            ps = con.prepareStatement(sql);
            ps.setString(1, pv.getCliente());
            ps.setString(2, pv.getProductos());
            ps.setInt(3,pv.getCantidad());
            ps.setInt(4, pv.getPrecio_producto());
            ps.setString(5, pv.getFecha_venta());
            ps.execute();
        }catch(SQLException e){
            System.out.println(e.toString());
        }return r;
    }
    
    public boolean ActualizarProductosVentas(ProductosVentas pv){
        String sql = "UPDATE productos_ventas SET cantidad = ? WHERE producto = ? AND precio_prod = ?";
        try{
            con = cn.getConnection();
            ps = con.prepareStatement(sql);
            ps.setInt(1, pv.getCantidad());
            ps.setString(2, pv.getProductos());
            ps.setInt(3, pv.getPrecio_producto());
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
    
    public List ListarVentas(){
        List<Venta> ListaVenta = new ArrayList();
        String sql = "SELECT * FROM ventas";
        try{
            con = cn.getConnection();
            ps= con.prepareStatement(sql);
            rs = ps.executeQuery();
            while(rs.next()){
                Venta vent = new Venta();
                vent.setId(rs.getInt("id"));
                vent.setTotal(rs.getInt("total"));
                vent.setFecha_venta(rs.getString("fecha_venta"));
                ListaVenta.add(vent);
            }
        }catch(SQLException e){
            System.out.println(e.toString());
        }return ListaVenta;
    }
    
    public List ListarProductosVentas(){
        List<ProductosVentas> ListaProductosVnta = new ArrayList();
        String sql = "SELECT producto, cantidad, precio_prod FROM productos_ventas";
        try{
            con = cn.getConnection();
            ps= con.prepareStatement(sql);
            rs = ps.executeQuery();
            while(rs.next()){
                ProductosVentas pv = new ProductosVentas();
                pv.setProductos(rs.getString("producto"));
                pv.setCantidad(rs.getInt("cantidad"));
                pv.setPrecio_producto(rs.getInt("precio_prod"));
                ListaProductosVnta.add(pv);
            }
        }catch(SQLException e){
            System.out.println(e.toString());
        }return ListaProductosVnta;
    }
}
