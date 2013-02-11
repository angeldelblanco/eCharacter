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
import de.lessvoid.nifty.builder.ImageBuilder;
import de.lessvoid.nifty.builder.PanelBuilder;
import de.lessvoid.nifty.builder.PopupBuilder;
import de.lessvoid.nifty.controls.ButtonClickedEvent;
import de.lessvoid.nifty.controls.SliderChangedEvent;
import de.lessvoid.nifty.controls.button.builder.ButtonBuilder;
import de.lessvoid.nifty.controls.label.builder.LabelBuilder;
import de.lessvoid.nifty.controls.slider.builder.SliderBuilder;
import de.lessvoid.nifty.elements.Element;
import de.lessvoid.nifty.elements.render.PanelRenderer;
import de.lessvoid.nifty.screen.Screen;
import de.lessvoid.nifty.screen.ScreenController;
import de.lessvoid.nifty.tools.Color;
import java.io.IOException;
import types.Age;
import types.Gender;

public class StartScreen extends AbstractAppState implements ScreenController {
    
    private static final int TEXTURES_PAGE = 6;
    private Nifty nifty;
    private Application app;
    private Screen screen;
    private Gui gui;
    private String selection;
    private int skinspage, eyespage, tshirtspage, trouserspage, shoespage;
    private Element popup;
    private float red, green, blue;
    
    public StartScreen(Gui gui){
        this.gui = gui;
    }
    
    public void startGame(String nextScreen) {
        nifty.gotoScreen(nextScreen);  // switch to another screen
        new PopupBuilder("popupColor") {{
                childLayoutCenter();
                backgroundColor("#fffa");
                panel(new PanelBuilder("popupPanel") {{
                    backgroundImage("Interface/CuadroAzul.png");
                    height("25%");
                    width("25%");
                    childLayoutVertical();
                    panel(new PanelBuilder("panelSelection") {{
                        height("80%");
                        childLayoutHorizontal();
                        panel(new PanelBuilder("panelRed") {{
                            childLayoutVertical();
                            height("90%");
                            width("25%");
                            valignCenter();
                            control(new SliderBuilder("sliderR", true){{
                                max(255);
                                min(0);
                                initial(0);
                            }});
                            control(new LabelBuilder() {{
                                alignCenter();
                                text("Red");
                                width("100%");
                            }});
                        }});
                        panel(new PanelBuilder("panelGreen") {{
                            childLayoutVertical();
                            height("90%");
                            width("25%");
                            valignCenter();
                            control(new SliderBuilder("sliderG", true){{
                                max(255);
                                min(0);
                                initial(0);
                            }});
                            control(new LabelBuilder() {{
                                alignCenter();
                                text("Green");
                                width("100%");
                            }});
                        }});
                        panel(new PanelBuilder("panelBlue") {{
                            childLayoutVertical();
                            height("90%");
                            width("25%");
                            valignCenter();
                            control(new SliderBuilder("sliderB", true){{
                                max(255);
                                min(0);
                                initial(0);
                            }});
                            control(new LabelBuilder() {{
                                alignCenter();
                                text("Blue");
                                width("100%");
                            }});
                        }});
                        panel(new PanelBuilder("panelColor") {{
                            backgroundColor("#000f");
                            valignCenter();
                            alignCenter();
                            height("25%");
                            width("15%");
                        }});
                    }});
                    panel(new PanelBuilder("panelButton") {{
                            childLayoutHorizontal();
                            height("20%");
                            panel(new PanelBuilder("panelAcept") {{
                                height("90%");
                                width("50%");
                                valignCenter();
                                childLayoutCenter();
                                control(new ButtonBuilder("aceptButton", "Acept"));
                            }});
                            panel(new PanelBuilder("panelCancel") {{
                                height("90%");
                                width("50%");
                                valignCenter();
                                childLayoutCenter();
                                control(new ButtonBuilder("cancelButton", "Cancel"));
                            }});
                    }});
                }});
        }}.registerPopup(nifty);
        red = 0;
        green = 0;
        blue = 0;
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
        skinspage = 0;
        eyespage = 0;
        tshirtspage = 0; 
        trouserspage = 0; 
        shoespage = 0;
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
        String [] screens = {"skinScreen","eyesScreen","tshirtScreen","trousersScreen","shoesScreen"};
        for(String auxScreen : screens){
            for(int i=0; i<gui.length(auxScreen); i++){
                ImageBuilder image = new ImageBuilder(){{
                    width("0%");
                    height("0%");
                }};
                image.id("i"+Integer.toString(i));
                image.filename(gui.path(auxScreen,i));
                image.interactOnClick("changeTexture("+Integer.toString(i)+")");
                image.build(nifty, nifty.getScreen(auxScreen), nifty.getScreen(auxScreen).findElementByName("t"+Integer.toString(i%TEXTURES_PAGE)));
            }
        }
    }
    
