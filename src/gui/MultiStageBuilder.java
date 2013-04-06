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
import de.lessvoid.nifty.elements.render.TextRenderer;
import i18n.I18N;
import java.util.ArrayList;
import types.StageType;


public class MultiStageBuilder {
    private static final int TEXTURES_PAGE = 2;
    private static final int SUBSTAGE_PAGE = 2;
    private Nifty nifty;
    private I18N i18nGui, i18nFamily; 
    private Control control;
    private String stage;
    private int multiPage[];
    
    public MultiStageBuilder(Nifty nifty, Control control, I18N i18nGui, I18N i18nFamily, ArrayList<String> stages){
        stage = StageType.multiStage.toString();
        this.nifty = nifty;
        this.control = control;
        this.i18nGui = i18nGui;
        this.i18nFamily = i18nFamily;
        multiPage = new int[SUBSTAGE_PAGE];
        for(int i=0; i<SUBSTAGE_PAGE;i++){
            multiPage[i]=0;
        }
        initIcons(stages);
    }
    private void initIcons(ArrayList<String> stages){
        for(int j = 0; j < stages.size(); j++){
            if(control.getStagesTypes(stages.get(j)).toString().equals(stage)){
                ArrayList<String> idSubStages = control.getIdsSubStages(stages.get(j));
                for(int i=0;i<control.getNumSubStages(stages.get(j));i++){
                    nifty.getScreen(stage).findElementByName("text"+Integer.toString(i%SUBSTAGE_PAGE)).getRenderer(TextRenderer.class).setText(i18nFamily.getString(idSubStages.get(i)));
                    ArrayList<String> idsTexturesOrSubMeshes = control.getIdsTexturesORSubMeshes(idSubStages.get(i));
                    for(int k=0; k<control.getNumTexturesORSubMeshes(idSubStages.get(i)); k++){
                        ImageBuilder image = new ImageBuilder(){{
                            width("0%");
                            height("0%");
                        }};
                        image.id(idSubStages.get(i)+"i"+Integer.toString(k));
                        image.filename(control.getIconPathTexturesORSubMeshes(idsTexturesOrSubMeshes.get(k)));
                        image.interactOnClick("changeTextureOrSubMesh("+idsTexturesOrSubMeshes.get(k)+")");
                        image.build(nifty, nifty.getScreen(stage), nifty.getScreen(stage).findElementByName("t"+Integer.toString(i%SUBSTAGE_PAGE)+Integer.toString(k%TEXTURES_PAGE)));
                    }
                    nifty.getScreen(stage).findElementByName("colorText"+Integer.toString(i%SUBSTAGE_PAGE)).getRenderer(TextRenderer.class).setText(i18nGui.getString("idColor"));
                    nifty.getScreen(stage).findElementByName("panel_color"+Integer.toString(i%SUBSTAGE_PAGE)).layoutElements();
                }
            }
        }  
    }
    
    public void hideSubTexturePage(int t,int page, String param){
        ArrayList<String> idSubStages = control.getIdsSubStages(param);
        if((page*SUBSTAGE_PAGE+t)<idSubStages.size()){
            for(int i=multiPage[t]*TEXTURES_PAGE; i<control.getNumTexturesORSubMeshes(idSubStages.get(page*SUBSTAGE_PAGE+t)); i++){
                if(i<((multiPage[t]+1)*TEXTURES_PAGE)){
                    nifty.getScreen(stage).findElementByName(idSubStages.get(page*SUBSTAGE_PAGE+t)+"i"+Integer.toString(i)).setVisible(false);
                    nifty.getScreen(stage).findElementByName(idSubStages.get(page*SUBSTAGE_PAGE+t)+"i"+Integer.toString(i)).setHeight(0);
                    nifty.getScreen(stage).findElementByName(idSubStages.get(page*SUBSTAGE_PAGE+t)+"i"+Integer.toString(i)).setWidth(0);
                }
            } 
        }
    }

