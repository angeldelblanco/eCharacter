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
package es.eucm.character.control;

import com.jme3.animation.AnimChannel;
import com.jme3.animation.AnimControl;
import com.jme3.animation.Bone;
import com.jme3.animation.LoopMode;
import com.jme3.animation.SkeletonControl;
import com.jme3.export.binary.BinaryImporter;
import com.jme3.light.DirectionalLight;
import com.jme3.material.Material;
import com.jme3.math.FastMath;
import com.jme3.math.Quaternion;
import com.jme3.math.Vector3f;
import com.jme3.scene.Spatial;
import com.jme3.texture.Image;
import com.jme3.texture.Texture2D;
import com.jme3.texture.plugins.AWTLoader;
import es.eucm.character.data.model.EscalationType;
import es.eucm.character.data.model.SubMeshType;
import es.eucm.character.data.model.TransformationType;
import es.eucm.character.data.texturessubmeshesdata.TexturesSubMeshesData;
import es.eucm.character.export.ScreenshotThread;
import es.eucm.character.imageprocessing.ImagesProcessingMainMesh;
import es.eucm.character.loader.Configuration;
import es.eucm.character.loader.ResourceHandler;
import es.eucm.character.export.CameraValues;
import es.eucm.character.types.ElementType;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;

public class SceneControl {
    private Control control;
    private Configuration config;
    private Spatial mainMesh;
    private HashMap<String,Spatial> subMeshes;
    private HashMap<String,Boolean> animations;
    private HashMap<String,Boolean> cameras;
    private HashMap<String,Boolean> qualities;
    private Material mat;
    private AnimChannel animChannel;
    private AnimControl animControl;
    private Vector3f vectorScaleBase;
    private TexturesSubMeshesData texturesSubMeshesData;
    private float angRotate;
    private DirectionalLight directionalLight;
    
    public SceneControl(Control control,Configuration config,String mainMeshPath, ArrayList<TransformationType> listTransformationMainMesh,
                TexturesSubMeshesData texturesSubMeshesData){
        this.control = control;
        this.config = config;
        this.texturesSubMeshesData = texturesSubMeshesData;        
        this.subMeshes = new HashMap<String, Spatial>();
        this.animations = new HashMap<String,Boolean>();
        this.cameras = new HashMap<String,Boolean>();
        this.qualities = new HashMap<String,Boolean>();
        this.vectorScaleBase = new Vector3f(1.0f,1.0f,1.0f);
        this.angRotate = 0.0f;
        
        this.directionalLight = new DirectionalLight();
        this.directionalLight.setDirection(new Vector3f(-0.1f, -1f, -1).normalizeLocal());
        this.control.getRootNode().addLight(this.directionalLight);
        this.mainMesh = this.control.getAssetManager().loadModel(mainMeshPath); 
        
        /*try {
            this.mainMesh = (Spatial)BinaryImporter.getInstance().load(ResourceHandler.getResource(mainMeshPath)); 
            
        } catch (IOException ex) {
            Logger.getLogger(SceneControl.class.getName()).log(Level.SEVERE, null, ex);
        }*/
        
        loadTexture();
        this.control.getRootNode().attachChild(mainMesh);
        setPositionModel(listTransformationMainMesh);
        loadSubMeshes();
       
        this.animControl = this.mainMesh.getControl(AnimControl.class);
        this.animChannel = this.animControl.createChannel();
        Set<String> animList = (Set<String>) this.animControl.getAnimationNames();
        if(animList.size() > 0){
            this.animChannel.setAnim(animList.iterator().next());
            this.animChannel.setLoopMode(LoopMode.Loop); 
        }   
        fillAnimations(animList);
        fillCameras(control.getCamerasLabels());
        fillQualities(control.getQualityLabels());
    }
    
    private void setPositionModel(ArrayList<TransformationType> listTransformations){
        Iterator<TransformationType> it = listTransformations.iterator();
        while (it.hasNext()){
            TransformationType t = it.next();
            if(t.getTransformationType().equals("scale")){
                vectorScaleBase = vectorScaleBase.multLocal(t.getValueX(),t.getValueY(),t.getValueZ());                
            }
            applyTransformation(t,mainMesh);
        }
    }
    
