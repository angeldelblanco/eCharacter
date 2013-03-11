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
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElements;
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
 *                   &lt;element name="license" type="{}licenseType"/>
 *                   &lt;element name="name" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                   &lt;element name="description" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                   &lt;element name="author" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                   &lt;element name="URL" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                 &lt;/sequence>
 *               &lt;/restriction>
 *             &lt;/complexContent>
 *           &lt;/complexType>
 *         &lt;/element>
 *         &lt;element name="properties">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 &lt;sequence>
 *                   &lt;element name="stages">
 *                     &lt;complexType>
 *                       &lt;complexContent>
 *                         &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                           &lt;choice maxOccurs="21">
 *                             &lt;element name="multiStage" type="{}multiStageType" maxOccurs="10"/>
 *                             &lt;element name="scaleStage" type="{}scaleStageType" maxOccurs="10"/>
 *                             &lt;element name="animationStage" type="{}animationStageType"/>
 *                           &lt;/choice>
 *                         &lt;/restriction>
 *                       &lt;/complexContent>
 *                     &lt;/complexType>
 *                   &lt;/element>
 *                 &lt;/sequence>
 *               &lt;/restriction>
 *             &lt;/complexContent>
 *           &lt;/complexType>
 *         &lt;/element>
 *         &lt;element name="models">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 &lt;sequence>
 *                   &lt;element name="model" type="{}modelType" maxOccurs="unbounded"/>
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
    "properties",
    "models"
})
@XmlRootElement(name = "family")
public class Family {

    @XmlElement(required = true)
    protected Family.Metadata metadata;
    @XmlElement(required = true)
    protected Family.Properties properties;
    @XmlElement(required = true)
    protected Family.Models models;

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
     * Obtiene el valor de la propiedad properties.
     * 
     * @return
     *     possible object is
     *     {@link Family.Properties }
     *     
     */
    public Family.Properties getProperties() {
        return properties;
    }

    /**
     * Define el valor de la propiedad properties.
     * 
     * @param value
     *     allowed object is
     *     {@link Family.Properties }
     *     
     */
    public void setProperties(Family.Properties value) {
        this.properties = value;
    }

    /**
     * Obtiene el valor de la propiedad models.
     * 
     * @return
     *     possible object is
     *     {@link Family.Models }
     *     
     */
    public Family.Models getModels() {
        return models;
    }

    /**
     * Define el valor de la propiedad models.
     * 
     * @param value
     *     allowed object is
     *     {@link Family.Models }
     *     
     */
    public void setModels(Family.Models value) {
        this.models = value;
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
     *         &lt;element name="license" type="{}licenseType"/>
     *         &lt;element name="name" type="{http://www.w3.org/2001/XMLSchema}string"/>
     *         &lt;element name="description" type="{http://www.w3.org/2001/XMLSchema}string"/>
     *         &lt;element name="author" type="{http://www.w3.org/2001/XMLSchema}string"/>
     *         &lt;element name="URL" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
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
        "url"
    })
    public static class Metadata {

        @XmlElement(required = true)
        protected LicenseType license;
        @XmlElement(required = true)
        protected String name;
        @XmlElement(required = true)
        protected String description;
        @XmlElement(required = true)
        protected String author;
        @XmlElement(name = "URL")
        protected String url;

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
     *         &lt;element name="model" type="{}modelType" maxOccurs="unbounded"/>
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
        "model"
    })
    public static class Models {

        @XmlElement(required = true)
        protected List<ModelType> model;

        /**
         * Gets the value of the model property.
         * 
         * <p>
         * This accessor method returns a reference to the live list,
         * not a snapshot. Therefore any modification you make to the
         * returned list will be present inside the JAXB object.
         * This is why there is not a <CODE>set</CODE> method for the model property.
         * 
         * <p>
         * For example, to add a new item, do as follows:
         * <pre>
         *    getModel().add(newItem);
         * </pre>
         * 
         * 
         * <p>
         * Objects of the following type(s) are allowed in the list
         * {@link ModelType }
         * 
         * 
         */
        public List<ModelType> getModel() {
            if (model == null) {
                model = new ArrayList<ModelType>();
            }
            return this.model;
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
     *         &lt;element name="stages">
     *           &lt;complexType>
     *             &lt;complexContent>
     *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
     *                 &lt;choice maxOccurs="21">
     *                   &lt;element name="multiStage" type="{}multiStageType" maxOccurs="10"/>
     *                   &lt;element name="scaleStage" type="{}scaleStageType" maxOccurs="10"/>
     *                   &lt;element name="animationStage" type="{}animationStageType"/>
     *                 &lt;/choice>
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
        "stages"
    })
    public static class Properties {

        @XmlElement(required = true)
        protected Family.Properties.Stages stages;

        /**
         * Obtiene el valor de la propiedad stages.
         * 
         * @return
         *     possible object is
         *     {@link Family.Properties.Stages }
         *     
         */
        public Family.Properties.Stages getStages() {
            return stages;
        }

        /**
         * Define el valor de la propiedad stages.
         * 
         * @param value
         *     allowed object is
         *     {@link Family.Properties.Stages }
         *     
         */
        public void setStages(Family.Properties.Stages value) {
            this.stages = value;
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
         *       &lt;choice maxOccurs="21">
         *         &lt;element name="multiStage" type="{}multiStageType" maxOccurs="10"/>
         *         &lt;element name="scaleStage" type="{}scaleStageType" maxOccurs="10"/>
         *         &lt;element name="animationStage" type="{}animationStageType"/>
         *       &lt;/choice>
         *     &lt;/restriction>
         *   &lt;/complexContent>
         * &lt;/complexType>
         * </pre>
         * 
         * 
         */
        @XmlAccessorType(XmlAccessType.FIELD)
        @XmlType(name = "", propOrder = {
            "multiStageOrScaleStageOrAnimationStage"
        })
        public static class Stages {

            @XmlElements({
                @XmlElement(name = "multiStage", type = MultiStageType.class),
                @XmlElement(name = "scaleStage", type = ScaleStageType.class),
                @XmlElement(name = "animationStage", type = AnimationStageType.class)
            })
            protected List<Object> multiStageOrScaleStageOrAnimationStage;

            /**
             * Gets the value of the multiStageOrScaleStageOrAnimationStage property.
             * 
             * <p>
             * This accessor method returns a reference to the live list,
             * not a snapshot. Therefore any modification you make to the
             * returned list will be present inside the JAXB object.
             * This is why there is not a <CODE>set</CODE> method for the multiStageOrScaleStageOrAnimationStage property.
             * 
             * <p>
             * For example, to add a new item, do as follows:
             * <pre>
             *    getMultiStageOrScaleStageOrAnimationStage().add(newItem);
             * </pre>
             * 
             * 
             * <p>
             * Objects of the following type(s) are allowed in the list
             * {@link MultiStageType }
             * {@link ScaleStageType }
             * {@link AnimationStageType }
             * 
             * 
             */
            public List<Object> getMultiStageOrScaleStageOrAnimationStage() {
                if (multiStageOrScaleStageOrAnimationStage == null) {
                    multiStageOrScaleStageOrAnimationStage = new ArrayList<Object>();
                }
                return this.multiStageOrScaleStageOrAnimationStage;
            }

        }

    }

}
