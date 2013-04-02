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

import data.model.*;
import java.util.ArrayList;
import java.util.Iterator;

public class ModelControl 
{
    private Model model;
    
    public ModelControl(Model model)
    {
        this.model = model;
    }
    
    /**************************  MAIN MESH   **********************************/
    
    //--------------------- TRANSFORMATIONS AND BONES  ------------------------/
    
    //Return the list of transformation of the main mesh.
    public ArrayList<TransformationType> getMainMeshTransformations()
    {
        return (ArrayList<TransformationType>) model.getMainMesh().getTransformation();
    }
    
    /*
     * Receives a input bonesController and return a list 
     * with the names of the bones associated.   
     */
    public ArrayList<String> getBones(String idBonesController)
    {
        ArrayList<String> listBonesNames = new ArrayList<String>();
        ArrayList<BoneType> bones = (ArrayList<BoneType>) model.getMainMesh().getBones().getBone();
        Iterator<BoneType> it = bones.iterator();
        while(it.hasNext())
        {
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
    public int getMinValueBoneController(String idBoneController)
    {
        ArrayList<BoneType> listBones = (ArrayList<BoneType>) model.getMainMesh().getBones().getBone();
        Iterator<BoneType> it = listBones.iterator();
        while(it.hasNext())
        {
            BoneType bone = it.next();
            if(bone.getIdControllerRef().equals(idBoneController)){
                return bone.getMinValue().intValue();
            }
        }
        return -1;
    }
    
    /*
     * Receives a input bone controller and return the maximum value associated 
     * with this controller.
     */
    public int getMaxValueBoneController(String idBoneController)
    {
        ArrayList<BoneType> listBones = (ArrayList<BoneType>) model.getMainMesh().getBones().getBone();
        Iterator<BoneType> it = listBones.iterator();
        while(it.hasNext())
        {
            BoneType bone = it.next();
            if(bone.getIdControllerRef().equals(idBoneController)){
                return bone.getMaxValue().intValue();
            }
        }
        return -1;
    }
    
    /*
     * Receives a input bone controller and return the default value associated 
     * with this controller.
     */
    public int getDefaultValueBoneController(String idBoneController)
    {
        ArrayList<BoneType> listBones = (ArrayList<BoneType>) model.getMainMesh().getBones().getBone();
        Iterator<BoneType> it = listBones.iterator();
        while(it.hasNext())
        {
            BoneType bone = it.next();
            if(bone.getIdControllerRef().equals(idBoneController)){
                return bone.getDefaultValue().intValue();
            }
        }
        return -1;
    }
    
    //-------------------    MAIN MESH´S TEXTURES   --------------------------/
    
    //Return the numbers of main mesh´s textures.
    public int getNumTextures(String idPanelRef)
    {
        int n = 0;
        ArrayList<TextureType> listTextures = (ArrayList<TextureType>) model.getMainMesh().getTextures().getBaseShadowTextureOrSimpleTextureOrDoubleTexture();
        Iterator<TextureType> it = listTextures.iterator();
        while(it.hasNext())
        {
            TextureType texture = it.next();
            if(texture.getIdPanelRef().equals(idPanelRef)){
                n++;
            }
        }
        return n;
    }
    
    /*
     * Receives a input id panel and return a list with the id textures 
     * which are associated with this panel.  
     */
    public ArrayList<String> getListIdsTextures(String idPanelRef)
    {
        ArrayList<String> listIdsTextures = new ArrayList<String>();
        ArrayList<TextureType> listTextures = (ArrayList<TextureType>) model.getMainMesh().getTextures().getBaseShadowTextureOrSimpleTextureOrDoubleTexture();
        Iterator<TextureType> it = listTextures.iterator();
        while(it.hasNext())
        {
            TextureType texture = it.next();
            if(texture.getIdPanelRef().equals(idPanelRef)){
                listIdsTextures.add(texture.getIdTexture());
            }
        }
        return listIdsTextures;
    }
    
    public TextureType getTexture(String idTexture)
    {
        ArrayList<TextureType> listTextures = (ArrayList<TextureType>) model.getMainMesh().getTextures().getBaseShadowTextureOrSimpleTextureOrDoubleTexture();
        Iterator<TextureType> it = listTextures.iterator();
        while(it.hasNext())
        {
            TextureType texture = it.next();
            if(texture.getIdTexture().equals(idTexture)){
                return texture;
            }
        }
        return null;
    }
    
    
    
    /*
     * Receives a input id panel and return a list with the texture´s icon path 
     * which are associated with this panel. 
     */
    public ArrayList<String> getIconsPathsTextures(String idPanelRef)
    {
        ArrayList<String> listIcons = new ArrayList<String>();
        ArrayList<TextureType> listTextures = (ArrayList<TextureType>) model.getMainMesh().getTextures().getBaseShadowTextureOrSimpleTextureOrDoubleTexture();
        Iterator<TextureType> it = listTextures.iterator();
        while(it.hasNext())
        {
            TextureType texture = it.next();
            if(texture.getIdPanelRef().equals(idPanelRef)){
                listIcons.add(texture.getIconPath());
            }            
        }
        return listIcons;
    }
    
    /*public ArrayList<TextureType> getDefaultTextures()
    {
        ArrayList<TextureType> listDefaultTextures = new ArrayList<TextureType>();
        ArrayList<TextureType> listTextures = (ArrayList<TextureType>) model.getMainMesh().getTextures().getBaseShadowTextureOrSimpleTextureOrDoubleTexture();
        Iterator<TextureType> it = listTextures.iterator();
        while(it.hasNext())
        {
            TextureType texture = it.next();
            if(texture.isDefault()){
                listDefaultTextures.add(texture);
            }
        }
        return listDefaultTextures;
    }*/
    
    //------------------------ PHYSICAL BUILD ---------------------------------/
    
    /*
     * Receives a input id panel and return a list with the name of physical build 
     *which are asocciated with this panel.
     */
    public ArrayList<String> getPhysicalBuild(String idPanelRef)
    {
        ArrayList<String> listIdPhysicalBuild = new ArrayList<String>();
        ArrayList<PhysicalBuildType> list = (ArrayList<PhysicalBuildType>) model.getMainMesh().getPhysicalBuilds().getPhysicalBuild();
        Iterator<PhysicalBuildType> it = list.iterator();
        while(it.hasNext())
        {
            PhysicalBuildType p = it.next();
            if(p.getIdPanelRef().equals(idPanelRef)){
                listIdPhysicalBuild.add(p.getLabel());
            }
        }
        return listIdPhysicalBuild;
    } 
    
    //Return a list of escalations associated to the physical build with id == idPhysicalBuild
    public ArrayList<EscalationType> getPhysicalBuildEscalations(String idPhysicalBuild)
    {
        ArrayList<PhysicalBuildType> listPhysicalBuild = (ArrayList<PhysicalBuildType>) model.getMainMesh().getPhysicalBuilds().getPhysicalBuild();
        Iterator<PhysicalBuildType> it = listPhysicalBuild.iterator();
        while(it.hasNext())
        {
            PhysicalBuildType physicalBuild = it.next();
            if(physicalBuild.getIdPhysicalBuild().equals(idPhysicalBuild)){
                return (ArrayList<EscalationType>) physicalBuild.getEscalation();
            }
        }
        return null;
    }   
    
    /***************************   SUBMESHES   ********************************/
    
    //Return the number of subMeshes.
    public int getNumSubMeshes(String idPanelRef)
    {
        int n = 0;
        ArrayList<SubMeshType> listSubMeshes = (ArrayList<SubMeshType>) model.getSubMesh();
        Iterator<SubMeshType> it = listSubMeshes.iterator();
        while(it.hasNext())
        {
            SubMeshType subMesh = it.next();
            if(subMesh.getIdPanelRef().equals(idPanelRef)){
                n++;
            }
        }
        return n;
    }
    
    public SubMeshType getSubMesh(String idSubMesh)
    {
        ArrayList<SubMeshType> listSubMeshes = (ArrayList<SubMeshType>) model.getSubMesh();
        Iterator<SubMeshType> it = listSubMeshes.iterator();
        while(it.hasNext())
        {
            SubMeshType subMesh = it.next();
            if(subMesh.getIdSubMesh().equals(idSubMesh)){
                return subMesh;
            }
        }
        return null;
    }
    
    //Receives a input id panel and return a list with the submesh´s icon path which are associated with this panel
    public ArrayList<String> getSubMeshIconPath(String idPanelRef)
    {
        ArrayList<String> listIcons = new ArrayList<String>();
        ArrayList<SubMeshType> listSubMeshes = (ArrayList<SubMeshType>) model.getSubMesh();
        Iterator<SubMeshType> it = listSubMeshes.iterator();
        while(it.hasNext())
        {
            SubMeshType subMesh = it.next();
            if(subMesh.getIdPanelRef().equals(idPanelRef)){
                listIcons.add(subMesh.getIconPath());
            }            
        }
        return listIcons; 
    }
    
    //Return a list of textures associated to the submesh with id == idSubMesh.
    public ArrayList<TextureType> getSubMeshTexture(String idSubMesh)
    {
        ArrayList<SubMeshType> listSubMeshes = (ArrayList<SubMeshType>) model.getSubMesh();
        Iterator<SubMeshType> it = listSubMeshes.iterator();
        while(it.hasNext())
        {
            SubMeshType subMesh = it.next();
            if(subMesh.getIdSubMesh().equals(idSubMesh)){
                return (ArrayList<TextureType>) subMesh.getTextures().getBaseShadowTextureOrSimpleTextureOrDoubleTexture();
            }
        }
        return null;
    }
    
    //Return a list of transformation for the submesh with id = idSubMesh.
    public ArrayList<TransformationType> getSubMeshTransformation(String idSubMesh)
    {
        ArrayList<SubMeshType> listSubMeshes = (ArrayList<SubMeshType>) model.getSubMesh();
        Iterator<SubMeshType> it = listSubMeshes.iterator();
        while(it.hasNext())
        {
            SubMeshType subMesh = it.next();
            if(subMesh.getIdSubMesh().equals(idSubMesh)){
                return (ArrayList<TransformationType>)subMesh.getTransformation();
            }
        }
        return null;
    }       
   
    
    
    
}
