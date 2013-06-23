/*******************************************************************************
 * <eCharacter> is a research project of the <e-UCM>
 *          research group.
 *
 *    Developed by: Alejandro Muñoz del Rey, Sergio de Luis Nieto and David González
 *    Ledesma.
 *    Under the supervision of Baltasar Fernández-Manjón and Javier Torrente
 * 
 *    Copyright 2012-2013 <e-UCM> research group.
 *  
 *     <e-UCM> is a research group of the Department of Software Engineering
 *          and Artificial Intelligence at the Complutense University of Madrid
 *          (School of Computer Science).
 *  
 *          C Profesor Jose Garcia Santesmases sn,
 *          28040 Madrid (Madrid), Spain.
 *  
 *          For more info please visit:  <http://character.e-ucm.es>, 
 *          <http://e-adventure.e-ucm.es> or <http://www.e-ucm.es>
 *  
 *  ****************************************************************************
 *      <eCharacter> is free software: you can 
 *      redistribute it and/or modify it under the terms of the GNU Lesser 
 *      General Public License as published by the Free Software Foundation, 
 *      either version 3 of the License, or (at your option) any later version.
 *  
 *      <eCharacter> is distributed in the hope that it 
 *      will be useful, but WITHOUT ANY WARRANTY; without even the implied 
 *      warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  
 *      See the GNU Lesser General Public License for more details.
 *  
 *      You should have received a copy of the GNU Lesser General Public License
 *      along with <eCharacter>. If not, 
 *      see <http://www.gnu.org/licenses/>.
 ******************************************************************************/

package es.eucm.echaracter.control;

import com.jme3.asset.AssetManager;
import com.jme3.math.Vector3f;
import com.jme3.niftygui.NiftyJmeDisplay;
import com.jme3.renderer.Camera;
import com.jme3.renderer.ViewPort;
import com.jme3.scene.Node;
import es.eucm.echaracter.data.model.EscalationType;
import es.eucm.echaracter.data.model.Model;
import es.eucm.echaracter.data.model.SubMeshType;
import es.eucm.echaracter.data.model.TextureType;
import es.eucm.echaracter.data.texturessubmeshesdata.TexturesSubMeshesData;
import es.eucm.echaracter.export.CameraValues;
import es.eucm.echaracter.export.ScreenshotMyAppState;
import es.eucm.echaracter.loader.Configuration;
import es.eucm.echaracter.loader.FamilyWithPath;
import es.eucm.echaracter.loader.XMLFamilyReader;
import es.eucm.echaracter.loader.XMLReader;
import es.eucm.echaracter.main.Application;
import es.eucm.echaracter.types.ElementType;
import es.eucm.echaracter.types.StageType;
import es.eucm.echaracter.types.TexturesMeshType;
import java.awt.Color;
import java.util.ArrayList;
import java.util.Iterator;

public class Control {
    
    private Configuration config;
    private Node rootNode;
    private Camera camera;
    private AssetManager assetManager;
    private Application app;
    private ViewPort viewPort;
    private ViewPort guiViewPort;
    private NiftyJmeDisplay niftyDisplay;
    private ScreenshotMyAppState screenShotState;
    
    private ArrayList<FamilyWithPath> families;
    private Model model;
    private FamilyControl fc;
    private ModelControl mc;
    private SceneControl sc;
    
    public Control(Configuration config, Node rootNode,Camera camera,AssetManager assetManager, Application app, 
            ViewPort viewPort, ViewPort guiViewPort, NiftyJmeDisplay niftyDisplay, ScreenshotMyAppState screenShotState){
        this.config = config;
        String familiesPath = this.config.getProperty(Configuration.FAMILIES_PATH);
        XMLFamilyReader xmlReader = new XMLFamilyReader(familiesPath);
        families = xmlReader.readXML();
        this.assetManager = assetManager;
        this.rootNode = rootNode;
        this.camera = camera;
        this.app = app;
        this.viewPort = viewPort;
        this.guiViewPort = guiViewPort;
        this.niftyDisplay = niftyDisplay;
        this.screenShotState = screenShotState;
    }
    
    /*************************Getter******************************************/
    public AssetManager getAssetManager() {
        return assetManager;
    }

    public Node getRootNode() {
        return rootNode;
    }
    
    public Camera getCamera(){
        return camera;
    }
    
    public ViewPort getViewPort() {
        return viewPort;
    }

    public ViewPort getGuiViewPort() {
        return guiViewPort;
    }

    public NiftyJmeDisplay getNiftyDisplay() {
        return niftyDisplay;
    }

