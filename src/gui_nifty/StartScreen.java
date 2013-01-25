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

import com.jme3.app.Application;
import com.jme3.app.state.AbstractAppState;
import com.jme3.app.state.AppStateManager;
import de.lessvoid.nifty.Nifty;
import de.lessvoid.nifty.NiftyEventSubscriber;
import de.lessvoid.nifty.controls.SliderChangedEvent;
import de.lessvoid.nifty.screen.Screen;
import de.lessvoid.nifty.screen.ScreenController;
import de.lessvoid.nifty.builder.ImageBuilder;
import de.lessvoid.nifty.elements.Element;
import types.Age;
import types.Gender;

public class StartScreen extends AbstractAppState implements ScreenController {
    
    private Nifty nifty;
    private Application app;
    private Screen screen;
    private Gui gui;
    private String selection;
    private ImageBuilder [] skins, eyes, tshirts, trousers, shoes;
    
    public StartScreen(Gui gui){
        this.gui = gui;
    }
    
    public void startGame(String nextScreen) {
        nifty.gotoScreen(nextScreen);  // switch to another screen
    }

    public void quitGame() {
        app.stop();
    }

    public void bind(Nifty nifty, Screen screen) {
        this.nifty = nifty;
        this.screen = screen;
    }

    public void onStartScreen() {}

    public void onEndScreen() {}
    
    @Override
    public void initialize(AppStateManager stateManager, Application app) 
    {
        this.app = app;
    }
    
    public void manSelected(String nextScreen) 
    {
        nifty.gotoScreen(nextScreen);  // switch to another screen
        gui.setGender(Gender.Male);
    }
    
    public void womanSelected(String nextScreen) 
    {
        nifty.gotoScreen(nextScreen);  // switch to another screen
        gui.setGender(Gender.Female);
    }
    
    public void adultSelected(String nextScreen) 
    {
        nifty.gotoScreen(nextScreen);  // switch to another screen
        gui.setAgeModel(Age.Adult);
        gui.loadModel();
        initIcons();
    }
    
    public void youngSelected(String nextScreen) 
    {
        nifty.gotoScreen(nextScreen);  // switch to another screen 
        gui.setAgeModel(Age.Young);
        gui.loadModel();
        initIcons();
    }
    
    public void initIcons(){
        /*String[] s = new String[4];
        s[0] = "Interface/Icons/IconoPielBlancaBoy.png";
        s[1] = "Interface/Icons/IconoPielNegraBoy.png";
        s[2] = "Interface/Icons/IconoPielAmarillaBoy.png";
        s[3] = "Interface/Icons/IconoPielRojaBoy.png";*/
        
        String[] s = gui.getArraySkinIcon();
        skins = new ImageBuilder[gui.lengthSkins()];
        for(int i=0; i<gui.lengthSkins(); i++){
            skins[i] = new ImageBuilder(){{
                width("0%");
                height("0%");
            }};
            skins[i].id("s"+Integer.toString(i));
            //skins[i].filename(gui.pathSkin(i));
            skins[i].filename(s[i]);
            skins[i].interactOnClick("changeSkin("+Integer.toString(i)+")");
            skins[i].build(nifty, nifty.getScreen("skinScreen"), nifty.getScreen("skinScreen").findElementByName("t"+Integer.toString(i%4)));
        }
        /*eyes = new ImageBuilder[gui.lengthEyes()];
        for(int i=0; i<gui.lengthEyes(); i++){
            eyes[i].id(Integer.toString(i));
            eyes[i] = new ImageBuilder(){{
                interactOnClick("gui.changeEyes("+get("id")+")");
                width("0%");
                height("0%");
            }};
            eyes[i].build(nifty, nifty.getScreen("eyesScreen"), nifty.getScreen("eyesScreen").findElementByName("t"+Integer.toString(i%4)));
        }
        tshirts = new ImageBuilder[gui.lengthTShirt()];
        for(int i=0; i<gui.lengthTShirt(); i++){
            tshirts[i].id(Integer.toString(i));
            tshirts[i] = new ImageBuilder(){{
                interactOnClick("gui.changeTShirt("+get("id")+")");
                width("0%");
                height("0%");
            }};
            tshirts[i].build(nifty, nifty.getScreen("tshirtScreen"), nifty.getScreen("tshirtScreen").findElementByName("t"+Integer.toString(i%4)));
        }
        trousers = new ImageBuilder[gui.lengthTrouser()];
        for(int i=0; i<gui.lengthTrouser(); i++){
            trousers[i].id(Integer.toString(i));
            trousers[i] = new ImageBuilder(){{
                interactOnClick("gui.changeTrousers("+get("id")+")");
                width("0%");
                height("0%");
            }};
            trousers[i].build(nifty, nifty.getScreen("trousersScreen"), nifty.getScreen("trousersScreen").findElementByName("t"+Integer.toString(i%4)));
        }
        shoes = new ImageBuilder[gui.lengthShoes()];
        for(int i=0; i<gui.lengthShoes(); i++){
            shoes[i].id(Integer.toString(i));
            shoes[i] = new ImageBuilder(){{
                interactOnClick("gui.changeShoes("+get("id")+")");
                width("0%");
                height("0%");
            }};
            shoes[i].build(nifty, nifty.getScreen("shoesScreen"), nifty.getScreen("shoesScreen").findElementByName("t"+Integer.toString(i%4)));
        }*/
    }
    
