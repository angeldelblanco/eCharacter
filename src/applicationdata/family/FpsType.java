//
// Este archivo ha sido generado por la arquitectura JavaTM para la implantaci�n de la referencia de enlace (JAXB) XML v2.2.6 
// Visite <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Todas las modificaciones realizadas en este archivo se perder�n si se vuelve a compilar el esquema de origen. 
// Generado el: PM.03.12 a las 05:20:25 PM CET 
//


package applicationdata.family;

import java.math.BigInteger;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Clase Java para fpsType complex type.
 * 
 * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 * 
 * <pre>
 * &lt;complexType name="fpsType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;attribute name="qualityName" use="required" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="fpsValue" use="required" type="{http://www.w3.org/2001/XMLSchema}integer" />
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "fpsType")
public class FpsType {

    @XmlAttribute(name = "qualityName", required = true)
    protected String qualityName;
    @XmlAttribute(name = "fpsValue", required = true)
    protected BigInteger fpsValue;

    /**
     * Obtiene el valor de la propiedad qualityName.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getQualityName() {
        return qualityName;
    }

    /**
     * Define el valor de la propiedad qualityName.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setQualityName(String value) {
        this.qualityName = value;
    }

    /**
     * Obtiene el valor de la propiedad fpsValue.
     * 
     * @return
     *     possible object is
     *     {@link BigInteger }
     *     
     */
    public BigInteger getFpsValue() {
        return fpsValue;
    }

    /**
     * Define el valor de la propiedad fpsValue.
     * 
     * @param value
     *     allowed object is
     *     {@link BigInteger }
     *     
     */
    public void setFpsValue(BigInteger value) {
        this.fpsValue = value;
    }

}
