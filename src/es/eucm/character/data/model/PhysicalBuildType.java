//
// Este archivo ha sido generado por la arquitectura JavaTM para la implantaci�n de la referencia de enlace (JAXB) XML v2.2.6 
// Visite <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Todas las modificaciones realizadas en este archivo se perder�n si se vuelve a compilar el esquema de origen. 
// Generado el: AM.04.11 a las 10:46:54 AM CEST 
//


package es.eucm.character.data.model;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlID;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.adapters.CollapsedStringAdapter;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;


/**
 * <p>Clase Java para physicalBuildType complex type.
 * 
 * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 * 
 * <pre>
 * &lt;complexType name="physicalBuildType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="escalation" type="{}escalationType" maxOccurs="unbounded"/>
 *       &lt;/sequence>
 *       &lt;attribute name="idPanelRef" use="required" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="label" use="required" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="idPhysicalBuild" use="required" type="{http://www.w3.org/2001/XMLSchema}ID" />
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "physicalBuildType", propOrder = {
    "escalation"
})
public class PhysicalBuildType {

    @XmlElement(required = true)
    protected List<EscalationType> escalation;
    @XmlAttribute(name = "idPanelRef", required = true)
    protected String idPanelRef;
    @XmlAttribute(name = "label", required = true)
    protected String label;
    @XmlAttribute(name = "idPhysicalBuild", required = true)
    @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
    @XmlID
    @XmlSchemaType(name = "ID")
    protected String idPhysicalBuild;

    /**
     * Gets the value of the escalation property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the escalation property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getEscalation().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link EscalationType }
     * 
     * 
     */
    public List<EscalationType> getEscalation() {
        if (escalation == null) {
            escalation = new ArrayList<EscalationType>();
        }
        return this.escalation;
    }

    /**
     * Obtiene el valor de la propiedad idPanelRef.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getIdPanelRef() {
        return idPanelRef;
    }

    /**
     * Define el valor de la propiedad idPanelRef.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setIdPanelRef(String value) {
        this.idPanelRef = value;
    }

    /**
     * Obtiene el valor de la propiedad label.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getLabel() {
        return label;
    }

    /**
     * Define el valor de la propiedad label.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setLabel(String value) {
        this.label = value;
    }

    /**
     * Obtiene el valor de la propiedad idPhysicalBuild.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getIdPhysicalBuild() {
        return idPhysicalBuild;
    }

    /**
     * Define el valor de la propiedad idPhysicalBuild.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setIdPhysicalBuild(String value) {
        this.idPhysicalBuild = value;
    }

}
