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


public class SingleStageBuilder {
    private static final int TEXTURES_PAGE = 6;
    private Nifty nifty;
    private I18N i18nGui; 
    private Control control;
    private String stageType;
    
    public SingleStageBuilder(Nifty nifty, Control control, I18N i18nGui, ArrayList<String> stages){
        stageType = StageType.singleStage.toString();
        this.nifty = nifty;
        this.control = control;
        this.i18nGui = i18nGui;
        initIcons(stages);
    }
    
    /*receives as input a list of all the stages of the selected family 
      and builds all them of that are singlestage*/
    
    private void initIcons(ArrayList<String> stages){
        for(int j = 0; j < stages.size(); j++){
            if(control.getStagesTypes(stages.get(j)).toString().equals(stageType)){
                ArrayList<String> idSubStages = control.getIdsSubStages(stages.get(j));
                ArrayList<String> idsTexturesOrSubMeshes = control.getIdsTexturesORSubMeshes(idSubStages.get(0));
                for(int i=0; i<control.getNumTexturesORSubMeshes(idSubStages.get(0)); i++){
                    ImageBuilder image = new ImageBuilder(){{
                        width("0%");
                        height("0%");
                    }};
                    image.id(stages.get(j)+"i"+Integer.toString(i));
                    image.filename(control.getIconPathTexturesORSubMeshes(idsTexturesOrSubMeshes.get(i)));
                    image.interactOnClick("changeTextureOrSubMesh("+idsTexturesOrSubMeshes.get(i)+")");
                    image.build(nifty, nifty.getScreen(stageType), nifty.getScreen(stageType).findElementByName("t"+Integer.toString(i%TEXTURES_PAGE)));
                }
                nifty.getScreen(stageType).findElementByName("colorText").getRenderer(TextRenderer.class).setText(i18nGui.getString("idColor"));
                nifty.getScreen(stageType).findElementByName("panel_color").layoutElements(); 
            }         
        }
    }
    
    //hide the pictures from previous page of the selection stage
    
    public void hideTexturePage(String selection, int page){
        ArrayList<String> idSubStages = control.getIdsSubStages(selection);
        for(int i=page*TEXTURES_PAGE; i<control.getNumTexturesORSubMeshes(idSubStages.get(0)); i++){
            if(i<((page+1)*TEXTURES_PAGE)){
                nifty.getScreen(stageType).findElementByName(selection+"i"+Integer.toString(i)).setVisible(false);
                nifty.getScreen(stageType).findElementByName(selection+"i"+Integer.toString(i)).setHeight(0);
                nifty.getScreen(stageType).findElementByName(selection+"i"+Integer.toString(i)).setWidth(0);
            }
        }
    }
    
    //displays the current page images of the selection stage
    
    public void showTexturePage(String selection, int page){
        ArrayList<String> idSubStages = control.getIdsSubStages(selection);
        for(int i=page*TEXTURES_PAGE; i<control.getNumTexturesORSubMeshes(idSubStages.get(0)); i++){
            if(i<((page+1)*TEXTURES_PAGE)){
                nifty.getScreen(stageType).findElementByName(selection+"i"+Integer.toString(i)).setVisible(true);
                nifty.getScreen(stageType).findElementByName(selection+"i"+Integer.toString(i)).setHeight(nifty.getScreen(stageType).findElementByName("t"+Integer.toString(i%TEXTURES_PAGE)).getHeight()-5);
                nifty.getScreen(stageType).findElementByName(selection+"i"+Integer.toString(i)).setWidth(nifty.getScreen(stageType).findElementByName("t"+Integer.toString(i%TEXTURES_PAGE)).getWidth()-5);
            }
        }
        if(page > 0){
            nifty.getScreen(stageType).findElementByName("leftT").enable();
            nifty.getScreen(stageType).findElementByName("leftT").setVisible(true);
        }
        else{
            nifty.getScreen(stageType).findElementByName("leftT").disable();
            nifty.getScreen(stageType).findElementByName("leftT").setVisible(false);
        }
        if((((double)control.getNumTexturesORSubMeshes(idSubStages.get(0))/(double)TEXTURES_PAGE) - page) > 1){
            nifty.getScreen(stageType).findElementByName("rightT").enable();
            nifty.getScreen(stageType).findElementByName("rightT").setVisible(true);
        }
        else{
            nifty.getScreen(stageType).findElementByName("rightT").disable();
            nifty.getScreen(stageType).findElementByName("rightT").setVisible(false);
        }
    }
}
