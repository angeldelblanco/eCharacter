//
// Este archivo ha sido generado por la arquitectura JavaTM para la implantación de la referencia de enlace (JAXB) XML v2.2.6 
// Visite <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Todas las modificaciones realizadas en este archivo se perderán si se vuelve a compilar el esquema de origen. 
// Generado el: AM.04.11 a las 10:46:54 AM CEST 
//


package data.model;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Clase Java para baseShadowTextureSubMeshType complex type.
 * 
 * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 * 
 * <pre>
 * &lt;complexType name="baseShadowTextureSubMeshType">
 *   &lt;complexContent>
 *     &lt;extension base="{}textureSubMeshType">
 *       &lt;sequence>
 *         &lt;element name="path" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="shadowPath" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "baseShadowTextureSubMeshType", propOrder = {
    "path",
    "shadowPath"
})
public class BaseShadowTextureSubMeshType
    extends TextureSubMeshType
{

    @XmlElement(required = true)
    protected String path;
    protected String shadowPath;

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
     * Obtiene el valor de la propiedad shadowPath.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getShadowPath() {
        return shadowPath;
    }

    /**
     * Define el valor de la propiedad shadowPath.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setShadowPath(String value) {
        this.shadowPath = value;
    }

}
