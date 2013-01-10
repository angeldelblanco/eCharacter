package Model;

import com.jme3.animation.AnimChannel;
import com.jme3.animation.AnimControl;
import com.jme3.animation.Bone;
import com.jme3.animation.LoopMode;
import com.jme3.app.state.ScreenshotAppState;
import com.jme3.material.Material;
import com.jme3.math.Quaternion;
import com.jme3.math.Vector3f;
import com.jme3.niftygui.NiftyJmeDisplay;
import com.jme3.renderer.ViewPort;
import com.jme3.scene.Spatial;
import gui_nifty.Gui;
import imagesProcessing.ColoringImage;
import imagesProcessing.ImagesProcessing;
import animation.ScreenshotThread;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.util.Iterator;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import types.Age;
import types.Gender;
import types.TypeObject;

public class Model{

    private Spatial model;
    private Material mat;
    private Vector3f vectorScaleBase;
    private AnimChannel channel;
    private AnimControl control;
    private Age age;
    private Gender gender;
    
    private ImagesProcessing img;
    private BufferedImage skin, trousers, tShirt, eyes, shoes;
    
    private String [] arraySkin, arrayShoesColor, arrayShoesShadow, arrayTrouserColor, arrayTrouserShadow, arrayTShirtColor, 
            arrayTShirtShadow, arrayEyes;
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
    
    private int indexImage;
    private String destinationPath;
    private String modelPath;
    
    private TypeObject typeObject;
    
    public Model(){}
    
    public void scale(String[] namesBones,float inc)
    {
        Bone b;
        Vector3f vector;
        for(int i = 0; i < namesBones.length ; i++)
        {
            b = control.getSkeleton().getBone(namesBones[i]);
            vector = vectorScaleBase.mult(inc);
            b.setUserControl(true);
            b.setUserTransforms(Vector3f.ZERO,Quaternion.IDENTITY,vector);
        }
    }
    
    public void screenShot(ViewPort guiViewPort, NiftyJmeDisplay niftyDisplay, ScreenshotAppState screenShotState)
    {
        guiViewPort.removeProcessor(niftyDisplay);
        Set<String> namesAnimations = (Set<String>) control.getAnimationNames();
        ScreenshotThread sst = new ScreenshotThread(screenShotState,channel,guiViewPort,niftyDisplay,namesAnimations);
        sst.start();
    }
    
    public void changeSkin(int steep)
    {
        indexSkin=indexSkin+steep;
        if (indexSkin < 0){ indexSkin = numSkins - 1;}
        indexSkin = indexSkin%numSkins;
        skin = readBuffer(arraySkin[indexSkin]);
        //Creat the image             
        img = new ImagesProcessing(skin, trousers, tShirt, eyes, shoes);                
        destinationPath = "assets/Textures/FinalTexture"+indexImage+".png";                
        img.fusionaImagenes(destinationPath);                              
    }
    
    public void changeShoes()
    {
        indexShoes++;   
        shoes = readBuffer(arrayShoesColor[indexShoes%numShoes], arrayShoesShadow[indexShoes%numShoes], TypeObject.shoes);
        //Creat the image        
        img = new ImagesProcessing(skin, trousers, tShirt, eyes, shoes); 
        destinationPath = "assets/Textures/FinalTexture"+indexImage+".png";                
        img.fusionaImagenes(destinationPath);                
    }
    
    public void changeTrousers(int steep)
    {
        indexTrouser=indexTrouser+steep;
        if (indexTrouser < 0){ indexTrouser = numTrousers - 1;}
        indexTrouser = indexTrouser%numTrousers;   
        trousers = readBuffer(arrayTrouserColor[indexTrouser], arrayTrouserShadow[indexTrouser], TypeObject.trouser);
        //Creat the image        
        img = new ImagesProcessing(skin, trousers, tShirt, eyes, shoes);     
        destinationPath = "assets/Textures/FinalTexture"+indexImage+".png";        
        img.fusionaImagenes(destinationPath);        
    }
    
