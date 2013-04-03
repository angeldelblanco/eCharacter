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

import data.family.ModelRefType;
import data.family.Family;
import com.jme3.app.Application;
import com.jme3.app.state.AbstractAppState;
import com.jme3.app.state.AppStateManager;
import control.FamilyControl;
import control.ModelControl;
import de.lessvoid.nifty.Nifty;
import de.lessvoid.nifty.NiftyEventSubscriber;
import de.lessvoid.nifty.builder.HoverEffectBuilder;
import de.lessvoid.nifty.builder.ImageBuilder;
import de.lessvoid.nifty.builder.PanelBuilder;
import de.lessvoid.nifty.builder.PopupBuilder;
import de.lessvoid.nifty.builder.TextBuilder;
import de.lessvoid.nifty.controls.Button;
import de.lessvoid.nifty.controls.ButtonClickedEvent;
import de.lessvoid.nifty.controls.DropDown;
import de.lessvoid.nifty.controls.DropDownSelectionChangedEvent;
import de.lessvoid.nifty.controls.Slider;
import de.lessvoid.nifty.controls.SliderChangedEvent;
import de.lessvoid.nifty.controls.button.builder.ButtonBuilder;
import de.lessvoid.nifty.controls.dropdown.builder.DropDownBuilder;
import de.lessvoid.nifty.controls.label.builder.LabelBuilder;
import de.lessvoid.nifty.controls.slider.builder.SliderBuilder;
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
import types.Age;
import types.Gender;
import types.StageType;

public class StartScreen extends AbstractAppState implements ScreenController {
    
    private static final int TEXTURES_PAGE = 6;
    private static final int BONES_PAGE = 5;
    private Nifty nifty;
    private I18N i18nGui, i18nModel, i18nFamily; 
    private Application app;
    private Screen screen;
    private Gui gui;
    private FamilyControl fc;
    private ModelControl mc;
    private String selection, familySelection, panelSelection, modelSelection, tabSelected;
    private int page, modelsPage;
    private Element popupColor, popupAnim, popupFin;
    private float red, green, blue;
    private ArrayList<String> stages,idBones,idPhysicalBuild;
    private ArrayList<Family> families;
    private int modelsSize, modelsAntSize;
    private int index;
    private String languaje;
    
    public StartScreen(Gui gui){
        this.gui = gui;
    }
    
