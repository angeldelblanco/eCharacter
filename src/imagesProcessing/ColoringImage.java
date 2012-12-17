package imagesProcessing;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import javax.imageio.ImageIO;
import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.Logger;
import tipos.Objeto;

public class ColoringImage 
{
    private int color;
    private String imagePath, imageColoredPath, destinationPath;
    private final static Logger logger = Logger.getLogger(ColoringImage.class);
    private long tiempoInicio, totalTiempo;               
    
    public ColoringImage(String imagePath, int color, Objeto estado) throws IOException
    {
        this.imagePath = imagePath;
        this.color = color;
        
        BasicConfigurator.configure();
        
        switch(estado) {
            case t_shirt:
                imageColoredPath = "assets/Textures/Textures Boy/TShirtSombreada.png";
                destinationPath = "assets/Textures/Textures Boy/TShirtFinal.png";
            break;
            case trouser:
                imageColoredPath = "assets/Textures/Textures Boy/TrouserSombreada.png";
                destinationPath = "assets/Textures/Textures Boy/TrouserFinal.png";
            break;
        }
        tiempoInicio = System.currentTimeMillis();
        coloringImage();
        totalTiempo = System.currentTimeMillis() - tiempoInicio;
        logger.info("El tiempo de coloringImage() es :"  + totalTiempo + " miliseg");
        
        tiempoInicio = System.currentTimeMillis();
        juntaImagen();
        totalTiempo = System.currentTimeMillis() - tiempoInicio;
        logger.info("El tiempo de juntaImagen() es : " + totalTiempo + " miliseg");
    }        

    private void coloringImage() throws IOException 
    {
        BufferedImage image = ImageIO.read(new File(imagePath)); 
        String shadowPath = getShadowPath();
        BufferedImage aux = ImageIO.read(new File(shadowPath)); 
        
        int w = image.getWidth();
        int h= image.getHeight();  
        
        for (int i=0;i<w;i++)
        {
            for (int j=0;j<h;j++)
            {  
                //16777215 es el codigo RGB para transparente
                //if (image.getRGB(i, j)!= 16777215) 
                if (image.getRGB(i, j) < 0)
                {
                    image.setRGB(i, j, color);
                }
            }
        }
        
        ImageIO.write(image, "png", new File(imageColoredPath));
    }
    
    private void juntaImagen() throws IOException 
    {
        BufferedImage image = ImageIO.read(new File(imageColoredPath));
        
        String shadowPath = getShadowPath();
        System.out.println(shadowPath);
        
        BufferedImage shadow = ImageIO.read(new File(shadowPath));
        
        int w = image.getWidth();
        int h= image.getHeight();
        
        ArrayList<BufferedImage> images = new ArrayList<BufferedImage>();
        images.add(image);
        images.add(shadow);
        
        BufferedImage finalImage = new BufferedImage(w,h,BufferedImage.TYPE_4BYTE_ABGR);
        Graphics g = finalImage.getGraphics();
        for (int i = 0; i < images.size(); i++) 
        {
            BufferedImage aux = images.get(i);
            g.drawImage(aux, 0, 0, null);
        }
        try 
        {
            ImageIO.write(finalImage, "png", new File(destinationPath));
        } 
        catch (IOException e) 
        {
            System.out.println("Failed saving image");
        }  
    }
    
    private String getShadowPath()
    {
        int length = imagePath.length();
        
        String aux = imagePath.substring(0, length-15);
        aux = aux + "SombrasChico.png";
        
        return aux;
    }
    
    public static void main(String[] args) throws IOException 
    {
        ColoringImage app = new ColoringImage("assets/Textures/Textures Boy/PhotoShop/CamisetaLargaSolidoChico.png", -3394561, Objeto.t_shirt);
    } 
}
