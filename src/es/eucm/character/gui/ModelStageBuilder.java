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

import es.eucm.character.control.Control;
import de.lessvoid.nifty.Nifty;
import de.lessvoid.nifty.builder.PanelBuilder;
import de.lessvoid.nifty.builder.TextBuilder;
import de.lessvoid.nifty.controls.dropdown.builder.DropDownBuilder;
import de.lessvoid.nifty.elements.Element;
import de.lessvoid.nifty.elements.render.ImageRenderer;
import de.lessvoid.nifty.elements.render.PanelRenderer;
import de.lessvoid.nifty.elements.render.TextRenderer;
import de.lessvoid.nifty.tools.Color;
import es.eucm.character.i18n.I18N;
import java.util.ArrayList;

public class ModelStageBuilder {
    private static final int MODELS_PAGE = 6;
    private Nifty nifty;
    private I18N i18nGui; 
    private Control control;
    private String stageType;
    private int modelsPage;
    
    public ModelStageBuilder(Nifty nifty, Control control,I18N i18nGui){
        stageType = "modelScreen";
        this.nifty = nifty;
        this.control = control;
        modelsPage = 0;
        this.i18nGui = i18nGui;
        initModels();
    }
    
    /*receives as input the lenguaje of the aplication
      and builds all the families and the pictures of models*/
    
    private void initModels(){
        nifty.getScreen(stageType).findElementByName("chooseText").getRenderer(TextRenderer.class).setText(i18nGui.getString("idChoose"));
        nifty.getScreen(stageType).findElementByName("choosePanel").layoutElements();
        nifty.getScreen(stageType).findElementByName("nextText").getRenderer(TextRenderer.class).setText(i18nGui.getString("idNext"));
        nifty.getScreen(stageType).findElementByName("panel_screenright").layoutElements();
        nifty.getScreen(stageType).findElementByName("panel_screenright").setVisible(false);
        nifty.getScreen(stageType).findElementByName("loadPopupPanel").setVisible(false);
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
        }}.build(nifty, nifty.getScreen(stageType), nifty.getScreen(stageType).findElementByName("familyPanel"));
    }
    
    //Change the model's page hiding pictures of previous models or previous family
    
    public void changeCharacterPage(I18N i18nFamily,String steep){
        if(steep.equals("+")){
            modelsPage++;
        }
        if(steep.equals("-")){
            modelsPage--;
        }
        if(steep.equals("0")){
            modelsPage = 0;
        }
        unCheck();
        for(int i=modelsPage*MODELS_PAGE; i<control.getNumModels(); i++){
            if(i<((modelsPage+1)*MODELS_PAGE)){
                Element image = nifty.getScreen(stageType).findElementByName("m"+Integer.toString(i%MODELS_PAGE));
                image.setVisible(true);
                ImageRenderer imager = image.getRenderer(ImageRenderer.class);
                imager.setImage(nifty.getRenderEngine().createImage(control.getModelIconPath(control.getModelsLabel().get(i)), false));
            }
        }
        for(int i=control.getNumModels();i<((modelsPage+1)*MODELS_PAGE);i++){
            Element image = nifty.getScreen(stageType).findElementByName("m"+Integer.toString(i%MODELS_PAGE));
            image.setVisible(false);
        }
        if(modelsPage > 0){
            nifty.getScreen(stageType).findElementByName("leftT").setVisible(true);
        }
        else{
            nifty.getScreen(stageType).findElementByName("leftT").setVisible(false);
        }
        if((((double)control.getNumModels()/(double)MODELS_PAGE) - modelsPage) > 1){
            nifty.getScreen(stageType).findElementByName("rightT").setVisible(true);
        }
        else{
            nifty.getScreen(stageType).findElementByName("rightT").setVisible(false);
        }
        String url = "";
        if(control.getMetadataFamilyURL()!=null){url = control.getMetadataFamilyURL();}
        System.out.println(i18nFamily.getString(control.getMetadataFamilyDescription())+"\n"+i18nFamily.getString(control.getMetadataFamilyAuthor())+"\n"+url);
        nifty.getScreen(stageType).findElementByName("descriptionText").getRenderer(TextRenderer.class).setText(i18nFamily.getString(control.getMetadataFamilyDescription())+"\n"+i18nFamily.getString(control.getMetadataFamilyAuthor())+"\n"+url);
        nifty.getScreen(stageType).findElementByName("descriptionPanel").layoutElements();
    }
    
    //Select the current model
    
    public String selectModel(String id){
        int i = modelsPage*MODELS_PAGE + Integer.valueOf(id);
        control.getModelFamilyPath(control.getModelsLabel().get(i));
        unCheck();
        nifty.getScreen(stageType).findElementByName("m"+id).getParent().getRenderer(PanelRenderer.class).setBackgroundColor(new Color("#FF0000AA"));
        nifty.getScreen(stageType).findElementByName("panel_screenright").setVisible(true);
        return control.getModelFamilyPath(control.getModelsLabel().get(i));
    } 
    
    //uncheck all the models
    
    private void unCheck(){
        for(int i = 0; i < MODELS_PAGE; i++){
            nifty.getScreen(stageType).findElementByName("t"+Integer.toString(i)).getRenderer(PanelRenderer.class).setBackgroundColor(new Color("#00000000"));
        }
        nifty.getScreen(stageType).findElementByName("panel_screenright").setVisible(false);
    }
}
