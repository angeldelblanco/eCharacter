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

package gui_nifty;

import Model.Model;
import XML.XMLReader;
import XML.XMLReaderJAXB;
import applicationdata.family.Family;
import com.jme3.animation.AnimChannel;
import com.jme3.animation.AnimControl;
import com.jme3.app.SimpleApplication;
import com.jme3.app.state.ScreenshotAppState;
import com.jme3.asset.plugins.FileLocator;
import com.jme3.light.DirectionalLight;
import com.jme3.material.Material;
import com.jme3.math.Vector3f;
import com.jme3.niftygui.NiftyJmeDisplay;
import com.jme3.scene.Spatial;
import com.jme3.system.AppSettings;
import de.lessvoid.nifty.Nifty;
import java.awt.Color;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import types.Age;
import types.Gender;
import types.TypeObject;

public class Gui extends SimpleApplication{

    private StartScreen startScreen;
    private ScreenshotAppState screenShotState;
    private NiftyJmeDisplay niftyDisplay;
    private Model model;
    private Material mat;
    private static ArrayList<Family> families;
    private static XMLReaderJAXB xmlReader;
    
    private int indexImage = 0;
    
    public static void main(String[] args) {
        //Averiguar la resolucion de pantalla del usuario
        /*Toolkit t = Toolkit.getDefaultToolkit();
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        System.out.println("Tu resolución es de " + screenSize.width + "x" + screenSize.height);*/
        
        //Read the XML's with all families.
        xmlReader = new XMLReaderJAXB<Family>("assets/XML Configuration/families");
        families = xmlReader.readXML(Family.class);

        AppSettings settings = new AppSettings(true);
        settings.setResolution(1024, 768);
        //settings.setFullscreen(true);
        Gui app = new Gui();
        app.setShowSettings(false);
        app.setSettings(settings);
        app.start();
        
    }
    
    @Override
    public void simpleInitApp() {
        setDisplayFps(false);
        setDisplayStatView(false);
        // Register locator to assetManager
        assetManager.registerLocator("./", FileLocator.class); 
        
        startScreen = new StartScreen(this);
        stateManager.attach(startScreen);
        
        screenShotState = new ScreenshotAppState();
        stateManager.attach(screenShotState);
        
        /**
        * Åctivate the Nifty-JME integration: 
        */
        niftyDisplay = new NiftyJmeDisplay(assetManager, inputManager, audioRenderer, guiViewPort);
        Nifty nifty = niftyDisplay.getNifty();
        guiViewPort.addProcessor(niftyDisplay);
        nifty.fromXml("assets/Interface/screen.xml", "start", startScreen);
        //nifty.setDebugOptionPanelColors(true);

        flyCam.setDragToRotate(true); // you need the mouse for clicking now
        model = new Model();
    }
    
     public void onAnimCycleDone(AnimControl control, AnimChannel channel, String animName) 
    {
        
    }
 
    public void onAnimChange(AnimControl control, AnimChannel channel, String animName) 
    {
    }

    public ArrayList<Family> getFamilies() {
        return families;
    }
    
    public int length(String param){
        if(param.equals("skinScreen")){return lengthSkins();}
        if(param.equals("eyesScreen")){return lengthEyes();}
        if(param.equals("tshirtScreen")){return lengthTShirt();}
        if(param.equals("trousersScreen")){return lengthTrouser();}
        if(param.equals("shoesScreen")){return lengthShoes();}
        return 0;
    }
    
    public String path(String param, int i){
        if(param.equals("skinScreen")){return pathSkin(i);}
        if(param.equals("eyesScreen")){return pathEyes(i);}
        if(param.equals("tshirtScreen")){return pathTShirt(i);}
        if(param.equals("trousersScreen")){return pathTrouser(i);}
        if(param.equals("shoesScreen")){return pathShoes(i);}
        return "";
    }
    
    public void setTypeObject(String selection){
        if(selection.equals("skinScreen")){}
        if(selection.equals("hairScreen")){}
        if(selection.equals("eyesScreen")){}
        if(selection.equals("tshirtScreen")){
            model.setTypeObject(TypeObject.t_shirt);
        }
        if(selection.equals("trousersScreen")){
            model.setTypeObject(TypeObject.trouser);
        }
        if(selection.equals("shoesScreen")){
            model.setTypeObject(TypeObject.shoes);
        }
        if(selection.equals("accesoriesScreen")){}
    }
    
