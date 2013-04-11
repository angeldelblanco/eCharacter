//
// Este archivo ha sido generado por la arquitectura JavaTM para la implantaci�n de la referencia de enlace (JAXB) XML v2.2.6 
// Visite <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Todas las modificaciones realizadas en este archivo se perder�n si se vuelve a compilar el esquema de origen. 
// Generado el: PM.04.06 a las 04:27:19 PM CEST 
//


package es.eucm.character.data.family;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Clase Java para cameraType complex type.
 * 
 * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 * 
 * <pre>
 * &lt;complexType name="cameraType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="upAxis" type="{}vectorType"/>
 *         &lt;element name="location" type="{}vectorType"/>
 *         &lt;element name="direction" type="{}vectorType"/>
 *       &lt;/sequence>
 *       &lt;attribute name="cameraLabel" use="required" type="{http://www.w3.org/2001/XMLSchema}string" />
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "cameraType", propOrder = {
    "upAxis",
    "location",
    "direction"
})
public class CameraType {

    @XmlElement(required = true)
    protected VectorType upAxis;
    @XmlElement(required = true)
    protected VectorType location;
    @XmlElement(required = true)
    protected VectorType direction;
    @XmlAttribute(name = "cameraLabel", required = true)
    protected String cameraLabel;

    /**
     * Obtiene el valor de la propiedad upAxis.
     * 
     * @return
     *     possible object is
     *     {@link VectorType }
     *     
     */
    public VectorType getUpAxis() {
        return upAxis;
    }

    /**
     * Define el valor de la propiedad upAxis.
     * 
     * @param value
     *     allowed object is
     *     {@link VectorType }
     *     
     */
    public void setUpAxis(VectorType value) {
        this.upAxis = value;
    }

    /**
     * Obtiene el valor de la propiedad location.
     * 
     * @return
     *     possible object is
     *     {@link VectorType }
     *     
     */
    public VectorType getLocation() {
        return location;
    }

    /**
     * Define el valor de la propiedad location.
     * 
     * @param value
     *     allowed object is
     *     {@link VectorType }
     *     
     */
    public void setLocation(VectorType value) {
        this.location = value;
    }

    /**
     * Obtiene el valor de la propiedad direction.
     * 
     * @return
     *     possible object is
     *     {@link VectorType }
     *     
     */
    public VectorType getDirection() {
        return direction;
    }

    /**
     * Define el valor de la propiedad direction.
     * 
     * @param value
     *     allowed object is
     *     {@link VectorType }
     *     
     */
    public void setDirection(VectorType value) {
        this.direction = value;
    }

    /**
     * Obtiene el valor de la propiedad cameraLabel.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCameraLabel() {
        return cameraLabel;
    }

    /**
     * Define el valor de la propiedad cameraLabel.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCameraLabel(String value) {
        this.cameraLabel = value;
    }

}
