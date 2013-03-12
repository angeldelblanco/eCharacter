//
// Este archivo ha sido generado por la arquitectura JavaTM para la implantación de la referencia de enlace (JAXB) XML v2.2.6 
// Visite <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Todas las modificaciones realizadas en este archivo se perderán si se vuelve a compilar el esquema de origen. 
// Generado el: PM.03.12 a las 05:20:56 PM CET 
//


package applicationdata.model;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Clase Java para subMeshesType complex type.
 * 
 * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 * 
 * <pre>
 * &lt;complexType name="subMeshesType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="iconPath" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="transformations" type="{}transformationsType" maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/sequence>
 *       &lt;attribute name="idPanel" use="required" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="associatedBone" use="required" type="{http://www.w3.org/2001/XMLSchema}string" />
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "subMeshesType", propOrder = {
    "iconPath",
    "transformations"
})
public class SubMeshesType {

    @XmlElement(required = true)
    protected String iconPath;
    protected List<TransformationsType> transformations;
    @XmlAttribute(name = "idPanel", required = true)
    protected String idPanel;
    @XmlAttribute(name = "associatedBone", required = true)
    protected String associatedBone;

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
     * Gets the value of the transformations property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the transformations property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getTransformations().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link TransformationsType }
     * 
     * 
     */
    public List<TransformationsType> getTransformations() {
        if (transformations == null) {
            transformations = new ArrayList<TransformationsType>();
        }
        return this.transformations;
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
     * Obtiene el valor de la propiedad associatedBone.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAssociatedBone() {
        return associatedBone;
    }

    /**
     * Define el valor de la propiedad associatedBone.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAssociatedBone(String value) {
        this.associatedBone = value;
    }

}
