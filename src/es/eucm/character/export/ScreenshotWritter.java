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

import com.jme3.system.JmeSystem;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
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
            writeFile(data);
        }
        for (ScreenshotData data : queue){
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
