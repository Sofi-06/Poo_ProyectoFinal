package edu.usta.formularios;

import com.formdev.flatlaf.FlatDarkLaf;
import java.awt.Dimension;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.JInternalFrame;
import javax.swing.JOptionPane;
import javax.swing.plaf.basic.BasicInternalFrameUI;

public class FrmPrincipal extends javax.swing.JFrame {

    public FrmPrincipal() {
        initComponents();

        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                salir();
            }

        });
    }

    private void salir() {
        int opcion;
        String textoBotones[] = {"Aceptar", "Cancelar"};

        opcion = JOptionPane.showOptionDialog(panelEscritorio, "¿Está seguro de salir?",
                "Aviso", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null,
                textoBotones, textoBotones[1]);

        if (opcion == JOptionPane.YES_OPTION) {
            System.exit(0);
        }

    }

    private void centrar(JInternalFrame ventana) {
        Dimension tamannoPanel = panelEscritorio.getSize();
        Dimension tamannoVentana = ventana.getSize();

        int posX = (tamannoPanel.width - tamannoVentana.width) / 2;
        int posY = (tamannoPanel.height - tamannoVentana.height) / 2;

        ventana.setLocation(posX, posY);
    }

    private void quitarCabecera(JInternalFrame ventana) {

        BasicInternalFrameUI interUse = (BasicInternalFrameUI) ventana.getUI();
        interUse.setNorthPane(null);
    }

    private void agregarVentanaPanel(JInternalFrame ventana) {
        if (panelEscritorio.getComponentCount() > 0) {

            panelEscritorio.removeAll();
        }

        ventana.setVisible(true);
        quitarCabecera(ventana);
        centrar(ventana);

        panelEscritorio.add(ventana);
        panelEscritorio.revalidate();
        panelEscritorio.repaint();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jMenuItem5 = new javax.swing.JMenuItem();
        panelEscritorio = new javax.swing.JDesktopPane();
        jMenuBar1 = new javax.swing.JMenuBar();
        mArchivo = new javax.swing.JMenu();
        smSalirArchivo = new javax.swing.JMenuItem();
        mDepartamento = new javax.swing.JMenu();
        smRegistrarDepartamento = new javax.swing.JMenuItem();
        smListarDepartamento = new javax.swing.JMenuItem();
        smAdminDepartamento = new javax.swing.JMenuItem();
        mCiudad = new javax.swing.JMenu();
        smRegistrarCiudad = new javax.swing.JMenuItem();
        smListarCiudad = new javax.swing.JMenuItem();
        smCiudadAdmin = new javax.swing.JMenuItem();
        mRevista = new javax.swing.JMenu();
        smRegistrarRevista = new javax.swing.JMenuItem();
        smListarRevista = new javax.swing.JMenuItem();
        smAdminRevista = new javax.swing.JMenuItem();
        mArticulo = new javax.swing.JMenu();
        smRegistrarArticulo = new javax.swing.JMenuItem();
        smListarArticulo = new javax.swing.JMenuItem();
        smArticuloAdmin = new javax.swing.JMenuItem();

        jMenuItem5.setText("jMenuItem5");

        setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);

        panelEscritorio.setBackground(new java.awt.Color(216, 176, 123));

        javax.swing.GroupLayout panelEscritorioLayout = new javax.swing.GroupLayout(panelEscritorio);
        panelEscritorio.setLayout(panelEscritorioLayout);
        panelEscritorioLayout.setHorizontalGroup(
            panelEscritorioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 659, Short.MAX_VALUE)
        );
        panelEscritorioLayout.setVerticalGroup(
            panelEscritorioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 435, Short.MAX_VALUE)
        );

        mArchivo.setText("Archivo");

        smSalirArchivo.setText("Salir");
        smSalirArchivo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                smSalirArchivoActionPerformed(evt);
            }
        });
        mArchivo.add(smSalirArchivo);

        jMenuBar1.add(mArchivo);

        mDepartamento.setText("Departamentos");

        smRegistrarDepartamento.setText("Registrar Departamentos");
        smRegistrarDepartamento.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                smRegistrarDepartamentoActionPerformed(evt);
            }
        });
        mDepartamento.add(smRegistrarDepartamento);

        smListarDepartamento.setText("Listar Departamentos");
        smListarDepartamento.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                smListarDepartamentoActionPerformed(evt);
            }
        });
        mDepartamento.add(smListarDepartamento);

        smAdminDepartamento.setText("Administrar Departamentos");
        smAdminDepartamento.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                smAdminDepartamentoActionPerformed(evt);
            }
        });
        mDepartamento.add(smAdminDepartamento);

        jMenuBar1.add(mDepartamento);

        mCiudad.setText("Ciudades");

        smRegistrarCiudad.setText("Registrar Ciudades");
        smRegistrarCiudad.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                smRegistrarCiudadActionPerformed(evt);
            }
        });
        mCiudad.add(smRegistrarCiudad);

        smListarCiudad.setText("Listar Ciudades");
        smListarCiudad.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                smListarCiudadActionPerformed(evt);
            }
        });
        mCiudad.add(smListarCiudad);

        smCiudadAdmin.setText("Administrar Ciudades");
        smCiudadAdmin.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                smCiudadAdminActionPerformed(evt);
            }
        });
        mCiudad.add(smCiudadAdmin);

        jMenuBar1.add(mCiudad);

        mRevista.setText("Revistas");

        smRegistrarRevista.setText("Registrar Revistas");
        smRegistrarRevista.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                smRegistrarRevistaActionPerformed(evt);
            }
        });
        mRevista.add(smRegistrarRevista);

        smListarRevista.setText("Listar Revistas");
        smListarRevista.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                smListarRevistaActionPerformed(evt);
            }
        });
        mRevista.add(smListarRevista);

        smAdminRevista.setText("Administrar Revistas");
        smAdminRevista.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                smAdminRevistaActionPerformed(evt);
            }
        });
        mRevista.add(smAdminRevista);

        jMenuBar1.add(mRevista);

        mArticulo.setText("Articulos");

        smRegistrarArticulo.setText("Registrar Articulos");
        smRegistrarArticulo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                smRegistrarArticuloActionPerformed(evt);
            }
        });
        mArticulo.add(smRegistrarArticulo);

        smListarArticulo.setText("Listar Articulos");
        smListarArticulo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                smListarArticuloActionPerformed(evt);
            }
        });
        mArticulo.add(smListarArticulo);

        smArticuloAdmin.setText("Administrar Articulos");
        smArticuloAdmin.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                smArticuloAdminActionPerformed(evt);
            }
        });
        mArticulo.add(smArticuloAdmin);

        jMenuBar1.add(mArticulo);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(panelEscritorio)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(panelEscritorio)
                .addContainerGap())
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void smRegistrarCiudadActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_smRegistrarCiudadActionPerformed
        FrmCiudadRegistrar registrarCiudad = new FrmCiudadRegistrar();
        agregarVentanaPanel(registrarCiudad);
    }//GEN-LAST:event_smRegistrarCiudadActionPerformed

    private void smListarCiudadActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_smListarCiudadActionPerformed
        FrmCiudadListar listarCiudad = new FrmCiudadListar();
        agregarVentanaPanel(listarCiudad);
    }//GEN-LAST:event_smListarCiudadActionPerformed

    private void smRegistrarDepartamentoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_smRegistrarDepartamentoActionPerformed
        FrmDeptoRegistrar registrarDepto = new FrmDeptoRegistrar();
        agregarVentanaPanel(registrarDepto);
    }//GEN-LAST:event_smRegistrarDepartamentoActionPerformed

    private void smSalirArchivoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_smSalirArchivoActionPerformed
        salir();
    }//GEN-LAST:event_smSalirArchivoActionPerformed

    private void smListarDepartamentoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_smListarDepartamentoActionPerformed
        FrmDeptoListar listarDpto = new FrmDeptoListar();
        agregarVentanaPanel(listarDpto);
    }//GEN-LAST:event_smListarDepartamentoActionPerformed

    private void smAdminDepartamentoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_smAdminDepartamentoActionPerformed
        FrmDptoAdmin adminDpto = new FrmDptoAdmin();
        agregarVentanaPanel(adminDpto);
    }//GEN-LAST:event_smAdminDepartamentoActionPerformed

    private void smCiudadAdminActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_smCiudadAdminActionPerformed
        FrmCiudadAdmin admninCiudad = new FrmCiudadAdmin();
        agregarVentanaPanel(admninCiudad);
    }//GEN-LAST:event_smCiudadAdminActionPerformed

    private void smRegistrarRevistaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_smRegistrarRevistaActionPerformed
        FrmRevistaRegistrar ventanaRevista = new FrmRevistaRegistrar();
        agregarVentanaPanel(ventanaRevista);
    }//GEN-LAST:event_smRegistrarRevistaActionPerformed

    private void smListarRevistaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_smListarRevistaActionPerformed
        FrmRevistaListar revista = new FrmRevistaListar();
        agregarVentanaPanel(revista);
    }//GEN-LAST:event_smListarRevistaActionPerformed

    private void smAdminRevistaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_smAdminRevistaActionPerformed
        FrmRevistaAdmin administraRevista = new FrmRevistaAdmin();
        agregarVentanaPanel(administraRevista);
    }//GEN-LAST:event_smAdminRevistaActionPerformed

    private void smRegistrarArticuloActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_smRegistrarArticuloActionPerformed
        FrmArticuloRegistrar ventanaArticulo = new FrmArticuloRegistrar();
        agregarVentanaPanel(ventanaArticulo);
    }//GEN-LAST:event_smRegistrarArticuloActionPerformed

    private void smListarArticuloActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_smListarArticuloActionPerformed
        FrmArticuloListar articulo = new FrmArticuloListar();
        agregarVentanaPanel(articulo);
    }//GEN-LAST:event_smListarArticuloActionPerformed

    private void smArticuloAdminActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_smArticuloAdminActionPerformed
        FrmArticuloAdmin administraArticulo = new FrmArticuloAdmin();
        agregarVentanaPanel(administraArticulo);
    }//GEN-LAST:event_smArticuloAdminActionPerformed

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

            javax.swing.UIManager.setLookAndFeel(new FlatDarkLaf());
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(FrmPrincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new FrmPrincipal().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenuItem jMenuItem5;
    private javax.swing.JMenu mArchivo;
    private javax.swing.JMenu mArticulo;
    private javax.swing.JMenu mCiudad;
    private javax.swing.JMenu mDepartamento;
    private javax.swing.JMenu mRevista;
    private javax.swing.JDesktopPane panelEscritorio;
    private javax.swing.JMenuItem smAdminDepartamento;
    private javax.swing.JMenuItem smAdminRevista;
    private javax.swing.JMenuItem smArticuloAdmin;
    private javax.swing.JMenuItem smCiudadAdmin;
    private javax.swing.JMenuItem smListarArticulo;
    private javax.swing.JMenuItem smListarCiudad;
    private javax.swing.JMenuItem smListarDepartamento;
    private javax.swing.JMenuItem smListarRevista;
    private javax.swing.JMenuItem smRegistrarArticulo;
    private javax.swing.JMenuItem smRegistrarCiudad;
    private javax.swing.JMenuItem smRegistrarDepartamento;
    private javax.swing.JMenuItem smRegistrarRevista;
    private javax.swing.JMenuItem smSalirArchivo;
    // End of variables declaration//GEN-END:variables
}
