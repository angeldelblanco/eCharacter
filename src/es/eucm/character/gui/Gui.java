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
import de.lessvoid.nifty.builder.PanelBuilder;
import de.lessvoid.nifty.builder.TextBuilder;
import de.lessvoid.nifty.controls.Button;
import de.lessvoid.nifty.controls.ButtonClickedEvent;
import de.lessvoid.nifty.controls.CheckBoxStateChangedEvent;
import de.lessvoid.nifty.controls.DropDown;
import de.lessvoid.nifty.controls.DropDownSelectionChangedEvent;
import de.lessvoid.nifty.controls.SliderChangedEvent;
import de.lessvoid.nifty.controls.dropdown.builder.DropDownBuilder;
import de.lessvoid.nifty.elements.render.PanelRenderer;
import de.lessvoid.nifty.elements.render.TextRenderer;
import de.lessvoid.nifty.screen.Screen;
import de.lessvoid.nifty.screen.ScreenController;
import de.lessvoid.nifty.tools.Color;
import es.eucm.character.control.Control;
import es.eucm.character.i18n.I18N;
import es.eucm.character.loader.Configuration;
import es.eucm.character.types.StageType;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;

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
    private ModelStageBuilder modelsb;
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
        modelsb = new ModelStageBuilder(nifty,control,i18nGui);
        DropDown family = nifty.getScreen("modelScreen").findNiftyControl("familyDropDown", DropDown.class);
        Iterator<String> it = families.iterator();
        while(it.hasNext()){
            control.selectFamily(it.next());
            I18N i18nAux = new I18N(control.getLanguageFamilyPath(),language);
            family.addItem(i18nAux.getString(control.getMetadataFamilyName()));
        }
        control.selectFamily(families.get(0));
        i18nFamily = new I18N(control.getLanguageFamilyPath(),language);
        family.selectItem(i18nFamily.getString(control.getMetadataFamilyName()));
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
        new DropDownBuilder("localeDropDown") {{
                valignCenter();
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
    
    public void changeCharacterPage(String steep){
        modelsb.changeCharacterPage(i18nFamily,steep);    
    }
    
    public void selectModel(String id){
        modelSelection = modelsb.selectModel(id);
    }
    
    public void loadFirstScreen(){
        control.selectModel(modelSelection);
        i18nModel = new I18N(control.getLanguageModelPath(),language);
        stages = control.getStagesLabels();
        selection = stages.get(index);
        scalesb = new ScaleStageBuilder(nifty,control,i18nFamily,i18nModel,i18nGui);
        singlesb = new SingleStageBuilder(nifty,control,i18nGui);
        multisb = new MultiStageBuilder(nifty, control, i18nGui, i18nFamily);
        animationsb = new AnimationStageBuilder(nifty, control, i18nGui, i18nFamily);
        popUp = new PopUpBuilder(nifty, control, i18nGui);
        ArrayList<String> idPanel = control.getIdsSubStages(selection);
        idPhysicalBuild = control.getIdsPhysicalBuild(idPanel.get(0));
        idBones = control.getIdBonesController(selection);
        buildMenu();
        
        loadScreen(control.getStagesTypes(selection).toString(),"","");
        nifty.gotoScreen(control.getStagesTypes(selection).toString());
    }
    
    public void changeScalePage(String steep){
        changePage(steep);
        scalesb.changeScalePage(page, selection);
    }
    
    public void changeTab(String param){
        if(control.getStagesTypes(selection).toString().equals(StageType.scaleStage.toString())){
            changePage("0");
            scalesb.changeTab(param, selection);
        }
        if(control.getStagesTypes(selection).toString().equals(StageType.animationStage.toString())){
            animationsb.changeTab(param, selection);
        }
    }
    
    public void changeScreen(String param){
        String old = selection;
        int oldIndex = index;
        selection = param;
        if(param.equals("+")){
            index++;
            selection = stages.get(index);
            
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
                index = stages.lastIndexOf(selection);
            }
        }
        if(!selection.equals("")){
            String stage = control.getStagesTypes(selection).toString();
            String oldStage = control.getStagesTypes(stages.get(oldIndex)).toString();
            if(!oldStage.equals(stage)){
                nifty.gotoScreen(stage);
            }
            loadScreen(stage,oldStage,old);
        }
        else{
            control.restartApplication();
            //nifty.getScreen(stage).findElementByName("panel_screenleft").disable();
            //nifty.getScreen(stage).findElementByName("panel_screenleft").setVisible(false);
        }/*
        else{
            //nifty.getScreen(stage).findElementByName("panel_screenleft").enable();
            //nifty.getScreen(stage).findElementByName("panel_screenleft").setVisible(true);
        }*/
    }
    
    public void loadScreen(String type, String oldType, String param){
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
    
    public String getButton(String param){
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
    
    public void changeTextureOrSubMesh(String substage, String idTextureOrSubMesh){
        if(control.getStagesTypes(selection) == StageType.singleStage){
            singlesb.changeTextureOrSubMesh(selection,page,substage,idTextureOrSubMesh);
            textureOrSubMeshSelected = singlesb.getTextureOrSubMesh();
            subStageSelected = singlesb.getSubStage(selection, page, substage);
        }
        if(control.getStagesTypes(selection) == StageType.multiStage){
            multisb.changeTextureOrSubMesh(selection,page,substage,idTextureOrSubMesh);
            textureOrSubMeshSelected = multisb.getTextureOrSubMesh(substage);
            subStageSelected = multisb.getSubStage(selection, page, substage);
        }
        control.changeTextureOrSubMesh(subStageSelected, textureOrSubMeshSelected);
        if(control.isMultiSelection(selection, subStageSelected)){
            if(control.getStagesTypes(selection) == StageType.singleStage){
                singlesb.checkIn(page);
            }
            if(control.getStagesTypes(selection) == StageType.multiStage){
                multisb.checkIn(Integer.valueOf(substage));
            }
        }
    }
    
  /******************************FamilyDropDownControler*****************************/
    
  @NiftyEventSubscriber(id="familyDropDown")
  public void onFamilyDropDownSelectionChanged(final String id, final DropDownSelectionChangedEvent<String> event) {
    if (event.getSelection() != null) {
        control.selectFamily(families.get(event.getSelectionItemIndex()));
        i18nFamily = new I18N(control.getLanguageFamilyPath(),language);
        changeCharacterPage("0");
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
    
    public void export() {
        popUp.export();
    }
    
    /******************************PopUpsControler*****************************/
    
    public void popUpButtonClicked(String id) {
        int sel = popUp.popUpButtonClicked(id);
        if(sel == 1){
                index = 0;
                selection = stages.get(index);
                loadScreen(control.getStagesTypes(selection).toString(),"","");
                nifty.gotoScreen(control.getStagesTypes(selection).toString());
        }
    }
    
    /******************************PopUpColorControler*****************************/
    
    public void showWindowChangeColor(String h) throws InterruptedException{
        if(control.getStagesTypes(selection) == StageType.singleStage){
            textureOrSubMeshSelected = singlesb.getTextureOrSubMesh();
            subStageSelected = singlesb.getSubStage();
        }
        if(control.getStagesTypes(selection) == StageType.multiStage){
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
    
    /*@NiftyEventSubscriber(id="baseCheckBox")
    public void onBaseCheckBoxChange(final String id, final SliderChangedEvent event) {
        if(popupColor != null){
            green = event.getValue();
            changeColor();
        }
    }
    
    @NiftyEventSubscriber(id="shadowCheckBox")
    public void onShadowCheckBoxChange(final String id, final SliderChangedEvent event) {
        if(popupColor != null){
            green = event.getValue();
            changeColor();
        }
    }*/
    
    public void changeSliderColor(String color){
        popUp.changeSliderColor(color);
    }
    
    public void changeSliderColor2(String color){
        popUp.changeSliderColor2(color);
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
    
    @NiftyEventSubscriber(id="sliderR2")
    public void onRed2SliderChange(final String id, final SliderChangedEvent event) {
        popUp.onRed2SliderChange(event.getValue());
    }

    @NiftyEventSubscriber(id="sliderG2")
    public void onGreen2SliderChange(final String id, final SliderChangedEvent event) {
        popUp.onGreen2SliderChange(event.getValue());
    }

    @NiftyEventSubscriber(id="sliderB2")
    public void onBlue2SliderChange(final String id, final SliderChangedEvent event) {
        popUp.onBlue2SliderChange(event.getValue());
    }
    
    @NiftyEventSubscriber(id="aceptButton")
    public void onChangeButtonClicked(final String id, final ButtonClickedEvent event) throws InterruptedException, IOException {
        popUp.accept(textureOrSubMeshSelected,subStageSelected);
    }
    
    @NiftyEventSubscriber(id="cancelButton")
    public void onCancelButtonClicked(final String id, final ButtonClickedEvent event) {
        popUp.cancel(); 
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