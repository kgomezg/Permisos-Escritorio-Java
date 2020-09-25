package permisos.gui.logica.entidades;

import java.util.Date;

/**
 *
 * @author Kevin Camilo Gómez González - kgomezg81832@universidadean.edu.co
 * @date 2020.09.19
 * @desc 
 */
public final class Usuario {

    private int usuId;
    private String usuLogin;
    private String usuIden;
    private int usuIdenTipo;
    private String usuPrimerNombre;
    private String usuSegundoNombre;
    private String usuPrimerApellido;
    private String usuSegundoApellido;
    private String usuDireccion;
    private String usuTelefono;
    private String usuEmail;
    private String usuClave;
    private int usuPerfil;
    private Date usuFechaRegistro;

    public Usuario() {
        setUsuId(0);
        setUsuLogin("");
        setUsuIden("");
        setUsuIdenTipo(1);
        setUsuPrimerNombre("");
        setUsuSegundoNombre("");
        setUsuPrimerApellido("");
        setUsuSegundoApellido("");
        setUsuDireccion("");
        setUsuTelefono("");
        setUsuEmail("");
        setUsuPerfil(1);
        setUsuFechaRegistro(new Date());
        setUsuClave("");
    }

    public Usuario(int _usuId, String _usuLogin, String _usuIden, int _usuIdenTipo, String _usuPrimerNombre, String _usuSegundoNombre, String _usuPrimerApellido, String _usuSegundoApellido, String _usuDireccion, String _usuTelefono, String _usuEmail, int _usuPerfil, Date _usuFechaRegistro, String _usuClave) {
        setUsuId(_usuId);
        setUsuLogin(_usuLogin);
        setUsuIden(_usuIden);
        setUsuIdenTipo(_usuIdenTipo);
        setUsuPrimerNombre(_usuPrimerNombre);
        setUsuSegundoNombre(_usuSegundoNombre);
        setUsuPrimerApellido(_usuPrimerApellido);
        setUsuSegundoApellido(_usuSegundoApellido);
        setUsuDireccion(_usuDireccion);
        setUsuTelefono(_usuTelefono);
        setUsuEmail(_usuEmail);
        setUsuPerfil(_usuPerfil);
        setUsuFechaRegistro(_usuFechaRegistro);
        setUsuClave(_usuClave);
    }

    public void imprimir() {
        String s = "";

        s += "{";
        s += "UsuId: " + getUsuId() + ",";
        s += "UsuLogin: " + getUsuLogin() + ",";
        s += "UsuIden: " + getUsuIden() + ",";
        s += "UsuIdenTipo: " + getUsuIdenTipo() + ",";
        s += "UsuPrimerNombre: " + getUsuPrimerNombre() + ",";
        s += "UsuSegundoNombre: " + getUsuSegundoNombre() + ",";
        s += "UsuPrimerApellido: " + getUsuPrimerApellido() + ",";
        s += "UsuSegundoApellido: " + getUsuSegundoApellido() + ",";
        s += "UsuDireccion: " + getUsuDireccion() + ",";
        s += "UsuTelefono: " + getUsuTelefono() + ",";
        s += "UsuEmail: " + getUsuEmail() + ",";
        s += "UsuPerfil: " + getUsuPerfil() + ",";
        s += "UsuFechaRegistro: " + getUsuFechaRegistro().toString() + "";
        s += "}";

        System.out.println(s);
    }

    /**
     * @return the usuId
     */
    public int getUsuId() {
        return usuId;
    }

    /**
     * @param usuId the usuId to set
     */
    public void setUsuId(int usuId) {
        this.usuId = usuId;
    }

    /**
     * @return the usuLogin
     */
    public String getUsuLogin() {
        return usuLogin;
    }

    /**
     * @param usuLogin the usuLogin to set
     */
    public void setUsuLogin(String usuLogin) {
        this.usuLogin = usuLogin;
    }

    /**
     * @return the usuIden
     */
    public String getUsuIden() {
        return usuIden;
    }

    /**
     * @param usuIden the usuIden to set
     */
    public void setUsuIden(String usuIden) {
        this.usuIden = usuIden;
    }

    /**
     * @return the usuIdenTipo
     */
    public int getUsuIdenTipo() {
        return usuIdenTipo;
    }

    /**
     * @param usuIdenTipo the usuIdenTipo to set
     */
    public void setUsuIdenTipo(int usuIdenTipo) {
        this.usuIdenTipo = usuIdenTipo;
    }

    /**
     * @return the usuPrimerNombre
     */
    public String getUsuPrimerNombre() {
        return usuPrimerNombre;
    }

    /**
     * @param usuPrimerNombre the usuPrimerNombre to set
     */
    public void setUsuPrimerNombre(String usuPrimerNombre) {
        this.usuPrimerNombre = usuPrimerNombre;
    }

    /**
     * @return the usuSegundoNombre
     */
    public String getUsuSegundoNombre() {
        return usuSegundoNombre;
    }

    /**
     * @param usuSegundoNombre the usuSegundoNombre to set
     */
    public void setUsuSegundoNombre(String usuSegundoNombre) {
        this.usuSegundoNombre = usuSegundoNombre;
    }

    /**
     * @return the usuPrimerApellido
     */
    public String getUsuPrimerApellido() {
        return usuPrimerApellido;
    }

    /**
     * @param usuPrimerApellido the usuPrimerApellido to set
     */
    public void setUsuPrimerApellido(String usuPrimerApellido) {
        this.usuPrimerApellido = usuPrimerApellido;
    }

    /**
     * @return the usuSegundoApellido
     */
    public String getUsuSegundoApellido() {
        return usuSegundoApellido;
    }

    /**
     * @param usuSegundoApellido the usuSegundoApellido to set
     */
    public void setUsuSegundoApellido(String usuSegundoApellido) {
        this.usuSegundoApellido = usuSegundoApellido;
    }

    /**
     * @return the usuDireccion
     */
    public String getUsuDireccion() {
        return usuDireccion;
    }

    /**
     * @param usuDireccion the usuDireccion to set
     */
    public void setUsuDireccion(String usuDireccion) {
        this.usuDireccion = usuDireccion;
    }

    /**
     * @return the usuTelefono
     */
    public String getUsuTelefono() {
        return usuTelefono;
    }

    /**
     * @param usuTelefono the usuTelefono to set
     */
    public void setUsuTelefono(String usuTelefono) {
        this.usuTelefono = usuTelefono;
    }

    /**
     * @return the usuEmail
     */
    public String getUsuEmail() {
        return usuEmail;
    }

    /**
     * @param usuEmail the usuEmail to set
     */
    public void setUsuEmail(String usuEmail) {
        this.usuEmail = usuEmail;
    }

    /**
     * @return the usuPerfil
     */
    public int getUsuPerfil() {
        return usuPerfil;
    }

    /**
     * @param usuPerfil the usuPerfil to set
     */
    public void setUsuPerfil(int usuPerfil) {
        this.usuPerfil = usuPerfil;
    }

    /**
     * @return the usuFechaRegistro
     */
    public Date getUsuFechaRegistro() {
        return usuFechaRegistro;
    }

    /**
     * @param usuFechaRegistro the usuFechaRegistro to set
     */
    public void setUsuFechaRegistro(Date usuFechaRegistro) {
        this.usuFechaRegistro = usuFechaRegistro;
    }

    /**
     * @return the usuClave
     */
    public String getUsuClave() {
        return usuClave;
    }

    /**
     * @param usuClave the usuClave to set
     */
    public void setUsuClave(String usuClave) {
        this.usuClave = usuClave;
    }

}
