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

package es.eucm.character.main;

import com.jme3.animation.AnimChannel;
import com.jme3.animation.AnimControl;
import com.jme3.app.SimpleApplication;
import com.jme3.input.KeyInput;
import com.jme3.input.controls.ActionListener;
import com.jme3.input.controls.KeyTrigger;
import com.jme3.math.Vector3f;
import com.jme3.niftygui.NiftyJmeDisplay;
import com.jme3.system.AppSettings;
import de.lessvoid.nifty.Nifty;
import es.eucm.character.control.Control;
import es.eucm.character.export.ScreenshotMyAppState;
import es.eucm.character.gui.Gui;
import es.eucm.character.gui.Tooltip;
import es.eucm.character.loader.Configuration;
import es.eucm.character.loader.ResourceLocator;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.logging.ConsoleHandler;
import java.util.logging.Handler;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;

public class Application extends SimpleApplication{

    private Gui gui;
    private Tooltip tooltip;
    private ScreenshotMyAppState screenShotState;
    private NiftyJmeDisplay niftyDisplay;
    private Control control;
    private Configuration config;
    private Nifty nifty;
    private float t = 0;
    private AppSettings newSettings;
    
    /*private ViewPort pv;
    private Picture p;*/
    
    public Application(int width,int height,Configuration config){
        //Creation of the new configuration
        newSettings = new AppSettings(true);
        newSettings.setResolution(width,height);
        newSettings.setTitle("eCharacter");
        initIcons();
        //newSettings.setFullscreen(true);
        this.setShowSettings(false);
        this.setSettings(newSettings);
        this.config = config;
    }
    
    @Override
    public void simpleInitApp() {
        //CODIGO PARA PONER UNA IMAGEN DE FONDO
        /*p = new Picture("background");
        p.setImage(assetManager, "Interface/greystilebutton.png", false);
        p.setWidth(settings.getWidth());
        p.setHeight(settings.getHeight());
        p.setPosition(0, 0);

        pv = renderManager.createPreView("background", cam);
        pv.setClearFlags(true, true, true);
        pv.attachScene(p);
        viewPort.setClearFlags(false, true, true);
        p.updateGeometricState();
        //CODIGO PARA QUITAR LA IMAGEN DE FONDO (ESTO HAY QUE HACERLO EN DONDE NOS INTERESE QUITAR EL FONDO)
        //pv.detachScene(p);*/
        
        initKeys();
        disableLogNifty();        
                
        setDisplayFps(false);
        setDisplayStatView(false);
        
        // Register locator to assetManager
        assetManager.registerLocator("."+File.separator, ResourceLocator.class);
        
        //Add nifty
        niftyDisplay = new NiftyJmeDisplay(assetManager, inputManager, audioRenderer, guiViewPort);
        guiViewPort.addProcessor(niftyDisplay);
        
        screenShotState = new ScreenshotMyAppState();        
        control = new Control(config,rootNode,cam,assetManager,this, viewPort, guiViewPort, niftyDisplay, screenShotState);
        gui = new Gui(control,config);
        
        //Activate the Nifty-JME integration
        nifty = niftyDisplay.getNifty();
        nifty.fromXml("assets/Interface/screen.xml", "start", gui);
        //nifty.setDebugOptionPanelColors(true);
        
        //Attach nifty-control and screenshot
        stateManager.attach(gui);
        stateManager.attach(screenShotState);  
        
        //Disable flyCam
        flyCam.setDragToRotate(true); // you need the mouse for clicking now
        flyCam.setEnabled(false);
        /*LwjglInitHelper.renderLoop(nifty, new RenderLoop(nifty));
 	LwjglInitHelper.destroy();*/
    }
    
    public void onAnimCycleDone(AnimControl control, AnimChannel channel, String animName) 
    {}
 
    public void onAnimChange(AnimControl control, AnimChannel channel, String animName) 
    {}
    
    //Disable log of nifty
    private void disableLogNifty(){
        Logger root = Logger.getLogger("");
        Handler[] handlers = root.getHandlers();
        for (int i = 0; i < handlers.length; i++) {
            if (handlers[i] instanceof ConsoleHandler) {
                ((ConsoleHandler) handlers[i]).setLevel(Level.WARNING);
            }
        }
    }
    
    private void initIcons(){
        BufferedImage[] arrayIcons = new BufferedImage[4];
        try {
            arrayIcons[0] = ImageIO.read(new File("assets"+File.separator+"Icons"+File.separator+"Logo2-256.png"));
            arrayIcons[1] = ImageIO.read(new File("assets"+File.separator+"Icons"+File.separator+"Logo2-128.png"));
            arrayIcons[2] = ImageIO.read(new File("assets"+File.separator+"Icons"+File.separator+"Logo2-32.png"));
            arrayIcons[3] = ImageIO.read(new File("assets"+File.separator+"Icons"+File.separator+"Logo2-16.png"));
        } catch (IOException ex) {
            Logger.getLogger(Application.class.getName()).log(Level.SEVERE, null, ex);
        }
        newSettings.setIcons(arrayIcons);
    }
    
