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

package es.eucm.echaracter.i18n;

import es.eucm.echaracter.loader.XMLReader;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.StringTokenizer;

public class I18N {
    private Metadata language;
    private static final String defaultLanguage = "en_UK";
    
    public I18N(String languagesPath, String language){
        XMLReader<Metadata> xmlReader = new XMLReader<Metadata>(languagesPath);
        ArrayList<Metadata> listLanguages = xmlReader.readXML(Metadata.class);
        Iterator<Metadata> it = listLanguages.iterator();
        boolean found = false;
        while(it.hasNext()){
            Metadata aux = it.next();
            
            String readedLanguage = aux.getLanguage();
            StringTokenizer tokens=new StringTokenizer(readedLanguage, "_");
            String prefixLanguage = null;
            while (tokens.hasMoreTokens()){
                prefixLanguage = tokens.nextToken(); 
                break;
            }
            
            
            
            if (aux.getLanguage().equals(language) || prefixLanguage.equals(language)){
                this.language = aux;
                found = true;
            }
        }
        //language not found. Search defaultLanguage file.
        if(!found){
            it = listLanguages.iterator();
            boolean found2 = false;
            while(it.hasNext()){
                Metadata aux = it.next();
                if (aux.getLanguage().equals(defaultLanguage)){
                    this.language = aux;
                    found2 = true;
                }
            }
            //defaultLanguage nor found. Search the first language.
            if (!found2){
                if (listLanguages.size()>0){
                    this.language = listLanguages.get(0);
                }
            }
        }
    }
    
    public String getString(String id){
        ArrayList<StringType> listString = (ArrayList<StringType>) language.getString();
        Iterator<StringType> it = listString.iterator();
        while(it.hasNext()){
            StringType string = it.next();
            if(string.getId().equals(id)){
                return string.getValue();
            }
        }
        return null;
    }
    
    public static boolean isLanguage(String languagesPath, String language){
        XMLReader<Metadata> xmlReader = new XMLReader<Metadata>(languagesPath);
        ArrayList<Metadata> listLanguages = xmlReader.readXML(Metadata.class);
        Iterator<Metadata> it = listLanguages.iterator();
        while(it.hasNext()){
            Metadata aux = it.next();
            String readedLanguage = aux.getLanguage();
            StringTokenizer tokens=new StringTokenizer(readedLanguage, "_");
            String prefixLanguage = null;
            while (tokens.hasMoreTokens()){
                prefixLanguage = tokens.nextToken(); 
                break;
            }
            
            if (prefixLanguage.equals(language)){
                return true;
            }
            else{
                return false;
            }
        }
        return false;
    }
}