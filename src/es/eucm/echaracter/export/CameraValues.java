/*******************************************************************************
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

package es.eucm.echaracter.export;

import com.jme3.math.Vector3f;

public class CameraValues {
    private String cameraName;
    private Vector3f up;
    private Vector3f position;
    private Vector3f direction;
    
    public CameraValues(String cameraName, Vector3f position, Vector3f direction, Vector3f up){
        this.cameraName = cameraName;
        this.position = position;
        this.direction = direction;
        this.up = up;
    }

    public String getCameraName(){
        return cameraName;
    }
    
    public Vector3f getUp() {
        return up;
    }

    public Vector3f getPosition() {
        return position;
    }

    public Vector3f getDirection() {
        return direction;
    }

    public void setUp(Vector3f up) {
        this.up = up;
    }

    public void setPosition(Vector3f position) {
        this.position = position;
    }

    public void setDirection(Vector3f direction) {
        this.direction = direction;
    }
    
    
}
