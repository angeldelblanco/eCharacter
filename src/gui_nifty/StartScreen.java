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
        skins = new ImageBuilder[gui.lengthSkins()];
        for(int i=0; i<gui.lengthSkins(); i++){
            skins[i] = new ImageBuilder(){{
                width("0%");
                height("0%");
            }};
            skins[i].id("i"+Integer.toString(i));
            skins[i].filename(gui.pathSkin(i));
            skins[i].interactOnClick("changeTexture("+Integer.toString(i)+")");
            skins[i].build(nifty, nifty.getScreen("skinScreen"), nifty.getScreen("skinScreen").findElementByName("t"+Integer.toString(i%6)));
        }
        eyes = new ImageBuilder[gui.lengthEyes()];
        for(int i=0; i<gui.lengthEyes(); i++){
            eyes[i] = new ImageBuilder(){{
                width("0%");
                height("0%");
            }};
            eyes[i].id("i"+Integer.toString(i));
            eyes[i].filename(gui.pathEyes(i));
            eyes[i].interactOnClick("changeTexture("+Integer.toString(i)+")");
            eyes[i].build(nifty, nifty.getScreen("eyesScreen"), nifty.getScreen("eyesScreen").findElementByName("t"+Integer.toString(i%6)));
        }
        tshirts = new ImageBuilder[gui.lengthTShirt()];
        for(int i=0; i<gui.lengthTShirt(); i++){
            tshirts[i] = new ImageBuilder(){{
                width("0%");
                height("0%");
            }};
            tshirts[i].id("i"+Integer.toString(i));
            tshirts[i].filename(gui.pathTshirt(i));
            tshirts[i].interactOnClick("changeTexture("+Integer.toString(i)+")");
            tshirts[i].build(nifty, nifty.getScreen("tshirtScreen"), nifty.getScreen("tshirtScreen").findElementByName("t"+Integer.toString(i%6)));
        }
        trousers = new ImageBuilder[gui.lengthTrouser()];
        for(int i=0; i<gui.lengthTrouser(); i++){
            trousers[i] = new ImageBuilder(){{
                interactOnClick("gui.changeTrousers("+get("id")+")");
                width("0%");
                height("0%");
            }};
            trousers[i].id("i"+Integer.toString(i));
            trousers[i].filename(gui.pathTrouser(i));
            trousers[i].interactOnClick("changeTexture("+Integer.toString(i)+")");
            trousers[i].build(nifty, nifty.getScreen("trousersScreen"), nifty.getScreen("trousersScreen").findElementByName("t"+Integer.toString(i%6)));
        }
        shoes = new ImageBuilder[gui.lengthShoes()];
        for(int i=0; i<gui.lengthShoes(); i++){
            shoes[i] = new ImageBuilder(){{
                width("0%");
                height("0%");
            }};
            shoes[i].id("i"+Integer.toString(i));
            shoes[i].filename(gui.pathShoes(i));
            shoes[i].interactOnClick("changeTexture("+Integer.toString(i)+")");
            shoes[i].build(nifty, nifty.getScreen("shoesScreen"), nifty.getScreen("shoesScreen").findElementByName("t"+Integer.toString(i%6)));
        }
    }
    
    public void changeScreen(String param)
    {
        selection = param;
        nifty.gotoScreen(param);
        if(param.equals("skinScreen")||param.equals("eyesScreen")||param.equals("tshirtScreen")||param.equals("trousersScreen")||param.equals("shoesScreen")){
            for(int i=0; i<gui.length(param); i++){
                if(i<6){
                    nifty.getScreen(param).findElementByName("i"+Integer.toString(i)).setVisible(true);
                    nifty.getScreen(param).findElementByName("i"+Integer.toString(i)).setHeight(nifty.getScreen(param).findElementByName("t"+Integer.toString(i%6)).getHeight());
                    nifty.getScreen(param).findElementByName("i"+Integer.toString(i)).setWidth(nifty.getScreen(param).findElementByName("t"+Integer.toString(i%6)).getWidth());
                }
            }
            nifty.getScreen(param).findElementByName("leftT").disable();
            nifty.getScreen(param).findElementByName("leftT").setVisible(false);
            if(gui.length(param) < 6){
                nifty.getScreen(param).findElementByName("rightT").disable();
                nifty.getScreen(param).findElementByName("rightT").setVisible(false);
            }
        }
        if(param.equals("hairScreen")){
        }
        if(param.equals("accesoriesScreen")){
        }
        if(param.equals("bonesScreen")){
        }
        if(param.equals("basicScreen")){
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
    
    public void changeTexture(String steep)
    {
        if(selection.equals("skinScreen")){gui.changeSkin(Integer.parseInt(steep));}
        if(selection.equals("hairScreen")){}
        if(selection.equals("eyesScreen")){gui.changeEyes(Integer.parseInt(steep));}
        if(selection.equals("tshirtScreen")){gui.changeTShirt(Integer.parseInt(steep));}
        if(selection.equals("trousersScreen")){gui.changeTrousers(Integer.parseInt(steep));}
        if(selection.equals("shoesScreen")){}
        if(selection.equals("accesoriesScreen")){}
    }
    
    public void showWindowChangeColor() throws InterruptedException
    {
        if(selection.equals("skinScreen")){}
        if(selection.equals("hairScreen")){}
        if(selection.equals("eyesScreen")){}
        if(selection.equals("tshirtScreen")){gui.showWindowChangeColorTShirt();}
        if(selection.equals("trousersScreen")){gui.showWindowChangeColorTrouser();}
        if(selection.equals("shoesScreen")){gui.showWindowChangeColorShoes();}
        if(selection.equals("accesoriesScreen")){}
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