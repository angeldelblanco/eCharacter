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

package es.eucm.echaracter.gui.progressbar;

import de.lessvoid.nifty.Nifty;
import de.lessvoid.nifty.controls.Controller;
import de.lessvoid.nifty.elements.Element;
import de.lessvoid.nifty.elements.render.TextRenderer;
import de.lessvoid.nifty.input.NiftyInputEvent;
import de.lessvoid.nifty.screen.Screen;
import de.lessvoid.nifty.tools.SizeValue;
import java.util.Properties;
import javax.sound.midi.ControllerEventListener;
import org.xml.sax.Attributes;

public class ProgressbarControl implements Controller {
  private Element progressBarElement;

  public void onStartScreen() {
  }

  public void onFocus(final boolean getFocus) {
  }

  public boolean inputEvent(final NiftyInputEvent inputEvent) {
    return false;
  }

  public void setProgress(final float progressValue) {
    float progress = progressValue;
    if (progress < 0.0f) {
      progress = 0.0f;
    } else if (progress > 1.0f) {
      progress = 1.0f;
    }
    final int MIN_WIDTH = 32; 
    int pixelWidth = (int)(MIN_WIDTH + (progressBarElement.getParent().getWidth() - MIN_WIDTH) * progress);
    progressBarElement.setConstraintWidth(new SizeValue(pixelWidth + "px"));
    progressBarElement.getParent().layoutElements();
  }

    public void bind(Nifty nifty, Screen screen, Element element, Properties parameter, de.lessvoid.xml.xpp3.Attributes controlDefinitionAttributes) {
        progressBarElement = element.findElementByName("my-progress#progress");
    }

    public void init(Properties parameter, de.lessvoid.xml.xpp3.Attributes controlDefinitionAttributes) {
        
    }
}
