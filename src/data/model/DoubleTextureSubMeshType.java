//
// Este archivo ha sido generado por la arquitectura JavaTM para la implantación de la referencia de enlace (JAXB) XML v2.2.6 
// Visite <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Todas las modificaciones realizadas en este archivo se perderán si se vuelve a compilar el esquema de origen. 
// Generado el: AM.04.11 a las 10:46:54 AM CEST 
//


package data.model;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Clase Java para doubleTextureSubMeshType complex type.
 * 
 * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 * 
 * <pre>
 * &lt;complexType name="doubleTextureSubMeshType">
 *   &lt;complexContent>
 *     &lt;extension base="{}textureSubMeshType">
 *       &lt;sequence>
 *         &lt;element name="basePath" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="detailsPath" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "doubleTextureSubMeshType", propOrder = {
    "basePath",
    "detailsPath"
})
public class DoubleTextureSubMeshType
    extends TextureSubMeshType
{

    @XmlElement(required = true)
    protected String basePath;
    @XmlElement(required = true)
    protected String detailsPath;

    /**
     * Obtiene el valor de la propiedad basePath.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getBasePath() {
        return basePath;
    }

    /**
     * Define el valor de la propiedad basePath.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setBasePath(String value) {
        this.basePath = value;
    }

    /**
     * Obtiene el valor de la propiedad detailsPath.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDetailsPath() {
        return detailsPath;
    }

    /**
     * Define el valor de la propiedad detailsPath.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDetailsPath(String value) {
        this.detailsPath = value;
    }

}