    public int lengthSkins(){
        return model.getArraySkinIcon().length;
    }
    
    public String pathSkin(int i){
        if((i>=0)&&(i<= model.getArraySkinIcon().length)){
            return model.getArraySkinIcon()[i];
        }
        else{
            return "";
        }
    }
    
    public String[] getArraySkinIcon() {
        return model.getArraySkinIcon();
    }
    
    public int lengthEyes(){
        return model.getArrayEyesIcon().length;
    }
    
    public String pathEyes(int i){
        if((i>=0)&&(i<= model.getArrayEyesIcon().length)){
            return model.getArrayEyesIcon()[i];
        }
        else{
            return "";
        }
    }
    
    public int lengthTShirt(){
        return model.getArrayTShirtIcon().length;
    }
    
    public String pathTShirt(int i){
        if((i>=0)&&(i<= model.getArrayTShirtIcon().length)){
            return model.getArrayTShirtIcon()[i];
        }
        else{
            return "";
        }
    }
    
    public int lengthTrouser(){
        return model.getArrayTrouserIcon().length;
    }
    
    public String pathTrouser(int i){
        if((i>=0)&&(i<= model.getArrayTrouserIcon().length)){
            return model.getArrayTrouserIcon()[i];
        }
        else{
            return "";
        }
    }
    
    public int lengthShoes(){
        return model.getArrayShoesIcon().length;
    }
    
    public String pathShoes(int i){
        if((i>=0)&&(i<= model.getArrayShoesIcon().length)){
            return model.getArrayShoesIcon()[i];
        }
        else{
            return "";
        }
    }
    
    public void changeSkin(int steep)
    {   
        indexImage++;
        model.setIndexImage(indexImage);
        model.dettachAllChild();
        model.changeSkin(steep);
        mat.setTexture("ColorMap", assetManager.loadTexture("assets/Textures/FinalTexture"+indexImage+".png")); 
        model.setMaterial(mat);
        model.attachAllChild();
        //Delete the file                
        Path file = Paths.get("assets/Textures/FinalTexture"+indexImage+".png");                
        try {                   
            Files.delete(file);                
        } catch (IOException ex) {                    
            System.out.println("Failed deleting file");                
        }
    }
    
    public void changeShoes(int steep)
    {
        indexImage++;
        model.setIndexImage(indexImage);
        model.dettachAllChild();
        model.changeShoes(steep);
        mat.setTexture("ColorMap", assetManager.loadTexture("assets/Textures/FinalTexture"+indexImage+".png"));                
        model.setMaterial(mat);
        model.attachAllChild();
        //Delete the file               
        Path file = Paths.get("assets/Textures/FinalTexture"+indexImage+".png");               
        try {                    
            Files.delete(file);                
        } catch (IOException ex) {                    
            System.out.println("Failed deleting file");                
        }
    }
    
    public void changeTrousers(int steep)
    {
        indexImage++; 
        model.setIndexImage(indexImage);
        model.dettachAllChild();
        model.changeTrousers(steep);
        mat.setTexture("ColorMap", assetManager.loadTexture("assets/Textures/FinalTexture"+indexImage+".png")); 
        model.setMaterial(mat);
        model.attachAllChild();
        //Delete the file                
        Path file = Paths.get("assets/Textures/FinalTexture"+indexImage+".png");                
        try {                   
            Files.delete(file);                
        } catch (IOException ex) {                    
            System.out.println("Failed deleting file");                
        }
    }
    
    public void changeTShirt(int steep)
    {
        indexImage++; 
        model.setIndexImage(indexImage);
        model.dettachAllChild();
        model.changeTShirt(steep);
        mat.setTexture("ColorMap", assetManager.loadTexture("assets/Textures/FinalTexture"+indexImage+".png")); 
        model.setMaterial(mat);
        model.attachAllChild();
        //Delete the file                
        Path file = Paths.get("assets/Textures/FinalTexture"+indexImage+".png");                
        try {                   
            Files.delete(file);                
        } catch (IOException ex) {                    
            System.out.println("Failed deleting file");                
        }
    }
    
