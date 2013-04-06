//
// Este archivo ha sido generado por la arquitectura JavaTM para la implantación de la referencia de enlace (JAXB) XML v2.2.6 
// Visite <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Todas las modificaciones realizadas en este archivo se perderán si se vuelve a compilar el esquema de origen. 
// Generado el: PM.04.06 a las 04:27:19 PM CEST 
//


package data.family;

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
 * <p>Clase Java para scaleStageType complex type.
 * 
 * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 * 
 * <pre>
 * &lt;complexType name="scaleStageType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="boneController" maxOccurs="10">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 &lt;attribute name="controllerLabel" use="required" type="{http://www.w3.org/2001/XMLSchema}string" />
 *                 &lt;attribute name="idController" use="required" type="{http://www.w3.org/2001/XMLSchema}ID" />
 *               &lt;/restriction>
 *             &lt;/complexContent>
 *           &lt;/complexType>
 *         &lt;/element>
 *       &lt;/sequence>
 *       &lt;attribute name="stageLabel" use="required" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="idPanel" use="required" type="{http://www.w3.org/2001/XMLSchema}ID" />
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "scaleStageType", propOrder = {
    "boneController"
})
public class ScaleStageType {

    @XmlElement(required = true)
    protected List<ScaleStageType.BoneController> boneController;
    @XmlAttribute(name = "stageLabel", required = true)
    protected String stageLabel;
    @XmlAttribute(name = "idPanel", required = true)
    @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
    @XmlID
    @XmlSchemaType(name = "ID")
    protected String idPanel;

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
     * {@link ScaleStageType.BoneController }
     * 
     * 
     */
    public List<ScaleStageType.BoneController> getBoneController() {
        if (boneController == null) {
            boneController = new ArrayList<ScaleStageType.BoneController>();
        }
        return this.boneController;
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
     * <p>Clase Java para anonymous complex type.
     * 
     * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
     * 
     * <pre>
     * &lt;complexType>
     *   &lt;complexContent>
     *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
     *       &lt;attribute name="controllerLabel" use="required" type="{http://www.w3.org/2001/XMLSchema}string" />
     *       &lt;attribute name="idController" use="required" type="{http://www.w3.org/2001/XMLSchema}ID" />
     *     &lt;/restriction>
     *   &lt;/complexContent>
     * &lt;/complexType>
     * </pre>
     * 
     * 
     */
    @XmlAccessorType(XmlAccessType.FIELD)
    @XmlType(name = "")
    public static class BoneController {

        @XmlAttribute(name = "controllerLabel", required = true)
        protected String controllerLabel;
        @XmlAttribute(name = "idController", required = true)
        @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
        @XmlID
        @XmlSchemaType(name = "ID")
        protected String idController;

        /**
         * Obtiene el valor de la propiedad controllerLabel.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getControllerLabel() {
            return controllerLabel;
        }

        /**
         * Define el valor de la propiedad controllerLabel.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setControllerLabel(String value) {
            this.controllerLabel = value;
        }

        /**
         * Obtiene el valor de la propiedad idController.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getIdController() {
            return idController;
        }

        /**
         * Define el valor de la propiedad idController.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setIdController(String value) {
            this.idController = value;
        }

    }

}
