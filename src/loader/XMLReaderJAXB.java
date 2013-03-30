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

import data.family.Family;
import data.model.Model;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.bind.*;
import types.XMLType;

public class XMLReaderJAXB<T> 
{
    private ArrayList<T> list;
    private String path;
    private ResourceHandler resourceHandler;
    private XMLValidator xmlValidator;
    
    public XMLReaderJAXB(String path)
    {
        this.path = path;

        list = new ArrayList<T>();
        resourceHandler = new ResourceHandler();
        xmlValidator = new XMLValidator();
        //readFamiliesPath(path);
    }
    
    public ArrayList<T> readXML(Class<T> docClass)
    {
        /** Check the type of the XML */
        XMLType type = null;
        if (docClass.equals(Family.class))
        {
            type = XMLType.family;
        }
        else if (docClass.equals(Model.class))
        {
            type = XMLType.model;
        }
        File dirPath = new File(path);
        File[] ficheros = dirPath.listFiles();
        for (int x=0;x<ficheros.length;x++)
        {
            File file = ficheros[x];
            /** Check if the file is a file, the extension is "xml" and validate the XML with the XSD */
            if (! file.isDirectory() && getExtension(file.getPath()).equals("xml") && xmlValidator.checkXML(file.getPath(), type)) {
                try {    
                    list.add(unmarshal(docClass, resourceHandler.getResource(file.getPath())));
                } catch (JAXBException ex) {
                    Logger.getLogger(XMLReaderJAXB.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        return list;
    }
    
    private <T> T unmarshal( Class<T> docClass, InputStream inputStream )throws JAXBException 
    {
        String packageName = docClass.getPackage().getName();
        JAXBContext jc = JAXBContext.newInstance( packageName );
        Unmarshaller u = jc.createUnmarshaller();
        T doc = (T)u.unmarshal( inputStream );
        return doc;
    } 
    
    private String getExtension(String filePath)
    {
        int dot = filePath.lastIndexOf(".");
        return filePath.substring(dot + 1);
    }
    
    public static void main(String args[]) throws JAXBException, FileNotFoundException
    {
        XMLReaderJAXB<Family> aux = new XMLReaderJAXB<Family>("assets/XML Configuration/families");
        ArrayList<Family> families = aux.readXML(Family.class);
        Iterator<Family> it = families.iterator();
        while(it.hasNext())
        {
            Family fam = it.next();
            System.out.println(fam.getMetadata().getName());
            System.out.println(fam.getMetadata().getDescription());
        }
    }   
}