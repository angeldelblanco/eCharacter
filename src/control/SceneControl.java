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
import data.model.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Set;

public class SceneControl 
{
    private Node rootNode;
    private AssetManager assetManager;
    private ModelControl modelControl;
    private Spatial mainMesh;
    private HashMap<String,TexturesInPanel> textures;
    private HashMap<String,Spatial> subMeshes;
    private Material mat;
    private AnimChannel channel;
    private AnimControl control;
    private Vector3f vectorScaleBase;
    
    public SceneControl(Node rootNode,AssetManager assetManager,ModelControl modelControl)
    {
        this.rootNode = rootNode;
        this.assetManager = assetManager;
        this.modelControl = modelControl;
        
        DirectionalLight dl = new DirectionalLight();
        dl.setDirection(new Vector3f(-0.1f, -1f, -1).normalizeLocal());
        this.rootNode.addLight(dl);
        
        mainMesh = this.assetManager.loadModel(this.modelControl.getMainMeshPath());        
        mat = new Material(assetManager, "Common/MatDefs/Misc/Unshaded.j3md"); 
        //Aqui deberia ir cargar Texturas por Defecto
        mat.setTexture("ColorMap",assetManager.loadTexture("assets/Textures/OriginalTexture.png"));         
        setDefaultSubMeshes();
        attachAllChild();
        rootNode.attachChild(mainMesh);
        setPositionModel(modelControl.getMainMeshTransformations());
       
        /*
        //Borrar la imagen
        Path file = Paths.get("assets/Textures/OriginalTexture.png");
        try {
            Files.delete(file);
        } 
        catch (IOException ex) {
            System.out.println("Error al borrar el fichero");
        } */      
    }
    
    public SceneControl(Spatial mainMesh,Material mat)
    {
        this.mainMesh = mainMesh;
        this.mat = mat;
        this.mainMesh.setMaterial(mat);
        this.subMeshes = new HashMap<String, Spatial>();
        this.vectorScaleBase = new Vector3f(1.0f,1.0f,1.0f);
        this.control = mainMesh.getControl(AnimControl.class);
        this.channel = this.control.createChannel();
        ArrayList<String> animList = (ArrayList<String>) this.control.getAnimationNames();
        if(animList.size() > 0){
            this.channel.setAnim(animList.get(0));
            this.channel.setLoopMode(LoopMode.Loop); 
        }
    }
    
    private void setDefaultSubMeshes()
    {
        ArrayList<SubMeshType> listSubMeshes = modelControl.getDefaultSubMeshes();
        Iterator<SubMeshType> it = listSubMeshes.iterator();
        while(it.hasNext())
        {
            SubMeshType subMesh = it.next();
            Spatial subMeshSpatial = assetManager.loadModel(subMesh.getPath());
            /******ÑAPAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA*///
            Material subMeshMaterial = new Material(assetManager, "Common/MatDefs/Light/Lighting.j3md");
            subMeshMaterial.setTexture("DiffuseMap",assetManager.loadTexture("assets/Textures/eAdventure/Textures Boy/PeloBoy.png"));
            subMeshSpatial.setMaterial(subMeshMaterial);
            /******FIN DE ÑAPAAAAAAAAA*/
            //If texture are distinct null,the submesh has a texture´s list.
            //Else have a .material and it load automatically 
            /*if(subMesh.getTextures().getBaseShadowTextureOrSimpleTextureOrDoubleTexture() != null){
                //PORHACER
            }*/
            ArrayList<TransformationType> listTransformation = modelControl.getSubMeshTransformation(subMesh.getIdSubMesh());
            addSubMesh(subMesh.getAssociatedBone(),subMeshSpatial,listTransformation);
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
        else if(transformation.equals("transalate")){
            s.move(t.getValueX(),t.getValueY(),t.getValueZ());
        }
        else if(transformation.equals("center")){
            s.center();
        }  
    }    
    
     public void addSubMesh(String bone,Spatial subMesh,ArrayList<TransformationType> listTransformations)
    {
            subMeshes.put(bone,subMesh);
            SkeletonControl skeletonControl = this.mainMesh.getControl(SkeletonControl.class);
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
    
    public void setPhysicalBuild(String idPhysicalBuild)
    {
        ArrayList<EscalationType> listEscalations = modelControl.getPhysicalBuildEscalations(idPhysicalBuild);
        Iterator<EscalationType> it = listEscalations.iterator();
        while(it.hasNext())
        {
            EscalationType escalation = it.next();
            applyEscalation(mainMesh,escalation);
        }
    }
    
    public void setBoneControllerValue(String idBoneController, float inc)
    {
        ArrayList<String> listBones = modelControl.getBones(idBoneController);      
        scaleBone(listBones, inc);
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
}