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
import de.lessvoid.nifty.controls.Button;
import de.lessvoid.nifty.controls.Slider;
import de.lessvoid.nifty.effects.Effect;
import de.lessvoid.nifty.effects.EffectEventId;
import de.lessvoid.nifty.elements.Element;
import de.lessvoid.nifty.elements.render.ImageRenderer;
import de.lessvoid.nifty.elements.render.PanelRenderer;
import de.lessvoid.nifty.elements.render.TextRenderer;
import de.lessvoid.nifty.tools.Color;
import es.eucm.character.control.Control;
import es.eucm.character.i18n.I18N;
import es.eucm.character.types.TexturesMeshType;
import java.util.ArrayList;
import java.util.List;


public class PopUpBuilder {
    private static final int TEXTURES_POPUP = 16;
    private Control control;
    private Nifty nifty;
    private int popUpPage, popUpNum;
    private I18N i18nGui, i18nModel;
    private Element popupColor;
    private float red, green, blue;
    private Color baseColor, detailsColor;
    private String tab;

    public PopUpBuilder(Nifty nifty, Control control, I18N i18nGui, I18N i18nModel){
        popUpPage = 0;
        popUpNum = 0;
        this.control = control;
        this.nifty = nifty;
        this.i18nGui = i18nGui;
        this.i18nModel = i18nModel;
        popupColor = null;
        baseColor = null;
        detailsColor = null;
        tab = "basic";
    }
    
    public void showWindowChangeColor(String subStageSelected, String textureOrSubMeshSelected){
        red = 0; 
        green = 0; 
        blue = 0;
        int r,g,b;
        if(control.getTextureType(textureOrSubMeshSelected).equals(TexturesMeshType.baseShadow)){
            popupColor = nifty.createPopup("popupColor");
            popupColor.findElementByName("acceptText").getRenderer(TextRenderer.class).setText(i18nGui.getString("idAcept"));
            popupColor.findElementByName("acceptPanel").layoutElements();
            popupColor.findElementByName("advancedSelection").setVisible(false);
            popupColor.findElementByName("colorOptions").setVisible(false);
            baseColor = null;
            detailsColor = null;
            if(control.getColorTexture(subStageSelected, textureOrSubMeshSelected)!=null){
                if(control.getColorTexture(subStageSelected, textureOrSubMeshSelected).get(0)!=null){
                    r = control.getColorTexture(subStageSelected, textureOrSubMeshSelected).get(0).getRed();
                    g = control.getColorTexture(subStageSelected, textureOrSubMeshSelected).get(0).getGreen();
                    b = control.getColorTexture(subStageSelected, textureOrSubMeshSelected).get(0).getBlue();
                    baseColor = new Color(r/255.f,g/255.f,b/255.f,1);
                }
            }
        }
        if(control.getTextureType(textureOrSubMeshSelected).equals(TexturesMeshType.doubleTexture)){
            popupColor = nifty.createPopup("popupColor");
            popupColor.findElementByName("acceptText").getRenderer(TextRenderer.class).setText(i18nGui.getString("idAcept"));
            popupColor.findElementByName("acceptPanel").layoutElements();
            popupColor.findElementByName("advancedSelection").setVisible(false);
            popupColor.findElementByName("colorOptions").setVisible(true);
            popupColor.findElementByName("basicText").getRenderer(TextRenderer.class).setText(i18nGui.getString("idBase"));
            popupColor.findElementByName("customText").getRenderer(TextRenderer.class).setText(i18nGui.getString("idDetails"));
            baseColor = null;
            detailsColor = null;
            if(control.getColorTexture(subStageSelected, textureOrSubMeshSelected)!=null){
                if(control.getColorTexture(subStageSelected, textureOrSubMeshSelected).get(0)!=null){
                    r = control.getColorTexture(subStageSelected, textureOrSubMeshSelected).get(0).getRed();
                    g = control.getColorTexture(subStageSelected, textureOrSubMeshSelected).get(0).getGreen();
                    b = control.getColorTexture(subStageSelected, textureOrSubMeshSelected).get(0).getBlue();
                    baseColor = new Color(r/255.f,g/255.f,b/255.f,1);
                }
                if(control.getColorTexture(subStageSelected, textureOrSubMeshSelected).get(1)!=null){
                    r = control.getColorTexture(subStageSelected, textureOrSubMeshSelected).get(1).getRed();
                    g = control.getColorTexture(subStageSelected, textureOrSubMeshSelected).get(1).getGreen();
                    b = control.getColorTexture(subStageSelected, textureOrSubMeshSelected).get(1).getBlue();
                    detailsColor = new Color(r/255.f,g/255.f,b/255.f,1);
                }
            }
        }
        if(control.getTextureType(textureOrSubMeshSelected).equals(TexturesMeshType.multiOptionTexture)){
            popupColor = nifty.createPopup("PopUpColorDefault");
            changePopUpPage("0",subStageSelected,textureOrSubMeshSelected);
            baseColor = null;
            detailsColor = null;
        }
        popupColor.findElementByName("cancelText").getRenderer(TextRenderer.class).setText(i18nGui.getString("idCancel"));
        popupColor.findElementByName("cancelPanel").layoutElements();
        nifty.showPopup(nifty.getCurrentScreen(), popupColor.getId(), null);
        changeTabColor("basic");
    }
    
