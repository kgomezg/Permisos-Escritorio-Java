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
public final class Perfil {

    private int Id;
    private String Perfil, PerfilAbreviado;

    public Perfil() {

    }

    public Perfil(int _id, String _perfil, String _perfilAbreviado) {
        setId(_id);
        setPerfil(_perfil);
        setPerfilAbreviado(_perfilAbreviado);
    }

    public void imprimir() {
        String s = "";

        s += "{";
        s += "Id: " + getId() + ",";
        s += "Perfil: " + getPerfil() + ",";
        s += "PerfilAbreviado: " + getPerfilAbreviado() + ",";
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
     * @return the Perfil
     */
    public String getPerfil() {
        return Perfil;
    }

    /**
     * @param Perfil the Perfil to set
     */
    public void setPerfil(String Perfil) {
        this.Perfil = Perfil;
    }

    /**
     * @return the PerfilAbreviado
     */
    public String getPerfilAbreviado() {
        return PerfilAbreviado;
    }

    /**
     * @param PerfilAbreviado the PerfilAbreviado to set
     */
    public void setPerfilAbreviado(String PerfilAbreviado) {
        this.PerfilAbreviado = PerfilAbreviado;
    }

}
