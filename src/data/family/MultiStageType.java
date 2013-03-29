//
// Este archivo ha sido generado por la arquitectura JavaTM para la implantaci�n de la referencia de enlace (JAXB) XML v2.2.6 
// Visite <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Todas las modificaciones realizadas en este archivo se perder�n si se vuelve a compilar el esquema de origen. 
// Generado el: PM.03.14 a las 06:34:31 PM CET 
//


package data.family;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElementRef;
import javax.xml.bind.annotation.XmlElementRefs;
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
 *       &lt;choice maxOccurs="unbounded">
 *         &lt;element name="meshSubStage" type="{}subStageType"/>
 *         &lt;element name="textureSubStage" type="{}subStageType"/>
 *       &lt;/choice>
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
    "meshSubStageOrTextureSubStage"
})
public class MultiStageType {

    @XmlElementRefs({
        @XmlElementRef(name = "textureSubStage", type = JAXBElement.class, required = false),
        @XmlElementRef(name = "meshSubStage", type = JAXBElement.class, required = false)
    })
    protected List<JAXBElement<SubStageType>> meshSubStageOrTextureSubStage;
    @XmlAttribute(name = "stageLabel", required = true)
    protected String stageLabel;

    /**
     * Gets the value of the meshSubStageOrTextureSubStage property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the meshSubStageOrTextureSubStage property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getMeshSubStageOrTextureSubStage().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link JAXBElement }{@code <}{@link SubStageType }{@code >}
     * {@link JAXBElement }{@code <}{@link SubStageType }{@code >}
     * 
     * 
     */
    public List<JAXBElement<SubStageType>> getMeshSubStageOrTextureSubStage() {
        if (meshSubStageOrTextureSubStage == null) {
            meshSubStageOrTextureSubStage = new ArrayList<JAXBElement<SubStageType>>();
        }
        return this.meshSubStageOrTextureSubStage;
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
