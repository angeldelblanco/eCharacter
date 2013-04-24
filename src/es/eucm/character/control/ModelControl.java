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

import es.eucm.character.data.model.BaseShadowTextureType;
import es.eucm.character.data.model.BoneType;
import es.eucm.character.data.model.DoubleTextureType;
import es.eucm.character.data.model.EscalationType;
import es.eucm.character.data.model.Model;
import es.eucm.character.data.model.MultiOptionTextureType;
import es.eucm.character.data.model.MultiOptionTextureType.OptionTexture;
import es.eucm.character.data.model.PhysicalBuildType;
import es.eucm.character.data.model.SimpleTextureType;
import es.eucm.character.data.model.SubMeshType;
import es.eucm.character.data.model.SubMeshType.SubMeshTexture;
import es.eucm.character.data.model.TextureType;
import es.eucm.character.data.model.TransformationType;
import es.eucm.character.loader.ResourceLocator;
import es.eucm.character.types.TexturesMeshType;
import java.util.ArrayList;
import java.util.Iterator;

public class ModelControl {
    private Model model;
    
    public ModelControl(Model model){
        this.model = model;
    }
    
    /**************************  MAIN MESH   **********************************/
    
    public String getMainMeshPath(){
        return model.getMainMesh().getPath();
    }
    
    //--------------------------  LANGUAGE PATH   -----------------------------/
    public String getLanguagePath(){
        return model.getLanguagesPath();
    }
    
    //--------------------- TRANSFORMATIONS AND BONES  ------------------------/
    
    //Return the list of transformation of the main mesh.
    public ArrayList<TransformationType> getMainMeshTransformations(){
        return (ArrayList<TransformationType>) model.getMainMesh().getTransformation();
    }
    
    /*
     * Receives a input bonesController and return a list 
     * with the names of the bones associated.   
     */
    public ArrayList<String> getBones(String idBonesController){
        ArrayList<String> listBonesNames = new ArrayList<String>();
        ArrayList<BoneType> bones = (ArrayList<BoneType>) model.getMainMesh().getBones().getBone();
        Iterator<BoneType> it = bones.iterator();
        while(it.hasNext()){
            BoneType b = it.next();
            if(b.getIdControllerRef().equals((idBonesController))){
                listBonesNames.add(b.getBoneName());
            }
        }
        return listBonesNames;
    }
    
    /*
     * Receives a input bone controller and return the minimum value associated 
     * with this controller.
     */
    public float getMinValueBoneController(String idBoneController){
        ArrayList<BoneType> listBones = (ArrayList<BoneType>) model.getMainMesh().getBones().getBone();
        Iterator<BoneType> it = listBones.iterator();
        while(it.hasNext()){
            BoneType bone = it.next();
            if(bone.getIdControllerRef().equals(idBoneController)){
                return bone.getMinValue();
            }
        }
        return -1;
    }
    
    /*
     * Receives a input bone controller and return the maximum value associated 
     * with this controller.
     */
    public float getMaxValueBoneController(String idBoneController){
        ArrayList<BoneType> listBones = (ArrayList<BoneType>) model.getMainMesh().getBones().getBone();
        Iterator<BoneType> it = listBones.iterator();
        while(it.hasNext()){
            BoneType bone = it.next();
            if(bone.getIdControllerRef().equals(idBoneController)){
                return bone.getMaxValue();
            }
        }
        return -1;
    }
    
    /*
     * Receives a input bone controller and return the default value associated 
     * with this controller.
     */
    public float getDefaultValueBoneController(String idBoneController){
        ArrayList<BoneType> listBones = (ArrayList<BoneType>) model.getMainMesh().getBones().getBone();
        Iterator<BoneType> it = listBones.iterator();
        while(it.hasNext()){
            BoneType bone = it.next();
            if(bone.getIdControllerRef().equals(idBoneController)){
                return bone.getDefaultValue();
            }
        }
        return -1;
    }
    
    public void setDefaultValueBoneController(String idBoneController, float value){
        ArrayList<BoneType> listBones = (ArrayList<BoneType>) model.getMainMesh().getBones().getBone();
        Iterator<BoneType> it = listBones.iterator();
        while(it.hasNext()){
            BoneType bone = it.next();
            if(bone.getIdControllerRef().equals(idBoneController)){
                bone.setDefaultValue(value);
                
            }
        }
    }
    
