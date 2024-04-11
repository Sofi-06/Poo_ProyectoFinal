package edu.usta.formularios;

import edu.usta.daos.DaoDepartamento;
import edu.usta.entidades.Departamento;
import java.awt.Image;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableCellRenderer;

import javax.swing.table.DefaultTableModel;

public class FrmDptoAdmin extends javax.swing.JInternalFrame {

    private Integer codigoDpto = null;

    private String titulos[] = {"Código", "Nombre", "Dane", "Cant", "Elim", "Edit"};
    private DefaultTableModel miModeloTabla = new DefaultTableModel(titulos, 0) {

        @Override
        public boolean isCellEditable(int row, int column) {
            return false;
        }

        @Override
        public Class<?> getColumnClass(int columnIndex) {
            if (columnIndex == 4) {
                return ImageIcon.class;
            }
            if (columnIndex == 5) {
                return ImageIcon.class;
            }
            return Object.class;
        }

    };

    public FrmDptoAdmin() {
        initComponents();

        tablaDatos.setModel(miModeloTabla);
        cargarDptos("");
        lblTotalRegistros.setText(armarLineaCantidad());
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
        establecerImagen(lblImagen, "https://cdn-icons-png.flaticon.com/128/10190/10190480.png?ga=GA1.1.881827217.1693972543&semt=ais", 40, 40);
        establecerImagen(lblImagen2, "https://cdn-icons-png.flaticon.com/128/1944/1944504.png?ga=GA1.1.881827217.1693972543&semt=ais", 30, 30);
        establecerImagen(lblImagen3, "https://cdn-icons-png.flaticon.com/128/7855/7855931.png?ga=GA1.1.881827217.1693972543&semt=ais", 30, 30);
        establecerImagen(lblImagen4, "https://cdn-icons-png.flaticon.com/128/755/755008.png?ga=GA1.1.881827217.1693972543&semt=ais", 30, 30);
    }

    private void cargarDptos(String ordencito) {
        List<Departamento> arrDpto;
        DaoDepartamento miDao = new DaoDepartamento();

        String nomElim = "/edu/usta/iconos/borrar.png";
        String rutaIconElim = this.getClass().getResource(nomElim).getPath();
        ImageIcon borrarIcono = new ImageIcon(rutaIconElim);

        String nomEdit = "/edu/usta/iconos/editar.png";
        String rutaIconEdit = this.getClass().getResource(nomEdit).getPath();
        ImageIcon editarIcono = new ImageIcon(rutaIconEdit);

        miModeloTabla.setNumRows(0);

        arrDpto = miDao.consultar(ordencito);

        arrDpto.forEach((dpto) -> {
            Object filita[] = new Object[6];

            filita[0] = dpto.getCodDepartamento();
            filita[1] = dpto.getNombreDepartamento();
            filita[2] = dpto.getDaneDepartamento();
            filita[3] = dpto.getCantCiudades();
            filita[4] = borrarIcono;
            filita[5] = editarIcono;

            miModeloTabla.addRow(filita);
        });

        tablaDatos.getColumnModel().getColumn(0).setPreferredWidth(60);
        tablaDatos.getColumnModel().getColumn(1).setPreferredWidth(350);
        tablaDatos.getColumnModel().getColumn(2).setPreferredWidth(60);

        DefaultTableCellRenderer centrado = new DefaultTableCellRenderer();
        centrado.setHorizontalAlignment(JLabel.CENTER);
    }

    private int contarRegistros() {
        DaoDepartamento miDao = new DaoDepartamento();
        int cantidad = miDao.totalResgistros();
        return cantidad;
    }

    private String armarLineaCantidad() {
        String cadena = "Se encontraron " + contarRegistros() + " departamentos en la base de datos";

        return cadena;
    }

