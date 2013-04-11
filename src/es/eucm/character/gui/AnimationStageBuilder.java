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
import de.lessvoid.nifty.controls.button.builder.ButtonBuilder;
import de.lessvoid.nifty.controls.checkbox.builder.CheckboxBuilder;
import de.lessvoid.nifty.controls.radiobutton.builder.RadioButtonBuilder;
import de.lessvoid.nifty.elements.render.PanelRenderer;
import de.lessvoid.nifty.elements.render.TextRenderer;
import de.lessvoid.nifty.tools.Color;
import es.eucm.character.i18n.I18N;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Set;
import es.eucm.character.types.StageType;

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
        nifty.getScreen(stage).findElementByName("basicText").getRenderer(TextRenderer.class).setText(i18nGui.getString("idBasic"));
        nifty.getScreen(stage).findElementByName("basicPanel").layoutElements();
        nifty.getScreen(stage).findElementByName("customText").getRenderer(TextRenderer.class).setText(i18nGui.getString("idCustom"));
        nifty.getScreen(stage).findElementByName("advancedPanel").layoutElements();
        init();
    }
    
    private void init(){
      
        nifty.getScreen(stage).findElementByName("previewText").getRenderer(TextRenderer.class).setText(i18nGui.getString("idAnimations"));
        nifty.getScreen(stage).findElementByName("qualityText").getRenderer(TextRenderer.class).setText(i18nGui.getString("idQuality"));
        nifty.getScreen(stage).findElementByName("qualityPanel").layoutElements();
        nifty.getScreen(stage).findElementByName("checkBoxText").getRenderer(TextRenderer.class).setText(i18nGui.getString("idCamera"));
        Set<String> animations = control.getAnimationNames();
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
            nifty.getScreen(stage).findElementByName("qualityText").setVisible(false);
            nifty.getScreen(stage).findElementByName("checkBoxText").setVisible(false);
            nifty.getScreen(stage).findElementByName("previewPanel").setVisible(true);
            nifty.getScreen(stage).findElementByName("Panel2").setVisible(false);
            nifty.getScreen(stage).findElementByName("Panel3").setVisible(false);
            nifty.getScreen(stage).findElementByName("basicPanel").getRenderer(PanelRenderer.class).setBackgroundColor(new Color("#00000000"));
            nifty.getScreen(stage).findElementByName("advancedPanel").getRenderer(PanelRenderer.class).setBackgroundColor(new Color("#808080AA"));
        }    
        if(tabSelected.equals("advanced")){
            nifty.getScreen(stage).findElementByName("qualityText").setVisible(true);
            nifty.getScreen(stage).findElementByName("checkBoxText").setVisible(true);
            nifty.getScreen(stage).findElementByName("previewPanel").setVisible(true);
            nifty.getScreen(stage).findElementByName("Panel2").setVisible(true);
            nifty.getScreen(stage).findElementByName("Panel3").setVisible(true);
            nifty.getScreen(stage).findElementByName("basicPanel").getRenderer(PanelRenderer.class).setBackgroundColor(new Color("#808080AA"));
            nifty.getScreen(stage).findElementByName("advancedPanel").getRenderer(PanelRenderer.class).setBackgroundColor(new Color("#00000000"));
        }
    }
    
}
