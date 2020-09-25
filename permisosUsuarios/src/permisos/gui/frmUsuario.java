/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package permisos.gui;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;
import permisos.gui.logica.CommTablaLogic;
import permisos.gui.logica.UsuarioLogic;
import permisos.gui.logica.entidades.EstadoPermiso;
import permisos.gui.logica.entidades.Perfil;
import permisos.gui.logica.entidades.TipoDocumento;
import permisos.gui.logica.entidades.TipoPermiso;
import permisos.gui.logica.entidades.Usuario;
import permisos.utilitario.Encriptar;

/**
 *
 * @author Kevin Camilo Gómez González - kgomezg81832@universidadean.edu.co
 */
public class frmUsuario extends javax.swing.JFrame {

    private final Encriptar e = new Encriptar();

    private static frmInicio frmIni;
    private static Usuario UsuarioLogeado;
    private static int IdPerfilLogeado = 0;

    private static final CommTablaLogic ctl = new CommTablaLogic();
    private static final UsuarioLogic ul = new UsuarioLogic();

    private List<EstadoPermiso> estadosPermmisos = new ArrayList<EstadoPermiso>();
    private List<Perfil> perfiles = new ArrayList<Perfil>();
    private List<TipoDocumento> tiposDocumentos = new ArrayList<TipoDocumento>();
    private List<TipoPermiso> tiposPermisos = new ArrayList<TipoPermiso>();

    /**
     * Creates new form frmUsuario
     */
    public frmUsuario() {
        initComponents();

        // Agrego validación de cerrar sesión.
        frmUsuario.this.addWindowListener(new java.awt.event.WindowAdapter() {
            @Override
            public void windowClosing(java.awt.event.WindowEvent windowEvent) {
                Object[] opciones = {"Aceptar", "Cancelar"};

                if (JOptionPane.showOptionDialog(
                        frmUsuario.this, "Señor(a) Usuario(a):\n¿Está seguro de volver atrás?\nSi lo esta de click en Aceptar de lo contrario de click en Cancelar.", "Confirmación",
                        0, JOptionPane.INFORMATION_MESSAGE, null, opciones, null) == 0) {
                    frmUsuario.this.setVisible(false);
                    setIdPerfilLogeado(0);
                }
            }
        });

        cargarEstadosPermisos();
        cargarPerfiles();
        cargarTiposDocumentos();
        cargarTiposPermisos();
    }

    public void desinit() {
        setVisible(false);
        frmInicio.IdUsuarioLogeado = UsuarioLogeado.getUsuId();
        frmInicio.u = frmIni.consultaUsuario(IdPerfilLogeado);
        frmIni.CargarInformacion();
    }

    public void init(String Proceso, frmInicio _frmIni) {
        frmIni = _frmIni;

        if (getIdPerfilLogeado() == 1) { // Administrador

            switch (Proceso) {
                case "Crear" -> {
                    setTitle("Creación de Usuario");

                    outDocumentoIdentificacion.setEnabled(true);
                    outPrimerNombre.setEnabled(true);
                    outSegundoNombre.setEnabled(true);
                    outPrimerApellido.setEnabled(true);
                    outSegundoApellido.setEnabled(true);
                    outTelefono.setEnabled(true);
                    outEmail.setEnabled(true);
                    outDireccion.setEnabled(true);
                    outUsuario.setEnabled(true);
                    outClave.setEnabled(true);

                    slcPerfil.setEnabled(true);
                    slcTipoDocumento.setEnabled(true);

                    btnCrearUsuarioAdmin.setEnabled(true);

                    CargarInformacion();
                }
                case "Editar" -> {
                    setTitle("Edición de Usuario");

                    outPrimerNombre.setEnabled(true);
                    outSegundoNombre.setEnabled(true);
                    outPrimerApellido.setEnabled(true);
                    outSegundoApellido.setEnabled(true);
                    outTelefono.setEnabled(true);
                    outEmail.setEnabled(true);
                    outDireccion.setEnabled(true);

                    outClave.setEnabled(false);
                    outDocumentoIdentificacion.setEnabled(false);
                    outUsuario.setEnabled(false);
                    slcPerfil.setEnabled(false);
                    slcTipoDocumento.setEnabled(false);

                    btnEditarInformacionAdmin.setEnabled(true);

                    CargarInformacion(getUsuarioLogeado());
                }
            }

        } else if (getIdPerfilLogeado() == 2) { // Usuario

            switch (Proceso) {
                case "Editar" -> {
                    setTitle("Edición de Usuario");

                    outPrimerNombre.setEnabled(true);
                    outSegundoNombre.setEnabled(true);
                    outPrimerApellido.setEnabled(true);
                    outSegundoApellido.setEnabled(true);
                    outTelefono.setEnabled(true);
                    outEmail.setEnabled(true);
                    outDireccion.setEnabled(true);

                    outClave.setEnabled(false);
                    outDocumentoIdentificacion.setEnabled(false);
                    outUsuario.setEnabled(false);
                    slcPerfil.setEnabled(false);
                    slcTipoDocumento.setEnabled(false);

                    btnEditarInformacionUsuario.setEnabled(true);

                    CargarInformacion(getUsuarioLogeado());
                }
            }
        }

        setVisible(true);
    }

