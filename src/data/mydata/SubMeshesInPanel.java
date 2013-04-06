/*******************************************************************************
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
package data.mydata;

import data.model.SubMeshType;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Set;

public class SubMeshesInPanel 
{
    private boolean multiSelection;
    //HashMap<submesh, boolean = if this subMesh is selected>
    private HashMap<SubMeshType,Boolean> subMeshes;
    
    public SubMeshesInPanel(boolean multiSelection)
    {
        this.multiSelection = multiSelection;
        subMeshes = new HashMap<SubMeshType,Boolean>();
    }
    
    public void addSubMesh(SubMeshType subMesh,boolean isCheck)
    {
        subMeshes.put(subMesh, isCheck);
    }
    
    /*
     * Return a list with the activated submeshes in this panel
     */
    public ArrayList<SubMeshType> getCheckedSubMeshes()
    {
        ArrayList<SubMeshType> listCheckedSubMeshes = new ArrayList<SubMeshType>();
        Set<SubMeshType> setSubMeshesType = subMeshes.keySet();
        Iterator<SubMeshType> it = setSubMeshesType.iterator();
        while(it.hasNext())
        {
            SubMeshType subMesh = it.next();
            if (subMeshes.get(subMesh)){
               listCheckedSubMeshes.add(subMesh);
           } 
        }
        return listCheckedSubMeshes;
    }
    
    public void changeSubMesh(String idSubMesh)
    {
    }
}
