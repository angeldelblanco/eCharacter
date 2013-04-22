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

package es.eucm.character.loader;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.StringTokenizer;

/**
 * This class allow resource searching in file system. 
 */
public class ResourceHandler{
    public static InputStream getResource(String fileName, String directory){        
        File dirPath = new File(directory);
        /** List all the files of this directory */
        File[] files = dirPath.listFiles();
        InputStream stream = null;
        for (int x=0;x<files.length;x++){
            File file = files[x];
            if (! file.isDirectory()) {
                /** "file" is a file */
                if (file.getName().equals(fileName)){
                    /** We found the file. */
                    String resource = dirPath+File.separator+file.getName();                    
                    System.out.println("File found : " + resource);                    
                    try {
                        stream = new FileInputStream(resource);
                    } catch (FileNotFoundException ex) {
                        System.out.println(ex.getMessage());
                    }
                    return stream;
                }   
            }
            else{
                /** "file" is a directory. */
                stream = getResource(fileName, directory+File.separator+file.getName());
            }
        }
        return stream;
    }
    
    public static InputStream getResource(String filePath){
        try {
            InputStream stream = new FileInputStream(filePath);  
            /** The file exists */
            return stream;
        } catch (FileNotFoundException ex) {
            /** The file don't exists. */
            String fileName = getFileName(filePath);
            /** Search this file in root directory. */
            return getResource(fileName, "."+File.separator);
        }     
    }
    
    /** Get the name of the file */
    private static String getFileName(String filePath){
        StringTokenizer tokens=new StringTokenizer(filePath, "/");
        String fileName = "";
        while (tokens.hasMoreTokens()){
            fileName = tokens.nextToken(); 
        }
        return fileName;
    }
}