    public ScreenshotMyAppState getScreenShotState() {
        return screenShotState;
    }
    /*************************Family******************************************/
    public ArrayList<String> getFamiliesID(){
        ArrayList<String> listID = new ArrayList<String>();
        Iterator<FamilyWithPath> it = families.iterator();
        while(it.hasNext()){
            FamilyWithPath family = it.next();
            listID.add(family.getPath());
        }
        return listID;
    }
    
    public void selectFamily(String idFamily){
        Iterator<FamilyWithPath> it = families.iterator();
        while(it.hasNext()){
            FamilyWithPath family = it.next();
            if (family.getPath().equals(idFamily)){
                fc = new FamilyControl(family.getFamily());
            }
        }
    }
    //Antes de llamar a estos métodos hay que hacer antes un selectFamily
    public String getIconPathFamily(){
        return fc.getIconPathFamily();
    }
    
    public String getLanguageFamilyPath(){
        return fc.getLanguagePath();
    }
    
    public ArrayList<String> getModelsLabel(){
        return fc.getModelsLabels();
    }
    
    public String getModelIconPath(String modelLabel){
        return fc.getModelIconPath(modelLabel);
    }
    
    public String getModelFamilyPath(String modelLabel){
        return fc.getModelPath(modelLabel);
    }
    
    public int getNumModels(){
        return fc.getNumModels();
    }
    
    public String getMetadataFamilyName(){
        return fc.getMetadataName();
    }
    
    public String getMetadataFamilyURL(){
        return fc.getMetadataURL();
    }
    
    public String getMetadataFamilyAbstract(){
        return fc.getMetadataAbstract();
    }
    
    public String getMetadataFamilyDescription(){
        return fc.getMetadataDescription();
    }
    
    public String getMetadataFamilyAuthor(){
        return fc.getMetadataAuthor();
    }
    
    public ArrayList<String> getStagesLabels(){
        return fc.getStagesLabels();
    }
    
    public StageType getStageTypes(String idStage){
        return fc.getStagesTypes(idStage);
    }
    
    public String getIconPathStage(String idStage){
        return fc.getIconPathStage(idStage);
    }
    
    public int getNumCameras(){
        return fc.getNumCameras();
    }
    
    public ArrayList<String> getCamerasLabels(){
        return fc.getCamerasLabels();
    }
    
    public CameraValues getCameraValues(String cameraLabel){
        return fc.getCameraValues(cameraLabel);
    }
    
    public int getNumQualities(){
        return fc.getNumQualities();
    }
    
    public ArrayList<String> getQualityLabels(){
        return fc.getQualityLabels();
    }
    
    public int getQualityValue(String qualityLabel){
        return fc.getQualityValue(qualityLabel);
    }
    
    public ArrayList<String> getIdsSubStages(String stageLabel){
        return fc.getIdsSubStages(stageLabel);
    }
    
    public int getNumSubStages(String stageLabel){
        return fc.getNumSubStage(stageLabel);
    }
    
    public String getSubStageLabel(String multiStageLabel,String idSubStage){
        return fc.getSubStageLabel(multiStageLabel, idSubStage);
    }
    
    public ArrayList<String> getIdBonesController(String idStageLabel){
        return fc.getIdBonesController(idStageLabel);
    }
    
    public String getBoneControllerLabel(String idStageLabel,String idBoneController){
        return fc.getBoneControllerLabel(idStageLabel, idBoneController);
    }
    
    //Devuelve -1 en caso de que haya error.
    public int getBoneControllerLevel(String idStageLabel,String idBoneController){
        return fc.getBoneControllerLevel(idStageLabel, idBoneController);
    }
    
    /*************************Models******************************************/
    
    public void selectModel(String pathModel){
        XMLReader xmlReader2 = new XMLReader<Model>(pathModel);
        ArrayList<Model> models = xmlReader2.readXML(Model.class);
        if (models.size()==1){
            model = models.get(0);
        }
        mc = new ModelControl(model);
        TexturesSubMeshesData texturesSubMeshesData = new TexturesSubMeshesData(fc.getAllSubStages(), mc.getAllTextures(), mc.getAllSubMeshes());
        sc = new SceneControl(this, config, mc.getMainMeshPath(), mc.getMainMeshTransformations(),texturesSubMeshesData);
    }
   
    public String getLanguageModelPath(){
        return mc.getLanguagePath();
    }
    
    public ArrayList<String> getIdsTexturesORSubMeshes(String idPanelRef){
        return mc.getIdsTexturesORSubMeshes(idPanelRef);
    }
    
