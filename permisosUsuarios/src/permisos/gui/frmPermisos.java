/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package permisos.gui;

import java.awt.Component;
import java.util.ArrayList;
import java.util.List;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableColumnModel;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumn;
import static permisos.gui.frmUsuario.setIdPerfilLogeado;
import permisos.gui.logica.CommTablaLogic;
import permisos.gui.logica.PermisoLogic;
import permisos.gui.logica.UsuarioLogic;
import permisos.gui.logica.entidades.EstadoPermiso;
import permisos.gui.logica.entidades.TipoPermiso;
import permisos.gui.logica.entidades.Usuario;
import permisos.utilitario.ListaSelectorForzada;

/**
 *
 * @author Kevin Camilo Gómez González - kgomezg81832@universidadean.edu.co
 */
public class frmPermisos extends javax.swing.JFrame {

    private static final frmPermiso frmPer = new frmPermiso();

    private static final CommTablaLogic ctl = new CommTablaLogic();
    private static final UsuarioLogic ul = new UsuarioLogic();

    private static Usuario UsuarioLogeado;
    private static int TipoBusqueda = 0;
    private static int IdUsuarioLogeado = 0;
    private static final PermisoLogic pl = new PermisoLogic();

    private List<EstadoPermiso> estadosPermmisos = new ArrayList<EstadoPermiso>();
    private List<TipoPermiso> tiposPermisos = new ArrayList<TipoPermiso>();

    /**
     * Creates new form frmPermisos
     */
    public frmPermisos() {
        initComponents();

        // Agrego validación de cerrar sesión.
        frmPermisos.this.addWindowListener(new java.awt.event.WindowAdapter() {
            @Override
            public void windowClosing(java.awt.event.WindowEvent windowEvent) {
                Object[] opciones = {"Aceptar", "Cancelar"};

                if (JOptionPane.showOptionDialog(
                        frmPermisos.this, "Señor(a) Usuario(a):\n¿Está seguro de volver atrás?\nSi lo esta de click en Aceptar de lo contrario de click en Cancelar.", "Confirmación",
                        0, JOptionPane.INFORMATION_MESSAGE, null, opciones, null) == 0) {
                    desinit();
                }
            }
        });
    }