    public void changeScreen(String param)
    {
        selection = param;
        if(param.equals("skin")){
            nifty.gotoScreen("skinScreen");
            for(int i=0; i<gui.lengthSkins(); i++){
                if(i<4){
                    nifty.getScreen("skinScreen").findElementByName("s"+Integer.toString(i)).setVisible(true);
                    nifty.getScreen("skinScreen").findElementByName("s"+Integer.toString(i)).setHeight(nifty.getScreen("skinScreen").findElementByName("t"+Integer.toString(i%4)).getHeight());
                    nifty.getScreen("skinScreen").findElementByName("s"+Integer.toString(i)).setWidth(nifty.getScreen("skinScreen").findElementByName("t"+Integer.toString(i%4)).getWidth());
                }
            }
        }
        if(param.equals("hair")){
            nifty.gotoScreen("hairScreen");
        }
        if(param.equals("eyes")){
            nifty.gotoScreen("eyesScreen");
        }
        if(param.equals("tshirt")){
            nifty.gotoScreen("tshirtScreen");
        }
        if(param.equals("trousers")){
            nifty.gotoScreen("trousersScreen");
        }
        if(param.equals("shoes")){
            nifty.gotoScreen("shoesScreen");
        }
        if(param.equals("accesories")){
            nifty.gotoScreen("accesoriesScreen");
        }
        if(param.equals("bones")){
            nifty.gotoScreen("bonesScreen");
        }
        if(param.equals("basic")){
            nifty.gotoScreen("basicScreen");
        }
    }
    
    public String getMenu(String param)
    {
        if(param.equals("yes")){
            return "Interface/MenuRojo.png";
        }
        if(param.equals("no")){
            return "Interface/MenuAzul.png";
        }
        if(param.equals("an")){
            return "Interface/MenuAzulAntiguo.png";
        }
        if(param.equals("ay")){
            return "Interface/MenuRojoAntiguo.png";
        }
        return null;
    }
    
    public String getButton(String param)
    {
        if(param.equals("left")){
            return "Interface/ant.png";
        }
        if(param.equals("right")){
            return "Interface/sig.png";
        }
        if(param.equals("color")){
            return "Interface/ColorButton.png";
        }
        if(param.equals("next")){
            return "Interface/next.png";
        }
        if(param.equals("previous")){
            return "Interface/previous.png";
        }
        return null;
    }
    
    public void changeSkin(String steep)
    {
        gui.changeSkin(Integer.parseInt(steep));
    }
    
    public void changeTexture(String steep)
    {
        int i;
        if(steep.equals("+")){i = 1;}
        else{i = -1;}
        if(selection.equals("skin")){gui.changeSkin(i);}
        if(selection.equals("hair")){}
        if(selection.equals("eyes")){gui.changeEyes(i);}
        if(selection.equals("tshirt")){gui.changeTShirt(i);}
        if(selection.equals("trousers")){gui.changeTrousers(i);}
        if(selection.equals("shoes")){}
        if(selection.equals("accesories")){}
    }
    
    public void showWindowChangeColor() throws InterruptedException
    {
        if(selection.equals("skin")){}
        if(selection.equals("hair")){}
        if(selection.equals("eyes")){}
        if(selection.equals("tshirt")){gui.showWindowChangeColorTShirt();}
        if(selection.equals("trousers")){gui.showWindowChangeColorTrouser();}
        if(selection.equals("shoes")){gui.showWindowChangeColorShoes();}
        if(selection.equals("accesories")){}
    }
    
    @NiftyEventSubscriber(id="head")
    public void onHeadSliderChange(final String id, final SliderChangedEvent event) 
    {
        float inc = 1.0f + event.getValue() * 0.01f;
        gui.scaleHead(inc);
    }
    
    @NiftyEventSubscriber(id="torso")
    public void onTorsoSliderChange(final String id, final SliderChangedEvent event) 
    {
        float inc = 1.0f + event.getValue() * 0.01f;
        gui.scaleTorax(inc);
    }
    
    @NiftyEventSubscriber(id="hands")
    public void onHandsSliderChange(final String id, final SliderChangedEvent event) 
    {
        float inc = 1.0f + event.getValue() * 0.01f;
        gui.scaleHands(inc);
    }
    
    @NiftyEventSubscriber(id="feet")
    public void onFeetSliderChange(final String id, final SliderChangedEvent event) 
    {
        float inc = 1.0f + event.getValue() * 0.01f;
        gui.scaleFeet(inc);
    }
    
    @NiftyEventSubscriber(id="legs")
    public void onLegsSliderChange(final String id, final SliderChangedEvent event) 
    {
        float inc = 1.0f + event.getValue() * 0.01f;
        gui.scaleLegs(inc);
    }
    
    @NiftyEventSubscriber(id="arms")
    public void onArmsSliderChange(final String id, final SliderChangedEvent event) 
    {
        float inc = 1.0f + event.getValue() * 0.01f;
        gui.scaleArms(inc);
    }
    
    public void screenshot() 
    {
        gui.screenshot();
        nifty.gotoScreen("finishScreen");
        gui.setBodyType(0);
    }
    
    public void changeBodyType(String bodyType)
    {
        if(bodyType.equals("Normal")){gui.setBodyType(0);}
        if(bodyType.equals("Tall")){gui.setBodyType(1);}
        if(bodyType.equals("Small")) {gui.setBodyType(2);}
        if(bodyType.equals("Heavy")) {gui.setBodyType(3);}
        if(bodyType.equals("Thin")) {gui.setBodyType(4);}
    }
}