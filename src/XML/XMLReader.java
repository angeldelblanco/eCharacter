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

import Model.Model;
import java.io.File;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import types.Gender;

public class XMLReader {
    private Model model;
    private String file;
    private Gender gender;
    
    public XMLReader(Model model, String file, Gender gender)
    {
        this.model = model;
        this.file = file;
        this.gender = gender;
    }
    //This method read the XML configuration document.
    public void readXML()
    {
        if (pathOK(file)){
            String gender_word = "";
            switch(gender) {
                case Male:
                    gender_word = "man";
                    break;
                case Female:
                    gender_word = "woman";
                    break;
            }        
            try {
                //Assing the file to the DOM doc.
                File xmlFile = new File(file);
                DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
                DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
                Document doc = dBuilder.parse(xmlFile);
                doc.getDocumentElement().normalize();

                //Read the doc.
                //Read all nodes with name man or woman
                NodeList nListMan = doc.getElementsByTagName(gender_word);
                for (int temp = 0; temp < nListMan.getLength(); temp++) {
                   Node nNode = nListMan.item(temp); 
                   if (nNode.getNodeType() == Node.ELEMENT_NODE) {
                      //Here, we have the element man 
                      Element eElement = (Element) nNode;
                      NodeList nListObjects = eElement.getElementsByTagName("objects");
                      Node nNode2 = (Node) nListObjects.item(0);
                      if (nNode2.getNodeType() == Node.ELEMENT_NODE) {
                        //In eElement2, we have the node objects
                        Element eElement2 = (Element) nNode2;
                        NodeList nListSkins = eElement2.getElementsByTagName("skin");

                        int numSkins = nListSkins.getLength();
                        String [] arraySkin = new String[numSkins];
                        String [] arraySkinIcon = new String[numSkins];

                        int indexSkinReaded;
                        for (indexSkinReaded = 0; indexSkinReaded < nListSkins.getLength(); indexSkinReaded++){
                            Node nNode3 = nListSkins.item(indexSkinReaded);
                            if (nNode3.getNodeType() == Node.ELEMENT_NODE) {
                                NamedNodeMap attributes = nNode3.getAttributes();
                                Node nValue = attributes.getNamedItem("path");
                                String pathSkinReaded = nValue.getNodeValue();
                                if (pathOK(pathSkinReaded)){
                                    //Print the data readed
                                    System.out.println("Skin "+indexSkinReaded+": " + pathSkinReaded);
                                    //Save the path of this skin
                                    arraySkin[indexSkinReaded] = pathSkinReaded;
                                }
                                else{
                                    System.out.println("El siguiente path del skin es incorrecto: "+pathSkinReaded);
                                    return;
                                }
                                nValue = attributes.getNamedItem("pathIcon");
                                String pathIconSkinReaded = nValue.getNodeValue();
                                if (pathOK(pathIconSkinReaded)){
                                    //Print the data readed
                                    System.out.println("Skin "+indexSkinReaded+": " + pathIconSkinReaded);
                                    //Save the path of this skin's icon
                                    arraySkinIcon[indexSkinReaded] = pathIconSkinReaded;
                                }
                                else{
                                    System.out.println("El siguiente path del skin es incorrecto: "+pathIconSkinReaded);
                                    return;
                                }
                            }
                        }
                        model.setNumSkins(numSkins);
                        model.setArraySkin(arraySkin);
                        model.setArraySkinIcon(arraySkinIcon);

                        NodeList nListEyes = eElement2.getElementsByTagName("eyes");

                        int numEyes = nListEyes.getLength();
                        String [] arrayEyes = new String[numEyes];
                        String [] arrayEyesIcon = new String[numEyes];

                        int indexEyesReaded;
                        for (indexEyesReaded = 0; indexEyesReaded < nListEyes.getLength(); indexEyesReaded++){
                            Node nNode3 = nListEyes.item(indexEyesReaded);
                            if (nNode3.getNodeType() == Node.ELEMENT_NODE) {
                                NamedNodeMap attributes = nNode3.getAttributes();
                                Node nValue = attributes.getNamedItem("path");
                                String pathEyesReaded = nValue.getNodeValue();
                                if (pathOK(pathEyesReaded)){
                                    //Print the data readed
                                    System.out.println("Eyes "+indexEyesReaded+": " + pathEyesReaded);
                                    //Save the path of these eyes
                                    arrayEyes[indexEyesReaded] = pathEyesReaded;
                                }
                                else{
                                    System.out.println("El siguiente path de eyes es incorrecto: "+pathEyesReaded);
                                    return;
                                }

                                nValue = attributes.getNamedItem("pathIcon");
                                String pathIconEyesReaded = nValue.getNodeValue();
                                if (pathOK(pathIconEyesReaded)){
                                    //Print the data readed
                                    System.out.println("Eyes "+indexEyesReaded+": " + pathIconEyesReaded);           
                                    //Save the path of these eyes
                                    arrayEyesIcon[indexEyesReaded] = pathIconEyesReaded;
                                }
                                else{
                                    System.out.println("El siguiente path de eyes es incorrecto: "+pathIconEyesReaded);
                                    return;
                                }
                            }
                        }
                        model.setNumEyes(numEyes);
                        model.setArrayEyes(arrayEyes);
                        model.setArrayEyesIcon(arrayEyesIcon);

                        NodeList nListTShirts = eElement2.getElementsByTagName("tshirt");

                        int numTShirts = nListTShirts.getLength();
                        String [] arrayTShirtColor = new String[numTShirts];
                        String [] arrayTShirtShadow = new String[numTShirts];
                        String [] arrayTShirtIcon = new String[numTShirts];

                        int indexTShirtReaded;
                        for (indexTShirtReaded = 0; indexTShirtReaded < nListTShirts.getLength(); indexTShirtReaded++){
                            Node nNode3 = nListTShirts.item(indexTShirtReaded);
                            if (nNode3.getNodeType() == Node.ELEMENT_NODE) {
                                NamedNodeMap attributes = nNode3.getAttributes();
                                Node nValue = attributes.getNamedItem("pathColor");
                                String pathTShirtColorReaded = nValue.getNodeValue();
                                if (pathOK(pathTShirtColorReaded)){
                                    //Print the data readed
                                    System.out.println("TShirt "+indexTShirtReaded+": " + pathTShirtColorReaded);
                                    //Save the path of this tshirt
                                    arrayTShirtColor[indexTShirtReaded] = pathTShirtColorReaded;
                                }
                                else{
                                    System.out.println("El siguiente path de tshirt es incorrecto: "+pathTShirtColorReaded);
                                    return;
                                }
                                
                                nValue = attributes.getNamedItem("pathShadow");
                                String pathTShirtShadowReaded = nValue.getNodeValue();
                                if (pathOK(pathTShirtShadowReaded)){
                                    //Print the data readed
                                    System.out.println("TShirt "+indexTShirtReaded+": " + pathTShirtShadowReaded);
                                    //Save the path of this tshirt
                                    arrayTShirtShadow[indexTShirtReaded] = pathTShirtShadowReaded;
                                }
                                else{
                                    System.out.println("El siguiente path de tshirt es incorrecto: "+pathTShirtShadowReaded);
                                    return;
                                }
                                
                                nValue = attributes.getNamedItem("pathIcon");
                                String pathIconTShirtReaded = nValue.getNodeValue();
                                if (pathOK(pathIconTShirtReaded)){
                                    //Print the data readed
                                    System.out.println("TShirt "+indexTShirtReaded+": " + pathIconTShirtReaded);
                                    //Save the path of this tshirt
                                    arrayTShirtIcon[indexTShirtReaded] = pathIconTShirtReaded;
                                }
                                else{
                                    System.out.println("El siguiente path de tshirt es incorrecto: "+pathIconTShirtReaded);
                                    return;
                                }
                            }
                        }
                        model.setNumTShirts(numSkins);
                        model.setArrayTShirtColor(arrayTShirtColor);
                        model.setArrayTShirtShadow(arrayTShirtShadow);
                        model.setArrayTShirtIcon(arrayTShirtIcon);

                        NodeList nListTrousers = eElement2.getElementsByTagName("trouser");

                        int numTrousers = nListTrousers.getLength();
                        String [] arrayTrouserColor = new String[numTrousers];
                        String [] arrayTrouserShadow = new String[numTrousers];
                        String [] arrayTrouserIcon = new String[numTrousers];

                        int indexTrouserReaded;
                        for (indexTrouserReaded = 0; indexTrouserReaded < nListTrousers.getLength(); indexTrouserReaded++){
                            Node nNode3 = nListTrousers.item(indexTrouserReaded);
                            if (nNode3.getNodeType() == Node.ELEMENT_NODE) {
                                NamedNodeMap attributes = nNode3.getAttributes();
                                Node nValue = attributes.getNamedItem("pathColor");
                                String pathTrouserColorReaded = nValue.getNodeValue();
                                if (pathOK(pathTrouserColorReaded)){
                                    //Print the data readed
                                    System.out.println("Trouser "+indexTrouserReaded+": " + pathTrouserColorReaded);
                                    //Save the path of this trouser
                                    arrayTrouserColor[indexTrouserReaded] = pathTrouserColorReaded;
                                }
                                else{
                                    System.out.println("El siguiente path de trouser es incorrecto: "+pathTrouserColorReaded);
                                    return;
                                }
                                
                                nValue = attributes.getNamedItem("pathShadow");
                                String pathTrouserShadowReaded = nValue.getNodeValue();
                                if (pathOK(pathTrouserShadowReaded)){
                                    //Print the data readed
                                    System.out.println("Trouser "+indexTrouserReaded+": " + pathTrouserShadowReaded);
                                    //Save the path of this trouser
                                    arrayTrouserShadow[indexTrouserReaded] = pathTrouserShadowReaded;
                                }
                                else{
                                    System.out.println("El siguiente path de trouser es incorrecto: "+pathTrouserShadowReaded);
                                    return;
                                }
                                
                                nValue = attributes.getNamedItem("pathIcon");
                                String pathIconTrouserReaded = nValue.getNodeValue();
                                if (pathOK(pathIconTrouserReaded)){
                                    //Print the data readed
                                    System.out.println("Trouser "+indexTrouserReaded+": " + pathIconTrouserReaded);
                                    //Save the path of this trouser
                                    arrayTrouserIcon[indexTrouserReaded] = pathIconTrouserReaded;
                                }
                                else{
                                    System.out.println("El siguiente path de trouser es incorrecto: "+pathIconTrouserReaded);
                                    return;
                                }
                            }
                        }
                        model.setNumTrousers(numTrousers);
                        model.setArrayTrouserColor(arrayTrouserColor);
                        model.setArrayTrouserShadow(arrayTrouserShadow);
                        model.setArrayTrouserIcon(arrayTrouserIcon);

                        NodeList nListShoes = eElement2.getElementsByTagName("shoes");

                        int numShoes = nListShoes.getLength();
                        String [] arrayShoesColor = new String[numShoes];
                        String [] arrayShoesShadow = new String[numShoes];
                        String [] arrayShoesIcon = new String[numShoes];

                        int indexShoesReaded;
                        for (indexShoesReaded = 0; indexShoesReaded < nListShoes.getLength(); indexShoesReaded++){
                            Node nNode3 = nListShoes.item(indexShoesReaded);
                            if (nNode3.getNodeType() == Node.ELEMENT_NODE) {
                                NamedNodeMap attributes = nNode3.getAttributes();
                                Node nValue = attributes.getNamedItem("pathColor");
                                String pathShoesColorReaded = nValue.getNodeValue();
                                if (pathOK(pathShoesColorReaded)){
                                    //Print the data readed
                                    System.out.println("Shoes "+indexShoesReaded+": " + pathShoesColorReaded);
                                    //Save the path of this shoe
                                    arrayShoesColor[indexShoesReaded] = pathShoesColorReaded;
                                }
                                else{
                                    System.out.println("El siguiente path de shoes es incorrecto: "+pathShoesColorReaded);
                                    return;
                                }

                                nValue = attributes.getNamedItem("pathShadow");
                                String pathShoesShadowReaded = nValue.getNodeValue();
                                if (pathOK(pathShoesShadowReaded)){
                                    //Print the data readed
                                    System.out.println("Shoes "+indexShoesReaded+": " + pathShoesShadowReaded);
                                    //Save the path of this shoe
                                    arrayShoesShadow[indexShoesReaded] = pathShoesShadowReaded;
                                }
                                else{
                                    System.out.println("El siguiente path de shoes es incorrecto: "+pathShoesShadowReaded);
                                    return;
                                }

                                nValue = attributes.getNamedItem("pathIcon");
                                String pathIconShoesReaded = nValue.getNodeValue();
                                if (pathOK(pathIconShoesReaded)){
                                    //Print the data readed
                                    System.out.println("Shoes "+indexShoesReaded+": " + pathIconShoesReaded);
                                    //Save the path of this shoe
                                    arrayShoesIcon[indexShoesReaded] = pathIconShoesReaded;
                                }
                                else{
                                    System.out.println("El siguiente path de shoes es incorrecto: "+pathIconShoesReaded);
                                    return;
                                }
                            }
                        }
                        model.setNumShoes(numShoes);
                        model.setArrayShoesColor(arrayShoesColor);
                        model.setArrayShoesShadow(arrayShoesShadow);
                        model.setArrayShoesIcon(arrayShoesIcon);
                      }
                      NodeList nListAnimations = eElement.getElementsByTagName("model");        
                      Node nNode3 = nListAnimations.item(0);
                        if (nNode3.getNodeType() == Node.ELEMENT_NODE) {
                            NamedNodeMap attributes = nNode3.getAttributes();
                            Node nValue = attributes.getNamedItem("path");
                            String pathAnimationReaded = nValue.getNodeValue();
                            if (pathOK(pathAnimationReaded)){
                                //Print the data readed
                                System.out.println("Animation : " + pathAnimationReaded);
                                //Save the path of this skin
                                String modelPath = pathAnimationReaded;
                                model.setModelPath(modelPath);
                            }
                            else{
                                    System.out.println("El siguiente path de model es incorrecto: "+pathAnimationReaded);
                                    return;
                                }
                        }                      
                   }
                }
            } catch (Exception e) {
                  e.printStackTrace();
            }
        }
        else {
            System.out.println("Ruta del cichero XML incorrecta");
        }
    }
    
    private boolean pathOK (String path){
        File fileReaded = new File(path);
        if (fileReaded.exists()){
            return true;
        }
        else{
            return false;
        }
    }
}