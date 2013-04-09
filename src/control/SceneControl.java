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

import com.jme3.animation.AnimChannel;
import com.jme3.animation.AnimControl;
import com.jme3.animation.Bone;
import com.jme3.animation.LoopMode;
import com.jme3.animation.SkeletonControl;
import com.jme3.asset.AssetManager;
import com.jme3.light.DirectionalLight;
import com.jme3.material.Material;
import com.jme3.math.FastMath;
import com.jme3.math.Quaternion;
import com.jme3.math.Vector3f;
import com.jme3.scene.Node;
import com.jme3.scene.Spatial;
import com.jme3.texture.Image;
import com.jme3.texture.Texture2D;
import com.jme3.texture.plugins.AWTLoader;
import data.model.EscalationType;
import data.model.SubMeshType;
import data.model.TransformationType;
import data.texturessubmeshesdata.TexturesSubMeshesData;
import imageprocessing.ImagesProcessing;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Set;

public class SceneControl 
{
    private Node rootNode;
    private AssetManager assetManager;
    private Spatial mainMesh;
    private HashMap<String,Spatial> subMeshes;
    private Material mat;
    private AnimChannel channel;
    private AnimControl control;
    private Vector3f vectorScaleBase;
    private TexturesSubMeshesData texturesSubMeshesData;
    private int cont;
    
    public SceneControl(Node rootNode, AssetManager assetManager, String mainMeshPath, 
            ArrayList<TransformationType> listTransformationMainMesh, TexturesSubMeshesData texturesSubMeshesData)
    {
        this.rootNode = rootNode;
        this.assetManager = assetManager;
        this.texturesSubMeshesData = texturesSubMeshesData;
        this.subMeshes = new HashMap<String, Spatial>();
        this.vectorScaleBase = new Vector3f(1.0f,1.0f,1.0f);
        
        DirectionalLight dl = new DirectionalLight();
        dl.setDirection(new Vector3f(-0.1f, -1f, -1).normalizeLocal());
        this.rootNode.addLight(dl);
        mainMesh = this.assetManager.loadModel(mainMeshPath); 
        
        cont=1;
        
        mat = new Material(assetManager, "Common/MatDefs/Misc/Unshaded.j3md"); 
        //Aqui deberia ir cargar Texturas por Defecto
        String tempPath = "assets/Textures/FinalTexture"+cont+".png";
        //ImagesProcessing imagesProcessing = new ImagesProcessing(getImageProcessingHashMap(texturesSubMeshesData.getCheckedTextures()));
        
        HashMap<Integer,ArrayList<BufferedImage>> listTextures = texturesSubMeshesData.getCheckedTextures();
        ImagesProcessing imagesProcessing = new ImagesProcessing(listTextures);
        
        
        
        BufferedImage bi = imagesProcessing.process(tempPath);
        
        AWTLoader loader = new AWTLoader();
        Image load = loader.load(bi, true);
        Texture2D texture = new Texture2D(load);
                
        
        
        //mat.setTexture("ColorMap",assetManager.loadTexture(tempPath));
        mat.setTexture("ColorMap",texture);
        
        
        mainMesh.setMaterial(mat);
        this.rootNode.attachChild(mainMesh);
        setPositionModel(listTransformationMainMesh);
        setActivatedSubMeshes();
       
        this.control = mainMesh.getControl(AnimControl.class);
        this.channel = this.control.createChannel();
        Set<String> animList = (Set<String>) this.control.getAnimationNames();
        if(animList.size() > 0){
            this.channel.setAnim(animList.iterator().next());
            this.channel.setLoopMode(LoopMode.Loop); 
        }
       
        
        //Borrar la imagen
        Path file = Paths.get(tempPath);
        try {
            Files.delete(file);
        } 
        catch (IOException ex) {
            System.out.println("Error al borrar el fichero");
        }      
    }
    
