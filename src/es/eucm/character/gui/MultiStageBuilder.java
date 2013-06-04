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


public class MultiStageBuilder {
    private static final int TEXTURES_PAGE = 2;
    private static final int SUBSTAGE_PAGE = 2;
    private Nifty nifty;
    private I18N i18nFamily, i18nModel; 
    private Control control;
    private String stageType, subStageSelected[];
    private int multiPage[];
    private HashMap<String,String> seleccionado;
    
    public MultiStageBuilder(Nifty nifty, Control control, I18N i18nGui, I18N i18nFamily, I18N i18nModel){
        stageType = StageType.multiStage.toString();
        this.nifty = nifty;
        this.control = control;
        this.i18nFamily = i18nFamily;
        this.i18nModel = i18nModel;
        subStageSelected = new String[SUBSTAGE_PAGE];
        seleccionado = new HashMap<String,String>();
        multiPage = new int[SUBSTAGE_PAGE];
        for(int i=0; i<SUBSTAGE_PAGE;i++){
            nifty.getScreen(stageType).findElementByName("colorText"+Integer.toString(i)).getRenderer(TextRenderer.class).setText(i18nGui.getString("idColor"));
            nifty.getScreen(stageType).findElementByName("panel_color"+Integer.toString(i)).layoutElements();
            multiPage[i]=0;
            subStageSelected[i]=""; 
        }
    }

    //displays the current subpage images of the selection stage
    
    public void showSubTexturePage(String selection, int h, int page, String steep){
        ArrayList<String> idSubStages = control.getIdsSubStages(selection);
        if((page*SUBSTAGE_PAGE+h)<idSubStages.size()){
            unCheck(h);
            subStageSelected[h]=""; 
            nifty.getScreen(stageType).findElementByName("t"+Integer.toString(h)).setVisible(true);
            nifty.getScreen(stageType).findElementByName("panel_color"+Integer.toString(h)).setVisible(false);
            changeMultiPage(h,steep);
            nifty.getScreen(stageType).findElementByName("text"+Integer.toString(h)).getRenderer(TextRenderer.class).setText(i18nFamily.getString(control.getSubStageLabel(selection,idSubStages.get(page*SUBSTAGE_PAGE+h))));
            nifty.getScreen(stageType).findElementByName("textPanel"+Integer.toString(h)).layoutElements();
            ArrayList<String> idsTexturesOrSubMeshes = control.getIdsTexturesORSubMeshes(idSubStages.get(h+page*TEXTURES_PAGE));
            for(int i=multiPage[h]*TEXTURES_PAGE; i<control.getNumTexturesORSubMeshes(idSubStages.get(page*SUBSTAGE_PAGE+h)); i++){
                if(i<((multiPage[h]+1)*TEXTURES_PAGE)){
                    Element image = nifty.getScreen(stageType).findElementByName("i"+Integer.toString(h)+Integer.toString(i%TEXTURES_PAGE));
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
                    if (control.isChecked(idSubStages.get(h+page*TEXTURES_PAGE), idsTexturesOrSubMeshes.get(i))){
                        nifty.getScreen(stageType).findElementByName("t"+Integer.toString(h)+Integer.toString(i%TEXTURES_PAGE)).getRenderer(PanelRenderer.class).setBackgroundColor(new Color("#FF0000AA"));
                        subStageSelected[h] = idSubStages.get(h+page*TEXTURES_PAGE);
                        if(!seleccionado.containsKey(subStageSelected[h])){
                            seleccionado.put(subStageSelected[h], idsTexturesOrSubMeshes.get(i));
                        }
                    }
                }
            }
            if(seleccionado.containsKey(subStageSelected[h])){
                if(!(control.getTextureType(seleccionado.get(subStageSelected[h])) == TexturesMeshType.simpleTexture)){
                    nifty.getScreen(stageType).findElementByName("panel_color"+Integer.toString(h)).setVisible(true);
                }
                else{
                    nifty.getScreen(stageType).findElementByName("panel_color"+Integer.toString(h)).setVisible(false);
                }
            }
            else{
                nifty.getScreen(stageType).findElementByName("panel_color"+Integer.toString(h)).setVisible(false);
            }
            
            for(int i=control.getNumTexturesORSubMeshes(idSubStages.get(page*SUBSTAGE_PAGE+h));i<((multiPage[h]+1)*TEXTURES_PAGE);i++){
                Element image = nifty.getScreen(stageType).findElementByName("i"+Integer.toString(h)+Integer.toString(i%TEXTURES_PAGE));
                image.setVisible(false);
            }
            if(multiPage[h] > 0){
                nifty.getScreen(stageType).findElementByName("leftT"+Integer.toString(h)).setVisible(true);
            }
            else{
                nifty.getScreen(stageType).findElementByName("leftT"+Integer.toString(h)).setVisible(false);
            }
            if(((((double)control.getNumTexturesORSubMeshes(idSubStages.get(page*SUBSTAGE_PAGE+h)))/((double)TEXTURES_PAGE)) - multiPage[h]) > 1){
                nifty.getScreen(stageType).findElementByName("rightT"+Integer.toString(h)).setVisible(true);
            }
            else{
                nifty.getScreen(stageType).findElementByName("rightT"+Integer.toString(h)).setVisible(false);
            }
        }
        else{
            nifty.getScreen(stageType).findElementByName("t"+Integer.toString(h)).setVisible(false);
            
        }
    }
    
