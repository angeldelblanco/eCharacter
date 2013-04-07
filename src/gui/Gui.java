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

package gui;

import com.jme3.app.Application;
import com.jme3.app.state.AbstractAppState;
import com.jme3.app.state.AppStateManager;
import com.jme3.asset.AssetManager;
import com.jme3.scene.Node;
import control.Control;
import de.lessvoid.nifty.Nifty;
import de.lessvoid.nifty.NiftyEventSubscriber;
import de.lessvoid.nifty.builder.PanelBuilder;
import de.lessvoid.nifty.builder.TextBuilder;
import de.lessvoid.nifty.controls.Button;
import de.lessvoid.nifty.controls.ButtonClickedEvent;
import de.lessvoid.nifty.controls.DropDown;
import de.lessvoid.nifty.controls.DropDownSelectionChangedEvent;
import de.lessvoid.nifty.controls.SliderChangedEvent;
import de.lessvoid.nifty.controls.dropdown.builder.DropDownBuilder;
import de.lessvoid.nifty.elements.Element;
import de.lessvoid.nifty.elements.render.PanelRenderer;
import de.lessvoid.nifty.elements.render.TextRenderer;
import de.lessvoid.nifty.screen.Screen;
import de.lessvoid.nifty.screen.ScreenController;
import de.lessvoid.nifty.tools.Color;
import i18n.I18N;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import loader.Configuration;
import types.StageType;

public class Gui extends AbstractAppState implements ScreenController {
    
    private static final int BONES_PAGE = 5;
    private Nifty nifty;
    private I18N i18nGui, i18nModel, i18nFamily; 
    private Control control;
    private Application app;
    private Screen screen;
    private Configuration config;
    private String selection, familySelection, modelSelection;
    private int page;
    private Element popupColor;
    private float red, green, blue;
    private ArrayList<String> stages,idBones,idPhysicalBuild;
    private ArrayList<String> families;
    private int modelsSize, modelsAntSize;
    private int index, popUpNum;
    private String language;
    private ModelStageBuilder modelsb;
    private ScaleStageBuilder scalesb;
    private SingleStageBuilder singlesb;
    private MultiStageBuilder multisb;
    private AnimationStageBuilder animationsb;
    
    public Gui(Control control,Configuration config){
        this.control = control;
        this.config = config;
        families = this.control.getFamiliesName();
    }
    
    public void startGame(String nextScreen) {
        nifty.gotoScreen(nextScreen);  // switch to another screen
        modelsb = new ModelStageBuilder(nifty,control,i18nGui,language,families);
        control.selectFamily(families.get(0));
        i18nFamily = new I18N(control.getLanguageFamilyPath(),language);
        modelsSize = control.getNumModels();
        modelsAntSize = 0;
        familySelection = i18nFamily.getString(control.getMetadataFamilyName());
        DropDown family = nifty.getScreen("modelScreen").findNiftyControl("familyDropDown", DropDown.class);
        family.selectItem(i18nFamily.getString(control.getMetadataFamilyName()));
        changeCharacterPage("0",familySelection);
    }

    public void quitGame() {
        app.stop();
    }

    public void bind(Nifty nifty, Screen screen) {
        this.nifty = nifty;
        this.screen = screen;
    }

    public void onStartScreen() {
        
    }

    public void onEndScreen() {}
    
    @Override
    public void initialize(AppStateManager stateManager, Application app) 
    {
        selection = "";
        this.app = app;
        page = 0;
        popupColor = null;
        index = 0;
        popUpNum = 0;
        familySelection = "";
        new DropDownBuilder("localeDropDown") {{
                valignCenter();
                //alignRight();
                width("100");
        }}.build(nifty, nifty.getScreen("start"), nifty.getScreen("start").findElementByName("panel_location"));
        DropDown locale = nifty.getScreen("start").findNiftyControl("localeDropDown", DropDown.class);
        ArrayList<String> languajes = config.getListLanguagesAvailables();
        Iterator<String> it = languajes.iterator();
        String defectLanguage = config.getProperty(Configuration.Language);
        while(it.hasNext()){
            final String l = it.next();
            locale.addItem(l);
        }
        language = defectLanguage;
        locale.selectItem(defectLanguage);
        i18nGui = new I18N(config.getProperty(Configuration.LocalePath),language);
    }
    
