//
// Este archivo ha sido generado por la arquitectura JavaTM para la implantación de la referencia de enlace (JAXB) XML v2.2.6 
// Visite <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Todas las modificaciones realizadas en este archivo se perderán si se vuelve a compilar el esquema de origen. 
// Generado el: AM.03.11 a las 10:45:53 AM CET 
//


package applicationdata.family;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlID;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.adapters.CollapsedStringAdapter;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;


/**
 * <p>Clase Java para meshStageType complex type.
 * 
 * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 * 
 * <pre>
 * &lt;complexType name="meshStageType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;attribute name="subStageName" use="required" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="idPanel" use="required" type="{http://www.w3.org/2001/XMLSchema}ID" />
 *       &lt;attribute name="meshChangeControl" use="required" type="{http://www.w3.org/2001/XMLSchema}boolean" />
 *       &lt;attribute name="multiselection" use="required" type="{http://www.w3.org/2001/XMLSchema}boolean" />
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "meshStageType")
public class MeshStageType {

    @XmlAttribute(name = "subStageName", required = true)
    protected String subStageName;
    @XmlAttribute(name = "idPanel", required = true)
    @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
    @XmlID
    @XmlSchemaType(name = "ID")
    protected String idPanel;
    @XmlAttribute(name = "meshChangeControl", required = true)
    protected boolean meshChangeControl;
    @XmlAttribute(name = "multiselection", required = true)
    protected boolean multiselection;

    /**
     * Obtiene el valor de la propiedad subStageName.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSubStageName() {
        return subStageName;
    }

    /**
     * Define el valor de la propiedad subStageName.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSubStageName(String value) {
        this.subStageName = value;
    }

    /**
     * Obtiene el valor de la propiedad idPanel.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getIdPanel() {
        return idPanel;
    }

    /**
     * Define el valor de la propiedad idPanel.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setIdPanel(String value) {
        this.idPanel = value;
    }

    /**
     * Obtiene el valor de la propiedad meshChangeControl.
     * 
     */
    public boolean isMeshChangeControl() {
        return meshChangeControl;
    }

    /**
     * Define el valor de la propiedad meshChangeControl.
     * 
     */
    public void setMeshChangeControl(boolean value) {
        this.meshChangeControl = value;
    }

    /**
     * Obtiene el valor de la propiedad multiselection.
     * 
     */
    public boolean isMultiselection() {
        return multiselection;
    }

    /**
     * Define el valor de la propiedad multiselection.
     * 
     */
    public void setMultiselection(boolean value) {
        this.multiselection = value;
    }

}
