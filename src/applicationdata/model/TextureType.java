//
// Este archivo ha sido generado por la arquitectura JavaTM para la implantación de la referencia de enlace (JAXB) XML v2.2.6 
// Visite <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Todas las modificaciones realizadas en este archivo se perderán si se vuelve a compilar el esquema de origen. 
// Generado el: PM.03.13 a las 05:23:37 PM CET 
//


package applicationdata.model;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElements;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Clase Java para textureType complex type.
 * 
 * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 * 
 * <pre>
 * &lt;complexType name="textureType">
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
@XmlType(name = "textureType", propOrder = {
    "baseShadowTextureOrSimpleTextureOrDoubleTexture"
})
public class TextureType {

    @XmlElements({
        @XmlElement(name = "baseShadowTexture", type = BaseShadowTextureType.class),
        @XmlElement(name = "simpleTexture", type = SimpleTextureType.class),
        @XmlElement(name = "doubleTexture", type = DoubleTextureType.class),
        @XmlElement(name = "multiOptionTexture", type = MultiOptionTextureType.class)
    })
    protected List<Object> baseShadowTextureOrSimpleTextureOrDoubleTexture;

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
    public List<Object> getBaseShadowTextureOrSimpleTextureOrDoubleTexture() {
        if (baseShadowTextureOrSimpleTextureOrDoubleTexture == null) {
            baseShadowTextureOrSimpleTextureOrDoubleTexture = new ArrayList<Object>();
        }
        return this.baseShadowTextureOrSimpleTextureOrDoubleTexture;
    }

}
