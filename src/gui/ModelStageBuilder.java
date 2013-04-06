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

package gui;

import control.Control;
import de.lessvoid.nifty.Nifty;
import de.lessvoid.nifty.builder.ImageBuilder;
import de.lessvoid.nifty.builder.PanelBuilder;
import de.lessvoid.nifty.builder.TextBuilder;
import de.lessvoid.nifty.controls.DropDown;
import de.lessvoid.nifty.controls.dropdown.builder.DropDownBuilder;
import de.lessvoid.nifty.elements.render.PanelRenderer;
import de.lessvoid.nifty.elements.render.TextRenderer;
import de.lessvoid.nifty.tools.Color;
import i18n.I18N;
import java.util.ArrayList;
import java.util.Iterator;

public class ModelStageBuilder {
    private static final int MODELS_PAGE = 6;
    private Nifty nifty;
    private I18N i18nGui; 
    private Control control;
    private String stage;
    private ArrayList<String> families;
    private int modelsPage;
    
    public ModelStageBuilder(Nifty nifty, Control control,I18N i18nGui, String language, ArrayList<String> families){
        stage = "modelScreen";
        this.nifty = nifty;
        this.control = control;
        this.families = families;
        modelsPage = 0;
        this.i18nGui = i18nGui;
        initModels(language);
    }
    
    private void initModels(String language){
        nifty.getScreen(stage).findElementByName("chooseText").getRenderer(TextRenderer.class).setText(i18nGui.getString("idChoose"));
        nifty.getScreen(stage).findElementByName("choosePanel").layoutElements();
        nifty.getScreen(stage).findElementByName("panel_screenright").disable();
        nifty.getScreen(stage).findElementByName("panel_screenright").setVisible(false);
        nifty.getScreen(stage).findElementByName("loadPopupPanel").setVisible(false);
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
        }}.build(nifty, nifty.getScreen(stage), nifty.getScreen(stage).findElementByName("familyPanel"));
        DropDown family = nifty.getScreen(stage).findNiftyControl("familyDropDown", DropDown.class);
        Iterator<String> it = families.iterator();
        while(it.hasNext()){
            control.selectFamily(it.next());
            I18N i18nFamily = new I18N(control.getLanguageFamilyPath(),language);
            ArrayList<String> models = control.getModelsLabel();
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
                image.id(i18nFamily.getString(control.getMetadataFamilyName())+"model"+Integer.toString(i));
                image.filename(control.getModelIconPath(m));
                image.interactOnClick("selectModel("+image.get("id")+","+control.getModelFamilyPath(m) +")");
                //image.onHoverEffect(new HoverEffectBuilder("Man"));
                //Nombre de los modelos con el i18n
                image.build(nifty, nifty.getScreen(stage), nifty.getScreen(stage).findElementByName("t"+Integer.toString(i%MODELS_PAGE)));
                i++;
            }
            family.addItem(i18nFamily.getString(control.getMetadataFamilyName()));
        }
    }
    
    public void changeCharacterPage(I18N i18nFamily,String steep, String familySelection, String familyAnt, int modelsSize, int modelsAntSize){
            for(int i=modelsPage*MODELS_PAGE; i<modelsAntSize; i++){
                if(i<((modelsPage+1)*MODELS_PAGE)){
                    nifty.getScreen(stage).findElementByName(familyAnt+"model"+Integer.toString(i)).setVisible(false);
                    nifty.getScreen(stage).findElementByName(familyAnt+"model"+Integer.toString(i)).setHeight(0);
                    nifty.getScreen(stage).findElementByName(familyAnt+"model"+Integer.toString(i)).setWidth(0);
                }
            }
            if(steep.equals("+")){
                modelsPage++;
            }
            if(steep.equals("-")){
                modelsPage--;
            }
            if(steep.equals("0")){
                unCheck();
                modelsPage = 0;
            }
            for(int i=modelsPage*MODELS_PAGE; i<modelsSize; i++){
                if(i<((modelsPage+1)*MODELS_PAGE)){
                    nifty.getScreen(stage).findElementByName(familySelection+"model"+Integer.toString(i)).setVisible(true);
                    nifty.getScreen(stage).findElementByName(familySelection+"model"+Integer.toString(i)).setHeight(nifty.getScreen(stage).findElementByName("t"+Integer.toString(i%MODELS_PAGE)).getHeight()-5);
                    nifty.getScreen(stage).findElementByName(familySelection+"model"+Integer.toString(i)).setWidth(nifty.getScreen(stage).findElementByName("t"+Integer.toString(i%MODELS_PAGE)).getWidth()-5);
                }
            }
            if(modelsPage > 0){
                nifty.getScreen(stage).findElementByName("leftT").enable();
                nifty.getScreen(stage).findElementByName("leftT").setVisible(true);
            }
            else{
                nifty.getScreen(stage).findElementByName("leftT").disable();
                nifty.getScreen(stage).findElementByName("leftT").setVisible(false);
            }
            if((((double)modelsSize/(double)MODELS_PAGE) - modelsPage) > 1){
                nifty.getScreen(stage).findElementByName("rightT").enable();
                nifty.getScreen(stage).findElementByName("rightT").setVisible(true);
            }
            else{
                nifty.getScreen(stage).findElementByName("rightT").disable();
                nifty.getScreen(stage).findElementByName("rightT").setVisible(false);
            }
            Iterator<String> it = families.iterator();
            while(it.hasNext()){
                control.selectFamily(it.next());
                if(i18nFamily.getString(control.getMetadataFamilyName()).equals(familySelection)){
                    String url = "";
                    if(control.getMetadataFamilyURL()!=null){url = control.getMetadataFamilyURL();}
                    nifty.getScreen(stage).findElementByName("descriptionText").getRenderer(TextRenderer.class).setText(i18nFamily.getString(control.getMetadataFamilyDescription())+"\n"+control.getMetadataFamilyAuthor()+"\n"+url);
                    nifty.getScreen(stage).findElementByName("descriptionPanel").layoutElements();
                }
            }
    }
    
    public void selectModel(String id){
        unCheck();
        nifty.getScreen(stage).findElementByName(id).getParent().getRenderer(PanelRenderer.class).setBackgroundColor(new Color("#FF0000AA"));
        nifty.getScreen(stage).findElementByName("nextText").getRenderer(TextRenderer.class).setText(i18nGui.getString("idNext"));
        nifty.getScreen(stage).findElementByName("panel_screenright").layoutElements();
        nifty.getScreen(stage).findElementByName("panel_screenright").enable();
        nifty.getScreen(stage).findElementByName("panel_screenright").setVisible(true);
    } 
    
    private void unCheck(){
        for(int i = 0; i < MODELS_PAGE; i++){
            nifty.getScreen(stage).findElementByName("t"+Integer.toString(i)).getRenderer(PanelRenderer.class).setBackgroundColor(new Color("#00000000"));
        }
    }
}
