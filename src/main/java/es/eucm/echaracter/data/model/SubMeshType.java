//
// Este archivo ha sido generado por la arquitectura JavaTM para la implantación de la referencia de enlace (JAXB) XML v2.2.6 
// Visite <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Todas las modificaciones realizadas en este archivo se perderán si se vuelve a compilar el esquema de origen. 
// Generado el: PM.06.27 a las 01:32:19 PM CEST 
//


package es.eucm.echaracter.data.model;

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
 * <p>Clase Java para subMeshType complex type.
 * 
 * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 * 
 * <pre>
 * &lt;complexType name="subMeshType">
 *   &lt;complexContent>
 *     &lt;extension base="{}meshType">
 *       &lt;sequence>
 *         &lt;element name="subMeshTexture" minOccurs="0">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 &lt;choice>
 *                   &lt;element name="baseShadowTextureSubMesh" type="{}baseShadowTextureSubMeshType"/>
 *                   &lt;element name="simpleTextureSubMesh" type="{}simpleTextureSubMeshType"/>
 *                   &lt;element name="doubleTextureSubMesh" type="{}doubleTextureSubMeshType"/>
 *                   &lt;element name="multiOptionTextureSubMesh" type="{}multiOptionTextureSubMeshType"/>
 *                 &lt;/choice>
 *               &lt;/restriction>
 *             &lt;/complexContent>
 *           &lt;/complexType>
 *         &lt;/element>
 *         &lt;element name="iconPath" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *       &lt;/sequence>
 *       &lt;attribute name="idPanelRef" use="required" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="associatedBone" use="required" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="idSubMesh" use="required" type="{http://www.w3.org/2001/XMLSchema}ID" />
 *       &lt;attribute name="default" use="required" type="{http://www.w3.org/2001/XMLSchema}boolean" />
 *       &lt;attribute name="text" use="required" type="{http://www.w3.org/2001/XMLSchema}string" />
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "subMeshType", propOrder = {
    "subMeshTexture",
    "iconPath"
})
public class SubMeshType
    extends MeshType
{

    protected SubMeshType.SubMeshTexture subMeshTexture;
    @XmlElement(required = true)
    protected String iconPath;
    @XmlAttribute(name = "idPanelRef", required = true)
    protected String idPanelRef;
    @XmlAttribute(name = "associatedBone", required = true)
    protected String associatedBone;
    @XmlAttribute(name = "idSubMesh", required = true)
    @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
    @XmlID
    @XmlSchemaType(name = "ID")
    protected String idSubMesh;
    @XmlAttribute(name = "default", required = true)
    protected boolean _default;
    @XmlAttribute(name = "text", required = true)
    protected String text;

    /**
     * Obtiene el valor de la propiedad subMeshTexture.
     * 
     * @return
     *     possible object is
     *     {@link SubMeshType.SubMeshTexture }
     *     
     */
    public SubMeshType.SubMeshTexture getSubMeshTexture() {
        return subMeshTexture;
    }

    /**
     * Define el valor de la propiedad subMeshTexture.
     * 
     * @param value
     *     allowed object is
     *     {@link SubMeshType.SubMeshTexture }
     *     
     */
    public void setSubMeshTexture(SubMeshType.SubMeshTexture value) {
        this.subMeshTexture = value;
    }

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
     * Obtiene el valor de la propiedad idPanelRef.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getIdPanelRef() {
        return idPanelRef;
    }

    /**
     * Define el valor de la propiedad idPanelRef.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setIdPanelRef(String value) {
        this.idPanelRef = value;
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

    /**
     * Obtiene el valor de la propiedad idSubMesh.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getIdSubMesh() {
        return idSubMesh;
    }

    /**
     * Define el valor de la propiedad idSubMesh.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setIdSubMesh(String value) {
        this.idSubMesh = value;
    }

    /**
     * Obtiene el valor de la propiedad default.
     * 
     */
    public boolean isDefault() {
        return _default;
    }

    /**
     * Define el valor de la propiedad default.
     * 
     */
    public void setDefault(boolean value) {
        this._default = value;
    }

    /**
     * Obtiene el valor de la propiedad text.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getText() {
        return text;
    }

    /**
     * Define el valor de la propiedad text.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setText(String value) {
        this.text = value;
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
     *       &lt;choice>
     *         &lt;element name="baseShadowTextureSubMesh" type="{}baseShadowTextureSubMeshType"/>
     *         &lt;element name="simpleTextureSubMesh" type="{}simpleTextureSubMeshType"/>
     *         &lt;element name="doubleTextureSubMesh" type="{}doubleTextureSubMeshType"/>
     *         &lt;element name="multiOptionTextureSubMesh" type="{}multiOptionTextureSubMeshType"/>
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
        "baseShadowTextureSubMesh",
        "simpleTextureSubMesh",
        "doubleTextureSubMesh",
        "multiOptionTextureSubMesh"
    })
    public static class SubMeshTexture {

        protected BaseShadowTextureSubMeshType baseShadowTextureSubMesh;
        protected SimpleTextureSubMeshType simpleTextureSubMesh;
        protected DoubleTextureSubMeshType doubleTextureSubMesh;
        protected MultiOptionTextureSubMeshType multiOptionTextureSubMesh;

        /**
         * Obtiene el valor de la propiedad baseShadowTextureSubMesh.
         * 
         * @return
         *     possible object is
         *     {@link BaseShadowTextureSubMeshType }
         *     
         */
        public BaseShadowTextureSubMeshType getBaseShadowTextureSubMesh() {
            return baseShadowTextureSubMesh;
        }

        /**
         * Define el valor de la propiedad baseShadowTextureSubMesh.
         * 
         * @param value
         *     allowed object is
         *     {@link BaseShadowTextureSubMeshType }
         *     
         */
        public void setBaseShadowTextureSubMesh(BaseShadowTextureSubMeshType value) {
            this.baseShadowTextureSubMesh = value;
        }

        /**
         * Obtiene el valor de la propiedad simpleTextureSubMesh.
         * 
         * @return
         *     possible object is
         *     {@link SimpleTextureSubMeshType }
         *     
         */
        public SimpleTextureSubMeshType getSimpleTextureSubMesh() {
            return simpleTextureSubMesh;
        }

        /**
         * Define el valor de la propiedad simpleTextureSubMesh.
         * 
         * @param value
         *     allowed object is
         *     {@link SimpleTextureSubMeshType }
         *     
         */
        public void setSimpleTextureSubMesh(SimpleTextureSubMeshType value) {
            this.simpleTextureSubMesh = value;
        }

        /**
         * Obtiene el valor de la propiedad doubleTextureSubMesh.
         * 
         * @return
         *     possible object is
         *     {@link DoubleTextureSubMeshType }
         *     
         */
        public DoubleTextureSubMeshType getDoubleTextureSubMesh() {
            return doubleTextureSubMesh;
        }

        /**
         * Define el valor de la propiedad doubleTextureSubMesh.
         * 
         * @param value
         *     allowed object is
         *     {@link DoubleTextureSubMeshType }
         *     
         */
        public void setDoubleTextureSubMesh(DoubleTextureSubMeshType value) {
            this.doubleTextureSubMesh = value;
        }

        /**
         * Obtiene el valor de la propiedad multiOptionTextureSubMesh.
         * 
         * @return
         *     possible object is
         *     {@link MultiOptionTextureSubMeshType }
         *     
         */
        public MultiOptionTextureSubMeshType getMultiOptionTextureSubMesh() {
            return multiOptionTextureSubMesh;
        }

        /**
         * Define el valor de la propiedad multiOptionTextureSubMesh.
         * 
         * @param value
         *     allowed object is
         *     {@link MultiOptionTextureSubMeshType }
         *     
         */
        public void setMultiOptionTextureSubMesh(MultiOptionTextureSubMeshType value) {
            this.multiOptionTextureSubMesh = value;
        }

    }

}