    private void applyTransformation(TransformationType t,Spatial s){
        String transformation = t.getTransformationType();
        if(transformation.equals("scale")){
            s.scale(t.getValueX(),t.getValueY(),t.getValueZ());
        }
        else if(transformation.equals("rotate")){
            s.rotate(FastMath.DEG_TO_RAD*t.getValueX(),FastMath.DEG_TO_RAD*t.getValueY(),FastMath.DEG_TO_RAD*t.getValueZ());
        }
        else if(transformation.equals("translate")){
            s.move(t.getValueX(),t.getValueY(),t.getValueZ());
        }
        else if(transformation.equals("center")){
            s.center();
        }  
    }    
    
    private void applySubMesh(String bone,Spatial subMesh,ArrayList<TransformationType> listTransformations){
        SkeletonControl skeletonControl = this.mainMesh.getControl(SkeletonControl.class);
        if(subMeshes.get(bone) != null){
            skeletonControl.getAttachmentsNode(bone).detachAllChildren();
        }
        subMeshes.put(bone,subMesh);            
        skeletonControl.getAttachmentsNode(bone).attachChild(subMesh);           
        Iterator<TransformationType> it = listTransformations.iterator();
        while (it.hasNext()){
            TransformationType t = it.next();
            applyTransformation(t,subMesh);
        }
    }
     
    private void dettachAllChild(){
        SkeletonControl skeletonControl = this.mainMesh.getControl(SkeletonControl.class);
        Set<String> bones = subMeshes.keySet();
        Iterator<String> it = bones.iterator();
        while(it.hasNext()){
            String bone = it.next();
            Spatial subMesh = subMeshes.get(bone); 
            skeletonControl.getAttachmentsNode(bone).detachChild(subMesh);
        }
    }
    
    private void attachAllChild(){
        SkeletonControl skeletonControl = this.mainMesh.getControl(SkeletonControl.class);
        Set<String> bones = subMeshes.keySet();
        Iterator<String> it = bones.iterator();
        while(it.hasNext()){
            String bone = it.next();
            Spatial subMesh = subMeshes.get(bone); 
            skeletonControl.getAttachmentsNode(bone).attachChild(subMesh);
        } 
    }
    
    public void applyEscalations(ArrayList<EscalationType> listEscalations){
        Iterator<EscalationType> it = listEscalations.iterator();
        while(it.hasNext()){
            EscalationType escalation = it.next();
            applyEscalation(mainMesh,escalation);
        }
    }
    
    private void applyEscalation(Spatial mesh,EscalationType escalation){
        Vector3f scale = new Vector3f(escalation.getValueX(),escalation.getValueY(),escalation.getValueZ());
        String boneName = escalation.getBoneName();
        if(boneName.equals("ALL")){
            scale.multLocal(vectorScaleBase);
            mesh.setLocalScale(scale);
        }
        else{
            Bone b = animControl.getSkeleton().getBone(boneName);
            b.setUserControl(true);
            b.setUserTransforms(Vector3f.ZERO, Quaternion.IDENTITY,scale);
        }
    }
    
    public void scaleBone(ArrayList<String> bonesNames,float inc){
        Iterator<String> it = bonesNames.iterator();
        while(it.hasNext()){
            String idBone = it.next();
            Bone b = animControl.getSkeleton().getBone(idBone);
            b.setUserControl(true);
            b.setUserTransforms(Vector3f.ZERO,Quaternion.IDENTITY,new Vector3f(inc,inc,inc));
        }
    }
    
    public ArrayList<String> getAnimationsName(){
        ArrayList<String> listAnimationsName = new ArrayList<String>();
        listAnimationsName.addAll(animControl.getAnimationNames());
        return listAnimationsName;
    }
    
    public int getNumAnimations(){
        return animControl.getAnimationNames().size();
    }
    
    public void setAnimation(String animationName){
        Set<String> animList = (Set<String>) this.animControl.getAnimationNames();
        Iterator<String> it = animList.iterator();
        while(it.hasNext()){
            String anim = it.next();
            if(anim.equals(animationName)){
                this.animChannel.setAnim(anim);
                this.animChannel.setLoopMode(LoopMode.Loop);
            }
        }
    }
    
