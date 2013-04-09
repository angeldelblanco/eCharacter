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
import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;
import javax.imageio.ImageIO;
import loader.ResourceHandler;
import org.apache.log4j.Logger;
import org.apache.log4j.xml.DOMConfigurator;

public class ColoringImage 
{
    private final static Logger logger = Logger.getLogger(ColoringImage.class);
    private long tiempoInicio, totalTiempo;         
    
    
    public static BufferedImage coloringImage(TextureType texture, Color color){
        BufferedImage bi = null;
        try{
            if(texture instanceof BaseShadowTextureType){
                BaseShadowTextureType baseShadowTexture = ((BaseShadowTextureType)texture);
                BufferedImage biBase = ImageIO.read(ResourceHandler.getResource(baseShadowTexture.getPath()));
                BufferedImage biShadow = ImageIO.read(ResourceHandler.getResource(baseShadowTexture.getShadowPath()));
                bi = coloringImageBaseShadow(biBase, biShadow, color);
            }
            //El simple texture aqui no tiene sentido
            
            //HAY QUE VER COMO HACER PARA SABER SI SE ESTÁ CAMBIANDO EL COLOR AL BASE O AL DETAIL
            else if(texture instanceof DoubleTextureType){
                DoubleTextureType doubleTexture = ((DoubleTextureType)texture);
                BufferedImage biBase = ImageIO.read(ResourceHandler.getResource(doubleTexture.getBasePath()));
                BufferedImage biDetails = ImageIO.read(ResourceHandler.getResource(doubleTexture.getDetailsPath()));
                //Difenciar el función de a qué le estemos cambiando el color, pasarle el base o el details
                /* if (cambiarBase){
                       bi = coloringImageDouble(biBase, color);
                       bi = pasteImage(bi, biDetails);
                   }
                   else{
                       bi = coloringImageDouble(biDetails, color);
                       bi = pasteImage(biBase, bi);
                   }*/
                
                bi = coloringImageDouble(biBase, color);
                bi = pasteImage(bi, biDetails);
            }
        }
        catch (IOException ex) {
            java.util.logging.Logger.getLogger(ImagesProcessing.class.getName()).log(Level.SEVERE, null, ex);
        }
        return bi;
    }
    /*public ColoringImage(String imagePath, String shadowPath, Color color) throws IOException
    {
        this.imagePath = imagePath;
        this.shadowPath = shadowPath;
        this.color = color;       
 
        //Configuration of Logger
        DOMConfigurator.configure("assets/Log/configuration_log.xml");
        //BasicConfigurator.configure();
        
        switch(estado) {
            case t_shirt:
                imageColoredPath = "assets/Textures/TShirtSombreada.png";
                destinationPath = "assets/Textures/TShirtFinal.png";
            break;
            case trouser:
                imageColoredPath = "assets/Textures/TrouserSombreada.png";
                destinationPath = "assets/Textures/TrouserFinal.png";
            break;
            case shoes:
                imageColoredPath = "assets/Textures/ShoesSombreada.png";
                destinationPath = "assets/Textures/ShoesFinal.png";
            break;
        }
    }    */    

    /*public BufferedImage coloringImageBaseShadow() throws IOException 
    {
        tiempoInicio = System.currentTimeMillis();
        
        image = ImageIO.read(new File(imagePath)); 
        if (shadowPath != null) {
            shadow = ImageIO.read(new File(shadowPath));
        }
        
        Color shadowColor;
        double red;
        double green;
        double blue;
        int alpha;
        
        int w = image.getWidth();
        int h= image.getHeight();  
        
        for (int i=0;i<w;i++)
        {
            for (int j=0;j<h;j++)
            {  
                //if the pixel isn't transparent
                if (image.getRGB(i, j) < 0)
                {
                    image.setRGB(i, j, color.getRGB());
                }
                if (shadow != null){
                    if((shadow.getRGB(i,j)&0xFF000000) != 0){
                            shadowColor = new Color(shadow.getRGB(i, j));
                            if(!((shadowColor.getRed()<85)&&(shadowColor.getGreen()<85)&&(shadowColor.getBlue()<85))){
                                                    red = color.getRed() - shadowColor.getRed()*0.5;
                                                    green = color.getGreen() - shadowColor.getGreen()*0.5;
                                                    blue = color.getBlue() - shadowColor.getBlue()*0.5;
                            }
                            else{
                                    red=85;green=85;blue=85;
                            }
                            //Arreglar con maximos y minimos
                            if(red < 0) red = 0;
                            if(red > 255) red = 255;
                            if(green < 0) green = 0;
                            if(green > 255) green = 255;
                            if(blue < 0) blue = 0;
                            if(blue > 255) blue = 255;
                            alpha = ((shadow.getRGB(i,j)&0xFF000000));
                            //Quitar el new Color, trabajar con ints
                            shadow.setRGB(i, j, new Color((int)red,(int)green,(int)blue).getRGB() + alpha);
                    }
                }
            }
        }
        totalTiempo = System.currentTimeMillis() - tiempoInicio;
        logger.info("El tiempo de coloringImage() es :"  + totalTiempo + " miliseg");
        
        //Se escribe para probar el resultado. Luego hay que quitarlo.
        //ImageIO.write(image, "png", new File(imageColoredPath));
        
        BufferedImage finalImage = pasteImage();
        return finalImage;
    }*/
    
