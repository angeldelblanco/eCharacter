//
// Este archivo ha sido generado por la arquitectura JavaTM para la implantación de la referencia de enlace (JAXB) XML v2.2.6 
// Visite <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Todas las modificaciones realizadas en este archivo se perderán si se vuelve a compilar el esquema de origen. 
// Generado el: PM.03.12 a las 05:20:25 PM CET 
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
 * <p>Clase Java para textureSubStageType complex type.
 * 
 * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 * 
 * <pre>
 * &lt;complexType name="textureSubStageType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;attribute name="subStageName" use="required" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="idPanel" use="required" type="{http://www.w3.org/2001/XMLSchema}ID" />
 *       &lt;attribute name="multiselection" type="{http://www.w3.org/2001/XMLSchema}boolean" />
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "textureSubStageType")
public class TextureSubStageType {

    @XmlAttribute(name = "subStageName", required = true)
    protected String subStageName;
    @XmlAttribute(name = "idPanel", required = true)
    @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
    @XmlID
    @XmlSchemaType(name = "ID")
    protected String idPanel;
    @XmlAttribute(name = "multiselection")
    protected Boolean multiselection;

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
     * Obtiene el valor de la propiedad multiselection.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isMultiselection() {
        return multiselection;
    }

    /**
     * Define el valor de la propiedad multiselection.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setMultiselection(Boolean value) {
        this.multiselection = value;
    }

}
