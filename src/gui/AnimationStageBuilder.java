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
import de.lessvoid.nifty.controls.DropDown;
import de.lessvoid.nifty.controls.checkbox.builder.CheckboxBuilder;
import de.lessvoid.nifty.controls.dropdown.builder.DropDownBuilder;
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
    
    public AnimationStageBuilder(Nifty nifty, Control control, I18N i18nGui, I18N i18nFamily){
        stage = StageType.animationStage.toString();
        this.nifty = nifty;
        this.control = control;
        this.i18nGui = i18nGui;
        this.i18nFamily = i18nFamily;
        init();
    }
    
    private void init(){
        int limit = 0;
        //if(fc.getNumCameras()<)
        limit = control.getNumCameras();
        for(int i = 0; i<limit;i++){
            final int j = i;
            new PanelBuilder() {{
                width("40%");
                childLayoutHorizontal();
                text(new TextBuilder(){{
                    valignCenter();
                    color(Color.WHITE);
                    font("Interface/Fonts/Default.fnt");
                    text(i18nFamily.getString(control.getCamerasLabels().get(j)));
                    width("80%");
                    wrap(true);
                }});
                control(new CheckboxBuilder("CheckBox"+Integer.toString(j)) {{
                    checked(false);
                }});
            }}.build(nifty, nifty.getScreen(stage), nifty.getScreen(stage).findElementByName("checkBoxPanel"));
        }
        new DropDownBuilder("qualityDropDown") {{
                valignCenter();
                //alignRight();
                width("100");
        }}.build(nifty, nifty.getScreen(stage), nifty.getScreen(stage).findElementByName("qualityPanel"));
        ArrayList<String> q = control.getQualityLabels();
        Iterator<String> it = q.iterator();
        DropDown quality = nifty.getScreen(stage).findNiftyControl("qualityDropDown", DropDown.class);
        /*while(it.hasNext()){
            quality.addItem(i18nFamily.getString(it.next()));
        }*/
        nifty.getScreen(stage).findElementByName("previewText").getRenderer(TextRenderer.class).setText(i18nGui.getString("idPreview"));
        nifty.getScreen(stage).findElementByName("qualityText").getRenderer(TextRenderer.class).setText(i18nGui.getString("idQuality"));
        nifty.getScreen(stage).findElementByName("checkBoxText").getRenderer(TextRenderer.class).setText(i18nGui.getString("idCamera"));
        
    }
    
}
