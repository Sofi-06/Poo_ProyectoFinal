package edu.usta.formularios;

import edu.usta.configuracion.Dominio;
import edu.usta.daos.DaoCiudad;
import edu.usta.daos.DaoDepartamento;
import edu.usta.daos.DaoRevista;
import edu.usta.entidades.Ciudad;
import edu.usta.entidades.Departamento;
import edu.usta.entidades.Revista;
import java.awt.Dialog;
import java.awt.Image;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

public class FrmRevistaEditar extends javax.swing.JDialog {

    private final Revista objActualizar;
    private Integer indiceCat;
    private Integer indiceDpto;
    private Integer codigoDpto;
    private Integer indiceCiu;

    private Map<Integer, String> codigosCategorias = new HashMap<Integer, String>();
    private final DefaultComboBoxModel<String> miModeloCombo = new DefaultComboBoxModel<>();

    private Map<Integer, Integer> losCodigoDptos = new HashMap<>();
    private Map<Integer, Integer> losCodigoCiudad = new HashMap<>();

    private DefaultComboBoxModel<String> miModeloDpto = new DefaultComboBoxModel<>();
    private DefaultComboBoxModel<String> miModeloCiudad = new DefaultComboBoxModel<>();

    public FrmRevistaEditar(java.awt.Frame parent, boolean modal, Revista objExterno) {
        super(parent, modal);
        quitarHeader(this);
        initComponents();

        objActualizar = objExterno;
        cmbCategoria.setModel(miModeloCombo);
        cmbCiudad.setModel(miModeloCiudad);
        cmbDpto.setModel(miModeloDpto);

        cajaNombre.setText("" + objExterno.getNombreRevista());
        cajaIssn.setText("" + objExterno.getIssnRevista());

        cargarCategorias();

        cargarDptos();
        cargarCiudadPorDpto("", objExterno.getCodCiudad().getCodDepartamento().getCodDepartamento());
        imagenes();
    }

    private void establecerImagen(JLabel label, String url, int width, int height) {
        try {
            URL imgU = new URL(url);
            Image boxesImg = new ImageIcon(imgU).getImage().getScaledInstance(width, height, Image.SCALE_SMOOTH);
            ImageIcon iconoBox = new ImageIcon(boxesImg);
            label.setIcon(iconoBox);
        } catch (MalformedURLException ex) {
            JOptionPane.showMessageDialog(null, "La URL de la imagen no es válida.");
        }
    }

    private void imagenes() {
        establecerImagen(lblImagen, "https://cdn-icons-png.flaticon.com/128/1827/1827933.png?ga=GA1.1.881827217.1693972543&semt=ais", 40, 40);
        establecerImagen(lblImagen2, "https://cdn-icons-png.flaticon.com/128/8176/8176078.png?ga=GA1.1.881827217.1693972543&semt=ais", 80, 80);
    }

    private void cargarCategorias() {
        indiceCat = 0;
        codigosCategorias = Dominio.ARREGLO_CATEGORIAS;

        for (Map.Entry<Integer, String> entry : codigosCategorias.entrySet()) {
            Integer key = entry.getKey();
            String value = entry.getValue();

            miModeloCombo.addElement(value);

            if (Objects.equals(objActualizar.getCategoriaRevista(), value)) {
                indiceCat = key;
            }
        }
        cmbCategoria.setSelectedIndex(indiceCat);
    }

    private void cargarDptos() {
        Integer indice = 0;
        indiceDpto = 0;
        List<Departamento> arrDpto;

        DaoDepartamento miDaoDpto = new DaoDepartamento();
        arrDpto = miDaoDpto.consultar("nombre_departamento");

        losCodigoDptos.put(0, 0);

        miModeloDpto.addElement("Seleccione el departamento...");

        for (Departamento miDpto : arrDpto) {
            indice++;

            losCodigoDptos.put(indice, miDpto.getCodDepartamento());
            miModeloDpto.addElement(miDpto.getNombreDepartamento());

            if (Objects.equals(objActualizar.getCodCiudad().getCodDepartamento().getCodDepartamento(), miDpto.getCodDepartamento())) {
                indiceDpto = indice;
                System.out.println("indice: " + indiceDpto);
            }
        }
        cmbDpto.setSelectedIndex(indiceDpto);
    }

