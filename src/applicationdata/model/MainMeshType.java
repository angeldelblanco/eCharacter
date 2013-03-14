//
// Este archivo ha sido generado por la arquitectura JavaTM para la implantaci�n de la referencia de enlace (JAXB) XML v2.2.6 
// Visite <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Todas las modificaciones realizadas en este archivo se perder�n si se vuelve a compilar el esquema de origen. 
// Generado el: PM.03.14 a las 06:34:47 PM CET 
//


package applicationdata.model;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
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
 *         &lt;element name="bone" type="{}boneType" maxOccurs="10"/>
 *         &lt;element name="complexion" type="{}complexionType" maxOccurs="10"/>
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
    "bone",
    "complexion"
})
public class MainMeshType
    extends MeshType
{

    @XmlElement(required = true)
    protected List<BoneType> bone;
    @XmlElement(required = true)
    protected List<ComplexionType> complexion;

    /**
     * Gets the value of the bone property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the bone property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getBone().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link BoneType }
     * 
     * 
     */
    public List<BoneType> getBone() {
        if (bone == null) {
            bone = new ArrayList<BoneType>();
        }
        return this.bone;
    }

    /**
     * Gets the value of the complexion property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the complexion property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getComplexion().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link ComplexionType }
     * 
     * 
     */
    public List<ComplexionType> getComplexion() {
        if (complexion == null) {
            complexion = new ArrayList<ComplexionType>();
        }
        return this.complexion;
    }

}
