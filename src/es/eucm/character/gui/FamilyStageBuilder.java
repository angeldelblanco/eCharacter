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
import de.lessvoid.nifty.builder.PanelBuilder;
import de.lessvoid.nifty.builder.TextBuilder;
import de.lessvoid.nifty.controls.dropdown.builder.DropDownBuilder;
import de.lessvoid.nifty.effects.Effect;
import de.lessvoid.nifty.effects.EffectEventId;
import de.lessvoid.nifty.elements.Element;
import de.lessvoid.nifty.elements.render.ImageRenderer;
import de.lessvoid.nifty.elements.render.TextRenderer;
import de.lessvoid.nifty.tools.Color;
import es.eucm.character.control.Control;
import es.eucm.character.i18n.I18N;
import java.util.List;

public class FamilyStageBuilder {
    private Nifty nifty;
    private I18N i18nGui; 
    private Control control;
    private String stageType;
    private int modelsPage;
    
    public FamilyStageBuilder(Nifty nifty, Control control,I18N i18nGui,String font){
        stageType = "modelScreen";
        this.nifty = nifty;
        this.control = control;
        modelsPage = 0;
        this.i18nGui = i18nGui;
        initModels(font);
    }
    
    /*receives as input the lenguaje of the aplication
      and builds all the families and the pictures of models*/
    
    private void initModels(final String font){
        new PanelBuilder() {{
            width("40%");
            childLayoutHorizontal();
            text(new TextBuilder(){{
                valignCenter();
                color(Color.WHITE);
                font(font);
                text(i18nGui.getString("idFamily"));
                width("50%");
                wrap(true);
            }});
            control(new DropDownBuilder("familyDropDown") {{
                valignCenter();
                width("50%");
            }});
        }}.build(nifty, nifty.getScreen(stageType), nifty.getScreen(stageType).findElementByName("familyDrop"));
    }
    
    //Change the model's page hiding pictures of previous models or previous family
    
    public String changeCharacterPage(I18N i18nFamily,String steep){
        if(steep.equals("+")){
            modelsPage++;
        }
        if(steep.equals("-")){
            modelsPage--;
        }
        if(steep.equals("0")){
            modelsPage = 0;
        }
        Element image = nifty.getScreen(stageType).findElementByName("m");
        image.setVisible(true);
        List<Effect> effects = image.getEffects(EffectEventId.onHover,Tooltip.class);
        effects.get(0).getParameters().setProperty("hintText", i18nFamily.getString(control.getModelsLabel().get(modelsPage)));
        ImageRenderer imager = image.getRenderer(ImageRenderer.class);
        String imagePath = control.getModelIconPath(control.getModelsLabel().get(modelsPage));
        if(imagePath!=null){
            imager.setImage(nifty.getRenderEngine().createImage(imagePath, false));
        }
        else{
            imager.setImage(nifty.getRenderEngine().createImage("assets/Interface/x.png", false));
        }
        if(modelsPage > 0){
            nifty.getScreen(stageType).findElementByName("leftT").setVisible(true);
        }
        else{
            nifty.getScreen(stageType).findElementByName("leftT").setVisible(false);
        }
        if((control.getNumModels() - modelsPage) > 1){
            nifty.getScreen(stageType).findElementByName("rightT").setVisible(true);
        }
        else{
            nifty.getScreen(stageType).findElementByName("rightT").setVisible(false);
        }
        String url = "";
        if(control.getMetadataFamilyURL()!=null){url =i18nGui.getString("idUrl")+": "+"\n"+control.getMetadataFamilyURL();}
        String desc = i18nGui.getString("idDescriptionFamily")+": "+"\n"+i18nFamily.getString(control.getMetadataFamilyDescription())+"\n";
        String aut = i18nGui.getString("idAuthor")+": "+"\n"+i18nFamily.getString(control.getMetadataFamilyAuthor())+"\n";
        nifty.getScreen(stageType).findElementByName("descriptionText").getRenderer(TextRenderer.class).setText(desc+aut+url);
        nifty.getScreen(stageType).findElementByName("family0").layoutElements();
        control.getModelFamilyPath(control.getModelsLabel().get(modelsPage));
        return control.getModelFamilyPath(control.getModelsLabel().get(modelsPage));
    }
}