    public void startGame(String nextScreen) {
        nifty.gotoScreen(nextScreen);  // switch to another screen
        nifty.getScreen("modelScreen").findElementByName("chooseText").getRenderer(TextRenderer.class).setText(i18nGui.getString("idChoose"));
        nifty.getScreen("modelScreen").findElementByName("choosePanel").layoutElements();
        families = gui.getFamilies();
        nifty.getScreen("modelScreen").findElementByName("panel_screenright").disable();
        nifty.getScreen("modelScreen").findElementByName("panel_screenright").setVisible(false);
        nifty.getScreen("modelScreen").findElementByName("loadPopupPanel").setVisible(false);
        new TextBuilder("descriptionText"){{
               color(Color.BLACK);
               font("Interface/Fonts/Default.fnt");
               width("100%");
               height("100%");
               wrap(true);
        }}.build(nifty, nifty.getScreen("modelScreen"), nifty.getScreen("modelScreen").findElementByName("descriptionPanel"));
        new PanelBuilder() {{
            width("40%");
            childLayoutHorizontal();
            text(new TextBuilder(){{
                valignCenter();
                color(Color.WHITE);
                font("Interface/Fonts/Default.fnt");
                text(i18nGui.getString("idFamily"));
                width("50%");
                wrap(true);
            }});
            control(new DropDownBuilder("familyDropDown") {{
                valignCenter();
                width("50%");
            }});
        }}.build(nifty, nifty.getScreen("modelScreen"), nifty.getScreen("modelScreen").findElementByName("familyPanel"));
        DropDown family = nifty.getScreen("modelScreen").findNiftyControl("familyDropDown", DropDown.class);
        Iterator<Family> it = families.iterator();
        while(it.hasNext()){
            fc = new FamilyControl(it.next());
            i18nFamily = new I18N(fc.getLanguagePath(),languaje);
            ArrayList<String> models = fc.getModelsLabels();
            Iterator<String> itm = models.iterator();
            int i = 0;
            while(itm.hasNext()){
                String m = itm.next();
                ImageBuilder image = new ImageBuilder(){{
                    width("0%");
                    height("0%");
                    childLayoutOverlay();
                    text("Man");
                }};
                image.id(fc.getMetadataName()+"model"+Integer.toString(i));
                image.filename(fc.getModelIconPath(m));
                image.interactOnClick("selectModel("+fc.getModelPath(m)+",Man)");
                //image.onHoverEffect(new HoverEffectBuilder("Man"));
                //Nombre de los modelos con el i18n
                image.build(nifty, nifty.getScreen("modelScreen"), nifty.getScreen("modelScreen").findElementByName("t"+Integer.toString(i%TEXTURES_PAGE)));
                i++;
            }
            family.addItem(i18nFamily.getString(fc.getMetadataName()));
        }
        fc = new FamilyControl(families.get(0));
        modelsSize = fc.getNumModels();
        modelsAntSize = 0;
        familySelection = fc.getMetadataName();
        modelsPage = 0;
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
        popupColor();
        popupAnim = null;
        popupAnim();
        popupFin = null;
        popupFin();
        index = 0;
        familySelection = "";
        panelSelection = null;
        new DropDownBuilder("localeDropDown") {{
                valignCenter();
                //alignRight();
                width("100");
        }}.build(nifty, nifty.getScreen("start"), nifty.getScreen("start").findElementByName("panel_location"));
        DropDown locale = nifty.getScreen("start").findNiftyControl("localeDropDown", DropDown.class);
        ArrayList<String> languajes = gui.config.getListLanguagesAvailables();
        Iterator<String> it = languajes.iterator();
        while(it.hasNext()){
            final String l = it.next();
            locale.addItem(l);
        }
        languaje = gui.config.getProperty(Configuration.Language);
        locale.selectItem(languaje);
        i18nGui = new I18N(gui.config.getProperty(Configuration.LocalePath),languaje);
    }
    
