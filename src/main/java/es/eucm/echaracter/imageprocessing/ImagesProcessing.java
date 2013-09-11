/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package es.eucm.echaracter.imageprocessing;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

public class ImagesProcessing {
    
    public static BufferedImage pasteImages(ArrayList<BufferedImage> images, int width, int height){
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