    private void CargarInformacion() {
        outClave.setText("");
        outPrimerNombre.setText("");
        outSegundoNombre.setText("");
        outPrimerApellido.setText("");
        outSegundoApellido.setText("");
        outUsuario.setText("");
        outFechaRegistro.setText(new SimpleDateFormat("dd/MM/yyyy").format(new Date()));
        outEmail.setText("");
        outTelefono.setText("");
        outDireccion.setText("");
        outDocumentoIdentificacion.setText("");

        slcPerfil.setSelectedItem(perfiles.get(2).getPerfil());
        slcTipoDocumento.setSelectedItem(tiposDocumentos.get(0).getDescripcion());

        if (getIdPerfilLogeado() == 1) {
            tabOpciones.remove(panUsuario);
        } else if (getIdPerfilLogeado() == 2) {
            tabOpciones.remove(panAdmin);
        }
    }

    private void CargarInformacion(Usuario u) {
        outUsuario.setText(u.getUsuLogin());
        outClave.setText(u.getUsuClave());
        outPrimerNombre.setText(u.getUsuPrimerNombre());
        outSegundoNombre.setText((u.getUsuSegundoNombre().equals("") ? "" : u.getUsuSegundoNombre()));
        outPrimerApellido.setText(u.getUsuPrimerApellido());
        outSegundoApellido.setText((u.getUsuSegundoApellido().equals("") ? "" : u.getUsuSegundoApellido()));
        outFechaRegistro.setText(new SimpleDateFormat("dd/MM/yyyy").format(u.getUsuFechaRegistro()));
        outEmail.setText(u.getUsuEmail());
        outTelefono.setText(u.getUsuTelefono());
        outDireccion.setText(u.getUsuDireccion());
        outDocumentoIdentificacion.setText(u.getUsuIden());

        slcPerfil.setSelectedItem(perfiles.get(u.getUsuPerfil()).getPerfil());
        slcTipoDocumento.setSelectedItem(tiposDocumentos.get(u.getUsuIdenTipo()).getDescripcion());

        if (getIdPerfilLogeado() == 1) {
            tabOpciones.remove(panUsuario);
        } else if (getIdPerfilLogeado() == 2) {
            tabOpciones.remove(panAdmin);
        }
    }

    private void cargarEstadosPermisos() {
        estadosPermmisos = ctl.consultarEstadosPermiso();
    }

    private void cargarPerfiles() {
        perfiles = ctl.consultarPerfiles();

        ArrayList<String> alTD = new ArrayList<>();

        perfiles.forEach(tp -> {
            alTD.add(tp.getPerfil());
        });

        DefaultComboBoxModel<String> dcbm = new DefaultComboBoxModel(alTD.toArray());
        slcPerfil.setModel(dcbm);
    }

    private void cargarTiposDocumentos() {
        tiposDocumentos = ctl.consultarTiposDocumento();

        ArrayList<String> alTD = new ArrayList<>();

        tiposDocumentos.forEach(tp -> {
            alTD.add(tp.getDescripcion());
        });

        DefaultComboBoxModel<String> dcbm = new DefaultComboBoxModel(alTD.toArray());
        slcTipoDocumento.setModel(dcbm);
    }

