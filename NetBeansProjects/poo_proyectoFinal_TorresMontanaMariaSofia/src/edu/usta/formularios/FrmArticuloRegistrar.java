package edu.usta.formularios;

import edu.usta.daos.DaoArticulo;
import edu.usta.daos.DaoRevista;
import edu.usta.entidades.Articulo;
import edu.usta.entidades.Revista;
import edu.usta.utilidades.MiRadio;
import java.awt.Image;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

public class FrmArticuloRegistrar extends javax.swing.JInternalFrame {

    private Map<Integer, Integer> losCodigosRevistas = new HashMap<>();
    private DefaultComboBoxModel miModeloCombo = new DefaultComboBoxModel();

    public FrmArticuloRegistrar() {
        initComponents();

        cmbRevista.setModel(miModeloCombo);
        cargarRevistas();
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
        establecerImagen(lblImagen2, "https://cdn-icons-png.flaticon.com/128/1295/1295909.png?ga=GA1.1.881827217.1693972543&semt=ais", 80, 80);

    }
    
    

    private void cargarRevistas() {

        List<Revista> arrRevistas;
        Integer indice = 0;

        DaoRevista miDao = new DaoRevista();
        arrRevistas = miDao.consultar("");

        losCodigosRevistas.put(0, 0);

        miModeloCombo.addElement("Seleccione la revista...");

        for (Revista miRevista : arrRevistas) {
            indice++;

            losCodigosRevistas.put(indice, miRevista.getCodRevista());
            miModeloCombo.addElement(miRevista.getNombreRevista());
        }
    }

    private Boolean estaTodoBien() {
        String nombre;
        Date fecha;
        Short paginas;
        Integer seleccionado;

        Boolean bandera = true;

        seleccionado = cmbRevista.getSelectedIndex();
        if (seleccionado == 0) {
            bandera = false;
            JOptionPane.showMessageDialog(panelCuerpo, "Seleccione una revista", "Advertencia", JOptionPane.WARNING_MESSAGE);
        } else {
            nombre = cajaNombre.getText();
            if (nombre.equals("")) {
                cajaNombre.requestFocus();
                bandera = false;
                JOptionPane.showMessageDialog(panelCuerpo, "Digite el nombre del articulo", "Advertencia", JOptionPane.WARNING_MESSAGE);
            } else {
                if (MiRadio.estaSeleccionado(grupoTipo) == false) {
                    bandera = false;
                    JOptionPane.showMessageDialog(panelCuerpo, "Seleccione el tipo", "Advertencia", JOptionPane.WARNING_MESSAGE);

                } else {
                    fecha = fArticulo.getDate();
                    if (fecha == null) {
                        bandera = false;
                        JOptionPane.showMessageDialog(panelCuerpo, "Seleccione la fecha de la publicación", "Advertencia", JOptionPane.WARNING_MESSAGE);

                    } else {
                        try {

                            paginas = Short.valueOf(cajaPaginas.getText());
                            if (paginas < 0) {
                                cajaPaginas.requestFocus();
                                bandera = false;
                                JOptionPane.showMessageDialog(panelCuerpo, "Digite numeros positivos", "Advertencia", JOptionPane.WARNING_MESSAGE);
                            }
                        } catch (NumberFormatException e) {
                            bandera = false;
                            JOptionPane.showMessageDialog(panelCuerpo, "Digite numero de páginas", "Advertencia", JOptionPane.WARNING_MESSAGE);
                        }
                    }

                }
            }
        }

        return bandera;
    }

