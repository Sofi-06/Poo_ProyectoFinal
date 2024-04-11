package edu.usta.formularios;

import edu.usta.daos.DaoArticulo;
import edu.usta.daos.DaoRevista;
import edu.usta.entidades.Articulo;
import edu.usta.entidades.Revista;
import edu.usta.utilidades.MiRadio;
import java.awt.Dialog;
import java.awt.Image;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

public class FrmArticuloEditar extends javax.swing.JDialog {

    private final Articulo objActualizar;
    private Map<Integer, Integer> losCodigosRevistas = new HashMap<>();
    private final DefaultComboBoxModel miModeloCombo = new DefaultComboBoxModel();
    private Integer indiceRevista;

    public FrmArticuloEditar(java.awt.Frame parent, boolean modal, Articulo objExterno) {
        super(parent, modal);
        quitarHeader(this);

        initComponents();

        objActualizar = objExterno;
        cmbRevista.setModel(miModeloCombo);
        MiRadio.apareceRadioSeleccionado(objActualizar.getTipoArticulo(), grupoTipo);

        cajaNombre.setText(objExterno.getNombreArticulo());
        cajaPaginas.setText("" + objExterno.getCantidadPaginasArticulo());
        fArticulo.setDate(objExterno.getFechaPublicacionArticulo());
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
        establecerImagen(lblImagen, "https://cdn-icons-png.flaticon.com/128/1827/1827933.png?ga=GA1.1.881827217.1693972543&semt=ais", 40, 40);
        establecerImagen(lblImagen2, "https://cdn-icons-png.flaticon.com/128/2896/2896400.png?ga=GA1.1.881827217.1693972543&semt=ais", 90, 90);

    }

    private void cargarRevistas() {

        List<Revista> arrRevistas;
        int indice = 0;

        DaoRevista miDao = new DaoRevista();
        arrRevistas = miDao.consultar("");
        losCodigosRevistas.put(0, 0);

        miModeloCombo.addElement("Seleccione la revista...");
        for (Revista miRevista : arrRevistas) {
            indice++;
            losCodigosRevistas.put(indice, miRevista.getCodRevista());
            miModeloCombo.addElement(miRevista.getNombreRevista());

            if (Objects.equals(objActualizar.getCodRevista().getCodRevista(), miRevista.getCodRevista())) {
                indiceRevista = indice;
            }

        }
        cmbRevista.setSelectedIndex(indiceRevista);
    }

