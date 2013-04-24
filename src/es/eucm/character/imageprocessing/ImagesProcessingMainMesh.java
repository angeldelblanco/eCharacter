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

import es.eucm.character.data.model.BaseShadowTextureType;
import es.eucm.character.data.model.DoubleTextureType;
import es.eucm.character.data.model.MultiOptionTextureType;
import es.eucm.character.data.model.MultiOptionTextureType.OptionTexture;
import es.eucm.character.data.model.SimpleTextureType;
import es.eucm.character.data.model.TextureType;
import es.eucm.character.loader.ResourceLocator;
import java.awt.Graphics;
import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;

public class ImagesProcessingMainMesh extends ImagesProcessing{
    
    public static BufferedImage process(HashMap<Integer,ArrayList<BufferedImage>> mapTextures){
        ArrayList<BufferedImage> images = new ArrayList<BufferedImage>();
        int numLayers = mapTextures.keySet().size();
        BufferedImage bi = null;
        for(int i=0; i<numLayers;i++){
            ArrayList<BufferedImage> listBufferedImage = mapTextures.get(i);
            //Es para obtener un BufferedImage en concreto para luego poder obtener el ancho y el alto
            bi = listBufferedImage.get(0);
            images.addAll(listBufferedImage);
        }
        
        return pasteBufferedImages(images, bi.getWidth(), bi.getHeight());
    }
    
    private static BufferedImage pasteBufferedImages(ArrayList<BufferedImage> images, int width, int height)
    {
        BufferedImage finalImage = new BufferedImage(width,height,BufferedImage.TYPE_4BYTE_ABGR);
        Graphics g = finalImage.getGraphics();
        for (int i = 0; i < images.size(); i++) 
        {
            BufferedImage aux = images.get(i);
            g.drawImage(aux, 0, 0, null);
        }

        //Rotate 180 grades
        double rotationRequired = Math.toRadians(180);
        double locationX = width / 2;
        double locationY = height / 2;
        AffineTransform tx = AffineTransform.getRotateInstance(rotationRequired, locationX, locationY);
        AffineTransformOp op = new AffineTransformOp(tx, AffineTransformOp.TYPE_BILINEAR);
        // Drawing the rotated image at the required drawing locations
        g.drawImage(op.filter(finalImage, null), 0, 0, null);        
        //Swaping the image
        
        g.drawImage(finalImage, 0, 0, width, height, width, 0, 0, height, null);

        /*try 
        {
            ImageIO.write(finalImage, "png", new File(destinationPath));
        } 
        catch (IOException e) 
        {
            System.out.println("Failed saving image");
        } */
        return finalImage;
    }
    
    public static ArrayList<BufferedImage> createBufferedImage(TextureType texture){
        
        ArrayList<BufferedImage> list = null;
        ArrayList<BufferedImage> images = new ArrayList<BufferedImage>();
        try{
            if(texture instanceof BaseShadowTextureType){
                BaseShadowTextureType baseShadowTexture = ((BaseShadowTextureType)texture);
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
            else if(texture instanceof SimpleTextureType){
                SimpleTextureType simpleTexture = ((SimpleTextureType)texture);
                BufferedImage bi = ImageIO.read(ResourceLocator.getResource(simpleTexture.getPath()));
                list = new ArrayList<BufferedImage>();
                list.add(bi);
                return list;
            }
            else if(texture instanceof DoubleTextureType){
                DoubleTextureType doubleTexture = ((DoubleTextureType)texture);
                BufferedImage bi = ImageIO.read(ResourceLocator.getResource(doubleTexture.getBasePath()));
                BufferedImage bi2 = ImageIO.read(ResourceLocator.getResource(doubleTexture.getDetailsPath()));
                list = new ArrayList<BufferedImage>();
                list.add(bi);
                list.add(bi2);
                return list;
            }
            else if(texture instanceof MultiOptionTextureType){
                //Mirar si funciona esta textura
                BufferedImage bi = null;
                MultiOptionTextureType multiOptionTexture = ((MultiOptionTextureType)texture);
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
        //Ya está creado el array list de bufferedImage. Ahora hay que generarlo
        //return pasteImages(images, bi.getWidth(), bi.getHeight());
    }
}