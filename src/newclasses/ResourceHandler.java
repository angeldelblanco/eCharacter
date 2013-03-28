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

package newclasses;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

public class ResourceHandler {
    public ResourceHandler(){}
    
    public InputStream getResource(String fileName) throws FileNotFoundException{
        File dirPath = new File("./");
        File[] files = dirPath.listFiles();
        for (int x=0;x<files.length;x++)
        {
            File file = files[x];
            if (! file.isDirectory()) {
                System.out.println(file.getPath());
                if (file.getName().equals(fileName)){
                    System.out.println("Fichero encontrado");
                    String resource = dirPath+File.separator+file.getName();
                    System.out.println(resource);
                    FileInputStream stream = new FileInputStream(resource);
                    //InputStream stream = this.getClass().getResourceAsStream(resource);
                    return stream;
                }   
            }
            else{
                //Es un directorio.
            }
        }
        
        
        return null;
    }
    
    public static void main(String args[]) throws FileNotFoundException
    {
        ResourceHandler r = new ResourceHandler();
        InputStream stream = r.getResource("pruebalectura.txt");
        System.out.println(stream.toString());
    } 
    
}
