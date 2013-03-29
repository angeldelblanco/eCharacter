//
// Este archivo ha sido generado por la arquitectura JavaTM para la implantaci�n de la referencia de enlace (JAXB) XML v2.2.6 
// Visite <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Todas las modificaciones realizadas en este archivo se perder�n si se vuelve a compilar el esquema de origen. 
// Generado el: PM.03.29 a las 12:58:59 PM CET 
//


package data.model;

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
    "bones",
    "physicalBuilds"
})
public class MainMeshType
    extends MeshType
{

    @XmlElement(required = true)
    protected BonesType bones;
    @XmlElement(required = true)
    protected PhysicalBuildsType physicalBuilds;

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

}
