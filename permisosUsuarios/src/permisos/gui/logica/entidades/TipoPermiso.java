/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package permisos.gui.logica.entidades;

/**
 *
 * @author Kevin Camilo Gómez González - kgomezg81832@universidadean.edu.co
 * @date 2020.09.19
 * @desc 
 */
public final class TipoPermiso {

    private int Id;
    private String TipoPermiso;

    public TipoPermiso() {

    }

    public TipoPermiso(int _id, String _tipoPermiso) {
        setId(_id);
        setTipoPermiso(_tipoPermiso);
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
     * @return the Perfil
     */
    public String getTipoPermiso() {
        return TipoPermiso;
    }

    /**
     * @param TipoPermiso the TipoPermiso to set
     */
    public void setTipoPermiso(String TipoPermiso) {
        this.TipoPermiso = TipoPermiso;
    }
}
