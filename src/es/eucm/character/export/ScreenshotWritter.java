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

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;

public class ScreenshotWritter extends Thread{
    private static final Logger logger = Logger.getLogger(ScreenshotWritter.class.getName());
    
    private String exportPath;
    private boolean terminate;
    private int xMin, xMax, yMin, yMax;
    private ArrayList<String> listAnimationsName;
    
    public ScreenshotWritter(String exportPath, ArrayList<String> listAnimationsName, 
            int xMin, int xMax, int yMin, int yMax){
        this.exportPath = exportPath;
        this.listAnimationsName = listAnimationsName;
        this.xMin = xMin;
        this.xMax = xMax;
        this.yMin = yMin;
        this.yMax = yMax;
        
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
        System.out.println("TAMAÑOOOO "+listAnimationsName.size());
        Iterator<String> it = listAnimationsName.iterator();
        while(it.hasNext()){
            BufferedImage img = null;
            File temp = null;
            try {
                temp = new File(exportPath+File.separator+it.next());
                img = ImageIO.read(temp);
                temp.delete();
                BufferedImage biCut = img.getSubimage(xMin, yMin, xMax-xMin, yMax-yMin);
                ImageIO.write(biCut, "png", temp);
            } catch (IOException e) {
                
            }
        }     
        System.out.println("ScreenshotWritter OK");
    }
}
