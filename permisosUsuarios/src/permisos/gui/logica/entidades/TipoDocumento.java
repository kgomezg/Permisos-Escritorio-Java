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
public final class TipoDocumento {

    private int Id;
    private String Descripcion;
    private String DescripcionAbreviada;

    public TipoDocumento() {

    }

    public TipoDocumento(int _id, String _descripcion, String _descripcionAbreviada) {
        setId(_id);
        setDescripcion(_descripcion);
        setDescripcionAbreviada(_descripcionAbreviada);
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
     * @return the Descripcion
     */
    public String getDescripcion() {
        return Descripcion;
    }

    /**
     * @param Descripcion the Descripcion to set
     */
    public void setDescripcion(String Descripcion) {
        this.Descripcion = Descripcion;
    }

    /**
     * @return the DescripcionAbreviada
     */
    public String getDescripcionAbreviada() {
        return DescripcionAbreviada;
    }

    /**
     * @param DescripcionAbreviada the DescripcionAbreviada to set
     */
    public void setDescripcionAbreviada(String DescripcionAbreviada) {
        this.DescripcionAbreviada = DescripcionAbreviada;
    }
}
