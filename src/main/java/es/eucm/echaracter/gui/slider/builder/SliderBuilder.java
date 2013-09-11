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

package es.eucm.echaracter.gui.slider.builder;

import de.lessvoid.nifty.builder.ControlBuilder;

public class SliderBuilder extends ControlBuilder {
  public SliderBuilder(final boolean vertical) {
    super(getName(vertical));
  }

  public SliderBuilder(final String id, final boolean vertical) {
    super(id, getName(vertical));
  }

  public void min(final float min) {
    set("min", String.valueOf(min));
  }

  public void max(final float max) {
    set("max", String.valueOf(max));
  }

  public void initial(final float initial) {
    set("initial", String.valueOf(initial));
  }

  public void stepSize(final float stepSize) {
    set("stepSize", String.valueOf(stepSize));
  }

  public void buttonStepSize(final float buttonStepSize) {
    set("buttonStepSize", String.valueOf(buttonStepSize));
  }

  private static String getName(final boolean vertical) {
    if (vertical) {
      return "verticalSlider";
    } else {
      return "horizontalSlider";
    }
  }
}
