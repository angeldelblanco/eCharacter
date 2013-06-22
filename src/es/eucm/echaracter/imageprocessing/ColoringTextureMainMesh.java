/*******************************************************************************
 * <eCharacter> is a research project of the <e-UCM>
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
 *      <eCharacter> is free software: you can 
 *      redistribute it and/or modify it under the terms of the GNU Lesser 
 *      General Public License as published by the Free Software Foundation, 
 *      either version 3 of the License, or (at your option) any later version.
 *  
 *      <eCharacter> is distributed in the hope that it 
 *      will be useful, but WITHOUT ANY WARRANTY; without even the implied 
 *      warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  
 *      See the GNU Lesser General Public License for more details.
 *  
 *      You should have received a copy of the GNU Lesser General Public License
 *      along with <eCharacter>. If not, 
 *      see <http://www.gnu.org/licenses/>.
 ******************************************************************************/

package es.eucm.echaracter.imageprocessing;

import es.eucm.echaracter.data.model.BaseShadowTextureType;
import es.eucm.echaracter.data.model.DoubleTextureType;
import es.eucm.echaracter.data.model.MultiOptionTextureType;
import es.eucm.echaracter.data.model.MultiOptionTextureType.OptionTexture;
import es.eucm.echaracter.data.model.TextureType;
import es.eucm.echaracter.loader.ResourceLocator;
import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.logging.Level;
import javax.imageio.ImageIO;
import org.apache.log4j.Logger;

public class ColoringTextureMainMesh extends ColoringImage{
    private final static Logger logger = Logger.getLogger(ColoringTextureMainMesh.class);
    private long tiempoInicio, totalTiempo;         
    
    
    public static ArrayList<BufferedImage> coloringImageBaseShadow(TextureType texture, Color color){
        ArrayList<BufferedImage> listBi = null;
        try{
            if(texture instanceof BaseShadowTextureType){
                BaseShadowTextureType baseShadowTexture = ((BaseShadowTextureType)texture);
                BufferedImage biBase = ImageIO.read(ResourceLocator.getResource(baseShadowTexture.getPath()));
                BufferedImage biShadow = null;
                if(baseShadowTexture.getShadowPath() != null){
                    biShadow = ImageIO.read(ResourceLocator.getResource(baseShadowTexture.getShadowPath()));
                }
                listBi = new ArrayList<BufferedImage>();
                listBi.add(coloringImageBaseShadow(biBase, biShadow, color));
            }
        }
        catch (IOException ex) {
            java.util.logging.Logger.getLogger(ImagesProcessingMainMesh.class.getName()).log(Level.SEVERE, null, ex);
        }
        return listBi;
    }
    
    public static BufferedImage coloringImageDoubleTextureDetails(TextureType texture, Color color){
        BufferedImage bi = null;
        try{
            if(texture instanceof DoubleTextureType){
                DoubleTextureType doubleTexture = ((DoubleTextureType)texture);
                BufferedImage biDetails = ImageIO.read(ResourceLocator.getResource(doubleTexture.getDetailsPath()));
                bi = coloringImageDouble(biDetails, color);
            }
        }
        catch (IOException ex) {
            java.util.logging.Logger.getLogger(ImagesProcessingMainMesh.class.getName()).log(Level.SEVERE, null, ex);
        }
        return bi;
    }
    
    public static BufferedImage coloringImageDoubleTextureBase(TextureType texture, Color color){
        BufferedImage bi = null;
        try{
            if(texture instanceof DoubleTextureType){
                DoubleTextureType doubleTexture = ((DoubleTextureType)texture);
                BufferedImage biBase = ImageIO.read(ResourceLocator.getResource(doubleTexture.getBasePath()));
                bi = coloringImageDouble(biBase, color);
            }
        }
        catch (IOException ex) {
            java.util.logging.Logger.getLogger(ImagesProcessingMainMesh.class.getName()).log(Level.SEVERE, null, ex);
        }
        return bi;
    }
    
    public static ArrayList<BufferedImage> coloringImageMultiOption(TextureType texture, String idSubTexture){
        ArrayList<BufferedImage> listBi = null;
        try{
            if(texture instanceof MultiOptionTextureType){
                MultiOptionTextureType multiOptionTexture = ((MultiOptionTextureType)texture);
                ArrayList<OptionTexture> listMultiOptionTexture = (ArrayList<OptionTexture>) multiOptionTexture.getOptionTexture();
                Iterator<OptionTexture> it2 = listMultiOptionTexture.iterator();
                while(it2.hasNext())
                {
                    OptionTexture optionTexture = it2.next();
                    if(optionTexture.getIdSubTexture().equals(idSubTexture)){
                        BufferedImage bi = ImageIO.read(ResourceLocator.getResource(optionTexture.getPath()));
                        listBi = new ArrayList<BufferedImage>();
                        listBi.add(bi);
                    }
                }
            }
        }
        catch (IOException ex) {
            java.util.logging.Logger.getLogger(ImagesProcessingMainMesh.class.getName()).log(Level.SEVERE, null, ex);
        }
        return listBi;
    }
}