    public void buildMenu(){
        String types[] = {StageType.singleStage.toString(),
                          StageType.scaleStage.toString(),
                          StageType.multiStage.toString(),
                          StageType.animationStage.toString()};
        for(String type : types){
            for(int i = 0; i<stages.size(); i++){
                final String pant = stages.get(i);
                PanelBuilder menu;
                menu = new PanelBuilder(){{
                    //width(Float.toString(100/stages.size())+"%");
                    height("100%");
                    childLayoutCenter();
                    text(new TextBuilder(){{
                        color(Color.WHITE);
                        font("Interface/Fonts/Default.fnt");
                        text(i18nFamily.getString(pant));
                        width("100%");
                    }});
                }};
                menu.id(stages.get(i)+"Menu");
                menu.backgroundImage(getMenu("no"));
                menu.interactOnClick("changeScreen("+stages.get(i)+")");
                menu.build(nifty, nifty.getScreen(type), nifty.getScreen(type).findElementByName("panel_options"));
            }
            nifty.getScreen(type).findElementByName("previousText").getRenderer(TextRenderer.class).setText(i18nGui.getString("idPrevious"));
            nifty.getScreen(type).findElementByName("panel_screenleft").layoutElements();
            if(type.equals(StageType.animationStage.toString())){
                nifty.getScreen(type).findElementByName("finishText").getRenderer(TextRenderer.class).setText(i18nGui.getString("idFinish"));
            }
            else{
                nifty.getScreen(type).findElementByName("nextText").getRenderer(TextRenderer.class).setText(i18nGui.getString("idNext"));
            }
            nifty.getScreen(type).findElementByName("panel_screenright").layoutElements();
        }
    }
    
    public void changeCharacterPage(String steep, String familyAnt){
        if(familyAnt.equals("same")){
            familyAnt = familySelection;
            modelsAntSize = modelsSize;
        }
        modelsb.changeCharacterPage(i18nFamily,steep,familySelection,familyAnt,modelsSize,modelsAntSize);    
    }
    
    public void selectModel(String id, String param){
        modelSelection = param;
        modelsb.selectModel(id);
    }
    
    public void loadFirstScreen(){
        //nifty.getScreen("modelScreen").findElementByName("loadPopupPanel").setVisible(true);
        control.selectModel(modelSelection);
        i18nModel = new I18N(control.getLanguageModelPath(),language);
        stages = control.getStagesLabels();
        selection = stages.get(index);
        scalesb = new ScaleStageBuilder(nifty,control,i18nFamily,i18nModel);
        singlesb = new SingleStageBuilder(nifty,control,i18nGui,stages);
        multisb = new MultiStageBuilder(nifty, control, i18nGui, i18nFamily,stages);
        animationsb = new AnimationStageBuilder(nifty, control, i18nGui, i18nFamily);
        ArrayList<String> idPanel = control.getIdsSubStages(selection);
        idPhysicalBuild = control.getIdsPhysicalBuild(idPanel.get(0));
        idBones = control.getIdBonesController(selection);
        buildMenu();
        
        loadScreen(control.getStagesTypes(selection).toString(),"","");
        nifty.getScreen(control.getStagesTypes(selection).toString()).findElementByName("panel_screenleft").disable();
        nifty.getScreen(control.getStagesTypes(selection).toString()).findElementByName("panel_screenleft").setVisible(false);
        nifty.gotoScreen(control.getStagesTypes(selection).toString());
    }
    
    public void changeScalePage(String steep){
        changePage(steep);
        scalesb.changeScalePage(page, selection);
    }
    
    public void changeTab(String param){
        changePage("0");
        scalesb.changeTab(param, selection);
    }
    
