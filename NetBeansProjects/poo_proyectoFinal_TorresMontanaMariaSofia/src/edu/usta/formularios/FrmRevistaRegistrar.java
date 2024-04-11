package edu.usta.formularios;

import edu.usta.daos.DaoCiudad;
import edu.usta.daos.DaoDepartamento;
import edu.usta.daos.DaoRevista;
import edu.usta.entidades.Ciudad;
import edu.usta.entidades.Departamento;
import edu.usta.entidades.Revista;
import java.awt.Image;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

public class FrmRevistaRegistrar extends javax.swing.JInternalFrame {

    private Integer indiceDpto;
    private Integer codDpto;
    private Integer codCiudad;

    private Map<Integer, Integer> losCodigoDptos = new HashMap<>();
    private Map<Integer, Integer> losCodigoCiudad = new HashMap<>();

    private DefaultComboBoxModel<String> miModeloDpto = new DefaultComboBoxModel<>();
    private DefaultComboBoxModel<String> miModeloCiudad = new DefaultComboBoxModel<>();

    public FrmRevistaRegistrar() {
        initComponents();

        cmbDpto.setModel(miModeloDpto);
        cmbCiudad.setModel(miModeloCiudad);

        cargarDptos();
        cargarCiudadPorDpto("", 0);
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
        establecerImagen(lblImagen, "https://cdn-icons-png.flaticon.com/128/1001/1001371.png?ga=GA1.1.881827217.1693972543&semt=ais", 40, 40);
        establecerImagen(lblImagen2, "https://cdn-icons-png.flaticon.com/128/1306/1306559.png?ga=GA1.1.881827217.1693972543&semt=ais", 80, 80);
    }

    private void cargarDptos() {
        Integer indice = 0;
        List<Departamento> arrDpto;

        DaoDepartamento miDaoDpto = new DaoDepartamento();
        arrDpto = miDaoDpto.consultar("nombre_departamento");

        losCodigoDptos.put(0, 0);

        miModeloDpto.addElement("Seleccione el departamento...");

        for (Departamento miDpto : arrDpto) {
            indice++;

            losCodigoDptos.put(indice, miDpto.getCodDepartamento());
            miModeloDpto.addElement(miDpto.getNombreDepartamento());
        }
    }

    private void cargarCiudadPorDpto(String orden, Integer codigo) {
        Integer indice = 0;
        List<Ciudad> arrCiudad;

        miModeloCiudad.removeAllElements();
        DaoCiudad miDaoCiudad = new DaoCiudad();
        arrCiudad = miDaoCiudad.consultarPorDpto(orden, codigo);

        losCodigoCiudad.put(0, 0);

        miModeloCiudad.addElement("Seleccione la ciudad...");

        for (Ciudad miCiudad : arrCiudad) {
            indice++;

            losCodigoCiudad.put(indice, miCiudad.getCodCiudad());
            miModeloCiudad.addElement(miCiudad.getNombreCiudad());
        }
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
                        JOptionPane.showMessageDialog(panelCuerpo, "Por favor seleccione una cátegoria", "Advertencia", JOptionPane.WARNING_MESSAGE);
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
        cajaNombre.setText("");
        cmbCategoria.setSelectedIndex(0);
        cmbDpto.setSelectedIndex(0);
        cmbCiudad.setSelectedIndex(0);
        cajaIssn.setText("");
        cajaNombre.requestFocus();
    }

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
        btnRegistrar = new javax.swing.JButton();
        btnLimpiar = new javax.swing.JButton();

        panelTitulo.setBackground(new java.awt.Color(131, 157, 192));

