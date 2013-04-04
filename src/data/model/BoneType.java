//
// Este archivo ha sido generado por la arquitectura JavaTM para la implantación de la referencia de enlace (JAXB) XML v2.2.6 
// Visite <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Todas las modificaciones realizadas en este archivo se perderán si se vuelve a compilar el esquema de origen. 
// Generado el: AM.04.04 a las 10:43:46 AM CEST 
//


package data.model;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Clase Java para boneType complex type.
 * 
 * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 * 
 * <pre>
 * &lt;complexType name="boneType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;attribute name="idControllerRef" use="required" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="defaultValue" use="required" type="{http://www.w3.org/2001/XMLSchema}float" />
 *       &lt;attribute name="minValue" use="required" type="{http://www.w3.org/2001/XMLSchema}float" />
 *       &lt;attribute name="maxValue" use="required" type="{http://www.w3.org/2001/XMLSchema}float" />
 *       &lt;attribute name="boneName" use="required" type="{http://www.w3.org/2001/XMLSchema}string" />
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "boneType")
public class BoneType {

    @XmlAttribute(name = "idControllerRef", required = true)
    protected String idControllerRef;
    @XmlAttribute(name = "defaultValue", required = true)
    protected float defaultValue;
    @XmlAttribute(name = "minValue", required = true)
    protected float minValue;
    @XmlAttribute(name = "maxValue", required = true)
    protected float maxValue;
    @XmlAttribute(name = "boneName", required = true)
    protected String boneName;

    /**
     * Obtiene el valor de la propiedad idControllerRef.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getIdControllerRef() {
        return idControllerRef;
    }

    /**
     * Define el valor de la propiedad idControllerRef.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setIdControllerRef(String value) {
        this.idControllerRef = value;
    }

    /**
     * Obtiene el valor de la propiedad defaultValue.
     * 
     */
    public float getDefaultValue() {
        return defaultValue;
    }

    /**
     * Define el valor de la propiedad defaultValue.
     * 
     */
    public void setDefaultValue(float value) {
        this.defaultValue = value;
    }

    /**
     * Obtiene el valor de la propiedad minValue.
     * 
     */
    public float getMinValue() {
        return minValue;
    }

    /**
     * Define el valor de la propiedad minValue.
     * 
     */
    public void setMinValue(float value) {
        this.minValue = value;
    }

    /**
     * Obtiene el valor de la propiedad maxValue.
     * 
     */
    public float getMaxValue() {
        return maxValue;
    }

    /**
     * Define el valor de la propiedad maxValue.
     * 
     */
    public void setMaxValue(float value) {
        this.maxValue = value;
    }

    /**
     * Obtiene el valor de la propiedad boneName.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getBoneName() {
        return boneName;
    }

    /**
     * Define el valor de la propiedad boneName.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setBoneName(String value) {
        this.boneName = value;
    }

}
