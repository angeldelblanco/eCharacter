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
package XML;

import applicationdata.family.*;
import applicationdata.model.*;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.bind.*;

public class XMLReaderJAXB<T> 
{
    private ArrayList<T> list;
    private String path;
    
    public XMLReaderJAXB(String path)
    {
        this.path = path;

        list = new ArrayList<T>();
        //readFamiliesPath(path);
    }
    
    public ArrayList<T> readXML(Class<T> docClass)
    {
        File dirPath = new File(path);
        /*String[] list = dirPath.list();
        for(int i=0; i<list.length;i++)
        {
            try 
            {
                String filePath = path+"/"+list[i];
                InputStream input = new FileInputStream(filePath);
                families.add(unmarshal(Family.class,input));
            } 
            catch (Exception ex) 
            {
                Logger.getLogger(XMLReaderJAXB.class.getName()).log(Level.SEVERE, null, ex);
            }
        }*/
        File[] ficheros = dirPath.listFiles();
        for (int x=0;x<ficheros.length;x++)
        {
            File file = ficheros[x];
            //Hay que hacer más comprobaciones(que no sea model.xsd y q sean XML)
            if (! file.isDirectory() && ! file.getName().equals("family.xsd")) {
                System.out.println(file.getPath());
                try {
                    try {
                        
                        list.add(unmarshal(docClass, new FileInputStream(file.getPath())));
                    } catch (FileNotFoundException ex) {
                        Logger.getLogger(XMLReaderJAXB.class.getName()).log(Level.SEVERE, null, ex);
                    }
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
        //JAXBElement<T> doc = (JAXBElement<T>)u.unmarshal( inputStream );
        T doc = (T)u.unmarshal( inputStream );
        return doc;
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