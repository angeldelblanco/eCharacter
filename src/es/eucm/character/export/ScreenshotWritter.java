package es.eucm.character.export;

import com.jme3.system.JmeSystem;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ScreenshotWritter extends Thread{
    private static final Logger logger = Logger.getLogger(ScreenshotWritter.class.getName());

    private ArrayList<ByteBuffer> list;
    private String nameAnimation;
    private int width;
    private int height;
    
    public ScreenshotWritter(ArrayList<ByteBuffer> list, String nameAnimation, int width, int height){
        this.list = list;
        this.nameAnimation = nameAnimation;
        this.width = width;
        this.height = height;
    }
    @Override
    public void run(){
        int cont = 1;
        System.out.println("Tamaño del list: "+list.size());
        Iterator<ByteBuffer> it = list.iterator();
        while(it.hasNext()){
            ByteBuffer byteBuffer = it.next();
            File file;
            file = new File("assets"+File.separator+"Textures"+File.separator+"screenshots"+File.separator+nameAnimation+cont+".png");

            OutputStream outStream = null;
            try {
                outStream = new FileOutputStream(file);
                JmeSystem.writeImageFile(outStream, "png", byteBuffer, width, height);
            } catch (IOException ex) {
                logger.log(Level.SEVERE, "Error while saving screenshot", ex);
            } finally {
                if (outStream != null){
                    try {
                        outStream.close();
                    } catch (IOException ex) {
                        logger.log(Level.SEVERE, "Error while saving screenshot", ex);
                    }
                }
            }
            cont++;
        }
        System.out.println("ScreenshotWritter OK");
    }
}