    public void CargarTablaPermisos(int TipoBusqueda, String parametro) {
        tblPermisos.setModel(pl.DevolverTablaPermisos(TipoBusqueda, parametro));

        for (int i = 0; i < tblPermisos.getColumnCount(); i++) {
            DefaultTableColumnModel colModel = (DefaultTableColumnModel) tblPermisos.getColumnModel();
            TableColumn col = colModel.getColumn(i);
            int width = 0;

            TableCellRenderer renderer = col.getHeaderRenderer();

            for (int r = 0; r < tblPermisos.getRowCount(); r++) {
                renderer = tblPermisos.getCellRenderer(r, i);

                Component comp = renderer.getTableCellRendererComponent(tblPermisos, tblPermisos.getValueAt(r, i), false, false, r, i);

                width = Math.max(width, comp.getPreferredSize().width);
            }
            col.setPreferredWidth(width + 2);
        }

        tblPermisos.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
        tblPermisos.setSelectionModel(new ListaSelectorForzada());
        tblPermisos.setDefaultEditor(Object.class, null);

        tblPermisos.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent event) {
                if (UsuarioLogeado.getUsuPerfil() == 1) {
                    btnEditarPermiso.setEnabled(true);
                }
            }
        });
    }

    public void CargarTablaPermisos(int TipoBusqueda, int IdUsuario) {
        tblPermisos.setModel(pl.DevolverTablaPermisos(TipoBusqueda, String.valueOf(IdUsuario)));

        for (int i = 0; i < tblPermisos.getColumnCount(); i++) {
            DefaultTableColumnModel colModel = (DefaultTableColumnModel) tblPermisos.getColumnModel();
            TableColumn col = colModel.getColumn(i);
            int width = 0;

            TableCellRenderer renderer = col.getHeaderRenderer();

            for (int r = 0; r < tblPermisos.getRowCount(); r++) {
                renderer = tblPermisos.getCellRenderer(r, i);

                Component comp = renderer.getTableCellRendererComponent(tblPermisos, tblPermisos.getValueAt(r, i), false, false, r, i);

                width = Math.max(width, comp.getPreferredSize().width);
            }
            col.setPreferredWidth(width + 2);
        }

        tblPermisos.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
        tblPermisos.setSelectionModel(new ListaSelectorForzada());
        tblPermisos.setDefaultEditor(Object.class, null);

        tblPermisos.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent event) {
                if (UsuarioLogeado.getUsuPerfil() == 1) {
                    btnEditarPermiso.setEnabled(true);
                }
            }
        });
    }

    public void init(int IdUsuario) {
        setVisible(true);

        btnConsultar.setEnabled(false);

        CargarTablaPermisos(1, IdUsuario);

        if (UsuarioLogeado.getUsuPerfil() == 1) {
            btnXTipoPermiso.setEnabled(true);
            btnXTipoPermiso.setEnabled(true);
            btnConsultar.setEnabled(true);
            slcGenerico.setEnabled(true);
        } else if (UsuarioLogeado.getUsuPerfil() == 2) {
            btnXTipoPermiso.setEnabled(false);
            btnXEstado.setEnabled(false);
            btnConsultar.setEnabled(false);
            slcGenerico.setEnabled(false);
        }
    }

    public void init() {
        setVisible(true);

        btnConsultar.setEnabled(false);

        CargarTablaPermisos(0, "");
    }

    public void desinit() {
        setVisible(false);
        IdUsuarioLogeado = 0;
    }

    private void cargarEstadosPermisos() {
        estadosPermmisos = ctl.consultarEstadosPermiso();

        ArrayList<String> alTD = new ArrayList<>();

        estadosPermmisos.forEach(tp -> {
            alTD.add(tp.getEstado());
        });

        DefaultComboBoxModel<String> dcbm = new DefaultComboBoxModel(alTD.toArray());
        slcGenerico.setModel(dcbm);
    }

    private void cargarTiposPermiso() {
        tiposPermisos = ctl.consultarTiposPermiso();

        ArrayList<String> alTD = new ArrayList<>();

        tiposPermisos.forEach(tp -> {
            alTD.add(tp.getTipoPermiso());
        });

        DefaultComboBoxModel<String> dcbm = new DefaultComboBoxModel(alTD.toArray());
        slcGenerico.setModel(dcbm);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        tblPermisos = new javax.swing.JTable();
        btnXEstado = new javax.swing.JButton();
        btnXTipoPermiso = new javax.swing.JButton();
        lblTipoBusqueda = new javax.swing.JLabel();
        btnConsultar = new javax.swing.JButton();
        slcGenerico = new javax.swing.JComboBox<>();
        lblComboXBusqueda = new javax.swing.JLabel();
        btnConsultarTodos = new javax.swing.JButton();
        btnEditarPermiso = new javax.swing.JButton();
        btnCancelar = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);
        setTitle("Lista de Permisos");
        setName("Lista de Permisos"); // NOI18N
        setResizable(false);

        tblPermisos.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_OFF);
        tblPermisos.setShowGrid(true);
        tblPermisos.getTableHeader().setResizingAllowed(false);
        tblPermisos.getTableHeader().setReorderingAllowed(false);
        jScrollPane1.setViewportView(tblPermisos);

        btnXEstado.setText("Estado");
        btnXEstado.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnXEstadoActionPerformed(evt);
            }
        });

        btnXTipoPermiso.setText("Tipo Permiso");
        btnXTipoPermiso.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnXTipoPermisoActionPerformed(evt);
            }
        });

        lblTipoBusqueda.setText("Tipo de Busqueda");

        btnConsultar.setText("Consultar Permisos");
        btnConsultar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnConsultarActionPerformed(evt);
            }
        });

        lblComboXBusqueda.setText("Opciones");

        btnConsultarTodos.setText("Consultar Todos");
        btnConsultarTodos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnConsultarTodosActionPerformed(evt);
            }
        });

        btnEditarPermiso.setText("Editar Permiso");
        btnEditarPermiso.setEnabled(false);
        btnEditarPermiso.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEditarPermisoActionPerformed(evt);
            }
        });

        btnCancelar.setText("Cancelar");
        btnCancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCancelarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(btnConsultarTodos, javax.swing.GroupLayout.PREFERRED_SIZE, 134, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btnXTipoPermiso, javax.swing.GroupLayout.PREFERRED_SIZE, 134, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(btnXEstado, javax.swing.GroupLayout.PREFERRED_SIZE, 143, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(lblTipoBusqueda, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(lblComboXBusqueda, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(slcGenerico, 0, 164, Short.MAX_VALUE))
                        .addGap(6, 6, 6)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(btnConsultar, javax.swing.GroupLayout.PREFERRED_SIZE, 181, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btnEditarPermiso, javax.swing.GroupLayout.PREFERRED_SIZE, 176, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(btnCancelar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 87, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblTipoBusqueda)
                    .addComponent(lblComboXBusqueda)
                    .addComponent(btnConsultar)
                    .addComponent(btnEditarPermiso))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(slcGenerico, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(btnCancelar))
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(btnXTipoPermiso)
                        .addComponent(btnXEstado)
                        .addComponent(btnConsultarTodos)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void btnXTipoPermisoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnXTipoPermisoActionPerformed
        cargarTiposPermiso();
        TipoBusqueda = 2;
        slcGenerico.setEnabled(true);

        btnEditarPermiso.setEnabled(false);
        btnConsultar.setEnabled(true);
    }//GEN-LAST:event_btnXTipoPermisoActionPerformed

    private void btnXEstadoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnXEstadoActionPerformed
        cargarEstadosPermisos();
        TipoBusqueda = 3;
        slcGenerico.setEnabled(true);

        btnEditarPermiso.setEnabled(false);
        btnConsultar.setEnabled(true);
    }//GEN-LAST:event_btnXEstadoActionPerformed

    private void btnConsultarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnConsultarActionPerformed

        if (slcGenerico.getSelectedIndex() == 0) {

        } else {
            CargarTablaPermisos(TipoBusqueda, String.valueOf(slcGenerico.getSelectedIndex()));
        }

    }//GEN-LAST:event_btnConsultarActionPerformed

    private void btnConsultarTodosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnConsultarTodosActionPerformed

        if (UsuarioLogeado.getUsuPerfil() == 1) {
            CargarTablaPermisos(0, "");

            btnConsultar.setEnabled(false);

            DefaultComboBoxModel<String> dcbm = new DefaultComboBoxModel();
            slcGenerico.setModel(dcbm);

            btnEditarPermiso.setEnabled(false);
            slcGenerico.setEnabled(false);
            btnXEstado.setEnabled(true);
            btnXTipoPermiso.setEnabled(true);

            slcGenerico.setEnabled(true);

        } else if (UsuarioLogeado.getUsuPerfil() == 2) {
            CargarTablaPermisos(1, String.valueOf(UsuarioLogeado.getUsuId()));

            btnConsultar.setEnabled(false);

            DefaultComboBoxModel<String> dcbm = new DefaultComboBoxModel();
            slcGenerico.setModel(dcbm);

            slcGenerico.setEnabled(false);

            btnEditarPermiso.setEnabled(false);
            slcGenerico.setEnabled(false);
            btnXEstado.setEnabled(false);
            btnXTipoPermiso.setEnabled(false);
        }

    }//GEN-LAST:event_btnConsultarTodosActionPerformed

    private void btnEditarPermisoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEditarPermisoActionPerformed

        int IdPermiso = (int) tblPermisos.getValueAt(tblPermisos.getSelectedRow(), 0);

        if (UsuarioLogeado.getUsuPerfil() == 1 && IdPermiso > 0) {
            frmPer.init(IdPermiso, UsuarioLogeado);
        } else if (UsuarioLogeado.getUsuPerfil() == 2) {
            frmPer.init(0, UsuarioLogeado);
        }

    }//GEN-LAST:event_btnEditarPermisoActionPerformed

    private void btnCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelarActionPerformed
        desinit();
    }//GEN-LAST:event_btnCancelarActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnCancelar;
    private javax.swing.JButton btnConsultar;
    private javax.swing.JButton btnConsultarTodos;
    private javax.swing.JButton btnEditarPermiso;
    private javax.swing.JButton btnXEstado;
    private javax.swing.JButton btnXTipoPermiso;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lblComboXBusqueda;
    private javax.swing.JLabel lblTipoBusqueda;
    private javax.swing.JComboBox<String> slcGenerico;
    private javax.swing.JTable tblPermisos;
    // End of variables declaration//GEN-END:variables

    /**
     * @return the UsuarioLogeado
     */
    public static Usuario getUsuarioLogeado() {
        return UsuarioLogeado;
    }

    /**
     * @param aUsuarioLogeado the UsuarioLogeado to set
     */
    public static void setUsuarioLogeado(Usuario aUsuarioLogeado) {
        UsuarioLogeado = aUsuarioLogeado;
    }
}