        lblTitulo.setFont(new java.awt.Font("Kristen ITC", 3, 24)); // NOI18N
        lblTitulo.setForeground(new java.awt.Color(255, 255, 255));
        lblTitulo.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblTitulo.setText("Registro de Revistas");

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
                .addGap(24, 24, 24)
                .addComponent(lblImagen, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblTitulo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(18, 18, 18)
                .addComponent(btnCerrar, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        panelTituloLayout.setVerticalGroup(
            panelTituloLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(lblImagen, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(lblTitulo, javax.swing.GroupLayout.DEFAULT_SIZE, 44, Short.MAX_VALUE)
            .addComponent(btnCerrar, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
        );

        panelCuerpo.setBackground(new java.awt.Color(199, 208, 215));

        lblNombre.setBackground(new java.awt.Color(0, 0, 0));
        lblNombre.setFont(new java.awt.Font("Segoe UI Black", 0, 12)); // NOI18N
        lblNombre.setForeground(new java.awt.Color(51, 0, 51));
        lblNombre.setText("Nombre revista:");

        lblCategoria.setFont(new java.awt.Font("Segoe UI Black", 0, 12)); // NOI18N
        lblCategoria.setForeground(new java.awt.Color(51, 0, 51));
        lblCategoria.setText("Categoría Revista:");

        lblISsn.setFont(new java.awt.Font("Segoe UI Black", 0, 12)); // NOI18N
        lblISsn.setForeground(new java.awt.Color(51, 0, 51));
        lblISsn.setText("ISSN Revista:");

        cajaNombre.setFont(new java.awt.Font("Segoe Print", 0, 12)); // NOI18N

        cajaIssn.setFont(new java.awt.Font("Segoe Print", 0, 12)); // NOI18N

        cmbCategoria.setFont(new java.awt.Font("Segoe Print", 0, 12)); // NOI18N
        cmbCategoria.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Seleccione la categoría...", "A1", "A2", "B", "C" }));

        lblDpto.setFont(new java.awt.Font("Segoe UI Black", 0, 12)); // NOI18N
        lblDpto.setForeground(new java.awt.Color(51, 0, 51));
        lblDpto.setText("Departamento:");

        lblCiudad.setFont(new java.awt.Font("Segoe UI Black", 0, 12)); // NOI18N
        lblCiudad.setForeground(new java.awt.Color(51, 0, 51));
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
                .addGap(34, 34, 34)
                .addGroup(panelCuerpoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(lblCiudad)
                    .addComponent(lblISsn)
                    .addComponent(lblCategoria)
                    .addComponent(lblNombre)
                    .addComponent(lblDpto))
                .addGap(58, 58, 58)
                .addGroup(panelCuerpoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelCuerpoLayout.createSequentialGroup()
                        .addGroup(panelCuerpoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(cmbCiudad, javax.swing.GroupLayout.PREFERRED_SIZE, 208, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(cmbDpto, javax.swing.GroupLayout.PREFERRED_SIZE, 208, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(lblImagen2, javax.swing.GroupLayout.PREFERRED_SIZE, 135, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(panelCuerpoLayout.createSequentialGroup()
                        .addGroup(panelCuerpoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(panelCuerpoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(cajaNombre, javax.swing.GroupLayout.DEFAULT_SIZE, 350, Short.MAX_VALUE)
                                .addComponent(cajaIssn, javax.swing.GroupLayout.DEFAULT_SIZE, 350, Short.MAX_VALUE))
                            .addComponent(cmbCategoria, javax.swing.GroupLayout.PREFERRED_SIZE, 350, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 20, Short.MAX_VALUE)))
                .addContainerGap())
        );
        panelCuerpoLayout.setVerticalGroup(
            panelCuerpoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelCuerpoLayout.createSequentialGroup()
                .addGap(13, 13, 13)
                .addGroup(panelCuerpoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblNombre)
                    .addComponent(cajaNombre, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(panelCuerpoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelCuerpoLayout.createSequentialGroup()
                        .addGap(27, 27, 27)
                        .addGroup(panelCuerpoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(cmbDpto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lblDpto))
                        .addGap(18, 18, 18)
                        .addGroup(panelCuerpoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(cmbCiudad, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lblCiudad))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(panelCuerpoLayout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lblImagen2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)))
                .addGroup(panelCuerpoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cmbCategoria, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblCategoria))
                .addGap(18, 18, 18)
                .addGroup(panelCuerpoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cajaIssn, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblISsn))
                .addGap(18, 18, 18))
        );

        panelBotones.setBackground(new java.awt.Color(215, 142, 40));

        btnRegistrar.setFont(new java.awt.Font("Segoe UI Black", 0, 12)); // NOI18N
        btnRegistrar.setText("Registrar");
        btnRegistrar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRegistrarActionPerformed(evt);
            }
        });

        btnLimpiar.setFont(new java.awt.Font("Segoe UI Black", 0, 12)); // NOI18N
        btnLimpiar.setText("Cancelar");
        btnLimpiar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLimpiarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout panelBotonesLayout = new javax.swing.GroupLayout(panelBotones);
        panelBotones.setLayout(panelBotonesLayout);
        panelBotonesLayout.setHorizontalGroup(
            panelBotonesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelBotonesLayout.createSequentialGroup()
                .addGap(131, 131, 131)
                .addComponent(btnRegistrar, javax.swing.GroupLayout.PREFERRED_SIZE, 131, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnLimpiar, javax.swing.GroupLayout.PREFERRED_SIZE, 124, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(153, 153, 153))
        );
        panelBotonesLayout.setVerticalGroup(
            panelBotonesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelBotonesLayout.createSequentialGroup()
                .addGap(17, 17, 17)
                .addGroup(panelBotonesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(btnLimpiar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnRegistrar, javax.swing.GroupLayout.DEFAULT_SIZE, 34, Short.MAX_VALUE))
                .addContainerGap(20, Short.MAX_VALUE))
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
                .addComponent(panelBotones, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnCerrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCerrarActionPerformed
        this.dispose();
    }//GEN-LAST:event_btnCerrarActionPerformed

    private void cmbDptoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbDptoActionPerformed
        indiceDpto = cmbDpto.getSelectedIndex();
        codDpto = losCodigoDptos.get(indiceDpto);

        if (indiceDpto == 0) {
            cmbCiudad.setEnabled(false);
            miModeloCiudad.removeAllElements();
            miModeloCiudad.addElement("Seleccione la ciudad...");
        } else {
            cmbCiudad.setEnabled(true);
            cargarCiudadPorDpto("nombre_ciudad", codDpto);
        }
    }//GEN-LAST:event_cmbDptoActionPerformed