    public int getNumTexturesORSubMeshes(String idPanelRef){
        return mc.getNumTexturesORSubMeshes(idPanelRef);
    }
    
    public String getIconPathTexturesORSubMeshes(String idTexturesORSubMeshes){
        return mc.getIconPathTexturesORSubMeshes(idTexturesORSubMeshes);
    }
    
    public String getTextTexturesORSubMeshes(String idTexturesORSubMeshes){
        return mc.getTextTexturesORSubMeshes(idTexturesORSubMeshes);
    }
    
    public ArrayList<String> getIdsSubTexturesInMultiOption(String idMultiOption){
        return mc.getIdsTexturesInMultiOption(idMultiOption);
    }
    
    public String getTextSubTextureInMultiOption(String idMultiOption, String idSubTexture){
        return mc.getTextSubTextureInMultiOption(idMultiOption, idSubTexture);
    }
    
    public int getNumTexturesInMultiOption(String idMultiOption){
        return mc.getNumTexturesInMultiOption(idMultiOption);
    }
    
    public String getIconPathInMultiOption(String idMultiOption,String idSubTexture){
        return mc.getIconPathTextureInMultiOption(idMultiOption, idSubTexture);
    }
    
    public ArrayList<String> getIdsPhysicalBuild(String idPanelRef){
        return mc.getIdsPhysicalBuild(idPanelRef);
    }
    
    public String getPhysicalBuildLabel(String idPhysicalBuild){
        return mc.getPhysicalBuildLabel(idPhysicalBuild);
    }
    
    public float getMaxValueBoneController(String idBoneController){
        return mc.getMaxValueBoneController(idBoneController);
    }
    
    public float getMinValueBoneController(String idBoneController){
        return mc.getMinValueBoneController(idBoneController);
    }
    
    public float getDefaultValueBoneController(String idBoneController){
        return mc.getDefaultValueBoneController(idBoneController);
    }
    
    public void setDefaultValueBoneController(String idBoneController, float value){
        mc.setDefaultValueBoneController(idBoneController, value);
    }
    
    public TexturesMeshType getTextureType(String idTextureOrSubMesh){
        return mc.getTextureType(idTextureOrSubMesh);
    }
    
    /*************************Scene******************************************/
    public void loadBackground(){
        app.loadBackground();
    }
    
    public void removeBackground(){
        app.removeBackground();
    }
    
    public ArrayList<String> getAnimationNames(){
        return sc.getAnimationsName();
    }
    
    public int getNumAnimations(){
        return sc.getNumAnimations();
    }
    
    public void setAnimations(String animationName){
        sc.setAnimation(animationName);
    }
    
    public void changeTextureOrSubMesh(String idPanelRef, String idTextureOrSubMesh){
        TextureType texture = mc.getTexture(idTextureOrSubMesh);
        if (texture != null){
            //It's a texture.
            sc.changeTexture(idPanelRef, texture.getIdTexture());
        }
        else{
            //It's a submesh
            SubMeshType subMesh = mc.getSubMesh(idTextureOrSubMesh);
            if (subMesh != null){
                sc.changeSubMesh(idPanelRef, subMesh.getIdSubMesh());
            }
        }
    }
    
    private ElementType getElementType(String idTextureOrSubmesh){
        if (mc.getTexture(idTextureOrSubmesh) != null){
            return ElementType.Texture;
        }
        else if (mc.getSubMesh(idTextureOrSubmesh) != null){
            return ElementType.SubMesh;
        }
        else{
            return null;
        }
    }
    
    public void changeColorBaseShadow(String idPanelRef, String idTextureOrSubMesh, float red,float green,float blue){
        ElementType type = getElementType(idTextureOrSubMesh);
        if (type != null){
            sc.changeColorBaseShadow(idPanelRef, idTextureOrSubMesh, type, red, green, blue);
        }
    }
    
    public void changeColorDoubleTexture(String idPanelRef, String idTextureOrSubMesh, float red1,float green1,float blue1, 
            float red2,float green2,float blue2){
        ElementType type = getElementType(idTextureOrSubMesh);
        if (type != null){
            if (red1 == -1 && green1 == -1 && blue1 == -1){
                //-1 representa que el usuario no ha cambiado la textura de base en este caso
                //Cambiar solo los detalles
                sc.changeColorDoubleTextureDetails(idPanelRef, idTextureOrSubMesh, type, red2, green2, blue2);
            }
            else if(red2 == -1 && green2 == -1 && blue2 == -1){
                //-1 representa que el usuario no ha cambiado la textura de detalles en este caso
                //Cambiar solo la base
                sc.changeColorDoubleTextureBase(idPanelRef, idTextureOrSubMesh, type, red1, green1, blue1);
            }
            else{
                //Cambiar base y detalles
                sc.changeColorDoubleTextureBase(idPanelRef, idTextureOrSubMesh, type, red1, green1, blue1);
                sc.changeColorDoubleTextureDetails(idPanelRef, idTextureOrSubMesh, type, red2, green2, blue2);
            }
        }
    }
    
