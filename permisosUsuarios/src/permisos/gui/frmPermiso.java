/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package permisos.gui;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;
import static permisos.gui.frmInicio.u;
import permisos.gui.logica.CommTablaLogic;
import permisos.gui.logica.PermisoLogic;
import permisos.gui.logica.UsuarioLogic;
import permisos.gui.logica.entidades.Permiso;
import permisos.gui.logica.entidades.Usuario;

/**
 *
 * @author Kevin Camilo Gómez González - kgomezg81832@universidadean.edu.co
 */
public class frmPermiso extends javax.swing.JFrame {

    private static final UsuarioLogic ul = new UsuarioLogic();
    private static final PermisoLogic pl = new PermisoLogic();
    private static final CommTablaLogic ctl = new CommTablaLogic();

    private static int IdPermiso = 0;
    private static Usuario usuarioLogeado;

    public frmPermiso() {
        initComponents();

        // Agrego validación de cerrar sesión.
        frmPermiso.this.addWindowListener(new java.awt.event.WindowAdapter() {
            @Override
            public void windowClosing(java.awt.event.WindowEvent windowEvent) {
                Object[] opciones = {"Aceptar", "Cancelar"};

                if (JOptionPane.showOptionDialog(
                        frmPermiso.this, "Señor(a) Usuario(a):\n¿Está seguro de volver atrás?\nSi lo esta de click en Aceptar de lo contrario de click en Cancelar.", "Confirmación",
                        0, JOptionPane.INFORMATION_MESSAGE, null, opciones, null) == 0) {
                    desinit();
                }
            }
        });
    }

    public void init(int IdPermiso, Usuario _usuLog) {
        usuarioLogeado = _usuLog;

        Permiso pEditarCrear = CargarPermiso(IdPermiso);

        cargarEstado();
        cargarTipoPermiso();

        if (usuarioLogeado.getUsuPerfil() == 1) {
            Usuario uEditar = ul.consultaUsuario(pEditarCrear.getIdUsuario());

            btnEditarCrearPermiso.setText("Editar Permiso");

            outObservacionesUsuario.setEnabled(false);
            outFechaInicio.setEnabled(false);
            outFechaFin.setEnabled(false);
            slcTipoPermiso.setEnabled(false);

            slcEstadoPermiso.setEnabled(true);
            outObservacionesAdministrador.setEnabled(true);

            outFechaInicio.setDate(pEditarCrear.getInicio());
            outFechaFin.setDate(pEditarCrear.getFin());
            outFechaSolicitud.setDate(pEditarCrear.getFechaAccion());
            outNumeroPermiso.setText(String.valueOf(pEditarCrear.getId()));
            outUsuarioSolicita.setText(uEditar.getUsuLogin());
            outObservacionesUsuario.setText(pEditarCrear.getObsUsuario());
            outObservacionesAdministrador.setText("");

            slcTipoPermiso.setSelectedIndex(pEditarCrear.getTipoPermiso());
            slcEstadoPermiso.setSelectedIndex(pEditarCrear.getEstado());

        } else if (usuarioLogeado.getUsuPerfil() == 2) {
            btnEditarCrearPermiso.setText("Crear Permiso");

            outObservacionesUsuario.setEnabled(true);
            outFechaInicio.setEnabled(true);
            outFechaFin.setEnabled(true);
            slcTipoPermiso.setEnabled(true);

            outFechaSolicitud.setEnabled(false);
            slcEstadoPermiso.setEnabled(false);
            outObservacionesAdministrador.setEnabled(false);

            outFechaSolicitud.setDate(new Date());
            outFechaFin.setDate(null);
            outFechaInicio.setDate(null);
            outUsuarioSolicita.setText(usuarioLogeado.getUsuLogin());

        }

        setVisible(true);

        btnEditarCrearPermiso.setEnabled(true);
    }

