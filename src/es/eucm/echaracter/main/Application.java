/*******************************************************************************
 * <eCharacter> is a research project of the <e-UCM>
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
 *          For more info please visit:  <http://echaracter.e-ucm.es>, 
 *          <http://e-adventure.e-ucm.es> or <http://www.e-ucm.es>
 *  
 *  ****************************************************************************
 *      <eCharacter> is free software: you can 
 *      redistribute it and/or modify it under the terms of the GNU Lesser 
 *      General Public License as published by the Free Software Foundation, 
 *      either version 3 of the License, or (at your option) any later version.
 *  
 *      <eCharacter> is distributed in the hope that it 
 *      will be useful, but WITHOUT ANY WARRANTY; without even the implied 
 *      warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  
 *      See the GNU Lesser General Public License for more details.
 *  
 *      You should have received a copy of the GNU Lesser General Public License
 *      along with <eCharacter>. If not, 
 *      see <http://www.gnu.org/licenses/>.
 ******************************************************************************/

package es.eucm.echaracter.main;

import com.jme3.animation.AnimChannel;
import com.jme3.animation.AnimControl;
import com.jme3.app.SimpleApplication;
import com.jme3.input.KeyInput;
import com.jme3.input.controls.ActionListener;
import com.jme3.input.controls.KeyTrigger;
import com.jme3.niftygui.NiftyJmeDisplay;
import com.jme3.renderer.ViewPort;
import com.jme3.system.AppSettings;
import com.jme3.ui.Picture;
import de.lessvoid.nifty.Nifty;
import es.eucm.echaracter.api.Callback;
import es.eucm.echaracter.control.Control;
import es.eucm.echaracter.export.ScreenshotMyAppState;
import es.eucm.echaracter.gui.Gui;
import es.eucm.echaracter.gui.Resources;
import es.eucm.echaracter.gui.Tooltip;
import es.eucm.echaracter.loader.Configuration;
import es.eucm.echaracter.loader.ResourceLocator;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.logging.ConsoleHandler;
import java.util.logging.Handler;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;

public class Application extends SimpleApplication{
    
    private final String guiFile = "assets/Interface/gui.xml";
    private boolean succeess = false;

    private Gui gui;
    private ScreenshotMyAppState screenShotState;
    private NiftyJmeDisplay niftyDisplay;
    private Control control;
    private Configuration config;
    private Nifty nifty;
    private float t = 0;
    private AppSettings newSettings;    
    private ViewPort pv;
    private Picture p;
    private Callback callback;
    
    public Application(int width,int height,Configuration config,Callback callback){
        //Creation of the new configuration
        newSettings = new AppSettings(true);
        newSettings.setSamples(4);
        newSettings.setResolution(width,height);
        newSettings.setTitle("eCharacter");
        initIcons();
        //newSettings.setFullscreen(true);
        this.setPauseOnLostFocus(false);
        this.setShowSettings(false);
        this.setSettings(newSettings);
        this.config = config;
        this.callback = callback;
    }
    
    @Override
    public void simpleInitApp() { 
        initKeys();
        disableLogNifty();        
                
        setDisplayFps(false);
        setDisplayStatView(false);
        
        // Register locator to assetManager
        assetManager.registerLocator("."+File.separator, ResourceLocator.class);
        assetManager.registerLocator(Configuration.APPLICATION_PATH+File.separator, ResourceLocator.class);
        
        if (checkResources()){
            //Add nifty
            niftyDisplay = new NiftyJmeDisplay(assetManager, inputManager, audioRenderer, guiViewPort);
            guiViewPort.addProcessor(niftyDisplay);

            screenShotState = new ScreenshotMyAppState();        
            control = new Control(callback,config,rootNode,cam,assetManager,this, viewPort, guiViewPort, niftyDisplay, screenShotState);
            gui = new Gui(control,config);

            //Activate the Nifty-JME integration
            nifty = niftyDisplay.getNifty();
            nifty.fromXml(guiFile, "start", gui);

            //Attach nifty-control and screenshot
            stateManager.attach(gui);
            stateManager.attach(screenShotState);  

            //Disable flyCam
            flyCam.setDragToRotate(true); // you need the mouse for clicking now
            flyCam.setEnabled(false);
        }
    }
    
    public void onAnimCycleDone(AnimControl control, AnimChannel channel, String animName) 
    {}
 
    public void onAnimChange(AnimControl control, AnimChannel channel, String animName) 
    {}
    
    @Override
    public void stop(){
        succeess = true;
        super.stop();
    }
    