    public void changeColorBaseShadow(String idPanelRef, String idTextureOrSubmesh, ElementType type, float red,float green,float blue){
        texturesSubMeshesData.changeColorBaseShadow(idPanelRef, idTextureOrSubmesh, type, red, green, blue);
        dettachAllChild();
        loadTexture();
        loadSubMeshes();
        attachAllChild();  
    }
    
    public void changeColorDoubleTextureDetails(String idPanel, String idTextureOrSubmesh, ElementType type, float red, float green, float blue) {
        texturesSubMeshesData.changeColorDoubleTextureDetails(idPanel, idTextureOrSubmesh, type, red, green, blue);
        dettachAllChild();
        loadTexture();
        loadSubMeshes();
        attachAllChild();  
    }
    
    public void changeColorDoubleTextureBase(String idPanel, String idTextureOrSubmesh, ElementType type, float red, float green, float blue) {
        texturesSubMeshesData.changeColorDoubleTextureBase(idPanel, idTextureOrSubmesh, type, red, green, blue);
        dettachAllChild();
        loadTexture();
        loadSubMeshes();
        attachAllChild(); 
    }
    
    public void changeColorMultiOptionTexture(String idPanel, String idTextureOrSubmesh, ElementType type, String idSubTexture){
        texturesSubMeshesData.changeColorMultiOptionTexture(idPanel, idTextureOrSubmesh, type, idSubTexture);
        dettachAllChild();
        loadTexture();
        loadSubMeshes();
        attachAllChild();  
    }
    
    private void loadTexture(){
        HashMap<Integer,ArrayList<BufferedImage>> listTextures = texturesSubMeshesData.getCheckedTextures();
        BufferedImage bi = ImagesProcessingMainMesh.process(listTextures);
        
        AWTLoader loader = new AWTLoader();
        Image load = loader.load(bi, true);
        Texture2D texture = new Texture2D(load);
        
        mat = new Material(control.getAssetManager(), "Common/MatDefs/Misc/Unshaded.j3md"); 
        mat.setTexture("ColorMap",texture);
        mainMesh.setMaterial(mat);
    }
    
    private void loadSubMeshes(){
        ArrayList<SubMeshType> listSubMeshes = texturesSubMeshesData.getCheckedSubMeshes();
        Iterator<SubMeshType> it = listSubMeshes.iterator();
        while(it.hasNext()){
            SubMeshType subMesh = it.next();
            Spatial subMeshSpatial = control.getAssetManager().loadModel(subMesh.getPath());
            if (subMesh.getSubMeshTexture()!= null){
                //Obtener la textura de esa submalla
                BufferedImage bi = texturesSubMeshesData.getSubMeshTexture(subMesh.getIdSubMesh());
                AWTLoader loader = new AWTLoader();
                Image load = loader.load(bi, true);
                Texture2D texture = new Texture2D(load);
                
                Material subMeshMaterial = new Material(control.getAssetManager(), "Common/MatDefs/Light/Lighting.j3md");
                subMeshMaterial.setTexture("DiffuseMap", texture);
                subMeshSpatial.setMaterial(subMeshMaterial);
            }
            else{
                //No hacer 
                System.out.println("ENTROOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOO");
            }
            ArrayList<TransformationType> listTransformation = (ArrayList<TransformationType>) subMesh.getTransformation();
            applySubMesh(subMesh.getAssociatedBone(),subMeshSpatial,listTransformation);
        }
    }
    
    public void changeSubMesh(String idPanel, String idSubMesh){
        texturesSubMeshesData.changeSubMesh(idPanel,idSubMesh);
        loadSubMeshes();
    }
        
    public void changeTexture(String idPanel, String idTexture){
        texturesSubMeshesData.changeTexture(idPanel, idTexture);
        dettachAllChild();
        loadTexture();
        attachAllChild();
    }
    
    public void deleteModel(){
        this.control.getRootNode().removeLight(directionalLight);
        this.control.getRootNode().detachAllChildren();
    }
    
    public boolean isCheckedTexture(String idPanel, String idTexture){
        return texturesSubMeshesData.isCheckedTexture(idPanel, idTexture);
    }
    
