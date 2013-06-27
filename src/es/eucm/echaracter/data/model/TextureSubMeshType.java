//
// Este archivo ha sido generado por la arquitectura JavaTM para la implantaci�n de la referencia de enlace (JAXB) XML v2.2.6 
// Visite <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Todas las modificaciones realizadas en este archivo se perder�n si se vuelve a compilar el esquema de origen. 
// Generado el: PM.06.27 a las 01:32:19 PM CEST 
//


package es.eucm.echaracter.data.model;

import java.math.BigInteger;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlID;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.adapters.CollapsedStringAdapter;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;


/**
 * <p>Clase Java para textureSubMeshType complex type.
 * 
 * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 * 
 * <pre>
 * &lt;complexType name="textureSubMeshType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;attribute name="layer" use="required" type="{http://www.w3.org/2001/XMLSchema}integer" />
 *       &lt;attribute name="idTexture" use="required" type="{http://www.w3.org/2001/XMLSchema}ID" />
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "textureSubMeshType")
@XmlSeeAlso({
    MultiOptionTextureSubMeshType.class,
    BaseShadowTextureSubMeshType.class,
    SimpleTextureSubMeshType.class,
    DoubleTextureSubMeshType.class
})
public class TextureSubMeshType {

    @XmlAttribute(name = "layer", required = true)
    protected BigInteger layer;
    @XmlAttribute(name = "idTexture", required = true)
    @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
    @XmlID
    @XmlSchemaType(name = "ID")
    protected String idTexture;

    /**
     * Obtiene el valor de la propiedad layer.
     * 
     * @return
     *     possible object is
     *     {@link BigInteger }
     *     
     */
    public BigInteger getLayer() {
        return layer;
    }

    /**
     * Define el valor de la propiedad layer.
     * 
     * @param value
     *     allowed object is
     *     {@link BigInteger }
     *     
     */
    public void setLayer(BigInteger value) {
        this.layer = value;
    }

    /**
     * Obtiene el valor de la propiedad idTexture.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getIdTexture() {
        return idTexture;
    }

    /**
     * Define el valor de la propiedad idTexture.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setIdTexture(String value) {
        this.idTexture = value;
    }

}
