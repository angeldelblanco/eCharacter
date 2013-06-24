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
 *          For more info please visit:  <http://echaracter.e-ucm.es>, 
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

package es.eucm.echaracter.gui.scrollbar;


import de.lessvoid.nifty.Nifty;
import de.lessvoid.nifty.controls.Scrollbar;
import de.lessvoid.nifty.effects.EffectImpl;
import de.lessvoid.nifty.effects.EffectProperties;
import de.lessvoid.nifty.effects.Falloff;
import de.lessvoid.nifty.elements.Element;
import de.lessvoid.nifty.render.NiftyRenderEngine;
import java.util.logging.Logger;

public class UpdateScrollpanelPositionToDisplayElement implements EffectImpl {
  private Logger log = Logger.getLogger(UpdateScrollpanelPositionToDisplayElement.class.getName());
  private Element targetElement;

  public void activate(final Nifty nifty, final Element elementParameter, final EffectProperties parameter) {
    String target = parameter.getProperty("target");
    if (target != null) {
      targetElement = nifty.getCurrentScreen().findElementByName(target);
    }
  }

  public void execute(
      final Element element,
      final float normalizedTime,
      final Falloff falloff,
      final NiftyRenderEngine r) {
    if (targetElement != null) {
      Scrollbar verticalScrollbar = targetElement.findNiftyControl("#nifty-internal-vertical-scrollbar", Scrollbar.class);

      int minY = (int) verticalScrollbar.getValue();
      int maxY = (int) verticalScrollbar.getValue() + (int) verticalScrollbar.getWorldPageSize();

      int currentMinY = element.getY() - targetElement.getY() + (int) verticalScrollbar.getValue();
      int currentMaxY = element.getY() - targetElement.getY() + element.getHeight() + (int) verticalScrollbar.getValue();

      // below?
      int delta = -1;
      if (currentMinY >= maxY || (currentMinY <= maxY && currentMaxY >= maxY)) {
        // scroll down
        delta = currentMaxY - maxY;
        verticalScrollbar.setValue(minY + delta);
      } else if (currentMaxY <= minY || (currentMinY <= minY && currentMaxY >= minY)) {
        // scroll up
        delta = minY - currentMinY;
        verticalScrollbar.setValue(minY - delta);
      }
      log.fine(minY + ":" + maxY + " - " + currentMinY + ":" + currentMaxY + " - " + delta);
    }
  }

  public void deactivate() {
  }
}
