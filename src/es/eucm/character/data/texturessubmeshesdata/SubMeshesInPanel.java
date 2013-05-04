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

import es.eucm.character.data.model.SubMeshType;
import es.eucm.character.imageprocessing.ColoringTextureSubMesh;
import es.eucm.character.imageprocessing.ImagesProcessingMainMesh;
import es.eucm.character.imageprocessing.ImagesProcessingSubMesh;
import java.awt.Color;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Set;

public class SubMeshesInPanel implements ElementInPanel{
    private boolean multiSelection;
    //HashMap<submesh, boolean = if this subMesh is selected>
    private HashMap<SubMeshType,AttrTexture> subMeshes;
    
    public SubMeshesInPanel(boolean multiSelection){
        this.multiSelection = multiSelection;
        subMeshes = new HashMap<SubMeshType,AttrTexture>();
    }
    
    public void addSubMesh(SubMeshType subMesh,boolean isCheck){
        subMeshes.put(subMesh, new AttrTexture(isCheck));
    }
    
    /*
     * Return a list with the activated submeshes in this panel
     */
    public ArrayList<SubMeshType> getCheckedSubMeshes(){
        ArrayList<SubMeshType> listCheckedSubMeshes = new ArrayList<SubMeshType>();
        Set<SubMeshType> setSubMeshesType = subMeshes.keySet();
        Iterator<SubMeshType> it = setSubMeshesType.iterator();
        while(it.hasNext()){
        SubMeshType subMesh = it.next();
            if (subMeshes.get(subMesh).isSelected()){
               listCheckedSubMeshes.add(subMesh);
            } 
        }
        return listCheckedSubMeshes;
    }
    
    @Override
    public boolean ischecked(String idSubMesh){
        boolean checked = false;
        Set<SubMeshType> setSubMeshType = subMeshes.keySet();
        Iterator<SubMeshType> it = setSubMeshType.iterator();
        while(it.hasNext()){
            SubMeshType subMesh = it.next();
            if (subMesh.getIdSubMesh().equals(idSubMesh)){
                checked = subMeshes.get(subMesh).isSelected();
            }
        }
        return checked;
    }
    
     /*
     * Change the submeshes with idSubMesh.
     * If the panel has multiselection, swap the value of this submesh.
     * Else we set the value of the other submeshes to false
     */
    @Override
    public void changeElement(String idSubMesh){
        Set<SubMeshType> keySet = subMeshes.keySet();
        Iterator<SubMeshType> it = keySet.iterator();
        while(it.hasNext()){
            SubMeshType subMesh = it.next();
            if(subMesh.getIdSubMesh().equals(idSubMesh)){
                if (multiSelection){
                    AttrTexture attrTexture = subMeshes.get(subMesh);
                    attrTexture.setSelected(!attrTexture.isSelected());
                }
                else{
                    AttrTexture attrTexture = subMeshes.get(subMesh);
                    attrTexture.setSelected(true);
                }
            }
            else if(!multiSelection){
                AttrTexture attrTexture = subMeshes.get(subMesh);
                attrTexture.setSelected(false);
            }
        }
    }
    
    @Override
    public void changeColorBaseShadow(String idSubMesh, float red, float green, float blue){
        Set<SubMeshType> keySet = subMeshes.keySet();
        Iterator<SubMeshType> it = keySet.iterator();
        while(it.hasNext()){
            SubMeshType subMesh = it.next();
            if (subMesh.getIdSubMesh().equals(idSubMesh)){
                ArrayList<BufferedImage> bi = ColoringTextureSubMesh.coloringImageBaseShadow(subMesh.getSubMeshTexture().getBaseShadowTextureSubMesh(), new Color(red, green, blue));
                subMeshes.get(subMesh).setListBufferedImage(bi);
                ArrayList<Color> colors = new ArrayList<Color>();
                colors.add(new Color(red, green, blue));
                subMeshes.get(subMesh).setListColors(colors);
            }
        }        
    }
    