    public void changeTShirt(int steep)
    {
        indexTShirt=indexTShirt+steep;
        if (indexTShirt < 0){ indexTShirt = numTShirts - 1;}
        indexTShirt = indexTShirt%numTShirts;
        tShirt = readBuffer(arrayTShirtColor[indexTShirt], arrayTShirtShadow[indexTShirt], TypeObject.t_shirt);
        //Creat the image        
        img = new ImagesProcessing(skin, trousers, tShirt, eyes, shoes);     
        destinationPath = "assets/Textures/FinalTexture"+indexImage+".png";        
        img.fusionaImagenes(destinationPath);        
    }
    
    public void changeEyes(int steep)
    {
        indexEyes=indexEyes+steep;
        if (indexEyes < 0){ indexEyes = numEyes - 1;}
        indexEyes = indexEyes%numEyes;
        eyes = readBuffer(arrayEyes[indexEyes]);
        //Creat the image        
        img = new ImagesProcessing(skin, trousers, tShirt, eyes, shoes); 
        destinationPath = "assets/Textures/FinalTexture"+indexImage+".png";        
        img.fusionaImagenes(destinationPath);              
    }
    
    public void changeColor(int color) throws IOException
    {
        ColoringImage coloringImage;
        switch(typeObject) {
            case t_shirt:
                coloringImage = new  ColoringImage(arrayTShirtColor[indexTShirt%numTShirts], arrayTShirtShadow[indexTShirt%numTShirts], color, TypeObject.t_shirt); 
                tShirt = coloringImage.coloringImage();      
                break;
            case trouser:
                coloringImage = new  ColoringImage(arrayTrouserColor[indexTrouser%numTrousers], arrayTrouserShadow[indexTrouser%numTrousers], color, TypeObject.trouser);
                trousers = coloringImage.coloringImage(); 
                break;      
            case shoes:
                coloringImage = new  ColoringImage(arrayShoesColor[indexShoes%numShoes], arrayShoesShadow[indexShoes%numShoes], color, TypeObject.shoes);
                shoes = coloringImage.coloringImage();
                break; 
        }    
        img = new ImagesProcessing(skin, trousers, tShirt, eyes, shoes); 
        destinationPath = "assets/Textures/FinalTexture"+indexImage+".png"; 
        img.fusionaImagenes(destinationPath); 
    }

    public void readBuffers()
    {
        indexSkin++;
        String skinsPath = arraySkin[indexSkin];
        indexShoes++;
        String shoesColorPath = arrayShoesColor[indexShoes];
        String shoesShadowPath = arrayShoesShadow[indexShoes];
        indexTrouser++;
        String trousersColorPath = arrayTrouserColor[indexTrouser];
        String trousersShadowPath = arrayTrouserShadow[indexTrouser];
        indexTShirt++;
        String tShirtsColorPath = arrayTShirtColor[indexTShirt];
        String tShirtsShadowPath = arrayTShirtShadow[indexTShirt];
        indexEyes++;
        String eyesPath = arrayEyes[indexEyes]; 
        
        try {
            skin = ImageIO.read(new File(skinsPath));
            ColoringImage coloredImage = new ColoringImage(trousersColorPath, trousersShadowPath, -3394561, TypeObject.trouser);
            trousers = coloredImage.coloringImage();
            coloredImage = new ColoringImage(tShirtsColorPath, tShirtsShadowPath, -3394561, TypeObject.t_shirt);
            tShirt = coloredImage.coloringImage();
            eyes = ImageIO.read(new File(eyesPath));
            coloredImage = new ColoringImage(shoesColorPath, shoesShadowPath, -3394561, TypeObject.shoes);
            shoes = coloredImage.coloringImage();
        } catch (IOException ex) {
            Logger.getLogger(Gui.class.getName()).log(Level.SEVERE, null, ex);
        }     
        img = new ImagesProcessing(skin, trousers, tShirt, eyes, shoes);
        destinationPath = "assets/Textures/OriginalTexture.png";
        img.fusionaImagenes(destinationPath);       
    }
    
