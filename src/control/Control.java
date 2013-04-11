/*******************************************************************************
 * <eAdventure Character Configurator> is a research project of the <e-UCM>
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
 *      <eAdventure Character Configurator> is free software: you can 
 *      redistribute it and/or modify it under the terms of the GNU Lesser 
 *      General Public License as published by the Free Software Foundation, 
 *      either version 3 of the License, or (at your option) any later version.
 *  
 *      <eAdventure Character Configurator> is distributed in the hope that it 
 *      will be useful, but WITHOUT ANY WARRANTY; without even the implied 
 *      warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  
 *      See the GNU Lesser General Public License for more details.
 *  
 *      You should have received a copy of the GNU Lesser General Public License
 *      along with <eAdventure Character Configurator>. If not, 
 *      see <http://www.gnu.org/licenses/>.
 ******************************************************************************/

package control;

import com.jme3.asset.AssetManager;
import com.jme3.scene.Node;
import data.family.Family;
import data.model.EscalationType;
import data.model.Model;
import data.model.SubMeshType;
import data.model.TextureType;
import data.texturessubmeshesdata.TexturesSubMeshesData;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Set;
import loader.Configuration;
import loader.XMLReader;
import types.StageType;
import types.TexturesType;

public class Control {
    
    private ArrayList<Family> families;
    private Model model;
    private FamilyControl fc;
    private ModelControl mc;
    private SceneControl sc;
    private AssetManager assetManager;
    private Node rootNode;
    
    public Control(Configuration config, Node rootNode, AssetManager assetManager)
    {
        String familiesPath = config.getProperty(Configuration.FamiliesPath);
        XMLReader<Family> xmlReader = new XMLReader<Family>(familiesPath);
        families = xmlReader.readXML(Family.class);
        this.assetManager = assetManager;
        this.rootNode = rootNode;
    }
    /*************************Family******************************************/
    public void selectFamily(String nameFamily)
    {
        Iterator<Family> it = families.iterator();
        while(it.hasNext())
        {
            Family family = it.next();
            if (family.getMetadata().getName().equals(nameFamily))
            {
                fc = new FamilyControl(family);
            }
        }
    }
    
    public ArrayList<String> getFamiliesName()
    {
        ArrayList<String> listNames = new ArrayList<String>();
        Iterator<Family> it = families.iterator();
        while(it.hasNext())
        {
            Family family = it.next();
            listNames.add(family.getMetadata().getName());
        }
        return listNames;
    }
    
    public String getLanguageFamilyPath()
    {
        return fc.getLanguagePath();
    }
    
    public ArrayList<String> getModelsLabel()
    {
        return fc.getModelsLabels();
    }
    
    public String getModelIconPath(String modelLabel)
    {
        return fc.getModelIconPath(modelLabel);
    }
    
    public String getModelFamilyPath(String modelLabel)
    {
        return fc.getModelPath(modelLabel);
    }
    
    public int getNumModels()
    {
        return fc.getNumModels();
    }
    
    public String getMetadataFamilyName()
    {
        return fc.getMetadataName();
    }
    
    public String getMetadataFamilyURL()
    {
        return fc.getMetadataURL();
    }
    
    public String getMetadataFamilyDescription()
    {
        return fc.getMetadataDescription();
    }
    
    public String getMetadataFamilyAuthor()
    {
        return fc.getMetadataAuthor();
    }
    
    public ArrayList<String> getStagesLabels()
    {
        return fc.getStagesLabels();
    }
    
    public StageType getStagesTypes(String idStage)
    {
        return fc.getStagesTypes(idStage);
    }
    
    public int getNumCameras()
    {
        return fc.getNumCameras();
    }
    
    public ArrayList<String> getCamerasLabels()
    {
        return fc.getCamerasLabels();
    }
    
    public ArrayList<String> getQualityLabels()
    {
        return fc.getQualityLabels();
    }
    
    public ArrayList<String> getIdsSubStages(String stageLabel)
    {
        return fc.getIdsSubStages(stageLabel);
    }
    
    public int getNumSubStages(String stageLabel)
    {
        return fc.getNumSubStage(stageLabel);
    }
    
    public String getSubStageLabel(String multiStageLabel,String idSubStage)
    {
        return fc.getSubStageLabel(multiStageLabel, idSubStage);
    }
    
    public ArrayList<String> getIdBonesController(String idStageLabel)
    {
        return fc.getIdBonesController(idStageLabel);
    }
    
