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
package data.texturessubmeshesdata;

import data.texturessubmeshesdata.TexturesInPanel;
import data.texturessubmeshesdata.SubMeshesInPanel;
import data.family.SubStageType;
import data.model.BaseShadowTextureType;
import data.model.DoubleTextureType;
import data.model.MultiOptionTextureType;
import data.model.MultiOptionTextureType.Texture;
import data.model.SimpleTextureType;
import data.model.SubMeshType;
import data.model.TextureType;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Set;


public class TexturesSubMeshesData 
{
    //HashMap<idPanel,TexturesInPanel>
    private HashMap<String,TexturesInPanel> texturesData;
    //HashMap<idPanel,SubMeshesInPanel>
    private HashMap<String,SubMeshesInPanel> subMeshesData;
    
    public TexturesSubMeshesData(ArrayList<SubStageType> listSubStages, ArrayList<TextureType> listTextures,ArrayList<SubMeshType> listSubMeshes)
    {
        texturesData = new HashMap<String,TexturesInPanel>();
        subMeshesData = new HashMap<String,SubMeshesInPanel>();
        setData(listSubStages, listTextures, listSubMeshes);
    }
    
    /*
     * Input: A list of all substages,a list of all the texturesData who the model has 
     * and a list of all the submeshes who the model has. 
     */
    private void setData(ArrayList<SubStageType> listSubStages, ArrayList<TextureType> listTextures,ArrayList<SubMeshType> listSubMeshes)
    {
        //Create the keyset of the hashMaps with the idPanel of the substages
        createKeySet(listSubStages);        
        //Fill texturesData with the list of textures
        fillTexturesData(listTextures);
        //Fill subMeshesData with the list of submeshes
        fillSubMeshesData(listSubMeshes);        
    }
    
    /*
     * Create the keyset of the hashMaps with the idPanel of the substages
     */
    private void createKeySet(ArrayList<SubStageType> listSubStages)
    {
        Iterator<SubStageType> it = listSubStages.iterator();
        while(it.hasNext())
        {
            SubStageType subStage = it.next();
            if(subStage.getSubStageType().equals("meshSubStage")){
                subMeshesData.put(subStage.getIdPanel(),new SubMeshesInPanel(subStage.isMultiselection()));
            }
            else if(subStage.getSubStageType().equals("textureSubStage")){
                texturesData.put(subStage.getIdPanel(),new TexturesInPanel(subStage.isMultiselection()));
            }            
        }        
    }
    
    /*
     * Fill texturesData with the list of textures
     */
    private void fillTexturesData(ArrayList<TextureType> listTextures)
    {
        Iterator<TextureType> it = listTextures.iterator();
        while(it.hasNext())
        {
            TextureType texture = it.next();
            if(texture instanceof BaseShadowTextureType){
                BaseShadowTextureType baseShadowTexture = ((BaseShadowTextureType)texture);
                texturesData.get(baseShadowTexture.getIdPanelRef()).addTexture(baseShadowTexture,baseShadowTexture.isDefault());
            }
            else if(texture instanceof SimpleTextureType){
                SimpleTextureType simpleTexture = ((SimpleTextureType)texture);
                texturesData.get(simpleTexture.getIdPanelRef()).addTexture(simpleTexture,simpleTexture.isDefault());
            }
            else if(texture instanceof DoubleTextureType){
                DoubleTextureType doubleTexture = ((DoubleTextureType)texture);
                texturesData.get(doubleTexture.getIdPanelRef()).addTexture(doubleTexture,doubleTexture.isDefault());
            }
            else if(texture instanceof MultiOptionTextureType){
                MultiOptionTextureType multiOptionTexture = ((MultiOptionTextureType)texture);
                ArrayList<Texture> listMultiOptionTexture = (ArrayList<Texture>) multiOptionTexture.getTexture();
                Iterator<Texture> it2 = listMultiOptionTexture.iterator();
                boolean isDefault = false;
                while(!isDefault && it2.hasNext())
                {
                    if(it2.next().isDefault()){
                        isDefault = true;
                    }
                }
                texturesData.get(multiOptionTexture.getIdPanelRef()).addTexture(multiOptionTexture,isDefault);
            }
        }
    }
    
