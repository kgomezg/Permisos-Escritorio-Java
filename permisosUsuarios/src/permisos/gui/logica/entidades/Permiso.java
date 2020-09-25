/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package permisos.gui.logica.entidades;

import java.util.Date;

/**
 *
 * @author Kevin Camilo Gómez González - kgomezg81832@universidadean.edu.co
 * @date 2020.09.19
 * @desc 
 */
public final class Permiso {

    private int Id;
    private int IdUsuario;
    private int TipoPermiso;
    private Date Inicio;
    private Date Fin;
    private String ObsUsuario;
    private String ObsAdministrador;
    private int Estado;
    private Date FechaAccion;


    public Permiso() {

    }

    public Permiso(int _Id, int _IdUsuario, int _TipoPermiso, Date _Inicio, Date _Fin, String _ObsUsuario, String _ObsAdministrador, int _Estado, Date _FechaAccion) {
        setId(_Id);
        setIdUsuario(_IdUsuario);
        setTipoPermiso(_TipoPermiso);
        setInicio(_Inicio);
        setFin(_Fin);
        setObsUsuario(_ObsUsuario);
        setObsAdministrador(_ObsAdministrador);
        setEstado(_Estado);
        setFechaAccion(_FechaAccion);
    }

    /**
     * @return the Id
     */
    public int getId() {
        return Id;
    }

    /**
     * @param Id the Id to set
     */
    public void setId(int Id) {
        this.Id = Id;
    }

    /**
     * @return the IdUsuario
     */
    public int getIdUsuario() {
        return IdUsuario;
    }

    /**
     * @param IdUsuario the IdUsuario to set
     */
    public void setIdUsuario(int IdUsuario) {
        this.IdUsuario = IdUsuario;
    }

    /**
     * @return the TipoPermiso
     */
    public int getTipoPermiso() {
        return TipoPermiso;
    }

    /**
     * @param TipoPermiso the TipoPermiso to set
     */
    public void setTipoPermiso(int TipoPermiso) {
        this.TipoPermiso = TipoPermiso;
    }

    /**
     * @return the Inicio
     */
    public Date getInicio() {
        return Inicio;
    }

    /**
     * @param Inicio the Inicio to set
     */
    public void setInicio(Date Inicio) {
        this.Inicio = Inicio;
    }

    /**
     * @return the Fin
     */
    public Date getFin() {
        return Fin;
    }

    /**
     * @param Fin the Fin to set
     */
    public void setFin(Date Fin) {
        this.Fin = Fin;
    }

    /**
     * @return the ObsUsuario
     */
    public String getObsUsuario() {
        return ObsUsuario;
    }

    /**
     * @param ObsUsuario the ObsUsuario to set
     */
    public void setObsUsuario(String ObsUsuario) {
        this.ObsUsuario = ObsUsuario;
    }

    /**
     * @return the ObsAdministrador
     */
    public String getObsAdministrador() {
        return ObsAdministrador;
    }

    /**
     * @param ObsAdministrador the ObsAdministrador to set
     */
    public void setObsAdministrador(String ObsAdministrador) {
        this.ObsAdministrador = ObsAdministrador;
    }

    /**
     * @return the Estado
     */
    public int getEstado() {
        return Estado;
    }

    /**
     * @param Estado the Estado to set
     */
    public void setEstado(int Estado) {
        this.Estado = Estado;
    }

    /**
     * @return the FechaAccion
     */
    public Date getFechaAccion() {
        return FechaAccion;
    }

    /**
     * @param FechaAccion the FechaAccion to set
     */
    public void setFechaAccion(Date FechaAccion) {
        this.FechaAccion = FechaAccion;
    }

}
