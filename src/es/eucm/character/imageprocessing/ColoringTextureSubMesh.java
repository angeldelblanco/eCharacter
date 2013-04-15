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

package es.eucm.character.imageprocessing;

import es.eucm.character.data.model.BaseShadowTextureSubMeshType;
import es.eucm.character.data.model.DoubleTextureSubMeshType;
import es.eucm.character.data.model.MultiOptionTextureSubMeshType;
import es.eucm.character.data.model.MultiOptionTextureSubMeshType.OptionTexture;
import es.eucm.character.loader.ResourceHandler;
import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.logging.Level;
import javax.imageio.ImageIO;

public class ColoringTextureSubMesh extends ColoringImage{
    
    public static ArrayList<BufferedImage> coloringImageBaseShadow(BaseShadowTextureSubMeshType texture, Color color){
        ArrayList<BufferedImage> listBi = null;
        try{
            BufferedImage biBase = ImageIO.read(ResourceHandler.getResource(texture.getPath()));
            BufferedImage biShadow = null;
            if(texture.getShadowPath() != null){
                biShadow = ImageIO.read(ResourceHandler.getResource(texture.getShadowPath()));
            }
            listBi = new ArrayList<BufferedImage>();
            listBi.add(coloringImageBaseShadow(biBase, biShadow, color));
        }
        catch (IOException ex) {
            java.util.logging.Logger.getLogger(ImagesProcessingMainMesh.class.getName()).log(Level.SEVERE, null, ex);
        }
        return listBi;
    }
    
    public static BufferedImage coloringImageDoubleTextureDetails(DoubleTextureSubMeshType texture, Color color){
        BufferedImage bi = null;
        try{
            BufferedImage biDetails = ImageIO.read(ResourceHandler.getResource(texture.getDetailsPath()));
            bi = coloringImageDouble(biDetails, color);
        }
        catch (IOException ex) {
            java.util.logging.Logger.getLogger(ImagesProcessingMainMesh.class.getName()).log(Level.SEVERE, null, ex);
        }
        return bi;
    }
    
    public static BufferedImage coloringImageDoubleTextureBase(DoubleTextureSubMeshType texture, Color color){
        BufferedImage bi = null;
        try{
            BufferedImage biBase = ImageIO.read(ResourceHandler.getResource(texture.getBasePath()));
            bi = coloringImageDouble(biBase, color);
        }
        catch (IOException ex) {
            java.util.logging.Logger.getLogger(ImagesProcessingMainMesh.class.getName()).log(Level.SEVERE, null, ex);
        }
        return bi;
    }
    
    public static ArrayList<BufferedImage> coloringImageMultiOption(MultiOptionTextureSubMeshType texture, String idSubTexture){
        ArrayList<BufferedImage> listBi = null;
        try{
            ArrayList<OptionTexture> listMultiOptionTexture = (ArrayList<OptionTexture>) texture.getOptionTexture();
            Iterator<OptionTexture> it2 = listMultiOptionTexture.iterator();
            while(it2.hasNext())
            {
                OptionTexture optionTexture = it2.next();
                if(optionTexture.getIdSubTexture().equals(idSubTexture)){
                    BufferedImage bi = ImageIO.read(ResourceHandler.getResource(optionTexture.getPath()));
                    listBi = new ArrayList<BufferedImage>();
                    listBi.add(bi);
                }
            }
        }
        catch (IOException ex) {
            java.util.logging.Logger.getLogger(ImagesProcessingMainMesh.class.getName()).log(Level.SEVERE, null, ex);
        }
        return listBi;
    }
}