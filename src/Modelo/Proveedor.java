/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelo;


public class Proveedor {
    private int id;
    private String RUT;
    private String nombre;
    private int telefono;
    private String direccion;

    public Proveedor() {
        
    }

    public Proveedor(int id, String RUT, String nombre, int telefono, String direccion) {
        this.id = id;
        this.RUT = RUT;
        this.nombre = nombre;
        this.telefono = telefono;
        this.direccion = direccion;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setRUT(String RUT) {
        this.RUT = RUT;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setTelefono(int telefono) {
        this.telefono = telefono;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public int getId() {
        return id;
    }

    public String getRUT() {
        return RUT;
    }

    public String getNombre() {
        return nombre;
    }

    public int getTelefono() {
        return telefono;
    }

    public String getDireccion() {
        return direccion;
    }  
}
