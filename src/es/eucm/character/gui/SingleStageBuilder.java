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
import de.lessvoid.nifty.effects.Effect;
import de.lessvoid.nifty.effects.EffectEventId;
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
import java.util.HashMap;
import java.util.List;


public class SingleStageBuilder {
    private static final int TEXTURES_PAGE = 6;
    private Nifty nifty;
    private Control control;
    private String stageType;
    private String subStageSelected;
    private HashMap<String,String> seleccionado;
    private I18N i18nModel;
    
    public SingleStageBuilder(Nifty nifty, Control control, I18N i18nGui, I18N i18nModel){
        stageType = StageType.singleStage.toString();
        this.nifty = nifty;
        this.control = control;
        seleccionado = new HashMap<String,String>();
        subStageSelected = "";
        this.i18nModel = i18nModel;
        nifty.getScreen(stageType).findElementByName("colorText").getRenderer(TextRenderer.class).setText(i18nGui.getString("idColor"));
        nifty.getScreen(stageType).findElementByName("panel_color").layoutElements();
        nifty.getScreen(stageType).findElementByName("panel_color").setVisible(false);
    }
    
    //displays the current page images of the selection stage
    
    public void showTexturePage(String selection, int page){
        
        ArrayList<String> idSubStages = control.getIdsSubStages(selection);
        ArrayList<String> idsTexturesOrSubMeshes = control.getIdsTexturesORSubMeshes(idSubStages.get(0));
        unCheck();
        subStageSelected = "";
        nifty.getScreen(stageType).findElementByName("panel_color").setVisible(false);
        for(int i=page*TEXTURES_PAGE; i<control.getNumTexturesORSubMeshes(idSubStages.get(0)); i++){
            if(i<((page+1)*TEXTURES_PAGE)){
                Element image = nifty.getScreen(stageType).findElementByName("i"+Integer.toString(i%TEXTURES_PAGE));
                List<Effect> effects = image.getEffects(EffectEventId.onHover,Tooltip.class);
                effects.get(0).getParameters().setProperty("hintText", i18nModel.getString(control.getTextTexturesORSubMeshes(idsTexturesOrSubMeshes.get(i))));
                image.setVisible(true);
                ImageRenderer imager = image.getRenderer(ImageRenderer.class);
                String imagePath = control.getIconPathTexturesORSubMeshes(idsTexturesOrSubMeshes.get(i));
                if(imagePath!=null){
                    imager.setImage(nifty.getRenderEngine().createImage(imagePath, false));
                }
                else{
                    imager.setImage(nifty.getRenderEngine().createImage("assets/Interface/x.png", false));
                }
                if (control.isChecked(idSubStages.get(0), idsTexturesOrSubMeshes.get(i))){
                    nifty.getScreen(stageType).findElementByName("t"+Integer.toString(i%TEXTURES_PAGE)).getRenderer(PanelRenderer.class).setBackgroundColor(new Color("#FF0000AA"));
                    subStageSelected = idSubStages.get(0);
                    if(!seleccionado.containsKey(subStageSelected)){
                        seleccionado.put(subStageSelected, idsTexturesOrSubMeshes.get(i));
                    }
                }
            }
        }
        if(seleccionado.containsKey(subStageSelected)){
            if(!(control.getTextureType(seleccionado.get(subStageSelected)) == TexturesMeshType.simpleTexture)){
                nifty.getScreen(stageType).findElementByName("panel_color").setVisible(true);
            }
            else{
                nifty.getScreen(stageType).findElementByName("panel_color").setVisible(false);
            }
        }
        else{
            nifty.getScreen(stageType).findElementByName("panel_color").setVisible(false);
        }
        for(int i=control.getNumTexturesORSubMeshes(idSubStages.get(0));i<((page+1)*TEXTURES_PAGE);i++){
            Element image = nifty.getScreen(stageType).findElementByName("i"+Integer.toString(i%TEXTURES_PAGE));
            image.setVisible(false);
        }
        if(page > 0){
            nifty.getScreen(stageType).findElementByName("leftT").setVisible(true);
        }
        else{
            nifty.getScreen(stageType).findElementByName("leftT").setVisible(false);
        }
        if((((double)control.getNumTexturesORSubMeshes(idSubStages.get(0))/(double)TEXTURES_PAGE) - page) > 1){
            nifty.getScreen(stageType).findElementByName("rightT").setVisible(true);
        }
        else{
            nifty.getScreen(stageType).findElementByName("rightT").setVisible(false);
        }
    }
    
