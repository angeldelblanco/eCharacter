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
import java.io.File;
import java.util.logging.ConsoleHandler;
import java.util.logging.Handler;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Application extends SimpleApplication{

    private Gui gui;
    private Tooltip tooltip;
    private ScreenshotMyAppState screenShotState;
    private NiftyJmeDisplay niftyDisplay;
    private Control control;
    private Configuration config;
    private Nifty nifty;
    
    
    public Application(int width,int height,Configuration config){
        //Creation of the new configuration
        AppSettings newSettings = new AppSettings(true);
        newSettings.setResolution(width,height);
        //newSettings.setFullscreen(true);
        this.setShowSettings(false);
        this.setSettings(newSettings);
        this.config = config;
    }
    
    @Override
    public void simpleInitApp() {
        initKeys();
        //Disable log of nifty
        Logger root = Logger.getLogger("");
        Handler[] handlers = root.getHandlers();
        for (int i = 0; i < handlers.length; i++) {
            if (handlers[i] instanceof ConsoleHandler) {
                ((ConsoleHandler) handlers[i]).setLevel(Level.WARNING);
            }
        }
                
        setDisplayFps(false);
        setDisplayStatView(false);
        
        // Register locator to assetManager
        //assetManager.registerLocator("."+File.separator, FileLocator.class);
        assetManager.registerLocator("."+File.separator, ResourceLocator.class);
        
        niftyDisplay = new NiftyJmeDisplay(assetManager, inputManager, audioRenderer, guiViewPort);
        guiViewPort.addProcessor(niftyDisplay);
        screenShotState = new ScreenshotMyAppState();
        
        control = new Control(config,rootNode,assetManager,this, guiViewPort, niftyDisplay, screenShotState);
        gui = new Gui(control,config);
        /**
        * Åctivate the Nifty-JME integration: 
        */
        nifty = niftyDisplay.getNifty();
        nifty.fromXml("assets/Interface/screen.xml", "start", gui);
        //nifty.setDebugOptionPanelColors(true);

        stateManager.attach(gui);
        stateManager.attach(screenShotState);
        
        
        
        // Camera
        //setCamera(0.0f);
        //setCamera(-(float)Math.PI/4);
        /*System.out.println("Eye :"+cam.getLocation().getX()+" "+cam.getLocation().getY()+" "+cam.getLocation().getZ());
        System.out.println("Look :"+cam.getLeft().getX()+" "+cam.getLeft().getY()+" "+cam.getLeft().getZ());
        System.out.println("Up :"+cam.getUp().getX()+" "+cam.getUp().getY()+" "+cam.getUp().getZ());
        
        Vector3f newLocation = new Vector3f(cam.getLocation().getX(),1.0f,cam.getLocation().getZ());
        Vector3f newUp = new Vector3f(0.0f,0.5f,-1.0f);
        cam.setFrame(newLocation, cam.getLeft(),newUp,cam.getDirection());
       
        System.out.println("Eye :"+cam.getLocation().getX()+" "+cam.getLocation().getY()+" "+cam.getLocation().getZ());
        System.out.println("Look :"+cam.getLeft().getX()+" "+cam.getLeft().getY()+" "+cam.getLeft().getZ());
        System.out.println("Up :"+cam.getUp().getX()+" "+cam.getUp().getY()+" "+cam.getUp().getZ());*/
        
        //cam.setLocation(new Vector3f(0.0f, 3.0f, 10.0f));
        //cam.setAxes(cam.getLeft(), new Vector3f(0.0f, 0.7f, 0.0f), new Vector3f(0.0f, -0.7f, -0.7f));
        //cam.setRotation(new Quaternion(0.0f, 0.75f, 0.0f, 0.0f));
        
        flyCam.setDragToRotate(true); // you need the mouse for clicking now
        flyCam.setEnabled(false);
    }
    
    private void setCamera(float ang){
        Vector3f look = new Vector3f(0.0f,0.0f,0.0f);
        Vector3f eye = new Vector3f(0.0f,0.0f,10.0f);
        Vector3f direction = look.subtract(eye).normalize();
        Vector3f up_igr = new Vector3f(0.0f,1.0f,0.0f);
        //Esto es una rotacion en el eje Z
        /*float newX = up_igr.getX()*(float)Math.cos(ang) - up_igr.getY()*(float)Math.sin(ang);
        float newY = up_igr.getX()*(float)Math.sin(ang) + up_igr.getY()*(float)Math.cos(ang);
        float newZ = up_igr.getZ();*/
        
        //Esto es una rotacion en el eje Y
        /*float newX = up_igr.getX()*(float)Math.cos(ang) + up_igr.getZ()*(float)Math.sin(ang);
        float newY = up_igr.getY();
        float newZ = - up_igr.getX()*(float)Math.sin(ang) + up_igr.getZ()*(float)Math.cos(ang);*/
        
        //Esto es una rotacion en el eje X
        float newX = up_igr.getX();
        float newY = up_igr.getY()*(float)Math.cos(ang) - up_igr.getZ()*(float)Math.sin(ang);
        float newZ = up_igr.getY()*(float)Math.sin(ang) + up_igr.getZ()*(float)Math.cos(ang);
        up_igr.set(newX, newY, newZ);
        
        //Vector3f left = ((up_igr.cross(direction)).negate()).normalize();
        Vector3f left = ((up_igr.cross(direction))).normalize();
        Vector3f up = direction.cross(left);
        //Vector3f up = direction.cross(left).negate();
        
        System.out.println("*******************ANTES****************");
        System.out.println("Up: "+cam.getUp().toString());
        System.out.println("Direction: "+cam.getDirection().toString());
        System.out.println("Left: "+cam.getLeft().toString());
        
        cam.setAxes(left, up, direction);        
        
        System.out.println("*******************DESPUES****************");
        System.out.println("Up: "+cam.getUp().toString());
        System.out.println("Direction: "+cam.getDirection().toString());
        System.out.println("Left: "+cam.getLeft().toString());
    }
    
     public void onAnimCycleDone(AnimControl control, AnimChannel channel, String animName) 
    {}
 
    public void onAnimChange(AnimControl control, AnimChannel channel, String animName) 
    {}
    
     /** Custom Keybindings: Mapping a named action to a key input. */
    private void initKeys() {
        inputManager.addMapping("RotateLeft", new KeyTrigger(KeyInput.KEY_LEFT));
        inputManager.addMapping("RotateRight", new KeyTrigger(KeyInput.KEY_RIGHT));
        inputManager.addMapping("RestartRotate", new KeyTrigger(KeyInput.KEY_SPACE));
        inputManager.addListener(actionListener, "RotateLeft");
        inputManager.addListener(actionListener, "RotateRight");
        inputManager.addListener(actionListener, "RestartRotate");
  }

    /** Definining the named action that can be triggered by key inputs. */
    private ActionListener actionListener = new ActionListener() {
        public void onAction(String name, boolean keyPressed, float tpf) {
            if (name.equals("RotateLeft") && !keyPressed) {
                
                /*float nx = (float) (cam.getLocation().getX() * Math.cos(0.1f) +
                                    cam.getLocation().getZ() * Math.sin(0.1f));
                float ny = cam.getLocation().getY();
                float nz = (float) (-cam.getLocation().getX() * Math.sin(0.1f) +
                                    cam.getLocation().getZ() * Math.cos(0.1f));
                
                cam.setLocation(new Vector3f(nx,ny,nz));*/
                control.rotateModel(-10.0f);
            }
            if (name.equals("RotateRight") && !keyPressed){
                
                /*float nx = (float) (cam.getLocation().getX() * Math.cos(-1.0f) +
                                    cam.getLocation().getZ() * Math.sin(-1.0f));
                float nz = (float) (-cam.getLocation().getX() * Math.sin(-1.0f) +
                                    cam.getLocation().getZ() * Math.cos(-1.0f));
                
                cam.setLocation(new Vector3f(nx,cam.getLocation().getY(),nz));*/
                
                control.rotateModel(10.0f);
            }
            if (name.equals("RestartRotate") && !keyPressed){
                control.restartRotateModel();
            }
        }
    };
    
    @Override
    public void simpleUpdate(float tpf) {
    }
}