    //displays the current page images of the selection stage
    
    public void showTexturePage(String selection, int page){
            showSubTexturePage(selection, 0, page, "0");
            showSubTexturePage(selection, 1, page, "0");
            
            if(page > 0){
                nifty.getScreen(stageType).findElementByName("upT").setVisible(true);
            }
            else{
                nifty.getScreen(stageType).findElementByName("upT").setVisible(false);
            }
            if((((double)control.getNumSubStages(selection) /(double)SUBSTAGE_PAGE) - page) > 1){
           //if((control.getNumSubStages(selection) - page) > SUBSTAGE_PAGE){
                nifty.getScreen(stageType).findElementByName("downT").setVisible(true);
            }
            else{
                nifty.getScreen(stageType).findElementByName("downT").setVisible(false);
            }
    }
    
    //Change teh current subpage
    
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
    
    public void changeTextureOrSubMesh(String selection,int page, String h, String i){
        int j = Integer.valueOf(h);
        unCheck(j);
        ArrayList<String> idSubStages = control.getIdsSubStages(selection);
        ArrayList<String> idsTexturesOrSubMeshes = control.getIdsTexturesORSubMeshes(idSubStages.get(j+page*TEXTURES_PAGE));
        subStageSelected[j] = idSubStages.get(j+page*TEXTURES_PAGE);
        control.changeTextureOrSubMesh(subStageSelected[j], idsTexturesOrSubMeshes.get(multiPage[j]*TEXTURES_PAGE+Integer.valueOf(i)));
        if(seleccionado.containsValue(idsTexturesOrSubMeshes.get(multiPage[j]*TEXTURES_PAGE+Integer.valueOf(i)))){
            seleccionado.remove(subStageSelected[j]);
        }
        else{
            if(control.isChecked(subStageSelected[j], idsTexturesOrSubMeshes.get(multiPage[j]*TEXTURES_PAGE+Integer.valueOf(i)))){
                seleccionado.put(subStageSelected[j], idsTexturesOrSubMeshes.get(multiPage[j]*TEXTURES_PAGE+Integer.valueOf(i)));
            }
        }
        checkIn(j);
    }
    
    public String getTextureOrSubMesh(String h){
        return seleccionado.get(subStageSelected[Integer.valueOf(h)]);//textureOrSubMeshSelected[Integer.valueOf(h)];
    }
    public String getSubStage(String h){
        return subStageSelected[Integer.valueOf(h)];
    }
    
    public String getSubStage(String selection,int page, String h){
        int j = Integer.valueOf(h);
        ArrayList<String> idSubStages = control.getIdsSubStages(selection);
        subStageSelected[j] = idSubStages.get(j+page*TEXTURES_PAGE);
        return subStageSelected[j];
    }
    
    //uncheck all textures
    
    private void unCheck(int h){ 
        for(int i = 0; i < TEXTURES_PAGE; i++){
            nifty.getScreen(stageType).findElementByName("t"+Integer.toString(h)+Integer.toString(i)).getRenderer(PanelRenderer.class).setBackgroundColor(new Color("#00000000"));
        }
        nifty.getScreen(stageType).findElementByName("panel_color"+h).setVisible(false);
    }
    
    public void checkIn(int j){
        unCheck(j);
        ArrayList<String> idsTexturesOrSubMeshes = control.getIdsTexturesORSubMeshes(subStageSelected[j]);
        for(int i=multiPage[j]*TEXTURES_PAGE; i<control.getNumTexturesORSubMeshes(subStageSelected[j]); i++){
            if(i<((multiPage[j]+1)*TEXTURES_PAGE)){
                if (control.isChecked(subStageSelected[j], idsTexturesOrSubMeshes.get(i))){
                     nifty.getScreen(stageType).findElementByName("t"+Integer.toString(j)+Integer.toString(i%TEXTURES_PAGE)).getRenderer(PanelRenderer.class).setBackgroundColor(new Color("#FF0000AA"));
                     if(!seleccionado.containsKey(subStageSelected[j])){
                        seleccionado.put(subStageSelected[j], idsTexturesOrSubMeshes.get(i));
                     }
                }
            }
        }
        
        if(seleccionado.containsKey(subStageSelected[j])){
            TexturesMeshType texturesMeshType = control.getTextureType(seleccionado.get(subStageSelected[j]));

            if (texturesMeshType == null){
                nifty.getScreen(stageType).findElementByName("panel_color"+Integer.toString(j)).setVisible(false);
            }
            else{
                if(!(control.getTextureType(seleccionado.get(subStageSelected[j])) == TexturesMeshType.simpleTexture)){
                    nifty.getScreen(stageType).findElementByName("panel_color"+Integer.toString(j)).setVisible(true);
                }
                else{
                    nifty.getScreen(stageType).findElementByName("panel_color"+Integer.toString(j)).setVisible(false);
                }
            }
        }
        else{
            nifty.getScreen(stageType).findElementByName("panel_color"+Integer.toString(j)).setVisible(false);
        }
    }
}
