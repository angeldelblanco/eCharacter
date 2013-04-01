//
// Este archivo ha sido generado por la arquitectura JavaTM para la implantación de la referencia de enlace (JAXB) XML v2.2.6 
// Visite <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Todas las modificaciones realizadas en este archivo se perderán si se vuelve a compilar el esquema de origen. 
// Generado el: PM.04.01 a las 04:00:23 PM CEST 
//


package data.family;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Clase Java para animationStageType complex type.
 * 
 * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 * 
 * <pre>
 * &lt;complexType name="animationStageType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="fps" type="{}fpsType" maxOccurs="unbounded"/>
 *         &lt;element name="camera" type="{}cameraType" maxOccurs="unbounded"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "animationStageType", propOrder = {
    "fps",
    "camera"
})
public class AnimationStageType {

    @XmlElement(required = true)
    protected List<FpsType> fps;
    @XmlElement(required = true)
    protected List<CameraType> camera;

    /**
     * Gets the value of the fps property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the fps property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getFps().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link FpsType }
     * 
     * 
     */
    public List<FpsType> getFps() {
        if (fps == null) {
            fps = new ArrayList<FpsType>();
        }
        return this.fps;
    }

    /**
     * Gets the value of the camera property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the camera property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getCamera().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link CameraType }
     * 
     * 
     */
    public List<CameraType> getCamera() {
        if (camera == null) {
            camera = new ArrayList<CameraType>();
        }
        return this.camera;
    }

}
