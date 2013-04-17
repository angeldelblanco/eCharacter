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
import de.lessvoid.nifty.builder.PanelBuilder;
import de.lessvoid.nifty.builder.TextBuilder;
import de.lessvoid.nifty.controls.Button;
import de.lessvoid.nifty.controls.button.builder.ButtonBuilder;
import de.lessvoid.nifty.controls.checkbox.builder.CheckboxBuilder;
import de.lessvoid.nifty.controls.radiobutton.builder.RadioButtonBuilder;
import de.lessvoid.nifty.elements.Element;
import de.lessvoid.nifty.elements.render.ImageRenderer;
import de.lessvoid.nifty.elements.render.PanelRenderer;
import de.lessvoid.nifty.elements.render.TextRenderer;
import de.lessvoid.nifty.tools.Color;
import es.eucm.character.control.Control;
import es.eucm.character.i18n.I18N;
import es.eucm.character.types.StageType;
import es.eucm.character.types.TexturesMeshType;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Set;

public class AnimationStageBuilder {
    private static final int CHECKBOX_PAGE = 3;
    private Nifty nifty;
    private I18N i18nGui, i18nFamily; 
    private Control control;
    private String stageType;
    private String tabSelected;
    private int animationPage, qualityPage, cameraPage;
    
    public AnimationStageBuilder(Nifty nifty, Control control, I18N i18nGui, I18N i18nFamily){
        stageType = StageType.animationStage.toString();
        animationPage = 0;
        qualityPage = 0;
        cameraPage = 0;
        this.nifty = nifty;
        this.control = control;
        this.i18nGui = i18nGui;
        this.i18nFamily = i18nFamily;
        nifty.getScreen(stageType).findElementByName("basicText").getRenderer(TextRenderer.class).setText(i18nGui.getString("idBasic"));
        nifty.getScreen(stageType).findElementByName("basicPanel").layoutElements();
        nifty.getScreen(stageType).findElementByName("customText").getRenderer(TextRenderer.class).setText(i18nGui.getString("idCustom"));
        nifty.getScreen(stageType).findElementByName("advancedPanel").layoutElements();
        nifty.getScreen(stageType).findElementByName("animationsText").getRenderer(TextRenderer.class).setText(i18nGui.getString("idAnimations"));
        nifty.getScreen(stageType).findElementByName("animationsPanel").layoutElements();
        nifty.getScreen(stageType).findElementByName("qualityText").getRenderer(TextRenderer.class).setText(i18nGui.getString("idQuality"));
        nifty.getScreen(stageType).findElementByName("qualityPanel").layoutElements();
        nifty.getScreen(stageType).findElementByName("cameraText").getRenderer(TextRenderer.class).setText(i18nGui.getString("idCamera"));
        nifty.getScreen(stageType).findElementByName("cameraPanel").layoutElements();
        /*String types[] = {"a","q","c"};
        for(String type : types){
            nifty.getScreen(stageType).findNiftyControl(type+"AllButton", Button.class).setText(i18nGui.getString("idAll"));
            nifty.getScreen(stageType).findNiftyControl(type+"NoButton", Button.class).setText(i18nGui.getString("idNothing"));
        }*/
    } 
    
    private void init(){
      
        /*Set<String> animations = control.getAnimationNames();
        Iterator<String> it1 = animations.iterator();
        int a = 0;
        while(it1.hasNext()){
            final String animation = it1.next();
            final int j = a;
            new PanelBuilder() {{
                width("40%");
                childLayoutHorizontal();
                text(new TextBuilder("AnimationText"+Integer.toString(j)){{
                    valignCenter();
                    color(Color.WHITE);
                    font("Interface/Fonts/Default.fnt");
                    text(animation);
                    width("80%");
                    wrap(true);
                }});
                control(new CheckboxBuilder("CheckBoxAnimation"+Integer.toString(j)) {{
                    checked(false);
                }});
                panel(new PanelBuilder() {{
                    width("5%");
                }});
                control(new ButtonBuilder("AnimationButton"+Integer.toString(j)){{
                    label(i18nGui.getString("idPreview"));
                    interactOnClick("preview("+animation+")");
                }});
            }}.build(nifty, nifty.getScreen(stage), nifty.getScreen(stage).findElementByName("previewPanel"));
            a++;
        }
        
        ArrayList<String> quality = control.getQualityLabels();
        Iterator<String> it = quality.iterator();
        int i = 0;
        while(it.hasNext()){
            final String q = it.next();
            final int j = i;
            new PanelBuilder() {{
                childLayoutHorizontal();
                text(new TextBuilder("RadioText"+Integer.toString(j)){{
                    valignCenter();
                    color(Color.WHITE);
                    font("Interface/Fonts/Default.fnt");
                    text(i18nFamily.getString(q));
                    width("70%");
                    wrap(true);
                }});
                control(new RadioButtonBuilder("Radio"+Integer.toString(j)) {{
                    group("RadioGroupQuality");
                }});
            }}.build(nifty, nifty.getScreen(stage), nifty.getScreen(stage).findElementByName("qualityRadioButtons"));
            i++;
        }
        
        ArrayList<String> cameras = control.getCamerasLabels();
        Iterator<String> it2 = cameras.iterator();
        i = 0;
        while(it2.hasNext()){
            final String camera = it2.next();
            final int j = i;
            new PanelBuilder() {{
                width("40%");
                childLayoutHorizontal();
                text(new TextBuilder("CheckText"+Integer.toString(j)){{
                    valignCenter();
                    color(Color.WHITE);
                    font("Interface/Fonts/Default.fnt");
                    text(i18nFamily.getString(camera));
                    width("80%");
                    wrap(true);
                }});
                control(new CheckboxBuilder("CheckBox"+Integer.toString(j)) {{
                    checked(false);
                }});
            }}.build(nifty, nifty.getScreen(stage), nifty.getScreen(stage).findElementByName("cameraPanel"));
            i++;
        }*/
    }
    