    public void accept(String textureOrSubMeshSelected, String subStageSelected){
        if(control.getTextureType(textureOrSubMeshSelected).equals(TexturesMeshType.baseShadow)){
            if(baseColor != null){
                control.changeColorBaseShadow(subStageSelected,textureOrSubMeshSelected,baseColor.getRed(), baseColor.getGreen(),baseColor.getBlue());
            }
        }
        if(control.getTextureType(textureOrSubMeshSelected).equals(TexturesMeshType.doubleTexture)){
            if((baseColor != null)&&(detailsColor != null)){
                control.changeColorDoubleTexture(subStageSelected,textureOrSubMeshSelected,baseColor.getRed(), baseColor.getGreen(),baseColor.getBlue(),detailsColor.getRed(), detailsColor.getGreen(),detailsColor.getBlue());
            }
            else{
                if(baseColor != null){
                    control.changeColorDoubleTexture(subStageSelected,textureOrSubMeshSelected,baseColor.getRed(), baseColor.getGreen(),baseColor.getBlue(),-1,-1,-1);
                }
                else{
                    if(detailsColor != null){
                        control.changeColorDoubleTexture(subStageSelected,textureOrSubMeshSelected,-1,-1,-1,detailsColor.getRed(), detailsColor.getGreen(),detailsColor.getBlue());
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
                List<Effect> effects = image.getEffects(EffectEventId.onHover,Tooltip.class);
                effects.get(0).getParameters().setProperty("hintText", i18nModel.getString(control.getTextSubTextureInMultiOption(textureOrSubMeshSelected, control.getIdsSubTexturesInMultiOption(textureOrSubMeshSelected).get(i))));
                image.setVisible(true);
                ImageRenderer imager = image.getRenderer(ImageRenderer.class);
                imager.setImage(nifty.getRenderEngine().createImage(control.getIconPathInMultiOption(textureOrSubMeshSelected, control.getIdsSubTexturesInMultiOption(textureOrSubMeshSelected).get(i)), false));
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
        
        ArrayList<String> mul = control.getIdsSubTexturesInMultiOption(textureOrSubMeshSelected);
        int j = Integer.parseInt(im)+popUpPage*TEXTURES_POPUP;
        String sel = mul.get(j);
        control.changeColorMultiOptionTexture(subStageSelected,textureOrSubMeshSelected,sel);
        nifty.closePopup(popupColor.getId());
    }
    
    public void changeSliderColor(String color){
        if(popupColor != null){
            Color c = new Color(color);
            if(tab.equals("basic")){
                baseColor = new Color(color);
            }
            else{
                detailsColor = new Color(color);
            }
            popupColor.findNiftyControl("sliderR", Slider.class).setValue(c.getRed()*255);
            popupColor.findNiftyControl("sliderG", Slider.class).setValue(c.getGreen()*255);
            popupColor.findNiftyControl("sliderB", Slider.class).setValue(c.getBlue()*255);
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
  
    private void changeColor() {
        if(popupColor != null){
            if(tab.equals("basic")){
                baseColor = new Color(red / 255.f, green / 255.f, blue / 255.f, 1);
            }
            else{
                detailsColor = new Color(red / 255.f, green / 255.f, blue / 255.f, 1);
            }
            popupColor.findElementByName("colorPanel").getRenderer(PanelRenderer.class).setBackgroundColor(new Color(red / 255.f, green / 255.f, blue / 255.f, 1));
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
                control.removeModel();
                return 2;
            }
        }
        if(id.equals("popUpButton2")){
            if(popUpNum == 1){
                popUpNum = 2;
                control.screenShot();
                nifty.getScreen(stage).findElementByName("popUpText").getRenderer(TextRenderer.class).setText(i18nGui.getString("idPopup2"));
                nifty.getScreen(stage).findElementByName("textPanel").layoutElements();
                nifty.getScreen(stage).findElementByName("button1Text").getRenderer(TextRenderer.class).setText(i18nGui.getString("idPopupButton3"));
                nifty.getScreen(stage).findElementByName("button1").layoutElements();
                nifty.getScreen(stage).findElementByName("button2Text").getRenderer(TextRenderer.class).setText(i18nGui.getString("idPopupButton4"));
                nifty.getScreen(stage).findElementByName("button2").layoutElements();
                return 3;
            }
            if(popUpNum == 2){
                return 4;
            }
        }
        return 0;
    }
    
    public void export() {
        String stage = "popupScreen";
        nifty.gotoScreen(stage);
        popUpNum = 1;
        nifty.getScreen(stage).findElementByName("popUpText").getRenderer(TextRenderer.class).setText(i18nGui.getString("idPopup1"));
        nifty.getScreen(stage).findElementByName("textPanel").layoutElements();
        nifty.getScreen(stage).findElementByName("button1Text").getRenderer(TextRenderer.class).setText(i18nGui.getString("idPopupButton1"));
        nifty.getScreen(stage).findElementByName("button1").layoutElements();
        nifty.getScreen(stage).findElementByName("button2Text").getRenderer(TextRenderer.class).setText(i18nGui.getString("idPopupButton2"));
        nifty.getScreen(stage).findElementByName("button2").layoutElements();
    }
    
    public void showMoreColors(){
        if(popupColor.findElementByName("advancedSelection").isVisible()){
            popupColor.findElementByName("advancedSelection").setVisible(false);
        }
        else{
            popupColor.findElementByName("advancedSelection").setVisible(true);
        }
    }
    
    public void check(String id){
        
        popupColor.findElementByName("ci"+id).setVisible(true);
    }
    
    public void unCheck(){
        
        for(int i=0;i<72;i++){
            popupColor.findElementByName("ci"+i).setVisible(false);
        }
    }
    
    public void changeTabColor(String option){
        tab = option;
        if(tab.equals("basic")){
            popupColor.findElementByName("basicPanel").getRenderer(PanelRenderer.class).setBackgroundColor(new Color("#00000000"));
            popupColor.findElementByName("advancedPanel").getRenderer(PanelRenderer.class).setBackgroundColor(new Color("#808080AA"));
            if(baseColor!=null){
                changeSliderColor(baseColor.getColorString());
            }
            else{
                changeSliderColor("#00000000");
                popupColor.findElementByName("colorPanel").getRenderer(PanelRenderer.class).setBackgroundColor(new Color(0,0,0,0));
                baseColor=null;
            }
        }
        else{
            popupColor.findElementByName("basicPanel").getRenderer(PanelRenderer.class).setBackgroundColor(new Color("#808080AA"));
            popupColor.findElementByName("advancedPanel").getRenderer(PanelRenderer.class).setBackgroundColor(new Color("#00000000"));
            if(detailsColor!=null){
                changeSliderColor(detailsColor.getColorString());
            }
            else{
                changeSliderColor("#00000000");
                popupColor.findElementByName("colorPanel").getRenderer(PanelRenderer.class).setBackgroundColor(new Color(0,0,0,0));
                detailsColor=null;
            }
        }
        popupColor.findElementByName("advancedSelection").setVisible(false);
        unCheck();
    }
}
