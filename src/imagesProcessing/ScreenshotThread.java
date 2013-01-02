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

package imagesProcessing;

import animation.GenerateAnimation;
import com.jme3.animation.AnimChannel;
import com.jme3.animation.LoopMode;
import com.jme3.app.state.ScreenshotAppState;
import com.jme3.niftygui.NiftyJmeDisplay;
import com.jme3.renderer.ViewPort;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;


public class ScreenshotThread extends Thread
{
    static final int numScreenshot = 9;
    
    private ScreenshotAppState screenShotState;
    private AnimChannel channel;
    private ViewPort guiViewPort;
    private NiftyJmeDisplay niftyDisplay;    
    private Set<String> namesAnimations;
    private long stepAnimationTime;
    
    private ArrayList<String> imagesNames;
    
    public ScreenshotThread(ScreenshotAppState screeShotState,AnimChannel channel,
            ViewPort guiViewPort,NiftyJmeDisplay niftyDisplay,Set<String> namesAnimations)
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
            int cont = 1;
            Iterator<String> it = namesAnimations.iterator();
            GenerateAnimation generateAnimation = null;
            while(it.hasNext())
            {
                String nameAnimation = it.next();
                screenShotState.setFilePath("assets/Textures/screenshots/"+nameAnimation);
                imagesNames = new ArrayList<String>();                
                channel.setAnim(nameAnimation);
                channel.setLoopMode(LoopMode.DontLoop);
                stepAnimationTime = (long) (channel.getAnimMaxTime() * 1000 / numScreenshot);
                sleep(100);
                for(int j= 1 ; j<=numScreenshot; j++)
                {
                    screenShotState.takeScreenshot();
                    imagesNames.add(nameAnimation+"Gui"+cont+".png");
                    cont++;
                    sleep(stepAnimationTime);
                }
                generateAnimation = new GenerateAnimation("assets/Textures/screenshots",nameAnimation, imagesNames);
            }
            if (generateAnimation != null){
                generateAnimation.cleanDirectory("assets/Textures/screenshots");
            }
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
}