    public void showPage(String selection, String steep){
        
        int page = 0;
        int size = 0;
        ArrayList<String> labels = null;
        if(steep.equals("+")){
            page++;
        }
        else{
            if(steep.equals("-")){
                page--;
            }
        }
        if(selection.equals("a")){
            if(page == 0){
                animationPage = 0;
            }
            else{
                animationPage+=page;
            }
            page = animationPage;
            //labels = control.getAnimationNames();
            size = control.getNumAnimations();
        }
        else{
            if(selection.equals("q")){
                if(page == 0){
                    qualityPage = 0;
                }
                else{
                    qualityPage+=page;
                }
                page = qualityPage;
                labels = control.getQualityLabels();
                size = labels.size();
            }
            else{
                if(selection.equals("c")){
                    if(page == 0){
                        cameraPage = 0;
                    }
                    else{
                        cameraPage+=page;
                    }
                    page = cameraPage;
                    labels = control.getCamerasLabels();
                    size = control.getNumCameras();
                }
            }    
        }
        for(int i=page*CHECKBOX_PAGE; i<size; i++){
            if(i<((page+1)*CHECKBOX_PAGE)){
                nifty.getScreen(stageType).findElementByName(selection+Integer.toString(i%CHECKBOX_PAGE)).setVisible(true);
                nifty.getScreen(stageType).findElementByName(selection+"Text"+Integer.toString(i%CHECKBOX_PAGE)).getRenderer(TextRenderer.class).setText(i18nFamily.getString(labels.get(i)));
                nifty.getScreen(stageType).findElementByName(selection+Integer.toString(i%CHECKBOX_PAGE)).layoutElements();
                if(selection.equals("a")){
                }
                else{
                    if(selection.equals("q")){
                    }
                    else{
                        if(selection.equals("c")){
                        }
                    }
                }
            }
        }
        for(int i=size;i<((page+1)*CHECKBOX_PAGE);i++){
            nifty.getScreen(stageType).findElementByName(selection+Integer.toString(i%CHECKBOX_PAGE)).setVisible(false);
        }
        if(page > 0){
            nifty.getScreen(stageType).findElementByName(selection+"leftT").setVisible(true);
        }
        else{
            nifty.getScreen(stageType).findElementByName(selection+"leftT").setVisible(false);
        }
        if((((double)size/(double)CHECKBOX_PAGE) - page) > 1){
            nifty.getScreen(stageType).findElementByName(selection+"rightT").setVisible(true);
        }
        else{
            nifty.getScreen(stageType).findElementByName(selection+"rightT").setVisible(false);
        }
    }
    //Switches between Basic or Advanced tab
    
    public void changeTab(String param, String selection){
        tabSelected = param;
        if(tabSelected.equals("basic")){
            nifty.getScreen(stageType).findElementByName("Panel2").setVisible(false);
            nifty.getScreen(stageType).findElementByName("Panel3").setVisible(false);
            nifty.getScreen(stageType).findElementByName("basicPanel").getRenderer(PanelRenderer.class).setBackgroundColor(new Color("#00000000"));
            nifty.getScreen(stageType).findElementByName("advancedPanel").getRenderer(PanelRenderer.class).setBackgroundColor(new Color("#808080AA"));
            
        }    
        if(tabSelected.equals("advanced")){
            nifty.getScreen(stageType).findElementByName("Panel2").setVisible(true);
            nifty.getScreen(stageType).findElementByName("Panel3").setVisible(true);
            nifty.getScreen(stageType).findElementByName("basicPanel").getRenderer(PanelRenderer.class).setBackgroundColor(new Color("#808080AA"));
            nifty.getScreen(stageType).findElementByName("advancedPanel").getRenderer(PanelRenderer.class).setBackgroundColor(new Color("#00000000"));
            showPage("q","0");
            showPage("c","0");
        }
    }
    
}