    public void changeEyes(int steep)
    {
        indexImage++; 
        model.setIndexImage(indexImage);
        model.dettachAllChild();
        model.changeEyes(steep);
        mat.setTexture("ColorMap", assetManager.loadTexture("assets/Textures/FinalTexture"+indexImage+".png")); 
        model.setMaterial(mat);
        model.attachAllChild();
        //Delete the file                
        Path file = Paths.get("assets/Textures/FinalTexture"+indexImage+".png");                
        try {                   
            Files.delete(file);                
        } catch (IOException ex) {                    
            System.out.println("Failed deleting file");                
        }
    }
    
    public void changeColor(float red, float green, float blue) throws IOException
    {
        indexImage++; 
        model.setIndexImage(indexImage);
        model.dettachAllChild();
        Color color = new Color(red, green, blue);
        model.changeColor(color);
        mat.setTexture("ColorMap", assetManager.loadTexture("assets/Textures/FinalTexture"+indexImage+".png"));     
        model.setMaterial(mat);
        model.attachAllChild();
        //Delete the file               
        Path file = Paths.get("assets/Textures/FinalTexture"+indexImage+".png");               
        try {                    
            Files.delete(file);                
        } catch (IOException ex) {                    
            System.out.println("Failed deleting file");                
        }
    }
    
    public void screenshot() 
    {      
        model.screenShot(guiViewPort, niftyDisplay, screenShotState);
    }

    public void setGender(Gender gender) 
    {
       model.setGender(gender);
    }

    public void setAgeModel(Age age) 
    {
        model.setAge(age);
    }
    
    public void loadModel(){
        XMLReader xmlReader= new XMLReader(model, "assets/XML Configuration/configuration.xml", model.getGender());
        xmlReader.readXML();
        DirectionalLight dl = new DirectionalLight();
        dl.setDirection(new Vector3f(-0.1f, -1f, -1).normalizeLocal());
        rootNode.addLight(dl);

        model.readBuffers();
        
        //Cargar el modelo
        //assetManager.registerLocator("./", FileLocator.class); // register locator to assetManager
        
        mat = new Material(assetManager, "Common/MatDefs/Misc/Unshaded.j3md");
        mat.setTexture("ColorMap",assetManager.loadTexture("assets/Textures/OriginalTexture.png"));   
        model.setModel(assetManager.loadModel(model.getModelPath()), mat);       
        Spatial hairMesh = assetManager.loadModel("assets/Models/eAdventure/Hair Boy/goku haircut.mesh.xml");
        Material hairMat = new Material(assetManager, "Common/MatDefs/Light/Lighting.j3md");
        hairMat.setTexture("DiffuseMap",assetManager.loadTexture("assets/Models/eAdventure/Hair Boy/hairtexture.png"));
        hairMesh.setMaterial(hairMat);
        model.addSubMeshes("Cabeza",hairMesh);
        //model.setHair("assets/Models/eAdventure/Hair Boy/pasted__pasted__pasted__polySurfaceShape2.mesh.xml", "assets/Models/eAdventure/Hair Boy/pasted__pasted__pasted__polySurfaceShape2.material");
        rootNode.attachChild(model.getModel());
        model.setPositionModel();
        
        //Borrar la imagen
        Path file = Paths.get("assets/Textures/OriginalTexture.png");
        try {
            Files.delete(file);
        } 
        catch (IOException ex) {
            System.out.println("Error al borrar el fichero");
        }
    }
     
    public void setBodyType(int bodyType)
    {
        model.setBodyType(bodyType);
    }

    public void scaleHead(float inc) {
        model.scaleHead(inc);
    }

    public void scaleTorax(float inc) {
        model.scaleTorax(inc);
    }

    public void scaleHands(float inc) {
        model.scaleHands(inc);
    }

    public void scaleFeet(float inc) {
        model.scaleFeet(inc);
    }

    public void scaleLegs(float inc) {
        model.scaleLegs(inc);
    }

    public void scaleArms(float inc) {
        model.scaleArms(inc);
    }
}