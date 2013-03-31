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
import java.io.IOException;
import javax.xml.XMLConstants;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.dom.DOMSource;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import javax.xml.validation.Validator;
import org.w3c.dom.Document;
import org.xml.sax.SAXException;
import types.XMLType;

public class XMLValidator {
    public boolean checkXML(String filePath, XMLType type)
    {
        try 
        {
            // crear y configurar la factory de parsers de documentos XML
            DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
            dbf.setNamespaceAware(true);  // activar soporte para namespaces
            
            // cargar el documento XML
            DocumentBuilder parser = dbf.newDocumentBuilder();
            Document doc = parser.parse(new File(filePath));
            
            // crear una SchemaFactory preparada para interpretar esquemas XML W3C
            SchemaFactory sf = SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);

            // cargar el esquema XSD
            Schema schema = null;
            if (type==XMLType.family)
            {
                schema = sf.newSchema(new File("assets/XML Configuration/family.xsd"));
            }
            else if (type==XMLType.model)
            {
                schema = sf.newSchema(new File("assets/XML Configuration/model.xsd"));
            }
            // crear el objeto validator, que será el responsable de validar el XML
            Validator validator = schema.newValidator();

            // validar el documento XML
            validator.validate(new DOMSource(doc));

            // si se llega a este punto, el documento es válido
            System.out.println("DOCUMENTO VÁLIDO");
            return true;
        }
        catch (SAXException e)
        {
            // esta excepción indica fallo de validación
            System.err.println("DOCUMENTO INVÁLIDO");
            return false;
        }
        catch (ParserConfigurationException e)
        {
            // errores en la configuración del parser
            return false;
        }
        catch (IOException e)
        {
            // errores de lectura
            return false;
        }
    }
}