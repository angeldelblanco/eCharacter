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
 *          For more info please visit:  <http://echaracter.e-ucm.es>, 
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

import com.jme3.animation.AnimChannel;
import com.jme3.animation.AnimControl;
import com.jme3.animation.Bone;
import com.jme3.animation.LoopMode;
import com.jme3.animation.SkeletonControl;
import com.jme3.light.DirectionalLight;
import com.jme3.material.Material;
import com.jme3.math.ColorRGBA;
import com.jme3.math.FastMath;
import com.jme3.math.Quaternion;
import com.jme3.math.Vector3f;
import com.jme3.scene.CameraNode;
import com.jme3.scene.Geometry;
import com.jme3.scene.Node;
import com.jme3.scene.Spatial;
import com.jme3.texture.Image;
import com.jme3.texture.Texture2D;
import com.jme3.texture.plugins.AWTLoader;
import es.eucm.echaracter.data.model.EscalationType;
import es.eucm.echaracter.data.model.SubMeshType;
import es.eucm.echaracter.data.model.TransformationType;
import es.eucm.echaracter.data.texturessubmeshesdata.TexturesSubMeshesData;
import es.eucm.echaracter.export.CameraValues;
import es.eucm.echaracter.export.ScreenshotThread;
import es.eucm.echaracter.imageprocessing.ImagesProcessingMainMesh;
import es.eucm.echaracter.loader.Configuration;
import es.eucm.echaracter.types.ElementType;
import java.awt.Color;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.StringTokenizer;

public class SceneControl {
    private CameraNode cameraNode;  
    private CameraValues defaultCamera;
    private float acumAngRotationModel = 0.0f;
    private Control control;
    private Configuration config;
    private Node mainMeshNode;
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
    private ArrayList<DirectionalLight> lights;
    
    public SceneControl(Control control, Configuration config,String mainMeshPath, ArrayList<TransformationType> listTransformationMainMesh,
                TexturesSubMeshesData texturesSubMeshesData){    
        //Initialize structures
        this.control = control;
        this.config = config;
        this.texturesSubMeshesData = texturesSubMeshesData;        
        this.subMeshes = new HashMap<String, Spatial>();
        this.animations = new HashMap<String,Boolean>();
        this.cameras = new HashMap<String,Boolean>();
        this.qualities = new HashMap<String,Boolean>();
        this.lights = new ArrayList<DirectionalLight>();
        this.vectorScaleBase = new Vector3f(1.0f,1.0f,1.0f);
        
        loadBackground();
        
        //Add lights
        addLights();
        
        //Setting the camera´s inital configuration
        cameraNode = new CameraNode("camera",control.getCamera());
        defaultCamera = this.parseDefaultCamera();
        defaultCameraView();
        this.control.getRootNode().attachChild(cameraNode);
        
        //Load and locate the main model
        this.mainMesh = this.control.getAssetManager().loadModel(mainMeshPath); 
        loadTexture();
        mainMeshNode = new Node("mainMeshNode");
        mainMeshNode.attachChild(mainMesh);
        setPositionModel(listTransformationMainMesh);
        this.control.getRootNode().attachChild(mainMeshNode);
        loadSubMeshes();              

        //Setting the initial animation
        this.animControl = this.mainMesh.getControl(AnimControl.class);
        if (animControl != null){
            this.animChannel = this.animControl.createChannel();
            Set<String> animList = (Set<String>) this.animControl.getAnimationNames();
            if(animList.size() > 0){
                this.animChannel.setAnim(animList.iterator().next());
                this.animChannel.setLoopMode(LoopMode.Loop); 
            }
            fillAnimations(animList);
        }

        //Fill the structures
        fillCameras(control.getCamerasLabels());
        fillQualities(control.getQualityLabels());  
    }
    
    public final void loadBackground(){
        control.loadBackground();
    }
    
    private void addLights(){    /** A white, directional light source */ 

        DirectionalLight light1 = new DirectionalLight();
        light1.setDirection(new Vector3f(-0.1f, 0.0f, -1.0f).normalizeLocal());        
        DirectionalLight light2 = new DirectionalLight();
        light2.setDirection(new Vector3f(-0.1f,0.0f,1.0f).normalizeLocal());
        DirectionalLight light3 = new DirectionalLight();
        light3.setDirection(new Vector3f(1.0f,0.0f,0.0f).normalizeLocal());
        DirectionalLight light4 = new DirectionalLight();
        light4.setDirection(new Vector3f(-1.0f,0.0f,0.0f).normalizeLocal());
        control.getRootNode().addLight(light1);
        control.getRootNode().addLight(light2);
        control.getRootNode().addLight(light3);
        control.getRootNode().addLight(light4);
        lights.add(light1);
        lights.add(light2);
        lights.add(light3);
        lights.add(light4);
    }
    