    public void changeTextureOrSubMesh(String selection,int page, String h, String i){
        int j = Integer.valueOf(h);
        unCheck();
        ArrayList<String> idSubStages = control.getIdsSubStages(selection);
        ArrayList<String> idsTexturesOrSubMeshes = control.getIdsTexturesORSubMeshes(idSubStages.get(j));
        subStageSelected = idSubStages.get(j);
        control.changeTextureOrSubMesh(subStageSelected, idsTexturesOrSubMeshes.get(page*TEXTURES_PAGE+Integer.valueOf(i)));
        if(seleccionado.containsValue(idsTexturesOrSubMeshes.get(page*TEXTURES_PAGE+Integer.valueOf(i)))){
            seleccionado.remove(subStageSelected);
        }
        else{
            if(control.isChecked(subStageSelected, idsTexturesOrSubMeshes.get(page*TEXTURES_PAGE+Integer.valueOf(i)))){
                seleccionado.put(subStageSelected, idsTexturesOrSubMeshes.get(page*TEXTURES_PAGE+Integer.valueOf(i)));
            }
        }
        checkIn(page);
    }
    
    public String getTextureOrSubMesh(){
        return seleccionado.get(subStageSelected);
    }
    public String getSubStage(){
        return subStageSelected;
    }
    
    public String getSubStage(String selection,int page, String h){
        int j = Integer.valueOf(h);
        ArrayList<String> idSubStages = control.getIdsSubStages(selection);
        subStageSelected = idSubStages.get(j);
        return subStageSelected;
    }
    
    //uncheck all textures
    
    private void unCheck(){
        for(int i = 0; i < TEXTURES_PAGE; i++){
            nifty.getScreen(stageType).findElementByName("t"+Integer.toString(i)).getRenderer(PanelRenderer.class).setBackgroundColor(new Color("#00000000"));
        }
        nifty.getScreen(stageType).findElementByName("panel_color").setVisible(false);
    }
    
    public void checkIn(int page){
        unCheck();
        ArrayList<String> idsTexturesOrSubMeshes = control.getIdsTexturesORSubMeshes(subStageSelected);
        for(int i=page*TEXTURES_PAGE; i<control.getNumTexturesORSubMeshes(subStageSelected); i++){
            if(i<((page+1)*TEXTURES_PAGE)){
                if (control.isChecked(subStageSelected, idsTexturesOrSubMeshes.get(i))){
                     nifty.getScreen(stageType).findElementByName("t"+Integer.toString(i%TEXTURES_PAGE)).getRenderer(PanelRenderer.class).setBackgroundColor(new Color("#FF0000AA"));
                     if(!seleccionado.containsKey(subStageSelected)){
                        seleccionado.put(subStageSelected, idsTexturesOrSubMeshes.get(i));
                     }
                }
            }
        }
        if(seleccionado.containsKey(subStageSelected)){
            TexturesMeshType texturesMeshType = control.getTextureType(seleccionado.get(subStageSelected));

            if (texturesMeshType == null){
                nifty.getScreen(stageType).findElementByName("panel_color").setVisible(false);
            }
            else{
                if(!(control.getTextureType(seleccionado.get(subStageSelected)) == TexturesMeshType.simpleTexture)){
                    nifty.getScreen(stageType).findElementByName("panel_color").setVisible(true);
                }
                else{
                    nifty.getScreen(stageType).findElementByName("panel_color").setVisible(false);
                }
            }
        }
        else{
            nifty.getScreen(stageType).findElementByName("panel_color").setVisible(false);
        }
    }
}