    /*
     * Fill subMeshesData with the list of submeshes
     */
    private void fillSubMeshesData(ArrayList<SubMeshType> listSubMeshes)
    {
        Iterator<SubMeshType> it = listSubMeshes.iterator();
        while(it.hasNext())
        {
            SubMeshType subMesh = it.next();
            subMeshesData.get(subMesh.getIdPanelRef()).addSubMesh(subMesh, subMesh.isDefault());
        }
    }
    
    /*
     * Return the list of textures activated
     */
    /*public ArrayList<TextureType> getCheckedTextures()
    {
        ArrayList<TextureType> listCheckedTextures = new ArrayList<TextureType>();
        Set<String> keyIdPanels = texturesData.keySet();
        Iterator<String> it = keyIdPanels.iterator();
        while(it.hasNext())
        {
            TexturesInPanel texturesInPanel = texturesData.get(it.next());
            ArrayList<TextureType> listCheckedTexturesInPanel = texturesInPanel.getCheckedTextures();
            listCheckedTextures.addAll(listCheckedTexturesInPanel);
        }
        return listCheckedTextures;
    }*/
    
    public HashMap<Integer,ArrayList<BufferedImage>> getCheckedTextures()
    {
        HashMap<Integer,ArrayList<BufferedImage>> hashMapCheckedTextures = new HashMap<Integer,ArrayList<BufferedImage>>();
        Set<String> keyIdPanels = texturesData.keySet();
        Iterator<String> it = keyIdPanels.iterator();
        while(it.hasNext())
        {
            TexturesInPanel texturesInPanel = texturesData.get(it.next());
            //Obtener la lista de texturas seleccionadas en un panel en concreto
            ArrayList<TextureType> listCheckedTexturesInPanel = texturesInPanel.getCheckedTextures();          
            //Para cada textura, meterla en el hashmap con su layer correspondiente y su BufferedImage correspondiente
            Iterator<TextureType> it2 = listCheckedTexturesInPanel.iterator();
            while(it2.hasNext()){
                TextureType texture = it2.next();
                if(hashMapCheckedTextures.get(texture.getLayer().intValue()) != null){
                    BufferedImage bi = texturesInPanel.getBufferedImage(texture);
                    hashMapCheckedTextures.get(texture.getLayer().intValue()).add(bi);
                }
                else{
                    //Si la textura no está, en el layer correspondiente hay que añadir el BufferedImage asociado a esa textura
                    ArrayList<BufferedImage> list = new ArrayList<BufferedImage>();
                    BufferedImage bi = texturesInPanel.getBufferedImage(texture);
                    list.add(bi);
                    hashMapCheckedTextures.put(texture.getLayer().intValue(),list);
                }
            }
        }
        return hashMapCheckedTextures;
    } 
    
    /*
     * Return the list of textures activated
     */
    public ArrayList<SubMeshType> getCheckedSubMeshes()
    {
        ArrayList<SubMeshType> listCheckedSubMeshes = new ArrayList<SubMeshType>();
        Set<String> keyIdPanels = subMeshesData.keySet();
        Iterator<String> it = keyIdPanels.iterator();
        while(it.hasNext())
        {
            SubMeshesInPanel subMeshesInPanel = subMeshesData.get(it.next());
            ArrayList<SubMeshType> listCheckedSubMeshesInPanel = subMeshesInPanel.getCheckedSubMeshes();
            listCheckedSubMeshes.addAll(listCheckedSubMeshesInPanel);
        }
        return listCheckedSubMeshes;
    }
    
    /*
     * Change the texture with idTexture in the panel with idPanel
     */
    public void changeTexture(String idPanel,String idTexture){
        texturesData.get(idPanel).changeTexture(idTexture);
    }
    
    /*
     * Change the subMesh with idSubMesh in the panel with idPanel
     */
    public void changeSubMesh(String idPanel,String idSubMesh){
        subMeshesData.get(idPanel).changeSubMesh(idSubMesh);
    }

    public void changeColor(String idPanel, String idTexture, float red, float green, float blue) {
        texturesData.get(idPanel).changeColor(idTexture, red, green, blue);
    }
    
    public boolean isCheckedTexture(String idPanel, String idTexture){
        return texturesData.get(idPanel).ischecked(idTexture);
    }
    
    public boolean isCheckedSubMesh(String idPanel, String idSubMesh){
        return subMeshesData.get(idPanel).ischecked(idSubMesh);
    }
}