    public void changeScreen(String param)
    {
        String old = selection;
        int oldIndex = index;
        selection = param;
        if(param.equals("+")){
            index++;
            selection = stages.get(index);
            
        }else{
            if(param.equals("-")){
                index--;
                selection = stages.get(index);
            }else{
                for(int i = 0; i < stages.size(); i++){
                    if(selection.equals(stages.get(i))){
                        index = i;
                    }
                }
            }
        }
        String stage = control.getStagesTypes(selection).toString();
        String oldStage = control.getStagesTypes(stages.get(oldIndex)).toString();
        if(!oldStage.equals(stage)){
            nifty.gotoScreen(stage);
        }
        loadScreen(stage,oldStage,old);
        if(index==0){
            nifty.getScreen(stage).findElementByName("panel_screenleft").disable();
            nifty.getScreen(stage).findElementByName("panel_screenleft").setVisible(false);
        }
        else{
            nifty.getScreen(stage).findElementByName("panel_screenleft").enable();
            nifty.getScreen(stage).findElementByName("panel_screenleft").setVisible(true);
        }
    }
    
    public void loadScreen(String type, String oldType, String param){
        loadMenu(type);
        hideTexturePage(oldType, param);
        if(type.equals(StageType.singleStage.toString())){
            changeTexturePage("0");
        }
        if(type.equals(StageType.scaleStage.toString())){
            changeTab("basic");
        }
        if(type.equals(StageType.multiStage.toString())){
            changeMultiTexturePage("0");
        }
    }
    
    public void loadMenu(String type){
        String color = "#FF0000AA";
        Iterator<String> it = stages.iterator();
        while(it.hasNext()){
            String auxStage = it.next();
            nifty.getScreen(type).findElementByName(auxStage+"Menu").getRenderer(PanelRenderer.class).setBackgroundColor(new Color(color));
            if(auxStage.equals(selection)){
                color = "#FF000000";
            }
        }
    }
    
    /******************************ChangePageImages*****************************/
    
    public void changeTexturePage(String steep){
        if(!steep.equals("0")){
            singlesb.hideTexturePage(selection,page);
        }
        changePage(steep);
        singlesb.showTexturePage(selection,page);
    }
    
    public void hideTexturePage(String type, String param){
        if(type.equals(StageType.singleStage.toString())){
            singlesb.hideTexturePage(param, page);
        }
        if(type.equals(StageType.multiStage.toString())){
            multisb.hideSubTexturePage(0,page,param);
            multisb.hideSubTexturePage(1,page,param);
        }
    }
    
    public void changeMultiTexturePage(String steep){
        String stage = StageType.multiStage.toString();
        if(!steep.equals("0")){
            hideTexturePage(stage,selection);
        }
        changePage(steep);
        multisb.showTexturePage(selection, page);
    }
    
    public void changeTexturePage(String t, String steep){
        int h = Integer.valueOf(t);
        if(!steep.equals("0")){
            multisb.hideSubTexturePage(h,page,selection);
        }
        multisb.showSubTexturePage(selection, h, page, steep);
    }
    
    //Change page of textures or bones
    
    public void changePage(String steep){
        if(steep.equals("+")){
            page++;
        }
        if(steep.equals("-")){
            page--;
        }
        if(steep.equals("0")){
            page = 0;
        }
    }
    
    /******************************LoadGuiImages*****************************/
    
    public String getMenu(String param)
    {
        if(param.equals("yes")){
            return "assets/Interface/MenuRojo.png";
        }
        if(param.equals("no")){
            return "assets/Interface/MenuAzul.png";
        }
        if(param.equals("an")){
            return "assets/Interface/MenuAzulAntiguo.png";
        }
        if(param.equals("ay")){
            return "assets/Interface/MenuRojoAntiguo.png";
        }
        return null;
    }
    
    public String getButton(String param)
    {
        if(param.equals("left")){
            return "assets/Interface/ant.png";
        }
        if(param.equals("right")){
            return "assets/Interface/sig.png";
        }
        if(param.equals("color")){
            return "assets/Interface/ColorButton.png";
        }
        if(param.equals("next")){
            return "assets/Interface/next.png";
        }
        if(param.equals("previous")){
            return "assets/Interface/previous.png";
        }
        return null;
    }
    
