//
// Este archivo ha sido generado por la arquitectura JavaTM para la implantación de la referencia de enlace (JAXB) XML v2.2.6 
// Visite <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Todas las modificaciones realizadas en este archivo se perderán si se vuelve a compilar el esquema de origen. 
// Generado el: AM.03.11 a las 10:45:53 AM CET 
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
 *       &lt;attribute name="FPSValue" use="required" type="{http://www.w3.org/2001/XMLSchema}integer" />
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
    @XmlAttribute(name = "FPSValue", required = true)
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
    public BigInteger getFPSValue() {
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
    public void setFPSValue(BigInteger value) {
        this.fpsValue = value;
    }

}