    private BufferedImage readBuffer(String path)
    {
        try {
            BufferedImage aux = ImageIO.read(new File(path));
            return aux;
        } catch (IOException ex) {
            Logger.getLogger(Gui.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }
    
    private BufferedImage readBuffer(String pathColor, String pathShadow, TypeObject typeObject)
    {
        try {
            ColoringImage coloredImage = new ColoringImage(pathColor, pathShadow, -3394561, typeObject);
            BufferedImage aux = coloredImage.coloringImage();
            return aux;
        } catch (IOException ex) {
            Logger.getLogger(Gui.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }

    public Spatial getModel() 
    {
        return model;
    }

    public void setModel(Spatial model,Material mat) 
    {
        this.model = model;
        this.mat = mat;
        this.model.setMaterial(mat);
        this.model.rotate(1.5f, 0.0f, 0.0f);
        this.model.setLocalTranslation(0.0f, -3.70f, 0.0f);
        this.vectorScaleBase = model.getLocalScale();
        this.control = model.getControl(AnimControl.class);
        this.channel = this.control.createChannel();
        Iterator<String> it = this.control.getAnimationNames().iterator();
        this.channel.setAnim(it.next());
        this.channel.setLoopMode(LoopMode.Loop);
        if (this.age == Age.Young){
            //Scale the model
            model.scale(0.65f, 0.65f, 0.65f);
        }
    }

    public Material getMaterial() 
    {
        return mat;
    }

    public void setMaterial(Material mat) 
    {
        this.mat = mat;
        this.model.setMaterial(this.mat);
    }

    public Vector3f getVectorScaleBase() 
    {
        return vectorScaleBase;
    }

    public void setVectorScaleBase(Vector3f vectorScaleBase) 
    {
        this.vectorScaleBase = vectorScaleBase;
    }
    
    public Age getAge() 
    {
        return age;
    }

    public void setAge(Age age) 
    {
        this.age = age; 
    }
    
    public Gender getGender()
    {
        return gender;
    }
    
    public void setGender(Gender gender)
    {
        this.gender = gender;
    }

    public void setIndexImage(int indexImage) 
    {
        this.indexImage = indexImage;
    }   
    
    public TypeObject getTypeObject() 
    {
        return typeObject;
    }

    public void setTypeObject(TypeObject typeObject) 
    {
        this.typeObject = typeObject;
    }

    public void setArraySkin(String[] arraySkin) 
    {
        this.arraySkin = arraySkin;
    }

    public void setArrayShoesColor(String[] arrayShoesColor) 
    {
        this.arrayShoesColor = arrayShoesColor;
    }

    public void setArrayShoesShadow(String[] arrayShoesShadow) 
    {
        this.arrayShoesShadow = arrayShoesShadow;
    }

    public void setArrayTrouserColor(String[] arrayTrouserColor) 
    {
        this.arrayTrouserColor = arrayTrouserColor;
    }

    public void setArrayTrouserShadow(String[] arrayTrouserShadow) 
    {
        this.arrayTrouserShadow = arrayTrouserShadow;
    }

    public void setArrayTShirtColor(String[] arrayTShirtColor) 
    {
        this.arrayTShirtColor = arrayTShirtColor;
    }

    public void setArrayTShirtShadow(String[] arrayTShirtShadow) 
    {
        this.arrayTShirtShadow = arrayTShirtShadow;
    }

    public void setArrayEyes(String[] arrayEyes) 
    {
        this.arrayEyes = arrayEyes;
    }

    public void setNumSkins(int numSkins) 
    {
        this.numSkins = numSkins;
    }

    public void setNumShoes(int numShoes) 
    {
        this.numShoes = numShoes;
    }

    public void setNumTrousers(int numTrousers) 
    {
        this.numTrousers = numTrousers;
    }

    public void setNumTShirts(int numTShirts) 
    {
        this.numTShirts = numTShirts;
    }

    public void setNumEyes(int numEyes) 
    {
        this.numEyes = numEyes;
    }
    
    public String getModelPath() 
    {
        return modelPath;
    }
    
    public void setModelPath(String modelPath) 
    {
        this.modelPath = modelPath;
    }
}