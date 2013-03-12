//
// Este archivo ha sido generado por la arquitectura JavaTM para la implantación de la referencia de enlace (JAXB) XML v2.2.6 
// Visite <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Todas las modificaciones realizadas en este archivo se perderán si se vuelve a compilar el esquema de origen. 
// Generado el: PM.03.12 a las 05:20:25 PM CET 
//


package applicationdata.family;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Clase Java para modelRefType complex type.
 * 
 * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 * 
 * <pre>
 * &lt;complexType name="modelRefType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="modelName" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="iconPath" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="modelPath" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "modelRefType", propOrder = {
    "modelName",
    "iconPath",
    "modelPath"
})
public class ModelRefType {

    @XmlElement(required = true)
    protected String modelName;
    @XmlElement(required = true)
    protected String iconPath;
    @XmlElement(required = true)
    protected String modelPath;

    /**
     * Obtiene el valor de la propiedad modelName.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getModelName() {
        return modelName;
    }

    /**
     * Define el valor de la propiedad modelName.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setModelName(String value) {
        this.modelName = value;
    }

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
     * Obtiene el valor de la propiedad modelPath.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getModelPath() {
        return modelPath;
    }

    /**
     * Define el valor de la propiedad modelPath.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setModelPath(String value) {
        this.modelPath = value;
    }

}