    private void limpiar() {
        cajaNombre.setText("");
        fArticulo.setDate(null);
        cajaPaginas.setText("");
        cmbRevista.setSelectedIndex(0);
        grupoTipo.clearSelection();
        cajaNombre.requestFocus();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        grupoTipo = new javax.swing.ButtonGroup();
        panelTitulo = new javax.swing.JPanel();
        lblTitulo = new javax.swing.JLabel();
        btnCerrar = new javax.swing.JButton();
        lblImagen = new javax.swing.JLabel();
        panelCuerpo = new javax.swing.JPanel();
        lblNombre = new javax.swing.JLabel();
        lblPaginas = new javax.swing.JLabel();
        cajaNombre = new javax.swing.JTextField();
        cajaPaginas = new javax.swing.JTextField();
        lblRevista = new javax.swing.JLabel();
        cmbRevista = new javax.swing.JComboBox<>();
        lblFecha = new javax.swing.JLabel();
        fArticulo = new com.toedter.calendar.JDateChooser();
        lblTipo = new javax.swing.JLabel();
        radioCorto = new javax.swing.JRadioButton();
        radioRev = new javax.swing.JRadioButton();
        radioInves = new javax.swing.JRadioButton();
        radioCaso = new javax.swing.JRadioButton();
        lblImagen2 = new javax.swing.JLabel();
        panelBotones = new javax.swing.JPanel();
        btnRegistrar = new javax.swing.JButton();
        btnRegistrar1 = new javax.swing.JButton();

        panelTitulo.setBackground(new java.awt.Color(131, 157, 192));

        lblTitulo.setFont(new java.awt.Font("Kristen ITC", 0, 24)); // NOI18N
        lblTitulo.setForeground(new java.awt.Color(255, 255, 255));
        lblTitulo.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblTitulo.setText("Registro de Articulos");

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
                .addGap(40, 40, 40)
                .addComponent(lblImagen, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblTitulo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(41, 41, 41)
                .addComponent(btnCerrar, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(9, 9, 9))
        );
        panelTituloLayout.setVerticalGroup(
            panelTituloLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(btnCerrar, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
            .addComponent(lblImagen, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(panelTituloLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblTitulo)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        panelCuerpo.setBackground(new java.awt.Color(199, 208, 215));

        lblNombre.setFont(new java.awt.Font("Segoe UI Black", 0, 12)); // NOI18N
        lblNombre.setForeground(new java.awt.Color(51, 0, 51));
        lblNombre.setText("Nombre Articulo:");

        lblPaginas.setFont(new java.awt.Font("Segoe UI Black", 0, 12)); // NOI18N
        lblPaginas.setForeground(new java.awt.Color(51, 0, 51));
        lblPaginas.setText("Número páginas:");

        cajaNombre.setFont(new java.awt.Font("Segoe Print", 0, 12)); // NOI18N
        cajaNombre.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cajaNombreActionPerformed(evt);
            }
        });

        cajaPaginas.setFont(new java.awt.Font("Segoe Print", 0, 12)); // NOI18N
        cajaPaginas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cajaPaginasActionPerformed(evt);
            }
        });

        lblRevista.setFont(new java.awt.Font("Segoe UI Black", 0, 12)); // NOI18N
        lblRevista.setForeground(new java.awt.Color(51, 0, 51));
        lblRevista.setText("Revista:");

        cmbRevista.setFont(new java.awt.Font("Segoe Print", 0, 12)); // NOI18N

        lblFecha.setFont(new java.awt.Font("Segoe UI Black", 0, 12)); // NOI18N
        lblFecha.setForeground(new java.awt.Color(51, 0, 51));
        lblFecha.setText("Fecha Publicación:");

        fArticulo.setBackground(new java.awt.Color(255, 255, 255));
        fArticulo.setForeground(new java.awt.Color(255, 255, 255));
        fArticulo.setFont(new java.awt.Font("Segoe Print", 0, 12)); // NOI18N

        lblTipo.setFont(new java.awt.Font("Segoe UI Black", 0, 12)); // NOI18N
        lblTipo.setForeground(new java.awt.Color(51, 0, 51));
        lblTipo.setText("Tipo:");

        radioCorto.setBackground(new java.awt.Color(199, 208, 215));
        grupoTipo.add(radioCorto);
        radioCorto.setFont(new java.awt.Font("Segoe UI Black", 0, 12)); // NOI18N
        radioCorto.setForeground(new java.awt.Color(51, 0, 51));
        radioCorto.setText("Corto");
        radioCorto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                radioCortoActionPerformed(evt);
            }
        });

        radioRev.setBackground(new java.awt.Color(199, 208, 215));
        grupoTipo.add(radioRev);
        radioRev.setFont(new java.awt.Font("Segoe UI Black", 0, 12)); // NOI18N
        radioRev.setForeground(new java.awt.Color(51, 0, 51));
        radioRev.setText("Revisión");
        radioRev.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                radioRevActionPerformed(evt);
            }
        });

        radioInves.setBackground(new java.awt.Color(199, 208, 215));
        grupoTipo.add(radioInves);
        radioInves.setFont(new java.awt.Font("Segoe UI Black", 0, 12)); // NOI18N
        radioInves.setForeground(new java.awt.Color(51, 0, 51));
        radioInves.setText("Investigación");
        radioInves.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                radioInvesActionPerformed(evt);
            }
        });

        radioCaso.setBackground(new java.awt.Color(199, 208, 215));
        grupoTipo.add(radioCaso);
        radioCaso.setFont(new java.awt.Font("Segoe UI Black", 0, 12)); // NOI18N
        radioCaso.setForeground(new java.awt.Color(51, 0, 51));
        radioCaso.setText("Reporte de caso");
        radioCaso.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                radioCasoActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout panelCuerpoLayout = new javax.swing.GroupLayout(panelCuerpo);
        panelCuerpo.setLayout(panelCuerpoLayout);
        panelCuerpoLayout.setHorizontalGroup(
            panelCuerpoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelCuerpoLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelCuerpoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelCuerpoLayout.createSequentialGroup()
                        .addGroup(panelCuerpoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(panelCuerpoLayout.createSequentialGroup()
                                .addComponent(lblFecha)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelCuerpoLayout.createSequentialGroup()
                                .addGap(0, 0, Short.MAX_VALUE)
                                .addGroup(panelCuerpoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelCuerpoLayout.createSequentialGroup()
                                        .addComponent(lblPaginas)
                                        .addGap(4, 4, 4))
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelCuerpoLayout.createSequentialGroup()
                                        .addComponent(lblRevista, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)))))
                        .addGroup(panelCuerpoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(cajaPaginas, javax.swing.GroupLayout.PREFERRED_SIZE, 106, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(fArticulo, javax.swing.GroupLayout.PREFERRED_SIZE, 345, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(cmbRevista, javax.swing.GroupLayout.PREFERRED_SIZE, 390, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(323, 323, 323))
                    .addGroup(panelCuerpoLayout.createSequentialGroup()
                        .addGroup(panelCuerpoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(lblTipo)
                            .addComponent(lblNombre))
                        .addGap(18, 18, 18)
                        .addGroup(panelCuerpoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(cajaNombre, javax.swing.GroupLayout.PREFERRED_SIZE, 345, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(radioInves)
                            .addComponent(radioRev)
                            .addComponent(radioCaso)
                            .addComponent(radioCorto))
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
            .addGroup(panelCuerpoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(panelCuerpoLayout.createSequentialGroup()
                    .addGap(361, 361, 361)
                    .addComponent(lblImagen2, javax.swing.GroupLayout.PREFERRED_SIZE, 177, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(295, Short.MAX_VALUE)))
        );
        panelCuerpoLayout.setVerticalGroup(
            panelCuerpoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelCuerpoLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelCuerpoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cmbRevista, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblRevista))
                .addGap(18, 18, 18)
                .addGroup(panelCuerpoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cajaNombre, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblNombre))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(panelCuerpoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(lblTipo)
                    .addComponent(radioInves))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(radioRev)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(radioCorto)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(radioCaso)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(panelCuerpoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelCuerpoLayout.createSequentialGroup()
                        .addComponent(fArticulo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addGroup(panelCuerpoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(cajaPaginas, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lblPaginas)))
                    .addComponent(lblFecha))
                .addContainerGap())
            .addGroup(panelCuerpoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(panelCuerpoLayout.createSequentialGroup()
                    .addGap(80, 80, 80)
                    .addComponent(lblImagen2, javax.swing.GroupLayout.PREFERRED_SIZE, 108, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(72, Short.MAX_VALUE)))
        );

        panelBotones.setBackground(new java.awt.Color(215, 142, 40));

        btnRegistrar.setFont(new java.awt.Font("Segoe UI Black", 0, 12)); // NOI18N
        btnRegistrar.setText("Registrar");
        btnRegistrar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRegistrarActionPerformed(evt);
            }
        });

        btnRegistrar1.setFont(new java.awt.Font("Segoe UI Black", 0, 12)); // NOI18N
        btnRegistrar1.setText("Cancelar");
        btnRegistrar1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRegistrar1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout panelBotonesLayout = new javax.swing.GroupLayout(panelBotones);
        panelBotones.setLayout(panelBotonesLayout);
        panelBotonesLayout.setHorizontalGroup(
            panelBotonesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelBotonesLayout.createSequentialGroup()
                .addGap(134, 134, 134)
                .addComponent(btnRegistrar, javax.swing.GroupLayout.PREFERRED_SIZE, 94, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(119, 119, 119)
                .addComponent(btnRegistrar1, javax.swing.GroupLayout.PREFERRED_SIZE, 84, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        panelBotonesLayout.setVerticalGroup(
            panelBotonesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelBotonesLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(panelBotonesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnRegistrar, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnRegistrar1, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panelTitulo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(panelCuerpo, javax.swing.GroupLayout.PREFERRED_SIZE, 578, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addComponent(panelBotones, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(panelTitulo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(panelCuerpo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(panelBotones, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void cajaNombreActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cajaNombreActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cajaNombreActionPerformed

    private void btnCerrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCerrarActionPerformed
        this.dispose();
    }//GEN-LAST:event_btnCerrarActionPerformed

    private void btnRegistrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRegistrarActionPerformed
        if (estaTodoBien()) {
            String nombre;
            Date fecha;
            Short paginas;
            Integer indiceSeleccionado, codSeleccionado;
            Short tipo = MiRadio.codigoSeleccionado(grupoTipo);
            nombre = cajaNombre.getText();
            fecha = fArticulo.getDate();
            paginas = Short.valueOf(cajaPaginas.getText());
            indiceSeleccionado = cmbRevista.getSelectedIndex();
            codSeleccionado = losCodigosRevistas.get(indiceSeleccionado);

            Revista objRevista = new Revista(codSeleccionado, "", "", "", null, 0);

            Articulo objArticulo = new Articulo();

            objArticulo.setNombreArticulo(nombre);
            objArticulo.setTipoArticulo(tipo);
            objArticulo.setFechaPublicacionArticulo(fecha);
            objArticulo.setCantidadPaginasArticulo(paginas);
            objArticulo.setCodRevista(objRevista);

            DaoArticulo miDao = new DaoArticulo();

            if (miDao.registrar(objArticulo)) {
                JOptionPane.showMessageDialog(panelCuerpo, "Registro exitoso", "Información", JOptionPane.INFORMATION_MESSAGE);
                limpiar();
            } else {
                JOptionPane.showMessageDialog(panelCuerpo, "No se pudo resgistrar", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }


    }//GEN-LAST:event_btnRegistrarActionPerformed

    private void cajaPaginasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cajaPaginasActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cajaPaginasActionPerformed

    private void radioCortoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_radioCortoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_radioCortoActionPerformed

    private void radioInvesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_radioInvesActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_radioInvesActionPerformed

    private void radioCasoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_radioCasoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_radioCasoActionPerformed

    private void radioRevActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_radioRevActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_radioRevActionPerformed

    private void btnRegistrar1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRegistrar1ActionPerformed
        limpiar();
    }//GEN-LAST:event_btnRegistrar1ActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnCerrar;
    private javax.swing.JButton btnRegistrar;
    private javax.swing.JButton btnRegistrar1;
    private javax.swing.JTextField cajaNombre;
    private javax.swing.JTextField cajaPaginas;
    private javax.swing.JComboBox<String> cmbRevista;
    private com.toedter.calendar.JDateChooser fArticulo;
    private javax.swing.ButtonGroup grupoTipo;
    private javax.swing.JLabel lblFecha;
    private javax.swing.JLabel lblImagen;
    private javax.swing.JLabel lblImagen2;
    private javax.swing.JLabel lblNombre;
    private javax.swing.JLabel lblPaginas;
    private javax.swing.JLabel lblRevista;
    private javax.swing.JLabel lblTipo;
    private javax.swing.JLabel lblTitulo;
    private javax.swing.JPanel panelBotones;
    private javax.swing.JPanel panelCuerpo;
    private javax.swing.JPanel panelTitulo;
    private javax.swing.JRadioButton radioCaso;
    private javax.swing.JRadioButton radioCorto;
    private javax.swing.JRadioButton radioInves;
    private javax.swing.JRadioButton radioRev;
    // End of variables declaration//GEN-END:variables
}