    //-------------------    MAIN MESH´S TEXTURES   --------------------------/
    
    //Return the numbers of textures or submeshes of the panel with id idPanelRef.
    public int getNumTexturesORSubMeshes(String idPanelRef){
        return getIdsTexturesORSubMeshes(idPanelRef).size();
    }    
    
    /*
     * Receives a input id panel and return a list with the id texture or submesh
     * which are associated with this panel. 
     */
    public ArrayList<String> getIdsTexturesORSubMeshes(String idPanelRef){
        ArrayList<String> listIds = new ArrayList<String>();
        boolean found = false;
        ArrayList<TextureType> listTextures = (ArrayList<TextureType>) model.getMainMesh().getMainMeshTextures().getBaseShadowTextureOrSimpleTextureOrDoubleTexture();
        Iterator<TextureType> it = listTextures.iterator();
        while(it.hasNext()){
            TextureType texture = it.next();
            if(texture.getIdPanelRef().equals(idPanelRef)){
                listIds.add(texture.getIdTexture());
                found = true;
            }            
        }
        if (!found){
            ArrayList<SubMeshType> listSubMeshes = (ArrayList<SubMeshType>) model.getSubMesh();
            Iterator<SubMeshType> it2 = listSubMeshes.iterator();
            while(it2.hasNext()){
                SubMeshType subMesh = it2.next();
                if(subMesh.getIdPanelRef().equals(idPanelRef)){
                    listIds.add(subMesh.getIdSubMesh());
                }            
            }
        }
        return listIds;
    }
    
    /*
     * Receives a input id texture and return a texture´s icon path 
     * which are associated with this texture. 
     */
    public String getIconPathTexturesORSubMeshes(String idTexturesORSubMeshes){
        //Search in textures
        ArrayList<TextureType> listTextures = (ArrayList<TextureType>) model.getMainMesh().getMainMeshTextures().getBaseShadowTextureOrSimpleTextureOrDoubleTexture();
        Iterator<TextureType> it = listTextures.iterator();
        while(it.hasNext()){
            TextureType texture = it.next();
            if(texture.getIdTexture().equals(idTexturesORSubMeshes)){
                if (ResourceLocator.getResource(texture.getIconPath()) != null){
                    return texture.getIconPath();
                }
                else{ 
                    return null;
                }
            }            
        }
        //Search in submeshes
        ArrayList<SubMeshType> listSubMeshes = (ArrayList<SubMeshType>) model.getSubMesh();
        Iterator<SubMeshType> it2 = listSubMeshes.iterator();
        while(it2.hasNext()){
            SubMeshType subMesh = it2.next();
            if(subMesh.getIdSubMesh().equals(idTexturesORSubMeshes)){
                if (ResourceLocator.getResource(subMesh.getIconPath()) != null){
                    return subMesh.getIconPath();
                }
                else{
                    return null;
                }
            }            
        }
        return null; 
    }
    
    /*
     * Return the list of default textures.
     */
    public ArrayList<TextureType> getDefaultTextures(){
        ArrayList<TextureType> listDefaultTextures = new ArrayList<TextureType>();
        ArrayList<TextureType> listTextures = (ArrayList<TextureType>) model.getMainMesh().getMainMeshTextures().getBaseShadowTextureOrSimpleTextureOrDoubleTexture();
        Iterator<TextureType> it = listTextures.iterator();
        while(it.hasNext()){
            TextureType textureType = it.next();
            if(textureType instanceof BaseShadowTextureType){
                if(((BaseShadowTextureType)textureType).isDefault()){
                    listDefaultTextures.add(textureType);
                }
            }
            else if(textureType instanceof DoubleTextureType){
                if(((DoubleTextureType)textureType).isDefault()){
                    listDefaultTextures.add(textureType);
                }
            }
            else if(textureType instanceof SimpleTextureType){
                if(((SimpleTextureType)textureType).isDefault()){
                    listDefaultTextures.add(textureType);
                }
            }
            else if(textureType instanceof MultiOptionTextureType){
                ArrayList<OptionTexture> listMultiTextures = (ArrayList<OptionTexture>) ((MultiOptionTextureType)textureType).getOptionTexture();
                Iterator<OptionTexture> it2 = listMultiTextures.iterator();
                boolean isDefault = false;
                while(!isDefault && it2.hasNext()){
                    OptionTexture texture = it2.next();
                    if(texture.isDefault()){
                        isDefault = true;
                        listDefaultTextures.add(textureType);
                    }
                }
            }
        }
        return listDefaultTextures;
    }
    
