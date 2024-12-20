package edu.usta.formularios;

import edu.usta.daos.DaoRevista;
import edu.usta.entidades.Revista;
import java.awt.Image;
import java.net.MalformedURLException;
import java.net.URL;

import java.util.List;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableCellRenderer;

import javax.swing.table.DefaultTableModel;

public class FrmRevistaListar extends javax.swing.JInternalFrame {

    private String titulos[] = {"Código", "Nombre", "Ciudad", "Categoría", "ISSN"};
    private DefaultTableModel miModeloTabla = new DefaultTableModel(titulos, 0) {
        @Override
        public boolean isCellEditable(int row, int column) {
            return false;
        }

    };

    public FrmRevistaListar() {
        initComponents();

        tablaDatos.setModel(miModeloTabla);
        cargarRevistas("");
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
        establecerImagen(lblImagen, "https://cdn-icons-png.flaticon.com/128/381/381631.png?ga=GA1.1.881827217.1693972543&semt=ais", 40, 40);
        establecerImagen(lblImagen2, "https://cdn-icons-png.flaticon.com/128/1944/1944504.png?ga=GA1.1.881827217.1693972543&semt=ais", 30, 30);
        establecerImagen(lblImagen3, "https://cdn-icons-png.flaticon.com/128/7855/7855931.png?ga=GA1.1.881827217.1693972543&semt=ais", 30, 30);
        establecerImagen(lblImagen4, "https://cdn-icons-png.flaticon.com/128/755/755008.png?ga=GA1.1.881827217.1693972543&semt=ais", 30, 30);
    }

    private void cargarRevistas(String ordencito) {
        List<Revista> arrRevistas;
        DaoRevista miDao = new DaoRevista();

        miModeloTabla.setNumRows(0);

        arrRevistas = miDao.consultar(ordencito);

        arrRevistas.forEach((revistica) -> {
            Object filita[] = new Object[5];

            filita[0] = revistica.getCodRevista();
            filita[1] = revistica.getNombreRevista();
            filita[2] = revistica.getCodCiudad().getNombreCiudad() + "-" + revistica.getCodCiudad().getCodDepartamento().getNombreDepartamento();
            filita[3] = revistica.getCategoriaRevista();
            filita[4] = revistica.getIssnRevista();

            miModeloTabla.addRow(filita);
        });

        tablaDatos.getColumnModel().getColumn(0).setPreferredWidth(60);
        tablaDatos.getColumnModel().getColumn(1).setPreferredWidth(150);
        tablaDatos.getColumnModel().getColumn(2).setPreferredWidth(200);
        tablaDatos.getColumnModel().getColumn(3).setPreferredWidth(80);
        tablaDatos.getColumnModel().getColumn(4).setPreferredWidth(60);

        DefaultTableCellRenderer centrado = new DefaultTableCellRenderer();
        centrado.setHorizontalAlignment(JLabel.CENTER);

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
        btnOrdenarCat = new javax.swing.JButton();
        lblImagen2 = new javax.swing.JLabel();
        lblImagen3 = new javax.swing.JLabel();
        lblImagen4 = new javax.swing.JLabel();

        panelTitulo.setBackground(new java.awt.Color(199, 208, 215));

        lblTitulo.setFont(new java.awt.Font("Kristen ITC", 0, 24)); // NOI18N
        lblTitulo.setForeground(new java.awt.Color(255, 255, 255));
        lblTitulo.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblTitulo.setText("Listar Revistas");

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
                .addGap(38, 38, 38)
                .addComponent(lblImagen, javax.swing.GroupLayout.PREFERRED_SIZE, 87, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblTitulo, javax.swing.GroupLayout.PREFERRED_SIZE, 349, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnCerrar, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        panelTituloLayout.setVerticalGroup(
            panelTituloLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(btnCerrar, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
            .addGroup(panelTituloLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblTitulo)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addComponent(lblImagen, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        panelCuerpo.setBackground(new java.awt.Color(131, 157, 192));

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

        btnOrdenarCat.setFont(new java.awt.Font("Segoe UI Black", 0, 14)); // NOI18N
        btnOrdenarCat.setText("Por Categoría");
        btnOrdenarCat.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnOrdenarCatActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout panelCuerpoLayout = new javax.swing.GroupLayout(panelCuerpo);
        panelCuerpo.setLayout(panelCuerpoLayout);
        panelCuerpoLayout.setHorizontalGroup(
            panelCuerpoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelCuerpoLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelCuerpoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 606, Short.MAX_VALUE)
                    .addGroup(panelCuerpoLayout.createSequentialGroup()
                        .addComponent(lblImagen2, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnOrdenarCod)
                        .addGap(82, 82, 82)
                        .addComponent(lblImagen3, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnOrdenarNom)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(lblImagen4, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnOrdenarCat)))
                .addContainerGap())
        );
        panelCuerpoLayout.setVerticalGroup(
            panelCuerpoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelCuerpoLayout.createSequentialGroup()
                .addGroup(panelCuerpoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelCuerpoLayout.createSequentialGroup()
                        .addGap(15, 15, 15)
                        .addGroup(panelCuerpoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btnOrdenarCod)
                            .addComponent(btnOrdenarNom)
                            .addComponent(btnOrdenarCat)))
                    .addGroup(panelCuerpoLayout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(panelCuerpoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblImagen3, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lblImagen2, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lblImagen4, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 238, Short.MAX_VALUE)
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
        cargarRevistas("cod_revista");

    }//GEN-LAST:event_btnOrdenarCodActionPerformed

    private void btnOrdenarNomActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnOrdenarNomActionPerformed
        cargarRevistas("nombre_revista");
    }//GEN-LAST:event_btnOrdenarNomActionPerformed

    private void btnOrdenarCatActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnOrdenarCatActionPerformed
        cargarRevistas("categoria_revista");
    }//GEN-LAST:event_btnOrdenarCatActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnCerrar;
    private javax.swing.JButton btnOrdenarCat;
    private javax.swing.JButton btnOrdenarCod;
    private javax.swing.JButton btnOrdenarNom;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lblImagen;
    private javax.swing.JLabel lblImagen2;
    private javax.swing.JLabel lblImagen3;
    private javax.swing.JLabel lblImagen4;
    private javax.swing.JLabel lblTitulo;
    private javax.swing.JPanel panelCuerpo;
    private javax.swing.JPanel panelTitulo;
    private javax.swing.JTable tablaDatos;
    // End of variables declaration//GEN-END:variables
}