    public void changeTextureOrSubMesh(String idTextureOrSubMesh)
    {
        control.changeTextureOrSubMesh(idTextureOrSubMesh);
    }
    
  /******************************FamilyDropDownControler*****************************/
    
  @NiftyEventSubscriber(id="familyDropDown")
  public void onFamilyDropDownSelectionChanged(final String id, final DropDownSelectionChangedEvent<String> event) {
    if (event.getSelection() != null && (!familySelection.equals(""))) {
        String familyAnt = familySelection;
        Iterator<String> it = families.iterator();
        familySelection = event.getSelection();
        while(it.hasNext()){
           control.selectFamily(it.next());
           if(i18nFamily.getString(control.getMetadataFamilyName()).equals(familySelection)){
               modelsAntSize = modelsSize;
               modelsSize = control.getNumModels();
               i18nFamily = new I18N(control.getLanguageFamilyPath(),language);
           }
        }
        changeCharacterPage("0", familyAnt);
    }
  }
    
  /******************************LocaleDropDownControler*****************************/  
    
  @NiftyEventSubscriber(id="localeDropDown")
  public void onLocaleDropDownSelectionChanged(final String id, final DropDownSelectionChangedEvent<String> event) {
    if (event.getSelection() != null) {
        Button startb = nifty.getScreen("start").findNiftyControl("startButton", Button.class);
        Button quitb = nifty.getScreen("start").findNiftyControl("quitButton", Button.class);
        language = event.getSelection();
        config.setProperty(Configuration.Language, language);
        i18nGui = new I18N(config.getProperty(Configuration.LocalePath),language);
        nifty.getScreen("start").findElementByName("description").getRenderer(TextRenderer.class).setText(i18nGui.getString("idDescription"));
        nifty.getScreen("start").findElementByName("panel_mid").layoutElements();
        nifty.getScreen("start").findElementByName("languageText").getRenderer(TextRenderer.class).setText(i18nGui.getString("idLanguage"));
        nifty.getScreen("start").findElementByName("panel_location").layoutElements();
        startb.setText(i18nGui.getString("idStart"));
        quitb.setText(i18nGui.getString("idQuit"));
    }
  }
    
    /******************************FinishButtonControler*****************************/
    
    public void export() 
    {
        String stage = "popupScreen";
        nifty.gotoScreen(stage);
        popUpNum = 1;
        nifty.getScreen(stage).findElementByName("popUpText").getRenderer(TextRenderer.class).setText(i18nGui.getString("idPopup1"));
        nifty.getScreen(stage).findElementByName("popup").layoutElements();
        nifty.getScreen(stage).findNiftyControl("popUpButton1", Button.class).setText(i18nGui.getString("idPopupButton1"));
        nifty.getScreen(stage).findNiftyControl("popUpButton2", Button.class).setText(i18nGui.getString("idPopupButton2"));
    }
    
    /******************************PopUpsControler*****************************/
    
    public void popUpButtonClicked(String id) {
        String stage = "popupScreen";
        if(id.equals("popUpButton1")){
            if(popUpNum == 1){
                index = 0;
                selection = stages.get(index);
                loadScreen(control.getStagesTypes(selection).toString(),"","");
                nifty.gotoScreen(control.getStagesTypes(selection).toString());
            }
            if(popUpNum == 2){
                /*String familyAnt = familySelection;
                DropDown family = nifty.getScreen("modelScreen").findNiftyControl("familyDropDown", DropDown.class);
                control.selectFamily(families.get(0));
                modelsAntSize = modelsSize;
                modelsSize = control.getNumModels();
                i18nFamily = new I18N(control.getLanguageFamilyPath(),language);
                familySelection = i18nFamily.getString(control.getMetadataFamilyName());
                family.selectItem(i18nFamily.getString(control.getMetadataFamilyName()));
                changeCharacterPage("0", familyAnt);*/
                nifty.gotoScreen("modelScreen");
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
            }
            if(popUpNum == 2){
                //nifty.gotoScreen("finalScreen");
            }
        }
    }
    
    /******************************PopUpColorControler*****************************/
    
