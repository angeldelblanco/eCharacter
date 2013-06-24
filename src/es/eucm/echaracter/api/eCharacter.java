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
package es.eucm.echaracter.api;

import es.eucm.echaracter.main.Launcher;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Properties;

public class eCharacter {
    
    /* The keyset for the file properties is:
     * - lang : Active language
     * - defaultExportPath: Default export path
     */
    
    private HashSet<String> keySet;
    
    public eCharacter(){
        keySet = new HashSet<String>();
        keySet.add("lang");
        keySet.add("defaultExportPath");
        keySet.add("defaultFamily");
        keySet.add("defaultModel");
    }
    
    public void eCharacter(Properties properties,Callback callback) throws Exception{ 
        Iterator<Object> it = properties.keySet().iterator();
        while(it.hasNext()){
            String key = (String) it.next();
            if (!keySet.contains(key)){
                throw new Exception("Bad key in properties");
            }
        }
        Launcher.eCharacter(properties,callback);
    }
        
    
}