    private void quitarHeader(JDialog dialog) {

        Dialog dialogAsDialog = (Dialog) dialog;

        dialogAsDialog.setUndecorated(true);
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
        cajaNombre.setText("" + objActualizar.getNombreArticulo());
        fArticulo.setDate(objActualizar.getFechaPublicacionArticulo());
        cajaPaginas.setText("" + objActualizar.getCantidadPaginasArticulo());
        MiRadio.apareceRadioSeleccionado(objActualizar.getTipoArticulo(), grupoTipo);
        cmbRevista.setSelectedIndex(indiceRevista);
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
        radioInves = new javax.swing.JRadioButton();
        radioRev = new javax.swing.JRadioButton();
        radioCorto = new javax.swing.JRadioButton();
        radioCaso = new javax.swing.JRadioButton();
        lblImagen2 = new javax.swing.JLabel();
        panelBotones = new javax.swing.JPanel();
        btnEditar = new javax.swing.JButton();
        btnCancelar = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        panelTitulo.setBackground(new java.awt.Color(131, 157, 192));

        lblTitulo.setFont(new java.awt.Font("Kristen ITC", 0, 24)); // NOI18N
        lblTitulo.setForeground(new java.awt.Color(255, 255, 255));
        lblTitulo.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblTitulo.setText("Editar Articulos");

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
                .addGap(16, 16, 16)
                .addComponent(lblImagen, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(lblTitulo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(49, 49, 49)
                .addComponent(btnCerrar, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(19, 19, 19))
        );
        panelTituloLayout.setVerticalGroup(
            panelTituloLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(btnCerrar, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
            .addComponent(lblImagen, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 45, Short.MAX_VALUE)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelTituloLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(lblTitulo, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        panelCuerpo.setBackground(new java.awt.Color(199, 208, 215));

        lblNombre.setFont(new java.awt.Font("Segoe UI Black", 0, 12)); // NOI18N
        lblNombre.setForeground(new java.awt.Color(51, 0, 51));
        lblNombre.setText("Nombre Articulo:");

        lblPaginas.setFont(new java.awt.Font("Segoe UI Black", 0, 12)); // NOI18N
        lblPaginas.setForeground(new java.awt.Color(51, 0, 51));
        lblPaginas.setText("Cantidad Págnas:");

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

        grupoTipo.add(radioInves);
        radioInves.setFont(new java.awt.Font("Segoe UI Black", 0, 12)); // NOI18N
        radioInves.setForeground(new java.awt.Color(51, 0, 51));
        radioInves.setText("Investigación");
        radioInves.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                radioInvesActionPerformed(evt);
            }
        });

        grupoTipo.add(radioRev);
        radioRev.setFont(new java.awt.Font("Segoe UI Black", 0, 12)); // NOI18N
        radioRev.setForeground(new java.awt.Color(51, 0, 51));
        radioRev.setText("Revisión");
        radioRev.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                radioRevActionPerformed(evt);
            }
        });

        grupoTipo.add(radioCorto);
        radioCorto.setFont(new java.awt.Font("Segoe UI Black", 0, 12)); // NOI18N
        radioCorto.setForeground(new java.awt.Color(51, 0, 51));
        radioCorto.setText("Corto");
        radioCorto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                radioCortoActionPerformed(evt);
            }
        });

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
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelCuerpoLayout.createSequentialGroup()
                .addGroup(panelCuerpoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelCuerpoLayout.createSequentialGroup()
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(panelCuerpoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblNombre, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(lblRevista, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lblPaginas, javax.swing.GroupLayout.Alignment.TRAILING))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED))
                    .addGroup(panelCuerpoLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(lblFecha)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addGroup(panelCuerpoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(cajaPaginas)
                    .addComponent(cajaNombre)
                    .addComponent(cmbRevista, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(fArticulo, javax.swing.GroupLayout.DEFAULT_SIZE, 345, Short.MAX_VALUE))
                .addGap(158, 158, 158))
            .addGroup(panelCuerpoLayout.createSequentialGroup()
                .addGap(71, 71, 71)
                .addComponent(lblTipo)
                .addGap(18, 18, 18)
                .addGroup(panelCuerpoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(radioInves)
                    .addComponent(radioRev)
                    .addComponent(radioCaso)
                    .addComponent(radioCorto))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(panelCuerpoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelCuerpoLayout.createSequentialGroup()
                    .addContainerGap(311, Short.MAX_VALUE)
                    .addComponent(lblImagen2, javax.swing.GroupLayout.PREFERRED_SIZE, 161, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(151, Short.MAX_VALUE)))
        );
        panelCuerpoLayout.setVerticalGroup(
            panelCuerpoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelCuerpoLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelCuerpoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cmbRevista, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblRevista))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(panelCuerpoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cajaNombre, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblNombre))
                .addGap(9, 9, 9)
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
                    .addComponent(fArticulo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblFecha))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(panelCuerpoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cajaPaginas, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblPaginas))
                .addGap(16, 16, 16))
            .addGroup(panelCuerpoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelCuerpoLayout.createSequentialGroup()
                    .addContainerGap(75, Short.MAX_VALUE)
                    .addComponent(lblImagen2, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(85, Short.MAX_VALUE)))
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
                .addComponent(btnEditar, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(123, 123, 123)
                .addComponent(btnCancelar, javax.swing.GroupLayout.PREFERRED_SIZE, 99, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(103, 103, 103))
        );
        panelBotonesLayout.setVerticalGroup(
            panelBotonesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelBotonesLayout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addGroup(panelBotonesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnEditar, javax.swing.GroupLayout.DEFAULT_SIZE, 34, Short.MAX_VALUE)
                    .addComponent(btnCancelar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panelBotones, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(panelTitulo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(panelCuerpo, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 533, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(panelTitulo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(panelCuerpo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(panelBotones, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void btnCerrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCerrarActionPerformed
        this.dispose();
    }//GEN-LAST:event_btnCerrarActionPerformed

    private void cajaNombreActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cajaNombreActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cajaNombreActionPerformed

    private void cajaPaginasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cajaPaginasActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cajaPaginasActionPerformed

    private void btnEditarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEditarActionPerformed
        if (estaTodoBien()) {
            String nombre;
            Date fecha;
            Short paginas;
            Integer indiceSeleccionado, codSeleccionado;
            Short genero = MiRadio.codigoSeleccionado(grupoTipo);
            nombre = cajaNombre.getText();
            fecha = fArticulo.getDate();
            paginas = Short.valueOf(cajaPaginas.getText());
            indiceSeleccionado = cmbRevista.getSelectedIndex();
            codSeleccionado = losCodigosRevistas.get(indiceSeleccionado);

            Revista objRevista = new Revista(codSeleccionado, "", "", "", null, 0);

            objActualizar.setNombreArticulo(nombre);
            objActualizar.setFechaPublicacionArticulo(fecha);
            objActualizar.setTipoArticulo(genero);
            objActualizar.setCantidadPaginasArticulo(paginas);
            objActualizar.setCodRevista(objRevista);

            DaoArticulo miDao = new DaoArticulo();

            if (miDao.actualizar(objActualizar)) {
                JOptionPane.showMessageDialog(panelCuerpo, "Edición exitosa", "Información", JOptionPane.INFORMATION_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(panelCuerpo, "No se pudo editar", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }

    }//GEN-LAST:event_btnEditarActionPerformed

    private void btnCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelarActionPerformed
        limpiar();
    }//GEN-LAST:event_btnCancelarActionPerformed

    private void radioCortoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_radioCortoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_radioCortoActionPerformed

    private void radioRevActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_radioRevActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_radioRevActionPerformed

    private void radioInvesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_radioInvesActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_radioInvesActionPerformed

    private void radioCasoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_radioCasoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_radioCasoActionPerformed

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
            java.util.logging.Logger.getLogger(FrmArticuloEditar.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(FrmArticuloEditar.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(FrmArticuloEditar.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(FrmArticuloEditar.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                FrmArticuloEditar dialog = new FrmArticuloEditar(new javax.swing.JFrame(), true, new Articulo());
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
