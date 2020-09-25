/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package permisos.utilitario;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.DocumentBuilder;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.w3c.dom.Node;
import org.w3c.dom.Element;
import java.io.File;
import java.io.IOException;
import javax.xml.parsers.ParserConfigurationException;
import org.w3c.dom.DOMException;
import org.xml.sax.SAXException;

/**
 *
 * @author Kevin Camilo Gómez González - kgomezg81832@universidadean.edu.co
 * @date 2020.09.12
 * @desc
 */
public final class PropiedadesConexion {

    private String Server;
    private String Puerto;
    private String BaseDatos;

    public PropiedadesConexion() {
        LeerConfig();
    }

    public void LeerConfig() {
        try {
            // Obtengo el archivo xml
            File fXmlFile = new File("src/permisos/utilitario/files/config.xml");

            // Procedo a parsear el archivo
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(fXmlFile);
            doc.getDocumentElement().normalize();

            // Mapeo los nodos del xml
            NodeList nList = doc.getElementsByTagName("bd");
            Node nNode = nList.item(0);

            if (nNode.getNodeType() == Node.ELEMENT_NODE) {

                Element eElement = (Element) nNode;
                // Obtengo los valores de las llaves
                setBaseDatos(eElement.getElementsByTagName("baseDatos").item(0).getTextContent());
                setPuerto(eElement.getElementsByTagName("puerto").item(0).getTextContent());
                setServer(eElement.getElementsByTagName("server").item(0).getTextContent());

                System.out.println("Archivo de Configuración Leido Correctamente.");
            }

        } catch (IOException | ParserConfigurationException | DOMException | SAXException e) {
            System.out.println("Mensaje: " + e.getMessage());
        }
    }

    /**
     * @return the Server
     */
    public String getServer() {
        return Server;
    }

    /**
     * @param Server the Server to set
     */
    public void setServer(String Server) {
        this.Server = Server;
    }

    /**
     * @return the Puerto
     */
    public String getPuerto() {
        return Puerto;
    }

    /**
     * @param Puerto the Puerto to set
     */
    public void setPuerto(String Puerto) {
        this.Puerto = Puerto;
    }

    /**
     * @return the BaseDatos
     */
    public String getBaseDatos() {
        return BaseDatos;
    }

    /**
     * @param BaseDatos the BaseDatos to set
     */
    public void setBaseDatos(String BaseDatos) {
        this.BaseDatos = BaseDatos;
    }
}
