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
import de.lessvoid.nifty.effects.EffectImpl;
import de.lessvoid.nifty.effects.EffectProperties;
import de.lessvoid.nifty.effects.Falloff;
import de.lessvoid.nifty.elements.Element;
import de.lessvoid.nifty.elements.render.TextRenderer;
import de.lessvoid.nifty.render.NiftyRenderEngine;
import de.lessvoid.nifty.tools.SizeValue;
import de.lessvoid.nifty.tools.TargetElementResolver;

public class Tooltip implements EffectImpl {
    private Nifty nifty;
    private Element targetElement;
    private String hintText;

    public void activate(final Nifty niftyParam, final Element element, final EffectProperties parameter) {
        this.nifty = niftyParam;

        TargetElementResolver resolver = new TargetElementResolver(nifty.getCurrentScreen(), element);
        targetElement = resolver.resolve(parameter.getProperty("targetElement"));

        String text = parameter.getProperty("hintText");
        if (text != null) {
            hintText = text;
        }
    }

    public void execute(final Element element, final float normalizedTime, final Falloff falloff, final NiftyRenderEngine r) {
        if (targetElement != null) {
            if (hintText != null) {
                targetElement.findElementByName("content").getRenderer(TextRenderer.class).setText(hintText);
            }
            int x = hintText.length()*5;
            targetElement.setConstraintX(new SizeValue(element.getX() + element.getHeight()/2 - x + "px"));
            targetElement.setConstraintY(new SizeValue(element.getY() + element.getHeight()/2 + "px"));
            targetElement.setPaddingLeft(new SizeValue(x+"px"));
            targetElement.setPaddingRight(new SizeValue(x+"px"));
            targetElement.show();
            nifty.getCurrentScreen().layoutLayers();
        }
    }

    public void deactivate() {
        if (targetElement != null) {
            targetElement.hide();
        }
    }
}
