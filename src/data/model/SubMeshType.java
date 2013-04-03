//
// Este archivo ha sido generado por la arquitectura JavaTM para la implantación de la referencia de enlace (JAXB) XML v2.2.6 
// Visite <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Todas las modificaciones realizadas en este archivo se perderán si se vuelve a compilar el esquema de origen. 
// Generado el: PM.04.03 a las 05:48:55 PM CEST 
//


package data.model;

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
 *         &lt;element name="iconPath" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *       &lt;/sequence>
 *       &lt;attribute name="idPanelRef" use="required" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="associatedBone" use="required" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="idSubMesh" use="required" type="{http://www.w3.org/2001/XMLSchema}ID" />
 *       &lt;attribute name="default" use="required" type="{http://www.w3.org/2001/XMLSchema}boolean" />
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "subMeshType", propOrder = {
    "iconPath"
})
public class SubMeshType
    extends MeshType
{

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

}