    public TextureType getTexture(String idTexture){
        ArrayList<TextureType> listTextures = (ArrayList<TextureType>) model.getMainMesh().getMainMeshTextures().getBaseShadowTextureOrSimpleTextureOrDoubleTexture();
        Iterator<TextureType> it = listTextures.iterator();
        while(it.hasNext()){
            TextureType texture = it.next();
            if(texture.getIdTexture().equals(idTexture)){
                return texture;
            }
        }
        return null;
    }
    
    private TexturesMeshType getTextureMainMeshType(String idTexture){
        TexturesMeshType texturesType = null;
        ArrayList<TextureType> listTextures = (ArrayList<TextureType>) model.getMainMesh().getMainMeshTextures().getBaseShadowTextureOrSimpleTextureOrDoubleTexture();
        Iterator<TextureType> it = listTextures.iterator();
        while(it.hasNext()){
            TextureType texture = it.next();
            if(texture.getIdTexture().equals(idTexture)){
                if(texture instanceof BaseShadowTextureType){
                    texturesType = TexturesMeshType.baseShadow;
                }
                else if(texture instanceof DoubleTextureType){
                    texturesType = TexturesMeshType.doubleTexture;
                }
                else if(texture instanceof SimpleTextureType){
                    texturesType = TexturesMeshType.simpleTexture;
                }
                else if(texture instanceof MultiOptionTextureType){
                    texturesType = TexturesMeshType.multiOptionTexture;
                }
            }
        }
        return texturesType;
    }
    
    private TexturesMeshType getTextureSubMeshtype(String idSubMesh){
        SubMeshType subMesh = getSubMesh(idSubMesh);
        SubMeshTexture textureSubMesh = subMesh.getSubMeshTexture();
        if (textureSubMesh != null){
            if(textureSubMesh.getBaseShadowTextureSubMesh() != null){
                return TexturesMeshType.baseShadow;
            }
            else if(textureSubMesh.getSimpleTextureSubMesh() != null){
                return TexturesMeshType.simpleTexture;
            }
            else if(textureSubMesh.getDoubleTextureSubMesh()!= null){
                return TexturesMeshType.doubleTexture;
            }
            else if(textureSubMesh.getMultiOptionTextureSubMesh() != null){
                return TexturesMeshType.multiOptionTexture;
            }
        }
        return null;
    }
    
    public TexturesMeshType getTextureType(String idTextureOrSubMesh){
        TexturesMeshType texturesType = getTextureMainMeshType(idTextureOrSubMesh);
        if (texturesType != null){
            //It's a texture.
            return texturesType;
        }
        else{
            return getTextureSubMeshtype(idTextureOrSubMesh);
        }
    }
    
    public ArrayList<TextureType> getAllTextures(){
        return (ArrayList<TextureType>) model.getMainMesh().getMainMeshTextures().getBaseShadowTextureOrSimpleTextureOrDoubleTexture();
    }
    
    public ArrayList<String> getIdsTexturesInMultiOption(String idMultiOption){
        ArrayList<String> listIdTexturesMultiOption = new ArrayList<String>();
        MultiOptionTextureType multiOptionTexture = (MultiOptionTextureType) getTexture(idMultiOption);
        ArrayList<OptionTexture> listOptionTexture = (ArrayList<OptionTexture>) multiOptionTexture.getOptionTexture();
        Iterator<OptionTexture> it = listOptionTexture.iterator();
        while(it.hasNext()){
            OptionTexture optionTexture = it.next();
            listIdTexturesMultiOption.add(optionTexture.getIdSubTexture());
        }
        return listIdTexturesMultiOption;   
    }
    
    public int getNumTexturesInMultiOption(String idMultiOption){
        MultiOptionTextureType multiOptionTexture = (MultiOptionTextureType) getTexture(idMultiOption);
        return multiOptionTexture.getOptionTexture().size();
    }
    