    public void showWindowChangeColor() throws InterruptedException
    {
        red = 0;
        blue = 0;
        green = 0;
        //Si lleva el if a los X cambios de color lanza excepcion
        //if(popupColor == null){
            popupColor = nifty.createPopup("popupColor");
        //}
        nifty.showPopup(nifty.getCurrentScreen(), popupColor.getId(), null);
    }
    
    @NiftyEventSubscriber(id="sliderR")
    public void onRedSliderChange(final String id, final SliderChangedEvent event) {
      if(popupColor != null){
        red = event.getValue();
        changeColor();
      }
    }

    @NiftyEventSubscriber(id="sliderG")
    public void onGreenSliderChange(final String id, final SliderChangedEvent event) {
      if(popupColor != null){
        green = event.getValue();
        changeColor();
      }
    }

    @NiftyEventSubscriber(id="sliderB")
    public void onBlueSliderChange(final String id, final SliderChangedEvent event) {
      if(popupColor != null){
        blue = event.getValue();
        changeColor();
      }
    }
  
    private void changeColor() {
        //if(popupColor != null){
            popupColor.findElementByName("colorPanel").getRenderer(PanelRenderer.class).setBackgroundColor(new Color(red / 255.f, green / 255.f, blue / 255.f, 1));
        //}
    }
    
    @NiftyEventSubscriber(id="aceptButton")
    public void onChangeButtonClicked(final String id, final ButtonClickedEvent event) throws InterruptedException, IOException {
        control.changeColor(red / 255.f, green / 255.f, blue / 255.f);
        nifty.closePopup(popupColor.getId()); 
    }
    
    @NiftyEventSubscriber(id="cancelButton")
    public void onCancelButtonClicked(final String id, final ButtonClickedEvent event) {
        nifty.closePopup(popupColor.getId()); 
    }
    
    /******************************SliderControler*****************************/
    
    @NiftyEventSubscriber(id="slider0")
    public void Slider0Change(final String id, final SliderChangedEvent event) 
    {
        float inc = 1.0f + event.getValue() * 0.01f;
        control.setBoneControllerValue(idBones.get(page*BONES_PAGE), inc);
        control.setDefaultValueBoneController(idBones.get(page*BONES_PAGE),event.getValue());
    }
    
    @NiftyEventSubscriber(id="slider1")
    public void onSlider1Change(final String id, final SliderChangedEvent event) 
    {
        float inc = 1.0f + event.getValue() * 0.01f;
        control.setBoneControllerValue(idBones.get(page*BONES_PAGE+1), inc);
        control.setDefaultValueBoneController(idBones.get(page*BONES_PAGE+1),event.getValue());

    }
    
    @NiftyEventSubscriber(id="slider2")
    public void onSlider2Change(final String id, final SliderChangedEvent event) 
    {
        float inc = 1.0f + event.getValue() * 0.01f;
        control.setBoneControllerValue(idBones.get(page*BONES_PAGE+2), inc);
        control.setDefaultValueBoneController(idBones.get(page*BONES_PAGE+2),event.getValue());
    }
    
    @NiftyEventSubscriber(id="slider3")
    public void onSlider3Change(final String id, final SliderChangedEvent event) 
    {
        float inc = 1.0f + event.getValue() * 0.01f;
        control.setBoneControllerValue(idBones.get(page*BONES_PAGE+3), inc);
        control.setDefaultValueBoneController(idBones.get(page*BONES_PAGE+3),event.getValue());
    }
    
    @NiftyEventSubscriber(id="slider4")
    public void onSlider4Change(final String id, final SliderChangedEvent event) 
    {
        float inc = 1.0f + event.getValue() * 0.01f;
        control.setBoneControllerValue(idBones.get(page*BONES_PAGE+4), inc);
        control.setDefaultValueBoneController(idBones.get(page*BONES_PAGE+4),event.getValue());
    }
    
    /******************************PhysicalBuildControler*****************************/
    
    public void changeBodyType(String bodyType)
    {
        control.setPhysicalBuild(idPhysicalBuild.get(page*BONES_PAGE+Integer.parseInt(bodyType)));
    }
}