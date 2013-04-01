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
package main;

import gui.Gui;
import java.io.File;
import java.io.InputStream;
import java.util.StringTokenizer;
import loader.Configuration;
import loader.ResourceHandler;

/**
 * This class initializes the application resources and launch the GUI
 */
public class Main 
{            
    public static void main(String[] args)
    {
        //Initializes the resources and load the initial configuration
        ResourceHandler resourceHandler = new ResourceHandler();
        Configuration config = new Configuration();
        InputStream stream = resourceHandler.getResource(Configuration.PROPERTIES_FILE_NAME,"."+File.separator);
        if(stream != null){
            config.loadPropertiesFile(stream);
        }
        else{
            config.loadDefaultProperties();
        }
        //Setting the GUI´s initial configuration
        StringTokenizer dimension = new StringTokenizer(config.getProperty(Configuration.Resolution));
        int width = Integer.parseInt(dimension.nextToken("x"));
        int height = Integer.parseInt(dimension.nextToken());
        Gui app = new Gui(width,height,config);
        //Launch the GUI
        app.start();
    }
    
}
