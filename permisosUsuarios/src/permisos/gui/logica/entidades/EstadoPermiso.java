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
public final class EstadoPermiso {

    private int Id;
    private String Estado;

    public EstadoPermiso() {

    }

    public EstadoPermiso(int _id, String _estado) {
        setId(_id);
        setEstado(_estado);
    }

    public void imprimir() {
        String s = "";

        s += "{";
        s += "Id: " + getId() + ",";
        s += "Estado: " + getEstado();
        s += "}";

        System.out.println(s);
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
     * @return the Estado
     */
    public String getEstado() {
        return Estado;
    }

    /**
     * @param Estado the Estado to set
     */
    public void setEstado(String Estado) {
        this.Estado = Estado;
    }
}
