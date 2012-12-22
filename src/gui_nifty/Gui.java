package gui_nifty;

import com.jme3.animation.AnimChannel;
import com.jme3.animation.AnimControl;
import com.jme3.animation.LoopMode;
import com.jme3.app.SimpleApplication;
import com.jme3.input.KeyInput;
import com.jme3.input.controls.ActionListener;
import com.jme3.input.controls.KeyTrigger;
import com.jme3.light.DirectionalLight;
import com.jme3.material.Material;
import com.jme3.math.Vector3f;
import com.jme3.niftygui.NiftyJmeDisplay;
import com.jme3.renderer.Renderer;
import com.jme3.scene.Spatial;
import com.jme3.system.AppSettings;
import com.jme3.texture.FrameBuffer;
import com.jme3.util.BufferUtils;
import com.jme3.util.Screenshots;
import de.lessvoid.nifty.Nifty;
import imagesProcessing.ColoringImage;
import imagesProcessing.ImagesProcessing;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import javax.imageio.ImageIO;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import tipos.Age;
import tipos.Gender;
import tipos.TypeObject;
import window_color.ColorChooser;

public class Gui extends SimpleApplication{

    private StartScreen startScreen;
    
    private AnimChannel channel;
    private AnimControl control;
    private Spatial model;
    private Material mat;
    private ImagesProcessing img;
    private String skinsPath, trousersColorPath, trousersShadowPath, tShirtsColorPath, tShirtsShadowPath, eyesPath, 
            shoesColorPath, shoesShadowPath, destinationPath;
    
    private int indexCaptura = 0;
    private int indexImage = 0;
    
    String [] arraySkin, arrayShoesColor, arrayShoesShadow, arrayTrouserColor, arrayTrouserShadow, arrayTShirtColor, arrayTShirtShadow, arrayEyes;
    private int indexSkin = -1;
    private int indexTShirt = -1;
    private int indexShoes = -1;
    private int indexTrouser = -1;
    private int indexEyes = -1;
    private int numSkins;
    private int numShoes;
    private int numTrousers;
    private int numTShirts;
    private int numEyes;
    
    private Gender gender;
    private Age age;
    private TypeObject typeObject;
    
    public static void main(String[] args) {
        AppSettings settings = new AppSettings(true);
        settings.setResolution(800, 600);
        Gui app = new Gui();
        app.setShowSettings(false);
        app.setSettings(settings);
        app.start();
    }
    
    @Override
    public void simpleInitApp() {
        setDisplayFps(false);
        setDisplayStatView(false);
        
        //CHICO
        //Andar
        //Spatial chico = assetManager.loadModel("Models/eAdventure/Animation Boy/Andar/polySurfaceShape4.mesh.xml");
        //Coger
        //Spatial chico = assetManager.loadModel("Models/eAdventure/Animation Boy/Coger/polySurfaceShape4.mesh.xml");
        //Hablar
        //Spatial chico = assetManager.loadModel("Models/eAdventure/Animation Boy/Hablar/polySurfaceShape4.mesh.xml");
        //Parado
        //Spatial chico = assetManager.loadModel("Models/eAdventure/Animation Boy/Parado/polySurfaceShape4.mesh.xml");

        //CHICA
        //Andar
        //Spatial chico = assetManager.loadModel("Models/eAdventure/Animation Girl/Andar/polySurfaceShape12.mesh.xml");
        //Coger
        //Spatial chico = assetManager.loadModel("Models/eAdventure/Animation Girl/Coger/polySurfaceShape12.mesh.xml");
        //Hablar
        //Spatial chico = assetManager.loadModel("Models/eAdventure/Animation Girl/Hablar/polySurfaceShape12.mesh.xml");
        //Parada
        //Spatial chico = assetManager.loadModel("Models/eAdventure/Animation Girl/Parada/polySurfaceShape12.mesh.xml");

        //Animacion cambiada con blender
        /*model = assetManager.loadModel("Models/prueba/polySurfaceShape4.mesh.xml");
        mat = new Material(assetManager, "Common/MatDefs/Misc/Unshaded.j3md");
        mat.setTexture("ColorMap",assetManager.loadTexture("Textures/OriginalTexture.png"));  
        model.setMaterial(mat);*/
        
        startScreen = new StartScreen(this);
        stateManager.attach(startScreen);
        
        /**
        * Åctivate the Nifty-JME integration: 
        */
        NiftyJmeDisplay niftyDisplay = new NiftyJmeDisplay(
                assetManager, inputManager, audioRenderer, guiViewPort);
        Nifty nifty = niftyDisplay.getNifty();
        guiViewPort.addProcessor(niftyDisplay);
        nifty.fromXml("Interface/screen.xml", "start", startScreen);
        //nifty.setDebugOptionPanelColors(true);

        flyCam.setDragToRotate(true); // you need the mouse for clicking now
    }
    
