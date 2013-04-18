/********************************************************************************
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
package es.eucm.character.gui;

import de.lessvoid.nifty.Nifty;
import de.lessvoid.nifty.NiftyEventSubscriber;
import de.lessvoid.nifty.controls.Button;
import de.lessvoid.nifty.controls.ButtonClickedEvent;
import de.lessvoid.nifty.controls.CheckBox;
import de.lessvoid.nifty.controls.Slider;
import de.lessvoid.nifty.controls.SliderChangedEvent;
import de.lessvoid.nifty.elements.Element;
import de.lessvoid.nifty.elements.render.ImageRenderer;
import de.lessvoid.nifty.elements.render.PanelRenderer;
import de.lessvoid.nifty.elements.render.TextRenderer;
import de.lessvoid.nifty.tools.Color;
import es.eucm.character.control.Control;
import es.eucm.character.i18n.I18N;
import es.eucm.character.types.TexturesMeshType;
import java.io.IOException;
import java.util.ArrayList;


public class PopUpBuilder {
    private static final int TEXTURES_POPUP = 6;
    private Control control;
    private Nifty nifty;
    private int popUpPage, popUpNum;
    private I18N i18nGui;
    private Element popupColor;
    private float red, green, blue, red2, green2, blue2; 

    public PopUpBuilder(Nifty nifty, Control control, I18N i18nGui){
        popUpPage = 0;
        popUpNum = 0;
        this.control = control;
        this.nifty = nifty;
        this.i18nGui = i18nGui;
        popupColor = null;
    }
    
    public void showWindowChangeColor(String subStageSelected, String textureOrSubMeshSelected){
        red = 0; 
        green = 0; 
        blue = 0; 
        red2 = 0; 
        green2 = 0; 
        blue2 = 0;
        if(control.getTextureType(textureOrSubMeshSelected).equals(TexturesMeshType.baseShadow)){
            popupColor = nifty.createPopup("popupColor");
            popupColor.findElementByName("redText").getRenderer(TextRenderer.class).setText(i18nGui.getString("idRed"));
            popupColor.findElementByName("redPanel").layoutElements();
            popupColor.findElementByName("greenText").getRenderer(TextRenderer.class).setText(i18nGui.getString("idGreen"));
            popupColor.findElementByName("greenPanel").layoutElements();
            popupColor.findElementByName("blueText").getRenderer(TextRenderer.class).setText(i18nGui.getString("idBlue"));
            popupColor.findElementByName("bluePanel").layoutElements();
            popupColor.findNiftyControl("aceptButton", Button.class).setText(i18nGui.getString("idAcept"));
        }
        if(control.getTextureType(textureOrSubMeshSelected).equals(TexturesMeshType.doubleTexture)){
            popupColor = nifty.createPopup("popupColor2");
            popupColor.findElementByName("redText").getRenderer(TextRenderer.class).setText(i18nGui.getString("idRed"));
            popupColor.findElementByName("redPanel").layoutElements();
            popupColor.findElementByName("greenText").getRenderer(TextRenderer.class).setText(i18nGui.getString("idGreen"));
            popupColor.findElementByName("greenPanel").layoutElements();
            popupColor.findElementByName("blueText").getRenderer(TextRenderer.class).setText(i18nGui.getString("idBlue"));
            popupColor.findElementByName("bluePanel").layoutElements();
            popupColor.findNiftyControl("aceptButton", Button.class).setText(i18nGui.getString("idAcept"));
            popupColor.findElementByName("baseText").getRenderer(TextRenderer.class).setText(i18nGui.getString("idBase"));
            popupColor.findElementByName("checkBasePanel").layoutElements();
            popupColor.findElementByName("shadowText").getRenderer(TextRenderer.class).setText(i18nGui.getString("idDetails"));
            popupColor.findElementByName("checkShadowPanel").layoutElements();
            popupColor.findElementByName("red2Text").getRenderer(TextRenderer.class).setText(i18nGui.getString("idRed"));
            popupColor.findElementByName("red2Panel").layoutElements();
            popupColor.findElementByName("green2Text").getRenderer(TextRenderer.class).setText(i18nGui.getString("idGreen"));
            popupColor.findElementByName("green2Panel").layoutElements();
            popupColor.findElementByName("blue2Text").getRenderer(TextRenderer.class).setText(i18nGui.getString("idBlue"));
            popupColor.findElementByName("blue2Panel").layoutElements();
        }
        if(control.getTextureType(textureOrSubMeshSelected).equals(TexturesMeshType.multiOptionTexture)){
            popupColor = nifty.createPopup("popupColor3");
            changePopUpPage("0",subStageSelected,textureOrSubMeshSelected);
        }
        popupColor.findNiftyControl("cancelButton", Button.class).setText(i18nGui.getString("idCancel"));
        nifty.showPopup(nifty.getCurrentScreen(), popupColor.getId(), null);
    }
    
    public void accept(String textureOrSubMeshSelected, String subStageSelected){
        if(control.getTextureType(textureOrSubMeshSelected).equals(TexturesMeshType.baseShadow)){
            control.changeColorBaseShadow(subStageSelected,textureOrSubMeshSelected,red / 255.f, green / 255.f, blue / 255.f);
        }
        if(control.getTextureType(textureOrSubMeshSelected).equals(TexturesMeshType.doubleTexture)){
            boolean base = popupColor.findNiftyControl("baseCheckBox", CheckBox.class).isChecked();
            boolean shadow = popupColor.findNiftyControl("shadowCheckBox", CheckBox.class).isChecked();
            if(base&&shadow){
                control.changeColorDoubleTexture(subStageSelected,textureOrSubMeshSelected,red/ 255.f,green/ 255.f,blue/ 255.f,red2/ 255.f,green2/ 255.f,blue2/ 255.f);
            }
            else{
                if(base){
                    control.changeColorDoubleTexture(subStageSelected,textureOrSubMeshSelected,red/ 255.f,green/ 255.f,blue/ 255.f,-1,-1,-1);
                }
                else{
                    if(shadow){
                        control.changeColorDoubleTexture(subStageSelected,textureOrSubMeshSelected,-1,-1,-1,red2/ 255.f,green2/ 255.f,blue2/ 255.f);
                    }
                }
            }
        }
        nifty.closePopup(popupColor.getId()); 
    }
    
    public void changePopUpPage(String steep, String subStageSelected, String textureOrSubMeshSelected){
        if(steep.equals("+")){
            popUpPage++;
        }
        if(steep.equals("-")){
            popUpPage--;
        }
        if(steep.equals("0")){
            popUpPage = 0;
        }
        for(int i=popUpPage*TEXTURES_POPUP; i<control.getNumTexturesInMultiOption(textureOrSubMeshSelected); i++){
            if(i<((popUpPage+1)*TEXTURES_POPUP)){
                Element image = popupColor.findElementByName("i"+Integer.toString(i%TEXTURES_POPUP));
                image.setVisible(true);
                ImageRenderer imager = image.getRenderer(ImageRenderer.class);
                imager.setImage(nifty.getRenderEngine().createImage(control.getIconPathInMultiOption(textureOrSubMeshSelected, control.getIdsTexturesInMultiOption(textureOrSubMeshSelected).get(i)), false));
            }
        }
        for(int i=control.getNumTexturesInMultiOption(textureOrSubMeshSelected);i<((popUpPage+1)*TEXTURES_POPUP);i++){
            Element image = popupColor.findElementByName("i"+Integer.toString(i%TEXTURES_POPUP));
            image.setVisible(false);
        }
        if(popUpPage > 0){
            popupColor.findElementByName("leftT").setVisible(true);
        }
        else{
            popupColor.findElementByName("leftT").setVisible(false);
        }
        if((((double)control.getNumTexturesInMultiOption(textureOrSubMeshSelected) /(double)TEXTURES_POPUP) - popUpPage) > 1){
            popupColor.findElementByName("rightT").setVisible(true);
        }
        else{
            popupColor.findElementByName("rightT").setVisible(false);
        }
    }
    
    public void changePopUpColor(String im, String subStageSelected, String textureOrSubMeshSelected){
        
        ArrayList<String> mul = control.getIdsTexturesInMultiOption(textureOrSubMeshSelected);
        int j = Integer.parseInt(im)+popUpPage*TEXTURES_POPUP;
        String sel = mul.get(j);
        control.changeColorMultiOptionTexture(subStageSelected,textureOrSubMeshSelected,sel);
        nifty.closePopup(popupColor.getId());
    }
    
    public void changeSliderColor(String color){
        if(popupColor != null){
            Color c = new Color(color);
            popupColor.findNiftyControl("sliderR", Slider.class).setValue(c.getRed()*255);
            popupColor.findNiftyControl("sliderG", Slider.class).setValue(c.getGreen()*255);
            popupColor.findNiftyControl("sliderB", Slider.class).setValue(c.getBlue()*255);
        }
    }    
    
    public void changeSliderColor2(String color){
        if(popupColor != null){
            Color c = new Color(color);
            popupColor.findNiftyControl("sliderR2", Slider.class).setValue(c.getRed()*255);
            popupColor.findNiftyControl("sliderG2", Slider.class).setValue(c.getGreen()*255);
            popupColor.findNiftyControl("sliderB2", Slider.class).setValue(c.getBlue()*255);
        }
    }
    
    public void onRedSliderChange(float value) {
        if(popupColor != null){
            red = value;
            changeColor();
        }
    }

    public void onGreenSliderChange(float value) {
        if(popupColor != null){
            green = value;
            changeColor();
        }
    }

    public void onBlueSliderChange(float value) {
        if(popupColor != null){
            blue = value;
            changeColor();
        }
    }
    
    public void onRed2SliderChange(float value) {
        if(popupColor != null){
            red2 = value;
            changeColor2();
        }
    }

    public void onGreen2SliderChange(float value) {
        if(popupColor != null){
            green2 = value;
            changeColor2();
        }
    }

    public void onBlue2SliderChange(float value) {
        if(popupColor != null){
            blue2 = value;
            changeColor2();
        }
    }
  
    private void changeColor() {
        if(popupColor != null){
            popupColor.findElementByName("colorPanel").getRenderer(PanelRenderer.class).setBackgroundColor(new Color(red / 255.f, green / 255.f, blue / 255.f, 1));
        }
    }
    
    private void changeColor2() {
        if(popupColor != null){
            popupColor.findElementByName("color2Panel").getRenderer(PanelRenderer.class).setBackgroundColor(new Color(red2 / 255.f, green2 / 255.f, blue2 / 255.f, 1));
        }
    }
    
    public void cancel() {
        nifty.closePopup(popupColor.getId()); 
    }
    
    public int popUpButtonClicked(String id) {
        String stage = "popupScreen";
        if(id.equals("popUpButton1")){
            if(popUpNum == 1){
                return 1;
            }
            if(popUpNum == 2){
                control.restartApplication();
                return 2;
            }
        }
        if(id.equals("popUpButton2")){
            if(popUpNum == 1){
                popUpNum = 2;
                control.screenShot();
                nifty.getScreen(stage).findElementByName("popUpText").getRenderer(TextRenderer.class).setText(i18nGui.getString("idPopup2"));
                nifty.getScreen(stage).findElementByName("popup").layoutElements();
                nifty.getScreen(stage).findNiftyControl("popUpButton1", Button.class).setText(i18nGui.getString("idPopupButton3"));
                nifty.getScreen(stage).findNiftyControl("popUpButton2", Button.class).setText(i18nGui.getString("idPopupButton4"));
                return 3;
            }
            if(popUpNum == 2){
                return 4;
                //nifty.gotoScreen("finalScreen");
            }
        }
        return 0;
    }
    
    public void export() {
        String stage = "popupScreen";
        nifty.gotoScreen(stage);
        popUpNum = 1;
        nifty.getScreen(stage).findElementByName("popUpText").getRenderer(TextRenderer.class).setText(i18nGui.getString("idPopup1"));
        nifty.getScreen(stage).findElementByName("popup").layoutElements();
        nifty.getScreen(stage).findNiftyControl("popUpButton1", Button.class).setText(i18nGui.getString("idPopupButton1"));
        nifty.getScreen(stage).findNiftyControl("popUpButton2", Button.class).setText(i18nGui.getString("idPopupButton2"));
    }
}
