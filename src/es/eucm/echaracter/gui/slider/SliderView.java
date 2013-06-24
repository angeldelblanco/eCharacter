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

package es.eucm.echaracter.gui.slider;

/**
 * The SliderView is used to update a sliders visual representation.
 * @author void
 */
public interface SliderView {

  /**
   * Get the size of the Slider area. This is the area in pixel that is available to
   * scroll around.
   * @return pixel size value
   */
  int getSize();

  /**
   * Update the Position of the slider.
   * @param position the new position in px
   */
  void update(int position);

  /**
   * This translates the given x/y position into a single value. A horizontal slider will simply
   * return x and a vertical one will return y.
   * @param pixelX x
   * @param pixelY y
   * @return value
   */
  int filter(int pixelX, int pixelY);

  /**
   * That's a callback that is called when the value has been changed.
   * @param value the new value
   */
  void valueChanged(float value);
}
