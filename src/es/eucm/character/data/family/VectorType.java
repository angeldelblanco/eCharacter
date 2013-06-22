//
// Este archivo ha sido generado por la arquitectura JavaTM para la implantación de la referencia de enlace (JAXB) XML v2.2.6 
// Visite <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Todas las modificaciones realizadas en este archivo se perderán si se vuelve a compilar el esquema de origen. 
// Generado el: AM.06.22 a las 11:33:06 AM CEST 
//


package es.eucm.character.data.family;

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
 *       &lt;attribute name="valueX" use="required" type="{http://www.w3.org/2001/XMLSchema}float" />
 *       &lt;attribute name="valueY" use="required" type="{http://www.w3.org/2001/XMLSchema}float" />
 *       &lt;attribute name="valueZ" use="required" type="{http://www.w3.org/2001/XMLSchema}float" />
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
    protected float valueX;
    @XmlAttribute(name = "valueY", required = true)
    protected float valueY;
    @XmlAttribute(name = "valueZ", required = true)
    protected float valueZ;

    /**
     * Obtiene el valor de la propiedad valueX.
     * 
     */
    public float getValueX() {
        return valueX;
    }

    /**
     * Define el valor de la propiedad valueX.
     * 
     */
    public void setValueX(float value) {
        this.valueX = value;
    }

    /**
     * Obtiene el valor de la propiedad valueY.
     * 
     */
    public float getValueY() {
        return valueY;
    }

    /**
     * Define el valor de la propiedad valueY.
     * 
     */
    public void setValueY(float value) {
        this.valueY = value;
    }

    /**
     * Obtiene el valor de la propiedad valueZ.
     * 
     */
    public float getValueZ() {
        return valueZ;
    }

    /**
     * Define el valor de la propiedad valueZ.
     * 
     */
    public void setValueZ(float value) {
        this.valueZ = value;
    }

}
