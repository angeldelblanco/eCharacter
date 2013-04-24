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
import es.eucm.character.data.model.SimpleTextureSubMeshType;
import es.eucm.character.data.model.SubMeshType.SubMeshTexture;
import es.eucm.character.loader.ResourceLocator;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;

public class ImagesProcessingSubMesh extends ImagesProcessing{
    public static ArrayList<BufferedImage> createBufferedImage(SubMeshTexture texture){
        
        ArrayList<BufferedImage> list = null;
        ArrayList<BufferedImage> images = new ArrayList<BufferedImage>();
        try{
            if(texture.getBaseShadowTextureSubMesh() != null){
                BaseShadowTextureSubMeshType baseShadowTexture = texture.getBaseShadowTextureSubMesh();
                BufferedImage bi = ImageIO.read(ResourceLocator.getResource(baseShadowTexture.getPath()));
                images.add(bi);
                if(baseShadowTexture.getShadowPath() != null){
                    images.add(ImageIO.read(ResourceLocator.getResource(baseShadowTexture.getShadowPath())));
                }
                BufferedImage finalBi = pasteImages(images, bi.getWidth(), bi.getHeight());
                list = new ArrayList<BufferedImage>();
                list.add(finalBi);
                return list;
            }
            else if(texture.getSimpleTextureSubMesh() != null){
                SimpleTextureSubMeshType simpleTexture = texture.getSimpleTextureSubMesh();
                BufferedImage bi = ImageIO.read(ResourceLocator.getResource(simpleTexture.getPath()));
                list = new ArrayList<BufferedImage>();
                list.add(bi);
                return list;
            }
            else if(texture.getDoubleTextureSubMesh() != null){
                DoubleTextureSubMeshType doubleTexture = texture.getDoubleTextureSubMesh();
                BufferedImage bi = ImageIO.read(ResourceLocator.getResource(doubleTexture.getBasePath()));
                BufferedImage bi2 = ImageIO.read(ResourceLocator.getResource(doubleTexture.getDetailsPath()));
                list = new ArrayList<BufferedImage>();
                list.add(bi);
                list.add(bi2);
                return list;
            }
            else if(texture.getMultiOptionTextureSubMesh() != null){
                //Mirar si funciona esta textura
                BufferedImage bi = null;
                MultiOptionTextureSubMeshType multiOptionTexture = texture.getMultiOptionTextureSubMesh();
                ArrayList<OptionTexture> listTextures = (ArrayList<OptionTexture>) multiOptionTexture.getOptionTexture();
                Iterator<OptionTexture> it = listTextures.iterator();
                boolean isDefault = false;               
                OptionTexture optionTexture = null;               
                while(!isDefault && it.hasNext()){
                    optionTexture = it.next();
                    if (optionTexture.isDefault()){
                        isDefault = true;
                        bi = ImageIO.read(ResourceLocator.getResource(optionTexture.getPath()));
                    }
                }
                if (!isDefault){
                    //No ha encontrado ninguna subtextura en dafault
                    bi = ImageIO.read(ResourceLocator.getResource(optionTexture.getPath()));
                }
                list = new ArrayList<BufferedImage>();
                list.add(bi);
                return list;
            }
        }
        catch (IOException ex) {
            Logger.getLogger(ImagesProcessingMainMesh.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }
}