    public void creaMenu(){
        String types[] = {StageType.singleStage.toString(),
                          StageType.scaleStage.toString(),
                          StageType.multiStage.toString(),
                          StageType.animationStage.toString()};
        for(String type : types){
            for(int i = 0; i<stages.size(); i++){
                final String pant = stages.get(i);
                PanelBuilder menu;
                menu = new PanelBuilder(){{
                    width(Float.toString(100/stages.size())+"%");
                    height("100%");
                    childLayoutCenter();
                    text(new TextBuilder(){{
                        color(Color.WHITE);
                        font("Interface/Fonts/Default.fnt");
                        text(pant);
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
    
    public void popupColor(){
        new PopupBuilder("popupColor") {{
                childLayoutCenter();
                backgroundColor("#fffa");
                panel(new PanelBuilder("popupPanel") {{
                    backgroundImage("assets/Interface/CuadroAzul.png");
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
    
    public void popupAnim()
    {
            new PanelBuilder("popupPanelAnim") {{
                height("100%");
                width("100%");
                childLayoutVertical();
                panel(new PanelBuilder("panelTexto") {{
                    height("50%");
                    childLayoutCenter();
                    control(new LabelBuilder() {{
                            alignCenter();
                            text("Do you want to tweak the model, or go to the export stage?");
                            width("100%");
                    }});
                }});
                panel(new PanelBuilder("panelButton") {{
                    childLayoutHorizontal();
                    height("50%");
                    panel(new PanelBuilder("panelTweak") {{
                        height("90%");
                        width("50%");
                        valignCenter();
                        childLayoutCenter();
                        control(new ButtonBuilder("tweakButton", "Tweak model"));
                    }});
                    panel(new PanelBuilder("panelExport") {{
                        height("90%");
                        width("50%");
                        valignCenter();
                        childLayoutCenter();
                        control(new ButtonBuilder("exportButton", "Export stage"));
                    }});
                }});
            }}.build(nifty, nifty.getScreen("popupScreen"), nifty.getScreen("popupScreen").findElementByName("popup"));
    }
    
    public void popupFin()
    {
            new PanelBuilder("popupPanel") {{
                height("100%");
                width("100%");
                childLayoutVertical();
                panel(new PanelBuilder("panelTexto") {{
                    height("50%");
                    childLayoutCenter();
                    control(new LabelBuilder() {{
                            alignCenter();
                            text("Do you want to generate a new model?");
                            width("100%");
                    }});
                }});
                panel(new PanelBuilder("panelButton") {{
                        childLayoutHorizontal();
                        height("50%");
                        panel(new PanelBuilder("panelYes") {{
                            height("90%");
                            width("50%");
                            valignCenter();
                            childLayoutCenter();
                            control(new ButtonBuilder("yesButton", "Yes"));
                        }});
                        panel(new PanelBuilder("panelNo") {{
                            height("90%");
                            width("50%");
                            valignCenter();
                            childLayoutCenter();
                            control(new ButtonBuilder("noButton", "No"));
                        }});
                }});
            }}.build(nifty, nifty.getScreen("popupScreen"), nifty.getScreen("popupfinScreen").findElementByName("popup"));
    }
    
    public void changeCharacterPage(String steep, String familyAnt){
            for(int i=modelsPage*TEXTURES_PAGE; i<modelsAntSize; i++){
                if(familyAnt.equals("same")){
                    familyAnt = familySelection;
                    modelsAntSize = modelsSize;
                }
                if(i<((modelsPage+1)*TEXTURES_PAGE)){
                    nifty.getScreen("modelScreen").findElementByName(familyAnt+"model"+Integer.toString(i)).setVisible(false);
                    nifty.getScreen("modelScreen").findElementByName(familyAnt+"model"+Integer.toString(i)).setHeight(0);
                    nifty.getScreen("modelScreen").findElementByName(familyAnt+"model"+Integer.toString(i)).setWidth(0);
                }
            }
            if(steep.equals("+")){modelsPage++;}
            if(steep.equals("-")){modelsPage--;}
            if(steep.equals("0")){modelsPage = 0;}
            for(int i=modelsPage*TEXTURES_PAGE; i<modelsSize; i++){
                if(i<((modelsPage+1)*TEXTURES_PAGE)){
                    nifty.getScreen("modelScreen").findElementByName(familySelection+"model"+Integer.toString(i)).setVisible(true);
                    nifty.getScreen("modelScreen").findElementByName(familySelection+"model"+Integer.toString(i)).setHeight(nifty.getScreen("modelScreen").findElementByName("t"+Integer.toString(i%TEXTURES_PAGE)).getHeight());
                    nifty.getScreen("modelScreen").findElementByName(familySelection+"model"+Integer.toString(i)).setWidth(nifty.getScreen("modelScreen").findElementByName("t"+Integer.toString(i%TEXTURES_PAGE)).getWidth());
                }
            }
            if(modelsPage > 0){
                nifty.getScreen("modelScreen").findElementByName("leftT").enable();
                nifty.getScreen("modelScreen").findElementByName("leftT").setVisible(true);
            }
            else{
                nifty.getScreen("modelScreen").findElementByName("leftT").disable();
                nifty.getScreen("modelScreen").findElementByName("leftT").setVisible(false);
            }
            if((((double)modelsSize/(double)TEXTURES_PAGE) - modelsPage) > 1){
                nifty.getScreen("modelScreen").findElementByName("rightT").enable();
                nifty.getScreen("modelScreen").findElementByName("rightT").setVisible(true);
            }
            else{
                nifty.getScreen("modelScreen").findElementByName("rightT").disable();
                nifty.getScreen("modelScreen").findElementByName("rightT").setVisible(false);
            }
            Iterator<Family> it = families.iterator();
            while(it.hasNext()){
                fc = new FamilyControl(it.next());
                if(fc.getMetadataName().equals(familySelection)){
                    String url = "";
                    if(fc.getMetadataURL()!=null){url = fc.getMetadataURL();}
                    nifty.getScreen("modelScreen").findElementByName("descriptionText").getRenderer(TextRenderer.class).setText(i18nFamily.getString(fc.getMetadataDescription())+"\n"+fc.getMetadataAuthor()+"\n"+url);
                    nifty.getScreen("modelScreen").findElementByName("descriptionPanel").layoutElements();
                }
            }
    }
    
    public void selectModel(String param,String param2){
        if(panelSelection != null){
            nifty.getScreen("modelScreen").findElementByName(panelSelection).getRenderer(PanelRenderer.class).setBackgroundColor(new Color("#FF000000"));
        }
        modelSelection = param;
        if(param2.equals("Man")){
            gui.setGender(Gender.Male);
            gui.setAgeModel(Age.Adult);
            panelSelection = "t0";
        }
        if(param2.equals("Woman")){
            gui.setGender(Gender.Female);
            gui.setAgeModel(Age.Adult);
            panelSelection = "t1";
        }
        if(param2.equals("Boy")){
            gui.setGender(Gender.Male);
            gui.setAgeModel(Age.Young);
            panelSelection = "t2";
        }
        if(param2.equals("Girl")){
            gui.setGender(Gender.Female);
            gui.setAgeModel(Age.Young);
            panelSelection = "t3";
        }
        nifty.getScreen("modelScreen").findElementByName(panelSelection).getRenderer(PanelRenderer.class).setBackgroundColor(new Color("#FF0000AA"));
        nifty.getScreen("modelScreen").findElementByName("nextText").getRenderer(TextRenderer.class).setText(i18nGui.getString("idNext"));
        nifty.getScreen("modelScreen").findElementByName("panel_screenright").layoutElements();
        nifty.getScreen("modelScreen").findElementByName("panel_screenright").enable();
        nifty.getScreen("modelScreen").findElementByName("panel_screenright").setVisible(true);
    }
    
    public void loadFirstScreen(){
        nifty.getScreen("modelScreen").findElementByName("loadPopupPanel").setVisible(true);
        gui.loadModel();
        /*Iterator<Family> it = families.iterator();
        while(it.hasNext()){
            Family f = it.next();
            if(f.getMetadata().getName().equals(familySelection)){
                fc = new FamilyControl(f);
            }
        }*/
        i18nModel = new I18N(gui.getModel(modelSelection).getLanguagesPath(),languaje);
        mc = new ModelControl(gui.getModel(modelSelection));
        stages = fc.getStagesLabels();
        selection = stages.get(index);
        creaMenu();
        initIcons();
        cargaScreen(fc.getStagesTypes(selection).toString(),"","");
        nifty.getScreen(fc.getStagesTypes(selection).toString()).findElementByName("panel_screenleft").disable();
        nifty.getScreen(fc.getStagesTypes(selection).toString()).findElementByName("panel_screenleft").setVisible(false);
        nifty.gotoScreen(fc.getStagesTypes(selection).toString());
    }
    
    public void initIcons(){
        String stage = StageType.singleStage.toString();
        for(int j = 0; j < stages.size(); j++){
            if(fc.getStagesTypes(stages.get(j)).toString().equals(stage)){
                ArrayList<String> idSubStages = fc.getIdsSubStages(stages.get(j));
                ArrayList<String> idsTextures = mc.getIdsTexturesORSubMeshes(idSubStages.get(0));
                //ArrayList<String> idTexture = mc.getIdsTextures(stages.get(j));
                for(int i=0; i<mc.getNumTexturesORSubMeshes(idSubStages.get(0)); i++){
                    ImageBuilder image = new ImageBuilder(){{
                        width("0%");
                        height("0%");
                    }};
                    image.id(stages.get(j)+"i"+Integer.toString(i));
                    image.filename(mc.getIconPathTexturesORSubMeshes(idsTextures.get(i)));
                    //image.filename(gui.path(stages.get(j),i));
                    image.interactOnClick("changeTexture("+Integer.toString(i)+")");
                    image.build(nifty, nifty.getScreen(stage), nifty.getScreen(stage).findElementByName("t"+Integer.toString(i%TEXTURES_PAGE)));
                }
                nifty.getScreen(stage).findElementByName("colorText").getRenderer(TextRenderer.class).setText(i18nGui.getString("idColor"));
                nifty.getScreen(stage).findElementByName("panel_color").layoutElements(); 
            }
        }
    }
    
    public void changeScalePage(String steep){
            if(steep.equals("+")){page++;}
            if(steep.equals("-")){page--;}
            if(steep.equals("0")){page = 0;}
            String stage = fc.getStagesTypes(selection).toString();
            int bonesSize = 0;
            if(tabSelected.equals("basic")){
                ArrayList<String> idPanel = fc.getIdsSubStages(selection);
                idPhysicalBuild = mc.getIdsPhysicalBuild(idPanel.get(0));
                bonesSize = idPhysicalBuild.size();
                for(int i=page*BONES_PAGE; i<bonesSize; i++){
                    if(i<((page+1)*BONES_PAGE)){
                       nifty.getScreen(stage).findElementByName("text"+Integer.toString(i%BONES_PAGE)).getRenderer(TextRenderer.class).setText(mc.getPhysicalBuildLabel(idPhysicalBuild.get(i)));
                       nifty.getScreen(stage).findElementByName("cont"+Integer.toString(i%BONES_PAGE)).layoutElements();
                       nifty.getScreen(stage).findElementByName("cont"+Integer.toString(i%BONES_PAGE)).enable();
                       nifty.getScreen(stage).findElementByName("cont"+Integer.toString(i%BONES_PAGE)).setVisible(true);
                       nifty.getScreen(stage).findElementByName("slider"+Integer.toString(i%BONES_PAGE)).setVisible(false);
                    }
                }
                nifty.getScreen(stage).findElementByName("panel_basic").getRenderer(PanelRenderer.class).setBackgroundColor(new Color("#00000000"));
                nifty.getScreen(stage).findElementByName("panel_advanced").getRenderer(PanelRenderer.class).setBackgroundColor(new Color("#808080AA"));
            }
            if(tabSelected.equals("advanced")){
                idBones = fc.getIdBonesController(selection);
                bonesSize = idBones.size();
                for(int i=page*BONES_PAGE; i<bonesSize; i++){
                    if(i<((page+1)*BONES_PAGE)){
                       nifty.getScreen(stage).findElementByName("text"+Integer.toString(i%BONES_PAGE)).getRenderer(TextRenderer.class).setText(fc.getBoneControllerLabel(selection, idBones.get(i)));
                       nifty.getScreen(stage).findElementByName("cont"+Integer.toString(i%BONES_PAGE)).layoutElements();
                       nifty.getScreen(stage).findElementByName("cont"+Integer.toString(i%BONES_PAGE)).disable();
                       nifty.getScreen(stage).findElementByName("cont"+Integer.toString(i%BONES_PAGE)).setVisible(true);
                       nifty.getScreen(stage).findElementByName("slider"+Integer.toString(i%BONES_PAGE)).setVisible(true);
                       Slider s = nifty.getScreen(stage).findNiftyControl("slider"+Integer.toString(i%BONES_PAGE), Slider.class);
                       s.setMax(mc.getMaxValueBoneController(idBones.get(i)));
                       s.setMin(mc.getMinValueBoneController(idBones.get(i)));
                       s.setValue(mc.getDefaultValueBoneController(idBones.get(i)));
                    }
                }
                nifty.getScreen(stage).findElementByName("panel_basic").getRenderer(PanelRenderer.class).setBackgroundColor(new Color("#808080AA"));
                nifty.getScreen(stage).findElementByName("panel_advanced").getRenderer(PanelRenderer.class).setBackgroundColor(new Color("#00000000"));
            }
            for(int i=bonesSize;i<((page+1)*BONES_PAGE);i++){
                nifty.getScreen(stage).findElementByName("cont"+Integer.toString(i%BONES_PAGE)).disable();
                nifty.getScreen(stage).findElementByName("cont"+Integer.toString(i%BONES_PAGE)).setVisible(false);
                nifty.getScreen(stage).findElementByName("slider"+Integer.toString(i%BONES_PAGE)).setVisible(false);
            }
            if(page > 0){
                nifty.getScreen(stage).findElementByName("leftT").enable();
                nifty.getScreen(stage).findElementByName("leftT").setVisible(true);
            }
            else{
                nifty.getScreen(stage).findElementByName("leftT").disable();
                nifty.getScreen(stage).findElementByName("leftT").setVisible(false);
            }
            if((((double)bonesSize/(double)BONES_PAGE) - page) > 1){
                nifty.getScreen(stage).findElementByName("rightT").enable();
                nifty.getScreen(stage).findElementByName("rightT").setVisible(true);
            }
            else{
                nifty.getScreen(stage).findElementByName("rightT").disable();
                nifty.getScreen(stage).findElementByName("rightT").setVisible(false);
            }
    }
    /*public void changeMultiTexture(String steep){
        if(steep.equals("+")){page++;}
        if(steep.equals("-")){page--;}
        if(steep.equals("0")){page = 0;}
        String stage = fc.getStagesTypes(selection).toString();
        ArrayList<String> idSubStages = fc.getIdsSubStages(selection);
        for(int i=page*BONES_PAGE; i<fc.getNumSubStage(selection); i++){
            if(i<((page+1)*BONES_PAGE)){
               nifty.getScreen(stage).findElementByName("text"+Integer.toString(i)).getRenderer(TextRenderer.class).setText(names[i]);
               nifty.getScreen(stage).findElementByName("cont"+Integer.toString(i)).layoutElements();
            }
        }
        if(page > 0){
            nifty.getScreen(stage).findElementByName("leftT").enable();
            nifty.getScreen(stage).findElementByName("leftT").setVisible(true);
        }
        else{
            nifty.getScreen(stage).findElementByName("leftT").disable();
            nifty.getScreen(stage).findElementByName("leftT").setVisible(false);
        }
        if((((double)fc.getNumSubStage(selection)/(double)BONES_PAGE) - page) > 1){
            nifty.getScreen(stage).findElementByName("rightT").enable();
            nifty.getScreen(stage).findElementByName("rightT").setVisible(true);
        }
        else{
            nifty.getScreen(stage).findElementByName("rightT").disable();
            nifty.getScreen(stage).findElementByName("rightT").setVisible(false);
        }
    }*/
    
    public void changeTab(String param){
        String stage = fc.getStagesTypes(selection).toString();
        tabSelected = param;
        changeScalePage("0");
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
        String stage = fc.getStagesTypes(selection).toString();
        String oldStage = fc.getStagesTypes(stages.get(oldIndex)).toString(); 
        if(!oldStage.equals(stage)){
            nifty.gotoScreen(stage);
        }
        cargaScreen(stage,oldStage,old);
        gui.setTypeObject(selection);
        if(index==0){
            nifty.getScreen(stage).findElementByName("panel_screenleft").disable();
            nifty.getScreen(stage).findElementByName("panel_screenleft").setVisible(false);
        }
        else{
            nifty.getScreen(stage).findElementByName("panel_screenleft").enable();
            nifty.getScreen(stage).findElementByName("panel_screenleft").setVisible(true);
        }
    }
    
    public void cargaScreen(String type, String oldType, String param){
        cargaMenu(type);
        if(type.equals(StageType.singleStage.toString())){
            escondeTexturePage(oldType, param);
            changeTexturePage("0");
        }
        if(type.equals(StageType.scaleStage.toString())){
            changeTab("basic");
        }
        if(type.equals(StageType.multiStage.toString())){
            //changeMultiTexture("0");
        }
    }
    
    public void cargaMenu(String type){
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
    
    public void escondeTexturePage(String type, String param){
        if(type.equals(StageType.singleStage.toString())){
            ArrayList<String> idSubStages = fc.getIdsSubStages(param);
            for(int i=page*TEXTURES_PAGE; i<mc.getNumTexturesORSubMeshes(idSubStages.get(0)); i++){
                if(i<((page+1)*TEXTURES_PAGE)){
                    nifty.getScreen(type).findElementByName(param+"i"+Integer.toString(i)).setVisible(false);
                    nifty.getScreen(type).findElementByName(param+"i"+Integer.toString(i)).setHeight(0);
                    nifty.getScreen(type).findElementByName(param+"i"+Integer.toString(i)).setWidth(0);
                }
            }
        }
    }
    
    public void changeTexturePage(String steep){
            String stage = StageType.singleStage.toString();
            if(!steep.equals("0")){
                escondeTexturePage(stage,selection);
            }
            changePage(steep);
            ArrayList<String> idSubStages = fc.getIdsSubStages(selection);
            for(int i=page*TEXTURES_PAGE; i<mc.getNumTexturesORSubMeshes(idSubStages.get(0)); i++){
                if(i<((page+1)*TEXTURES_PAGE)){
                    nifty.getScreen(stage).findElementByName(selection+"i"+Integer.toString(i)).setVisible(true);
                    nifty.getScreen(stage).findElementByName(selection+"i"+Integer.toString(i)).setHeight(nifty.getScreen(stage).findElementByName("t"+Integer.toString(i%TEXTURES_PAGE)).getHeight());
                    nifty.getScreen(stage).findElementByName(selection+"i"+Integer.toString(i)).setWidth(nifty.getScreen(stage).findElementByName("t"+Integer.toString(i%TEXTURES_PAGE)).getWidth());
                }
            }
            if(page > 0){
                nifty.getScreen(stage).findElementByName("leftT").enable();
                nifty.getScreen(stage).findElementByName("leftT").setVisible(true);
            }
            else{
                nifty.getScreen(stage).findElementByName("leftT").disable();
                nifty.getScreen(stage).findElementByName("leftT").setVisible(false);
            }
            if((((double)mc.getNumTexturesORSubMeshes(idSubStages.get(0))/(double)TEXTURES_PAGE) - page) > 1){
                nifty.getScreen(stage).findElementByName("rightT").enable();
                nifty.getScreen(stage).findElementByName("rightT").setVisible(true);
            }
            else{
                nifty.getScreen(stage).findElementByName("rightT").disable();
                nifty.getScreen(stage).findElementByName("rightT").setVisible(false);
            }
    }
    
    public void changePage(String steep){
        if(steep.equals("+")){page++;}
        if(steep.equals("-")){page--;}
        if(steep.equals("0")){page = 0;}
    }
    
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
        blue = 0;
        green = 0;
        //Si lleva el if a los X cambios de color lanza excepcion
        //if(popupColor == null){
            popupColor = nifty.createPopup("popupColor");
        //}
        nifty.showPopup(nifty.getCurrentScreen(), popupColor.getId(), null);
    }
    
    @NiftyEventSubscriber(id="familyDropDown")
  public void onFamilyDropDownSelectionChanged(final String id, final DropDownSelectionChangedEvent<String> event) {
    if (event.getSelection() != null && (!familySelection.equals(""))) {
        String familyAnt = familySelection;
        Iterator<Family> it = families.iterator();
        familySelection = event.getSelection();
        while(it.hasNext()){
           fc = new FamilyControl(it.next());
           if(i18nFamily.getString(fc.getMetadataName()).equals(familySelection)){
               modelsAntSize = modelsSize;
               modelsSize = fc.getNumModels();
               i18nFamily = new I18N(fc.getLanguagePath(),languaje);
           }
        }
        changeCharacterPage("0", familyAnt);
    }
  }
    @NiftyEventSubscriber(id="localeDropDown")
  public void onLocaleDropDownSelectionChanged(final String id, final DropDownSelectionChangedEvent<String> event) {
    if (event.getSelection() != null) {
        Button startb = nifty.getScreen("start").findNiftyControl("startButton", Button.class);
        Button quitb = nifty.getScreen("start").findNiftyControl("quitButton", Button.class);
        languaje = event.getSelection();
        i18nGui = new I18N(gui.config.getProperty(Configuration.LocalePath),languaje);
        nifty.getScreen("start").findElementByName("description").getRenderer(TextRenderer.class).setText(i18nGui.getString("idDescription"));
        nifty.getScreen("start").findElementByName("panel_mid").layoutElements();
        nifty.getScreen("start").findElementByName("languageText").getRenderer(TextRenderer.class).setText(i18nGui.getString("idLanguage"));
        nifty.getScreen("start").findElementByName("panel_location").layoutElements();
        startb.setText(i18nGui.getString("idStart"));
        quitb.setText(i18nGui.getString("idQuit"));
    }
  }
    
    @NiftyEventSubscriber(id="aceptButton")
    public void onChangeButtonClicked(final String id, final ButtonClickedEvent event) throws InterruptedException, IOException {
        gui.changeColor(red / 255.f, green / 255.f, blue / 255.f);
        nifty.closePopup(popupColor.getId()); 
    }
    
    @NiftyEventSubscriber(id="cancelButton")
    public void onCancelButtonClicked(final String id, final ButtonClickedEvent event) {
        nifty.closePopup(popupColor.getId()); 
    }
    
    @NiftyEventSubscriber(id="tweakButton")
    public void onTweakButtonClicked(final String id, final ButtonClickedEvent event) throws InterruptedException, IOException {
        //Lanza excepcion al cambiar de pantalla
        changeScreen("basicScreen");
    }
    
    @NiftyEventSubscriber(id="exportButton")
    public void onExportButtonClicked(final String id, final ButtonClickedEvent event) {
        nifty.gotoScreen("popupfinScreen");
        //gui.screenshot();
    }
    
    @NiftyEventSubscriber(id="yesButton")
    public void onYesButtonClicked(final String id, final ButtonClickedEvent event) throws InterruptedException, IOException {
        nifty.gotoScreen("start"); 
    }
    
    @NiftyEventSubscriber(id="noButton")
    public void onNoButtonClicked(final String id, final ButtonClickedEvent event) {
        //nifty.gotoScreen("finalScreen");
        //nifty.closePopup(popupFin.getId());
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
            popupColor.findElementByName("panelColor").getRenderer(PanelRenderer.class).setBackgroundColor(new Color(red / 255.f, green / 255.f, blue / 255.f, 1));
        //}
    }
    
    @NiftyEventSubscriber(id="slider0")
    public void onHeadSliderChange(final String id, final SliderChangedEvent event) 
    {
        float inc = 1.0f + event.getValue() * 0.01f;
        gui.scaleHead(inc);
    }
    
    @NiftyEventSubscriber(id="slider1")
    public void onTorsoSliderChange(final String id, final SliderChangedEvent event) 
    {
        float inc = 1.0f + event.getValue() * 0.01f;
        gui.scaleTorax(inc);
    }
    
    @NiftyEventSubscriber(id="slider3")
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
    
    @NiftyEventSubscriber(id="slider4")
    public void onLegsSliderChange(final String id, final SliderChangedEvent event) 
    {
        float inc = 1.0f + event.getValue() * 0.01f;
        gui.scaleLegs(inc);
    }
    
    @NiftyEventSubscriber(id="slider2")
    public void onArmsSliderChange(final String id, final SliderChangedEvent event) 
    {
        float inc = 1.0f + event.getValue() * 0.01f;
        gui.scaleArms(inc);
    }
    
    public void screenshot() 
    {
        nifty.gotoScreen("popupScreen");
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