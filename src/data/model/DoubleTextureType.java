//
// Este archivo ha sido generado por la arquitectura JavaTM para la implantaci�n de la referencia de enlace (JAXB) XML v2.2.6 
// Visite <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Todas las modificaciones realizadas en este archivo se perder�n si se vuelve a compilar el esquema de origen. 
// Generado el: PM.03.29 a las 12:58:59 PM CET 
//


package data.model;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Clase Java para doubleTextureType complex type.
 * 
 * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 * 
 * <pre>
 * &lt;complexType name="doubleTextureType">
 *   &lt;complexContent>
 *     &lt;extension base="{}textureType">
 *       &lt;sequence>
 *         &lt;element name="basePath" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="detailsPath" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *       &lt;/sequence>
 *       &lt;attribute name="default" use="required" type="{http://www.w3.org/2001/XMLSchema}boolean" />
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "doubleTextureType", propOrder = {
    "basePath",
    "detailsPath"
})
public class DoubleTextureType
    extends TextureType
{

    @XmlElement(required = true)
    protected String basePath;
    @XmlElement(required = true)
    protected String detailsPath;
    @XmlAttribute(name = "default", required = true)
    protected boolean _default;

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

    /**
     * Obtiene el valor de la propiedad default.
     * 
     */
    public boolean isDefault() {
        return _default;
    }

    /**
     * Define el valor de la propiedad default.
     * 
     */
    public void setDefault(boolean value) {
        this._default = value;
    }

}
