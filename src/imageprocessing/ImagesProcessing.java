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

package imageprocessing;

import data.model.BaseShadowTextureType;
import data.model.DoubleTextureType;
import data.model.MultiOptionTextureType;
import data.model.SimpleTextureType;
import data.model.TextureType;
import java.awt.Graphics;
import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import loader.ResourceHandler;

public class ImagesProcessing
{
    private ArrayList<BufferedImage> images;
    //private HashMap<Integer,ArrayList<TextureType>> mapTextures;
    private HashMap<Integer,ArrayList<BufferedImage>> mapTextures;
    private int width;
    private int height;
    
    /*public ImagesProcessing(HashMap<Integer,ArrayList<TextureType>> mapTextures){
        this.mapTextures = mapTextures;
        this.images = new ArrayList<BufferedImage>();
    }*/
    
    public ImagesProcessing(HashMap<Integer,ArrayList<BufferedImage>> mapTextures){
        this.mapTextures = mapTextures;
        this.images = new ArrayList<BufferedImage>();
    }
    
    /*public BufferedImage process(String destinationPath){
        int numLayers = mapTextures.keySet().size();
        for(int i=0; i<numLayers;i++){
            ArrayList<TextureType> listTexturesLayer = mapTextures.get(i);
            addToBufferedImage(listTexturesLayer);
        }
        return pasteImages(destinationPath);
    }*/
    
    public BufferedImage process(String destinationPath){
        int numLayers = mapTextures.keySet().size();
        BufferedImage bi = null;
        for(int i=0; i<numLayers;i++){
            ArrayList<BufferedImage> listBufferedImage = mapTextures.get(i);
            //Es para obtener un BufferedImage en concreto para luego poder obtener el ancho y el alto
            bi = listBufferedImage.get(0);
            addToBufferedImage(listBufferedImage);
        }
        width = bi.getWidth();
        height = bi.getHeight();
        
        return pasteBufferedImages(destinationPath);
    }
    
    /*private void addToBufferedImage(ArrayList<TextureType> listTexturesLayer){
        Iterator<TextureType> it = listTexturesLayer.iterator();
        BufferedImage bi = null;
        while(it.hasNext()){
            TextureType texture = it.next();
            try{
                if(texture instanceof BaseShadowTextureType){
                    BaseShadowTextureType baseShadowTexture = ((BaseShadowTextureType)texture);
                    bi = ImageIO.read(ResourceHandler.getResource(baseShadowTexture.getPath()));
                    images.add(bi);
                    if(baseShadowTexture.getShadowPath() != null){
                        images.add(ImageIO.read(ResourceHandler.getResource(baseShadowTexture.getShadowPath())));
                    }
                }
                else if(texture instanceof SimpleTextureType){
                    SimpleTextureType simpleTexture = ((SimpleTextureType)texture);
                    bi = ImageIO.read(ResourceHandler.getResource(simpleTexture.getPath()));
                    images.add(bi);
                    images.add(ImageIO.read(ResourceHandler.getResource(simpleTexture.getPath())));
                }
                else if(texture instanceof DoubleTextureType){
                    DoubleTextureType doubleTexture = ((DoubleTextureType)texture);
                    bi = ImageIO.read(ResourceHandler.getResource(doubleTexture.getBasePath()));
                    images.add(bi);
                    images.add(ImageIO.read(ResourceHandler.getResource(doubleTexture.getDetailsPath())));
                }
                else if(texture instanceof MultiOptionTextureType){
                    MultiOptionTextureType multiOptionTexture = ((MultiOptionTextureType)texture);
                    //Mirar como hacer esta textura
                }
            }
            catch (IOException ex) {
                Logger.getLogger(ImagesProcessing.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        width = bi.getWidth();
        height = bi.getHeight();
    }*/
    
    private void addToBufferedImage(ArrayList<BufferedImage> listBufferedImage){
        images.addAll(listBufferedImage);
    }
    
    private BufferedImage pasteBufferedImages(String destinationPath)
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

        try 
        {
            ImageIO.write(finalImage, "png", new File(destinationPath));
        } 
        catch (IOException e) 
        {
            System.out.println("Failed saving image");
        } 
        return finalImage;
    }
    
    public static BufferedImage createBufferedImage(TextureType texture){
        
        BufferedImage bi = null;
        int width;
        int height;
        ArrayList<BufferedImage> images = new ArrayList<BufferedImage>();
        try{
            if(texture instanceof BaseShadowTextureType){
                BaseShadowTextureType baseShadowTexture = ((BaseShadowTextureType)texture);
                bi = ImageIO.read(ResourceHandler.getResource(baseShadowTexture.getPath()));
                images.add(bi);
                if(baseShadowTexture.getShadowPath() != null){
                    images.add(ImageIO.read(ResourceHandler.getResource(baseShadowTexture.getShadowPath())));
                }
            }
            else if(texture instanceof SimpleTextureType){
                SimpleTextureType simpleTexture = ((SimpleTextureType)texture);
                bi = ImageIO.read(ResourceHandler.getResource(simpleTexture.getPath()));
                images.add(bi);
                images.add(ImageIO.read(ResourceHandler.getResource(simpleTexture.getPath())));
            }
            else if(texture instanceof DoubleTextureType){
                DoubleTextureType doubleTexture = ((DoubleTextureType)texture);
                bi = ImageIO.read(ResourceHandler.getResource(doubleTexture.getBasePath()));
                images.add(bi);
                images.add(ImageIO.read(ResourceHandler.getResource(doubleTexture.getDetailsPath())));
            }
            else if(texture instanceof MultiOptionTextureType){
                MultiOptionTextureType multiOptionTexture = ((MultiOptionTextureType)texture);
                //Mirar como hacer esta textura
            }
        }
        catch (IOException ex) {
            Logger.getLogger(ImagesProcessing.class.getName()).log(Level.SEVERE, null, ex);
        }
        //Ya está creado el array list de bufferedImage. Ahora hay que generarlo
        return pasteImages(images, bi.getWidth(), bi.getHeight());
    }
    
    private static BufferedImage pasteImages(ArrayList<BufferedImage> images, int width, int height){
        BufferedImage finalImage = new BufferedImage(width,height,BufferedImage.TYPE_4BYTE_ABGR);
        Graphics g = finalImage.getGraphics();
        for (int i = 0; i < images.size(); i++) 
        {
            BufferedImage aux = images.get(i);
            g.drawImage(aux, 0, 0, null);
        }
 
        return finalImage;
    }
}