    public void changeColorMultiOptionTexture(String idPanelRef, String idTextureOrSubMesh,String idSubTexture){
        ElementType type = getElementType(idTextureOrSubMesh);
        if (type != null){
            sc.changeColorMultiOptionTexture(idPanelRef, idTextureOrSubMesh, type, idSubTexture);
        }
    }
    
    /* Return the last color selected by user. 
     * If texture is BaseShadow, the ArrayList only has one element.
     * if texture is BaseShadow, return null if there isn't any color selected 
     * If texture is DobleTexture, the ArrayList has two elements (the first element is color of base, and the second element is color of details).
     * If texture is DobleTexture, return null if there isn't any color selected or return the ArrayList with one of the elemtns null, when 
     * the base or details haven't any color slected. 
     * ArrayList(0) -> Base
     * ArrayList(1) -> Details
     */
    public ArrayList<Color> getColorTexture(String idPanelRef, String idTextureOrSubMesh){
        ElementType type = getElementType(idTextureOrSubMesh);
        if (type != null){
            return sc.getColorTexture(idPanelRef, idTextureOrSubMesh, type);
        }
        return null;
    }
    
    public void screenShot(){
        sc.screenShot();
    }
    
    public void setBoneControllerValue(String idBoneController, float inc){
        ArrayList<String> listBones = mc.getBones(idBoneController);
        sc.scaleBone(listBones, inc);
    }
    
    public void setPhysicalBuild(String idPhysicalBuild){
        ArrayList<EscalationType> listEscalations = mc.getPhysicalBuildEscalations(idPhysicalBuild);
        sc.applyEscalations(listEscalations);
    }
    
    public void removeModel(){
        app.removeBackground();
        sc.removeModel();
    }
    
    public boolean isChecked(String idPanel, String idTextureOrSubMesh){
        boolean checked = false;
        TextureType texture = mc.getTexture(idTextureOrSubMesh);
        if (texture != null){
            //It's a texture.
            checked = sc.isCheckedTexture(idPanel, idTextureOrSubMesh);
        }
        else{
            //It's a submesh
            SubMeshType subMesh = mc.getSubMesh(idTextureOrSubMesh);
            if (subMesh != null){
                checked = sc.isCheckedSubMesh(idPanel, idTextureOrSubMesh);
            }
        }
        return checked;
    }
    
    public boolean isMultiSelection(String labelStage, String idPanel){
        return fc.isMultiSelection(labelStage,idPanel);
    }
    
    public void clickAnimation(String animationName, boolean check){
        sc.clickAnimation(animationName, check);
    }
    
    public boolean isCheckedAnimation(String animationName){
        return sc.isCheckedAnimation(animationName);
    }
    
    public void clickAllAnimations(boolean check){
        sc.clickAllAnimations(check);
    }
            
    public void clickCamera(String cameraLabel, boolean check){
        sc.clickCamera(cameraLabel, check);
    }
    
    public boolean isCheckedCamera(String cameraLabel){
        return sc.isCheckedCamera(cameraLabel);
    }
    
    public void clickAllCameras(boolean check){
        sc.clickAllCameras(check);
    }
    
    public void clickQuality(String qualityLabel, boolean check){
        sc.clickQuality(qualityLabel, check);
    }
    
    public boolean isCheckedQuality(String qualityLabel){
        return sc.isCheckedQuality(qualityLabel);
    }
    
    public void clickAllQualities(boolean check){
        sc.clickAllQualities(check);
    }
    
    //-------------------CAMERA CONTROLLER-----------------------------------/
    public void previewCamera(String idCamera){
        CameraValues cameraValues = fc.getCameraValues(idCamera);
        sc.setCameraView(cameraValues.getPosition(), cameraValues.getDirection(), cameraValues.getUp());
    }
    
    public void setViewCamera(Vector3f position,Vector3f direction,Vector3f up){
        sc.setCameraView(position,direction,up);
    }
    
    public void rotateModel(float ang){
        sc.rotateModel(ang);
    }
    
    public void restartRotationModel(){
        sc.restartRotationModel();
    }
    
    public void defaultCameraView(){
        sc.defaultCameraView();
    }

    
}
