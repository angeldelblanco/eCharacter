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
 * <p>Clase Java para vectorType complex type.
 * 
 * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 * 
 * <pre>
 * &lt;complexType name="vectorType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;attribute name="valueX" use="required" type="{http://www.w3.org/2001/XMLSchema}integer" />
 *       &lt;attribute name="valueY" use="required" type="{http://www.w3.org/2001/XMLSchema}integer" />
 *       &lt;attribute name="valueZ" use="required" type="{http://www.w3.org/2001/XMLSchema}integer" />
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "vectorType")
public class VectorType {

    @XmlAttribute(name = "valueX", required = true)
    protected BigInteger valueX;
    @XmlAttribute(name = "valueY", required = true)
    protected BigInteger valueY;
    @XmlAttribute(name = "valueZ", required = true)
    protected BigInteger valueZ;

    /**
     * Obtiene el valor de la propiedad valueX.
     * 
     * @return
     *     possible object is
     *     {@link BigInteger }
     *     
     */
    public BigInteger getValueX() {
        return valueX;
    }

    /**
     * Define el valor de la propiedad valueX.
     * 
     * @param value
     *     allowed object is
     *     {@link BigInteger }
     *     
     */
    public void setValueX(BigInteger value) {
        this.valueX = value;
    }

    /**
     * Obtiene el valor de la propiedad valueY.
     * 
     * @return
     *     possible object is
     *     {@link BigInteger }
     *     
     */
    public BigInteger getValueY() {
        return valueY;
    }

    /**
     * Define el valor de la propiedad valueY.
     * 
     * @param value
     *     allowed object is
     *     {@link BigInteger }
     *     
     */
    public void setValueY(BigInteger value) {
        this.valueY = value;
    }

    /**
     * Obtiene el valor de la propiedad valueZ.
     * 
     * @return
     *     possible object is
     *     {@link BigInteger }
     *     
     */
    public BigInteger getValueZ() {
        return valueZ;
    }

    /**
     * Define el valor de la propiedad valueZ.
     * 
     * @param value
     *     allowed object is
     *     {@link BigInteger }
     *     
     */
    public void setValueZ(BigInteger value) {
        this.valueZ = value;
    }

}
