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

package es.eucm.character.export;

import com.jme3.animation.AnimChannel;
import com.jme3.animation.LoopMode;
import com.jme3.app.state.ScreenshotAppState;
import com.jme3.niftygui.NiftyJmeDisplay;
import com.jme3.renderer.ViewPort;
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

public class ScreenshotThread extends Thread{
    private static final Logger logger = Logger.getLogger(ScreenshotThread.class.getName());
    
    static final int quality = 24;
    
    private ScreenshotMyAppState screenShotState;
    private AnimChannel channel;
    private ViewPort guiViewPort;
    private NiftyJmeDisplay niftyDisplay;    
    private ArrayList<String> namesAnimations;
    private float stepAnimationTime;
    
    private ArrayList<String> imagesNames;
    
    public ScreenshotThread(ScreenshotMyAppState screeShotState,AnimChannel channel,
            ViewPort guiViewPort,NiftyJmeDisplay niftyDisplay,ArrayList<String> namesAnimations)
    {
        super();
        this.screenShotState = screeShotState;
        this.channel = channel;
        this.guiViewPort = guiViewPort;
        this.niftyDisplay = niftyDisplay;
        this.namesAnimations = namesAnimations;        
    }
    
    @Override
    public void run()
    {
        try 
        {
            String dirScreenshots = "assets/Textures/screenshots";
            int cont = 1;
            Iterator<String> it = namesAnimations.iterator();
            GenerateAnimation generateAnimation = new GenerateAnimation();
            generateAnimation.cleanDirectory(dirScreenshots);
            while(it.hasNext()){
                String nameAnimation = it.next();
                screenShotState.setFilePath(dirScreenshots+"/"+nameAnimation);
                imagesNames = new ArrayList<String>();                
                channel.setAnim(nameAnimation);
                channel.setLoopMode(LoopMode.DontLoop);
                //Redondeo
                int numScreenShots = Math.round(channel.getAnimMaxTime() * quality); 
                stepAnimationTime = (channel.getAnimMaxTime() * 1000 / numScreenShots);
                //sleep(100);
                float time = 0.0f;
                for(int j= 1 ; j<=numScreenShots; j++){
                    System.out.println("Captura antes "+j+" con tiempo "+System.currentTimeMillis());
                    time = time+(stepAnimationTime/1000);
                    channel.setTime(time);
                    
                    //long tAntes = System.currentTimeMillis();
                    synchronized (this){
                        screenShotState.takeScreenshot(this);
                        wait();
                    }
                    //long tDespues = System.currentTimeMillis();
                    //float desfaseSegundos = (tDespues - tAntes)/1000;
                    
                    
                    System.out.println("Captura despues "+j+" con tiempo "+System.currentTimeMillis());
                    cont++;
                    sleep((long)stepAnimationTime);
                    //time = channel.getTime();
                    //time = channel.getTime()-desfaseSegundos;
                }
                ArrayList<ByteBuffer> list = screenShotState.getListByteBuffer();
                //synchronized (this){
                    this.writeFiles(list, screenShotState.getWidth(), screenShotState.getHeight());
                    //this.wait();
                //}
                
                
                /*synchronized (this){
                    screenShotState.writeFiles();
                    wait();
                }*/
                System.out.println("ENTROOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOO");
                //generateAnimation.createAnimation(dirScreenshots, nameAnimation, imagesNames);
            }
            //generateAnimation.saveZIP("assets/Textures/Screenshots.zip", dirScreenshots);
            
            it = namesAnimations.iterator();
            channel.setAnim(it.next());
            channel.setLoopMode(LoopMode.Loop);
            guiViewPort.addProcessor(niftyDisplay);            
         } 
        catch (InterruptedException ex) 
        {
                Logger.getLogger(ScreenshotThread.class.getName()).log(Level.SEVERE, null, ex);
        }      
    }
    
    private void writeFiles(ArrayList<ByteBuffer> list, int width, int height){
        int cont = 1;
        System.out.println("Tamaño del list: "+list.size());
        Iterator<ByteBuffer> it = list.iterator();
        while(it.hasNext()){
            ByteBuffer byteBuffer = it.next();
            File file;
            file = new File("assets"+File.separator+"Textures"+File.separator+"screenshots"+File.separator+"Prueba "+cont+".png");

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
        /*synchronized (this){
            this.notify();
        }*/
    }
}