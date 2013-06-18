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
import de.lessvoid.nifty.controls.Slider;
import de.lessvoid.nifty.elements.render.PanelRenderer;
import de.lessvoid.nifty.elements.render.TextRenderer;
import de.lessvoid.nifty.tools.Color;
import es.eucm.character.control.Control;
import es.eucm.character.i18n.I18N;
import es.eucm.character.types.StageType;
import java.util.ArrayList;

public class ScaleStageBuilder {
    private static final int SCALE_PAGE = 5;
    private Nifty nifty;
    private I18N i18nModel, i18nFamily;
    private String tabSelected, stage;// selection;
    private ArrayList<String> idBones, idPhysicalBuild;
    private Control control;
    
    public ScaleStageBuilder(Nifty nifty, Control control, I18N i18nFamily, I18N i18nModel, I18N i18nGui){
        stage = StageType.scaleStage.toString();
        this.i18nFamily = i18nFamily;
        this.i18nModel = i18nModel;
        this.nifty = nifty;
        this.control = control;
        nifty.getScreen(stage).findElementByName("basicText").getRenderer(TextRenderer.class).setText(i18nGui.getString("idBasic"));
        nifty.getScreen(stage).findElementByName("basicPanel").layoutElements();
        nifty.getScreen(stage).findElementByName("customText").getRenderer(TextRenderer.class).setText(i18nGui.getString("idCustom"));
        nifty.getScreen(stage).findElementByName("advancedPanel").layoutElements();
    }
    
    //Change the bone's current page
    
    public void changeScalePage(int page, String selection){
            int bonesSize = 0;
            if(tabSelected.equals("basic")){
                ArrayList<String> idPanel = control.getIdsSubStages(selection);
                idPhysicalBuild = control.getIdsPhysicalBuild(idPanel.get(0));
                bonesSize = idPhysicalBuild.size();
                for(int i=page*SCALE_PAGE; i<bonesSize; i++){
                    if(i<((page+1)*SCALE_PAGE)){
                       nifty.getScreen(stage).findElementByName("textb"+Integer.toString(i%SCALE_PAGE)).getRenderer(TextRenderer.class).setText(i18nModel.getString(control.getPhysicalBuildLabel(idPhysicalBuild.get(i))));
                       nifty.getScreen(stage).findElementByName("b"+Integer.toString(i%SCALE_PAGE)).layoutElements();
                       nifty.getScreen(stage).findElementByName("b"+Integer.toString(i%SCALE_PAGE)).enable();
                       nifty.getScreen(stage).findElementByName("b"+Integer.toString(i%SCALE_PAGE)).setVisible(true);
                       nifty.getScreen(stage).findElementByName("a"+Integer.toString(i%SCALE_PAGE)).setVisible(false);
                    }
                }
                //nifty.getScreen(stage).findElementByName("basicPanel").getRenderer(PanelRenderer.class).setBackgroundColor(new Color("#00000000"));
                //nifty.getScreen(stage).findElementByName("advancedPanel").getRenderer(PanelRenderer.class).setBackgroundColor(new Color("#808080AA"));
            }
            if(tabSelected.equals("advanced")){
                idBones = control.getIdBonesController(selection);
                bonesSize = idBones.size();
                for(int i=page*SCALE_PAGE; i<bonesSize; i++){
                    if(i<((page+1)*SCALE_PAGE)){
                       String text = i18nFamily.getString(control.getBoneControllerLabel(selection, idBones.get(i)));
                       int level = control.getBoneControllerLevel(selection, idBones.get(i));
                       nifty.getScreen(stage).findElementByName("texta"+Integer.toString(i%SCALE_PAGE)).getRenderer(TextRenderer.class).setText(indentLabel(text,level));
                       nifty.getScreen(stage).findElementByName("a"+Integer.toString(i%SCALE_PAGE)).layoutElements();
                       nifty.getScreen(stage).findElementByName("b"+Integer.toString(i%SCALE_PAGE)).disable();
                       nifty.getScreen(stage).findElementByName("b"+Integer.toString(i%SCALE_PAGE)).setVisible(false);
                       nifty.getScreen(stage).findElementByName("a"+Integer.toString(i%SCALE_PAGE)).setVisible(true);
                       nifty.getScreen(stage).findElementByName("slider"+Integer.toString(i%SCALE_PAGE)).setVisible(true);
                       Slider s = nifty.getScreen(stage).findNiftyControl("slider"+Integer.toString(i%SCALE_PAGE), Slider.class);
                       s.setup(control.getMinValueBoneController(idBones.get(i)), control.getMaxValueBoneController(idBones.get(i)), control.getDefaultValueBoneController(idBones.get(i)), 1, 1);
                    }
                }
                //nifty.getScreen(stage).findElementByName("basicPanel").getRenderer(PanelRenderer.class).setBackgroundColor(new Color("#808080AA"));
                //nifty.getScreen(stage).findElementByName("advancedPanel").getRenderer(PanelRenderer.class).setBackgroundColor(new Color("#00000000"));
            }
            for(int i=bonesSize;i<((page+1)*SCALE_PAGE);i++){
                nifty.getScreen(stage).findElementByName("b"+Integer.toString(i%SCALE_PAGE)).disable();
                nifty.getScreen(stage).findElementByName("b"+Integer.toString(i%SCALE_PAGE)).setVisible(false);
                nifty.getScreen(stage).findElementByName("a"+Integer.toString(i%SCALE_PAGE)).setVisible(false);
            }
            if(page > 0){
                nifty.getScreen(stage).findElementByName("leftT").enable();
                nifty.getScreen(stage).findElementByName("leftT").setVisible(true);
            }
            else{
                nifty.getScreen(stage).findElementByName("leftT").disable();
                nifty.getScreen(stage).findElementByName("leftT").setVisible(false);
            }
            if((((double)bonesSize/(double)SCALE_PAGE) - page) > 1){
                nifty.getScreen(stage).findElementByName("rightT").enable();
                nifty.getScreen(stage).findElementByName("rightT").setVisible(true);
            }
            else{
                nifty.getScreen(stage).findElementByName("rightT").disable();
                nifty.getScreen(stage).findElementByName("rightT").setVisible(false);
            }
    }
    
    //Switches between Basic or Advanced tab
    
    public void changeTab(String param, String selection){
        tabSelected = param;
        changeScalePage(0, selection);
    }
    
    private String indentLabel(String label, int level){
        for (int i=0; i<level; i++){
             label="   "+label;
        }
        return label;
    }
}
