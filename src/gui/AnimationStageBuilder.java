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
import de.lessvoid.nifty.builder.PanelBuilder;
import de.lessvoid.nifty.builder.TextBuilder;
import de.lessvoid.nifty.controls.checkbox.builder.CheckboxBuilder;
import de.lessvoid.nifty.controls.radiobutton.builder.RadioButtonBuilder;
import de.lessvoid.nifty.elements.render.PanelRenderer;
import de.lessvoid.nifty.elements.render.TextRenderer;
import de.lessvoid.nifty.tools.Color;
import i18n.I18N;
import java.util.ArrayList;
import java.util.Iterator;
import types.StageType;

public class AnimationStageBuilder {
    private Nifty nifty;
    private I18N i18nGui, i18nFamily; 
    private Control control;
    private String stage;
    private String tabSelected;
    
    public AnimationStageBuilder(Nifty nifty, Control control, I18N i18nGui, I18N i18nFamily){
        stage = StageType.animationStage.toString();
        this.nifty = nifty;
        this.control = control;
        this.i18nGui = i18nGui;
        this.i18nFamily = i18nFamily;
        init();
    }
    
    private void init(){
      
        nifty.getScreen(stage).findElementByName("previewText").getRenderer(TextRenderer.class).setText(i18nGui.getString("idPreview"));
        nifty.getScreen(stage).findElementByName("qualityText").getRenderer(TextRenderer.class).setText(i18nGui.getString("idQuality"));
        nifty.getScreen(stage).findElementByName("qualityPanel").layoutElements();
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
        int limit;
        //if(fc.getNumCameras()<)
        limit = control.getNumCameras();
        nifty.getScreen(stage).findElementByName("checkBoxText").getRenderer(TextRenderer.class).setText(i18nGui.getString("idCamera"));
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
            }}.build(nifty, nifty.getScreen(stage), nifty.getScreen(stage).findElementByName("checkBoxPanel"));
            i++;
        }
    }
    //Switches between Basic or Advanced tab
    
    public void changeTab(String param, String selection){
        tabSelected = param;
        if(tabSelected.equals("basic")){
                int limit = control.getNumCameras();
                nifty.getScreen(stage).findElementByName("qualityText").setVisible(false);
                nifty.getScreen(stage).findElementByName("checkBoxText").setVisible(false);
                for(int i = 0; i<control.getQualityLabels().size();i++){
                    nifty.getScreen(stage).findElementByName("RadioText"+Integer.toString(i)).setVisible(false);
                    nifty.getScreen(stage).findElementByName("Radio"+Integer.toString(i)).setVisible(false);
                }
                for(int i = 0; i<limit;i++){
                    nifty.getScreen(stage).findElementByName("CheckText"+Integer.toString(i)).setVisible(false);
                    nifty.getScreen(stage).findElementByName("CheckBox"+Integer.toString(i)).setVisible(false);
                }
                nifty.getScreen(stage).findElementByName("panel_basic").getRenderer(PanelRenderer.class).setBackgroundColor(new Color("#00000000"));
                nifty.getScreen(stage).findElementByName("panel_advanced").getRenderer(PanelRenderer.class).setBackgroundColor(new Color("#808080AA"));
        }    
        if(tabSelected.equals("advanced")){
            int limit = control.getNumCameras();
            nifty.getScreen(stage).findElementByName("qualityText").setVisible(true);
            nifty.getScreen(stage).findElementByName("checkBoxText").setVisible(true);
            for(int i = 0; i<control.getQualityLabels().size();i++){
                    nifty.getScreen(stage).findElementByName("RadioText"+Integer.toString(i)).setVisible(true);
                    nifty.getScreen(stage).findElementByName("Radio"+Integer.toString(i)).setVisible(true);
            }
            for(int i = 0; i<limit;i++){
                nifty.getScreen(stage).findElementByName("CheckText"+Integer.toString(i)).setVisible(true);
                nifty.getScreen(stage).findElementByName("CheckBox"+Integer.toString(i)).setVisible(true);
            }
            nifty.getScreen(stage).findElementByName("panel_basic").getRenderer(PanelRenderer.class).setBackgroundColor(new Color("#808080AA"));
            nifty.getScreen(stage).findElementByName("panel_advanced").getRenderer(PanelRenderer.class).setBackgroundColor(new Color("#00000000"));
        }
        //for(int i=;i<;i++){ //Checkboxsobrantes ocultarlos

    }
    
}
