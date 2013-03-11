//
// Este archivo ha sido generado por la arquitectura JavaTM para la implantación de la referencia de enlace (JAXB) XML v2.2.6 
// Visite <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Todas las modificaciones realizadas en este archivo se perderán si se vuelve a compilar el esquema de origen. 
// Generado el: AM.03.11 a las 10:45:53 AM CET 
//


package applicationdata.family;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElements;
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
 *         &lt;element name="meshStage" type="{}meshStageType"/>
 *         &lt;element name="textureStage" type="{}textureStageType"/>
 *       &lt;/choice>
 *       &lt;attribute name="stageName" use="required" type="{http://www.w3.org/2001/XMLSchema}string" />
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "multiStageType", propOrder = {
    "meshStageOrTextureStage"
})
public class MultiStageType {

    @XmlElements({
        @XmlElement(name = "meshStage", type = MeshStageType.class),
        @XmlElement(name = "textureStage", type = TextureStageType.class)
    })
    protected List<Object> meshStageOrTextureStage;
    @XmlAttribute(name = "stageName", required = true)
    protected String stageName;

    /**
     * Gets the value of the meshStageOrTextureStage property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the meshStageOrTextureStage property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getMeshStageOrTextureStage().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link MeshStageType }
     * {@link TextureStageType }
     * 
     * 
     */
    public List<Object> getMeshStageOrTextureStage() {
        if (meshStageOrTextureStage == null) {
            meshStageOrTextureStage = new ArrayList<Object>();
        }
        return this.meshStageOrTextureStage;
    }

    /**
     * Obtiene el valor de la propiedad stageName.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getStageName() {
        return stageName;
    }

    /**
     * Define el valor de la propiedad stageName.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setStageName(String value) {
        this.stageName = value;
    }

}