    private void cargarCiudadPorDpto(String orden, Integer codigo) {
        Integer indice = 0;
        List<Ciudad> arrCiudad;
        indiceCiu = 0;

        miModeloCiudad.removeAllElements();
        DaoCiudad miDaoCiudad = new DaoCiudad();
        arrCiudad = miDaoCiudad.consultarPorDpto(orden, codigo);

        losCodigoCiudad.put(0, 0);

        miModeloCiudad.addElement("Seleccione la ciudad...");

        for (Ciudad miCiudad : arrCiudad) {
            indice++;

            losCodigoCiudad.put(indice, miCiudad.getCodCiudad());
            miModeloCiudad.addElement(miCiudad.getNombreCiudad());

            if (Objects.equals(objActualizar.getCodCiudad().getCodCiudad(), miCiudad.getCodCiudad())) {
                indiceCiu = indice;
            }
        }
        cmbCiudad.setSelectedIndex(indiceCiu);
    }

    private void quitarHeader(JDialog dialog) {

        Dialog dialogAsDialog = (Dialog) dialog;

        dialogAsDialog.setUndecorated(true);
    }

    private Boolean estaTodoBien() {
        String nombre, issn;

        Boolean bandera = true;

        nombre = cajaNombre.getText();
        if (nombre.equals("")) {
            cajaNombre.requestFocus();
            bandera = false;
            JOptionPane.showMessageDialog(panelCuerpo, "Digite el nombre de la revista", "Advertencia", JOptionPane.WARNING_MESSAGE);
        } else {
            if (cmbDpto.getSelectedIndex() == 0) {
                bandera = false;
                JOptionPane.showMessageDialog(panelCuerpo, "Por favor seleccione un departamento", "Advertencia", JOptionPane.WARNING_MESSAGE);
            } else {
                if (cmbCiudad.getSelectedIndex() == 0) {
                    bandera = false;
                    JOptionPane.showMessageDialog(panelCuerpo, "Por favor seleccione una ciudad", "Advertencia", JOptionPane.WARNING_MESSAGE);
                } else {
                    if (cmbCategoria.getSelectedIndex() == 0) {
                        bandera = false;
                        JOptionPane.showMessageDialog(panelCuerpo, "Por favor seleccione una categoria", "Advertencia", JOptionPane.WARNING_MESSAGE);
                    } else {
                        issn = cajaIssn.getText();

                        if (issn.equals("")) {
                            cajaIssn.requestFocus();
                            bandera = false;
                            JOptionPane.showMessageDialog(panelCuerpo, "Digite el issn de la revista", "Advertencia", JOptionPane.WARNING_MESSAGE);
                        }
                    }
                }
            }
        }

        return bandera;
    }

