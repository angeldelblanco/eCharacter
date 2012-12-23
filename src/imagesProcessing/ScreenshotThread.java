package imagesProcessing;

import com.jme3.app.state.ScreenshotAppState;
import gui_nifty.Gui;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ScreenshotThread extends Thread
{
    static final int numScreenshot = 9;
    private ScreenshotAppState screenShotState;
    private int stepAnimationTime;
    private int indexCaptura;
    private String screenshotName;
    
    public ScreenshotThread(ScreenshotAppState screeShotState,float animationTime,String screenshotName)
    {
        super();
        this.screenShotState = screeShotState;
        this.stepAnimationTime = (int)animationTime / numScreenshot;
        this.indexCaptura = 0;
        this.screenshotName = screenshotName;        
    }
    
    @Override
    public void run()
    {
        screenShotState.setFilePath("assets/Textures/screenshots/");
        for(int i = 1 ; i<=numScreenshot; i++)
        {
            screenShotState.takeScreenshot();
            try {
                sleep(i * stepAnimationTime);
            } catch (InterruptedException ex) {
                Logger.getLogger(ScreenshotThread.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
}