    private void setActivatedSubMeshes()
    {
        ArrayList<SubMeshType> listSubMeshes = texturesSubMeshesData.getCheckedSubMeshes();
        Iterator<SubMeshType> it = listSubMeshes.iterator();
        while(it.hasNext())
        {
            SubMeshType subMesh = it.next();
            Spatial subMeshSpatial = assetManager.loadModel(subMesh.getPath());
            //ÑAPAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA
            Material subMeshMaterial = new Material(assetManager, "Common/MatDefs/Light/Lighting.j3md");
            subMeshMaterial.setTexture("DiffuseMap",assetManager.loadTexture("assets/Textures/eAdventure/Textures Boy/PeloBoy.png"));
            subMeshSpatial.setMaterial(subMeshMaterial);
            //FIN DE ÑAPAAAAAAAAA
            //If texture are distinct null,the submesh has a texture´s list.
            //Else have a .material and it load automatically 
            //if(subMesh.getTextures().getBaseShadowTextureOrSimpleTextureOrDoubleTexture() != null){
                //PORHACER
            //}
            ArrayList<TransformationType> listTransformation = (ArrayList<TransformationType>) subMesh.getTransformation();
            applySubMesh(subMesh.getAssociatedBone(),subMeshSpatial,listTransformation);
        }
    }
    
    private void setPositionModel(ArrayList<TransformationType> listTransformations)
    {
        Iterator<TransformationType> it = listTransformations.iterator();
        while (it.hasNext())
        {
            TransformationType t = it.next();
            if(t.getTransformationType().equals("scale")){
                vectorScaleBase = vectorScaleBase.multLocal(t.getValueX(),t.getValueY(),t.getValueZ());                
            }
            applyTransformation(t,mainMesh);
        }
    }
    