    private void btnRegistrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRegistrarActionPerformed
        if (estaTodoBien()) {
            String nombre, issn, categoria;
            Integer indiceSeleccionadoCiu;

            nombre = cajaNombre.getText();
            categoria = cmbCategoria.getSelectedItem().toString();
            issn = cajaIssn.getText();
            indiceSeleccionadoCiu = cmbCiudad.getSelectedIndex();
            codCiudad = losCodigoCiudad.get(indiceSeleccionadoCiu);

            Ciudad objCiudad = new Ciudad(codCiudad, "", "", null, 0);
            Revista objRevista = new Revista();

            objRevista.setNombreRevista(nombre);
            objRevista.setCategoriaRevista(categoria);
            objRevista.setIssnRevista(issn);
            objRevista.setCodCiudad(objCiudad);

            DaoRevista miDaoRev = new DaoRevista();

            if (miDaoRev.registrar(objRevista)) {
                JOptionPane.showMessageDialog(panelCuerpo, "Registro exitoso", "Información", JOptionPane.INFORMATION_MESSAGE);
                limpiar();
            } else {
                JOptionPane.showMessageDialog(panelCuerpo, "No se pudo resgistrar", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }

    }//GEN-LAST:event_btnRegistrarActionPerformed

    private void btnLimpiarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLimpiarActionPerformed
        limpiar();
    }//GEN-LAST:event_btnLimpiarActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnCerrar;
    private javax.swing.JButton btnLimpiar;
    private javax.swing.JButton btnRegistrar;
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
