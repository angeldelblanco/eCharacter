//
// Este archivo ha sido generado por la arquitectura JavaTM para la implantación de la referencia de enlace (JAXB) XML v2.2.6 
// Visite <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Todas las modificaciones realizadas en este archivo se perderán si se vuelve a compilar el esquema de origen. 
// Generado el: AM.03.11 a las 10:45:40 AM CET 
//


package applicationdata.model;

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
 *         &lt;element name="mesh" maxOccurs="unbounded">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 &lt;sequence>
 *                   &lt;element name="path" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                   &lt;element name="textures" type="{}textureType" minOccurs="0"/>
 *                   &lt;choice minOccurs="0">
 *                     &lt;element name="bones" type="{}bonesType"/>
 *                     &lt;element name="subMeshes" type="{}subMeshesType"/>
 *                   &lt;/choice>
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
    "mesh"
})
@XmlRootElement(name = "model")
public class Model {

    @XmlElement(required = true)
    protected List<Model.Mesh> mesh;

    /**
     * Gets the value of the mesh property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the mesh property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getMesh().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link Model.Mesh }
     * 
     * 
     */
    public List<Model.Mesh> getMesh() {
        if (mesh == null) {
            mesh = new ArrayList<Model.Mesh>();
        }
        return this.mesh;
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
     *         &lt;element name="path" type="{http://www.w3.org/2001/XMLSchema}string"/>
     *         &lt;element name="textures" type="{}textureType" minOccurs="0"/>
     *         &lt;choice minOccurs="0">
     *           &lt;element name="bones" type="{}bonesType"/>
     *           &lt;element name="subMeshes" type="{}subMeshesType"/>
     *         &lt;/choice>
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
        "path",
        "textures",
        "bones",
        "subMeshes"
    })
    public static class Mesh {

        @XmlElement(required = true)
        protected String path;
        protected TextureType textures;
        protected BonesType bones;
        protected SubMeshesType subMeshes;

        /**
         * Obtiene el valor de la propiedad path.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getPath() {
            return path;
        }

        /**
         * Define el valor de la propiedad path.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setPath(String value) {
            this.path = value;
        }

        /**
         * Obtiene el valor de la propiedad textures.
         * 
         * @return
         *     possible object is
         *     {@link TextureType }
         *     
         */
        public TextureType getTextures() {
            return textures;
        }

        /**
         * Define el valor de la propiedad textures.
         * 
         * @param value
         *     allowed object is
         *     {@link TextureType }
         *     
         */
        public void setTextures(TextureType value) {
            this.textures = value;
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
         * Obtiene el valor de la propiedad subMeshes.
         * 
         * @return
         *     possible object is
         *     {@link SubMeshesType }
         *     
         */
        public SubMeshesType getSubMeshes() {
            return subMeshes;
        }

        /**
         * Define el valor de la propiedad subMeshes.
         * 
         * @param value
         *     allowed object is
         *     {@link SubMeshesType }
         *     
         */
        public void setSubMeshes(SubMeshesType value) {
            this.subMeshes = value;
        }

    }

}
