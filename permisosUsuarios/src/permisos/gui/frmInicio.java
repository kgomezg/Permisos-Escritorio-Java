/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package permisos.gui;

import java.text.SimpleDateFormat;
import permisos.gui.logica.UsuarioLogic;
import permisos.gui.logica.CommTablaLogic;
import permisos.gui.logica.entidades.EstadoPermiso;
import permisos.gui.logica.entidades.TipoDocumento;
import permisos.gui.logica.entidades.Perfil;
import permisos.gui.logica.entidades.Usuario;
import permisos.gui.logica.entidades.Permiso;
import permisos.gui.logica.entidades.TipoPermiso;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import permisos.utilitario.ErrorBD;

/**
 *
 * @author Kevin Camilo Gómez González - kgomezg81832@universidadean.edu.co
 */
public class frmInicio extends javax.swing.JFrame {

    protected static Usuario u = new Usuario();
    protected static int IdUsuarioLogeado;

    private static final frmPermiso frmPermiso = new frmPermiso();
    private static final frmPermisos frmPermisos = new frmPermisos();
    private static final frmUsuario frmUsuario = new frmUsuario();
    private static final CommTablaLogic ctl = new CommTablaLogic();
    private static final UsuarioLogic ul = new UsuarioLogic();

    private List<EstadoPermiso> estadosPermmisos = new ArrayList<EstadoPermiso>();
    private List<Perfil> perfiles = new ArrayList<Perfil>();
    private List<TipoDocumento> tiposDocumentos = new ArrayList<TipoDocumento>();
    private List<TipoPermiso> tiposPermisos = new ArrayList<TipoPermiso>();
    private List<Permiso> permisos = new ArrayList<Permiso>();

    public frmInicio() {
        initComponents();

        // Agrego validación de cerrar sesión.
        frmInicio.this.addWindowListener(new java.awt.event.WindowAdapter() {
            @Override
            public void windowClosing(java.awt.event.WindowEvent windowEvent) {
                Object[] opciones = {"Aceptar", "Cancelar"};

                if (JOptionPane.showOptionDialog(
                        frmInicio.this, "Señor(a) Usuario(a):\n¿Está seguro de cerrar sesión?\nSi lo esta de click en Aceptar de lo contrario de click en Cancelar", "Confirmación",
                        0, JOptionPane.INFORMATION_MESSAGE, null, opciones, null) == 0) {
                    System.exit(0);
                }
            }
        });

        cargarEstadosPermisos();
        cargarPerfiles();
        cargarTiposDocumentos();
        cargarTiposPermisos();
//        cargarPermisos();
    }

    public void init() {
        setVisible(true);

        try {
            u = consultaUsuario(IdUsuarioLogeado);

            CargarInformacion();
        } catch (Throwable ex) {
            ErrorBD ebd = new ErrorBD(ex.getClass().toString(), ex.getCause().toString(), ex.getMessage());
        }
    }

    Usuario consultaUsuario(int IdUsuarioLogeado) {
        return ul.consultaUsuario(IdUsuarioLogeado);
    }

    public void init(int _IdUsuario) {
        IdUsuarioLogeado = _IdUsuario;

        setVisible(true);

        try {
            u = consultaUsuario(IdUsuarioLogeado);
            CargarInformacion();
        } catch (Throwable ex) {
            ErrorBD ebd = new ErrorBD(ex.getClass().toString(), ex.getCause().toString(), ex.getMessage());
        }
    }

    public void desinit() {
        setVisible(false);
    }

    void CargarInformacion() {
        outNombreCompleto.setText(
                u.getUsuPrimerNombre() + " "
                + (u.getUsuSegundoNombre().equals("") ? "" : u.getUsuSegundoNombre() + " ")
                + " " + u.getUsuPrimerApellido() + " "
                + (u.getUsuSegundoApellido().equals("") ? "" : u.getUsuSegundoApellido())
        );

        outFechaRegistro.setText(new SimpleDateFormat("dd/MM/yyyy").format(u.getUsuFechaRegistro()));
        outEmail.setText(u.getUsuEmail());
        outTelefono.setText(u.getUsuTelefono());
        outDireccion.setText(u.getUsuDireccion());
        outDocumentoIdentificacion.setText(tiposDocumentos.get(u.getUsuIdenTipo()).getDescripcionAbreviada() + " " + u.getUsuIden());
        outPerfil.setText(perfiles.get(u.getUsuPerfil()).getPerfil());

        if (u.getUsuPerfil() == 1) {
            tabOpciones.remove(panUsuario);
        } else if (u.getUsuPerfil() == 2) {
            tabOpciones.remove(panAdmin);
        }

        frmUsuario.setIdPerfilLogeado(u.getUsuPerfil());
    }

    private void cargarEstadosPermisos() {
        estadosPermmisos = ctl.consultarEstadosPermiso();
    }

    private void cargarPerfiles() {
        perfiles = ctl.consultarPerfiles();
    }

    private void cargarTiposDocumentos() {
        tiposDocumentos = ctl.consultarTiposDocumento();
    }

