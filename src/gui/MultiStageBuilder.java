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
import de.lessvoid.nifty.elements.Element;
import de.lessvoid.nifty.elements.render.ImageRenderer;
import de.lessvoid.nifty.elements.render.PanelRenderer;
import de.lessvoid.nifty.elements.render.TextRenderer;
import de.lessvoid.nifty.tools.Color;
import i18n.I18N;
import java.util.ArrayList;
import types.StageType;


public class MultiStageBuilder {
    private static final int TEXTURES_PAGE = 2;
    private static final int SUBSTAGE_PAGE = 2;
    private Nifty nifty;
    private I18N i18nGui, i18nFamily; 
    private Control control;
    private String stageType;
    private int multiPage[];
    
    public MultiStageBuilder(Nifty nifty, Control control, I18N i18nGui, I18N i18nFamily){
        stageType = StageType.multiStage.toString();
        this.nifty = nifty;
        this.control = control;
        this.i18nGui = i18nGui;
        this.i18nFamily = i18nFamily;
        multiPage = new int[SUBSTAGE_PAGE];
        for(int i=0; i<SUBSTAGE_PAGE;i++){
            multiPage[i]=0;
        }
        init();
    }
    
    /*receives as input a list of all the stages of the selected family 
      and builds all them of that are multistage*/
    
    private void init(){
        for(int i = 0 ; i<SUBSTAGE_PAGE; i++){
            nifty.getScreen(stageType).findElementByName("colorText"+Integer.toString(i)).getRenderer(TextRenderer.class).setText(i18nGui.getString("idColor"));
            nifty.getScreen(stageType).findElementByName("panel_color"+Integer.toString(i)).layoutElements();
        }  
    }

    //displays the current subpage images of the selection stage
    