     public void onAnimCycleDone(AnimControl control, AnimChannel channel, String animName) 
    {
        if (animName.equals("my_animation")) {
            channel.setAnim("my_animation");
            channel.setLoopMode(LoopMode.DontLoop);
            channel.setSpeed(1f);
        }
    }
 
    public void onAnimChange(AnimControl control, AnimChannel channel, String animName) 
    {}
    
    public void changeSkin()
    {
        indexSkin++;
        skinsPath = arraySkin[indexSkin%numSkins];
        //Creat the image             
        img = new ImagesProcessing(skinsPath, trousersColorPath, trousersShadowPath, tShirtsColorPath, tShirtsShadowPath, 
                eyesPath, shoesColorPath, shoesShadowPath);                
        indexImage++;                
        destinationPath = "assets/Textures/FinalTexture"+indexImage+".png";                
        img.fusionaImagenes(destinationPath);                
        //Set the texture to the material                
        mat.setTexture("ColorMap", assetManager.loadTexture("Textures/FinalTexture"+indexImage+".png"));                  
        model.setMaterial(mat);                
        //Delete the file                
        Path file = Paths.get(destinationPath);                
        try {                   
            Files.delete(file);                
        } catch (IOException ex) {                    
            System.out.println("Failed deleting file");                
        }            
    }
    
    public void changeShoes()
    {
        indexShoes++;   
        shoesColorPath = arrayShoesColor[indexShoes%numShoes];    
        shoesShadowPath = arrayShoesShadow[indexShoes%numShoes]; 
        //Creat the image                
        img = new ImagesProcessing(skinsPath, trousersColorPath, trousersShadowPath, tShirtsColorPath, tShirtsShadowPath, 
                eyesPath, shoesColorPath, shoesShadowPath);                 
        indexImage++;                
        destinationPath = "assets/Textures/FinalTexture"+indexImage+".png";                
        img.fusionaImagenes(destinationPath);                
        //Set the texture to the material               
        mat.setTexture("ColorMap", assetManager.loadTexture("Textures/FinalTexture"+indexImage+".png"));                
        model.setMaterial(mat);               
        //Delete the file               
        Path file = Paths.get(destinationPath);               
        try {                    
            Files.delete(file);                
        } catch (IOException ex) {                    
            System.out.println("Failed deleting file");                
        }
    }
    
    public void changeTrousers()
    {
        indexTrouser++;   
        trousersColorPath = arrayTrouserColor[indexTrouser%numTrousers]; 
        trousersShadowPath = arrayTrouserShadow[indexTrouser%numTrousers]; 
        //Creat the image                
        img = new ImagesProcessing(skinsPath, trousersColorPath, trousersShadowPath, tShirtsColorPath, tShirtsShadowPath, 
                eyesPath, shoesColorPath, shoesShadowPath);          
        indexImage++;        
        destinationPath = "assets/Textures/FinalTexture"+indexImage+".png";        
        img.fusionaImagenes(destinationPath);        
        //Set the texture to the material        
        mat.setTexture("ColorMap", assetManager.loadTexture("Textures/FinalTexture"+indexImage+".png"));        
        model.setMaterial(mat);        
        //Delete the file        
        Path file = Paths.get(destinationPath);        
        try {        
            Files.delete(file);            
        } catch (IOException ex) {        
            System.out.println("Failed deleting file");            
        }
    }
    
