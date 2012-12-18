package imagesProcessing;

import java.awt.Color;
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
    private BufferedImage shadow, image;
    
    public ColoringImage(String imagePath, int color, Objeto estado) throws IOException
    {
        this.imagePath = imagePath;
        this.color = color;        
 
        //Configuration of Logger
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
        image = ImageIO.read(new File(imagePath)); 
        String shadowPath = getShadowPath();
        shadow = ImageIO.read(new File(shadowPath));
        
        Color shadowColor;
        Color baseColor = new Color(color);
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
                //16777215 es el codigo RGB para transparente
                //if (image.getRGB(i, j)!= 16777215) 
                if (image.getRGB(i, j) < 0)
                {
                    image.setRGB(i, j, color);
                }
                if((shadow.getRGB(i,j)&0xFF000000) != 0){
            		shadowColor = new Color(shadow.getRGB(i, j));
            		red = baseColor.getRed() - shadowColor.getRed()*0.5;
            		green = baseColor.getGreen() - shadowColor.getGreen()*0.5;
            		blue = baseColor.getBlue() - shadowColor.getBlue()*0.5;
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
        
        ImageIO.write(image, "png", new File(imageColoredPath));
    }
    
    private void juntaImagen() throws IOException 
    {     
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
        
        //Arreglar chapuza, idea: poner de nombre a los archivos camisetaazul.sombras
        //y coger el substring hasta el punto
        String aux = imagePath.substring(0, length-15);
        aux = aux + "SombrasChico.png";
        
        return aux;
    }
    
    public static void main(String[] args) throws IOException 
    {
        ColoringImage app = new ColoringImage("assets/Textures/Textures Boy/PhotoShop/CamisaLargaSolidoChico.png", -3394561, Objeto.t_shirt);
    } 
}
