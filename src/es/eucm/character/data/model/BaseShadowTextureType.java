//
// Este archivo ha sido generado por la arquitectura JavaTM para la implantación de la referencia de enlace (JAXB) XML v2.2.6 
// Visite <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Todas las modificaciones realizadas en este archivo se perderán si se vuelve a compilar el esquema de origen. 
// Generado el: AM.06.07 a las 12:36:24 AM CEST 
//


package es.eucm.character.data.model;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Clase Java para baseShadowTextureType complex type.
 * 
 * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 * 
 * <pre>
 * &lt;complexType name="baseShadowTextureType">
 *   &lt;complexContent>
 *     &lt;extension base="{}textureType">
 *       &lt;sequence>
 *         &lt;element name="path" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="colorDefault" type="{}colorType"/>
 *         &lt;element name="shadowPath" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
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
@XmlType(name = "baseShadowTextureType", propOrder = {
    "path",
    "colorDefault",
    "shadowPath"
})
public class BaseShadowTextureType
    extends TextureType
{

    @XmlElement(required = true)
    protected String path;
    @XmlElement(required = true)
    protected ColorType colorDefault;
    protected String shadowPath;
    @XmlAttribute(name = "default", required = true)
    protected boolean _default;

    /**
     * Obtiene el valor de la propiedad path.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPath() {
        return path;
    }

    /**
     * Define el valor de la propiedad path.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPath(String value) {
        this.path = value;
    }

    /**
     * Obtiene el valor de la propiedad colorDefault.
     * 
     * @return
     *     possible object is
     *     {@link ColorType }
     *     
     */
    public ColorType getColorDefault() {
        return colorDefault;
    }

    /**
     * Define el valor de la propiedad colorDefault.
     * 
     * @param value
     *     allowed object is
     *     {@link ColorType }
     *     
     */
    public void setColorDefault(ColorType value) {
        this.colorDefault = value;
    }

    /**
     * Obtiene el valor de la propiedad shadowPath.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getShadowPath() {
        return shadowPath;
    }

    /**
     * Define el valor de la propiedad shadowPath.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setShadowPath(String value) {
        this.shadowPath = value;
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
