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
                GenerateAnimation generateAnimation = new GenerateAnimation("assets/Textures/screenshots",nameAnimation, imagesNames);
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