    public boolean isCheckedSubMesh(String idPanel, String idSubMesh){
        return texturesSubMeshesData.isCheckedSubMesh(idPanel, idSubMesh);
    }
    
    public void rotateModel(float inc){
        angRotate += inc;
        mainMesh.rotate(0,0,FastMath.DEG_TO_RAD*inc);
    }
    
    public void restartRotateModel(){
        float ang = 360 - (angRotate % 360.0f);
        mainMesh.rotate(0,0,FastMath.DEG_TO_RAD*ang);
        angRotate = 0.0f;
    }
    
    public void screenShot(){
        //Animations list that is checked
        ArrayList<String> listAnimationsChecked = new ArrayList<String>();
        Iterator<String> it = animations.keySet().iterator();
        while(it.hasNext()){
            String animationName = it.next();
            if(animations.get(animationName)){
                listAnimationsChecked.add(animationName);
            }
        }
        
        //List of qualities value that is checked
        ArrayList<Integer> listQualitiesChecked = new ArrayList<Integer>();
        it = qualities.keySet().iterator();
        while(it.hasNext()){
            String qualityLabel = it.next();
            if(qualities.get(qualityLabel)){
                listQualitiesChecked.add(control.getQualityValue(qualityLabel));
            }
        }
        if(listQualitiesChecked.isEmpty()){
            int defaultQuality = Integer.parseInt(config.getProperty(Configuration.DEFAULT_QUALITY));
            listQualitiesChecked.add(defaultQuality);
        }
        //List of cameras value that is checked
        ArrayList<CameraValues> listCamerasChecked = new ArrayList<CameraValues>();
        it = cameras.keySet().iterator();
        while(it.hasNext()){
            String cameraLabel = it.next();
            if(cameras.get(cameraLabel)){
                listCamerasChecked.add(control.getCameraValues(cameraLabel));
            }
        }
        if(listAnimationsChecked.size() > 0){
            control.getGuiViewPort().removeProcessor(control.getNiftyDisplay());
            ScreenshotThread sst = new ScreenshotThread(control.getScreenShotState(),animChannel,
                    control.getGuiViewPort(),control.getNiftyDisplay(),listAnimationsChecked, 
                    listQualitiesChecked, listCamerasChecked);
            sst.start();
        }
    }
    
    private void fillAnimations(Set<String> listAnimations){
        Iterator<String> it = listAnimations.iterator();
        while(it.hasNext()){
            String animation = it.next();
            animations.put(animation, false);
        }
    }
    
    private void fillCameras(ArrayList<String> listCameras){
        Iterator<String> it = listCameras.iterator();
        while(it.hasNext()){
            String camera = it.next();
            cameras.put(camera, false);
        } 
    }
    
    private void fillQualities(ArrayList<String> listQualities){
        Iterator<String> it = listQualities.iterator();
        while(it.hasNext()){
            String quality = it.next();
            qualities.put(quality, false);
        } 
    }

    public void clickAnimation(String animationName, boolean check) {
        animations.put(animationName, check);
    }
    
    public boolean isCheckedAnimation(String animationName) {
        return animations.get(animationName);
    }
    
    public void clickAllAnimations(boolean check){
        Set<String> keySet = animations.keySet();
        Iterator<String> it = keySet.iterator();
        while(it.hasNext()){
            animations.put(it.next(),check);
        }
    }
    
    public void clickCamera(String cameraLabel, boolean check) {
        cameras.put(cameraLabel, check);
    }
    
    public boolean isCheckedCamera(String cameraLabel) {
        return cameras.get(cameraLabel);
    }
    
    public void clickAllCameras(boolean check){
        Set<String> keySet = cameras.keySet();
        Iterator<String> it = keySet.iterator();
        while(it.hasNext()){
            cameras.put(it.next(),check);
        }
    }
     
    public void clickQuality(String qualityLabel, boolean check) {
        qualities.put(qualityLabel, check);
    }
    
    public boolean isCheckedQuality(String qualityLabel) {
        return qualities.get(qualityLabel);
    }
    
    public void clickAllQualities(boolean check){
        Set<String> keySet = qualities.keySet();
        Iterator<String> it = keySet.iterator();
        while(it.hasNext()){
            qualities.put(it.next(),check);
        }
    }
}