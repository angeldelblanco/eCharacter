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
import java.util.Iterator;
import javax.imageio.ImageIO;
import loader.ResourceHandler;

public class ImagesProcessing
{
    private ResourceHandler resourceHandler;
    private ArrayList<BufferedImage> images;
    private HashMap<Integer,ArrayList<TextureType>> mapTextures;
    private int width;
    private int height;
    
    public ImagesProcessing(HashMap<Integer,ArrayList<TextureType>> mapTextures){
        this.mapTextures = mapTextures;
        this.images = new ArrayList<BufferedImage>();
        this.resourceHandler = new ResourceHandler();
    }
    
    public void process(String destinationPath){
        int numLayers = mapTextures.keySet().size();
        for(int i=0; i<numLayers;i++){
            ArrayList<TextureType> listTexturesLayer = mapTextures.get(i);
            addToBufferedImage(listTexturesLayer);
        }
        pasteImages(destinationPath);
    }
    
    private void addToBufferedImage(ArrayList<TextureType> listTexturesLayer){
        Iterator<TextureType> it = listTexturesLayer.iterator();
        while(it.hasNext()){
            TextureType texture = it.next();
            try{
                if(texture instanceof BaseShadowTextureType){
                    BaseShadowTextureType baseShadowTexture = ((BaseShadowTextureType)texture);
                    images.add(ImageIO.read(resourceHandler.getResource(baseShadowTexture.getPath())));
                    if(baseShadowTexture.getShadowPath() != null){
                        images.add(ImageIO.read(resourceHandler.getResource(baseShadowTexture.getShadowPath())));
                    }
                }
                else if(texture instanceof SimpleTextureType){
                    SimpleTextureType simpleTexture = ((SimpleTextureType)texture);
                }
                else if(texture instanceof DoubleTextureType){
                    DoubleTextureType doubleTexture = ((DoubleTextureType)texture);
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
                }
            }
            
        }
    }
    
    private void pasteImages(String destinationPath)
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
    }
}