    public void desinit() {
        IdPermiso = 0;
        usuarioLogeado = new Usuario();
        setVisible(false);

        outObservacionesAdministrador.setText("");
        outObservacionesUsuario.setText("");
        outFechaInicio.setDate(null);
        outFechaFin.setDate(null);
        btnEditarCrearPermiso.setEnabled(false);
    }

    public Permiso CargarPermiso(int IdPermiso) {
        if (usuarioLogeado.getUsuPerfil() == 1) {
            return pl.ConsultarPermiso(IdPermiso);
        } else {
            Permiso p = new Permiso();

            p.setObsUsuario("");
            p.setTipoPermiso(0);
            p.setEstado(1);
            p.setIdUsuario(usuarioLogeado.getUsuPerfil());
            p.setFechaAccion(new Date());
            p.setInicio(null);
            p.setFin(null);

            return p;
        }
    }

    private void cargarEstado() {
        ArrayList<String> alTD = new ArrayList<>();

        ctl.consultarEstadosPermiso().forEach(tp -> {
            alTD.add(tp.getEstado());
        });

        DefaultComboBoxModel<String> dcbm = new DefaultComboBoxModel(alTD.toArray());
        slcEstadoPermiso.setModel(dcbm);

        if (usuarioLogeado.getUsuPerfil() == 2) {
            slcEstadoPermiso.setSelectedIndex(1);
        }
    }

    private void cargarTipoPermiso() {
        ArrayList<String> alTD = new ArrayList<>();

        ctl.consultarTiposPermiso().forEach(tp -> {
            alTD.add(tp.getTipoPermiso());
        });

        DefaultComboBoxModel<String> dcbm = new DefaultComboBoxModel(alTD.toArray());
        slcTipoPermiso.setModel(dcbm);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        lblNumeroPermiso = new javax.swing.JLabel();
        outNumeroPermiso = new javax.swing.JTextField();
        lblUsuarioSolicitante = new javax.swing.JLabel();
        outUsuarioSolicita = new javax.swing.JTextField();
        lblFechaInicio = new javax.swing.JLabel();
        outFechaInicio = new com.toedter.calendar.JDateChooser();
        jScrollPane1 = new javax.swing.JScrollPane();
        outObservacionesUsuario = new javax.swing.JTextArea();
        lblObservacionesUsuario = new javax.swing.JLabel();
        lblObservacionesAdminstrador = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        outObservacionesAdministrador = new javax.swing.JTextArea();
        lblFechaFin = new javax.swing.JLabel();
        outFechaFin = new com.toedter.calendar.JDateChooser();
        lblFechaSolicitud = new javax.swing.JLabel();
        outFechaSolicitud = new com.toedter.calendar.JDateChooser();
        slcTipoPermiso = new javax.swing.JComboBox<>();
        outTipoPermiso = new javax.swing.JLabel();
        btnEditarCrearPermiso = new javax.swing.JButton();
        outEstadoPermiso = new javax.swing.JLabel();
        slcEstadoPermiso = new javax.swing.JComboBox<>();
        btnCancelar = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);
        setTitle("Permiso");
        setName("Permiso No°"); // NOI18N

        lblNumeroPermiso.setText("Número de Permiso:");

        outNumeroPermiso.setEditable(false);
        outNumeroPermiso.setBackground(new java.awt.Color(255, 255, 255));
        outNumeroPermiso.setDisabledTextColor(new java.awt.Color(0, 0, 0));
        outNumeroPermiso.setEnabled(false);

        lblUsuarioSolicitante.setText("Usuario Solicitante:");

        outUsuarioSolicita.setEditable(false);
        outUsuarioSolicita.setBackground(new java.awt.Color(255, 255, 255));
        outUsuarioSolicita.setDisabledTextColor(new java.awt.Color(0, 0, 0));
        outUsuarioSolicita.setEnabled(false);

        lblFechaInicio.setText("Fecha Inicio:");