    private void setPositionModel(ArrayList<TransformationType> listTransformations){
        Iterator<TransformationType> it = listTransformations.iterator();
        while (it.hasNext()){
            TransformationType t = it.next();
            if(t.getTransformationType().equals("rotate")){
                mainMeshNode.detachChild(mainMesh);
                applyTransformation(t, mainMesh);
                mainMeshNode.attachChild(mainMesh);
            }
            else{
                if(t.getTransformationType().equals("scale")){
                    vectorScaleBase = vectorScaleBase.multLocal(t.getValueX(),t.getValueY(),t.getValueZ());                
                }
                applyTransformation(t,mainMeshNode);
            }
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
        printChildrenStructure(mainMeshNode,0);
        Iterator<String> it = bonesNames.iterator();
        while(it.hasNext()){
            String idBone = it.next();
            Bone b = animControl.getSkeleton().getBone(idBone);
            b.setUserControl(true);
            //b.setUserTransforms(Vector3f.ZERO,Quaternion.IDENTITY,new Vector3f(inc,inc,inc));
            b.setUserTransforms(Vector3f.ZERO,Quaternion.IDENTITY,new Vector3f(inc,1,1));
                
        }
    }
    
    private void printChildrenStructure(Node node, int level){
        System.out.println(indent(level)+node.getName());
        for (Spatial child:node.getChildren()){
            if (child instanceof Geometry){
                System.out.println(indent(level+1)+((Geometry)child).getName());
            } else if (child instanceof Node){
                printChildrenStructure ((Node)child, level+1);
            }
        }
    }
    
    private String indent(int level){
        String tabs = "";
        for (int i=0; i<level; i++)
            tabs+="\t";
        return tabs;
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
    
    public ArrayList<Color> getColorTexture(String idPanel, String idTextureOrSubMesh, ElementType type){
        return texturesSubMeshesData.getColorTexture(idPanel, idTextureOrSubMesh, type);
    }
    
    private void loadTexture(){
        HashMap<Integer,ArrayList<BufferedImage>> listTextures = texturesSubMeshesData.getCheckedTextures();
        BufferedImage bi = ImagesProcessingMainMesh.process(listTextures);
        
        AWTLoader loader = new AWTLoader();
        Image load = loader.load(bi, true);
        Texture2D texture = new Texture2D(load);
        
        /*mat = new Material(control.getAssetManager(), "Common/MatDefs/Misc/Unshaded.j3md"); 
        mat.setTexture("ColorMap",texture);*/
        mat = new Material(control.getAssetManager(), "Common/MatDefs/Light/Lighting.j3md"); 
        mat.setTexture("DiffuseMap",texture);
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
    
    public void removeModel(){
        Iterator<DirectionalLight> it = lights.iterator();
        while(it.hasNext()){
            this.control.getRootNode().removeLight(it.next());
        }
        this.control.getRootNode().detachAllChildren();
    }
    
    public boolean isCheckedTexture(String idPanel, String idTexture){
        return texturesSubMeshesData.isCheckedTexture(idPanel, idTexture);
    }
    
    public boolean isCheckedSubMesh(String idPanel, String idSubMesh){
        return texturesSubMeshesData.isCheckedSubMesh(idPanel, idSubMesh);
    }
    
    public void screenShot(){
        //Remove background and restart rotation
        control.removeBackground();
        restartRotationModel();
        control.getViewPort().setBackgroundColor(new ColorRGBA(0f, 0f, 0f, 0.4f));        
        
        //Check if the application has been called by API
        boolean callbackAPI = false;
        if((control.getCallback() != null) && (config.getProperty(Configuration.INPUT_DEFAULT_ANIMATIONS) != null)
                && (config.getProperty(Configuration.INPUT_DEFAULT_CAMERA) != null) 
                && (config.getProperty(Configuration.INPUT_DEFAULT_QUALITY) != null)){
            callbackAPI = true;
        }
        
        
        //Animations list that is checked
        ArrayList<String> listAnimationsChecked = new ArrayList<String>();
        if (callbackAPI){
            String animations = config.getProperty(Configuration.INPUT_DEFAULT_ANIMATIONS);
            StringTokenizer animationsTokenizer = new StringTokenizer(animations, "|");
            while(animationsTokenizer.hasMoreTokens()){
                String animationName = animationsTokenizer.nextToken();
                if (existsAnimation(animationName)){
                    listAnimationsChecked.add(animationName);
                }
            }
            // If the special animation ##all## is set, then all available animations will be used
            if (animations.toLowerCase().contains("##all##")){
                for (String animationName:this.animations.keySet()){
                    listAnimationsChecked.add(animationName);
                }
            }
        }else{
            Iterator<String> it = animations.keySet().iterator();
            while(it.hasNext()){
                String animationName = it.next();
                if(animations.get(animationName)){
                    listAnimationsChecked.add(animationName);
                }
            }
        }
        
        //List of qualities value that is checked
        ArrayList<Integer> listQualitiesChecked = new ArrayList<Integer>();
        Iterator<String> it = qualities.keySet().iterator();
        while(it.hasNext()){
            String qualityLabel = it.next();
            if(qualities.get(qualityLabel)){
                listQualitiesChecked.add(control.getQualityValue(qualityLabel));
            }
        }
        if(listQualitiesChecked.isEmpty()){
            int defaultQuality;
            if(callbackAPI){
                defaultQuality = Integer.parseInt(config.getProperty(Configuration.INPUT_DEFAULT_QUALITY));
            }
            else{
                defaultQuality = Integer.parseInt(config.getProperty(Configuration.DEFAULT_QUALITY));
            }
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
        if(listCamerasChecked.isEmpty()){
            if (callbackAPI){
                StringTokenizer camerasTokenizer = new StringTokenizer(config.getProperty(Configuration.INPUT_DEFAULT_CAMERA), "|");
                while(camerasTokenizer.hasMoreTokens()){
                    String cameraName = camerasTokenizer.nextToken();
                    listCamerasChecked.add(control.getCameraValues(cameraName));
                }
                
                // If the special animation ##all## is set, then all available animations will be used
                if (config.getProperty(Configuration.INPUT_DEFAULT_CAMERA).toLowerCase().contains("##all##")){
                    for (String cameraName:this.cameras.keySet()){
                        listCamerasChecked.add(control.getCameraValues(cameraName));
                    }
                }                
            }
            else{
                listCamerasChecked.add(defaultCamera);
            }
        }
        
        if(listAnimationsChecked.size() > 0){
            control.getNiftyDisplay().getNifty().gotoScreen("emptyScreen");
            ScreenshotThread sst = new ScreenshotThread(this,config,control.getCallback(), control.getScreenShotState(),animChannel,
                    control.getNiftyDisplay(),listAnimationsChecked, 
                    listQualitiesChecked, listCamerasChecked);
            sst.start();
        }
    }
    
    private boolean existsAnimation(String animation){
        return animations.keySet().contains(animation);
    }
    
    //This method is called if the callback exists and the export process has success.
    public void quitGame(){
        control.quitGame();
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
    
    //-----------------------CAMERA CONTROLLER--------------------------------//
    //luego hay que hacerlo privado y en Control no tiene que estar
    public void setCameraView(Vector3f position,Vector3f direction,Vector3f up){
        cameraNode.setLocalTranslation(position);
        cameraNode.lookAt(direction,up);
    }
    
    public void rotateModel(float ang){
        acumAngRotationModel += ang;    
        this.control.getRootNode().getChild("mainMeshNode").rotate(0,FastMath.DEG_TO_RAD*ang,0);
    }
    
    public void restartRotationModel(){
        float ang = 360 - (acumAngRotationModel % 360);   
        this.control.getRootNode().getChild("mainMeshNode").rotate(0,FastMath.DEG_TO_RAD*ang,0);
        acumAngRotationModel = 0.0f;
    }
    
    public final void defaultCameraView(){
        setCameraView(defaultCamera.getPosition(),defaultCamera.getDirection(),defaultCamera.getUp());      
    }
    
    private CameraValues parseDefaultCamera(){        
        Vector3f position = parseVector(config.getProperty(Configuration.DEFAULT_VECTOR_POSITION));
        Vector3f direction = parseVector(config.getProperty(Configuration.DEFAULT_VECTOR_DIRECTION));
        Vector3f up = parseVector(config.getProperty(Configuration.DEFAULT_VECTOR_UP));
        String name = config.getProperty(Configuration.DEFAULT_CAMERA_NAME);
        return new CameraValues(name, position, direction, up);
    }
    
    private Vector3f parseVector(String vector){
        StringTokenizer st = new StringTokenizer(vector, ",");
        if(st.countTokens() == 3){
            float x = Float.parseFloat(st.nextToken());
            float y = Float.parseFloat(st.nextToken());
            float z = Float.parseFloat(st.nextToken());
            return new Vector3f(x,y,z);
        }
        return null;
    }
}