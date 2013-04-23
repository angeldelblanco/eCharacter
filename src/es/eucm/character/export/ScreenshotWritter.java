package es.eucm.character.export;

import com.jme3.system.JmeSystem;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.concurrent.BlockingQueue;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ScreenshotWritter extends Thread{
    private static final Logger logger = Logger.getLogger(ScreenshotWritter.class.getName());
    
    private ArrayListQueue<ScreenshotData> queue;
    private String exportPath;
    private boolean terminate;
    
    public ScreenshotWritter(ArrayListQueue<ScreenshotData> queue, String exportPath){
        this.queue = queue;
        this.exportPath = exportPath;
        setTerminate(false);
    }

    public synchronized boolean isTerminate() {
        return terminate;
    }

    public synchronized void setTerminate(boolean terminate) {
        this.terminate = terminate;
    }
    
    @Override
    public void run(){
        while(!isTerminate()){
            ScreenshotData data = null;
            synchronized(queue){
                data = queue.poll();
            }
                
            if (data==null){
                try {
                    Thread.sleep(100);
                } catch (InterruptedException ex) {
                    Logger.getLogger(ScreenshotWritter.class.getName()).log(Level.SEVERE, null, ex);
                }
                continue;
            }
            /*File file = new File(exportPath+File.separator+data.getName()+".png");
            OutputStream outStream = null;
            try {
                outStream = new FileOutputStream(file);
                JmeSystem.writeImageFile(outStream, "png", data.getBuff(), data.getWidth(), data.getHeight());
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
            }*/
            writeFile(data);
        }
        int size = queue.size();
        for (int i = 0; i<size; i++){
            ScreenshotData data = queue.poll();
            /*File file;
                file = new File(exportPath+File.separator+data.getName()+".png");

                OutputStream outStream = null;
                try {
                    outStream = new FileOutputStream(file);
                    JmeSystem.writeImageFile(outStream, "png", data.getBuff(), data.getWidth(), data.getHeight());
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
                }*/
            writeFile(data);
        }
        System.out.println("ScreenshotWritter OK");
    }
    
    private void writeFile(ScreenshotData data){
        File file = new File(exportPath+File.separator+data.getName()+".png");
        OutputStream outStream = null;
        try {
            outStream = new FileOutputStream(file);
            JmeSystem.writeImageFile(outStream, "png", data.getBuff(), data.getWidth(), data.getHeight());
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
    }
}