    private void limpiar() {
        cajaNombre.setText("" + objActualizar.getNombreRevista());
        cajaIssn.setText("" + objActualizar.getIssnRevista());
        cmbCategoria.setSelectedIndex(indiceCat);
        cmbCiudad.setSelectedIndex(indiceCiu);
        cmbDpto.setSelectedIndex(indiceDpto);
        cajaNombre.requestFocus();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        panelTitulo = new javax.swing.JPanel();
        lblTitulo = new javax.swing.JLabel();
        btnCerrar = new javax.swing.JButton();
        lblImagen = new javax.swing.JLabel();
        panelCuerpo = new javax.swing.JPanel();
        lblNombre = new javax.swing.JLabel();
        lblCategoria = new javax.swing.JLabel();
        lblISsn = new javax.swing.JLabel();
        cajaNombre = new javax.swing.JTextField();
        cajaIssn = new javax.swing.JTextField();
        cmbCategoria = new javax.swing.JComboBox<>();
        lblDpto = new javax.swing.JLabel();
        lblCiudad = new javax.swing.JLabel();
        cmbDpto = new javax.swing.JComboBox<>();
        cmbCiudad = new javax.swing.JComboBox<>();
        lblImagen2 = new javax.swing.JLabel();
        panelBotones = new javax.swing.JPanel();
        btnEditar = new javax.swing.JButton();
        btnCancelar = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        panelTitulo.setBackground(new java.awt.Color(131, 157, 192));

        lblTitulo.setFont(new java.awt.Font("Kristen ITC", 0, 24)); // NOI18N
        lblTitulo.setForeground(new java.awt.Color(255, 255, 255));
        lblTitulo.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblTitulo.setText("Editar Revistas");

        btnCerrar.setBackground(new java.awt.Color(255, 204, 204));
        btnCerrar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/edu/usta/iconos/iconito2.jpg"))); // NOI18N
        btnCerrar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnCerrar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCerrarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout panelTituloLayout = new javax.swing.GroupLayout(panelTitulo);
        panelTitulo.setLayout(panelTituloLayout);
        panelTituloLayout.setHorizontalGroup(
            panelTituloLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelTituloLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(lblImagen, javax.swing.GroupLayout.PREFERRED_SIZE, 58, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(lblTitulo, javax.swing.GroupLayout.PREFERRED_SIZE, 343, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(49, 49, 49)
                .addComponent(btnCerrar, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        panelTituloLayout.setVerticalGroup(
            panelTituloLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(btnCerrar, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
            .addGroup(panelTituloLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblTitulo)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addComponent(lblImagen, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        panelCuerpo.setBackground(new java.awt.Color(199, 208, 215));

        lblNombre.setForeground(new java.awt.Color(255, 255, 255));
        lblNombre.setText("Nombre revista:");

        lblCategoria.setForeground(new java.awt.Color(255, 255, 255));
        lblCategoria.setText("Categoría Revista:");

        lblISsn.setForeground(new java.awt.Color(255, 255, 255));
        lblISsn.setText("ISSN Revista:");

        cajaNombre.setFont(new java.awt.Font("Segoe Print", 0, 12)); // NOI18N

        cajaIssn.setFont(new java.awt.Font("Segoe Print", 0, 12)); // NOI18N

        cmbCategoria.setFont(new java.awt.Font("Segoe Print", 0, 12)); // NOI18N

        lblDpto.setForeground(new java.awt.Color(255, 255, 255));
        lblDpto.setText("Departamento:");

        lblCiudad.setForeground(new java.awt.Color(255, 255, 255));
        lblCiudad.setText("Ciudad:");

        cmbDpto.setFont(new java.awt.Font("Segoe Print", 0, 12)); // NOI18N
        cmbDpto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbDptoActionPerformed(evt);
            }
        });

        cmbCiudad.setFont(new java.awt.Font("Segoe Print", 0, 12)); // NOI18N

        javax.swing.GroupLayout panelCuerpoLayout = new javax.swing.GroupLayout(panelCuerpo);
        panelCuerpo.setLayout(panelCuerpoLayout);
        panelCuerpoLayout.setHorizontalGroup(
            panelCuerpoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelCuerpoLayout.createSequentialGroup()
                .addGroup(panelCuerpoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelCuerpoLayout.createSequentialGroup()
                        .addGap(34, 34, 34)
                        .addGroup(panelCuerpoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(lblCategoria)
                            .addComponent(lblISsn))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelCuerpoLayout.createSequentialGroup()
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(panelCuerpoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblNombre, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(lblDpto, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(lblCiudad, javax.swing.GroupLayout.Alignment.TRAILING))))
                .addGap(58, 58, 58)
                .addGroup(panelCuerpoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelCuerpoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addComponent(cajaNombre, javax.swing.GroupLayout.PREFERRED_SIZE, 350, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(cajaIssn, javax.swing.GroupLayout.PREFERRED_SIZE, 350, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(panelCuerpoLayout.createSequentialGroup()
                        .addGroup(panelCuerpoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(cmbDpto, javax.swing.GroupLayout.PREFERRED_SIZE, 208, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(cmbCiudad, javax.swing.GroupLayout.PREFERRED_SIZE, 208, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lblImagen2, javax.swing.GroupLayout.PREFERRED_SIZE, 135, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(cmbCategoria, javax.swing.GroupLayout.PREFERRED_SIZE, 344, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        panelCuerpoLayout.setVerticalGroup(
            panelCuerpoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelCuerpoLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelCuerpoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblNombre)
                    .addComponent(cajaNombre, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(panelCuerpoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelCuerpoLayout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addGroup(panelCuerpoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(cmbDpto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lblDpto))
                        .addGap(18, 18, 18)
                        .addGroup(panelCuerpoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(cmbCiudad, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lblCiudad)))
                    .addGroup(panelCuerpoLayout.createSequentialGroup()
                        .addGap(7, 7, 7)
                        .addComponent(lblImagen2, javax.swing.GroupLayout.PREFERRED_SIZE, 109, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(panelCuerpoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblCategoria)
                    .addComponent(cmbCategoria, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(panelCuerpoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cajaIssn, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblISsn))
                .addGap(24, 24, 24))
        );

        panelBotones.setBackground(new java.awt.Color(215, 142, 40));

        btnEditar.setFont(new java.awt.Font("Segoe UI Black", 0, 12)); // NOI18N
        btnEditar.setText("Editar");
        btnEditar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEditarActionPerformed(evt);
            }
        });

        btnCancelar.setFont(new java.awt.Font("Segoe UI Black", 0, 12)); // NOI18N
        btnCancelar.setText("Cancelar");
        btnCancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCancelarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout panelBotonesLayout = new javax.swing.GroupLayout(panelBotones);
        panelBotones.setLayout(panelBotonesLayout);
        panelBotonesLayout.setHorizontalGroup(
            panelBotonesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelBotonesLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnEditar, javax.swing.GroupLayout.PREFERRED_SIZE, 99, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(77, 77, 77)
                .addComponent(btnCancelar, javax.swing.GroupLayout.PREFERRED_SIZE, 94, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(168, 168, 168))
        );
        panelBotonesLayout.setVerticalGroup(
            panelBotonesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelBotonesLayout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addGroup(panelBotonesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnEditar, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnCancelar, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(15, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panelCuerpo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(panelTitulo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(panelBotones, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(panelTitulo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(panelCuerpo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(panelBotones, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void btnCerrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCerrarActionPerformed
        this.dispose();// parab cerrar solo la ventana y no todo el sistema
    }//GEN-LAST:event_btnCerrarActionPerformed

    private void btnEditarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEditarActionPerformed
        if (estaTodoBien()) {
            String nombre, issn, categoria;

            nombre = cajaNombre.getText();
            categoria = cmbCategoria.getSelectedItem().toString();
            issn = cajaIssn.getText();
            Integer indiceSeleccionado = cmbCiudad.getSelectedIndex();
            Integer codSeleccionado = losCodigoCiudad.get(indiceSeleccionado);

            Ciudad objCiudad = new Ciudad(codSeleccionado, "", "", null, 0);

            objActualizar.setNombreRevista(nombre);
            objActualizar.setCategoriaRevista(categoria);
            objActualizar.setIssnRevista(issn);
            objActualizar.setCodCiudad(objCiudad);

            DaoRevista miDaoRev = new DaoRevista();

            if (miDaoRev.actualizar(objActualizar)) {
                JOptionPane.showMessageDialog(panelCuerpo, "Edicion exitosa", "Información", JOptionPane.INFORMATION_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(panelCuerpo, "No se pudo editar", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }

    }//GEN-LAST:event_btnEditarActionPerformed

    private void btnCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelarActionPerformed
        limpiar();
    }//GEN-LAST:event_btnCancelarActionPerformed

    private void cmbDptoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbDptoActionPerformed
        indiceDpto = cmbDpto.getSelectedIndex();
        codigoDpto = losCodigoDptos.get(indiceDpto);

        if (indiceDpto == 0) {
            cmbCiudad.setEnabled(false);
            miModeloCiudad.removeAllElements();
            miModeloCiudad.addElement("Seleccione la ciudad...");
        } else {
            cmbCiudad.setEnabled(true);
            cargarCiudadPorDpto("nombre_ciudad", codigoDpto);
        }
    }//GEN-LAST:event_cmbDptoActionPerformed

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
            java.util.logging.Logger.getLogger(FrmRevistaEditar.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(FrmRevistaEditar.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(FrmRevistaEditar.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(FrmRevistaEditar.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                FrmRevistaEditar dialog = new FrmRevistaEditar(new javax.swing.JFrame(), true, new Revista());
                dialog.addWindowListener(new java.awt.event.WindowAdapter() {
                    @Override
                    public void windowClosing(java.awt.event.WindowEvent e) {
                        System.exit(0);
                    }
                });
                dialog.setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnCancelar;
    private javax.swing.JButton btnCerrar;
    private javax.swing.JButton btnEditar;
    private javax.swing.JTextField cajaIssn;
    private javax.swing.JTextField cajaNombre;
    private javax.swing.JComboBox<String> cmbCategoria;
    private javax.swing.JComboBox<String> cmbCiudad;
    private javax.swing.JComboBox<String> cmbDpto;
    private javax.swing.JLabel lblCategoria;
    private javax.swing.JLabel lblCiudad;
    private javax.swing.JLabel lblDpto;
    private javax.swing.JLabel lblISsn;
    private javax.swing.JLabel lblImagen;
    private javax.swing.JLabel lblImagen2;
    private javax.swing.JLabel lblNombre;
    private javax.swing.JLabel lblTitulo;
    private javax.swing.JPanel panelBotones;
    private javax.swing.JPanel panelCuerpo;
    private javax.swing.JPanel panelTitulo;
    // End of variables declaration//GEN-END:variables
}