    private void cargarTiposPermisos() {
        tiposPermisos = ctl.consultarTiposPermiso();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        outDocumentoIdentificacion = new javax.swing.JTextField();
        tabOpciones = new javax.swing.JTabbedPane();
        panAdmin = new javax.swing.JPanel();
        btnCrearUsuarioAdmin = new javax.swing.JButton();
        btnEditarInformacionAdmin = new javax.swing.JButton();
        btnCancelarAccionAdmin = new javax.swing.JButton();
        panUsuario = new javax.swing.JPanel();
        btnEditarInformacionUsuario = new javax.swing.JButton();
        btnCancelarUsuario = new javax.swing.JButton();
        lblPerfil = new javax.swing.JLabel();
        lblDireccion = new javax.swing.JLabel();
        outDireccion = new javax.swing.JTextField();
        lblEmail = new javax.swing.JLabel();
        outEmail = new javax.swing.JTextField();
        lblTelefono = new javax.swing.JLabel();
        outPrimerNombre = new javax.swing.JTextField();
        outTelefono = new javax.swing.JTextField();
        lblPrimerNombre = new javax.swing.JLabel();
        lblFechaRegistro = new javax.swing.JLabel();
        lblDocumentoIdentificacion = new javax.swing.JLabel();
        outFechaRegistro = new javax.swing.JTextField();
        lblTipoIdentidad = new javax.swing.JLabel();
        slcTipoDocumento = new javax.swing.JComboBox<>();
        slcPerfil = new javax.swing.JComboBox<>();
        lblUsuario = new javax.swing.JLabel();
        outUsuario = new javax.swing.JTextField();
        lblSegundoNombre = new javax.swing.JLabel();
        outSegundoNombre = new javax.swing.JTextField();
        lblPrimerApellido = new javax.swing.JLabel();
        outPrimerApellido = new javax.swing.JTextField();
        lblSegundoApellido = new javax.swing.JLabel();
        outSegundoApellido = new javax.swing.JTextField();
        lblClave = new javax.swing.JLabel();
        outClave = new javax.swing.JPasswordField();

        setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);
        setTitle("Usuario");
        setName("Usuario"); // NOI18N
        setResizable(false);

        outDocumentoIdentificacion.setBackground(new java.awt.Color(255, 255, 255));
        outDocumentoIdentificacion.setDisabledTextColor(new java.awt.Color(0, 0, 0));
        outDocumentoIdentificacion.setEnabled(false);

