//
// Este archivo ha sido generado por la arquitectura JavaTM para la implantación de la referencia de enlace (JAXB) XML v2.2.6 
// Visite <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Todas las modificaciones realizadas en este archivo se perderán si se vuelve a compilar el esquema de origen. 
// Generado el: AM.04.03 a las 11:47:25 AM CEST 
//


package data.family;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the data.family package. 
 * <p>An ObjectFactory allows you to programatically 
 * construct new instances of the Java representation 
 * for XML content. The Java representation of XML 
 * content can consist of schema derived interfaces 
 * and classes representing the binding of schema 
 * type definitions, element declarations and model 
 * groups.  Factory methods for each of these are 
 * provided in this class.
 * 
 */
@XmlRegistry
public class ObjectFactory {

    private final static QName _MultiStageTypeMeshSubStage_QNAME = new QName("", "meshSubStage");
    private final static QName _MultiStageTypeTextureSubStage_QNAME = new QName("", "textureSubStage");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: data.family
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link Family }
     * 
     */
    public Family createFamily() {
        return new Family();
    }

    /**
     * Create an instance of {@link ScaleStageType }
     * 
     */
    public ScaleStageType createScaleStageType() {
        return new ScaleStageType();
    }

    /**
     * Create an instance of {@link Family.Metadata }
     * 
     */
    public Family.Metadata createFamilyMetadata() {
        return new Family.Metadata();
    }

    /**
     * Create an instance of {@link Family.Stages }
     * 
     */
    public Family.Stages createFamilyStages() {
        return new Family.Stages();
    }

    /**
     * Create an instance of {@link Family.ModelsRef }
     * 
     */
    public Family.ModelsRef createFamilyModelsRef() {
        return new Family.ModelsRef();
    }

    /**
     * Create an instance of {@link AnimationStageType }
     * 
     */
    public AnimationStageType createAnimationStageType() {
        return new AnimationStageType();
    }

    /**
     * Create an instance of {@link CameraType }
     * 
     */
    public CameraType createCameraType() {
        return new CameraType();
    }

    /**
     * Create an instance of {@link ModelRefType }
     * 
     */
    public ModelRefType createModelRefType() {
        return new ModelRefType();
    }

    /**
     * Create an instance of {@link VectorType }
     * 
     */
    public VectorType createVectorType() {
        return new VectorType();
    }

    /**
     * Create an instance of {@link SubStageType }
     * 
     */
    public SubStageType createSubStageType() {
        return new SubStageType();
    }

    /**
     * Create an instance of {@link FpsType }
     * 
     */
    public FpsType createFpsType() {
        return new FpsType();
    }

    /**
     * Create an instance of {@link LicenseType }
     * 
     */
    public LicenseType createLicenseType() {
        return new LicenseType();
    }

    /**
     * Create an instance of {@link MultiStageType }
     * 
     */
    public MultiStageType createMultiStageType() {
        return new MultiStageType();
    }

    /**
     * Create an instance of {@link ScaleStageType.BoneController }
     * 
     */
    public ScaleStageType.BoneController createScaleStageTypeBoneController() {
        return new ScaleStageType.BoneController();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link SubStageType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "", name = "meshSubStage", scope = MultiStageType.class)
    public JAXBElement<SubStageType> createMultiStageTypeMeshSubStage(SubStageType value) {
        return new JAXBElement<SubStageType>(_MultiStageTypeMeshSubStage_QNAME, SubStageType.class, MultiStageType.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link SubStageType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "", name = "textureSubStage", scope = MultiStageType.class)
    public JAXBElement<SubStageType> createMultiStageTypeTextureSubStage(SubStageType value) {
        return new JAXBElement<SubStageType>(_MultiStageTypeTextureSubStage_QNAME, SubStageType.class, MultiStageType.class, value);
    }

}
