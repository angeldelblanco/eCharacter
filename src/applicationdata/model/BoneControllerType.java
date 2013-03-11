//
// Este archivo ha sido generado por la arquitectura JavaTM para la implantación de la referencia de enlace (JAXB) XML v2.2.6 
// Visite <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Todas las modificaciones realizadas en este archivo se perderán si se vuelve a compilar el esquema de origen. 
// Generado el: AM.03.11 a las 10:45:40 AM CET 
//


package applicationdata.model;

import java.math.BigInteger;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Clase Java para boneControllerType complex type.
 * 
 * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 * 
 * <pre>
 * &lt;complexType name="boneControllerType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;attribute name="labelController" use="required" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="defaultValue" use="required" type="{http://www.w3.org/2001/XMLSchema}integer" />
 *       &lt;attribute name="minValue" use="required" type="{http://www.w3.org/2001/XMLSchema}integer" />
 *       &lt;attribute name="maxValue" use="required" type="{http://www.w3.org/2001/XMLSchema}integer" />
 *       &lt;attribute name="boneName" use="required" type="{http://www.w3.org/2001/XMLSchema}string" />
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "boneControllerType")
public class BoneControllerType {

    @XmlAttribute(name = "labelController", required = true)
    protected String labelController;
    @XmlAttribute(name = "defaultValue", required = true)
    protected BigInteger defaultValue;
    @XmlAttribute(name = "minValue", required = true)
    protected BigInteger minValue;
    @XmlAttribute(name = "maxValue", required = true)
    protected BigInteger maxValue;
    @XmlAttribute(name = "boneName", required = true)
    protected String boneName;

    /**
     * Obtiene el valor de la propiedad labelController.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getLabelController() {
        return labelController;
    }

    /**
     * Define el valor de la propiedad labelController.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setLabelController(String value) {
        this.labelController = value;
    }

    /**
     * Obtiene el valor de la propiedad defaultValue.
     * 
     * @return
     *     possible object is
     *     {@link BigInteger }
     *     
     */
    public BigInteger getDefaultValue() {
        return defaultValue;
    }

    /**
     * Define el valor de la propiedad defaultValue.
     * 
     * @param value
     *     allowed object is
     *     {@link BigInteger }
     *     
     */
    public void setDefaultValue(BigInteger value) {
        this.defaultValue = value;
    }

    /**
     * Obtiene el valor de la propiedad minValue.
     * 
     * @return
     *     possible object is
     *     {@link BigInteger }
     *     
     */
    public BigInteger getMinValue() {
        return minValue;
    }

    /**
     * Define el valor de la propiedad minValue.
     * 
     * @param value
     *     allowed object is
     *     {@link BigInteger }
     *     
     */
    public void setMinValue(BigInteger value) {
        this.minValue = value;
    }

    /**
     * Obtiene el valor de la propiedad maxValue.
     * 
     * @return
     *     possible object is
     *     {@link BigInteger }
     *     
     */
    public BigInteger getMaxValue() {
        return maxValue;
    }

    /**
     * Define el valor de la propiedad maxValue.
     * 
     * @param value
     *     allowed object is
     *     {@link BigInteger }
     *     
     */
    public void setMaxValue(BigInteger value) {
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
