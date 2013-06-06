//
// Este archivo ha sido generado por la arquitectura JavaTM para la implantación de la referencia de enlace (JAXB) XML v2.2.6 
// Visite <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Todas las modificaciones realizadas en este archivo se perderán si se vuelve a compilar el esquema de origen. 
// Generado el: AM.06.07 a las 12:36:24 AM CEST 
//


package es.eucm.character.data.model;

import java.math.BigInteger;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Clase Java para colorType complex type.
 * 
 * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 * 
 * <pre>
 * &lt;complexType name="colorType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="red" type="{http://www.w3.org/2001/XMLSchema}integer"/>
 *         &lt;element name="green" type="{http://www.w3.org/2001/XMLSchema}integer"/>
 *         &lt;element name="blue" type="{http://www.w3.org/2001/XMLSchema}integer"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "colorType", propOrder = {
    "red",
    "green",
    "blue"
})
public class ColorType {

    @XmlElement(required = true)
    protected BigInteger red;
    @XmlElement(required = true)
    protected BigInteger green;
    @XmlElement(required = true)
    protected BigInteger blue;

    /**
     * Obtiene el valor de la propiedad red.
     * 
     * @return
     *     possible object is
     *     {@link BigInteger }
     *     
     */
    public BigInteger getRed() {
        return red;
    }

    /**
     * Define el valor de la propiedad red.
     * 
     * @param value
     *     allowed object is
     *     {@link BigInteger }
     *     
     */
    public void setRed(BigInteger value) {
        this.red = value;
    }

    /**
     * Obtiene el valor de la propiedad green.
     * 
     * @return
     *     possible object is
     *     {@link BigInteger }
     *     
     */
    public BigInteger getGreen() {
        return green;
    }

    /**
     * Define el valor de la propiedad green.
     * 
     * @param value
     *     allowed object is
     *     {@link BigInteger }
     *     
     */
    public void setGreen(BigInteger value) {
        this.green = value;
    }

    /**
     * Obtiene el valor de la propiedad blue.
     * 
     * @return
     *     possible object is
     *     {@link BigInteger }
     *     
     */
    public BigInteger getBlue() {
        return blue;
    }

    /**
     * Define el valor de la propiedad blue.
     * 
     * @param value
     *     allowed object is
     *     {@link BigInteger }
     *     
     */
    public void setBlue(BigInteger value) {
        this.blue = value;
    }

}
