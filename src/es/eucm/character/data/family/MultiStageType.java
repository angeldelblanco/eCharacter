//
// Este archivo ha sido generado por la arquitectura JavaTM para la implantación de la referencia de enlace (JAXB) XML v2.2.6 
// Visite <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Todas las modificaciones realizadas en este archivo se perderán si se vuelve a compilar el esquema de origen. 
// Generado el: PM.05.01 a las 01:57:40 PM CEST 
//


package es.eucm.character.data.family;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Clase Java para multiStageType complex type.
 * 
 * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 * 
 * <pre>
 * &lt;complexType name="multiStageType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="iconPath" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="subStage" type="{}subStageType" maxOccurs="unbounded"/>
 *       &lt;/sequence>
 *       &lt;attribute name="stageLabel" use="required" type="{http://www.w3.org/2001/XMLSchema}string" />
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "multiStageType", propOrder = {
    "iconPath",
    "subStage"
})
public class MultiStageType {

    protected String iconPath;
    @XmlElement(required = true)
    protected List<SubStageType> subStage;
    @XmlAttribute(name = "stageLabel", required = true)
    protected String stageLabel;

    /**
     * Obtiene el valor de la propiedad iconPath.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getIconPath() {
        return iconPath;
    }

    /**
     * Define el valor de la propiedad iconPath.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setIconPath(String value) {
        this.iconPath = value;
    }

    /**
     * Gets the value of the subStage property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the subStage property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getSubStage().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link SubStageType }
     * 
     * 
     */
    public List<SubStageType> getSubStage() {
        if (subStage == null) {
            subStage = new ArrayList<SubStageType>();
        }
        return this.subStage;
    }

    /**
     * Obtiene el valor de la propiedad stageLabel.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getStageLabel() {
        return stageLabel;
    }

    /**
     * Define el valor de la propiedad stageLabel.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setStageLabel(String value) {
        this.stageLabel = value;
    }

}
