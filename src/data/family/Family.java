//
// Este archivo ha sido generado por la arquitectura JavaTM para la implantación de la referencia de enlace (JAXB) XML v2.2.6 
// Visite <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Todas las modificaciones realizadas en este archivo se perderán si se vuelve a compilar el esquema de origen. 
// Generado el: AM.04.03 a las 11:47:25 AM CEST 
//


package data.family;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Clase Java para anonymous complex type.
 * 
 * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 * 
 * <pre>
 * &lt;complexType>
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="metadata">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 &lt;sequence>
 *                   &lt;element name="license" type="{}licenseType" minOccurs="0"/>
 *                   &lt;element name="name" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                   &lt;element name="description" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                   &lt;element name="author" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                   &lt;element name="URL" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                   &lt;element name="languagesPath" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                 &lt;/sequence>
 *               &lt;/restriction>
 *             &lt;/complexContent>
 *           &lt;/complexType>
 *         &lt;/element>
 *         &lt;element name="stages">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 &lt;sequence>
 *                   &lt;element name="scaleStage" type="{}scaleStageType" maxOccurs="10"/>
 *                   &lt;element name="multiStage" type="{}multiStageType" maxOccurs="10"/>
 *                   &lt;element name="animationStage" type="{}animationStageType"/>
 *                 &lt;/sequence>
 *               &lt;/restriction>
 *             &lt;/complexContent>
 *           &lt;/complexType>
 *         &lt;/element>
 *         &lt;element name="modelsRef">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 &lt;sequence>
 *                   &lt;element name="modelRef" type="{}modelRefType" maxOccurs="unbounded"/>
 *                 &lt;/sequence>
 *               &lt;/restriction>
 *             &lt;/complexContent>
 *           &lt;/complexType>
 *         &lt;/element>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "metadata",
    "stages",
    "modelsRef"
})
@XmlRootElement(name = "family")
public class Family {

    @XmlElement(required = true)
    protected Family.Metadata metadata;
    @XmlElement(required = true)
    protected Family.Stages stages;
    @XmlElement(required = true)
    protected Family.ModelsRef modelsRef;

    /**
     * Obtiene el valor de la propiedad metadata.
     * 
     * @return
     *     possible object is
     *     {@link Family.Metadata }
     *     
     */
    public Family.Metadata getMetadata() {
        return metadata;
    }

    /**
     * Define el valor de la propiedad metadata.
     * 
     * @param value
     *     allowed object is
     *     {@link Family.Metadata }
     *     
     */
    public void setMetadata(Family.Metadata value) {
        this.metadata = value;
    }

    /**
     * Obtiene el valor de la propiedad stages.
     * 
     * @return
     *     possible object is
     *     {@link Family.Stages }
     *     
     */
    public Family.Stages getStages() {
        return stages;
    }

    /**
     * Define el valor de la propiedad stages.
     * 
     * @param value
     *     allowed object is
     *     {@link Family.Stages }
     *     
     */
    public void setStages(Family.Stages value) {
        this.stages = value;
    }

    /**
     * Obtiene el valor de la propiedad modelsRef.
     * 
     * @return
     *     possible object is
     *     {@link Family.ModelsRef }
     *     
     */
    public Family.ModelsRef getModelsRef() {
        return modelsRef;
    }