    private void cargarTiposPermisos() {
        tiposPermisos = ctl.consultarTiposPermiso();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        outNombreCompleto = new javax.swing.JTextField();
        lblNombresCompletos = new javax.swing.JLabel();
        lblDocumentoIdentificacion = new javax.swing.JLabel();
        outDocumentoIdentificacion = new javax.swing.JTextField();
        lblPerfil = new javax.swing.JLabel();
        outPerfil = new javax.swing.JTextField();
        lblDireccion = new javax.swing.JLabel();
        outDireccion = new javax.swing.JTextField();
        lblEmail = new javax.swing.JLabel();
        outEmail = new javax.swing.JTextField();
        lblTelefono1 = new javax.swing.JLabel();
        outTelefono = new javax.swing.JTextField();
        lblEmail1 = new javax.swing.JLabel();
        outFechaRegistro = new javax.swing.JTextField();
        tabOpciones = new javax.swing.JTabbedPane();
        panAdmin = new javax.swing.JPanel();
        btnCrearUsuarioAdmin = new javax.swing.JButton();
        btnEditarUsuarioAdmin = new javax.swing.JButton();
        btnConsultarPermisosAdmin = new javax.swing.JButton();
        panUsuario = new javax.swing.JPanel();
        btnCrearPermiso = new javax.swing.JButton();
        btnConsultarPermisosUsuario = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);
        setTitle("Menú");
        setName("frmInicio"); // NOI18N
        setResizable(false);

        outNombreCompleto.setEditable(false);
        outNombreCompleto.setBackground(new java.awt.Color(255, 255, 255));
        outNombreCompleto.setDisabledTextColor(new java.awt.Color(0, 0, 0));
        outNombreCompleto.setEnabled(false);

        lblNombresCompletos.setText("Nombres Completos:");

        lblDocumentoIdentificacion.setText("Documento Identificación:");

        outDocumentoIdentificacion.setEditable(false);
        outDocumentoIdentificacion.setBackground(new java.awt.Color(255, 255, 255));
        outDocumentoIdentificacion.setDisabledTextColor(new java.awt.Color(0, 0, 0));
        outDocumentoIdentificacion.setEnabled(false);

        lblPerfil.setText("Perfil:");

        outPerfil.setEditable(false);
        outPerfil.setBackground(new java.awt.Color(255, 255, 255));
        outPerfil.setDisabledTextColor(new java.awt.Color(0, 0, 0));
        outPerfil.setEnabled(false);

        lblDireccion.setText("Dirección:");

        outDireccion.setEditable(false);
        outDireccion.setBackground(new java.awt.Color(255, 255, 255));
        outDireccion.setDisabledTextColor(new java.awt.Color(0, 0, 0));
        outDireccion.setEnabled(false);

        lblEmail.setText("Email:");

        outEmail.setEditable(false);
        outEmail.setBackground(new java.awt.Color(255, 255, 255));
        outEmail.setDisabledTextColor(new java.awt.Color(0, 0, 0));
        outEmail.setEnabled(false);

        lblTelefono1.setText("Teléfono:");

        outTelefono.setEditable(false);
        outTelefono.setBackground(new java.awt.Color(255, 255, 255));
        outTelefono.setDisabledTextColor(new java.awt.Color(0, 0, 0));
        outTelefono.setEnabled(false);

        lblEmail1.setText("Fecha Registro:");

        outFechaRegistro.setEditable(false);
        outFechaRegistro.setBackground(new java.awt.Color(255, 255, 255));
        outFechaRegistro.setDisabledTextColor(new java.awt.Color(0, 0, 0));
        outFechaRegistro.setEnabled(false);