    @Override
    public void destroy(){
        if(callback != null ){
            if (succeess){
                callback.exportSuccess();      
            }
            else{
                callback.exportFailed();
            }
        }
        super.destroy();
    }
    
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
            arrayIcons[0] = ImageIO.read(ResourceLocator.getResource("assets"+File.separator+"Icons"+File.separator+"Logo2-256.png"));
            arrayIcons[1] = ImageIO.read(ResourceLocator.getResource("assets"+File.separator+"Icons"+File.separator+"Logo2-128.png"));
            arrayIcons[2] = ImageIO.read(ResourceLocator.getResource("assets"+File.separator+"Icons"+File.separator+"Logo2-32.png"));
            arrayIcons[3] = ImageIO.read(ResourceLocator.getResource("assets"+File.separator+"Icons"+File.separator+"Logo2-16.png"));
        } catch (IOException ex) {
            Logger.getLogger(Application.class.getName()).log(Level.SEVERE, null, ex);
        }
        newSettings.setIcons(arrayIcons);
    }
    
    public void loadBackground(){
        //CODIGO PARA PONER UNA IMAGEN DE FONDO
        p = new Picture("background");
        p.setImage(assetManager, "assets/Interface/FondoVista3D-3.png", false);
        p.setWidth(settings.getWidth());
        p.setHeight(settings.getHeight());
        p.setPosition(0, 0);

        pv = renderManager.createPreView("background", cam);
        pv.setClearFlags(true, true, true);
        pv.attachScene(p);
        viewPort.setClearFlags(false, true, true);
        p.updateGeometricState();
    }
    
    public void removeBackground(){
        //CODIGO PARA QUITAR LA IMAGEN DE FONDO (ESTO HAY QUE HACERLO EN DONDE NOS INTERESE QUITAR EL FONDO)
        pv.detachScene(p);
    }
    
    // Custom Keybindings: Mapping a named action to a key input. 
    private void initKeys() {                
        //Rotate model
        inputManager.addMapping("RotateRight", new KeyTrigger(KeyInput.KEY_D));
        inputManager.addListener(actionListener,"RotateRight");
        inputManager.addMapping("RotateLeft", new KeyTrigger(KeyInput.KEY_A));
        inputManager.addListener(actionListener,"RotateLeft");
        inputManager.addMapping("Restart", new KeyTrigger(KeyInput.KEY_SPACE));
        inputManager.addListener(actionListener,"Restart");
  }

    // Definining the named action that can be triggered by key inputs.
    private ActionListener actionListener = new ActionListener() {
        public void onAction(String name, boolean keyPressed, float tpf) {
            if(name.equals("RotateRight") && !keyPressed){
                control.rotateModel(5.0f);
            }
            if(name.equals("RotateLeft") && !keyPressed){
                control.rotateModel(-5.0f);
            }
            if(name.equals("Restart") && !keyPressed){
                control.restartRotationModel();
            }
        }
    };
    
    private static boolean checkResources(){
        if (ResourceLocator.getPathResource(Resources.background_popup) == null){
            return false;
        }
        if (ResourceLocator.getPathResource(Resources.background_popup_custom) == null){
            return false;
        }
        if (ResourceLocator.getPathResource(Resources.background_popup_dialog) == null){
            return false;
        }
        if (ResourceLocator.getPathResource(Resources.background_popup_language) == null){
            return false;
        }
        if (ResourceLocator.getPathResource(Resources.blue) == null){
            return false;
        }
        if (ResourceLocator.getPathResource(Resources.button) == null){
            return false;
        }
        if (ResourceLocator.getPathResource(Resources.button_accept) == null){
            return false;
        }
        if (ResourceLocator.getPathResource(Resources.button_accept_over) == null){
            return false;
        }
        if (ResourceLocator.getPathResource(Resources.button_cancel) == null){
            return false;
        }
        if (ResourceLocator.getPathResource(Resources.button_cancel_over) == null){
            return false;
        }
        if (ResourceLocator.getPathResource(Resources.button_color) == null){
            return false;
        }
        if (ResourceLocator.getPathResource(Resources.button_color_over) == null){
            return false;
        }
        if (ResourceLocator.getPathResource(Resources.button_down) == null){
            return false;
        }
        if (ResourceLocator.getPathResource(Resources.button_down_over) == null){
            return false;
        }
        if (ResourceLocator.getPathResource(Resources.button_family) == null){
            return false;
        }
        if (ResourceLocator.getPathResource(Resources.button_family_over) == null){
            return false;
        }
        if (ResourceLocator.getPathResource(Resources.button_info) == null){
            return false;
        }
        if (ResourceLocator.getPathResource(Resources.button_info_over) == null){
            return false;
        }
        if (ResourceLocator.getPathResource(Resources.button_left) == null){
            return false;
        }
        if (ResourceLocator.getPathResource(Resources.button_left_over) == null){
            return false;
        }
        if (ResourceLocator.getPathResource(Resources.button_more) == null){
            return false;
        }
        if (ResourceLocator.getPathResource(Resources.button_more_over) == null){
            return false;
        }
        if (ResourceLocator.getPathResource(Resources.button_next) == null){
            return false;
        }
        if (ResourceLocator.getPathResource(Resources.button_next_over) == null){
            return false;
        }
        if (ResourceLocator.getPathResource(Resources.button_over) == null){
            return false;
        }
        if (ResourceLocator.getPathResource(Resources.button_previous) == null){
            return false;
        }
        if (ResourceLocator.getPathResource(Resources.button_previous_over) == null){
            return false;
        }
        if (ResourceLocator.getPathResource(Resources.button_right) == null){
            return false;
        }
        if (ResourceLocator.getPathResource(Resources.button_right_over) == null){
            return false;
        }
        if (ResourceLocator.getPathResource(Resources.button_up) == null){
            return false;
        }
        if (ResourceLocator.getPathResource(Resources.button_up_over) == null){
            return false;
        }
        if (ResourceLocator.getPathResource(Resources.close_language) == null){
            return false;
        }
        if (ResourceLocator.getPathResource(Resources.close_language_over) == null){
            return false;
        }
        if (ResourceLocator.getPathResource(Resources.eCharacter) == null){
            return false;
        }
        if (ResourceLocator.getPathResource(Resources.export) == null){
            return false;
        }
        if (ResourceLocator.getPathResource(Resources.export_over) == null){
            return false;
        }
        if (ResourceLocator.getPathResource(Resources.export_selection) == null){
            return false;
        }
        if (ResourceLocator.getPathResource(Resources.flag) == null){
            return false;
        }
        if (ResourceLocator.getPathResource(Resources.font_15) == null){
            return false;
        }
        if (ResourceLocator.getPathResource(Resources.font_20) == null){
            return false;
        }
        if (ResourceLocator.getPathResource(Resources.font_30) == null){
            return false;
        }
        if (ResourceLocator.getPathResource(Resources.green) == null){
            return false;
        }
        if (ResourceLocator.getPathResource(Resources.header_left_separator) == null){
            return false;
        }
        if (ResourceLocator.getPathResource(Resources.header_right_separator) == null){
            return false;
        }
        if (ResourceLocator.getPathResource(Resources.logo) == null){
            return false;
        }
        if (ResourceLocator.getPathResource(Resources.menu_selection) == null){
            return false;
        }
        if (ResourceLocator.getPathResource(Resources.model) == null){
            return false;
        }
        if (ResourceLocator.getPathResource(Resources.red) == null){
            return false;
        }
        if (ResourceLocator.getPathResource(Resources.s1_background) == null){
            return false;
        }
        if (ResourceLocator.getPathResource(Resources.s1_header) == null){
            return false;
        }
        if (ResourceLocator.getPathResource(Resources.s1_left_panel) == null){
            return false;
        }
        if (ResourceLocator.getPathResource(Resources.s1_right_panel) == null){
            return false;
        }
        if (ResourceLocator.getPathResource(Resources.background_popup_repository) == null){
            return false;
        }
        if (ResourceLocator.getPathResource(Resources.s2_header_left) == null){
            return false;
        }
        if (ResourceLocator.getPathResource(Resources.s2_header_right) == null){
            return false;
        }
        if (ResourceLocator.getPathResource(Resources.s2_right_panel) == null){
            return false;
        }
        if (ResourceLocator.getPathResource(Resources.s2_separator) == null){
            return false;
        }
        if (ResourceLocator.getPathResource(Resources.restartmodel) == null){
            return false;
        }
        if (ResourceLocator.getPathResource(Resources.restartmodel_over) == null){
            return false;
        }
        if (ResourceLocator.getPathResource(Resources.selector) == null){
            return false;
        }
        if (ResourceLocator.getPathResource(Resources.selector_down) == null){
            return false;
        }
        if (ResourceLocator.getPathResource(Resources.selector_down_over) == null){
            return false;
        }
        if (ResourceLocator.getPathResource(Resources.selector_up) == null){
            return false;
        }
        if (ResourceLocator.getPathResource(Resources.selector_up_over) == null){
            return false;
        }
        if (ResourceLocator.getPathResource(Resources.tab_left) == null){
            return false;
        }
        if (ResourceLocator.getPathResource(Resources.tab_rigth) == null){
            return false;
        }
        if (ResourceLocator.getPathResource(Resources.tick) == null){
            return false;
        }
        if (ResourceLocator.getPathResource(Resources.x) == null){
            return false;
        }
        if (ResourceLocator.getPathResource(Resources.repositorio) == null){
            return false;
        }
        if (ResourceLocator.getPathResource(Resources.repositorio_over) == null){
            return false;
        }
        if (ResourceLocator.getPathResource(Resources.eCharacterRepository) == null){
            return false;
        }
        if (ResourceLocator.getPathResource(Resources.camerapanel_leftbutton) == null){
            return false;
        }
        if (ResourceLocator.getPathResource(Resources.camerapanel_leftbutton_over) == null){
            return false;
        }
        if (ResourceLocator.getPathResource(Resources.camerapanel_rightbutton) == null){
            return false;
        }
        if (ResourceLocator.getPathResource(Resources.camerapanel_rightbutton_over) == null){
            return false;
        }
        if (ResourceLocator.getPathResource(Resources.complexion) == null){
            return false;
        }
        if (ResourceLocator.getPathResource(Resources.complexion_over) == null){
            return false;
        }
        return true;
    }
}