    /**
     * Define el valor de la propiedad modelsRef.
     * 
     * @param value
     *     allowed object is
     *     {@link Family.ModelsRef }
     *     
     */
    public void setModelsRef(Family.ModelsRef value) {
        this.modelsRef = value;
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
     *       &lt;sequence>
     *         &lt;element name="license" type="{}licenseType" minOccurs="0"/>
     *         &lt;element name="name" type="{http://www.w3.org/2001/XMLSchema}string"/>
     *         &lt;element name="description" type="{http://www.w3.org/2001/XMLSchema}string"/>
     *         &lt;element name="author" type="{http://www.w3.org/2001/XMLSchema}string"/>
     *         &lt;element name="URL" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *         &lt;element name="languagesPath" type="{http://www.w3.org/2001/XMLSchema}string"/>
     *       &lt;/sequence>
     *     &lt;/restriction>
     *   &lt;/complexContent>
     * &lt;/complexType>
     * </pre>
     * 
     * 
     */
    @XmlAccessorType(XmlAccessType.FIELD)
    @XmlType(name = "", propOrder = {
        "license",
        "name",
        "description",
        "author",
        "url",
        "languagesPath"
    })
    public static class Metadata {

        protected LicenseType license;
        @XmlElement(required = true)
        protected String name;
        @XmlElement(required = true)
        protected String description;
        @XmlElement(required = true)
        protected String author;
        @XmlElement(name = "URL")
        protected String url;
        @XmlElement(required = true)
        protected String languagesPath;

        /**
         * Obtiene el valor de la propiedad license.
         * 
         * @return
         *     possible object is
         *     {@link LicenseType }
         *     
         */
        public LicenseType getLicense() {
            return license;
        }

        /**
         * Define el valor de la propiedad license.
         * 
         * @param value
         *     allowed object is
         *     {@link LicenseType }
         *     
         */
        public void setLicense(LicenseType value) {
            this.license = value;
        }

        /**
         * Obtiene el valor de la propiedad name.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getName() {
            return name;
        }

        /**
         * Define el valor de la propiedad name.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setName(String value) {
            this.name = value;
        }

        /**
         * Obtiene el valor de la propiedad description.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getDescription() {
            return description;
        }

        /**
         * Define el valor de la propiedad description.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setDescription(String value) {
            this.description = value;
        }

        /**
         * Obtiene el valor de la propiedad author.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getAuthor() {
            return author;
        }

        /**
         * Define el valor de la propiedad author.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setAuthor(String value) {
            this.author = value;
        }

        /**
         * Obtiene el valor de la propiedad url.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getURL() {
            return url;
        }

        /**
         * Define el valor de la propiedad url.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setURL(String value) {
            this.url = value;
        }

        /**
         * Obtiene el valor de la propiedad languagesPath.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getLanguagesPath() {
            return languagesPath;
        }

        /**
         * Define el valor de la propiedad languagesPath.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setLanguagesPath(String value) {
            this.languagesPath = value;
        }

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
     *       &lt;sequence>
     *         &lt;element name="modelRef" type="{}modelRefType" maxOccurs="unbounded"/>
     *       &lt;/sequence>
     *     &lt;/restriction>
     *   &lt;/complexContent>
     * &lt;/complexType>
     * </pre>
     * 
     * 
     */
    @XmlAccessorType(XmlAccessType.FIELD)
    @XmlType(name = "", propOrder = {
        "modelRef"
    })
    public static class ModelsRef {

        @XmlElement(required = true)
        protected List<ModelRefType> modelRef;

        /**
         * Gets the value of the modelRef property.
         * 
         * <p>
         * This accessor method returns a reference to the live list,
         * not a snapshot. Therefore any modification you make to the
         * returned list will be present inside the JAXB object.
         * This is why there is not a <CODE>set</CODE> method for the modelRef property.
         * 
         * <p>
         * For example, to add a new item, do as follows:
         * <pre>
         *    getModelRef().add(newItem);
         * </pre>
         * 
         * 
         * <p>
         * Objects of the following type(s) are allowed in the list
         * {@link ModelRefType }
         * 
         * 
         */
        public List<ModelRefType> getModelRef() {
            if (modelRef == null) {
                modelRef = new ArrayList<ModelRefType>();
            }
            return this.modelRef;
        }

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
     *       &lt;sequence>
     *         &lt;element name="scaleStage" type="{}scaleStageType" maxOccurs="10"/>
     *         &lt;element name="multiStage" type="{}multiStageType" maxOccurs="10"/>
     *         &lt;element name="animationStage" type="{}animationStageType"/>
     *       &lt;/sequence>
     *     &lt;/restriction>
     *   &lt;/complexContent>
     * &lt;/complexType>
     * </pre>
     * 
     * 
     */
    @XmlAccessorType(XmlAccessType.FIELD)
    @XmlType(name = "", propOrder = {
        "scaleStage",
        "multiStage",
        "animationStage"
    })
    public static class Stages {

        @XmlElement(required = true)
        protected List<ScaleStageType> scaleStage;
        @XmlElement(required = true)
        protected List<MultiStageType> multiStage;
        @XmlElement(required = true)
        protected AnimationStageType animationStage;

        /**
         * Gets the value of the scaleStage property.
         * 
         * <p>
         * This accessor method returns a reference to the live list,
         * not a snapshot. Therefore any modification you make to the
         * returned list will be present inside the JAXB object.
         * This is why there is not a <CODE>set</CODE> method for the scaleStage property.
         * 
         * <p>
         * For example, to add a new item, do as follows:
         * <pre>
         *    getScaleStage().add(newItem);
         * </pre>
         * 
         * 
         * <p>
         * Objects of the following type(s) are allowed in the list
         * {@link ScaleStageType }
         * 
         * 
         */
        public List<ScaleStageType> getScaleStage() {
            if (scaleStage == null) {
                scaleStage = new ArrayList<ScaleStageType>();
            }
            return this.scaleStage;
        }

        /**
         * Gets the value of the multiStage property.
         * 
         * <p>
         * This accessor method returns a reference to the live list,
         * not a snapshot. Therefore any modification you make to the
         * returned list will be present inside the JAXB object.
         * This is why there is not a <CODE>set</CODE> method for the multiStage property.
         * 
         * <p>
         * For example, to add a new item, do as follows:
         * <pre>
         *    getMultiStage().add(newItem);
         * </pre>
         * 
         * 
         * <p>
         * Objects of the following type(s) are allowed in the list
         * {@link MultiStageType }
         * 
         * 
         */
        public List<MultiStageType> getMultiStage() {
            if (multiStage == null) {
                multiStage = new ArrayList<MultiStageType>();
            }
            return this.multiStage;
        }

        /**
         * Obtiene el valor de la propiedad animationStage.
         * 
         * @return
         *     possible object is
         *     {@link AnimationStageType }
         *     
         */
        public AnimationStageType getAnimationStage() {
            return animationStage;
        }

        /**
         * Define el valor de la propiedad animationStage.
         * 
         * @param value
         *     allowed object is
         *     {@link AnimationStageType }
         *     
         */
        public void setAnimationStage(AnimationStageType value) {
            this.animationStage = value;
        }

    }

}
