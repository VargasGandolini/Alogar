/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package Vista;

import Modelo.Cliente;
import Modelo.ClienteDao;
import Modelo.Conexion;
import Modelo.Config;
import Modelo.Eventos;
import Modelo.ProductosDao;
import Modelo.ProductosVentas;
import Modelo.Venta;
import Modelo.VentaDao;
import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.Image;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.event.DocumentListener;
import javax.swing.table.DefaultTableModel;
import org.w3c.dom.events.DocumentEvent;

/**
 *
 * @author alfredo
 */
public class NuevaVenta extends javax.swing.JFrame {
    int item;
    DefaultTableModel modelo = new DefaultTableModel();
    DefaultTableModel tmp = new DefaultTableModel();
    ClienteDao client = new ClienteDao();
    Date fechaVenta = new Date();
    String fechaActual = new SimpleDateFormat("yyyy-MM-dd").format(fechaVenta);
    double TotalPagar = 0;
    Venta v = new Venta();
    VentaDao VDao = new VentaDao();
    ProductosVentas pv = new ProductosVentas();
    ProductosDao proDao = new ProductosDao();
    Eventos event = new Eventos();
    Config conf = new Config();
    

    /**
     * Creates new form NuevaVenta
     */
    public NuevaVenta() {
        initComponents();
        this.setLocationRelativeTo(null);
        txtClienteVenta.setVisible(false);
        txtTelefonoClienteVenta.setVisible(false);
        txtDireccionClienteVenta.setVisible(false);
        txtIdVenta.setVisible(false);
        txtCodigoVenta.requestFocus();
        txtCodigoVenta.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
                String codigo = txtCodigoVenta.getText();
                if (!codigo.isEmpty()) {
                    IngresarProductoVentaCodigo(codigo);
                }
            }
        });
        txtCantidadVenta.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
                if (!"".equals(txtCantidadVenta.getText())) {
                    String codigo = txtCodigoVenta.getText();
                    String descripcion = txtDescripcionVenta.getText();
                    int cant = Integer.parseInt(txtCantidadVenta.getText());
                    double precio = Double.parseDouble(txtPrecioVenta.getText());
                    String precioFormateado = String.format("%.0f", precio);
                    double total = cant * precio;
                    item = item + 1;
                    DefaultTableModel tmp = (DefaultTableModel) TableVenta.getModel();
                    for (int i = 0; i < TableVenta.getRowCount(); i++) {
                        if(descripcion.equals(TableVenta.getValueAt(i, 1))){
                            tmp.removeRow(i);
                        }
                    }
                    ArrayList lista = new ArrayList();
                    lista.add(item);
                    lista.add(codigo);
                    lista.add(descripcion);
                    lista.add(cant);
                    lista.add(precioFormateado);
                    lista.add(total);
                    Object[] O = new Object[5];
                    O[0] = lista.get(1);
                    O[1] = lista.get(2);
                    O[2] = lista.get(3);
                    O[3] = lista.get(4);
                    O[4] = lista.get(5);
                    tmp.addRow(O);
                    TableVenta.setModel(tmp);
                    TotalPagar();
                    LimpiarVenta();
                    txtCodigoVenta.requestFocus();
                }
            }
        });
    }
    
    public String IngresarProductoVentaCodigo(String scannedBarcode) {
        Connection con;
        Conexion cn = new Conexion();
        PreparedStatement ps;
        ResultSet rs;
        String sql = "SELECT * FROM productos WHERE codigobarra = ?";

        try {
            con = cn.getConnection();
            ps = con.prepareStatement(sql);
            ps.setString(1, scannedBarcode);
            rs = ps.executeQuery();

            if (rs.next()) {
                String codigoProducto = rs.getString("codigobarra");
                String descripcion = rs.getString("nombre");
                String precio = (rs.getString("precio"));

                txtCodigoVenta.setText(codigoProducto);
                txtDescripcionVenta.setText(descripcion);
                txtPrecioVenta.setText((precio));
                txtCantidadVenta.requestFocus();
            } else {
                JOptionPane.showMessageDialog(null, "Código Incorrectro o Nulo");
                LimpiarVenta();
                txtCodigoVenta.requestFocus();
            }
        } catch (SQLException e) {
            System.out.println(e.toString());
        }
        return null;
    }

    public String IngresarProductoVentaNombre(String des) {
        Connection con;
        Conexion cn = new Conexion();
        PreparedStatement ps;
        ResultSet rs;
        String sql = "SELECT * FROM productos WHERE nombre = ?;";

        try {
            con = cn.getConnection();
            ps = con.prepareStatement(sql);
            ps.setString(1, des);
            rs = ps.executeQuery();

            if (rs.next()) {
                String codigoProducto = rs.getString("codigobarra");
                String descripcion = rs.getString("nombre");
                int precio = rs.getInt("precio");

                System.out.println(descripcion);

                txtCodigoVenta.setText(codigoProducto);
                txtDescripcionVenta.setText(descripcion);
                txtPrecioVenta.setText(String.valueOf(precio));
            } else {
                JOptionPane.showMessageDialog(null, "No existe el producto");
                //LimpiarProductos();
            }
        } catch (SQLException e) {
            System.out.println(e.toString());
        }
        return null;
    }
    
    public void LimpiarTable() {
        for (int i = 0; i < modelo.getRowCount(); i++) {
            modelo.removeRow(i);
            i = i - 1;
        }
    }
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel3 = new javax.swing.JLabel();
        txtCodigoVenta = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        txtDescripcionVenta = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        txtCantidadVenta = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        txtPrecioVenta = new javax.swing.JTextField();
        txtClienteVenta = new javax.swing.JTextField();
        txtDireccionClienteVenta = new javax.swing.JTextField();
        txtTelefonoClienteVenta = new javax.swing.JTextField();
        LabelTotal = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        btnEliminarVenta = new javax.swing.JButton();
        btnRealizarVenta = new javax.swing.JButton();
        txtIdVenta = new javax.swing.JTextField();
        jScrollPane3 = new javax.swing.JScrollPane();
        TableVenta = new javax.swing.JTable();
        PanelInicio = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jButton8 = new javax.swing.JButton();
        jLabel9 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setMinimumSize(new java.awt.Dimension(1380, 700));
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel3.setFont(new java.awt.Font("Liberation Sans", 3, 24)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Img/codigo-de-barras.png"))); // NOI18N
        jLabel3.setText("Código:");
        getContentPane().add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 110, 160, -1));

        txtCodigoVenta.setFont(new java.awt.Font("Liberation Sans", 2, 24)); // NOI18N
        txtCodigoVenta.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        txtCodigoVenta.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtCodigoVentaKeyTyped(evt);
            }
        });
        getContentPane().add(txtCodigoVenta, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 110, 380, -1));

        jLabel4.setFont(new java.awt.Font("Liberation Sans", 3, 24)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(255, 255, 255));
        jLabel4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Img/descripcion.png"))); // NOI18N
        jLabel4.setText("Descripción:");
        getContentPane().add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(580, 110, 200, -1));

        txtDescripcionVenta.setFont(new java.awt.Font("Liberation Sans", 2, 24)); // NOI18N
        txtDescripcionVenta.setHorizontalAlignment(javax.swing.JTextField.LEFT);
        txtDescripcionVenta.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        getContentPane().add(txtDescripcionVenta, new org.netbeans.lib.awtextra.AbsoluteConstraints(790, 110, 550, -1));

        jLabel5.setFont(new java.awt.Font("Liberation Sans", 3, 24)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(255, 255, 255));
        jLabel5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Img/cajas.png"))); // NOI18N
        jLabel5.setText("Cantidad:");
        getContentPane().add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 170, 160, -1));

        txtCantidadVenta.setFont(new java.awt.Font("Liberation Sans", 2, 24)); // NOI18N
        txtCantidadVenta.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        getContentPane().add(txtCantidadVenta, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 170, 380, -1));

        jLabel6.setFont(new java.awt.Font("Liberation Sans", 3, 24)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(255, 255, 255));
        jLabel6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Img/etiqueta-del-precio.png"))); // NOI18N
        jLabel6.setText("Precio:");
        getContentPane().add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(580, 170, 200, -1));

        txtPrecioVenta.setEditable(false);
        txtPrecioVenta.setBackground(new java.awt.Color(255, 255, 255));
        txtPrecioVenta.setFont(new java.awt.Font("Liberation Sans", 2, 24)); // NOI18N
        txtPrecioVenta.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        getContentPane().add(txtPrecioVenta, new org.netbeans.lib.awtextra.AbsoluteConstraints(790, 170, 550, -1));
        getContentPane().add(txtClienteVenta, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 320, 10, -1));
        getContentPane().add(txtDireccionClienteVenta, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 290, 10, -1));
        getContentPane().add(txtTelefonoClienteVenta, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 230, 10, -1));

        LabelTotal.setFont(new java.awt.Font("Liberation Sans", 2, 24)); // NOI18N
        LabelTotal.setForeground(new java.awt.Color(255, 255, 255));
        LabelTotal.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        LabelTotal.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Img/simbolo-de-dolar.png"))); // NOI18N
        LabelTotal.setText("0");
        LabelTotal.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        getContentPane().add(LabelTotal, new org.netbeans.lib.awtextra.AbsoluteConstraints(920, 230, 330, 110));

        jLabel8.setBackground(new java.awt.Color(255, 255, 255));
        jLabel8.setFont(new java.awt.Font("Liberation Sans", 3, 24)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(255, 255, 255));
        jLabel8.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel8.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Img/bruto.png"))); // NOI18N
        jLabel8.setText("Total a Pagar:");
        jLabel8.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jLabel8.setFocusable(false);
        jLabel8.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jLabel8.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        getContentPane().add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(680, 230, 230, 110));

        btnEliminarVenta.setBackground(new java.awt.Color(204, 204, 204));
        btnEliminarVenta.setFont(new java.awt.Font("Liberation Sans", 3, 24)); // NOI18N
        btnEliminarVenta.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Img/eliminar.png"))); // NOI18N
        btnEliminarVenta.setText("Eliminar Producto");
        btnEliminarVenta.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 2));
        btnEliminarVenta.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnEliminarVenta.setMaximumSize(new java.awt.Dimension(132, 58));
        btnEliminarVenta.setMinimumSize(new java.awt.Dimension(132, 58));
        btnEliminarVenta.setPreferredSize(new java.awt.Dimension(132, 58));
        btnEliminarVenta.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btnEliminarVenta.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEliminarVentaActionPerformed(evt);
            }
        });
        getContentPane().add(btnEliminarVenta, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 220, 220, 120));

        btnRealizarVenta.setBackground(new java.awt.Color(204, 204, 204));
        btnRealizarVenta.setFont(new java.awt.Font("Liberation Sans", 3, 24)); // NOI18N
        btnRealizarVenta.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Img/carrito-de-compras.png"))); // NOI18N
        btnRealizarVenta.setText("Realizar Venta");
        btnRealizarVenta.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 2));
        btnRealizarVenta.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnRealizarVenta.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btnRealizarVenta.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRealizarVentaActionPerformed(evt);
            }
        });
        getContentPane().add(btnRealizarVenta, new org.netbeans.lib.awtextra.AbsoluteConstraints(360, 220, 220, 120));
        getContentPane().add(txtIdVenta, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 260, 10, -1));

        TableVenta.setBackground(new java.awt.Color(204, 204, 204));
        TableVenta.setFont(new java.awt.Font("Liberation Sans", 3, 22)); // NOI18N
        TableVenta.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "CODIGO", "DESCRIPCION", "CANTIDAD", "PRECIO", "TOTAL"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Object.class, java.lang.Object.class, java.lang.Integer.class, java.lang.Integer.class, java.lang.Integer.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        TableVenta.setRowHeight(30);
        TableVenta.setShowGrid(true);
        TableVenta.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                TableVentaMouseClicked(evt);
            }
        });
        jScrollPane3.setViewportView(TableVenta);

        getContentPane().add(jScrollPane3, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 360, 1330, 390));

        PanelInicio.setBackground(new java.awt.Color(27, 66, 29));
        PanelInicio.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 2));
        PanelInicio.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Img/logo.png"))); // NOI18N
        jLabel2.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 2, true));
        PanelInicio.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 180, 100));

        jButton8.setBackground(new java.awt.Color(27, 66, 29));
        jButton8.setFont(new java.awt.Font("Liberation Sans", 0, 24)); // NOI18N
        jButton8.setForeground(new java.awt.Color(204, 204, 204));
        jButton8.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Img/esquema-del-boton-de-flecha-cuadrada-de-inicio-de-sesion.png"))); // NOI18N
        jButton8.setText("Volver A Inicio");
        jButton8.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 2, true));
        jButton8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton8ActionPerformed(evt);
            }
        });
        PanelInicio.add(jButton8, new org.netbeans.lib.awtextra.AbsoluteConstraints(1120, 0, 230, 100));

        jLabel9.setFont(new java.awt.Font("Liberation Sans", 0, 48)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(204, 204, 204));
        jLabel9.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel9.setText("NUEVA VENTA ALOGAR");
        PanelInicio.add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 0, 940, 100));

        getContentPane().add(PanelInicio, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1350, 100));

        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Img/Fondo Virtual para Zoom Ondas Il.png"))); // NOI18N
        jLabel1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 2));
        jLabel1.setMaximumSize(new java.awt.Dimension(1384, 1000));
        jLabel1.setMinimumSize(new java.awt.Dimension(1384, 1000));
        jLabel1.setPreferredSize(new java.awt.Dimension(1380, 1000));
        getContentPane().add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(-390, 0, 1770, 770));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton8ActionPerformed
        this.dispose();
        Inicio in = new Inicio();
        in.setVisible(true);
    }//GEN-LAST:event_jButton8ActionPerformed

    private void TableVentaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_TableVentaMouseClicked
        // TODO add your handling code here:
        int fila = TableVenta.rowAtPoint(evt.getPoint());
        txtCodigoVenta.setText(TableVenta.getValueAt(fila, 0).toString());
        txtDescripcionVenta.setText(TableVenta.getValueAt(fila, 1).toString());
        txtCantidadVenta.setText(TableVenta.getValueAt(fila, 2).toString());
        txtPrecioVenta.setText(TableVenta.getValueAt(fila, 3).toString());
    }//GEN-LAST:event_TableVentaMouseClicked

    private void btnEliminarVentaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEliminarVentaActionPerformed
        modelo = (DefaultTableModel) TableVenta.getModel();
        modelo.removeRow(TableVenta.getSelectedRow());
        TotalPagar();
        LimpiarVenta();
        txtCodigoVenta.requestFocus();
    }//GEN-LAST:event_btnEliminarVentaActionPerformed

    private void btnRealizarVentaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRealizarVentaActionPerformed
        RegistrarCliente();
        RegistrarVenta();
        RevisarExistenciaProducto();
        LimpiarVenta();
        GenerarBoleta();
        LimpiarTableVenta();
    }//GEN-LAST:event_btnRealizarVentaActionPerformed

    private void txtCodigoVentaKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtCodigoVentaKeyTyped
    event.numberKeyPress(evt);
    }//GEN-LAST:event_txtCodigoVentaKeyTyped

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(NuevaVenta.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(NuevaVenta.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(NuevaVenta.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(NuevaVenta.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new NuevaVenta().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel LabelTotal;
    private javax.swing.JPanel PanelInicio;
    private javax.swing.JTable TableVenta;
    private javax.swing.JButton btnEliminarVenta;
    private javax.swing.JButton btnRealizarVenta;
    private javax.swing.JButton jButton8;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JTextField txtCantidadVenta;
    private javax.swing.JTextField txtClienteVenta;
    private javax.swing.JTextField txtCodigoVenta;
    private javax.swing.JTextField txtDescripcionVenta;
    private javax.swing.JTextField txtDireccionClienteVenta;
    private javax.swing.JTextField txtIdVenta;
    private javax.swing.JTextField txtPrecioVenta;
    private javax.swing.JTextField txtTelefonoClienteVenta;
    // End of variables declaration//GEN-END:variables

    private void LimpiarVenta() {
        txtCodigoVenta.setText("");
        txtDescripcionVenta.setText("");
        txtCantidadVenta.setText("");
        txtPrecioVenta.setText("");
        txtIdVenta.setText("");
    }
    
    private void RegistrarCliente(){
        int opcion1 = JOptionPane.showConfirmDialog(null, "¿Ingresar Cliente?", "Confirmación", JOptionPane.YES_NO_OPTION);

        if (opcion1 == JOptionPane.YES_OPTION){
            setTitle("Registrar Nombre Cliente");
            setLocationRelativeTo(null);
             
            JPanel panel1 = new JPanel();
            panel1.setLayout(new GridLayout(1, 2));
            panel1.setPreferredSize(new Dimension(350,1));
            
            JLabel etiquetaNombre = new JLabel("Nombre Cliete: ");
            JTextField campoNombre = new JTextField();
            
            panel1.add(etiquetaNombre);
            panel1.add(campoNombre);
            
            int opcion2 = JOptionPane.showConfirmDialog(
                    this,
                    panel1,
                    "Agregar Productos",
                    JOptionPane.OK_CANCEL_OPTION,
                    JOptionPane.QUESTION_MESSAGE
            );
        
            if (opcion2 == JOptionPane.OK_OPTION){
                String nombre = campoNombre.getText();
                Cliente cliente = client.BuscarCliente(nombre);
                    if(nombre.equals(cliente.getNombre())){
                        JOptionPane.showMessageDialog(null, "El Cliente Ya Esta Registrado");
                    }else{
                        setTitle("Registrar Cliente");
                        setLocationRelativeTo(null);

                        JPanel panel2 = new JPanel();
                        panel2.setLayout(new GridLayout(4, 2));


                        JLabel etiquetaEmail = new JLabel("Mail Cliente: ");
                        JTextField campoEmail = new JTextField();

                        JLabel etiquetaTelefono = new JLabel("Telefono Cliente: ");
                        JTextField campoTelefono = new JTextField();


                        JLabel etiquetaDireccion = new JLabel("Dirección Cliente: ");
                        JTextField campoDireccion = new JTextField();

                        JLabel etiquetaFecha = new JLabel("Fecha de Ingreso: ");
                        JLabel campoFecha = new JLabel(" " + fechaActual);


                        panel2.add(etiquetaEmail);
                        panel2.add(campoEmail);
                        panel2.add(etiquetaTelefono);
                        panel2.add(campoTelefono);
                        panel2.add(etiquetaDireccion);
                        panel2.add(campoDireccion);
                        panel2.add(etiquetaFecha);
                        panel2.add(campoFecha);

                        int opcion3 = JOptionPane.showConfirmDialog(
                            this,
                            panel2,
                            "Agregar Productos",
                            JOptionPane.OK_CANCEL_OPTION,
                            JOptionPane.QUESTION_MESSAGE
                        );

                        if (opcion3 == JOptionPane.OK_OPTION){
                            String mail = campoEmail.getText();
                            String telefono = campoTelefono.getText();
                            String direccion = campoDireccion.getText();
                            if (!mail.isEmpty() && !telefono.isEmpty() && !direccion.isEmpty()){
                                Cliente clientenuevo = new Cliente();
                                clientenuevo.setNombre(nombre);
                                clientenuevo.setEmail(mail);
                                clientenuevo.setTelefono(telefono);
                                clientenuevo.setDireccion(direccion);
                                client.RegistrarCliente(clientenuevo);
                                JOptionPane.showMessageDialog(null, "Cliente Agregado");
                                LimpiarTable();
                                txtCodigoVenta.requestFocus();
                                txtClienteVenta.setText(clientenuevo.getNombre());
                                txtDireccionClienteVenta.setText(clientenuevo.getDireccion());
                                txtTelefonoClienteVenta.setText(clientenuevo.getTelefono());
                            }
                        }
                    }
                }else{
                    JOptionPane.showConfirmDialog(null, "Ingrese El Nombre Del Cliente");
                }
            }else{
                LimpiarTable();
                txtCodigoVenta.requestFocus();
                txtClienteVenta.setText("No Registrado");
                txtClienteVenta.setText("No Registrado");
                txtDireccionClienteVenta.setText("No Registrado");
                txtTelefonoClienteVenta.setText("No Registrado");
            }
        }
    
    private void RegistrarVenta() {
        v.setTotal(TotalPagar);
        v.setFecha_venta(fechaActual);
        VDao.RegistrarVenta(v);
    }
    
    private void RegistrarProductosVenta(String producto, int cantidad, int precio) {
            String cliente = txtClienteVenta.getText();
            pv.setCliente(cliente);
            pv.setProductos(producto);
            pv.setCantidad(cantidad);
            pv.setPrecio_producto(precio);
            pv.setFecha_venta(fechaActual);
            VDao.RegistrarProductosVenta(pv);
    }
    
    private void RevisarExistenciaProducto(){
        for(int i = 0; i < TableVenta.getRowCount(); i++){
            String producto = TableVenta.getValueAt(i, 1).toString();
            int precio = Integer.parseInt(TableVenta.getValueAt(i, 3).toString());
            int cantidad = Integer.parseInt(TableVenta.getValueAt(i, 2).toString());
            ProductosVentas PVenta = VDao.BuscarProducto(producto, precio);
            if(PVenta == null){
                RegistrarProductosVenta(producto, cantidad, precio);
            }else{
                int cantidad_nueva = PVenta.getCantidad() + cantidad;
                PVenta.setCantidad(cantidad_nueva);
                VDao.ActualizarProductosVentas(PVenta);
            }
        }
    }
    
    private void GenerarBoleta(){
        try{
            int num_boleta;
            num_boleta = VDao.IdVenta();
            com.itextpdf.text.Rectangle pageSize = new com.itextpdf.text.Rectangle(227, 792);
            FileOutputStream archivo;
            File file = new File(System.getProperty("user.home") + "/Escritorio/boleta" + num_boleta + "-" + fechaActual + ".pdf");
            archivo = new FileOutputStream(file);
            Document doc;
            doc = new Document(pageSize);
            PdfWriter.getInstance(doc, archivo);
            doc.open(); 
            
            PdfPTable NumBoleta = new PdfPTable(1);
            NumBoleta.setWidthPercentage(100);
            NumBoleta.getDefaultCell().setBorder(0);
            float[] ColumnaNumBoleta = new float[]{50f};
            NumBoleta.setWidths(ColumnaNumBoleta);

            Font numeroBoleta = new Font(Font.FontFamily.TIMES_ROMAN, 8, Font.BOLD, BaseColor.WHITE);
            PdfPCell cellTitulo = new PdfPCell(new Paragraph("VENTA N°"+ num_boleta, numeroBoleta));
            cellTitulo.setHorizontalAlignment(Element.ALIGN_RIGHT);
            cellTitulo.setBackgroundColor(BaseColor.DARK_GRAY);
            cellTitulo.setPaddingTop(5);
            cellTitulo.setPaddingBottom(5);
            cellTitulo.setBorder(0);
            
            NumBoleta.addCell(cellTitulo);
            
            doc.add(NumBoleta);
            
            PdfPTable Titulo = new PdfPTable(2);
            Titulo.setWidthPercentage(100);
            Titulo.getDefaultCell().setBorder(0);
            float[] ColumnaTitulo = new float[]{30f, 60f};
            Titulo.setWidths(ColumnaTitulo);
            
            String img = System.getProperty("user.home") + "/Escritorio/logo.png";

            Image image = Image.getInstance(img);
            image.setWidthPercentage(100);
            image.scaleToFit(50, 100);
            image.setAlignment(Element.ALIGN_LEFT);
            
            PdfPCell cellImagen = new PdfPCell(image);
            cellImagen.setBorder(0);
            cellImagen.setPaddingRight(10);

            Titulo.addCell(image);
            
            Font titulo = new Font(Font.FontFamily.TIMES_ROMAN, 6, Font.NORMAL, BaseColor.BLACK);
            PdfPCell texto1 = new PdfPCell(new Phrase("NOMBRE:  " + txtClienteVenta.getText() + "\nFECHA: " + fechaActual + "\nDIRECCIÓN: " + txtDireccionClienteVenta.getText() + "\nTELÉFONO: " + txtTelefonoClienteVenta.getText(), titulo));
            texto1.setHorizontalAlignment(Element.ALIGN_JUSTIFIED);
            texto1.setBorder(0);
            texto1.setPaddingLeft(30);
            
            Titulo.addCell(texto1);
            Titulo.addCell(new Paragraph("\n"));
            
            doc.add(Titulo);
            
            PdfPTable tablapro = new PdfPTable(4);
            tablapro.setWidthPercentage(100);
            float[] ColumnaPro;
            ColumnaPro = new float[]{120f, 80f, 80f, 80f};
            tablapro.setWidths(ColumnaPro); 
            
            Font textos = new Font(Font.FontFamily.TIMES_ROMAN, 5, Font.NORMAL, BaseColor.WHITE);
            
            PdfPCell pro1 = new PdfPCell(new Phrase("\nDESCRIPCIÓN", textos));
            pro1.setBackgroundColor(BaseColor.DARK_GRAY);
            pro1.setHorizontalAlignment(Element.ALIGN_CENTER);
            pro1.setBorderColor(BaseColor.WHITE);
            
            PdfPCell pro2 = new PdfPCell(new Phrase("\nCANTIDAD", textos));
            pro2.setBackgroundColor(BaseColor.DARK_GRAY);
            pro2.setHorizontalAlignment(Element.ALIGN_CENTER);
            pro2.setBorderColor(BaseColor.WHITE);
            
            PdfPCell pro3 = new PdfPCell(new Phrase("\nPRECIO", textos));
            pro3.setBackgroundColor(BaseColor.DARK_GRAY);
            pro3.setHorizontalAlignment(Element.ALIGN_CENTER);
            pro3.setBorderColor(BaseColor.WHITE);
            
            PdfPCell pro4 = new PdfPCell(new Phrase("\nSUBTOTAL", textos));
            pro4.setBackgroundColor(BaseColor.DARK_GRAY);
            pro4.setHorizontalAlignment(Element.ALIGN_CENTER);
            pro4.setBorderColor(BaseColor.WHITE);
            
            tablapro.addCell(pro1);
            tablapro.addCell(pro2);
            tablapro.addCell(pro3);
            tablapro.addCell(pro4);
            
            Font productos = new Font(Font.FontFamily.TIMES_ROMAN, 7, Font.NORMAL, BaseColor.BLACK);

            for (int i = 0; i < TableVenta.getRowCount(); i++) {
                String producto = TableVenta.getValueAt(i, 1).toString();
                String cantidad = TableVenta.getValueAt(i, 2).toString();
                double precio = Double.parseDouble(TableVenta.getValueAt(i, 3).toString());
                String precioFormateado = String.format("%.0f", precio);
                double total_producto = Double.parseDouble(TableVenta.getValueAt(i, 4).toString());
                String total_productoFormateado = String.format("%.0f", total_producto);

                PdfPCell cellProducto = new PdfPCell(new Phrase(producto, productos));
                PdfPCell cellCantidad = new PdfPCell(new Phrase(cantidad, productos));
                PdfPCell cellPrecio = new PdfPCell(new Phrase(precioFormateado, productos));
                PdfPCell cellTotal = new PdfPCell(new Phrase(total_productoFormateado, productos));

                cellProducto.setBorderColor(BaseColor.BLACK);
                cellCantidad.setBorderColor(BaseColor.BLACK);
                cellPrecio.setBorderColor(BaseColor.BLACK);
                cellTotal.setBorderColor(BaseColor.BLACK);
                
                cellProducto.setHorizontalAlignment(Element.ALIGN_CENTER);
                cellCantidad.setHorizontalAlignment(Element.ALIGN_CENTER);
                cellPrecio.setHorizontalAlignment(Element.ALIGN_CENTER);
                cellTotal.setHorizontalAlignment(Element.ALIGN_CENTER);

                cellProducto.setPaddingBottom(10);
                cellCantidad.setPaddingBottom(10);
                cellPrecio.setPaddingBottom(10);
                cellTotal.setPaddingBottom(10);

                tablapro.addCell(cellProducto);
                tablapro.addCell(cellCantidad);
                tablapro.addCell(cellPrecio);
                tablapro.addCell(cellTotal);
            }
            
            Font subtotal = new Font(Font.FontFamily.TIMES_ROMAN, 5, Font.NORMAL, BaseColor.BLACK);
            
            PdfPCell vacio = new PdfPCell(new Phrase("\n"));
            vacio.setBorder(0);
            
            tablapro.addCell(vacio);
            tablapro.addCell(vacio);
            
            PdfPCell subtotaltexto = new PdfPCell(new Phrase("SUBTOTAL", subtotal));
            subtotaltexto.setHorizontalAlignment(Element.ALIGN_CENTER);
            subtotaltexto.setBorderColor(BaseColor.BLACK);
            
            double total_boleta = Double.parseDouble(LabelTotal.getText());
            String totalFormateado = String.format("%.0f", total_boleta);
             
            PdfPCell subtotalnum = new PdfPCell(new Phrase(totalFormateado, productos));
            subtotalnum.setHorizontalAlignment(Element.ALIGN_CENTER);
            subtotalnum.setBorderColor(BaseColor.BLACK);

            tablapro.addCell(subtotaltexto);
            tablapro.addCell(subtotalnum);
                        
            tablapro.addCell(vacio);
            tablapro.addCell(vacio);
            
            PdfPCell totaltexto = new PdfPCell(new Phrase("TOTAL", textos));
            totaltexto.setBackgroundColor(BaseColor.DARK_GRAY);
            totaltexto.setHorizontalAlignment(Element.ALIGN_CENTER);
            totaltexto.setBorderColor(BaseColor.BLACK);
            
            PdfPCell totalnum = new PdfPCell(new Phrase(totalFormateado, productos));
            totalnum.setHorizontalAlignment(Element.ALIGN_CENTER);
            totalnum.setBorderColor(BaseColor.BLACK);

            tablapro.addCell(totaltexto);
            tablapro.addCell(totalnum);
            
            doc.add(tablapro);
            
            PdfPTable datosEmpresa = new PdfPTable(1);
            datosEmpresa.setWidthPercentage(100);
            float[] ColumnaEmpresa = new float[]{20f};
            datosEmpresa.setWidths(ColumnaEmpresa);
            datosEmpresa.setHorizontalAlignment(Element.ALIGN_LEFT);
            
            conf = proDao.BuscarDatos();
            PdfPCell datos = new PdfPCell(new Phrase(conf.getTelefono() + "\n" + conf.getDireccion() + "\n" + conf.getNombre() + "\n", titulo));
            datos.setBorder(0);
            datos.setHorizontalAlignment(Element.ALIGN_LEFT);
            
            datosEmpresa.addCell(datos);
            
            doc.add(datosEmpresa);
            
            doc.close();
            archivo.close();
        }catch(DocumentException | FileNotFoundException e){
            System.out.println(e.toString());
        } catch (IOException ex) {
            Logger.getLogger(NuevaVenta.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    private void LimpiarTableVenta() {
        tmp = (DefaultTableModel) TableVenta.getModel();
        int fila = TableVenta.getRowCount();
        for (int i = 0; i < fila; i++) {
            tmp.removeRow(0);
        }
    }

    private void TotalPagar() {
        TotalPagar = 0;
        int numFila = TableVenta.getRowCount();
        for (int i = 0; i < numFila; i++) {
            double cal = Double.parseDouble(TableVenta.getModel().getValueAt(i, 4).toString());
            TotalPagar = TotalPagar + cal;
        }
        LabelTotal.setText(String.format("%.0f", TotalPagar));
    }
    
}