    // Custom Keybindings: Mapping a named action to a key input. 
    private void initKeys() {        
        //Translate camera
        inputManager.addMapping("TranslateRight", new KeyTrigger(KeyInput.KEY_RIGHT));
        inputManager.addListener(actionListener,"TranslateRight");
        inputManager.addMapping("TranslateLeft", new KeyTrigger(KeyInput.KEY_LEFT));
        inputManager.addListener(actionListener,"TranslateLeft");
        inputManager.addMapping("TranslateUp", new KeyTrigger(KeyInput.KEY_UP));
        inputManager.addListener(actionListener,"TranslateUp");
        inputManager.addMapping("TranslateDown", new KeyTrigger(KeyInput.KEY_DOWN));
        inputManager.addListener(actionListener,"TranslateDown");
        inputManager.addMapping("Translate+", new KeyTrigger(KeyInput.KEY_X));
        inputManager.addListener(actionListener,"Translate+");
        inputManager.addMapping("Translate-", new KeyTrigger(KeyInput.KEY_Z));
        inputManager.addListener(actionListener,"Translate-");
        
        //Rotate camera
        inputManager.addMapping("RotateRight", new KeyTrigger(KeyInput.KEY_D));
        inputManager.addListener(actionListener,"RotateRight");
        inputManager.addMapping("RotateLeft", new KeyTrigger(KeyInput.KEY_A));
        inputManager.addListener(actionListener,"RotateLeft");
        inputManager.addMapping("PitchUp", new KeyTrigger(KeyInput.KEY_W));
        inputManager.addListener(actionListener,"PitchUp");
        inputManager.addMapping("PitchDown", new KeyTrigger(KeyInput.KEY_S));
        inputManager.addListener(actionListener,"PitchDown");
        
        //Views camera
        inputManager.addMapping("Frontal",new KeyTrigger(KeyInput.KEY_1));
        inputManager.addListener(actionListener, "Frontal");
        inputManager.addMapping("2D",new KeyTrigger(KeyInput.KEY_2));
        inputManager.addListener(actionListener, "2D");
  }

    // Definining the named action that can be triggered by key inputs.
    private ActionListener actionListener = new ActionListener() {
        public void onAction(String name, boolean keyPressed, float tpf) {
            if(name.equals("TranslateRight") && !keyPressed){
                Vector3f inc = new Vector3f(0.2f, 0.0f, 0.0f);
                control.translateCamera(inc);
            }
            if(name.equals("TranslateLeft") && !keyPressed){
                Vector3f inc = new Vector3f(-0.2f, 0.0f, 0.0f);
                control.translateCamera(inc);
            }
            if(name.equals("TranslateUp") && !keyPressed){
                Vector3f inc = new Vector3f(0.0f, 0.2f, 0.0f);
                control.translateCamera(inc);
            }
            if(name.equals("TranslateDown") && !keyPressed){
                Vector3f inc = new Vector3f(0.0f, -0.2f, 0.0f);
                control.translateCamera(inc);
            }
            if(name.equals("Translate+") && !keyPressed){
                Vector3f inc = new Vector3f(0.0f, 0.0f, 0.2f);
                control.translateCamera(inc);
            }
            if(name.equals("Translate-") && !keyPressed){
                Vector3f inc = new Vector3f(0.0f, 0.0f, -0.2f);
                control.translateCamera(inc);
            }
            if(name.equals("RotateRight") && !keyPressed){
                control.rotateCamera(5.0f,Vector3f.UNIT_Y);
            }
            if(name.equals("RotateLeft") && !keyPressed){
                control.rotateCamera(-5.0f, Vector3f.UNIT_Y);
            }
            if(name.equals("PitchUp") && !keyPressed){
                control.pitchCamera(-5.0f);
            }
            if(name.equals("PitchDown") && !keyPressed){
                control.pitchCamera(5.0f);
            }
            if (name.equals("Frontal") && !keyPressed) {
                Vector3f position = new Vector3f(0.0f,0.0f,10.f);
                Vector3f direction = new Vector3f(0.0f,0.0f,-1.0f);
                Vector3f up = new Vector3f(0.0f,1.0f,0.0f);
                control.setViewCamera(position, direction, up);
            }
            if (name.equals("2D") && !keyPressed) {
                Vector3f position = new Vector3f(-3.0f,3.0f,10.0f);
                Vector3f direction = new Vector3f(0.0f,0.0f,-1.0f);
                Vector3f up = new Vector3f(0.0f,1.0f,0.0f);
                control.setViewCamera(position, direction, up);
            }
            
        }
    };
    
    /*private static final class RenderLoop implements RenderLoopCallback {
        private final Nifty nifty;
 	private int progress = 0;
     	private long start = new Date().getTime();
     	
     	private RenderLoop(final Nifty nifty) {
            this.nifty = nifty;
     	}
    	
     	@Override
     	public void process() {
            long now = new Date().getTime();
            if (now - start > 50) { // add one percent every 50 ms
                start = now;
     	
         	progress++;
                nifty.getScreen("start").findControl("my-progress", ProgressbarControl.class).setProgress(progress / 100.0f);
     	
         	if (progress >= 100) {
                 	System.out.println("done");
                }
            }
     	}
    }*/
}