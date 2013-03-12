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
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Clase Java para bonesType complex type.
 * 
 * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 * 
 * <pre>
 * &lt;complexType name="bonesType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="boneController" type="{}boneControllerType" maxOccurs="10"/>
 *         &lt;element name="basicMode" type="{}basicModeType" maxOccurs="10"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "bonesType", propOrder = {
    "boneController",
    "basicMode"
})
public class BonesType {

    @XmlElement(required = true)
    protected List<BoneControllerType> boneController;
    @XmlElement(required = true)
    protected List<BasicModeType> basicMode;

    /**
     * Gets the value of the boneController property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the boneController property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getBoneController().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link BoneControllerType }
     * 
     * 
     */
    public List<BoneControllerType> getBoneController() {
        if (boneController == null) {
            boneController = new ArrayList<BoneControllerType>();
        }
        return this.boneController;
    }

    /**
     * Gets the value of the basicMode property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the basicMode property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getBasicMode().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link BasicModeType }
     * 
     * 
     */
    public List<BasicModeType> getBasicMode() {
        if (basicMode == null) {
            basicMode = new ArrayList<BasicModeType>();
        }
        return this.basicMode;
    }

}
