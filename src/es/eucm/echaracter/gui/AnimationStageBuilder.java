/********************************************************************************
 * <eCharacter> is a research project of the <e-UCM>
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
 *      <eCharacter> is free software: you can 
 *      redistribute it and/or modify it under the terms of the GNU Lesser 
 *      General Public License as published by the Free Software Foundation, 
 *      either version 3 of the License, or (at your option) any later version.
 *  
 *      <eCharacter> is distributed in the hope that it 
 *      will be useful, but WITHOUT ANY WARRANTY; without even the implied 
 *      warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  
 *      See the GNU Lesser General Public License for more details.
 *  
 *      You should have received a copy of the GNU Lesser General Public License
 *      along with <eCharacter>. If not, 
 *      see <http://www.gnu.org/licenses/>.
 ******************************************************************************/

package es.eucm.echaracter.gui;

import de.lessvoid.nifty.Nifty;
import de.lessvoid.nifty.controls.Button;
import de.lessvoid.nifty.controls.CheckBox;
import de.lessvoid.nifty.elements.render.ImageRenderer;
import de.lessvoid.nifty.elements.render.PanelRenderer;
import de.lessvoid.nifty.elements.render.TextRenderer;
import de.lessvoid.nifty.tools.Color;
import es.eucm.echaracter.control.Control;
import es.eucm.echaracter.i18n.I18N;
import es.eucm.echaracter.types.StageType;
import java.util.ArrayList;

public class AnimationStageBuilder {
    private static final int CHECKBOX_PAGE = 2;
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
            labels = control.getAnimationNames();
            if(labels == null){
                size = 0;
            }
            else{
                size = control.getNumAnimations();
            }
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
                if(labels == null){
                    size = 0;
                }
                else{
                    size = control.getNumQualities();
                }
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
                    if(labels == null){
                        size = 0;
                    }
                    else{
                        size = control.getNumCameras();
                    }
                }
            }    
        }
        for(int i=page*CHECKBOX_PAGE; i<size; i++){
            if(i<((page+1)*CHECKBOX_PAGE)){
                nifty.getScreen(stageType).findElementByName(selection+Integer.toString(i%CHECKBOX_PAGE)).setVisible(true);
                if(selection.equals("a")){
                    nifty.getScreen(stageType).findElementByName(selection+"Text"+Integer.toString(i%CHECKBOX_PAGE)).getRenderer(TextRenderer.class).setText(labels.get(i));
                    if(control.isCheckedAnimation(labels.get(i))){
                        nifty.getScreen(stageType).findNiftyControl("aCheckBox"+Integer.toString(i%CHECKBOX_PAGE), CheckBox.class).setChecked(true);
                    }
                    else{
                        nifty.getScreen(stageType).findNiftyControl("aCheckBox"+Integer.toString(i%CHECKBOX_PAGE), CheckBox.class).setChecked(false);
                    }
                }
                else{
                    nifty.getScreen(stageType).findElementByName(selection+"Text"+Integer.toString(i%CHECKBOX_PAGE)).getRenderer(TextRenderer.class).setText(i18nFamily.getString(labels.get(i)));
                    if(selection.equals("q")){
                        if(control.isCheckedQuality(labels.get(i))){
                            nifty.getScreen(stageType).findNiftyControl("qCheckBox"+Integer.toString(i%CHECKBOX_PAGE), CheckBox.class).setChecked(true);
                        }
                        else{
                            nifty.getScreen(stageType).findNiftyControl("qCheckBox"+Integer.toString(i%CHECKBOX_PAGE), CheckBox.class).setChecked(false);
                        }
                    }
                    else{
                        if(selection.equals("c")){
                            if(control.isCheckedCamera(labels.get(i))){
                                nifty.getScreen(stageType).findNiftyControl("cCheckBox"+Integer.toString(i%CHECKBOX_PAGE), CheckBox.class).setChecked(true);
                            }
                            else{
                                nifty.getScreen(stageType).findNiftyControl("cCheckBox"+Integer.toString(i%CHECKBOX_PAGE), CheckBox.class).setChecked(false);
                            }
                        }
                    }
                }
                nifty.getScreen(stageType).findElementByName(selection+Integer.toString(i%CHECKBOX_PAGE)).layoutElements();
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
            showPage("a","0");
        }    
        if(tabSelected.equals("advanced")){
            nifty.getScreen(stageType).findElementByName("Panel2").setVisible(true);
            nifty.getScreen(stageType).findElementByName("Panel3").setVisible(true);
            nifty.getScreen(stageType).findElementByName("basicPanel").getRenderer(PanelRenderer.class).setBackgroundColor(new Color("#808080AA"));
            nifty.getScreen(stageType).findElementByName("advancedPanel").getRenderer(PanelRenderer.class).setBackgroundColor(new Color("#00000000"));
            showPage("a","0");
            showPage("q","0");
            showPage("c","0");
        }
        String types[] = {"a","q","c"};
        for(String type : types){
            nifty.getScreen(stageType).findElementByName(type+"AllButtonText").getRenderer(TextRenderer.class).setText(i18nGui.getString("idAll"));
            nifty.getScreen(stageType).findElementByName(type+"AllButton").layoutElements();
            nifty.getScreen(stageType).findElementByName(type+"NoButtonText").getRenderer(TextRenderer.class).setText(i18nGui.getString("idNothing"));
            nifty.getScreen(stageType).findElementByName(type+"NoButton").layoutElements();
        }
        for(int i = 0; i<CHECKBOX_PAGE; i++){
            nifty.getScreen(stageType).findElementByName("aButton"+i+"Text").getRenderer(TextRenderer.class).setText(i18nGui.getString("idPreview"));
            nifty.getScreen(stageType).findElementByName("aButton"+i).layoutElements();
            nifty.getScreen(stageType).findElementByName("cButton"+i+"Text").getRenderer(TextRenderer.class).setText(i18nGui.getString("idPreview"));
            nifty.getScreen(stageType).findElementByName("cButton"+i).layoutElements();
        }
    }
    
    public void showAnimation(String animation){
        String a = control.getAnimationNames().get(Integer.valueOf(animation) + animationPage*CHECKBOX_PAGE);
        control.setAnimations(a);
    }
    
    public void showCamera(String camera){
        String idCamera = control.getCamerasLabels().get(Integer.valueOf(camera) + cameraPage*CHECKBOX_PAGE);
        control.previewCamera(idCamera);
    }
    
    public void checkOrUncheck(String selection, int pos, boolean checkedState){
        if(selection.equals("a")){
            control.clickAnimation(control.getAnimationNames().get(pos+animationPage*CHECKBOX_PAGE), checkedState);
        }
        else{
            if(selection.equals("q")){
                control.clickQuality(control.getQualityLabels().get(pos+qualityPage*CHECKBOX_PAGE), checkedState);
            }
            else{
                if(selection.equals("c")){
                    control.clickCamera(control.getCamerasLabels().get(pos+cameraPage*CHECKBOX_PAGE), checkedState);
                }
            }
        }
    }
    
    public void checkAll(String id, boolean b){
        
        if(id.equals("a")){
            control.clickAllAnimations(b);
        }
        else{
            if(id.equals("q")){
                control.clickAllQualities(b);
            }
            else{
                if(id.equals("c")){
                    control.clickAllCameras(b);
                }
            }
        }
        for(int i=0;i<CHECKBOX_PAGE;i++){
            if(nifty.getScreen(stageType).findElementByName(id+i).isVisible()){
                nifty.getScreen(stageType).findNiftyControl(id+"CheckBox"+i, CheckBox.class).setChecked(b);
            }
        }
    }
}