    public void showSubTexturePage(String selection, int h, int page, String steep){
        changeMultiPage(h,steep);
        ArrayList<String> idSubStages = control.getIdsSubStages(selection);
        if((page*SUBSTAGE_PAGE+h)<idSubStages.size()){
            nifty.getScreen(stage).findElementByName("panel_color"+Integer.toString(h)).enable();
            nifty.getScreen(stage).findElementByName("panel_color"+Integer.toString(h)).setVisible(true);
            for(int i=multiPage[h]*TEXTURES_PAGE; i<control.getNumTexturesORSubMeshes(idSubStages.get(page*SUBSTAGE_PAGE+h)); i++){
                if(i<((multiPage[h]+1)*TEXTURES_PAGE)){
                    nifty.getScreen(stage).findElementByName("text"+Integer.toString(h)).setVisible(true);
                    nifty.getScreen(stage).findElementByName("text"+Integer.toString(h)).getRenderer(TextRenderer.class).setText(i18nFamily.getString(control.getSubStageLabel(selection,idSubStages.get(page*SUBSTAGE_PAGE+h))));
                    nifty.getScreen(stage).findElementByName(idSubStages.get(page*SUBSTAGE_PAGE+h)+"i"+Integer.toString(i)).setVisible(true);
                    nifty.getScreen(stage).findElementByName(idSubStages.get(page*SUBSTAGE_PAGE+h)+"i"+Integer.toString(i)).setHeight(nifty.getScreen(stage).findElementByName("t"+Integer.toString(h)+Integer.toString(i%TEXTURES_PAGE)).getHeight()-5);
                    nifty.getScreen(stage).findElementByName(idSubStages.get(page*SUBSTAGE_PAGE+h)+"i"+Integer.toString(i)).setWidth(nifty.getScreen(stage).findElementByName("t"+Integer.toString(h)+Integer.toString(i%TEXTURES_PAGE)).getWidth()-5);
                }
            }
            if(multiPage[h] > 0){
                nifty.getScreen(stage).findElementByName("leftT"+Integer.toString(h)).enable();
                nifty.getScreen(stage).findElementByName("leftT"+Integer.toString(h)).setVisible(true);
            }
            else{
                nifty.getScreen(stage).findElementByName("leftT"+Integer.toString(h)).disable();
                nifty.getScreen(stage).findElementByName("leftT"+Integer.toString(h)).setVisible(false);
            }
            if((((double)control.getNumTexturesORSubMeshes(idSubStages.get(page*SUBSTAGE_PAGE+h))/(double)TEXTURES_PAGE) - multiPage[h]) > 1){
                nifty.getScreen(stage).findElementByName("rightT"+Integer.toString(h)).enable();
                nifty.getScreen(stage).findElementByName("rightT"+Integer.toString(h)).setVisible(true);
            }
            else{
                nifty.getScreen(stage).findElementByName("rightT"+Integer.toString(h)).disable();
                nifty.getScreen(stage).findElementByName("rightT"+Integer.toString(h)).setVisible(false);
            }
        }else{
            nifty.getScreen(stage).findElementByName("leftT"+Integer.toString(h)).disable();
            nifty.getScreen(stage).findElementByName("leftT"+Integer.toString(h)).setVisible(false);
            nifty.getScreen(stage).findElementByName("rightT"+Integer.toString(h)).disable();
            nifty.getScreen(stage).findElementByName("rightT"+Integer.toString(h)).setVisible(false);
            nifty.getScreen(stage).findElementByName("panel_color"+Integer.toString(h)).disable();
            nifty.getScreen(stage).findElementByName("panel_color"+Integer.toString(h)).setVisible(false);
            nifty.getScreen(stage).findElementByName("text"+Integer.toString(h)).setVisible(false);
            
        }
    }
    
    public void showTexturePage(String selection, int page){
            showSubTexturePage(selection, 0, page, "0");
            showSubTexturePage(selection, 1, page, "0");
            
            if(page > 0){
                nifty.getScreen(stage).findElementByName("leftT").enable();
                nifty.getScreen(stage).findElementByName("leftT").setVisible(true);
            }
            else{
                nifty.getScreen(stage).findElementByName("leftT").disable();
                nifty.getScreen(stage).findElementByName("leftT").setVisible(false);
            }
            if((((double)control.getNumSubStages(selection) /(double)SUBSTAGE_PAGE) - page) > 1){
                nifty.getScreen(stage).findElementByName("rightT").enable();
                nifty.getScreen(stage).findElementByName("rightT").setVisible(true);
            }
            else{
                nifty.getScreen(stage).findElementByName("rightT").disable();
                nifty.getScreen(stage).findElementByName("rightT").setVisible(false);
            }
    }
    
    public void changeMultiPage(int t,String steep){
        if(steep.equals("+")){
            multiPage[t]++;
        }
        if(steep.equals("-")){
            multiPage[t]--;
        }
        if(steep.equals("0")){
            multiPage[t] = 0;
        }
    }
    
}
