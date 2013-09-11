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

/**
 * The ScrollbarView is used to update the visual representation of the scrollbar.
 * @author void
 */
public interface ScrollbarView {

  /**
   * Get the size of the Scrollbar area. This is the area in pixel that is available to
   * scroll around.
   * @return pixel size value
   */
  int getAreaSize();

  /**
   * Returns the minimum size this View can show the handle without distortion. The handle
   * will never be smaller than this value.
   * @return minimum handle size
   */
  int getMinHandleSize();

  /**
   * Set the position and the size of the handle.
   * @param pos position
   * @param size size
   */
  void setHandle(int pos, int size);

  /**
   * That's a callback that is called when the value has been changed.
   * @param value the new value
   */
  void valueChanged(float value);

  /**
   * Filter the correct value for the given view (horizontal or vertical) from the given mouse coordinates.
   * @param pixelX x position
   * @param pixelY y position
   * @return x or y specific to implementation
   */
  int filter(int pixelX, int pixelY);
}
