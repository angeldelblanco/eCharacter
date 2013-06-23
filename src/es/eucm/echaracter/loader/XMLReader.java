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

import es.eucm.echaracter.data.family.Family;
import es.eucm.echaracter.data.model.Model;
import es.eucm.echaracter.i18n.Metadata;
import es.eucm.echaracter.repository.data.Repository;
import es.eucm.echaracter.types.XMLType;
import java.io.File;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.bind.*;

public class XMLReader<T> {
    private ArrayList<T> list;
    private String path;
    private XMLValidator xmlValidator;
    
    public XMLReader(String path){
        this.path = path;

        list = new ArrayList<T>();
        xmlValidator = new XMLValidator();
    }
    
    public ArrayList<T> readXML(Class<T> docClass){
        /** Check the type of the XML */
        XMLType type = null;
        if (docClass.equals(Family.class)){
            type = XMLType.family;
        }
        else if (docClass.equals(Model.class)){
            type = XMLType.model;
        }
        else if (docClass.equals(Metadata.class)){
            type = XMLType.language;
        }
        else if (docClass.equals(Repository.class)){
            type = XMLType.repository;
        }
        File dirPath = new File(path);
        if (dirPath.isDirectory()){
            File[] ficheros = dirPath.listFiles();
            for (int x=0;x<ficheros.length;x++){
                File file = ficheros[x];
                /** Check if the file is a file, the extension is "xml" and validate the XML with the XSD */
                if (! file.isDirectory() && getExtension(file.getPath()).equals("xml") && xmlValidator.checkXML(file.getPath(), type)) {
                    try {    
                        list.add(unmarshal(docClass, ResourceLocator.getResource(file.getPath())));
                    } catch (JAXBException ex) {
                        Logger.getLogger(XMLReader.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            }
        }
        else{
            if (getExtension(dirPath.getPath()).equals("xml") && xmlValidator.checkXML(dirPath.getPath(), type)) {
                try {    
                        list.add(unmarshal(docClass, ResourceLocator.getResource(dirPath.getPath())));
                    } catch (JAXBException ex) {
                        Logger.getLogger(XMLReader.class.getName()).log(Level.SEVERE, null, ex);
                    }
            }
        }
        return list;
    }
    
    private <T> T unmarshal( Class<T> docClass, InputStream inputStream )throws JAXBException {
        String packageName = docClass.getPackage().getName();
        JAXBContext jc = JAXBContext.newInstance( packageName );
        Unmarshaller u = jc.createUnmarshaller();
        T doc = (T)u.unmarshal( inputStream );
        return doc;
    } 
    
    private String getExtension(String filePath){
        int dot = filePath.lastIndexOf(".");
        return filePath.substring(dot + 1);
    }
}