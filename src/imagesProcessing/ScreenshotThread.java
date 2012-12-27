package imagesProcessing;

import animation.GenerateAnimation;
import com.jme3.animation.AnimChannel;
import com.jme3.animation.LoopMode;
import com.jme3.app.state.ScreenshotAppState;
import com.jme3.niftygui.NiftyJmeDisplay;
import com.jme3.renderer.ViewPort;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ScreenshotThread extends Thread
{
    static final int numScreenshot = 9;
    private ScreenshotAppState screenShotState;
    private long stepAnimationTime;
    private String screenshotName;
    private AnimChannel channel;
    private ViewPort guiViewPort;
    private NiftyJmeDisplay niftyDisplay;
    private ArrayList<String> imagesNames;
    
    public ScreenshotThread(ScreenshotAppState screeShotState,float animationTime,
            String screenshotName,AnimChannel channel,ViewPort guiViewPort,NiftyJmeDisplay niftyDisplay)
    {
        super();
        this.screenShotState = screeShotState;
        this.stepAnimationTime = (long) (animationTime * 1000 / numScreenshot);
        this.screenshotName = screenshotName;
        this.channel = channel;
        this.guiViewPort = guiViewPort;
        this.niftyDisplay = niftyDisplay;
        this.imagesNames = new ArrayList<String>();
        
    }
    
    @Override
    public void run()
    {
        try {
            screenShotState.setFilePath("assets/Textures/screenshots/"+screenshotName);
            channel.reset(true);
            channel.setAnim("my_animation");
            channel.setLoopMode(LoopMode.DontLoop);
            sleep(100);
            for(int i = 1 ; i<=numScreenshot; i++)
            {
                screenShotState.takeScreenshot();
                imagesNames.add(screenshotName+"Gui"+i+".png");
                sleep(stepAnimationTime + 50);
            }
            GenerateAnimation generateAnimation = new GenerateAnimation("assets/Textures/screenshots", screenshotName, imagesNames);
            guiViewPort.addProcessor(niftyDisplay);
        } catch (InterruptedException ex) {
            Logger.getLogger(ScreenshotThread.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
}