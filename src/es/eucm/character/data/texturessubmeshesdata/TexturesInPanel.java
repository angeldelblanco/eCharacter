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
package es.eucm.character.data.texturessubmeshesdata;

import es.eucm.character.data.model.TextureType;
import es.eucm.character.imageprocessing.ColoringImage;
import es.eucm.character.imageprocessing.ImagesProcessing;
import java.awt.Color;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Set;


public class TexturesInPanel 
{
    private boolean multiSelection;
    //HashMap<Texture, boolean = if this texture is selected>
    //private HashMap<TextureType,Boolean> textures;
    private HashMap<TextureType,AttrTexture> textures;
    
    public TexturesInPanel(boolean multiSelection)
    {
        this.multiSelection = multiSelection;
        textures = new HashMap<TextureType,AttrTexture>();
    }  
    
    public void addTexture(TextureType texture, boolean isCheck)
    {
        textures.put(texture, new AttrTexture(isCheck));
    }
    
    /*
     * Return a list with the activated textures in this panel
     */
    public ArrayList<TextureType> getCheckedTextures()
    {
        ArrayList<TextureType> listCheckedTextures = new ArrayList<TextureType>();
        Set<TextureType> setTextureType = textures.keySet();
        Iterator<TextureType> it = setTextureType.iterator();
        while(it.hasNext())
        {
            TextureType texture = it.next();
            if (textures.get(texture).isSelected()){
               listCheckedTextures.add(texture);
           } 
        }
        return listCheckedTextures;
    }
    
    public boolean ischecked(String idTexture){
        boolean checked = false;
        Set<TextureType> setTextureType = textures.keySet();
        Iterator<TextureType> it = setTextureType.iterator();
        while(it.hasNext()){
            TextureType texture = it.next();
            if (texture.getIdTexture().equals(idTexture)){
                checked = textures.get(texture).isSelected();
            }
        }
        return checked;
    }
    
    /*
     * Change the texture with idTexture.
     * If the panel has multiselection, swap the value of this texture.
     * Else we set the value of the other textures to false
     */
    public void changeTexture(String idTexture)
    {
        Set<TextureType> keySet = textures.keySet();
        Iterator<TextureType> it = keySet.iterator();
        while(it.hasNext())
        {
            TextureType texture = it.next();
            if (texture.getIdTexture().equals(idTexture))
            {
                if (multiSelection){
                    //boolean b = textures.get(texture).isSelected();
                    //textures.put(texture,!b);
                    AttrTexture attrTexture = textures.get(texture);
                    attrTexture.setSelected(!attrTexture.isSelected());
                    //textures.put(texture,attrTexture);
                }
                else {
                    //textures.put(texture,true);
                    AttrTexture attrTexture = textures.get(texture);
                    attrTexture.setSelected(true);
                    //textures.put(texture,attrTexture);
                }
            }
            else if (!multiSelection) {
                //textures.put(texture,false);
                AttrTexture attrTexture = textures.get(texture);
                attrTexture.setSelected(false);
                //textures.put(texture,attrTexture);
            }
        }        
    }
    
    public void changeColorBaseShadow(String idTexture, float red, float green, float blue){
        Set<TextureType> keySet = textures.keySet();
        Iterator<TextureType> it = keySet.iterator();
        while(it.hasNext()){
            TextureType texture = it.next();
            if (texture.getIdTexture().equals(idTexture)){
                ArrayList<BufferedImage> bi = ColoringImage.coloringImageBaseShadow(texture, new Color(red, green, blue));
                textures.get(texture).setListBufferedImage(bi);
            }
        }        
    }
    
    public void changeColorDoubleTextureDetails(String idTexture, float red, float green, float blue) {
        Set<TextureType> keySet = textures.keySet();
        Iterator<TextureType> it = keySet.iterator();
        while(it.hasNext()){
            TextureType texture = it.next();
            if (texture.getIdTexture().equals(idTexture)){
                //Obtener el buffer image de los detalles coloreados.
                BufferedImage bi = ColoringImage.coloringImageDoubleTextureDetails(texture, new Color(red, green, blue));
                //Obtener el buffer image de la base que había guardada
                BufferedImage biBaseOriginal = textures.get(texture).getListBufferedImage().get(0);
                //Crear la nueva lista de buffer image y guardarla
                ArrayList<BufferedImage> listBi = new ArrayList<BufferedImage>();
                listBi.add(biBaseOriginal);
                listBi.add(bi);
                textures.get(texture).setListBufferedImage(listBi);
            }
        }
    }
    
    public void changeColorDoubleTextureBase(String idTexture, float red, float green, float blue) {
        Set<TextureType> keySet = textures.keySet();
        Iterator<TextureType> it = keySet.iterator();
        while(it.hasNext()){
            TextureType texture = it.next();
            if (texture.getIdTexture().equals(idTexture)){
                /*ArrayList<BufferedImage> bi = ColoringImage.coloringImageDoubleTextureBase(texture, new Color(red, green, blue));
                textures.get(texture).setListBufferedImage(bi);*/
                //Obtener el buffer image de la base coloreada.
                BufferedImage bi = ColoringImage.coloringImageDoubleTextureBase(texture, new Color(red, green, blue));
                //Obtener el buffer image de los detalles que había guardado
                BufferedImage biDetailsOriginal = textures.get(texture).getListBufferedImage().get(1);
                //Crear la nueva lista de buffer image y guardarla
                ArrayList<BufferedImage> listBi = new ArrayList<BufferedImage>();
                listBi.add(bi);
                listBi.add(biDetailsOriginal);
                textures.get(texture).setListBufferedImage(listBi);
            }
        }
    }
    
    public void changeColorMultiOptionTexture(String idMultiOption, String idSubTexture) {
        Set<TextureType> keySet = textures.keySet();
        Iterator<TextureType> it = keySet.iterator();
        while(it.hasNext()){
            TextureType texture = it.next();
            if (texture.getIdTexture().equals(idMultiOption)){
                ArrayList<BufferedImage> bi = ColoringImage.coloringImageMultiOption(texture, idSubTexture);
                textures.get(texture).setListBufferedImage(bi);
            }
        }
    }
    
    //Comprobar que si es null, hay que generarlo
    public ArrayList<BufferedImage> getListBufferedImage(TextureType texture){
        //Si el BufferedImage es null, es porque no se ha creado todavía. Hay que crearlo con imageProcessing.
        if(textures.get(texture).getListBufferedImage()== null){
            textures.get(texture).setListBufferedImage(ImagesProcessing.createBufferedImage(texture));
            return textures.get(texture).getListBufferedImage();
        }
        else {
            return textures.get(texture).getListBufferedImage();
        }
    }
}