    public void changeTShirt()
    {
        indexTShirt++;
        tShirtsColorPath = arrayTShirtColor[indexTShirt%numTShirts];  
        tShirtsShadowPath = arrayTShirtShadow[indexTShirt%numTShirts];  
        //Creat the image        
        img = new ImagesProcessing(skinsPath, trousersColorPath, trousersShadowPath, tShirtsColorPath, tShirtsShadowPath, 
                eyesPath, shoesColorPath, shoesShadowPath);        
        indexImage++;        
        destinationPath = "assets/Textures/FinalTexture"+indexImage+".png";        
        img.fusionaImagenes(destinationPath);        
        //Set the texture to the material        
        mat.setTexture("ColorMap", assetManager.loadTexture("Textures/FinalTexture"+indexImage+".png"));          
        model.setMaterial(mat);        
        //Delete the file        
        Path file = Paths.get(destinationPath);        
        try {        
            Files.delete(file);            
        } catch (IOException ex) {        
            System.out.println("Failed deleting file");            
        }
    }
    
    public void changeEyes()
    {
        indexEyes++;
        eyesPath = arrayEyes[indexEyes%numEyes];                                      
        //Creat the image        
        img = new ImagesProcessing(skinsPath, trousersColorPath, trousersShadowPath, tShirtsColorPath, tShirtsShadowPath, 
                eyesPath, shoesColorPath, shoesShadowPath);      
        indexImage++;        
        destinationPath = "assets/Textures/FinalTexture"+indexImage+".png";        
        img.fusionaImagenes(destinationPath);        
        //Set the texture to the material        
        mat.setTexture("ColorMap", assetManager.loadTexture("Textures/FinalTexture"+indexImage+".png"));          
        model.setMaterial(mat);        
        //Delete the file        
        Path file = Paths.get(destinationPath);        
        try {        
            Files.delete(file);            
        } catch (IOException ex) {        
            System.out.println("Failed deleting file");            
        }        
    }
    
    public void changeColor(int color) throws IOException{
        ColoringImage coloringImage;
        Path file;
        BufferedImage coloredImage;
        switch(typeObject) {
            case t_shirt:
                coloringImage = new  ColoringImage(arrayTShirtColor[indexTShirt%numTShirts], arrayTShirtShadow[indexTShirt%numTShirts], color, TypeObject.t_shirt); 
                coloredImage = coloringImage.coloringImage();
                img = new ImagesProcessing(skinsPath, trousersColorPath, trousersShadowPath, coloredImage, eyesPath, 
                        shoesColorPath, shoesShadowPath);  
                break;
            case trouser:
                coloringImage = new  ColoringImage(arrayTrouserColor[indexTrouser%numTrousers], arrayTrouserShadow[indexTrouser%numTrousers], color, TypeObject.trouser);
                coloredImage = coloringImage.coloringImage();
                img = new ImagesProcessing(skinsPath, coloredImage, tShirtsColorPath, tShirtsShadowPath, eyesPath, 
                        shoesColorPath, shoesShadowPath);  
                break;      
            case shoes:
                coloringImage = new  ColoringImage(arrayShoesColor[indexShoes%numShoes], arrayShoesShadow[indexShoes%numShoes], color, TypeObject.shoes);
                coloredImage = coloringImage.coloringImage();
                img = new ImagesProcessing(skinsPath, trousersColorPath, trousersShadowPath, tShirtsColorPath, tShirtsShadowPath, 
                eyesPath, coloredImage);  
                break; 
        }     
        indexImage++; 
        destinationPath = "assets/Textures/FinalTexture"+indexImage+".png"; 
        img.fusionaImagenes(destinationPath); 
        mat.setTexture("ColorMap", assetManager.loadTexture("Textures/FinalTexture"+indexImage+".png"));     
        model.setMaterial(mat);
        //Delete the file               
        file = Paths.get(destinationPath);               
        try {                    
            Files.delete(file);                
        } catch (IOException ex) {                    
            System.out.println("Failed deleting file");                
        }
    }
    
