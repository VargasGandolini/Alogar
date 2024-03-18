/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package Vista;

import Modelo.Eventos;
import Modelo.Proveedor;
import Modelo.ProveedorDao;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;


/**
 *
 * @author alfredo
 */
public class Proveedores extends javax.swing.JFrame {
    Eventos event = new Eventos();
    Proveedor pr = new Proveedor();
    ProveedorDao PrDao = new ProveedorDao();
    DefaultTableModel modelo = new DefaultTableModel();
    DefaultTableModel tmp = new DefaultTableModel();
    
    public Proveedores() {
        initComponents();
        this.setLocationRelativeTo(null);
        txtIdProveedor.setVisible(false);
    }
    
    public void ListarProveedor() {
        List<Proveedor> ListarPr = PrDao.ListarProveedor();
        modelo = (DefaultTableModel) TableProveedor.getModel();
        Object[] ob = new Object[5];
        for (int i = 0; i < ListarPr.size(); i++) {
            ob[0] = ListarPr.get(i).getId();
            ob[1] = ListarPr.get(i).getRUT();
            ob[2] = ListarPr.get(i).getNombre();
            ob[3] = ListarPr.get(i).getTelefono();
            ob[4] = ListarPr.get(i).getDireccion();
            modelo.addRow(ob);
        }
        TableProveedor.setModel(modelo);
    }


    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel25 = new javax.swing.JLabel();
        jLabel26 = new javax.swing.JLabel();
        jLabel27 = new javax.swing.JLabel();
        jLabel28 = new javax.swing.JLabel();
        txtRUTProveedor = new javax.swing.JTextField();
        txtNombreProveedor = new javax.swing.JTextField();
        txtTelefónoProveedor = new javax.swing.JTextField();
        txtDirecciónProveedor = new javax.swing.JTextField();
        jScrollPane5 = new javax.swing.JScrollPane();
        TableProveedor = new javax.swing.JTable();
        btnGuardarProveedor = new javax.swing.JButton();
        txtEditarProveedor = new javax.swing.JButton();
        txtEliminarProveedor = new javax.swing.JButton();
        txtIdProveedor = new javax.swing.JTextField();
        PanelInicio = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        btnCerrarSesion = new javax.swing.JButton();
        jLabel9 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel25.setFont(new java.awt.Font("Liberation Sans", 3, 24)); // NOI18N
        jLabel25.setForeground(new java.awt.Color(255, 255, 255));
        jLabel25.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Img/tarjeta-de-identificacion.png"))); // NOI18N
        jLabel25.setText("RUT:");
        getContentPane().add(jLabel25, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 110, 150, -1));

        jLabel26.setFont(new java.awt.Font("Liberation Sans", 3, 24)); // NOI18N
        jLabel26.setForeground(new java.awt.Color(255, 255, 255));
        jLabel26.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Img/nombre.png"))); // NOI18N
        jLabel26.setText("Nombre:");
        getContentPane().add(jLabel26, new org.netbeans.lib.awtextra.AbsoluteConstraints(460, 110, 150, -1));

        jLabel27.setFont(new java.awt.Font("Liberation Sans", 3, 24)); // NOI18N
        jLabel27.setForeground(new java.awt.Color(255, 255, 255));
        jLabel27.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Img/llamada.png"))); // NOI18N
        jLabel27.setText("Teléfono:");
        getContentPane().add(jLabel27, new org.netbeans.lib.awtextra.AbsoluteConstraints(930, 110, 170, -1));

        jLabel28.setFont(new java.awt.Font("Liberation Sans", 3, 24)); // NOI18N
        jLabel28.setForeground(new java.awt.Color(255, 255, 255));
        jLabel28.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Img/mapa.png"))); // NOI18N
        jLabel28.setText("Dirección:");
        getContentPane().add(jLabel28, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 160, 170, -1));

        txtRUTProveedor.setFont(new java.awt.Font("Liberation Sans", 2, 24)); // NOI18N
        txtRUTProveedor.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtRUTProveedorActionPerformed(evt);
            }
        });
        txtRUTProveedor.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtRUTProveedorKeyTyped(evt);
            }
        });
        getContentPane().add(txtRUTProveedor, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 110, 230, -1));

        txtNombreProveedor.setFont(new java.awt.Font("Liberation Sans", 2, 24)); // NOI18N
        txtNombreProveedor.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtNombreProveedorKeyTyped(evt);
            }
        });
        getContentPane().add(txtNombreProveedor, new org.netbeans.lib.awtextra.AbsoluteConstraints(610, 110, 310, -1));

        txtTelefónoProveedor.setFont(new java.awt.Font("Liberation Sans", 2, 24)); // NOI18N
        txtTelefónoProveedor.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtTelefónoProveedorActionPerformed(evt);
            }
        });
        txtTelefónoProveedor.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtTelefónoProveedorKeyTyped(evt);
            }
        });
        getContentPane().add(txtTelefónoProveedor, new org.netbeans.lib.awtextra.AbsoluteConstraints(1100, 110, 210, -1));

        txtDirecciónProveedor.setFont(new java.awt.Font("Liberation Sans", 2, 24)); // NOI18N
        txtDirecciónProveedor.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtDirecciónProveedorActionPerformed(evt);
            }
        });
        txtDirecciónProveedor.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtDirecciónProveedorKeyTyped(evt);
            }
        });
        getContentPane().add(txtDirecciónProveedor, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 160, 700, -1));

        TableProveedor.setBackground(new java.awt.Color(204, 204, 204));
        TableProveedor.setFont(new java.awt.Font("Liberation Sans", 2, 24)); // NOI18N
        TableProveedor.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "RUT", "NOMBRE", "TELÉFONO", "DIRECCIÓN"
            }
        ));
        TableProveedor.setRowHeight(35);
        TableProveedor.setShowHorizontalLines(true);
        TableProveedor.setShowVerticalLines(true);
        TableProveedor.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                TableProveedorMouseClicked(evt);
            }
        });
        jScrollPane5.setViewportView(TableProveedor);
        if (TableProveedor.getColumnModel().getColumnCount() > 0) {
            TableProveedor.getColumnModel().getColumn(0).setPreferredWidth(1);
            TableProveedor.getColumnModel().getColumn(1).setPreferredWidth(20);
            TableProveedor.getColumnModel().getColumn(2).setPreferredWidth(170);
            TableProveedor.getColumnModel().getColumn(3).setPreferredWidth(10);
            TableProveedor.getColumnModel().getColumn(4).setPreferredWidth(420);
        }

        getContentPane().add(jScrollPane5, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 320, 1280, 410));

        btnGuardarProveedor.setBackground(new java.awt.Color(204, 204, 204));
        btnGuardarProveedor.setFont(new java.awt.Font("Liberation Sans", 3, 24)); // NOI18N
        btnGuardarProveedor.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Img/anadir-a-la-cola.png"))); // NOI18N
        btnGuardarProveedor.setText("Guardar Proveedor");
        btnGuardarProveedor.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 2));
        btnGuardarProveedor.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnGuardarProveedor.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btnGuardarProveedor.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGuardarProveedorActionPerformed(evt);
            }
        });
        getContentPane().add(btnGuardarProveedor, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 210, 230, -1));

        txtEditarProveedor.setBackground(new java.awt.Color(204, 204, 204));
        txtEditarProveedor.setFont(new java.awt.Font("Liberation Sans", 3, 24)); // NOI18N
        txtEditarProveedor.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Img/editar-codigo.png"))); // NOI18N
        txtEditarProveedor.setText("Editar Proveedor");
        txtEditarProveedor.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 2));
        txtEditarProveedor.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        txtEditarProveedor.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        txtEditarProveedor.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtEditarProveedorActionPerformed(evt);
            }
        });
        getContentPane().add(txtEditarProveedor, new org.netbeans.lib.awtextra.AbsoluteConstraints(500, 210, 230, -1));

        txtEliminarProveedor.setBackground(new java.awt.Color(204, 204, 204));
        txtEliminarProveedor.setFont(new java.awt.Font("Liberation Sans", 3, 24)); // NOI18N
        txtEliminarProveedor.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Img/eliminar.png"))); // NOI18N
        txtEliminarProveedor.setText("Eliminar Proveedor");
        txtEliminarProveedor.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 2));
        txtEliminarProveedor.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        txtEliminarProveedor.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        txtEliminarProveedor.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtEliminarProveedorActionPerformed(evt);
            }
        });
        getContentPane().add(txtEliminarProveedor, new org.netbeans.lib.awtextra.AbsoluteConstraints(980, 210, 230, -1));

        txtIdProveedor.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtIdProveedorActionPerformed(evt);
            }
        });
        getContentPane().add(txtIdProveedor, new org.netbeans.lib.awtextra.AbsoluteConstraints(950, 160, 10, -1));

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
        jLabel9.setText("PROVEEDORES ALOGAR");
        PanelInicio.add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 0, 940, 100));

        getContentPane().add(PanelInicio, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1350, 100));

        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Img/Fondo Virtual para Zoom Ondas Il.png"))); // NOI18N
        jLabel1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 2));
        jLabel1.setPreferredSize(new java.awt.Dimension(1380, 700));
        getContentPane().add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1350, 750));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnCerrarSesionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCerrarSesionActionPerformed
        this.dispose();
        Inicio In = new Inicio();
        In.setVisible(true);
    }//GEN-LAST:event_btnCerrarSesionActionPerformed

    private void txtRUTProveedorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtRUTProveedorActionPerformed
        // TODO add  your handling code here:
    }//GEN-LAST:event_txtRUTProveedorActionPerformed

    private void txtRUTProveedorKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtRUTProveedorKeyTyped
        // TODO add your handling code here:
        event.textKeyPress(evt);
    }//GEN-LAST:event_txtRUTProveedorKeyTyped

    private void txtNombreProveedorKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtNombreProveedorKeyTyped
        // TODO add your handling code here:
        event.textKeyPress(evt);
    }//GEN-LAST:event_txtNombreProveedorKeyTyped

    private void txtTelefónoProveedorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtTelefónoProveedorActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtTelefónoProveedorActionPerformed

    private void txtTelefónoProveedorKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtTelefónoProveedorKeyTyped
        // TODO add your handling code here:
        event.numberKeyPress(evt);
    }//GEN-LAST:event_txtTelefónoProveedorKeyTyped

    private void txtDirecciónProveedorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtDirecciónProveedorActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtDirecciónProveedorActionPerformed

    private void txtDirecciónProveedorKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtDirecciónProveedorKeyTyped
        // TODO add your handling code here:
    }//GEN-LAST:event_txtDirecciónProveedorKeyTyped

    private void TableProveedorMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_TableProveedorMouseClicked
        int fila = TableProveedor.rowAtPoint(evt.getPoint());
        txtIdProveedor.setText(TableProveedor.getValueAt(fila, 0).toString());
        txtRUTProveedor.setText(TableProveedor.getValueAt(fila, 1).toString());
        txtNombreProveedor.setText(TableProveedor.getValueAt(fila, 2).toString());
        txtTelefónoProveedor.setText(TableProveedor.getValueAt(fila, 3).toString());
        txtDirecciónProveedor.setText(TableProveedor.getValueAt(fila, 4).toString());
    }//GEN-LAST:event_TableProveedorMouseClicked

    private void btnGuardarProveedorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGuardarProveedorActionPerformed
        if (!"".equals(txtRUTProveedor.getText()) || !"".equals(txtNombreProveedor.getText()) || !"".equals(txtTelefónoProveedor.getText()) || !"".equals(txtDirecciónProveedor.getText())) {
            pr.setRUT((txtRUTProveedor.getText()));
            pr.setNombre(txtNombreProveedor.getText());
            pr.setTelefono(Integer.parseInt(txtTelefónoProveedor.getText()));
            pr.setDireccion(txtDirecciónProveedor.getText());
            PrDao.RegistrarProveedor(pr);
            JOptionPane.showMessageDialog(null, "Proveedor Registrado");
            LimpiarTable();
            ListarProveedor();
            LimpiarProveedor();
        } else {
            JOptionPane.showMessageDialog(null, "Los campos estan vacios");
        }
    }//GEN-LAST:event_btnGuardarProveedorActionPerformed

    private void txtEditarProveedorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtEditarProveedorActionPerformed
        if ("".equals(txtIdProveedor.getText())) {
            JOptionPane.showMessageDialog(null, "Seleccione una fila");
        } else {
            if (!"".equals(txtRUTProveedor.getText()) || !"".equals(txtNombreProveedor.getText()) || !"".equals(txtTelefónoProveedor.getText()) || !"".equals(txtDirecciónProveedor.getText())) {
                pr.setRUT((txtRUTProveedor.getText()));
                pr.setNombre(txtNombreProveedor.getText());
                pr.setTelefono(Integer.parseInt(txtTelefónoProveedor.getText()));
                pr.setDireccion(txtDirecciónProveedor.getText());
                pr.setId(Integer.parseInt(txtIdProveedor.getText()));
                PrDao.ModificarProveedor(pr);
                JOptionPane.showMessageDialog(null, "Proveedor Modificado");
                LimpiarTable();
                ListarProveedor();
                LimpiarProveedor();
            }
        }
    }//GEN-LAST:event_txtEditarProveedorActionPerformed

    private void txtEliminarProveedorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtEliminarProveedorActionPerformed
        if (!"".equals(txtIdProveedor.getText())) {
            int pregunta = JOptionPane.showConfirmDialog(null, "¿Estas seguro de eliminar?");
            if (pregunta == 0) {
                int id = Integer.parseInt(txtIdProveedor.getText());
                PrDao.EliminarProveedor(id);
                LimpiarTable();
                ListarProveedor();
                LimpiarProveedor();
            }
        } else {
            JOptionPane.showMessageDialog(null, "Seleccione una fila");
        }
    }//GEN-LAST:event_txtEliminarProveedorActionPerformed

    private void txtIdProveedorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtIdProveedorActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtIdProveedorActionPerformed

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
            java.util.logging.Logger.getLogger(Proveedores.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Proveedores.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Proveedores.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Proveedores.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Proveedores().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel PanelInicio;
    private javax.swing.JTable TableProveedor;
    private javax.swing.JButton btnCerrarSesion;
    private javax.swing.JButton btnGuardarProveedor;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel26;
    private javax.swing.JLabel jLabel27;
    private javax.swing.JLabel jLabel28;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JTextField txtDirecciónProveedor;
    private javax.swing.JButton txtEditarProveedor;
    private javax.swing.JButton txtEliminarProveedor;
    private javax.swing.JTextField txtIdProveedor;
    private javax.swing.JTextField txtNombreProveedor;
    private javax.swing.JTextField txtRUTProveedor;
    private javax.swing.JTextField txtTelefónoProveedor;
    // End of variables declaration//GEN-END:variables
    
    private void LimpiarTable() {
        tmp = (DefaultTableModel) TableProveedor.getModel();
        int fila = TableProveedor.getRowCount();
        for (int i = 0; i < fila; i++) {
            tmp.removeRow(0);
        }
    }

    private void LimpiarProveedor() {
        txtRUTProveedor.setText("");
        txtIdProveedor.setText("");
        txtNombreProveedor.setText("");
        txtTelefónoProveedor.setText("");
        txtDirecciónProveedor.setText("");
    }
}