/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package Vista;

import Modelo.CodPaisesDao;
import Modelo.Conexion;
import Modelo.ProductosDao;
import Reportes.ExcelProductos;
import java.awt.event.KeyEvent;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.KeyAdapter;
import org.jdesktop.swingx.autocomplete.AutoCompleteDecorator;
import javax.swing.RowSorter;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;


/**
 *
 * @author alfredo
 */
public final class Productos extends javax.swing.JFrame {
    DefaultTableModel modelo = new DefaultTableModel();
    Date fechaVenta = new Date();
    String fechaActual = new SimpleDateFormat("yyyy-MM-dd").format(fechaVenta);
    Modelo.Productos pro = new Modelo.Productos();
    ProductosDao proDao = new ProductosDao();
    CodPaisesDao codPaisesDao = new CodPaisesDao();

    
    public Productos() {
        initComponents();
        this.setLocationRelativeTo(null);
        AutoCompleteDecorator.decorate(cbxProveedorPro);
        AutoCompleteDecorator.decorate(cbxNombreProducto);
        proDao.ConsularProveedor(cbxProveedorPro);
        proDao.ConsularProducto(cbxNombreProducto);
        txtIdpro.setVisible(false);
        cbxNombreProducto.setSelectedItem("");
        txtCodigoProducto.requestFocus();
        txtCodigoProducto.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
                String codigo = txtCodigoProducto.getText();
                if (!codigo.isEmpty()) {
                    IngresarProductoCodigo(codigo);
                }
            }
        });
    }

    public String IngresarProductoCodigo(String scannedBarcode) {
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
                double precio = rs.getInt("precio");
                String precioFormateado = String.format("%.0f", precio);
                String proveedor = rs.getString("proveedor");

                txtCodigoProducto.setText(codigoProducto);
                cbxNombreProducto.setSelectedItem(descripcion);
                txtPrecioPro.setText(precioFormateado);
                cbxProveedorPro.setSelectedItem(String.valueOf(proveedor));

            } else {
                JOptionPane.showMessageDialog(null, "Código Incorrectro o Nulo");
                LimpiarProductos();
            }
        } catch (SQLException e) {
            System.out.println(e.toString());
        }
        return null;
    }
    
    public void ListarProductos() {
        List<Modelo.Productos> ListarPro = proDao.ListarProductos();
        modelo = (DefaultTableModel) TableProducto.getModel();
        Object[] ob = new Object[5];
        for (int i = 0; i < ListarPro.size(); i++) {
            ob[0] = ListarPro.get(i).getId();
            ob[1] = ListarPro.get(i).getCodigo();
            ob[2] = ListarPro.get(i).getNombre();
            ob[3] = ListarPro.get(i).getProveedor();
            ob[4] = ListarPro.get(i).getPrecio();
            modelo.addRow(ob);
        }
        TableProducto.setModel(modelo);
    }

    public String IngresarProductoVenta(String producto) {
        Connection con;
        Conexion cn = new Conexion();
        PreparedStatement ps;
        ResultSet rs;
        String sql = "SELECT * FROM productos WHERE nombre = ?";

        try {
            con = cn.getConnection();
            ps = con.prepareStatement(sql);
            ps.setString(1, producto);
            rs = ps.executeQuery();

            if (rs.next()) {
                String codigoProducto = rs.getString("codigobarra");
                String descripcion = rs.getString("nombre");
                double precio = rs.getInt("precio");
                String proveedor = rs.getString("proveedor");

                txtCodigoProducto.setText(codigoProducto);
                cbxNombreProducto.setSelectedItem(descripcion);
                txtPrecioPro.setText(String.valueOf(precio));
                cbxProveedorPro.setSelectedItem(String.valueOf(proveedor));

            } else {
                LimpiarProductos();
            }
        } catch (SQLException e) {
            System.out.println(e.toString());
        }
        return null;
    }


    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel20 = new javax.swing.JLabel();
        jLabel21 = new javax.swing.JLabel();
        jLabel23 = new javax.swing.JLabel();
        txtCodigoProducto = new javax.swing.JTextField();
        btnEliminarPro = new javax.swing.JButton();
        btnExcelPro = new javax.swing.JButton();
        btnIngresarProductos = new javax.swing.JButton();
        btnEditarPrecio = new javax.swing.JButton();
        jLabel24 = new javax.swing.JLabel();
        txtIdpro = new javax.swing.JTextField();
        cbxProveedorPro = new javax.swing.JComboBox<>();
        jScrollPane6 = new javax.swing.JScrollPane();
        TableProducto = new javax.swing.JTable();
        cbxNombreProducto = new javax.swing.JComboBox<>();
        txtPrecioPro = new javax.swing.JTextField();
        PanelInicio = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        btnCerrarSesion = new javax.swing.JButton();
        jLabel9 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel20.setFont(new java.awt.Font("Liberation Sans", 3, 24)); // NOI18N
        jLabel20.setForeground(new java.awt.Color(255, 255, 255));
        jLabel20.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Img/codigo-de-barras.png"))); // NOI18N
        jLabel20.setText("Código:");
        getContentPane().add(jLabel20, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 110, 160, -1));

        jLabel21.setFont(new java.awt.Font("Liberation Sans", 3, 24)); // NOI18N
        jLabel21.setForeground(new java.awt.Color(255, 255, 255));
        jLabel21.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Img/descripcion.png"))); // NOI18N
        jLabel21.setText("Descripción:");
        getContentPane().add(jLabel21, new org.netbeans.lib.awtextra.AbsoluteConstraints(480, 110, -1, -1));

        jLabel23.setFont(new java.awt.Font("Liberation Sans", 3, 24)); // NOI18N
        jLabel23.setForeground(new java.awt.Color(255, 255, 255));
        jLabel23.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Img/etiqueta-del-precio.png"))); // NOI18N
        jLabel23.setText("Precio:");
        getContentPane().add(jLabel23, new org.netbeans.lib.awtextra.AbsoluteConstraints(480, 160, 180, -1));

        txtCodigoProducto.setFont(new java.awt.Font("Liberation Sans", 2, 24)); // NOI18N
        txtCodigoProducto.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        getContentPane().add(txtCodigoProducto, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 110, 280, -1));

        btnEliminarPro.setBackground(new java.awt.Color(204, 204, 204));
        btnEliminarPro.setFont(new java.awt.Font("Liberation Sans", 3, 24)); // NOI18N
        btnEliminarPro.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Img/eliminar.png"))); // NOI18N
        btnEliminarPro.setText("Eliminar Producto");
        btnEliminarPro.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 2));
        btnEliminarPro.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnEliminarPro.setPreferredSize(new java.awt.Dimension(120, 51));
        btnEliminarPro.setVerifyInputWhenFocusTarget(false);
        btnEliminarPro.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btnEliminarPro.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEliminarProActionPerformed(evt);
            }
        });
        getContentPane().add(btnEliminarPro, new org.netbeans.lib.awtextra.AbsoluteConstraints(1100, 210, 240, 100));

        btnExcelPro.setBackground(new java.awt.Color(204, 204, 204));
        btnExcelPro.setFont(new java.awt.Font("Liberation Sans", 3, 24)); // NOI18N
        btnExcelPro.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Img/sobresalir.png"))); // NOI18N
        btnExcelPro.setText("Generar Excel");
        btnExcelPro.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 2));
        btnExcelPro.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnExcelPro.setMaximumSize(new java.awt.Dimension(120, 27));
        btnExcelPro.setMinimumSize(new java.awt.Dimension(120, 27));
        btnExcelPro.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btnExcelPro.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnExcelProActionPerformed(evt);
            }
        });
        getContentPane().add(btnExcelPro, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 210, 240, 100));

        btnIngresarProductos.setBackground(new java.awt.Color(204, 204, 204));
        btnIngresarProductos.setFont(new java.awt.Font("Liberation Sans", 3, 24)); // NOI18N
        btnIngresarProductos.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Img/anadir-a-la-cola.png"))); // NOI18N
        btnIngresarProductos.setText("Agregar Producto");
        btnIngresarProductos.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 2));
        btnIngresarProductos.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnIngresarProductos.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btnIngresarProductos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnIngresarProductosActionPerformed(evt);
            }
        });
        getContentPane().add(btnIngresarProductos, new org.netbeans.lib.awtextra.AbsoluteConstraints(390, 210, 240, 100));

        btnEditarPrecio.setBackground(new java.awt.Color(204, 204, 204));
        btnEditarPrecio.setFont(new java.awt.Font("Liberation Sans", 3, 24)); // NOI18N
        btnEditarPrecio.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Img/editar-codigo.png"))); // NOI18N
        btnEditarPrecio.setText("Editar Producto");
        btnEditarPrecio.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 2));
        btnEditarPrecio.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnEditarPrecio.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btnEditarPrecio.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEditarPrecioActionPerformed(evt);
            }
        });
        getContentPane().add(btnEditarPrecio, new org.netbeans.lib.awtextra.AbsoluteConstraints(750, 210, 240, 100));

        jLabel24.setFont(new java.awt.Font("Liberation Sans", 3, 24)); // NOI18N
        jLabel24.setForeground(new java.awt.Color(255, 255, 255));
        jLabel24.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Img/mensajero.png"))); // NOI18N
        jLabel24.setText("Proveedor:");
        getContentPane().add(jLabel24, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 160, 160, -1));

        txtIdpro.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtIdproActionPerformed(evt);
            }
        });
        getContentPane().add(txtIdpro, new org.netbeans.lib.awtextra.AbsoluteConstraints(1270, 120, -1, -1));

        cbxProveedorPro.setFont(new java.awt.Font("Liberation Sans", 2, 24)); // NOI18N
        cbxProveedorPro.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        getContentPane().add(cbxProveedorPro, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 160, 280, -1));

        TableProducto.setBackground(new java.awt.Color(204, 204, 204));
        TableProducto.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        TableProducto.setFont(new java.awt.Font("Liberation Mono", 2, 21)); // NOI18N
        TableProducto.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "CODIGO BARRA", "PRODUCTO", "PROVEEDOR", "PRECIO"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.String.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        TableProducto.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_NEXT_COLUMN);
        TableProducto.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        TableProducto.setGridColor(new java.awt.Color(0, 0, 0));
        TableProducto.setRowHeight(45);
        TableProducto.setShowHorizontalLines(true);
        TableProducto.setShowVerticalLines(true);
        TableProducto.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                TableProductoMouseClicked(evt);
            }
        });
        jScrollPane6.setViewportView(TableProducto);
        if (TableProducto.getColumnModel().getColumnCount() > 0) {
            TableProducto.getColumnModel().getColumn(0).setPreferredWidth(1);
            TableProducto.getColumnModel().getColumn(1).setPreferredWidth(40);
            TableProducto.getColumnModel().getColumn(2).setPreferredWidth(400);
            TableProducto.getColumnModel().getColumn(3).setPreferredWidth(150);
            TableProducto.getColumnModel().getColumn(4).setPreferredWidth(5);
        }
        TableRowSorter<TableModel> sorter = new TableRowSorter<>(TableProducto.getModel());
        TableProducto.setRowSorter(sorter);

        sorter.setSortable(0, true);

        TableProducto.setAutoCreateRowSorter(true);

        getContentPane().add(jScrollPane6, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 330, 1330, 420));

        cbxNombreProducto.setFont(new java.awt.Font("Liberation Sans", 2, 24)); // NOI18N
        cbxNombreProducto.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        getContentPane().add(cbxNombreProducto, new org.netbeans.lib.awtextra.AbsoluteConstraints(670, 110, 570, -1));

        txtPrecioPro.setFont(new java.awt.Font("Liberation Sans", 2, 24)); // NOI18N
        txtPrecioPro.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        getContentPane().add(txtPrecioPro, new org.netbeans.lib.awtextra.AbsoluteConstraints(670, 160, 570, -1));

        PanelInicio.setBackground(new java.awt.Color(27, 66, 29));
        PanelInicio.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 2));
        PanelInicio.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Img/logo.png"))); // NOI18N
        jLabel2.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 2, true));
        PanelInicio.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 180, 100));

        btnCerrarSesion.setBackground(new java.awt.Color(27, 66, 29));
        btnCerrarSesion.setFont(new java.awt.Font("Liberation Sans", 0, 24)); // NOI18N
        btnCerrarSesion.setForeground(new java.awt.Color(204, 204, 204));
        btnCerrarSesion.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Img/esquema-del-boton-de-flecha-cuadrada-de-inicio-de-sesion.png"))); // NOI18N
        btnCerrarSesion.setText("Volver A Inicio");
        btnCerrarSesion.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 2, true));
        btnCerrarSesion.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCerrarSesionActionPerformed(evt);
            }
        });
        PanelInicio.add(btnCerrarSesion, new org.netbeans.lib.awtextra.AbsoluteConstraints(1120, 0, 230, 100));

        jLabel9.setFont(new java.awt.Font("Liberation Sans", 0, 48)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(204, 204, 204));
        jLabel9.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel9.setText("PRODUCTOS ALOGAR");
        PanelInicio.add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 0, 940, 100));

        getContentPane().add(PanelInicio, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1350, 100));

        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Img/Fondo Virtual para Zoom Ondas Il.png"))); // NOI18N
        jLabel1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 2));
        jLabel1.setPreferredSize(new java.awt.Dimension(1380, 700));
        getContentPane().add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1350, 770));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnCerrarSesionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCerrarSesionActionPerformed
        this.dispose();
        Inicio in = new Inicio();
        in.setVisible(true);
    }//GEN-LAST:event_btnCerrarSesionActionPerformed

    private void btnEliminarProActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEliminarProActionPerformed
        // TODO add your handling code here:
        if (!"".equals(txtIdpro.getText())) {
            int pregunta = JOptionPane.showConfirmDialog(null, "¿Esta seguro de eliminar?");
            if (pregunta == 0) {
                int id = Integer.parseInt(txtIdpro.getText());
                proDao.EliminarProductos(id);
                LimpiarProductos();
                ListarProductos();
                txtCodigoProducto.requestFocus();
            }
        }
    }//GEN-LAST:event_btnEliminarProActionPerformed

    private void btnExcelProActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnExcelProActionPerformed
        // TODO add your handling code here:
        ExcelProductos.reporte();
        txtCodigoProducto.requestFocus();
    }//GEN-LAST:event_btnExcelProActionPerformed

    private void txtIdproActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtIdproActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtIdproActionPerformed

    private void TableProductoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_TableProductoMouseClicked
        // TODO add your handling code here:
        int fila = TableProducto.rowAtPoint(evt.getPoint());
        txtIdpro.setText(TableProducto.getValueAt(fila, 0).toString());
        txtCodigoProducto.setText(TableProducto.getValueAt(fila, 1).toString());
        cbxNombreProducto.setSelectedItem(TableProducto.getValueAt(fila, 2).toString());
        cbxProveedorPro.setSelectedItem(TableProducto.getValueAt(fila, 3).toString());
        txtPrecioPro.setText(TableProducto.getValueAt(fila, 4).toString());
    }//GEN-LAST:event_TableProductoMouseClicked

    private void btnIngresarProductosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnIngresarProductosActionPerformed
        setTitle("Ingresar Nueva Cantidad Producto");

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(6, 2));

        JLabel etiquetaCodigo = new JLabel("Código Producto: ");
        JTextField campoCodigo = new JTextField();

        JButton generarCodigo = new JButton("Generar Código");
        JLabel espacio = new JLabel("\n");

        JLabel etiquetaProducto = new JLabel("Nombre Producto: ");
        JTextField campoProducto = new JTextField();

        JLabel etiquetaProveedor = new JLabel("Seleccione Proveedor: ");
        JComboBox<List> comboBoxProveedor = new JComboBox<>();
        proDao.ConsularProveedor(comboBoxProveedor);

        JLabel etiquetaPrecio = new JLabel("Precio Producto: ");
        JTextField campoPrecio = new JTextField();

        JLabel etiquetaFecha = new JLabel("Fecha de Ingreso: ");
        JLabel campoFecha = new JLabel(" " + fechaActual);

        generarCodigo.addActionListener((ActionEvent e) -> {
            // Llama a tu función para generar el código
            String nuevoCodigo = generarCodigoProducto();
            campoCodigo.setText(nuevoCodigo);
        });

        panel.add(etiquetaCodigo);
        panel.add(campoCodigo);
        panel.add(espacio);
        panel.add(generarCodigo);
        panel.add(etiquetaProducto);
        panel.add(campoProducto);
        panel.add(etiquetaProveedor);
        panel.add(comboBoxProveedor);
        panel.add(etiquetaPrecio);
        panel.add(campoPrecio);
        panel.add(etiquetaFecha);
        panel.add(campoFecha);

        int opcion = JOptionPane.showConfirmDialog(
            this,
            panel,
            "Agregar Productos",
            JOptionPane.OK_CANCEL_OPTION,
            JOptionPane.QUESTION_MESSAGE
        );

        if (opcion == JOptionPane.OK_OPTION) {
            String codigo = campoCodigo.getText();
            String producto = campoProducto.getText();
            String proveedor = comboBoxProveedor.getSelectedItem().toString();
            String fecha = campoFecha.getText();
            String cantStr = campoPrecio.getText();
            if (!producto.isEmpty() && !fecha.isEmpty() && !cantStr.isEmpty() && !proveedor.isEmpty() && !codigo.isEmpty()) {
                pro.setCodigo(codigo);
                pro.setNombre(producto);
                pro.setProveedor(proveedor);
                pro.setPrecio(cantStr);
                pro.setFecha(fecha);
                proDao.RegistrarProductos(pro);
                JOptionPane.showMessageDialog(null, "Producto Registrado");
                LimpiarTable();
                ListarProductos();
                LimpiarProductos();
                txtCodigoProducto.requestFocus();
                dispose();
            }

        }
    }//GEN-LAST:event_btnIngresarProductosActionPerformed

    private void btnEditarPrecioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEditarPrecioActionPerformed
        // TODO add your handling code here:
        if ("".equals(txtIdpro.getText())) {
            JOptionPane.showMessageDialog(null, "Seleccione una fila");
        } else {
            if (!"".equals(txtCodigoProducto.getText()) || !"".equals(cbxNombreProducto.getSelectedItem()) || !"".equals(cbxProveedorPro.getSelectedItem()) || !"".equals(txtPrecioPro.getText())) {
                pro.setCodigo((txtCodigoProducto.getText()));
                pro.setNombre(cbxNombreProducto.getSelectedItem().toString());
                pro.setProveedor(cbxProveedorPro.getSelectedItem().toString());
                pro.setPrecio(txtPrecioPro.getText());
                pro.setId(Integer.parseInt(txtIdpro.getText()));
                proDao.ModificarProductos(pro);
                JOptionPane.showMessageDialog(null, "Producto Modificado");
                LimpiarTable();
                ListarProductos();
                LimpiarProductos();
            }
        }
    }//GEN-LAST:event_btnEditarPrecioActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Productos().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel PanelInicio;
    private javax.swing.JTable TableProducto;
    private javax.swing.JButton btnCerrarSesion;
    private javax.swing.JButton btnEditarPrecio;
    private javax.swing.JButton btnEliminarPro;
    private javax.swing.JButton btnExcelPro;
    private javax.swing.JButton btnIngresarProductos;
    private javax.swing.JComboBox<String> cbxNombreProducto;
    private javax.swing.JComboBox<String> cbxProveedorPro;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JScrollPane jScrollPane6;
    private javax.swing.JTextField txtCodigoProducto;
    private javax.swing.JTextField txtIdpro;
    private javax.swing.JTextField txtPrecioPro;
    // End of variables declaration//GEN-END:variables

    private void LimpiarProductos() {
        txtIdpro.setText("");
        txtCodigoProducto.setText("");
        cbxNombreProducto.setSelectedItem(null);
        cbxProveedorPro.setSelectedItem(null);
        txtPrecioPro.setText("");
    }
    
    private String generarCodigoProducto(){
        setTitle("Generar Código de Producto");
        setLocationRelativeTo(null);

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(1, 2));

        JLabel etiquetaPais = new JLabel("Seleccionar país de procedencia: ");
        JComboBox<List> comboBoxPais = new JComboBox<>();
        codPaisesDao.ConsularPais(comboBoxPais);

        panel.add(etiquetaPais);
        panel.add(comboBoxPais);

        int opcion = JOptionPane.showConfirmDialog(
                this,
                panel,
                "Seleccionar País",
                JOptionPane.OK_CANCEL_OPTION,
                JOptionPane.QUESTION_MESSAGE
        );

        if (opcion == JOptionPane.OK_OPTION) {
            String paisSeleccionado = (String) comboBoxPais.getSelectedItem();
            String codigoPais = codPaisesDao.BuscarPais(paisSeleccionado);

            // Generar un número aleatorio para el código de empresa
            int codigoEmpresa = (int) (Math.pow(10, 4) + Math.random() * 90000);
            String codigoEmpresaStr = String.valueOf(codigoEmpresa);

            String codigoparcial = codigoPais + codigoEmpresaStr;
            int longitudcodigo = codigoparcial.length();

            // Generar un número aleatorio para completar el código de producto
            int longitudRestante = 12 - longitudcodigo;
            int codigoProductoInt = (int) (Math.pow(10, longitudRestante - 1) + Math.random() * (Math.pow(10, longitudRestante) - 1));
            String codigoProducto = String.valueOf(codigoProductoInt);

            String codigoFinal = codigoparcial + codigoProducto;

            StringBuilder reversedBuilder = new StringBuilder(codigoFinal);
            reversedBuilder.reverse();
            String resultadoFinalAlReves = reversedBuilder.toString();

            int suma = 0;
            
            for (int i = 0; i < resultadoFinalAlReves.length(); i++) {
                char caracter = resultadoFinalAlReves.charAt(i);
                if ((i+1) % 2 != 0) {
                    int valorNumerico = Character.getNumericValue(caracter)*3;
                    suma += valorNumerico;
                }
                if ((i+1) % 2 == 0) {
                    int valorNumerico = Character.getNumericValue(caracter)*1;
                    suma += valorNumerico;
                }
            }
            int multiploDe10Superior = (suma / 10 + 1) * 10;

            int diferencia = multiploDe10Superior - suma;
            
            String verificador = String.valueOf(diferencia);
            
            String CodigoBarra = codigoFinal + verificador;
            
            return CodigoBarra;
        } else {
            return null;
        }
    }

    public void LimpiarTable() {
        for (int i = 0; i < modelo.getRowCount(); i++) {
            modelo.removeRow(i);
            i = i - 1;
        }
    }

}