        btnCrearUsuarioAdmin.setText("Crear Usuario");
        btnCrearUsuarioAdmin.setEnabled(false);
        btnCrearUsuarioAdmin.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCrearUsuarioAdminActionPerformed(evt);
            }
        });

        btnEditarInformacionAdmin.setText("Editar Usuario");
        btnEditarInformacionAdmin.setEnabled(false);
        btnEditarInformacionAdmin.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEditarInformacionAdminActionPerformed(evt);
            }
        });

        btnCancelarAccionAdmin.setText("Cancelar");
        btnCancelarAccionAdmin.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCancelarAccionAdminActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout panAdminLayout = new javax.swing.GroupLayout(panAdmin);
        panAdmin.setLayout(panAdminLayout);
        panAdminLayout.setHorizontalGroup(
            panAdminLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panAdminLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panAdminLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panAdminLayout.createSequentialGroup()
                        .addComponent(btnCrearUsuarioAdmin, javax.swing.GroupLayout.PREFERRED_SIZE, 179, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnEditarInformacionAdmin, javax.swing.GroupLayout.DEFAULT_SIZE, 380, Short.MAX_VALUE))
                    .addComponent(btnCancelarAccionAdmin, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        panAdminLayout.setVerticalGroup(
            panAdminLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panAdminLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panAdminLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnCrearUsuarioAdmin)
                    .addComponent(btnEditarInformacionAdmin))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnCancelarAccionAdmin, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        tabOpciones.addTab("Opciones", panAdmin);

        btnEditarInformacionUsuario.setText("Editar Información");

        btnCancelarUsuario.setText("Cancelar");
        btnCancelarUsuario.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCancelarUsuarioActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout panUsuarioLayout = new javax.swing.GroupLayout(panUsuario);
        panUsuario.setLayout(panUsuarioLayout);
        panUsuarioLayout.setHorizontalGroup(
            panUsuarioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panUsuarioLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(btnEditarInformacionUsuario, javax.swing.GroupLayout.PREFERRED_SIZE, 175, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnCancelarUsuario, javax.swing.GroupLayout.DEFAULT_SIZE, 384, Short.MAX_VALUE)
                .addContainerGap())
        );
        panUsuarioLayout.setVerticalGroup(
            panUsuarioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panUsuarioLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panUsuarioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(btnCancelarUsuario, javax.swing.GroupLayout.DEFAULT_SIZE, 54, Short.MAX_VALUE)
                    .addComponent(btnEditarInformacionUsuario, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        tabOpciones.addTab("Opciones", panUsuario);

        lblPerfil.setText("Perfil:");

        lblDireccion.setText("Dirección:");

        outDireccion.setBackground(new java.awt.Color(255, 255, 255));
        outDireccion.setDisabledTextColor(new java.awt.Color(0, 0, 0));
        outDireccion.setEnabled(false);

        lblEmail.setText("Email:");

        outEmail.setBackground(new java.awt.Color(255, 255, 255));
        outEmail.setDisabledTextColor(new java.awt.Color(0, 0, 0));
        outEmail.setEnabled(false);

        lblTelefono.setText("Teléfono:");

        outPrimerNombre.setBackground(new java.awt.Color(255, 255, 255));
        outPrimerNombre.setDisabledTextColor(new java.awt.Color(0, 0, 0));
        outPrimerNombre.setEnabled(false);

        outTelefono.setBackground(new java.awt.Color(255, 255, 255));
        outTelefono.setDisabledTextColor(new java.awt.Color(0, 0, 0));
        outTelefono.setEnabled(false);

        lblPrimerNombre.setText("Primer Nombre:");

        lblFechaRegistro.setText("Fecha Registro:");

        lblDocumentoIdentificacion.setText("Documento Identificación:");

        outFechaRegistro.setBackground(new java.awt.Color(255, 255, 255));
        outFechaRegistro.setDisabledTextColor(new java.awt.Color(0, 0, 0));
        outFechaRegistro.setEnabled(false);

        lblTipoIdentidad.setText("Tipo Documento de Identidad:");

        slcTipoDocumento.setEnabled(false);

        slcPerfil.setEnabled(false);

        lblUsuario.setText("Usuario:");

        outUsuario.setBackground(new java.awt.Color(255, 255, 255));
        outUsuario.setDisabledTextColor(new java.awt.Color(0, 0, 0));
        outUsuario.setEnabled(false);

        lblSegundoNombre.setText("Segundo Nombre:");

        outSegundoNombre.setBackground(new java.awt.Color(255, 255, 255));
        outSegundoNombre.setDisabledTextColor(new java.awt.Color(0, 0, 0));
        outSegundoNombre.setEnabled(false);

        lblPrimerApellido.setText("Primer Apellido:");

        outPrimerApellido.setBackground(new java.awt.Color(255, 255, 255));
        outPrimerApellido.setDisabledTextColor(new java.awt.Color(0, 0, 0));
        outPrimerApellido.setEnabled(false);

        lblSegundoApellido.setText("Segundo Apellido:");

        outSegundoApellido.setBackground(new java.awt.Color(255, 255, 255));
        outSegundoApellido.setDisabledTextColor(new java.awt.Color(0, 0, 0));
        outSegundoApellido.setEnabled(false);

        lblClave.setText("Clave:");

        outClave.setBackground(new java.awt.Color(255, 255, 255));
        outClave.setDisabledTextColor(new java.awt.Color(0, 0, 0));
        outClave.setEnabled(false);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(tabOpciones, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(lblPrimerNombre, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(lblTelefono, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 139, Short.MAX_VALUE)
                            .addComponent(outTelefono, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(outPrimerNombre, javax.swing.GroupLayout.Alignment.LEADING))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(outEmail)
                                    .addComponent(lblEmail, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(lblDireccion, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(outDireccion)))
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(outSegundoNombre)
                                    .addComponent(lblSegundoNombre, javax.swing.GroupLayout.DEFAULT_SIZE, 139, Short.MAX_VALUE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(outPrimerApellido)
                                    .addComponent(lblPrimerApellido, javax.swing.GroupLayout.PREFERRED_SIZE, 139, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(outSegundoApellido)
                                    .addComponent(lblSegundoApellido, javax.swing.GroupLayout.PREFERRED_SIZE, 139, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(0, 0, Short.MAX_VALUE))))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(lblPerfil, javax.swing.GroupLayout.DEFAULT_SIZE, 139, Short.MAX_VALUE)
                            .addComponent(slcPerfil, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(outUsuario)
                            .addComponent(lblUsuario, javax.swing.GroupLayout.DEFAULT_SIZE, 176, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblClave, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(outClave)))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(lblDocumentoIdentificacion, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 139, Short.MAX_VALUE)
                            .addComponent(outDocumentoIdentificacion, javax.swing.GroupLayout.Alignment.LEADING))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblTipoIdentidad, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(slcTipoDocumento, javax.swing.GroupLayout.PREFERRED_SIZE, 176, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(outFechaRegistro)
                            .addComponent(lblFechaRegistro, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(17, 17, 17)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addComponent(slcPerfil, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGroup(layout.createSequentialGroup()
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(lblUsuario)
                                .addComponent(lblPerfil))
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(outUsuario, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(outClave, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addComponent(lblClave))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblDocumentoIdentificacion)
                    .addComponent(lblTipoIdentidad)
                    .addComponent(lblFechaRegistro))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(outFechaRegistro, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(outDocumentoIdentificacion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(slcTipoDocumento, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(lblPrimerNombre)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(outPrimerNombre, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(lblSegundoNombre)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(outSegundoNombre, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(lblPrimerApellido)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(outPrimerApellido, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(lblTelefono)
                                    .addComponent(lblDireccion))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(outTelefono, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(outDireccion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(lblEmail)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(outEmail, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(18, 18, 18)
                        .addComponent(tabOpciones, javax.swing.GroupLayout.PREFERRED_SIZE, 103, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(lblSegundoApellido)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(outSegundoApellido, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void btnCancelarAccionAdminActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelarAccionAdminActionPerformed
        Object[] opciones = {"Aceptar", "Cancelar"};

        if (JOptionPane.showOptionDialog(
                frmUsuario.this, "Señor(a) Usuario(a):\n¿Está seguro de volver atrás?\nSi lo esta de click en Aceptar de lo contrario de click en Cancelar.", "Confirmación",
                0, JOptionPane.INFORMATION_MESSAGE, null, opciones, null) == 0) {
            frmUsuario.this.setVisible(false);
            setIdPerfilLogeado(0);
        }
    }//GEN-LAST:event_btnCancelarAccionAdminActionPerformed

    private void btnCancelarUsuarioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelarUsuarioActionPerformed
        Object[] opciones = {"Aceptar", "Cancelar"};

        if (JOptionPane.showOptionDialog(
                frmUsuario.this, "Señor(a) Usuario(a):\n¿Está seguro de volver atrás?\nSi lo esta de click en Aceptar de lo contrario de click en Cancelar.", "Confirmación",
                0, JOptionPane.INFORMATION_MESSAGE, null, opciones, null) == 0) {
            frmUsuario.this.setVisible(false);
            setIdPerfilLogeado(0);
        }
    }//GEN-LAST:event_btnCancelarUsuarioActionPerformed

    private void btnCrearUsuarioAdminActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCrearUsuarioAdminActionPerformed
        Usuario u = new Usuario(
                0,
                outUsuario.getText(),
                outDocumentoIdentificacion.getText(),
                tiposDocumentos.get(slcTipoDocumento.getSelectedIndex()).getId(),
                outPrimerApellido.getText(),
                outSegundoNombre.getText(),
                outPrimerApellido.getText(),
                outSegundoApellido.getText(),
                outDireccion.getText(),
                outTelefono.getText(),
                outEmail.getText(),
                perfiles.get(slcPerfil.getSelectedIndex()).getId(),
                new Date(),
                e.encriptarMD5(outClave.getText())
        );

        if (u.getUsuIdenTipo() == 0) {
            JOptionPane.showMessageDialog(null, "Señor(a) Usuario(a): Por favor seleccione un Tipo de Identficación valida.");
        } else if (u.getUsuPerfil() == 0) {
            JOptionPane.showMessageDialog(null, "Señor(a) Usuario(a): Por favor seleccione un Tipo de Perfil valido.");
        } else {
            ul.GuardaEditaUsuario(IdPerfilLogeado, u);

            JOptionPane.showMessageDialog(null, "Señor(a) Usuario(a): Cambios guardados con exito.");
            desinit();
        }
    }//GEN-LAST:event_btnCrearUsuarioAdminActionPerformed

    private void btnEditarInformacionAdminActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEditarInformacionAdminActionPerformed
        Usuario u = new Usuario(
                0,
                outUsuario.getText(),
                outDocumentoIdentificacion.getText(),
                tiposDocumentos.get(slcTipoDocumento.getSelectedIndex()).getId(),
                outPrimerApellido.getText(),
                outSegundoNombre.getText(),
                outPrimerApellido.getText(),
                outSegundoApellido.getText(),
                outDireccion.getText(),
                outTelefono.getText(),
                outEmail.getText(),
                perfiles.get(slcPerfil.getSelectedIndex()).getId(),
                new Date(),
                outClave.getText()
        );

        if (u.getUsuIdenTipo() == 0) {
            JOptionPane.showMessageDialog(null, "Señor(a) Usuario(a): Por favor seleccione un Tipo de Identficación valida.");
        } else if (u.getUsuPerfil() == 0) {
            JOptionPane.showMessageDialog(null, "Señor(a) Usuario(a): Por favor seleccione un Tipo de Perfil valido.");
        } else {
            ul.GuardaEditaUsuario(IdPerfilLogeado, u);

            JOptionPane.showMessageDialog(null, "Señor(a) Usuario(a): Cambios guardados con exito.");
            desinit();
        }
    }//GEN-LAST:event_btnEditarInformacionAdminActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnCancelarAccionAdmin;
    private javax.swing.JButton btnCancelarUsuario;
    private javax.swing.JButton btnCrearUsuarioAdmin;
    private javax.swing.JButton btnEditarInformacionAdmin;
    private javax.swing.JButton btnEditarInformacionUsuario;
    private javax.swing.JLabel lblClave;
    private javax.swing.JLabel lblDireccion;
    private javax.swing.JLabel lblDocumentoIdentificacion;
    private javax.swing.JLabel lblEmail;
    private javax.swing.JLabel lblFechaRegistro;
    private javax.swing.JLabel lblPerfil;
    private javax.swing.JLabel lblPrimerApellido;
    private javax.swing.JLabel lblPrimerNombre;
    private javax.swing.JLabel lblSegundoApellido;
    private javax.swing.JLabel lblSegundoNombre;
    private javax.swing.JLabel lblTelefono;
    private javax.swing.JLabel lblTipoIdentidad;
    private javax.swing.JLabel lblUsuario;
    private javax.swing.JPasswordField outClave;
    private javax.swing.JTextField outDireccion;
    private javax.swing.JTextField outDocumentoIdentificacion;
    private javax.swing.JTextField outEmail;
    private javax.swing.JTextField outFechaRegistro;
    private javax.swing.JTextField outPrimerApellido;
    private javax.swing.JTextField outPrimerNombre;
    private javax.swing.JTextField outSegundoApellido;
    private javax.swing.JTextField outSegundoNombre;
    private javax.swing.JTextField outTelefono;
    private javax.swing.JTextField outUsuario;
    private javax.swing.JPanel panAdmin;
    private javax.swing.JPanel panUsuario;
    private javax.swing.JComboBox<String> slcPerfil;
    private javax.swing.JComboBox<String> slcTipoDocumento;
    private javax.swing.JTabbedPane tabOpciones;
    // End of variables declaration//GEN-END:variables

    /**
     * @return the IdPerfilLogeado
     */
    public static int getIdPerfilLogeado() {
        return IdPerfilLogeado;
    }

    /**
     * @param aIdPerfilLogeado the IdPerfilLogeado to set
     */
    public static void setIdPerfilLogeado(int aIdPerfilLogeado) {
        IdPerfilLogeado = aIdPerfilLogeado;
    }

    /**
     * @return the usuLog
     */
    public static Usuario getUsuarioLogeado() {
        return UsuarioLogeado;
    }

    /**
     * @param aUsuLog the usuLog to set
     */
    public static void setUsuarioLogeado(Usuario aUsuLog) {
        UsuarioLogeado = aUsuLog;
    }
}