    @Override
    public void changeColorDoubleTextureDetails(String idSubMesh, float red, float green, float blue) {
        Set<SubMeshType> keySet = subMeshes.keySet();
        Iterator<SubMeshType> it = keySet.iterator();
        while(it.hasNext()){
            SubMeshType subMesh = it.next();
            if (subMesh.getIdSubMesh().equals(idSubMesh)){
                //Obtener el buffer image de los detalles coloreados.
                BufferedImage bi = ColoringTextureSubMesh.coloringImageDoubleTextureDetails(subMesh.getSubMeshTexture().getDoubleTextureSubMesh(), new Color(red, green, blue));
                //Obtener el buffer image de la base que había guardada
                BufferedImage biBaseOriginal = subMeshes.get(subMesh).getListBufferedImage().get(0);
                //Crear la nueva lista de buffer image y guardarla
                ArrayList<BufferedImage> listBi = new ArrayList<BufferedImage>();
                listBi.add(biBaseOriginal);
                listBi.add(bi);
                subMeshes.get(subMesh).setListBufferedImage(listBi);
                
                if (subMeshes.get(subMesh).getListColors() == null){
                    ArrayList<Color> colors = new ArrayList<Color>();
                    colors.add(null);
                    colors.add(new Color(red, green, blue));
                    subMeshes.get(subMesh).setListColors(colors);
                }
                else{
                    Color colorBase = subMeshes.get(subMesh).getListColors().get(0);
                    ArrayList<Color> colors = new ArrayList<Color>();
                    colors.add(colorBase);
                    colors.add(new Color(red, green, blue));
                    subMeshes.get(subMesh).setListColors(colors);
                }
            }
        }
    }
    
    @Override
    public void changeColorDoubleTextureBase(String idSubMesh, float red, float green, float blue) {
        Set<SubMeshType> keySet = subMeshes.keySet();
        Iterator<SubMeshType> it = keySet.iterator();
        while(it.hasNext()){
            SubMeshType subMesh = it.next();
            if (subMesh.getIdSubMesh().equals(idSubMesh)){
                //Obtener el buffer image de la base coloreada.
                BufferedImage bi = ColoringTextureSubMesh.coloringImageDoubleTextureBase(subMesh.getSubMeshTexture().getDoubleTextureSubMesh(), new Color(red, green, blue));
                //Obtener el buffer image de los detalles que había guardado
                BufferedImage biDetailsOriginal = subMeshes.get(subMesh).getListBufferedImage().get(1);
                //Crear la nueva lista de buffer image y guardarla
                ArrayList<BufferedImage> listBi = new ArrayList<BufferedImage>();
                listBi.add(bi);
                listBi.add(biDetailsOriginal);
                subMeshes.get(subMesh).setListBufferedImage(listBi);
                
                if (subMeshes.get(subMesh).getListColors() == null){
                    ArrayList<Color> colors = new ArrayList<Color>();
                    colors.add(new Color(red, green, blue));
                    colors.add(null);
                    subMeshes.get(subMesh).setListColors(colors);
                }
                else{
                    Color colorDetails = subMeshes.get(subMesh).getListColors().get(1);
                    ArrayList<Color> colors = new ArrayList<Color>();
                    colors.add(new Color(red, green, blue));
                    colors.add(colorDetails);
                    subMeshes.get(subMesh).setListColors(colors);
                }
            }
        }
    }
    
    @Override
    public void changeColorMultiOptionTexture(String idSubMesh, String idSubTexture) {
        Set<SubMeshType> keySet = subMeshes.keySet();
        Iterator<SubMeshType> it = keySet.iterator();
        while(it.hasNext()){
            SubMeshType subMesh = it.next();
            if (subMesh.getIdSubMesh().equals(idSubMesh)){
                ArrayList<BufferedImage> bi = ColoringTextureSubMesh.coloringImageMultiOption(subMesh.getSubMeshTexture().getMultiOptionTextureSubMesh(), idSubTexture);
                subMeshes.get(subMesh).setListBufferedImage(bi);
            }
        }
    }
    
    @Override
    public ArrayList<Color> getColorTexture(String idSubMesh) {
        Set<SubMeshType> keySet = subMeshes.keySet();
        Iterator<SubMeshType> it = keySet.iterator();
        while(it.hasNext()){
            SubMeshType subMesh = it.next();
            if (subMesh.getIdSubMesh().equals(idSubMesh)){
                return subMeshes.get(subMesh).getListColors();
            }
        }
        return null;
    }

    public BufferedImage getSubMeshTexture(String idSubMesh) {
        Set<SubMeshType> keySet = subMeshes.keySet();
        Iterator<SubMeshType> it = keySet.iterator();
        while(it.hasNext()){
            SubMeshType subMesh = it.next();
            if (subMesh.getIdSubMesh().equals(idSubMesh)){
                ArrayList<BufferedImage> listBi = subMeshes.get(subMesh).getListBufferedImage();
                if (listBi == null){
                    //Crear el BufferedImage
                    ArrayList<BufferedImage> list = ImagesProcessingSubMesh.createBufferedImage(subMesh.getSubMeshTexture());
                    subMeshes.get(subMesh).setListBufferedImage(list);
                    return ImagesProcessingMainMesh.pasteImages(list, list.get(0).getWidth(), list.get(0).getHeight());
                }
                else{
                    return ImagesProcessingMainMesh.pasteImages(listBi, listBi.get(0).getWidth(), listBi.get(0).getHeight());
                }
            }
        }
        return null;
    }
}
