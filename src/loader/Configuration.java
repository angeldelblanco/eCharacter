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
package loader;

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
public class Configuration 
{ 
    /** Properties file name */
    public final static String PROPERTIES_FILE_NAME = "initConfig.properties";
    
    /** Language */
    public final static String Language = "lang";
    
    /**List of available languages*/
    public final static String ListLanguage = "listLanguage";
 
    /** Resolution */
    public final static String Resolution = "resolution";
 
    /** Assets paths */
    public final static String AssetsPath = "assetsPath";
    
    /** Families path*/
    public final static String FamiliesPath = "familiesPath";    
    
    /** Locale path */
    public final static String LocalePath = "localePath";
    
    Properties properties;
    
    
    public Configuration()
    {
        properties = new Properties();
    }
    
    public String getProperty(String Key)
    {
        return this.properties.getProperty(Key); 
    }
    
    public void setProperty(String Key,String value)
    {
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
    
    public void loadPropertiesFile(InputStream input)
    {
        try {
            properties.load(input);
            input.close();
        } catch (IOException ex) {
            Logger.getLogger(Configuration.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void loadDefaultProperties()
    {
        //Default configuration
        this.properties.setProperty(Language, "en_UK");
        this.properties.setProperty(ListLanguage,"en_UK es_ES");
        this.properties.setProperty(Resolution, "1024x768");
        this.properties.setProperty(AssetsPath, "assets");
        this.properties.setProperty(FamiliesPath,"assets"+File.separator+"XML Configuration"+File.separator+"families");
        this.properties.setProperty(LocalePath,"assets"+File.separator+"Locale");
        //Saves the changes in the .properties file
        try {            
            FileOutputStream output = new FileOutputStream(PROPERTIES_FILE_NAME); 
            this.properties.store(output,null);
            output.close();
        } catch (IOException ex) {
            Logger.getLogger(Configuration.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public ArrayList<String> getListLanguagesAvailables()
    {
        ArrayList<String> listLanguages = new ArrayList<String>();
        StringTokenizer st = new StringTokenizer(getProperty(ListLanguage));
        while (st.hasMoreTokens())
        {
            listLanguages.add(st.nextToken());
        }
        return listLanguages;
    }
    
    /*public static void main(String[] args)
    {
        ResourceHandler resourceHandler = new ResourceHandler();
        Configuration config = new Configuration();
        InputStream stream = resourceHandler.getResource(Configuration.PROPERTIES_FILE_NAME,"."+File.separator);
        if(stream != null){
            config.loadPropertiesFile(stream);
        }
        else{
            config.loadDefaultProperties();
        }
        String path = config.getProperty(Configuration.FamiliesPath);
        System.out.println(path);
        File dirPath = new File(path);
        File[] ficheros = dirPath.listFiles();
        for (int x=0;x<ficheros.length;x++)
        {
            File file = ficheros[x];
            /** Check if the file is a file, the extension is "xml" and validate the XML with the XSD */
            /*System.out.println(file.getName());
        }
        
    }*/
}