    public void screenshot()
    {
        int height = this.viewPort.getCamera().getHeight();               
        int width = this.viewPort.getCamera().getWidth();   
        
        
        BufferedImage rawFrame = new BufferedImage(width, height,BufferedImage.TYPE_4BYTE_ABGR);        
        ByteBuffer byteBuffer = BufferUtils.createByteBuffer(width * height * 4 ); 
        
        FrameBuffer fram = this.viewPort.getOutputFrameBuffer();
        Renderer renderer1 = this.renderManager.getRenderer();
        renderer1.readFrameBuffer(fram, byteBuffer);        
        Screenshots.convertScreenShot(byteBuffer, rawFrame);       
        
        
        indexCaptura++;
        try {        
            if (indexCaptura < 10) 
            {
                ImageIO.write(rawFrame, "png", new File("assets/Textures/screenshots/Captura_0"+indexCaptura+".png"));
            } 
            else 
            {
                ImageIO.write(rawFrame, "png", new File("assets/Textures/screenshots/Captura_"+indexCaptura+".png"));
            }            
        } catch (IOException e) {        
            System.out.println("Error");            
        }
        
        //guiViewPort.addProcessor(niftyDisplay);
    }
    
    /** Custom Keybinding: Map named actions to inputs. */
    private void initKeys() 
    {
        inputManager.addMapping("ChangeSkin", new KeyTrigger(KeyInput.KEY_NUMPAD2));
        inputManager.addListener(actionListener, "ChangeSkin");
        inputManager.addMapping("ChangeShoes", new KeyTrigger(KeyInput.KEY_NUMPAD0));
        inputManager.addListener(actionListener, "ChangeShoes");
        inputManager.addMapping("ChangeTrouser", new KeyTrigger(KeyInput.KEY_NUMPAD1));
        inputManager.addListener(actionListener, "ChangeTrouser");
        inputManager.addMapping("ChangeTShirt", new KeyTrigger(KeyInput.KEY_NUMPAD4));
        inputManager.addListener(actionListener, "ChangeTShirt"); 
        inputManager.addMapping("ChangeEyes", new KeyTrigger(KeyInput.KEY_NUMPAD7));
        inputManager.addListener(actionListener, "ChangeEyes"); 
        inputManager.addMapping("Capture", new KeyTrigger(KeyInput.KEY_NUMPAD5));
        inputManager.addListener(actionListener, "Capture"); 
    }
  
    public ActionListener actionListener = new ActionListener() 
    {
        public void onAction(String name, boolean keyPressed, float tpf) 
        {
            if (name.equals("ChangeSkin") && !keyPressed) {
                changeSkin();
            }
            else if (name.equals("ChangeShoes") && !keyPressed) {
                changeShoes();
            }
            else if (name.equals("ChangeTrouser") && !keyPressed) {
                changeTrousers();
            }
            else if (name.equals("ChangeTShirt") && !keyPressed) {
                changeTShirt();
            }
             else if (name.equals("ChangeEyes") && !keyPressed) {
                changeEyes();
            }
            else if (name.equals("Capture") && !keyPressed) {          
                screenshot();          
            }
        }
    };
    
    public void showWindowChangeColorTShirt() throws InterruptedException
    {
        typeObject = TypeObject.t_shirt;
        ColorChooser window = new ColorChooser(this, guiViewPort); 
        guiViewPort.setEnabled(false);
    }
    
