//
// Este archivo ha sido generado por la arquitectura JavaTM para la implantaci�n de la referencia de enlace (JAXB) XML v2.2.6 
// Visite <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Todas las modificaciones realizadas en este archivo se perder�n si se vuelve a compilar el esquema de origen. 
// Generado el: AM.04.11 a las 10:46:54 AM CEST 
//


package es.eucm.character.data.model;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElements;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Clase Java para mainMeshType complex type.
 * 
 * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 * 
 * <pre>
 * &lt;complexType name="mainMeshType">
 *   &lt;complexContent>
 *     &lt;extension base="{}meshType">
 *       &lt;sequence>
 *         &lt;element name="mainMeshTextures" minOccurs="0">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 &lt;choice maxOccurs="unbounded">
 *                   &lt;element name="baseShadowTexture" type="{}baseShadowTextureType"/>
 *                   &lt;element name="simpleTexture" type="{}simpleTextureType"/>
 *                   &lt;element name="doubleTexture" type="{}doubleTextureType"/>
 *                   &lt;element name="multiOptionTexture" type="{}multiOptionTextureType"/>
 *                 &lt;/choice>
 *               &lt;/restriction>
 *             &lt;/complexContent>
 *           &lt;/complexType>
 *         &lt;/element>
 *         &lt;element name="bones" type="{}bonesType"/>
 *         &lt;element name="physicalBuilds" type="{}physicalBuildsType"/>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "mainMeshType", propOrder = {
    "mainMeshTextures",
    "bones",
    "physicalBuilds"
})
public class MainMeshType
    extends MeshType
{

    protected MainMeshType.MainMeshTextures mainMeshTextures;
    @XmlElement(required = true)
    protected BonesType bones;
    @XmlElement(required = true)
    protected PhysicalBuildsType physicalBuilds;

    /**
     * Obtiene el valor de la propiedad mainMeshTextures.
     * 
     * @return
     *     possible object is
     *     {@link MainMeshType.MainMeshTextures }
     *     
     */
    public MainMeshType.MainMeshTextures getMainMeshTextures() {
        return mainMeshTextures;
    }

    /**
     * Define el valor de la propiedad mainMeshTextures.
     * 
     * @param value
     *     allowed object is
     *     {@link MainMeshType.MainMeshTextures }
     *     
     */
    public void setMainMeshTextures(MainMeshType.MainMeshTextures value) {
        this.mainMeshTextures = value;
    }

    /**
     * Obtiene el valor de la propiedad bones.
     * 
     * @return
     *     possible object is
     *     {@link BonesType }
     *     
     */
    public BonesType getBones() {
        return bones;
    }

    /**
     * Define el valor de la propiedad bones.
     * 
     * @param value
     *     allowed object is
     *     {@link BonesType }
     *     
     */
    public void setBones(BonesType value) {
        this.bones = value;
    }

    /**
     * Obtiene el valor de la propiedad physicalBuilds.
     * 
     * @return
     *     possible object is
     *     {@link PhysicalBuildsType }
     *     
     */
    public PhysicalBuildsType getPhysicalBuilds() {
        return physicalBuilds;
    }

    /**
     * Define el valor de la propiedad physicalBuilds.
     * 
     * @param value
     *     allowed object is
     *     {@link PhysicalBuildsType }
     *     
     */
    public void setPhysicalBuilds(PhysicalBuildsType value) {
        this.physicalBuilds = value;
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
     *       &lt;choice maxOccurs="unbounded">
     *         &lt;element name="baseShadowTexture" type="{}baseShadowTextureType"/>
     *         &lt;element name="simpleTexture" type="{}simpleTextureType"/>
     *         &lt;element name="doubleTexture" type="{}doubleTextureType"/>
     *         &lt;element name="multiOptionTexture" type="{}multiOptionTextureType"/>
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
        "baseShadowTextureOrSimpleTextureOrDoubleTexture"
    })
    public static class MainMeshTextures {

        @XmlElements({
            @XmlElement(name = "baseShadowTexture", type = BaseShadowTextureType.class),
            @XmlElement(name = "simpleTexture", type = SimpleTextureType.class),
            @XmlElement(name = "doubleTexture", type = DoubleTextureType.class),
            @XmlElement(name = "multiOptionTexture", type = MultiOptionTextureType.class)
        })
        protected List<TextureType> baseShadowTextureOrSimpleTextureOrDoubleTexture;

        /**
         * Gets the value of the baseShadowTextureOrSimpleTextureOrDoubleTexture property.
         * 
         * <p>
         * This accessor method returns a reference to the live list,
         * not a snapshot. Therefore any modification you make to the
         * returned list will be present inside the JAXB object.
         * This is why there is not a <CODE>set</CODE> method for the baseShadowTextureOrSimpleTextureOrDoubleTexture property.
         * 
         * <p>
         * For example, to add a new item, do as follows:
         * <pre>
         *    getBaseShadowTextureOrSimpleTextureOrDoubleTexture().add(newItem);
         * </pre>
         * 
         * 
         * <p>
         * Objects of the following type(s) are allowed in the list
         * {@link BaseShadowTextureType }
         * {@link SimpleTextureType }
         * {@link DoubleTextureType }
         * {@link MultiOptionTextureType }
         * 
         * 
         */
        public List<TextureType> getBaseShadowTextureOrSimpleTextureOrDoubleTexture() {
            if (baseShadowTextureOrSimpleTextureOrDoubleTexture == null) {
                baseShadowTextureOrSimpleTextureOrDoubleTexture = new ArrayList<TextureType>();
            }
            return this.baseShadowTextureOrSimpleTextureOrDoubleTexture;
        }

    }

}
