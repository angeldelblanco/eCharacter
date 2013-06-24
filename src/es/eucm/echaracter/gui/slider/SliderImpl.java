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
 * A simple Slider mechanism which is used as the base for the Scrollbar.
 * @author void
 */
public class SliderImpl {
  private SliderView view;
  private float value;
  private float oldValue;
  private float min;
  private float max;
  private float stepSize;
  private float buttonStepSize;

  public void bindToView(final SliderView view, final float min, final float max, final float stepSize, final float buttonStepSize) {
    this.view = view;
    this.min = min;
    this.max = max;
    this.stepSize = stepSize;
    this.buttonStepSize = buttonStepSize;
    this.oldValue = -1.f;
    updateView();
    changeValue(0.f);
  }

  public void setValue(final float value) {
    changeValue(value);
    updateView();
  }

  public float getValue() {
    return value;
  }

  public void stepUp() {
    changeValue(value + buttonStepSize);
    updateView();
  }

  public void stepDown() {
    changeValue(value - buttonStepSize);
    updateView();
  }

  public void setValueFromPosition(final int pixelX, final int pixelY) {
    setValue(ensureStepSize(viewToWorld(view.filter(pixelX, pixelY))));
  }

  public float getMin() {
    return min;
  }

  public void setMin(final float min) {
    this.min = min;
    changeValue(value);
    updateView();
  }

  public float getMax() {
    return max;
  }

  public void setMax(final float max) {
    this.max = max;
    changeValue(value);
    updateView();
  }

  public float getStepSize() {
    return stepSize;
  }

  public void setStepSize(final float stepSize) {
    this.stepSize = stepSize;
    changeValue(value);
    updateView();
  }

  public float getButtonStepSize() {
    return buttonStepSize;
  }

  public void setButtonStepSize(final float buttonStepSize) {
    this.buttonStepSize = buttonStepSize;
    changeValue(value);
    updateView();
  }

  public void setup(final float min, final float max, final float current, final float stepSize, final float buttonStepSize) {
    this.min = min;
    this.max = max;
    this.value = current;
    this.stepSize = stepSize;
    this.buttonStepSize = buttonStepSize;
    changeValue(value);
    updateView();
  }

  private void changeValue(final float newValue) {
    value = newValue;    
    if (value > max) {
      value = max;
    } else if (newValue < min) {
      value = min;
    }
    value = ensureStepSize(value);
    if (value != oldValue) {
      oldValue = value;
      view.valueChanged(value);
    }
  }

  public void updateView() {
    view.update((int) worldToView(value));
  }

  private float ensureStepSize(final float value) {
    return Math.round(value / stepSize) * stepSize;
  }

  private float viewToWorld(final float viewValue) {
    return (viewValue / view.getSize() * (max - min)) + min;
  }

  private float worldToView(final float worldValue) {
    return (worldValue - min) / (max - min) * view.getSize();
  }
}
