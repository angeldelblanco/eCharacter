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

package es.eucm.character.gui;

import com.jme3.app.Application;
import com.jme3.app.state.AbstractAppState;
import com.jme3.app.state.AppStateManager;
import de.lessvoid.nifty.Nifty;
import de.lessvoid.nifty.NiftyEventSubscriber;
import de.lessvoid.nifty.builder.ElementBuilder;
import de.lessvoid.nifty.builder.ImageBuilder;
import de.lessvoid.nifty.builder.PanelBuilder;
import de.lessvoid.nifty.builder.TextBuilder;
import de.lessvoid.nifty.controls.Button;
import de.lessvoid.nifty.controls.ButtonClickedEvent;
import de.lessvoid.nifty.controls.CheckBoxStateChangedEvent;
import de.lessvoid.nifty.controls.DropDown;
import de.lessvoid.nifty.controls.DropDownSelectionChangedEvent;
import de.lessvoid.nifty.controls.SliderChangedEvent;
import de.lessvoid.nifty.controls.dropdown.builder.DropDownBuilder;
import de.lessvoid.nifty.elements.Element;
import de.lessvoid.nifty.elements.render.TextRenderer;
import de.lessvoid.nifty.screen.Screen;
import de.lessvoid.nifty.screen.ScreenController;
import es.eucm.character.control.Control;
import es.eucm.character.i18n.I18N;
import es.eucm.character.loader.Configuration;
import es.eucm.character.types.StageType;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.StringTokenizer;

public class Gui extends AbstractAppState implements ScreenController {
    
    private static final int BONES_PAGE = 5;
    private Nifty nifty;
    private I18N i18nGui, i18nModel, i18nFamily; 
    private Control control;
    private Application app;
    private Screen screen;
    private Configuration config;
    private String selection, modelSelection,subStageSelected, textureOrSubMeshSelected;
    private int page;
    private ArrayList<String> stages,idBones,idPhysicalBuild;
    private ArrayList<String> families;
    private int index;
    private String language;
    private FamilyStageBuilder modelsb;
    private ScaleStageBuilder scalesb;
    private SingleStageBuilder singlesb;
    private MultiStageBuilder multisb;
    private AnimationStageBuilder animationsb;
    private PopUpBuilder popUp;
    
    public Gui(Control control,Configuration config){
        this.control = control;
        this.config = config;
        families = this.control.getFamiliesID();
    }
    
    public void startGame(String nextScreen) {
        nifty.gotoScreen(nextScreen);  // switch to another screen
        modelsb = new FamilyStageBuilder(nifty,control,i18nGui,getTexture("family-button"),language);
    }
    