    private void applyTransformation(TransformationType t,Spatial s)
    {
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
    
     private void applySubMesh(String bone,Spatial subMesh,ArrayList<TransformationType> listTransformations)
    {
            SkeletonControl skeletonControl = this.mainMesh.getControl(SkeletonControl.class);
            if(subMeshes.get(bone) != null){
                skeletonControl.getAttachmentsNode(bone).detachAllChildren();
            }
            subMeshes.put(bone,subMesh);            
            skeletonControl.getAttachmentsNode(bone).attachChild(subMesh);           
            Iterator<TransformationType> it = listTransformations.iterator();
            while (it.hasNext())
            {
                TransformationType t = it.next();
                applyTransformation(t,subMesh);
            }
    }
     
    private void dettachAllChild()
    {
        SkeletonControl skeletonControl = this.mainMesh.getControl(SkeletonControl.class);
        Set<String> bones = subMeshes.keySet();
        Iterator<String> it = bones.iterator();
        while(it.hasNext())
        {
            String bone = it.next();
            Spatial subMesh = subMeshes.get(bone); 
            skeletonControl.getAttachmentsNode(bone).detachChild(subMesh);
        }
    }
    
    private void attachAllChild()
    {
        SkeletonControl skeletonControl = this.mainMesh.getControl(SkeletonControl.class);
        Set<String> bones = subMeshes.keySet();
        Iterator<String> it = bones.iterator();
        while(it.hasNext())
        {
            String bone = it.next();
            Spatial subMesh = subMeshes.get(bone); 
            skeletonControl.getAttachmentsNode(bone).attachChild(subMesh);
        } 
    }
    
    public void applyEscalations(ArrayList<EscalationType> listEscalations)
    {
        Iterator<EscalationType> it = listEscalations.iterator();
        while(it.hasNext())
        {
            EscalationType escalation = it.next();
            applyEscalation(mainMesh,escalation);
        }
    }
    
    private void applyEscalation(Spatial mesh,EscalationType escalation)
    {
        Vector3f scale = new Vector3f(escalation.getValueX(),escalation.getValueY(),escalation.getValueZ());
        String boneName = escalation.getBoneName();
        if(boneName.equals("ALL")){
            scale.multLocal(vectorScaleBase);
            mesh.setLocalScale(scale);
        }
        else{
            Bone b = control.getSkeleton().getBone(boneName);
            b.setUserControl(true);
            b.setUserTransforms(Vector3f.ZERO, Quaternion.IDENTITY,scale);
        }
    }
    
    public void scaleBone(ArrayList<String> bonesNames,float inc)
    {
        
        Iterator<String> it = bonesNames.iterator();
        while(it.hasNext())
        {
            String idBone = it.next();
            Bone b = control.getSkeleton().getBone(idBone);
            b.setUserControl(true);
            b.setUserTransforms(Vector3f.ZERO,Quaternion.IDENTITY,new Vector3f(inc,inc,inc));
        }
        
    }
    
    public Set<String> getAnimationsName()
    {
        return (Set<String>) control.getAnimationNames();
    }
    
    public int getNumAnimations()
    {
        return control.getAnimationNames().size();
    }
    
    public void setAnimation(String animationName)
    {
        Set<String> animList = (Set<String>) this.control.getAnimationNames();
        Iterator<String> it = animList.iterator();
        while(it.hasNext()){
            String anim = it.next();
            if(anim.equals(animationName)){
                this.channel.setAnim(anim);
                this.channel.setLoopMode(LoopMode.Loop);
            }
        }
    }
    
    public void changeColor(String idPanelRef, String idTexture, float red,float green,float blue)
    {
        texturesSubMeshesData.changeColor(idPanelRef, idTexture, red, green, blue);
        
        HashMap<Integer,ArrayList<BufferedImage>> listTextures = texturesSubMeshesData.getCheckedTextures();
        dettachAllChild();
        cont++;
        String tempPath = "assets/Textures/FinalTexture"+cont+".png";
        
        ImagesProcessing imagesProcessing = new ImagesProcessing(listTextures);  
        BufferedImage bi = imagesProcessing.process(tempPath);
        
        AWTLoader loader = new AWTLoader();
        Image load = loader.load(bi, true);
        Texture2D texture = new Texture2D(load);
        
        mat = new Material(assetManager, "Common/MatDefs/Misc/Unshaded.j3md"); 
        mat.setTexture("ColorMap",texture);
        mainMesh.setMaterial(mat);
        attachAllChild();
        
    }
    
    public void changeSubMesh(String idPanel, String idSubMesh)
    {
        texturesSubMeshesData.changeSubMesh(idPanel,idSubMesh);
        setActivatedSubMeshes();
    }
        
    public void changeTexture(String idPanel, String idTexture)
    {
        texturesSubMeshesData.changeTexture(idPanel, idTexture);
        HashMap<Integer,ArrayList<BufferedImage>> listTextures = texturesSubMeshesData.getCheckedTextures();
        dettachAllChild();
        cont++;
        String tempPath = "assets/Textures/FinalTexture"+cont+".png";
        ImagesProcessing imagesProcessing = new ImagesProcessing(listTextures);    
        BufferedImage bi = imagesProcessing.process(tempPath);
        
        AWTLoader loader = new AWTLoader();
        Image load = loader.load(bi, true);
        Texture2D texture = new Texture2D(load);
        
        mat = new Material(assetManager, "Common/MatDefs/Misc/Unshaded.j3md"); 
        mat.setTexture("ColorMap",texture);
        mainMesh.setMaterial(mat);
        attachAllChild();
         
        //Borrar la imagen
        Path file = Paths.get(tempPath);
        try {
            Files.delete(file);
        } 
        catch (IOException ex) {
            System.out.println("Error al borrar el fichero");
        } 
    }
    
    public void deleteModel(){
        this.rootNode.detachAllChildren();
    }
    
    public boolean isCheckedTexture(String idPanel, String idTexture){
        return texturesSubMeshesData.isCheckedTexture(idPanel, idTexture);
    }
    
    public boolean isCheckedSubMesh(String idPanel, String idSubMesh){
        return texturesSubMeshesData.isCheckedSubMesh(idPanel, idSubMesh);
    }
    
    
    
    public void screenShot()
    {
        //POR HACER
    }
    
    /*
     * public void screenShot(ViewPort guiViewPort, NiftyJmeDisplay niftyDisplay, ScreenshotAppState screenShotState)
    {
        guiViewPort.removeProcessor(niftyDisplay);
        Set<String> namesAnimations = (Set<String>) control.getAnimationNames();
        //ScreenshotThread sst = new ScreenshotThread(screenShotState,channel,guiViewPort,niftyDisplay,namesAnimations);
        //sst.start();
    }
     */
}