    public void changeScreen(String param)
    {
        selection = param;
        nifty.gotoScreen(param);
        gui.setTypeObject(selection);
        if(param.equals("skinScreen")||param.equals("eyesScreen")||param.equals("tshirtScreen")||param.equals("trousersScreen")||param.equals("shoesScreen")){
            changeTexturePage("0");
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
    
    public void changeTexturePage(String steep){
            for(int i=getPage()*TEXTURES_PAGE; i<gui.length(selection); i++){
                if(i<((getPage()+1)*TEXTURES_PAGE)){
                    nifty.getScreen(selection).findElementByName("i"+Integer.toString(i)).setVisible(false);
                    nifty.getScreen(selection).findElementByName("i"+Integer.toString(i)).setHeight(0);
                    nifty.getScreen(selection).findElementByName("i"+Integer.toString(i)).setWidth(0);
                }
            }
            changePage(steep);
            for(int i=getPage()*TEXTURES_PAGE; i<gui.length(selection); i++){
                if(i<((getPage()+1)*TEXTURES_PAGE)){
                    nifty.getScreen(selection).findElementByName("i"+Integer.toString(i)).setVisible(true);
                    nifty.getScreen(selection).findElementByName("i"+Integer.toString(i)).setHeight(nifty.getScreen(selection).findElementByName("t"+Integer.toString(i%TEXTURES_PAGE)).getHeight());
                    nifty.getScreen(selection).findElementByName("i"+Integer.toString(i)).setWidth(nifty.getScreen(selection).findElementByName("t"+Integer.toString(i%TEXTURES_PAGE)).getWidth());
                }
            }
            if(getPage() > 0){
                nifty.getScreen(selection).findElementByName("leftT").enable();
                nifty.getScreen(selection).findElementByName("leftT").setVisible(true);
            }
            else{
                nifty.getScreen(selection).findElementByName("leftT").disable();
                nifty.getScreen(selection).findElementByName("leftT").setVisible(false);
            }
            if((((double)gui.length(selection)/(double)TEXTURES_PAGE) - getPage()) > 1){
                nifty.getScreen(selection).findElementByName("rightT").enable();
                nifty.getScreen(selection).findElementByName("rightT").setVisible(true);
            }
            else{
                nifty.getScreen(selection).findElementByName("rightT").disable();
                nifty.getScreen(selection).findElementByName("rightT").setVisible(false);
            }
    }
    
    public int getPage(){
        if(selection.equals("skinScreen")){return skinspage;}
        if(selection.equals("eyesScreen")){return eyespage;}
        if(selection.equals("tshirtScreen")){return tshirtspage;}
        if(selection.equals("trousersScreen")){return trouserspage;}
        if(selection.equals("shoesScreen")){return shoespage;}
        return -1;
    }
    
    public void changePage(String steep){
        int i = 0;
        if(steep.equals("+")){i = 1;}
        if(steep.equals("-")){i = -1;}
        if(selection.equals("skinScreen")){
            if(selection.equals("0")){skinspage = 0;}
            else{skinspage = skinspage + i;}
        }
        if(selection.equals("eyesScreen")){
            if(selection.equals("0")){eyespage = 0;}
            else{eyespage = eyespage + i;}
        }
        if(selection.equals("tshirtScreen")){
            if(selection.equals("0")){tshirtspage = 0;}
            else{tshirtspage = tshirtspage + i;}
        }
        if(selection.equals("trousersScreen")){
            if(selection.equals("0")){trouserspage = 0;}
            else{trouserspage = trouserspage + i;}
        }
        if(selection.equals("shoesScreen")){
            if(selection.equals("0")){shoespage = 0;}
            else{shoespage = shoespage + i;}
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
        red = 0;
        green = 0;
        blue = 0;
        popup = nifty.createPopup("popupColor");
        nifty.showPopup(nifty.getCurrentScreen(), popup.getId(), null);
    }
    
    @NiftyEventSubscriber(id="aceptButton")
    public void onChangeButtonClicked(final String id, final ButtonClickedEvent event) throws InterruptedException, IOException {
        //Cambiar color con red, blue green
        gui.changeColor(red / 255.f, green / 255.f, blue / 255.f);
        nifty.closePopup(popup.getId()); 
    }
    
    @NiftyEventSubscriber(id="cancelButton")
    public void onCancelButtonClicked(final String id, final ButtonClickedEvent event) {
        nifty.closePopup(popup.getId()); 
    }
    
    @NiftyEventSubscriber(id="sliderR")
    public void onRedSliderChange(final String id, final SliderChangedEvent event) {
      red = event.getValue();
      changeColor();
    }

    @NiftyEventSubscriber(id="sliderG")
    public void onGreenSliderChange(final String id, final SliderChangedEvent event) {
      green = event.getValue();
      changeColor();
    }

    @NiftyEventSubscriber(id="sliderB")
    public void onBlueSliderChange(final String id, final SliderChangedEvent event) {
      blue = event.getValue();
      changeColor();
    }
  
    private void changeColor() {
        popup.findElementByName("panelColor").getRenderer(PanelRenderer.class).setBackgroundColor(new Color(red / 255.f, green / 255.f, blue / 255.f, 1));
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