    public String getBoneControllerLabel(String idStageLabel,String idBoneController)
    {
        return fc.getBoneControllerLabel(idStageLabel, idBoneController);
    }
    
    /*************************Models******************************************/
    
    public void selectModel(String pathModel)
    {
        XMLReader xmlReader2 = new XMLReader<Model>(pathModel);
        ArrayList<Model> models = xmlReader2.readXML(Model.class);
        if (models.size()==1){
            model = models.get(0);
        }
        mc = new ModelControl(model);
        TexturesSubMeshesData texturesSubMeshesData = new TexturesSubMeshesData(fc.getAllSubStages(), mc.getAllTextures(), mc.getAllSubMeshes());
        sc = new SceneControl(rootNode, assetManager, mc.getMainMeshPath(), mc.getMainMeshTransformations(), texturesSubMeshesData);
    }
    
    public String getLanguageModelPath()
    {
        return mc.getLanguagePath();
    }
    
    public ArrayList<String> getIdsTexturesORSubMeshes(String idPanelRef)
    {
        return mc.getIdsTexturesORSubMeshes(idPanelRef);
    }
    
    public int getNumTexturesORSubMeshes(String idPanelRef)
    {
        return mc.getNumTexturesORSubMeshes(idPanelRef);
    }
    
    public String getIconPathTexturesORSubMeshes(String idTexturesORSubMeshes)
    {
        return mc.getIconPathTexturesORSubMeshes(idTexturesORSubMeshes);
    }
    
    public ArrayList<String> getIdsPhysicalBuild(String idPanelRef)
    {
        return mc.getIdsPhysicalBuild(idPanelRef);
    }
    
    public String getPhysicalBuildLabel(String idPhysicalBuild)
    {
        return mc.getPhysicalBuildLabel(idPhysicalBuild);
    }
    
    public float getMaxValueBoneController(String idBoneController)
    {
        return mc.getMaxValueBoneController(idBoneController);
    }
    
    public float getMinValueBoneController(String idBoneController)
    {
        return mc.getMinValueBoneController(idBoneController);
    }
    
    public float getDefaultValueBoneController(String idBoneController)
    {
        return mc.getDefaultValueBoneController(idBoneController);
    }
    
    public void setDefaultValueBoneController(String idBoneController, float value)
    {
        mc.setDefaultValueBoneController(idBoneController, value);
    }
    
    public TexturesType getTextureType(String idTextureOrSubMesh)
    {
        return mc.getTextureType(idTextureOrSubMesh);
    }
    
    /*************************Scene******************************************/
    public Set<String> getAnimationNames()
    {
        return sc.getAnimationsName();
    }
    
    public int getNumAnimations()
    {
        return sc.getNumAnimations();
    }
    
    public void setAnimations(String animationName)
    {
        sc.setAnimation(animationName);
    }
    
    public void changeTextureOrSubMesh(String idPanelRef, String idTextureOrSubMesh)
    {
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
    
    public void changeColorBaseShadow(String idPanelRef, String idTexture, float red,float green,float blue){
        sc.changeColorBaseShadow(idPanelRef, idTexture, red, green, blue);
    }
    
    public void changeColorDoubleTexture(String idPanelRef, String idTexture, float red1,float green1,float blue1, 
            float red2,float green2,float blue2){
        if (red1 == -1 && green1 == -1 && blue1 == -1){
            //-1 representa que el usuario no ha cambiado la textura de base en este caso
            //Cambiar solo los detalles
            sc.changeColorDoubleTextureDetails(idPanelRef, idTexture, red2, green2, blue2);
        }
        else if(red2 == -1 && green2 == -1 && blue2 == -1){
            //-1 representa que el usuario no ha cambiado la textura de detalles en este caso
            //Cambiar solo la base
            sc.changeColorDoubleTextureBase(idPanelRef, idTexture, red1, green1, blue1);
        }
        else{
            //Cambiar base y detalles
            sc.changeColorDoubleTextureBase(idPanelRef, idTexture, red1, green1, blue1);
            sc.changeColorDoubleTextureDetails(idPanelRef, idTexture, red2, green2, blue2);
        }
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
    
    public void deleteModel(){
        sc.deleteModel();
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
    
    public void rotateModel(float inc){
        sc.rotateModel(inc);
    }
    
    public void restartRotateModel(){
        sc.restartRotateModel();
    }
}
