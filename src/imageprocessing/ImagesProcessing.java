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

import java.awt.Graphics;
import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import javax.imageio.ImageIO;

public class ImagesProcessing
{
    private BufferedImage skin, trousers, tShirt, eyes, shoes;
    /*private BufferedImage socks;
    private BufferedImage clock;
    private BufferedImage tie;
    private BufferedImage beard;*/
    private ArrayList<BufferedImage> images;
    
    public ImagesProcessing(BufferedImage skin, BufferedImage trousers, BufferedImage tShirt, BufferedImage eyes, 
            BufferedImage shoes)
    {
        this.skin = skin;
        this.trousers = trousers;
        this.tShirt = tShirt;
        this.eyes = eyes;
        this.shoes = shoes;
        images = new ArrayList<BufferedImage>();
        images.add(this.skin);
        images.add(this.trousers);
        images.add(this.tShirt);
        images.add(this.eyes);
        images.add(this.shoes);		
    }
    
    /*public ImagesProcessing(String skinPath, String trousersPath, String tShirtPath,
            String eyesPath, String socksPath, String shoesPath, String clockPath, String tiePath, String beardPath)
    {
            images = new ArrayList<BufferedImage>();
            try
            {
                skin = ImageIO.read(new File(skinPath));
                trousers = ImageIO.read(new File(trousersPath));
                tShirt = ImageIO.read(new File(tShirtPath));
                eyes = ImageIO.read(new File(eyesPath));
                shoes = ImageIO.read(new File(shoesPath));
                socks = ImageIO.read(new File(socksPath));
                clock = ImageIO.read(new File(clockPath));
                tie = ImageIO.read(new File(tiePath));
                beard = ImageIO.read(new File(beardPath));
                images.add(skin);
                images.add(socks);
                images.add(trousers);
                images.add(tShirt);
                images.add(eyes);
                images.add(shoes);
                images.add(clock);
                images.add(tie);
                images.add(beard);
            } 
        catch (IOException e) 
        {
            System.out.println("Failed loading file");
        }		
    }*/
    
    public void fusionaImagenes(String destinationPath)
    {
        int w = skin.getWidth();
        int h= skin.getHeight();  
        BufferedImage finalImage = new BufferedImage(w,h,BufferedImage.TYPE_4BYTE_ABGR);
        Graphics g = finalImage.getGraphics();
        for (int i = 0; i < images.size(); i++) 
        {
            BufferedImage aux = images.get(i);
            g.drawImage(aux, 0, 0, null);
        }

        //Rotate 180 grades
        double rotationRequired = Math.toRadians(180);
        double locationX = w / 2;
        double locationY = h / 2;
        AffineTransform tx = AffineTransform.getRotateInstance(rotationRequired, locationX, locationY);
        AffineTransformOp op = new AffineTransformOp(tx, AffineTransformOp.TYPE_BILINEAR);
        // Drawing the rotated image at the required drawing locations
        g.drawImage(op.filter(finalImage, null), 0, 0, null);        
        //Swaping the image
        g.drawImage(finalImage, 0, 0, w, h, w, 0, 0, h, null);

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