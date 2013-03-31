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
import java.util.HashSet;
import java.util.Iterator;

public class ModelControl 
{
    private Model model;
    
    public ModelControl(Model model)
    {
        this.model = model;
    }
    
    public int getNumTextures(String idPanel)
    {
        int n = 0;
        ArrayList<TextureType> listTextures = (ArrayList<TextureType>) model.getMainMesh().getTextures().getBaseShadowTextureOrSimpleTextureOrDoubleTexture();
        Iterator<TextureType> it = listTextures.iterator();
        while(it.hasNext())
        {
            TextureType texture = it.next();
            if(texture.getIdPanel().equals(idPanel)){
                n++;
            }
        }
        return n;
    }
    
    public ArrayList<String> getIconsPaths(String idPanel)
    {
        ArrayList<String> listIcons = new ArrayList<String>();
        ArrayList<TextureType> listTextures = (ArrayList<TextureType>) model.getMainMesh().getTextures().getBaseShadowTextureOrSimpleTextureOrDoubleTexture();
        Iterator<TextureType> it = listTextures.iterator();
        while(it.hasNext())
        {
            TextureType texture = it.next();
            if(texture.getIdPanel().equals(idPanel)){
                listIcons.add(texture.getIconPath());
            }            
        }
        return listIcons;
    }
    
    public int getNumSubMeshes(String idPanel)
    {
        int n = 0;
        ArrayList<SubMeshType> listSubMeshes = (ArrayList<SubMeshType>) model.getSubMesh();
        Iterator<SubMeshType> it = listSubMeshes.iterator();
        while(it.hasNext())
        {
            SubMeshType subMesh = it.next();
            if(subMesh.getIdPanel().equals(idPanel)){
                n++;
            }
        }
        return n;
    }
    
    public ArrayList<String> getSubMeshIconPath(String idPanel)
    {
        ArrayList<String> listIcons = new ArrayList<String>();
        ArrayList<SubMeshType> listSubMeshes = (ArrayList<SubMeshType>) model.getSubMesh();
        Iterator<SubMeshType> it = listSubMeshes.iterator();
        while(it.hasNext())
        {
            SubMeshType subMesh = it.next();
            if(subMesh.getIdPanel().equals(idPanel)){
                listIcons.add(subMesh.getIconPath());
            }            
        }
        return listIcons; 
    }
    
    public ArrayList<String> getPhysicalBuild()
    {
        ArrayList<String> listIdPhysicalBuild = new ArrayList<String>();
        ArrayList<PhysicalBuildType> list = (ArrayList<PhysicalBuildType>) model.getMainMesh().getPhysicalBuilds().getPhysicalBuild();
        Iterator<PhysicalBuildType> it = list.iterator();
        while(it.hasNext())
        {
            PhysicalBuildType p = it.next();
            listIdPhysicalBuild.add(p.getLabel());
        }
        return listIdPhysicalBuild;
    }            
    /**hay que llamar antes al metodo getIdBonesController(String idPanel) para obtener el conjunto de controladores*/
    public ArrayList<String> getBones(HashSet<String> idBonesController)
    {
        ArrayList<String> listId = new ArrayList<String>();
        ArrayList<BoneType> bones = (ArrayList<BoneType>) model.getMainMesh().getBones().getBone();
        Iterator<BoneType> it = bones.iterator();
        while(it.hasNext())
        {
            BoneType b = it.next();
            if(idBonesController.contains(b.getIdControllerRef())){
                listId.add(b.getBoneName());
            }
        }
        return listId;
    }
    
}