        outFechaInicio.setBackground(new java.awt.Color(255, 255, 255));

        outObservacionesUsuario.setColumns(20);
        outObservacionesUsuario.setRows(5);
        jScrollPane1.setViewportView(outObservacionesUsuario);

        lblObservacionesUsuario.setText("Observaciones Usuario:");

        lblObservacionesAdminstrador.setText("Observaciones Administrador:");

        outObservacionesAdministrador.setColumns(20);
        outObservacionesAdministrador.setRows(5);
        jScrollPane2.setViewportView(outObservacionesAdministrador);

        lblFechaFin.setText("Fecha Fin:");

        outFechaFin.setBackground(new java.awt.Color(255, 255, 255));

        lblFechaSolicitud.setText("Fecha Solicitud:");

        outFechaSolicitud.setBackground(new java.awt.Color(255, 255, 255));
        outFechaSolicitud.setEnabled(false);

        outTipoPermiso.setText("Tipo de Permiso:");

        btnEditarCrearPermiso.setText("Editar Permiso");
        btnEditarCrearPermiso.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEditarCrearPermisoActionPerformed(evt);
            }
        });

        outEstadoPermiso.setText("Estado Permiso:");

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
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(outTipoPermiso, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                .addComponent(lblObservacionesUsuario)
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addComponent(slcTipoPermiso, javax.swing.GroupLayout.Alignment.LEADING, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(lblObservacionesAdminstrador)
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jScrollPane2)
                                    .addComponent(slcEstadoPermiso, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(outEstadoPermiso, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addContainerGap())))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(outNumeroPermiso)
                                    .addComponent(lblNumeroPermiso, javax.swing.GroupLayout.DEFAULT_SIZE, 134, Short.MAX_VALUE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(lblUsuarioSolicitante, javax.swing.GroupLayout.DEFAULT_SIZE, 130, Short.MAX_VALUE)
                                    .addComponent(outUsuarioSolicita))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(lblFechaInicio, javax.swing.GroupLayout.DEFAULT_SIZE, 104, Short.MAX_VALUE)
                                    .addComponent(outFechaInicio, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(lblFechaFin, javax.swing.GroupLayout.DEFAULT_SIZE, 116, Short.MAX_VALUE)
                                    .addComponent(outFechaFin, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(lblFechaSolicitud, javax.swing.GroupLayout.DEFAULT_SIZE, 114, Short.MAX_VALUE)
                                    .addComponent(outFechaSolicitud, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(btnCancelar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(btnEditarCrearPermiso, javax.swing.GroupLayout.PREFERRED_SIZE, 318, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addContainerGap())))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addGroup(layout.createSequentialGroup()
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(lblNumeroPermiso)
                                .addComponent(lblUsuarioSolicitante)
                                .addComponent(lblFechaInicio))
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(outNumeroPermiso, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(outUsuarioSolicita, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(outFechaInicio, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGroup(layout.createSequentialGroup()
                            .addComponent(lblFechaFin)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(outFechaFin, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(lblFechaSolicitud)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(outFechaSolicitud, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(lblObservacionesUsuario)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(lblObservacionesAdminstrador)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(outTipoPermiso)
                    .addComponent(outEstadoPermiso))
                .addGap(6, 6, 6)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(slcTipoPermiso, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(slcEstadoPermiso, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnEditarCrearPermiso)
                    .addComponent(btnCancelar))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelarActionPerformed
        desinit();
    }//GEN-LAST:event_btnCancelarActionPerformed

    private void btnEditarCrearPermisoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEditarCrearPermisoActionPerformed
        Permiso p = new Permiso();
        Boolean resultado = false;

        if (usuarioLogeado.getUsuPerfil() == 1) {

            p.setId(Integer.parseInt(outNumeroPermiso.getText()));
            p.setIdUsuario(usuarioLogeado.getUsuId());
            p.setInicio(outFechaInicio.getDate());
            p.setTipoPermiso(slcTipoPermiso.getSelectedIndex());
            p.setInicio(outFechaInicio.getDate());
            p.setFin(outFechaFin.getDate());
            p.setFechaAccion(outFechaSolicitud.getDate());
            p.setObsUsuario(outObservacionesUsuario.getText());
            p.setObsAdministrador(outObservacionesAdministrador.getText());
            p.setEstado(slcEstadoPermiso.getSelectedIndex());

            pl.CrearEditarPermiso(usuarioLogeado.getUsuPerfil(), p);

            Object[] opciones = {"Aceptar"};

            if (JOptionPane.showOptionDialog(
                    frmPermiso.this, "Señor(a) Usuario(a):\nCambios guardados con exito.", "Información",
                    0, JOptionPane.INFORMATION_MESSAGE, null, opciones, null) == 0) {
                desinit();
            }
        } else if (usuarioLogeado.getUsuPerfil() == 2) {

            p.setId(0);
            p.setIdUsuario(usuarioLogeado.getUsuId());
            p.setInicio(outFechaInicio.getDate());
            p.setTipoPermiso(slcTipoPermiso.getSelectedIndex());
            p.setInicio(outFechaInicio.getDate());
            p.setFin(outFechaFin.getDate());
            p.setObsUsuario(outObservacionesUsuario.getText());
            p.setObsAdministrador("");
            p.setEstado(slcEstadoPermiso.getSelectedIndex());

            pl.CrearEditarPermiso(usuarioLogeado.getUsuPerfil(), p);

            Object[] opciones = {"Aceptar"};

            if (JOptionPane.showOptionDialog(
                    frmPermiso.this, "Señor(a) Usuario(a):\nCambios guardados con exito.", "Información",
                    0, JOptionPane.INFORMATION_MESSAGE, null, opciones, null) == 0) {
                desinit();
            }
        }
    }//GEN-LAST:event_btnEditarCrearPermisoActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnCancelar;
    private javax.swing.JButton btnEditarCrearPermiso;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JLabel lblFechaFin;
    private javax.swing.JLabel lblFechaInicio;
    private javax.swing.JLabel lblFechaSolicitud;
    private javax.swing.JLabel lblNumeroPermiso;
    private javax.swing.JLabel lblObservacionesAdminstrador;
    private javax.swing.JLabel lblObservacionesUsuario;
    private javax.swing.JLabel lblUsuarioSolicitante;
    private javax.swing.JLabel outEstadoPermiso;
    private com.toedter.calendar.JDateChooser outFechaFin;
    private com.toedter.calendar.JDateChooser outFechaInicio;
    private com.toedter.calendar.JDateChooser outFechaSolicitud;
    private javax.swing.JTextField outNumeroPermiso;
    private javax.swing.JTextArea outObservacionesAdministrador;
    private javax.swing.JTextArea outObservacionesUsuario;
    private javax.swing.JLabel outTipoPermiso;
    private javax.swing.JTextField outUsuarioSolicita;
    private javax.swing.JComboBox<String> slcEstadoPermiso;
    private javax.swing.JComboBox<String> slcTipoPermiso;
    // End of variables declaration//GEN-END:variables

    /**
     * @return the usuarioLogeado
     */
    public static Usuario getUsuarioLogeado() {
        return usuarioLogeado;
    }

    /**
     * @param aUsuarioLogeado the usuarioLogeado to set
     */
    public static void setUsuarioLogeado(Usuario aUsuarioLogeado) {
        usuarioLogeado = aUsuarioLogeado;
    }

    /**
     * @return the IdPermiso
     */
    public static int getIdPermiso() {
        return IdPermiso;
    }

    /**
     * @param aIdPermiso the IdPermiso to set
     */
    public static void setIdPermiso(int aIdPermiso) {
        IdPermiso = aIdPermiso;
    }

}