    public void showWindowChangeColorTrouser() throws InterruptedException
    {
        typeObject = TypeObject.trouser;
        ColorChooser window = new ColorChooser(this, guiViewPort); 
        guiViewPort.setEnabled(false);
    }
    
    public void showWindowChangeColorShoes() throws InterruptedException
    {
        typeObject = TypeObject.shoes;
        ColorChooser window = new ColorChooser(this, guiViewPort); 
        guiViewPort.setEnabled(false);
    }
    
    public Gender getGender() 
    {
        return gender;
    }

    public void setGender(Gender gender) 
    {
        this.gender = gender;
    }

    public Age getAge() 
    {
        return age;
    }

    public void setAge(Age age) 
    {
        this.age = age;
    }
    
    public void loadModel(){ //HACER
        readXML("assets/XML Configuration/configuration.xml", gender);
        DirectionalLight dl = new DirectionalLight();
        dl.setDirection(new Vector3f(-0.1f, -1f, -1).normalizeLocal());
        rootNode.addLight(dl);

        indexSkin++;
        skinsPath = arraySkin[indexSkin];
        indexShoes++;
        shoesColorPath = arrayShoesColor[indexShoes];
        shoesShadowPath = arrayShoesShadow[indexShoes];
        indexTrouser++;
        trousersColorPath = arrayTrouserColor[indexTrouser];
        trousersShadowPath = arrayTrouserShadow[indexTrouser];
        indexTShirt++;
        tShirtsColorPath = arrayTShirtColor[indexTShirt];
        tShirtsShadowPath = arrayTShirtShadow[indexTShirt];
        indexEyes++;
        eyesPath = arrayEyes[indexEyes];     

        img = new ImagesProcessing(skinsPath, trousersColorPath, trousersShadowPath, tShirtsColorPath, tShirtsShadowPath, 
                eyesPath, shoesColorPath, shoesShadowPath);
        destinationPath = "assets/Textures/OriginalTexture.png";
        img.fusionaImagenes(destinationPath);
        
        switch(gender) {
            case Male:
                model = assetManager.loadModel("Models/prueba/polySurfaceShape4.mesh.xml");
                mat = new Material(assetManager, "Common/MatDefs/Misc/Unshaded.j3md");
                mat.setTexture("ColorMap",assetManager.loadTexture("Textures/OriginalTexture.png"));  
                model.setMaterial(mat);
                model.rotate(1.5f, 0.0f, 0.0f);
                model.setLocalTranslation(0.0f, -3.0f, 0.0f);                

                control = model.getControl(AnimControl.class);
                channel = control.createChannel();
                channel.setAnim("my_animation");
                
                if (age == Age.Young){
                    //Scale the model
                    model.scale(0.65f, 0.65f, 0.65f);
                } 
                rootNode.attachChild(model);
                break;
            case Female:
                //Cargar el modelo de la mujer
                break;      
        }
        //Borrar la imagen
        Path file = Paths.get(destinationPath);
        try {
            Files.delete(file);
        } 
        catch (IOException ex) {
            System.out.println("Error al borrar el fichero");
        }
    }
    
