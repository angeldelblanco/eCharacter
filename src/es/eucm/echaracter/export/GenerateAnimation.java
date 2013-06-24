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

package es.eucm.echaracter.export;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Iterator;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

public final class GenerateAnimation {
    
    public static void createAnimation(String folderPath, String nameAnimationToSave, ArrayList<String> listAnimationsName, ZIPWritter zipWritter){
        String fileEaaPath = folderPath + File.separator + nameAnimationToSave + ".eaa";
        try{
            DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance( );
            TransformerFactory tf = TransformerFactory.newInstance( );
            DocumentBuilder db = dbf.newDocumentBuilder( );
            Document doc = db.newDocument( );
            OutputStream fout = null;
            //Creat the main node
            Element mainNode = doc.createElement( "animation");
            mainNode.setAttribute("id", nameAnimationToSave);
            mainNode.setAttribute("usetransitions", "no");
            mainNode.setAttribute("slides", "no");

            Iterator<String> it = listAnimationsName.iterator();      
            while (it.hasNext()){                
                //Creat the node "transition"
                Element transitionNode = doc.createElement("transition");
                transitionNode.setAttribute("type", "none");
                transitionNode.setAttribute("time", "0");
                //Creat the node "frame"
                Element frameNode = doc.createElement("frame");
                frameNode.setAttribute("maxSoundTime", "1000");
                frameNode.setAttribute("soundUri", "");
                frameNode.setAttribute("time", "150");
                frameNode.setAttribute("type", "image");
                frameNode.setAttribute("uri", "assets/animation/" + it.next());
                frameNode.setAttribute("waitforclick", "no");
                //Add de nodes to main node
                mainNode.appendChild(transitionNode);
                mainNode.appendChild(frameNode);
            }            
            doc.adoptNode(mainNode);
            doc.appendChild(mainNode);
            Transformer transformer = tf.newTransformer();
            transformer.setOutputProperty(OutputKeys.DOCTYPE_SYSTEM, "animation.dtd");
            try {
                fout = new FileOutputStream(fileEaaPath);
            }
            catch( FileNotFoundException e ) {
                System.out.println("error");
            }
            OutputStreamWriter writeFile = new OutputStreamWriter(fout, "UTF-8");
            transformer.transform(new DOMSource(doc), new StreamResult(writeFile));
            writeFile.close();
            fout.close();
            zipWritter.saveFile(fileEaaPath, nameAnimationToSave + ".eaa");
        }
        catch( Exception e ) {
            System.out.println("error");
        }
    }
    
    public static void cleanDirectory(String folder){        
        File f = new File(folder);
        if (f.exists()){
            clean(f);
        }
        else{
            f.mkdirs();
        }
    }
    
    private static void clean(File directorio){
         File[] ficheros = directorio.listFiles();
 
         for (int x=0;x<ficheros.length;x++){
             if (ficheros[x].isDirectory()) {
                clean(ficheros[x]);
             }
             ficheros[x].delete();
         }
    }
}