    private static BufferedImage coloringImageBaseShadow(BufferedImage imageOriginal, BufferedImage shadowOriginal, Color color) throws IOException 
    {
        BufferedImage image = imageOriginal;
        BufferedImage shadow = shadowOriginal;
        Color shadowColor;
        double red;
        double green;
        double blue;
        int alpha;
        
        int w = image.getWidth();
        int h= image.getHeight();  
        
        for (int i=0;i<w;i++)
        {
            for (int j=0;j<h;j++)
            {  
                //if the pixel isn't transparent
                if (image.getRGB(i, j) < 0)
                {
                    image.setRGB(i, j, color.getRGB());
                }
                if (shadow != null){
                    if((shadow.getRGB(i,j)&0xFF000000) != 0){
                            shadowColor = new Color(shadow.getRGB(i, j));
                            if(!((shadowColor.getRed()<85)&&(shadowColor.getGreen()<85)&&(shadowColor.getBlue()<85))){
                                                    red = color.getRed() - shadowColor.getRed()*0.5;
                                                    green = color.getGreen() - shadowColor.getGreen()*0.5;
                                                    blue = color.getBlue() - shadowColor.getBlue()*0.5;
                            }
                            else{
                                    red=85;green=85;blue=85;
                            }
                            //Arreglar con maximos y minimos
                            if(red < 0) red = 0;
                            if(red > 255) red = 255;
                            if(green < 0) green = 0;
                            if(green > 255) green = 255;
                            if(blue < 0) blue = 0;
                            if(blue > 255) blue = 255;
                            alpha = ((shadow.getRGB(i,j)&0xFF000000));
                            //Quitar el new Color, trabajar con ints
                            shadow.setRGB(i, j, new Color((int)red,(int)green,(int)blue).getRGB() + alpha);
                    }
                }
            }
        }
        //Se escribe para probar el resultado. Luego hay que quitarlo.
        //ImageIO.write(image, "png", new File(imageColoredPath));
        
        BufferedImage finalImage = pasteImage(image, shadow);
        return finalImage;
    }
    
    private static BufferedImage coloringImageDouble(BufferedImage imageOriginal, Color color) throws IOException 
    {
        BufferedImage image = imageOriginal;
        
        int w = image.getWidth();
        int h= image.getHeight();  
        
        for (int i=0;i<w;i++)
        {
            for (int j=0;j<h;j++)
            {  
                //if the pixel isn't transparent
                if (image.getRGB(i, j) < 0)
                {
                    image.setRGB(i, j, color.getRGB());
                }
            }
        }
        //Se escribe para probar el resultado. Luego hay que quitarlo.
        //ImageIO.write(image, "png", new File(imageColoredPath));
        return image;
    }
    
    private static BufferedImage pasteImage(BufferedImage image, BufferedImage shadow){
        int w = image.getWidth();
        int h= image.getHeight();
        
        BufferedImage finalImage = new BufferedImage(w,h,BufferedImage.TYPE_4BYTE_ABGR);
        Graphics g = finalImage.getGraphics();
        
        if (shadow == null){
            g.drawImage(image, 0, 0, null);
        }
        else{
            ArrayList<BufferedImage> images = new ArrayList<BufferedImage>();
            images.add(image);
            images.add(shadow);

            for (int i = 0; i < images.size(); i++) 
            {
                BufferedImage aux = images.get(i);
                g.drawImage(aux, 0, 0, null);
            }           
        }
        //Se escribe para probar el resultado. Luego hay que quitarlo
        //ImageIO.write(finalImage, "png", new File(destinationPath));
        return finalImage;
    }
    
    /*private BufferedImage pasteImage() throws IOException 
    {     
        tiempoInicio = System.currentTimeMillis();
        
        int w = image.getWidth();
        int h= image.getHeight();
        
        BufferedImage finalImage = new BufferedImage(w,h,BufferedImage.TYPE_4BYTE_ABGR);
        Graphics g = finalImage.getGraphics();
        
        if (shadow == null){
            g.drawImage(image, 0, 0, null);
        }
        else{
            ArrayList<BufferedImage> images = new ArrayList<BufferedImage>();
            images.add(image);
            images.add(shadow);

            for (int i = 0; i < images.size(); i++) 
            {
                BufferedImage aux = images.get(i);
                g.drawImage(aux, 0, 0, null);
            }           
        }
        //Se escribe para probar el resultado. Luego hay que quitarlo
        //ImageIO.write(finalImage, "png", new File(destinationPath));
        totalTiempo = System.currentTimeMillis() - tiempoInicio;
        logger.info("El tiempo de pasteImage() es : " + totalTiempo + " miliseg");
        return finalImage;
    }*/
    
    /*public static void main(String[] args) throws IOException 
    {
        ColoringImage app = new ColoringImage("assets/Textures/Textures Boy/ZapatosSolidoBoy.png", 
                "assets/Textures/Textures Boy/ZapatosSombrasBoy.png", new Color(0, 255, 0), TypeObject.shoes);
    } */
}
