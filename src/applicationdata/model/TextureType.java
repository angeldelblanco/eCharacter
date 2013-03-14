//
// Este archivo ha sido generado por la arquitectura JavaTM para la implantación de la referencia de enlace (JAXB) XML v2.2.6 
// Visite <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Todas las modificaciones realizadas en este archivo se perderán si se vuelve a compilar el esquema de origen. 
// Generado el: PM.03.14 a las 06:34:47 PM CET 
//


package applicationdata.model;

import java.math.BigInteger;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Clase Java para textureType complex type.
 * 
 * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 * 
 * <pre>
 * &lt;complexType name="textureType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="iconPath" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *       &lt;/sequence>
 *       &lt;attribute name="idPanel" use="required" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="layer" use="required" type="{http://www.w3.org/2001/XMLSchema}integer" />
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "textureType", propOrder = {
    "iconPath"
})
@XmlSeeAlso({
    SimpleTextureType.class,
    MultiOptionTextureType.class,
    BaseShadowTextureType.class,
    DoubleTextureType.class
})
public class TextureType {

    @XmlElement(required = true)
    protected String iconPath;
    @XmlAttribute(name = "idPanel", required = true)
    protected String idPanel;
    @XmlAttribute(name = "layer", required = true)
    protected BigInteger layer;

    /**
     * Obtiene el valor de la propiedad iconPath.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getIconPath() {
        return iconPath;
    }

    /**
     * Define el valor de la propiedad iconPath.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setIconPath(String value) {
        this.iconPath = value;
    }

    /**
     * Obtiene el valor de la propiedad idPanel.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getIdPanel() {
        return idPanel;
    }

    /**
     * Define el valor de la propiedad idPanel.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setIdPanel(String value) {
        this.idPanel = value;
    }

    /**
     * Obtiene el valor de la propiedad layer.
     * 
     * @return
     *     possible object is
     *     {@link BigInteger }
     *     
     */
    public BigInteger getLayer() {
        return layer;
    }

    /**
     * Define el valor de la propiedad layer.
     * 
     * @param value
     *     allowed object is
     *     {@link BigInteger }
     *     
     */
    public void setLayer(BigInteger value) {
        this.layer = value;
    }

}