    public String getIconPathTextureInMultiOption(String idMultiOption,String idSubTexture){
        MultiOptionTextureType multiOptionTexture = (MultiOptionTextureType) getTexture(idMultiOption);
        ArrayList<OptionTexture> listOptionTexture = (ArrayList<OptionTexture>) multiOptionTexture.getOptionTexture();
        Iterator<OptionTexture> it = listOptionTexture.iterator();
        while(it.hasNext()){
            OptionTexture optionTexture = it.next();
            if(optionTexture.getIdSubTexture().equals(idSubTexture)){
                if (ResourceLocator.getResource(optionTexture.getIconPath()) != null){
                    return optionTexture.getIconPath();
                }
                else{ 
                    return null;
                }
            }
        }
        return null;
    }
    
    //------------------------ PHYSICAL BUILD ---------------------------------/
    
    /*
     * Receives a input id panel and return a list with the id physical build 
     * which are asocciated with this panel.
     */
    public ArrayList<String> getIdsPhysicalBuild(String idPanelRef){
        ArrayList<String> listIdsPhysicalBuild = new ArrayList<String>();
        ArrayList<PhysicalBuildType> listPhysicalBuild = (ArrayList<PhysicalBuildType>) model.getMainMesh().getPhysicalBuilds().getPhysicalBuild();
        Iterator<PhysicalBuildType> it = listPhysicalBuild.iterator();
        while(it.hasNext()){
            PhysicalBuildType pb = it.next();
            if(pb.getIdPanelRef().equals(idPanelRef)){
                listIdsPhysicalBuild.add(pb.getIdPhysicalBuild());
            }
        }
        return listIdsPhysicalBuild;
    } 
    
    /*
     * Receives a input id physical build and return the name of physical build 
     * which are asocciated with this id physical build.
     */
    public String getPhysicalBuildLabel(String idPhysicalBuild){
        ArrayList<PhysicalBuildType> listPhysicalBuild = (ArrayList<PhysicalBuildType>) model.getMainMesh().getPhysicalBuilds().getPhysicalBuild();
        Iterator<PhysicalBuildType> it = listPhysicalBuild.iterator();
        while(it.hasNext()){
            PhysicalBuildType pb = it.next();
            if(pb.getIdPhysicalBuild().equals(idPhysicalBuild)){
                return pb.getLabel();
            }
        }
        return null;
    } 
    
    //Return a list of escalations associated to the physical build with id == idPhysicalBuild
    public ArrayList<EscalationType> getPhysicalBuildEscalations(String idPhysicalBuild){
        ArrayList<PhysicalBuildType> listPhysicalBuild = (ArrayList<PhysicalBuildType>) model.getMainMesh().getPhysicalBuilds().getPhysicalBuild();
        Iterator<PhysicalBuildType> it = listPhysicalBuild.iterator();
        while(it.hasNext()){
            PhysicalBuildType physicalBuild = it.next();
            if(physicalBuild.getIdPhysicalBuild().equals(idPhysicalBuild)){
                return (ArrayList<EscalationType>) physicalBuild.getEscalation();
            }
        }
        return null;
    }   
    
    /***************************   SUBMESHES   ********************************/
    
    public ArrayList<SubMeshType> getDefaultSubMeshes(){
        ArrayList<SubMeshType> listDefaultSubMeshes = new ArrayList<SubMeshType>();
        ArrayList<SubMeshType> listSubMeshes = (ArrayList<SubMeshType>) model.getSubMesh();
        Iterator<SubMeshType> it = listSubMeshes.iterator();
        while(it.hasNext()){
            SubMeshType subMesh = it.next();
            if(subMesh.isDefault()){
                listDefaultSubMeshes.add(subMesh);
            }
        }
        return listDefaultSubMeshes;
    }
    
    public SubMeshType getSubMesh(String idSubMesh){
        ArrayList<SubMeshType> listSubMeshes = (ArrayList<SubMeshType>) model.getSubMesh();
        Iterator<SubMeshType> it = listSubMeshes.iterator();
        while(it.hasNext()){
            SubMeshType subMesh = it.next();
            if(subMesh.getIdSubMesh().equals(idSubMesh)){
                return subMesh;
            }
        }
        return null;
    }
    
    public ArrayList<SubMeshType> getAllSubMeshes(){
        return (ArrayList<SubMeshType>) model.getSubMesh();
    }
}