    //This method read the XML document of configuration.
    private void readXML(String file, Gender gender1)
    {
        switch(gender) {
            case Male:
                try {
                    //Assing the file to the DOM doc.
                    File xmlFile = new File(file);
                    DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
                    DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
                    Document doc = dBuilder.parse(xmlFile);
                    doc.getDocumentElement().normalize();

                    //Read the doc.
                    //Read all nodes with name man
                    NodeList nListMan = doc.getElementsByTagName("man");
                    for (int temp = 0; temp < nListMan.getLength(); temp++) {
                       Node nNode = nListMan.item(temp); 
                       if (nNode.getNodeType() == Node.ELEMENT_NODE) {
                          //Here, we have the element man 
                          Element eElement = (Element) nNode;
                          NodeList nListObjects = eElement.getElementsByTagName("objects");
                          Node nNode2 = (Node) nListObjects.item(0);
                          if (nNode2.getNodeType() == Node.ELEMENT_NODE) {
                            //In eElement2, we have the node objects
                            Element eElement2 = (Element) nNode2;
                            NodeList nListSkins = eElement2.getElementsByTagName("skin");
                            
                            numSkins = nListSkins.getLength();
                            arraySkin = new String[numSkins];
                            
                            int indexSkinReaded;
                            for (indexSkinReaded = 0; indexSkinReaded < nListSkins.getLength(); indexSkinReaded++){
                                Node nNode3 = nListSkins.item(indexSkinReaded);
                                if (nNode3.getNodeType() == Node.ELEMENT_NODE) {
                                    //In element3, we have the node skin
                                    Element eElement3 = (Element) nNode3;
                                    NodeList nListPathSkin = eElement3.getElementsByTagName("path").item(0).getChildNodes();
                                    Node nValue = (Node) nListPathSkin.item(0);
                                    String pathSkinReaded = nValue.getNodeValue();
                                    //Print the data readed
                                    System.out.println("Skin "+indexSkinReaded+": " + pathSkinReaded);
                                    //Save the path of this skin
                                    arraySkin[indexSkinReaded] = pathSkinReaded;
                                }
                            }
                            NodeList nListEyes = eElement2.getElementsByTagName("eyes");
                            
                            numEyes = nListEyes.getLength();
                            arrayEyes = new String[numEyes];
                            
                            int indexEyesReaded;
                            for (indexEyesReaded = 0; indexEyesReaded < nListEyes.getLength(); indexEyesReaded++){
                                Node nNode4 = nListEyes.item(indexEyesReaded);
                                if (nNode4.getNodeType() == Node.ELEMENT_NODE) {
                                    //In element4, we have the node eyes
                                    Element eElement4 = (Element) nNode4;
                                    NodeList nListPathEyes = eElement4.getElementsByTagName("path").item(0).getChildNodes();
                                    Node nValue2 = (Node) nListPathEyes.item(0);
                                    String pathEyesReaded = nValue2.getNodeValue();
                                    //Print the data readed
                                    System.out.println("Eyes "+indexEyesReaded+": " + pathEyesReaded);
                                    //Save the path of these eyes
                                    arrayEyes[indexEyesReaded] = pathEyesReaded;
                                }
                            }
                            NodeList nListTShirts = eElement2.getElementsByTagName("tshirt");
                            
                            numTShirts = nListTShirts.getLength();
                            arrayTShirtColor = new String[numTShirts];
                            arrayTShirtShadow = new String[numTShirts];
                            
                            int indexTShirtReaded;
                            for (indexTShirtReaded = 0; indexTShirtReaded < nListTShirts.getLength(); indexTShirtReaded++){
                                Node nNode5 = nListTShirts.item(indexTShirtReaded);
                                if (nNode5.getNodeType() == Node.ELEMENT_NODE) {
                                    //In element5, we have the node tshirt
                                    Element eElement5 = (Element) nNode5;
                                    NodeList nListPathColorTshirt = eElement5.getElementsByTagName("pathColor").item(0).getChildNodes();
                                    Node nValue3 = (Node) nListPathColorTshirt.item(0);
                                    String pathTShirtColorReaded = nValue3.getNodeValue();
                                    //Print the data readed
                                    System.out.println("TShirt "+indexTShirtReaded+": " + pathTShirtColorReaded);
                                    //Save the path of this tshirt
                                    arrayTShirtColor[indexTShirtReaded] = pathTShirtColorReaded;

                                    NodeList nListPathShadowTshirt = eElement5.getElementsByTagName("pathShadow").item(0).getChildNodes();
                                    Node nValue4 = (Node) nListPathShadowTshirt.item(0);
                                    String pathTShirtShadowReaded = nValue4.getNodeValue();
                                    //Print the data readed
                                    System.out.println("TShirt "+indexTShirtReaded+": " + pathTShirtShadowReaded);
                                    //Save the path of this tshirt
                                    arrayTShirtShadow[indexTShirtReaded] = pathTShirtShadowReaded;
                                }
                            }
                            NodeList nListTrousers = eElement2.getElementsByTagName("trouser");
                            
                            numTrousers = nListTrousers.getLength();
                            arrayTrouserColor = new String[numTrousers];
                            arrayTrouserShadow = new String[numTrousers];
                            
                            int indexTrouserReaded;
                            for (indexTrouserReaded = 0; indexTrouserReaded < nListTrousers.getLength(); indexTrouserReaded++){
                                Node nNode6 = nListTrousers.item(indexTrouserReaded);
                                if (nNode6.getNodeType() == Node.ELEMENT_NODE) {
                                    //In element6, we have the node trouser
                                    Element eElement6 = (Element) nNode6;
                                    NodeList nListPathColorTrouser = eElement6.getElementsByTagName("pathColor").item(0).getChildNodes();
                                    Node nValue5 = (Node) nListPathColorTrouser.item(0);
                                    String pathTrouserColorReaded = nValue5.getNodeValue();
                                    //Print the data readed
                                    System.out.println("Trouser "+indexTrouserReaded+": " + pathTrouserColorReaded);
                                    //Save the path of this trouser
                                    arrayTrouserColor[indexTrouserReaded] = pathTrouserColorReaded;

                                    NodeList nListPathShadowTrouser = eElement6.getElementsByTagName("pathShadow").item(0).getChildNodes();
                                    Node nValue6 = (Node) nListPathShadowTrouser.item(0);
                                    String pathTrouserShadowReaded = nValue6.getNodeValue();
                                    //Print the data readed
                                    System.out.println("Trouser "+indexTrouserReaded+": " + pathTrouserShadowReaded);
                                    //Save the path of this tshirt
                                    arrayTrouserShadow[indexTrouserReaded] = pathTrouserShadowReaded;
                                }
                            }
                            NodeList nListShoes = eElement2.getElementsByTagName("shoes");
                            
                            numShoes = nListShoes.getLength();
                            arrayShoesColor = new String[numShoes];
                            arrayShoesShadow = new String[numShoes];
                            
                            int indexShoesReaded;
                            for (indexShoesReaded = 0; indexShoesReaded < nListShoes.getLength(); indexShoesReaded++){
                                Node nNode7 = nListShoes.item(indexShoesReaded);
                                if (nNode7.getNodeType() == Node.ELEMENT_NODE) {
                                    //In element7, we have the node shoes
                                    Element eElement7 = (Element) nNode7;
                                    NodeList nListPathColorShoes = eElement7.getElementsByTagName("pathColor").item(0).getChildNodes();
                                    Node nValue7 = (Node) nListPathColorShoes.item(0);
                                    String pathShoesColorReaded = nValue7.getNodeValue();
                                    //Print the data readed
                                    System.out.println("Eyes "+indexShoesReaded+": " + pathShoesColorReaded);
                                    //Save the path of this trouser
                                    arrayShoesColor[indexShoesReaded] = pathShoesColorReaded;

                                    NodeList nListPathShadowShoes = eElement7.getElementsByTagName("pathShadow").item(0).getChildNodes();
                                    Node nValue8 = (Node) nListPathShadowShoes.item(0);
                                    String pathShoesShadowReaded = nValue8.getNodeValue();
                                    //Print the data readed
                                    System.out.println("Eyes "+indexShoesReaded+": " + pathShoesShadowReaded);
                                    //Save the path of this tshirt
                                    arrayShoesShadow[indexShoesReaded] = pathShoesShadowReaded;
                                }
                            }
                          }
                       }
                    }
                } catch (Exception e) {
                      e.printStackTrace();
                }
                break;
            case Female:
                break;
        }     
    }
}