    public void showSubTexturePage(String selection, int h, int page, String steep){
        //ArrayList<String> idSubStages = control.getIdsSubStages(selection);
        ArrayList<String> idSubStages = new ArrayList<String>();
        idSubStages.add("skinsPanel");
        if((page*SUBSTAGE_PAGE+h)<idSubStages.size()){
            unCheck(h);
            changeMultiPage(h,steep);
            nifty.getScreen(stageType).findElementByName("t"+Integer.toString(h)).setVisible(true);
            nifty.getScreen(stageType).findElementByName("text"+Integer.toString(h)).getRenderer(TextRenderer.class).setText(i18nFamily.getString(control.getSubStageLabel(selection,idSubStages.get(page*SUBSTAGE_PAGE+h))));
            nifty.getScreen(stageType).findElementByName("textPanel"+Integer.toString(h)).layoutElements();
            //ArrayList<String> idsTexturesOrSubMeshes = control.getIdsTexturesORSubMeshes(idSubStages.get(h+page*TEXTURES_PAGE));
            ArrayList<String> idsTexturesOrSubMeshes = new ArrayList<String>();
            idsTexturesOrSubMeshes.add("assets/Interface/Icons/eAdventure/Boy/IconoPielBlancaBoy.png");
            idsTexturesOrSubMeshes.add("assets/Interface/Icons/eAdventure/Boy/IconoPielNegraBoy.png");
            idsTexturesOrSubMeshes.add("assets/Interface/Icons/eAdventure/Boy/IconoPielAmarillaBoy.png");
            idsTexturesOrSubMeshes.add("assets/Interface/Icons/eAdventure/Boy/IconoPielRojaBoy.png");
            //for(int i=multiPage[h]*TEXTURES_PAGE; i<control.getNumTexturesORSubMeshes(idSubStages.get(page*SUBSTAGE_PAGE+h)); i++){
            for(int i=multiPage[h]*TEXTURES_PAGE; i<idsTexturesOrSubMeshes.size(); i++){
                if(i<((multiPage[h]+1)*TEXTURES_PAGE)){
                    Element image = nifty.getScreen(stageType).findElementByName("i"+Integer.toString(h)+Integer.toString(i%TEXTURES_PAGE));
                    image.setVisible(true);
                    ImageRenderer imager = image.getRenderer(ImageRenderer.class);
                    //imager.setImage(nifty.getRenderEngine().createImage(control.getIconPathTexturesORSubMeshes(idsTexturesOrSubMeshes.get(i)), false));
                    imager.setImage(nifty.getRenderEngine().createImage(idsTexturesOrSubMeshes.get(i), false));
                    /*if (control.isChecked(idSubStages.get(h+page*TEXTURES_PAGE), idsTexturesOrSubMeshes.get(i))){
                        nifty.getScreen(stageType).findElementByName("t"+Integer.toString(h)+Integer.toString(i%TEXTURES_PAGE)).getRenderer(PanelRenderer.class).setBackgroundColor(new Color("#FF0000AA"));
                    }*/
                }
            }
            //for(int i=control.getNumTexturesORSubMeshes(idSubStages.get(page*SUBSTAGE_PAGE+h));i<((multiPage[h]+1)*TEXTURES_PAGE);i++){
            for(int i=idsTexturesOrSubMeshes.size();i<((multiPage[h]+1)*TEXTURES_PAGE);i++){
                Element image = nifty.getScreen(stageType).findElementByName("i"+Integer.toString(h)+Integer.toString(i%TEXTURES_PAGE));
                image.setVisible(false);
            }
            if(multiPage[h] > 0){
                nifty.getScreen(stageType).findElementByName("leftT"+Integer.toString(h)).setVisible(true);
            }
            else{
                nifty.getScreen(stageType).findElementByName("leftT"+Integer.toString(h)).setVisible(false);
            }
            if((((1.0*control.getNumTexturesORSubMeshes(idSubStages.get(page*SUBSTAGE_PAGE+h)))/(1.0*TEXTURES_PAGE)) - multiPage[h]) > 1){
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
            
            if(control.getNumSubStages(selection)<3){
                nifty.getScreen(stageType).findElementByName("changePanel").setVisible(false);
            }
            else{
                nifty.getScreen(stageType).findElementByName("changePanel").setVisible(true);
                if(page > 0){
                    nifty.getScreen(stageType).findElementByName("leftT").setVisible(true);
                }
                else{
                    nifty.getScreen(stageType).findElementByName("leftT").setVisible(false);
                }
                if((((1.0*control.getNumSubStages(selection)) /(1.0*SUBSTAGE_PAGE)) - page) > 1){
                    nifty.getScreen(stageType).findElementByName("rightT").setVisible(true);
                }
                else{
                    nifty.getScreen(stageType).findElementByName("rightT").setVisible(false);
                }
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
    
    public String chanchangeTextureOrSubMesh(String selection,int page, String h, String i){
        unCheck(Integer.valueOf(h));
        nifty.getScreen(stageType).findElementByName("t"+h+i).getRenderer(PanelRenderer.class).setBackgroundColor(new Color("#FF0000AA"));
        int j = Integer.valueOf(h);
        ArrayList<String> idSubStages = control.getIdsSubStages(selection);
        ArrayList<String> idsTexturesOrSubMeshes = control.getIdsTexturesORSubMeshes(idSubStages.get(j+page*TEXTURES_PAGE));
        return idsTexturesOrSubMeshes.get(multiPage[j]*TEXTURES_PAGE+Integer.valueOf(i));
    }
    
    public String getSubStage(String selection,int page, String h){
        int j = Integer.valueOf(h);
        ArrayList<String> idSubStages = control.getIdsSubStages(selection);
        return idSubStages.get(j+page*TEXTURES_PAGE);
    }
    
    //uncheck all textures
    
    private void unCheck(int h){
        for(int i = 0; i < TEXTURES_PAGE; i++){
            nifty.getScreen(stageType).findElementByName("t"+Integer.toString(h)+Integer.toString(i)).getRenderer(PanelRenderer.class).setBackgroundColor(new Color("#00000000"));
        }
    }
}
