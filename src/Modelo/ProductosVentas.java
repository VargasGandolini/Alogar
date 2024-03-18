/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelo;

public class ProductosVentas {
    private String cliente;
    private String productos;
    private int cantidad;
    private int precio_producto;
    private String fecha_venta;

    public ProductosVentas() {
    }

    public ProductosVentas(String cliente, String productos, int cantidad, int precio_producto, String fecha_venta) {
        this.cliente = cliente;
        this.productos = productos;
        this.cantidad = cantidad;
        this.precio_producto = precio_producto;
        this.fecha_venta = fecha_venta;
    }

    public String getCliente() {
        return cliente;
    }

    public void setCliente(String cliente) {
        this.cliente = cliente;
    }

    public String getProductos() {
        return productos;
    }

    public void setProductos(String productos) {
        this.productos = productos;
    }

    public int getPrecio_producto() {
        return precio_producto;
    }

    public void setPrecio_producto(int precio_producto) {
        this.precio_producto = precio_producto;
    }
    
    public String getFecha_venta() {
        return fecha_venta;
    }

    public void setFecha_venta(String fecha_venta) {
        this.fecha_venta = fecha_venta;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    
}
