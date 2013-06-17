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
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Properties;
import java.util.StringTokenizer;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Loading of the initial configuration from the .properties file.
 * In case the .properties file doesn´t exist, loading of a default configuration.
 */
public class Configuration { 
    
    private final static String userPath = System.getProperty("user.home");
    
    /** Properties file name */
    public final static String PROPERTIES_FILE_NAME = userPath+File.separator+"eCharacter"+File.separator+"initConfig.properties";
    //Language 
    public final static String LANGUAGE = "lang";
    
    //List of available languages
    public final static String LIST_LANGUAGE = "listLanguage";
 
    //Resolution
    public final static String RESOLUTION = "resolution";
 
    //Assets paths
    public final static String ASSETS_PATH = "assetsPath";
    
    //Families path
    public final static String FAMILIES_PATH = "familiesPath";    
    
    //Locale path
    public final static String LOCALE_PATH = "localePath";
    
    //Default quality
    public final static String DEFAULT_QUALITY = "defaultQualityFps";
    
    //Default camera name
    public final static String DEFAULT_CAMERA_NAME = "defaultCameraName";
    
    //Default camera: Vector Position
    public final static String DEFAULT_VECTOR_POSITION = "defaultVectorPosition";
    
    //Default camera: Vector Direction
    public final static String DEFAULT_VECTOR_DIRECTION = "defaultVectorDirection";
    
     //Default camera: Vector Up
    public final static String DEFAULT_VECTOR_UP = "defaultVectorUp";
    
    //Default export path
    public final static String DEFAULT_EXPORT_PATH = "defaultExportPath";
    
    //Defult temp path
    public final static String DEFAULT_TEMP_PATH = "defaultTempPath";
    
    Properties properties;
    
    
    public Configuration(){
        File f = new File(userPath+File.separator+"eCharacter");
        if (!f.exists()){
            f.mkdirs();
        }
        properties = new Properties();
    }
    
    public String getProperty(String Key){
        return this.properties.getProperty(Key); 
    }
    
    public void setProperty(String Key,String value){
        this.properties.setProperty(Key, value);
        //Saves the changes in the .properties file
        try {            
            FileOutputStream output = new FileOutputStream(PROPERTIES_FILE_NAME);
            this.properties.store(output,null);
            output.close();
        } catch (IOException ex) {
            Logger.getLogger(Configuration.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void loadPropertiesFile(InputStream input){
        try {
            properties.load(input);
            input.close();
        } catch (IOException ex) {
            Logger.getLogger(Configuration.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void loadDefaultProperties(){
        //Default configuration
        this.properties.setProperty(LANGUAGE, "en_UK");
        this.properties.setProperty(LIST_LANGUAGE,"en_UK es_ES fr_FR");
        this.properties.setProperty(RESOLUTION, "1024x768");
        this.properties.setProperty(ASSETS_PATH, "assets");
        this.properties.setProperty(FAMILIES_PATH,"assets/XML Configuration/families");
        this.properties.setProperty(LOCALE_PATH,"assets/Locale/Gui");
        this.properties.setProperty(DEFAULT_QUALITY, "10");
        this.properties.setProperty(DEFAULT_CAMERA_NAME, "DefaultCamera");
        this.properties.setProperty(DEFAULT_VECTOR_POSITION, "0.0,0.0,10.0");
        this.properties.setProperty(DEFAULT_VECTOR_DIRECTION, "0.0,0.0,-1.0");
        this.properties.setProperty(DEFAULT_VECTOR_UP, "0.0,1.0,0.0");
        this.properties.setProperty(DEFAULT_EXPORT_PATH, userPath+"/eCharacter/export");
        this.properties.setProperty(DEFAULT_TEMP_PATH, userPath+"/eCharacter/temp");
        
        //Saves the changes in the .properties file
        try {            
            FileOutputStream output = new FileOutputStream(PROPERTIES_FILE_NAME); 
            this.properties.store(output,null);
            output.close();
        } catch (IOException ex) {
            Logger.getLogger(Configuration.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
