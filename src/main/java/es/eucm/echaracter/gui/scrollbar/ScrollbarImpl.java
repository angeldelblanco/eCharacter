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

public class ScrollbarImpl {
  private ScrollbarView view;
  private float value;
  private float oldValue;
  private float min = 0.f;
  private float worldMax;
  private float worldPageSize;
  private float buttonStepSize;
  private float pageStepSize;
  private boolean moveTheHandle;
  private int moveTheHandleStartPosDelta;

  public void bindToView(final ScrollbarView view, final float value, final float worldMax, final float worldPageSize, final float buttonStepSize, final float pageStepSize) {
    this.view = view;
    this.value = value;
    this.oldValue = -1;
    this.worldMax = worldMax;
    this.worldPageSize = worldPageSize;
    this.buttonStepSize = buttonStepSize;
    this.pageStepSize = pageStepSize;
    this.moveTheHandle = false;
    ensureWorldPageSize();
    updateView();
    changeValue(this.value);
  }

  public void setup(final float value, final float worldMax, final float worldPageSize, final float buttonStepSize, final float pageStepSize) {
    this.value = value;
    this.worldMax = worldMax;
    this.worldPageSize = worldPageSize;
    this.buttonStepSize = buttonStepSize;
    this.pageStepSize = pageStepSize;
    ensureWorldPageSize();
    changeValue(value);
    updateView();
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

  public void stepPageUp() {
    changeValue(value + pageStepSize);
    updateView();
  }

  public void stepPageDown() {
    changeValue(value - pageStepSize);
    updateView();
  }

  public float getWorldMax() {
    return worldMax;
  }

  public void setWorldMax(final float max) {
    this.worldMax = max;
    ensureWorldPageSize();

    changeValue(value);
    updateView();
  }

  public float getWorldPageSize() {
    return worldPageSize;
  }

  public void setWorldPageSize(final float worldPageSize) {
    this.worldPageSize = worldPageSize;
    ensureWorldPageSize();

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

  public float getPageStepSize() {
    return pageStepSize;
  }

  public void setPageStepSize(final float pageStepSize) {
    this.pageStepSize = pageStepSize;
  }

  public void interactionClick(final int viewValueClicked) {
    int viewSize = view.getAreaSize();
    int handleSize = calcHandleSize(viewSize);
    int handlePosition = calcHandlePosition(viewSize, handleSize);
    if (hitsHandle(handlePosition, handleSize, viewValueClicked)) {
      moveTheHandle = true;
      moveTheHandleStartPosDelta = viewValueClicked - handlePosition;
    } else {
      moveTheHandle = false;
      if (viewValueClicked < handlePosition && viewValueClicked > 0) {
        stepPageDown();
      } else if (viewValueClicked > (handlePosition + handleSize) && viewValueClicked < viewSize) {
        stepPageUp();
      }
    }
  }

  public void interactionMove(final int viewValue) {
    if (!moveTheHandle) {
      return;
    }
    int viewSize = view.getAreaSize();
    if (viewValue < 0 || viewValue >= viewSize) {
      return;
    }
    int newViewPos = viewValue - moveTheHandleStartPosDelta;
    int handleSize = calcHandleSize(viewSize);
    float newWorldValue = viewToWorld(newViewPos, viewSize - handleSize, worldMax - worldPageSize);
    changeValue(newWorldValue);
    updateView();
  }

  private boolean hitsHandle(final int handlePosition, final int handleSize, final int viewValueClicked) {
    return viewValueClicked > handlePosition && viewValueClicked < (handlePosition + handleSize);
  }

  private void changeValue(final float newValue) {
    value = newValue;
    if (value > (worldMax - worldPageSize)) {
      value = worldMax - worldPageSize;
    } else if (newValue < min) {
      value = min;
    }
    if (value != oldValue) {
      oldValue = value;
      view.valueChanged(value);
    }
  }

  public void updateView() {
    int viewSize = view.getAreaSize();
    int handleSize = calcHandleSize(viewSize);
    view.setHandle(calcHandlePosition(viewSize, handleSize), handleSize);
  }

  private int calcHandlePosition(final int viewSize, final int handleSize) {
    int viewMin = (int) Math.floor(worldToView(value, worldMax - worldPageSize, viewSize - handleSize));
    if (viewMin + handleSize > viewSize) {
      viewMin = viewSize - handleSize;
    }
    return viewMin;
  }

  private int calcHandleSize(final int viewSize) {
    int minHandleSize = view.getMinHandleSize();
    if (worldMax == 0) {
      // special case: empty data and we can't divide by null anyway :)
      // we return the maximum size because when nothing is there the scrollbar handle should be maximal.
      return viewSize;
    }
    int handleSize = (int) Math.floor(worldPageSize * viewSize / worldMax);
    if (handleSize < minHandleSize) {
      return minHandleSize;
    }
    if (handleSize > viewSize) {
      return viewSize;
    }
    return handleSize;
  }

  private float viewToWorld(final float viewValue, final float viewMaxValue, final float worldMaxValue) {
    return viewValue / viewMaxValue * worldMaxValue;
  }

  private float worldToView(final float worldValue, final float worldMaxValue, final float viewMaxValue) {
    if (worldMax == 0.f) {
      return 0.f;
    }
    return (int) Math.round(worldValue / worldMaxValue * viewMaxValue);
  }

  private void ensureWorldPageSize() {
    if (worldPageSize > worldMax) {
      worldPageSize = worldMax;
    }
  }
}