        btnCrearUsuarioAdmin.setText("Crear Usuario");
        btnCrearUsuarioAdmin.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCrearUsuarioAdminActionPerformed(evt);
            }
        });

        btnEditarUsuarioAdmin.setText("Editar Usuario");
        btnEditarUsuarioAdmin.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEditarUsuarioAdminActionPerformed(evt);
            }
        });

        btnConsultarPermisosAdmin.setText("Consultar Permisos");
        btnConsultarPermisosAdmin.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnConsultarPermisosAdminActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout panAdminLayout = new javax.swing.GroupLayout(panAdmin);
        panAdmin.setLayout(panAdminLayout);
        panAdminLayout.setHorizontalGroup(
            panAdminLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panAdminLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panAdminLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(btnCrearUsuarioAdmin, javax.swing.GroupLayout.DEFAULT_SIZE, 179, Short.MAX_VALUE)
                    .addComponent(btnEditarUsuarioAdmin, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(18, 18, 18)
                .addComponent(btnConsultarPermisosAdmin, javax.swing.GroupLayout.DEFAULT_SIZE, 169, Short.MAX_VALUE)
                .addContainerGap())
        );
        panAdminLayout.setVerticalGroup(
            panAdminLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panAdminLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panAdminLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(panAdminLayout.createSequentialGroup()
                        .addComponent(btnCrearUsuarioAdmin)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnEditarUsuarioAdmin))
                    .addComponent(btnConsultarPermisosAdmin, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        tabOpciones.addTab("Opciones", panAdmin);

        btnCrearPermiso.setText("Crear Permiso");
        btnCrearPermiso.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCrearPermisoActionPerformed(evt);
            }
        });

        btnConsultarPermisosUsuario.setText("Consultar Permisos");
        btnConsultarPermisosUsuario.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnConsultarPermisosUsuarioActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout panUsuarioLayout = new javax.swing.GroupLayout(panUsuario);
        panUsuario.setLayout(panUsuarioLayout);
        panUsuarioLayout.setHorizontalGroup(
            panUsuarioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panUsuarioLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(btnCrearPermiso, javax.swing.GroupLayout.PREFERRED_SIZE, 175, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnConsultarPermisosUsuario, javax.swing.GroupLayout.DEFAULT_SIZE, 186, Short.MAX_VALUE)
                .addContainerGap())
        );
        panUsuarioLayout.setVerticalGroup(
            panUsuarioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panUsuarioLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panUsuarioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(btnConsultarPermisosUsuario, javax.swing.GroupLayout.DEFAULT_SIZE, 54, Short.MAX_VALUE)
                    .addComponent(btnCrearPermiso, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        tabOpciones.addTab("Opciones", panUsuario);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(tabOpciones)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(outPerfil)
                            .addComponent(lblPerfil, javax.swing.GroupLayout.DEFAULT_SIZE, 139, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblDocumentoIdentificacion, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(outDocumentoIdentificacion)))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(lblTelefono1, javax.swing.GroupLayout.DEFAULT_SIZE, 139, Short.MAX_VALUE)
                            .addComponent(outTelefono))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(outDireccion)
                            .addComponent(lblDireccion, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addComponent(outNombreCompleto, javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                        .addComponent(lblNombresCompletos, javax.swing.GroupLayout.PREFERRED_SIZE, 390, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(outEmail, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblEmail, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 214, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblEmail1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(outFechaRegistro))))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblPerfil)
                    .addComponent(lblDocumentoIdentificacion))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(outPerfil, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(outDocumentoIdentificacion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblNombresCompletos)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(outNombreCompleto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblTelefono1)
                    .addComponent(lblDireccion))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(outTelefono, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(outDireccion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblEmail)
                    .addComponent(lblEmail1))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(outEmail, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(outFechaRegistro, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(tabOpciones, javax.swing.GroupLayout.PREFERRED_SIZE, 103, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void btnCrearUsuarioAdminActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCrearUsuarioAdminActionPerformed
        frmUsuario ugui = new frmUsuario();
        frmUsuario.setIdPerfilLogeado(u.getUsuPerfil());
        ugui.init("Crear", frmInicio.this);
    }//GEN-LAST:event_btnCrearUsuarioAdminActionPerformed

    private void btnEditarUsuarioAdminActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEditarUsuarioAdminActionPerformed
        frmUsuario ugui = new frmUsuario();
        frmUsuario.setIdPerfilLogeado(u.getUsuPerfil());
        frmUsuario.setUsuarioLogeado(u);

        ugui.init("Editar", frmInicio.this);
    }//GEN-LAST:event_btnEditarUsuarioAdminActionPerformed

    private void btnConsultarPermisosAdminActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnConsultarPermisosAdminActionPerformed
        frmPermisos.setUsuarioLogeado(u);

        frmPermisos.init();

    }//GEN-LAST:event_btnConsultarPermisosAdminActionPerformed

    private void btnConsultarPermisosUsuarioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnConsultarPermisosUsuarioActionPerformed
        frmPermisos.setUsuarioLogeado(u);
        
        frmPermisos.init(IdUsuarioLogeado);
    }//GEN-LAST:event_btnConsultarPermisosUsuarioActionPerformed

    private void btnCrearPermisoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCrearPermisoActionPerformed
        frmPermiso.init(0, u);

    }//GEN-LAST:event_btnCrearPermisoActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnConsultarPermisosAdmin;
    private javax.swing.JButton btnConsultarPermisosUsuario;
    private javax.swing.JButton btnCrearPermiso;
    private javax.swing.JButton btnCrearUsuarioAdmin;
    private javax.swing.JButton btnEditarUsuarioAdmin;
    private javax.swing.JLabel lblDireccion;
    private javax.swing.JLabel lblDocumentoIdentificacion;
    private javax.swing.JLabel lblEmail;
    private javax.swing.JLabel lblEmail1;
    private javax.swing.JLabel lblNombresCompletos;
    private javax.swing.JLabel lblPerfil;
    private javax.swing.JLabel lblTelefono1;
    private javax.swing.JTextField outDireccion;
    private javax.swing.JTextField outDocumentoIdentificacion;
    private javax.swing.JTextField outEmail;
    private javax.swing.JTextField outFechaRegistro;
    private javax.swing.JTextField outNombreCompleto;
    private javax.swing.JTextField outPerfil;
    private javax.swing.JTextField outTelefono;
    private javax.swing.JPanel panAdmin;
    private javax.swing.JPanel panUsuario;
    private javax.swing.JTabbedPane tabOpciones;
    // End of variables declaration//GEN-END:variables

}