    private Boolean siElimino(Integer codigoElim) {
        int opcion;
        Boolean bandera = false;
        String textoBotones[] = {"Aceptar", "Cancelar"};

        DaoDepartamento miDao = new DaoDepartamento();
        Departamento objDpto = miDao.buscar(codigoElim);

        opcion = JOptionPane.showOptionDialog(panelCuerpo, "¿Desea eliminar el departamento: " + objDpto.getNombreDepartamento() + "?",
                "Aviso", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null,
                textoBotones, textoBotones[1]);

        if (opcion == JOptionPane.YES_OPTION) {

            bandera = true;

        }

        return bandera;
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
        jScrollPane1 = new javax.swing.JScrollPane();
        tablaDatos = new javax.swing.JTable();
        btnOrdenarCod = new javax.swing.JButton();
        btnOrdenarNom = new javax.swing.JButton();
        btnOrdenarDane = new javax.swing.JButton();
        lblTotalRegistros = new javax.swing.JLabel();
        lblImagen2 = new javax.swing.JLabel();
        lblImagen3 = new javax.swing.JLabel();
        lblImagen4 = new javax.swing.JLabel();

        panelTitulo.setBackground(new java.awt.Color(217, 162, 95));

        lblTitulo.setFont(new java.awt.Font("Kristen ITC", 0, 24)); // NOI18N
        lblTitulo.setForeground(new java.awt.Color(255, 255, 255));
        lblTitulo.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblTitulo.setText("Administrar Departamentos");

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
            .addGroup(panelTituloLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(lblImagen, javax.swing.GroupLayout.PREFERRED_SIZE, 64, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblTitulo, javax.swing.GroupLayout.PREFERRED_SIZE, 393, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(44, 44, 44)
                .addComponent(btnCerrar, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        panelTituloLayout.setVerticalGroup(
            panelTituloLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(btnCerrar, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelTituloLayout.createSequentialGroup()
                .addGap(0, 6, Short.MAX_VALUE)
                .addComponent(lblTitulo)
                .addContainerGap())
            .addComponent(lblImagen, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        panelCuerpo.setBackground(new java.awt.Color(191, 137, 105));

        tablaDatos.setBackground(new java.awt.Color(218, 215, 198));
        tablaDatos.setForeground(new java.awt.Color(51, 0, 51));
        tablaDatos.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        tablaDatos.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tablaDatosMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tablaDatos);

        btnOrdenarCod.setFont(new java.awt.Font("Segoe UI Black", 0, 14)); // NOI18N
        btnOrdenarCod.setText("Por Código");
        btnOrdenarCod.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnOrdenarCodActionPerformed(evt);
            }
        });

        btnOrdenarNom.setFont(new java.awt.Font("Segoe UI Black", 0, 14)); // NOI18N
        btnOrdenarNom.setText("Por Nombre");
        btnOrdenarNom.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnOrdenarNomActionPerformed(evt);
            }
        });

        btnOrdenarDane.setFont(new java.awt.Font("Segoe UI Black", 0, 14)); // NOI18N
        btnOrdenarDane.setText("Por Dane");
        btnOrdenarDane.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnOrdenarDaneActionPerformed(evt);
            }
        });

        lblTotalRegistros.setForeground(new java.awt.Color(51, 0, 0));
        lblTotalRegistros.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        javax.swing.GroupLayout panelCuerpoLayout = new javax.swing.GroupLayout(panelCuerpo);
        panelCuerpo.setLayout(panelCuerpoLayout);
        panelCuerpoLayout.setHorizontalGroup(
            panelCuerpoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelCuerpoLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelCuerpoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 615, Short.MAX_VALUE)
                    .addGroup(panelCuerpoLayout.createSequentialGroup()
                        .addComponent(lblImagen2, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnOrdenarCod)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(lblImagen3, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnOrdenarNom)
                        .addGap(84, 84, 84)
                        .addComponent(lblImagen4, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnOrdenarDane))
                    .addComponent(lblTotalRegistros, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        panelCuerpoLayout.setVerticalGroup(
            panelCuerpoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelCuerpoLayout.createSequentialGroup()
                .addGroup(panelCuerpoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelCuerpoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(lblImagen2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 59, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGroup(panelCuerpoLayout.createSequentialGroup()
                            .addGap(19, 19, 19)
                            .addComponent(btnOrdenarCod))
                        .addComponent(lblImagen3, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 59, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(panelCuerpoLayout.createSequentialGroup()
                        .addGap(17, 17, 17)
                        .addGroup(panelCuerpoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btnOrdenarNom)
                            .addComponent(btnOrdenarDane)))
                    .addComponent(lblImagen4, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 59, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 222, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblTotalRegistros, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panelCuerpo, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(panelTitulo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(panelTitulo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(panelCuerpo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnCerrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCerrarActionPerformed
        this.dispose();
    }//GEN-LAST:event_btnCerrarActionPerformed

    private void btnOrdenarCodActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnOrdenarCodActionPerformed
        cargarDptos("cod_departamento");

    }//GEN-LAST:event_btnOrdenarCodActionPerformed

    private void btnOrdenarNomActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnOrdenarNomActionPerformed
        cargarDptos("nombre_departamento");
    }//GEN-LAST:event_btnOrdenarNomActionPerformed

    private void tablaDatosMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tablaDatosMouseClicked
        int columnaEliminar = tablaDatos.getSelectedColumn();

        if (columnaEliminar == 4) {
            int filaSeleccionada = tablaDatos.getSelectedRow();

            String codigoTexto = miModeloTabla.getValueAt(filaSeleccionada, 0).toString();

            codigoDpto = Integer.valueOf(codigoTexto);

            DaoDepartamento miDao = new DaoDepartamento();

            Departamento objDpto = miDao.buscar(codigoDpto);

            if (objDpto == null) {
                JOptionPane.showMessageDialog(panelCuerpo, "No encontró el departamento", "Advertencia", JOptionPane.WARNING_MESSAGE);
            } else {

                if (siElimino(codigoDpto)) {
                    DaoDepartamento miDaoElim = new DaoDepartamento();
                    if (objDpto.getCantCiudades() > 0) {
                        JOptionPane.showMessageDialog(panelCuerpo, "Hay ciudades relacionadas", "Advertencia", JOptionPane.WARNING_MESSAGE);

                    } else {
                        if (miDaoElim.eliminar(codigoDpto)) {
                            cargarDptos("");
                            lblTotalRegistros.setText(armarLineaCantidad());
                            JOptionPane.showMessageDialog(panelCuerpo, "Eliminación exitosa", "Información", JOptionPane.INFORMATION_MESSAGE);
                        } else {
                            JOptionPane.showMessageDialog(panelCuerpo, "No se pudo eliminar el departamento", "Advertencia", JOptionPane.WARNING_MESSAGE);

                        }
                    }

                }
            }

        }

        if (columnaEliminar == 5) {
            int filaSeleccionada = tablaDatos.getSelectedRow();

            String codigoTexto = miModeloTabla.getValueAt(filaSeleccionada, 0).toString();

            codigoDpto = Integer.valueOf(codigoTexto);

            DaoDepartamento miDao = new DaoDepartamento();

            Departamento objDpto = miDao.buscar(codigoDpto);

            FrmDeptoEditar ventanaFlotante = new FrmDeptoEditar(null, true, objDpto);
            ventanaFlotante.setVisible(true);

            ventanaFlotante.addWindowListener(new WindowAdapter() {
                @Override
                public void windowClosed(WindowEvent e) {
                    cargarDptos("");
                }

            });

        }
    }//GEN-LAST:event_tablaDatosMouseClicked

    private void btnOrdenarDaneActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnOrdenarDaneActionPerformed
        cargarDptos("dane_departamento");
    }//GEN-LAST:event_btnOrdenarDaneActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnCerrar;
    private javax.swing.JButton btnOrdenarCod;
    private javax.swing.JButton btnOrdenarDane;
    private javax.swing.JButton btnOrdenarNom;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lblImagen;
    private javax.swing.JLabel lblImagen2;
    private javax.swing.JLabel lblImagen3;
    private javax.swing.JLabel lblImagen4;
    private javax.swing.JLabel lblTitulo;
    private javax.swing.JLabel lblTotalRegistros;
    private javax.swing.JPanel panelCuerpo;
    private javax.swing.JPanel panelTitulo;
    private javax.swing.JTable tablaDatos;
    // End of variables declaration//GEN-END:variables
}
