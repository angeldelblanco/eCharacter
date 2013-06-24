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
package es.eucm.echaracter.loader;

import com.jme3.asset.AssetInfo;
import com.jme3.asset.AssetKey;
import com.jme3.asset.AssetLoadException;
import com.jme3.asset.AssetLocator;
import com.jme3.asset.AssetManager;
import com.jme3.asset.AssetNotFoundException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.StringTokenizer;

public class ResourceLocator implements AssetLocator{

    private File root;
    
    public void setRootPath(String rootPath) {
        if(rootPath == null){
            throw new NullPointerException();
        }
        try {
            root = new File(rootPath).getCanonicalFile();
            if(!root.isDirectory()){
                throw new IllegalArgumentException("Given root path \"" + root + "\" is not a directory.");
            }
        } catch (IOException ex) {
            throw new AssetLoadException("Root path is invalid", ex);
        }
        
    }

    public AssetInfo locate(AssetManager manager, AssetKey key) {
        String filePath = key.getName();        
        String path = getAssetResource(filePath, root.getPath());
        if(path!= null){
            // File found
            return new AssetInfoResource(manager, key, path);
        }
        else {
            return null;
        }
    }
    
    private static String getAssetResource(String filePath, String rootPath){
        File file = new File(filePath);
        if (file.exists()){
            return filePath;
        }
        else{
            // The file don't exists.
            String path = Configuration.USER_PATH+File.separator+"eCharacter"+File.separator+filePath;
            file = new File(path);
            if (file.exists()){
                return path;
            }
            else{
                String fileName = getFileName(filePath);
                // Search this file in root directory.
                return getRecursiveAssetResource(fileName, rootPath);
            }
        }
    }
    
    private static String getRecursiveAssetResource(String fileName,String directory){
        File dirPath = new File(directory);
        /** List all the files of this directory */
        File[] files = dirPath.listFiles();
        String resource = null;
        int x = 0;
        while(resource == null && x<files.length){
        //for (int x=0;x<files.length;x++){
            File file = files[x];
            if (! file.isDirectory()) {
                /** "file" is a file */
                if (file.getName().equals(fileName)){
                    /** We found the file. */
                    try {
                        String canonical = file.getCanonicalPath();
                        String absolute = file.getAbsolutePath();
                        if (!canonical.endsWith(absolute)){
                            throw new AssetNotFoundException("Asset name doesn´t match requirements.\n" +
                                    "\"" + canonical + "\" doesn´t match \"" + absolute + "\"");
                        }
                    } catch (IOException ex) {
                        throw new AssetLoadException("Failed to get file canonical path " + file, ex);
                    }
                    resource = dirPath+File.separator+file.getName();                     
                    return resource;
                }   
            }
            else{
                /** "file" is a directory. */
                if(!file.isHidden()){
                    resource = getRecursiveAssetResource(fileName, directory+File.separator+file.getName());
                }
            }
            x++;
        }
        return resource;
    }
    
    private static String getPathResourceInDirectory(String fileName, String directory){        
        File dirPath = new File(directory);
        /** List all the files of this directory */
        File[] files = dirPath.listFiles();
        if (files!=null){
            String finalPath = null;
            for (int x=0;x<files.length;x++){
                File file = files[x];
                if (! file.isDirectory()) {
                    /** "file" is a file */
                    if (file.getName().equals(fileName)){
                        /** We found the file. */
                        String resource = dirPath+File.separator+file.getName();                                        
                        try {
                            InputStream stream = new FileInputStream(resource);
                            return resource;
                        } catch (FileNotFoundException ex) {
                            System.out.println(ex.getMessage());
                            return null;
                        }
                    }   
                }
                else{
                    /** "file" is a directory. */
                    finalPath = getPathResourceInDirectory(fileName, directory+File.separator+file.getName());
                }
            }
            return finalPath;
        }
        else{
            return null;
        }
    }
    
    private static InputStream getResourceInDirectory(String fileName, String directory){        
        File dirPath = new File(directory);
        /** List all the files of this directory */
        File[] files = dirPath.listFiles();
        if (files!=null){
            InputStream stream = null;
            for (int x=0;x<files.length;x++){
                File file = files[x];
                if (! file.isDirectory()) {
                    /** "file" is a file */
                    if (file.getName().equals(fileName)){
                        /** We found the file. */
                        String resource = dirPath+File.separator+file.getName();                                        
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
                    stream = getResourceInDirectory(fileName, directory+File.separator+file.getName());
                }
            }
            return stream;
        }
        else{
            return null;
        }
    }
    
    public static String getPathResource(String filePath){
        try {
            InputStream stream = new FileInputStream(filePath);  
            // The file exists
            return filePath;
        } catch (FileNotFoundException ex) {

            try {
                // The file don't exists. 
                String path = Configuration.USER_PATH+File.separator+"eCharacter"+File.separator+filePath;
                InputStream stream = new FileInputStream(path);
                return path;
            } catch (FileNotFoundException ex1) {
                String fileName = getFileName(filePath);
                // Search this file in root directory. 
                return getPathResourceInDirectory(fileName, "."+File.separator);
            }
            

            //String fileName = getFileName(filePath);
            // Search this file in root directory.
            //return getResourceInDirectory(fileName, "."+File.separator);
        }     
    }
    
    public static InputStream getResource(String filePath){
        try {
            InputStream stream = new FileInputStream(filePath);  
            // The file exists
            return stream;
        } catch (FileNotFoundException ex) {

            try {
                // The file don't exists. 
                String s = Configuration.USER_PATH+File.separator+"eCharacter"+File.separator+filePath;
                InputStream stream = new FileInputStream(s);
                return stream;
            } catch (FileNotFoundException ex1) {
                String fileName = getFileName(filePath);
                // Search this file in root directory. 
                return getResourceInDirectory(fileName, "."+File.separator);
            }
            

            //String fileName = getFileName(filePath);
            // Search this file in root directory.
            //return getResourceInDirectory(fileName, "."+File.separator);
        }     
    }
    
    /** Get the name of the file */
    private static String getFileName(String filePath){
        StringTokenizer tokens=new StringTokenizer(filePath, File.separator);
        String fileName = "";
        while (tokens.hasMoreTokens()){
            fileName = tokens.nextToken(); 
        }
        return fileName;
    }
    
    private static class AssetInfoResource extends AssetInfo{
        
        private String file;

        public AssetInfoResource(AssetManager manager,AssetKey key,String file){
            super(manager,key);
            this.file = file;
        }
        
        @Override
        public InputStream openStream() {
            try {
                return new FileInputStream(file);
            } catch (FileNotFoundException ex) {
                throw new AssetLoadException("Failed to open file: " + file, ex);
            }
        }
    }
}
