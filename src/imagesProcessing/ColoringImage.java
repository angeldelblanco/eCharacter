package imagesProcessing;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import javax.imageio.ImageIO;

public class ColoringImage 
{
    private Color color;
    private String imagePath, destinationPath;
    
    public ColoringImage(String imagePath, Color color) throws IOException
    {
        this.imagePath = imagePath;
        this.color = color;
        
        destinationPath = "assets/Textures/Textures Boy/Sombreada.png";
        coloringImage();
        juntaImagen();
    }        

    private void coloringImage() throws IOException 
    {
        BufferedImage image = ImageIO.read(new File(imagePath));
        
        int w = image.getWidth();
        int h= image.getHeight();  
        
        for (int i=0;i<w;i++)
        {
            for (int j=0;j<h;j++)
            {  
                //16777215 es el codigo RGB para transparente
                if (image.getRGB(i, j)!= 16777215) 
                {
                    image.setRGB(i, j, color.getRGB());
                }
            }
        }
        
        ImageIO.write(image, "png", new File(destinationPath));
    }
    
    private void juntaImagen() throws IOException 
    {
        BufferedImage image = ImageIO.read(new File(destinationPath));
        BufferedImage shadow = ImageIO.read(new File("assets/Textures/Textures Boy/PhotoShop/CamisaCortaSombrasChico.png"));
        
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
            ImageIO.write(finalImage, "png", new File("assets/Textures/Textures Boy/Final.png"));
        } 
        catch (IOException e) 
        {
            System.out.println("Failed saving image");
        }  
    }
    
    public static void main(String[] args) throws IOException 
    {
        ColoringImage app = new ColoringImage("assets/Textures/Textures Boy/PhotoShop/CamisaCortaSolidoChico.png", new Color(-3394561));
    } 
}
