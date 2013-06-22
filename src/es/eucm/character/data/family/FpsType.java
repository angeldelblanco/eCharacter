//
// Este archivo ha sido generado por la arquitectura JavaTM para la implantación de la referencia de enlace (JAXB) XML v2.2.6 
// Visite <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Todas las modificaciones realizadas en este archivo se perderán si se vuelve a compilar el esquema de origen. 
// Generado el: AM.06.22 a las 11:33:06 AM CEST 
//


package es.eucm.character.data.family;

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
 *       &lt;attribute name="qualityLabel" use="required" type="{http://www.w3.org/2001/XMLSchema}string" />
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

    @XmlAttribute(name = "qualityLabel", required = true)
    protected String qualityLabel;
    @XmlAttribute(name = "fpsValue", required = true)
    protected BigInteger fpsValue;

    /**
     * Obtiene el valor de la propiedad qualityLabel.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getQualityLabel() {
        return qualityLabel;
    }

    /**
     * Define el valor de la propiedad qualityLabel.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setQualityLabel(String value) {
        this.qualityLabel = value;
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