    public void loadFamilyScreen(){
        String types[] = {StageType.singleStage.toString(),
                          StageType.scaleStage.toString(),
                          StageType.multiStage.toString(),
                          StageType.animationStage.toString()};
        for(String type : types){
            nifty.gotoScreen(type);
            List<Element> listmenu = nifty.getScreen(type).findElementByName("panel_options").getElements();
            Iterator<Element> it = listmenu.iterator();
            while(it.hasNext()){
                Element element = it.next();
                element.markForRemoval();
            }
        }
        nifty.gotoScreen("modelScreen");
        modelsb.initModels(getTexture("family-button"));
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
    public void initialize(AppStateManager stateManager, Application app) {
        selection = "";
        this.app = app;
        page = 0;
        index = 0;
        popUp = null;
        new DropDownBuilder("localeDropDown") {{
                valignCenter();
                width("100");
        }}.build(nifty, nifty.getScreen("start"), nifty.getScreen("start").findElementByName("panel_location"));
        DropDown locale = nifty.getScreen("start").findNiftyControl("localeDropDown", DropDown.class);
        ArrayList<String> languages = this.getListLanguagesAvailables();
        Iterator<String> it = languages.iterator();
        String defectLanguage = config.getProperty(Configuration.LANGUAGE);
        while(it.hasNext()){
            final String l = it.next();
            locale.addItem(l);
        }
        language = defectLanguage;
        locale.selectItem(defectLanguage);
        i18nGui = new I18N(config.getProperty(Configuration.LOCALE_PATH),language);
    }
    
    private ArrayList<String> getListLanguagesAvailables(){
        ArrayList<String> listLanguages = new ArrayList<String>();
        StringTokenizer st = new StringTokenizer(config.getProperty(Configuration.LIST_LANGUAGE));
        while (st.hasMoreTokens())
        {
            listLanguages.add(st.nextToken());
        }
        return listLanguages;
    }
    
    public void buildMenu(){
        String types[] = {StageType.singleStage.toString(),
                          StageType.scaleStage.toString(),
                          StageType.multiStage.toString(),
                          StageType.animationStage.toString()};
        for(final String type : types){
            for(int i = 0; i<stages.size(); i++){
                final String pant = stages.get(i);
                PanelBuilder menu;
                menu = new PanelBuilder(){{
                    height("80%");
                    childLayoutVertical();
                    valign(VAlign.Bottom);
                    image(new ImageBuilder(){{
                        filename(control.getIconPathStage(pant));
                        align(Align.Center);
                    }});
                    image(new ImageBuilder(){{
                        id(pant+"menu-selection");
                        align(Align.Center);
                        filename(getMenu("menu-selection"));
                    }});
                }};
                menu.id(stages.get(i)+"Menu");
                menu.interactOnClick("changeScreen("+stages.get(i)+")");
                menu.build(nifty, nifty.getScreen(type), nifty.getScreen(type).findElementByName("panel_options"));
                if(i<stages.size()-1){
                    new ImageBuilder(){{
                        valign(VAlign.Center);
                        filename(getMenu("header-leftseparator"));
                    }}.build(nifty, nifty.getScreen(type), nifty.getScreen(type).findElementByName("panel_options"));
                }
                nifty.getScreen(type).findElementByName(stages.get(i)+"menu-selection").setVisible(false);
            }
        }          
    }
    
    public void changeCharacterPage(String steep){
        modelSelection = modelsb.changeCharacterPage(i18nFamily,steep);    
    }
    
    public void loadFirstScreen(){
        index = 0;
        control.selectModel(modelSelection);
        i18nModel = new I18N(control.getLanguageModelPath(),language);
        stages = control.getStagesLabels();
        selection = stages.get(index);
        scalesb = new ScaleStageBuilder(nifty,control,i18nFamily,i18nModel,i18nGui);
        singlesb = new SingleStageBuilder(nifty,control,i18nGui,i18nModel);
        multisb = new MultiStageBuilder(nifty, control, i18nGui, i18nFamily, i18nModel);
        animationsb = new AnimationStageBuilder(nifty, control, i18nGui, i18nFamily);
        if(popUp == null){
            popUp = new PopUpBuilder(nifty, control, i18nGui,i18nModel);
        }
        nifty.gotoScreen("scaleStage");
        ArrayList<String> idPanel = control.getIdsSubStages(selection);
        idPhysicalBuild = control.getIdsPhysicalBuild(idPanel.get(0));
        idBones = control.getIdBonesController(selection);
        buildMenu();
        
        loadScreen(control.getStageTypes(selection).toString());
        nifty.gotoScreen(control.getStageTypes(selection).toString());
    }
    
    public void changeScalePage(String steep){
        changePage(steep);
        scalesb.changeScalePage(page, selection);
    }
    
    public void changeTab(String param){
        if(selection.equals("")){
            animationsb.changeTab(param, selection);
        }
        else{
            if(control.getStageTypes(selection).toString().equals(StageType.scaleStage.toString())){
                changePage("0");
                scalesb.changeTab(param, selection);
            }
        }
    }
    
    public void changeScreen(String param){
        int oldIndex = index;
        selection = param;
        if(param.equals("+")){
            index++;
            if(index==stages.size()){
                selection = "";
            }
            else{
                selection = stages.get(index);
            }
            
        }else{
            if(param.equals("-")){
                index--;
                if(index>=0){
                    selection = stages.get(index);
                }
                else{
                    selection= "";
                }
            }else{
                if(param.equals("animationStage")){
                    selection ="";
                    index=stages.size();
                }
                else{
                    index = stages.lastIndexOf(selection);
                }
            }
        }
        if(!selection.equals("")){
            String stage = control.getStageTypes(selection).toString();
            String oldStage;
            if(oldIndex==stages.size()){
                oldStage = "animationStage";
            }
            else{
                oldStage = control.getStageTypes(stages.get(oldIndex)).toString();
            }
            if(!oldStage.equals(stage)){
                nifty.gotoScreen(stage);
                /*if(!stage.equals("animationStage")){
                    control.defaultCameraView();
                }*/
            }
            loadScreen(stage);
        }
        else{
            if(index < 0){
                control.removeModel();
                loadFamilyScreen();
            }
            else{
                nifty.gotoScreen("animationStage");
                loadScreen("animationStage");
            }
            //nifty.getScreen(stage).findElementByName("panel_screenleft").disable();
            //nifty.getScreen(stage).findElementByName("panel_screenleft").setVisible(false);
        }/*
        else{
            //nifty.getScreen(stage).findElementByName("panel_screenleft").enable();
            //nifty.getScreen(stage).findElementByName("panel_screenleft").setVisible(true);
        }*/
        
    }
    
    public void loadScreen(String type){
        loadMenu(type);
        if(type.equals(StageType.singleStage.toString())){
            changeTexturePage("0");
        }
        if(type.equals(StageType.scaleStage.toString())||type.equals(StageType.animationStage.toString())){
            changeTab("basic");
        }
        if(type.equals(StageType.multiStage.toString())){
            changeMultiTexturePage("0");
        }
    }
    
    public void loadMenu(String type){
        Iterator<String> it = stages.iterator();
        while(it.hasNext()){
            String auxStage = it.next();
                nifty.getScreen(type).findElementByName(auxStage+"menu-selection").setVisible(false);
                if(auxStage.equals(selection)){
                    nifty.getScreen(type).findElementByName(auxStage+"menu-selection").setVisible(true);
                }
        }
    }
    
    /******************************ChangePageImages*****************************/
    
    public void changeTexturePage(String steep){
        changePage(steep);
        singlesb.showTexturePage(selection,page);
    }
    
    public void changeMultiTexturePage(String steep){
        changePage(steep);
        multisb.showTexturePage(selection, page);
    }
    
    public void changePageSubStage(String t, String steep){
        int h = Integer.valueOf(t);
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
    
    public String getMenu(String param){
        if(param.equals("s1-header")){
            return "assets/Interface/s1-header.png";
        }
        if(param.equals("logo")){
            return "assets/Interface/logo.png";
        }
        if(param.equals("s1-settings")){
            return "assets/Interface/s1-settings.png";
        }
        if(param.equals("s1-settings-over")){
            return "assets/Interface/s1-settings-over.png";
        }
        if(param.equals("s2-header-left")){
            return "assets/Interface/s2-header-left.png";
        }
        if(param.equals("header-leftseparator")){
            return "assets/Interface/header-leftseparator.png";
        }
        if(param.equals("s2-header-right")){
            return "assets/Interface/s2-header-right.png";
        }
        if(param.equals("header-rightseparator")){
            return "assets/Interface/header-rightseparator.png";
        }
        if(param.equals("export")){
            return "assets/Interface/export.png";
        }
        if(param.equals("export-over")){
            return "assets/Interface/export-over.png";
        }
        if(param.equals("export-selection")){
            return "assets/Interface/export-selection.png";
        }
        if(param.equals("s2-settings")){
            return "assets/Interface/s2-settings.png";
        }
        if(param.equals("s2-settings-over")){
            return "assets/Interface/s2-settings-over.png";
        }
        if(param.equals("menu-selection")){
            return "assets/Interface/header-selection.png";
        }
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
        if(param.equals("ca")){
            return "assets/Interface/CuadroAzul.png";
        }
        return null;
    }
    
    public String getFont(String size){
        
        if(size.equals("15")){
            return "assets/Interface/Fonts/Brixton15.fnt";
        }
        if(size.equals("20")){
            return "assets/Interface/Fonts/Brixton20.fnt";
        }
        if(size.equals("30")){
            return "assets/Interface/Fonts/Brixton30.fnt";
        }
        return null;
    }
    
    public String getButton(String param){
        if(param.equals("left")){
            return "assets/Interface/scroll-left.png";
        }
        if(param.equals("left-over")){
            return "assets/Interface/scroll-left-over.png";
        }
        if(param.equals("right")){
            return "assets/Interface/scroll-right.png";
        }
        if(param.equals("right-over")){
            return "assets/Interface/scroll-right-over.png";
        }
        if(param.equals("up")){
            return "assets/Interface/scroll-up.png";
        }
        if(param.equals("up-over")){
            return "assets/Interface/scroll-up-over.png";
        }
        if(param.equals("down")){
            return "assets/Interface/scroll-down.png";
        }
        if(param.equals("down-over")){
            return "assets/Interface/scroll-down-over.png";
        }
        if(param.equals("info")){
            return "assets/Interface/s1-leftpanel-familyinfo.png";
        }
        if(param.equals("info-over")){
            return "assets/Interface/s1-leftpanel-familyinfo-over.png";
        }
        if(param.equals("color")){
            return "assets/Interface/rightpanel-selectcolor.png";
        }
        if(param.equals("color-over")){
            return "assets/Interface/rightpanel-selectcolor-over.png";
        }
        if(param.equals("next")){
            return "assets/Interface/next.png";
        }
        if(param.equals("next-over")){
            return "assets/Interface/next-over.png";
        }
        if(param.equals("previous")){
            return "assets/Interface/previous.png";
        }
        if(param.equals("previous-over")){
            return "assets/Interface/previous-over.png";
        }
        return null;
    }
    
    public String getTexture(String param){
        if(param.equals("s1-background")){
            return "assets/Interface/s1-background.png";
        }
        if(param.equals("s1-leftpanel")){
            return "assets/Interface/s1-leftpanel.png";
        }
        if(param.equals("family-button")){
            return "assets/Interface/family-button.png";
        }
        if(param.equals("family-button-over")){
            return "assets/Interface/family-button-over.png";
        }
        if(param.equals("s1-rightpanel")){
            return "assets/Interface/s1-rightpanel.png";
        }
        if(param.equals("s2-right-panel")){
            return "assets/Interface/s2-right-panel.png";
        }
        if(param.equals("model")){
            return "assets/Interface/model.png";
        }
        if(param.equals("s2-separator")){
            return "assets/Interface/s2-separator.png";
        }
        return null;
    }
    
    public String getTick(){
        
        return "assets/Interface/x.png";
    }
    
    public void changeTextureOrSubMesh(String substage, String idTextureOrSubMesh){
        if(control.getStageTypes(selection) == StageType.singleStage){
            singlesb.changeTextureOrSubMesh(selection,page,substage,idTextureOrSubMesh);
            textureOrSubMeshSelected = singlesb.getTextureOrSubMesh();
            subStageSelected = singlesb.getSubStage(selection, page, substage);
        }
        if(control.getStageTypes(selection) == StageType.multiStage){
            multisb.changeTextureOrSubMesh(selection,page,substage,idTextureOrSubMesh);
            textureOrSubMeshSelected = multisb.getTextureOrSubMesh(substage);
            subStageSelected = multisb.getSubStage(selection, page, substage);
        }
    }
    
  /******************************FamilyControler*****************************/
  
  public void changeFamilyPage(String steep){
      modelsb.showFamilyPage(steep);
  } 
  
  public void selectFamily(String id){
      int i = modelsb.selectFamily(id,getTexture("family-button"),getTexture("family-button-over"));
      control.selectFamily(families.get(i));
      i18nFamily = new I18N(control.getLanguageFamilyPath(),language);
      changeCharacterPage("0");
  }
    
  /******************************LocaleDropDownControler*****************************/  
    
  @NiftyEventSubscriber(id="localeDropDown")
  public void onLocaleDropDownSelectionChanged(final String id, final DropDownSelectionChangedEvent<String> event) {
    if (event.getSelection() != null) {
        Button startb = nifty.getScreen("start").findNiftyControl("startButton", Button.class);
        Button quitb = nifty.getScreen("start").findNiftyControl("quitButton", Button.class);
        language = event.getSelection();
        config.setProperty(Configuration.LANGUAGE, language);
        i18nGui = new I18N(config.getProperty(Configuration.LOCALE_PATH),language);
        nifty.getScreen("start").findElementByName("description").getRenderer(TextRenderer.class).setText(i18nGui.getString("idDescription"));
        nifty.getScreen("start").findElementByName("panel_mid").layoutElements();
        nifty.getScreen("start").findElementByName("languageText").getRenderer(TextRenderer.class).setText(i18nGui.getString("idLanguage"));
        nifty.getScreen("start").findElementByName("panel_location").layoutElements();
        startb.setText(i18nGui.getString("idStart"));
        quitb.setText(i18nGui.getString("idQuit"));
    }
  }
    
    /******************************FinishButtonControler*****************************/
    
    public void export() {
        popUp.export();
    }
    
    /******************************PopUpsControler*****************************/
    
    public void popUpButtonClicked(String id) {
        int sel = popUp.popUpButtonClicked(id);
        if(sel == 1){
                index = 0;
                selection = stages.get(index);
                loadScreen(control.getStageTypes(selection).toString());
                nifty.gotoScreen(control.getStageTypes(selection).toString());
        }
        if(sel==2){
            loadFamilyScreen();
        }
        
        if(sel==4){
            quitGame();
        }
    }
    
    /******************************PopUpColorControler*****************************/
    
    public void showWindowChangeColor(String h) throws InterruptedException{
        if(control.getStageTypes(selection) == StageType.singleStage){
            textureOrSubMeshSelected = singlesb.getTextureOrSubMesh();
            subStageSelected = singlesb.getSubStage();
        }
        if(control.getStageTypes(selection) == StageType.multiStage){
            textureOrSubMeshSelected = multisb.getTextureOrSubMesh(h);
            subStageSelected = multisb.getSubStage(h);
        }
        popUp.showWindowChangeColor(subStageSelected,textureOrSubMeshSelected);
    }
    
    public void changePopUpPage(String steep){
        popUp.changePopUpPage(steep,subStageSelected,textureOrSubMeshSelected);
    }
    
    public void changePopUpColor(String im){
        popUp.changePopUpColor(im,subStageSelected,textureOrSubMeshSelected);
    }
    
    public void changeSliderColor(String color, String id){
        popUp.changeSliderColor(color);
        popUp.unCheck();
        popUp.check(id);
    }
    
    @NiftyEventSubscriber(id="sliderR")
    public void onRedSliderChange(final String id, final SliderChangedEvent event) {
        popUp.onRedSliderChange(event.getValue());
    }

    @NiftyEventSubscriber(id="sliderG")
    public void onGreenSliderChange(final String id, final SliderChangedEvent event) {
        popUp.onGreenSliderChange(event.getValue());
    }

    @NiftyEventSubscriber(id="sliderB")
    public void onBlueSliderChange(final String id, final SliderChangedEvent event) {
        popUp.onBlueSliderChange(event.getValue());
    }
    
    @NiftyEventSubscriber(id="aceptButton")
    public void onChangeButtonClicked(final String id, final ButtonClickedEvent event) throws InterruptedException, IOException {
        popUp.accept(textureOrSubMeshSelected,subStageSelected);
    }
    
    @NiftyEventSubscriber(id="cancelButton")
    public void onCancelButtonClicked(final String id, final ButtonClickedEvent event) {
        popUp.cancel(); 
    }
    
    public void showMoreColors(){
        popUp.showMoreColors();
    }
    
    public void changeTabColor(String option){
        popUp.changeTabColor(option);
    }
    
    /******************************SliderControler*****************************/
    
    @NiftyEventSubscriber(id="slider0")
    public void onSlider0Change(final String id, final SliderChangedEvent event) {
        float inc = 1.0f + event.getValue() * 0.01f;
        control.setBoneControllerValue(idBones.get(page*BONES_PAGE), inc);
        control.setDefaultValueBoneController(idBones.get(page*BONES_PAGE),event.getValue());
    }
    
    @NiftyEventSubscriber(id="slider1")
    public void onSlider1Change(final String id, final SliderChangedEvent event) {
        float inc = 1.0f + event.getValue() * 0.01f;
        control.setBoneControllerValue(idBones.get(page*BONES_PAGE+1), inc);
        control.setDefaultValueBoneController(idBones.get(page*BONES_PAGE+1),event.getValue());

    }
    
    @NiftyEventSubscriber(id="slider2")
    public void onSlider2Change(final String id, final SliderChangedEvent event) {
        float inc = 1.0f + event.getValue() * 0.01f;
        control.setBoneControllerValue(idBones.get(page*BONES_PAGE+2), inc);
        control.setDefaultValueBoneController(idBones.get(page*BONES_PAGE+2),event.getValue());
    }
    
    @NiftyEventSubscriber(id="slider3")
    public void onSlider3Change(final String id, final SliderChangedEvent event) {
        float inc = 1.0f + event.getValue() * 0.01f;
        control.setBoneControllerValue(idBones.get(page*BONES_PAGE+3), inc);
        control.setDefaultValueBoneController(idBones.get(page*BONES_PAGE+3),event.getValue());
    }
    
    @NiftyEventSubscriber(id="slider4")
    public void onSlider4Change(final String id, final SliderChangedEvent event) {
        float inc = 1.0f + event.getValue() * 0.01f;
        control.setBoneControllerValue(idBones.get(page*BONES_PAGE+4), inc);
        control.setDefaultValueBoneController(idBones.get(page*BONES_PAGE+4),event.getValue());
    }
    
    /******************************PhysicalBuildControler*****************************/
    
    public void changeBodyType(String bodyType){
        control.setPhysicalBuild(idPhysicalBuild.get(page*BONES_PAGE+Integer.parseInt(bodyType)));
    }
    
    /******************************AnimationsControler*****************************/
    
    public void preview(String animation){
        animationsb.showAnimation(animation);
    }
    
    public void cameraPreview(String camera){
        animationsb.showCamera(camera);
    }
    
    public void changePageAnimations(String selection, String steep){
        animationsb.showPage(selection, steep);
    }
    
    @NiftyEventSubscriber(id="aCheckBox0")
    public void aCheckBox0Changed(final String id, final CheckBoxStateChangedEvent event){
        animationsb.checkOrUncheck("a",0,event.isChecked());
    }
    
    @NiftyEventSubscriber(id="aCheckBox1")
    public void aCheckBox1Changed(final String id, final CheckBoxStateChangedEvent event){
        animationsb.checkOrUncheck("a",1,event.isChecked());
    }
    
    @NiftyEventSubscriber(id="aCheckBox2")
    public void aCheckBox2Changed(final String id, final CheckBoxStateChangedEvent event){
        animationsb.checkOrUncheck("a",2,event.isChecked());
    }
    
    @NiftyEventSubscriber(id="qCheckBox0")
    public void qCheckBox0Changed(final String id, final CheckBoxStateChangedEvent event){
        animationsb.checkOrUncheck("q",0,event.isChecked());
    }
    
    @NiftyEventSubscriber(id="qCheckBox1")
    public void qCheckBox1Changed(final String id, final CheckBoxStateChangedEvent event){
        animationsb.checkOrUncheck("q",1,event.isChecked());
    }
    
    @NiftyEventSubscriber(id="qCheckBox2")
    public void qCheckBox2Changed(final String id, final CheckBoxStateChangedEvent event){
        animationsb.checkOrUncheck("q",2,event.isChecked());
    }
    
    @NiftyEventSubscriber(id="cCheckBox0")
    public void cCheckBox0Changed(final String id, final CheckBoxStateChangedEvent event){
        animationsb.checkOrUncheck("c",0,event.isChecked());
    }
    
    @NiftyEventSubscriber(id="cCheckBox1")
    public void cCheckBox1Changed(final String id, final CheckBoxStateChangedEvent event){
        animationsb.checkOrUncheck("c",1,event.isChecked());
    }
    
    @NiftyEventSubscriber(id="cCheckBox2")
    public void cCheckBox2Changed(final String id, final CheckBoxStateChangedEvent event){
        animationsb.checkOrUncheck("c",2,event.isChecked());
    }
    
    public void checkAll(String id, String bool){
        boolean b = false;
        if(bool.equals("true")){
            b = true;
        }
        animationsb.checkAll(id,b);
    }
}