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

                    int indexSkinReaded;
                    for (indexSkinReaded = 0; indexSkinReaded < nListSkins.getLength(); indexSkinReaded++){
                        Node nNode3 = nListSkins.item(indexSkinReaded);
                        if (nNode3.getNodeType() == Node.ELEMENT_NODE) {
                            NamedNodeMap attributes = nNode3.getAttributes();
                            Node nValue = attributes.getNamedItem("path");
                            String pathSkinReaded = nValue.getNodeValue();
                            //Print the data readed
                            System.out.println("Skin "+indexSkinReaded+": " + pathSkinReaded);
                            //Save the path of this skin
                            arraySkin[indexSkinReaded] = pathSkinReaded;
                        }
                    }
                    model.setNumSkins(numSkins);
                    model.setArraySkin(arraySkin);

                    NodeList nListEyes = eElement2.getElementsByTagName("eyes");

                    int numEyes = nListEyes.getLength();
                    String [] arrayEyes = new String[numEyes];

                    int indexEyesReaded;
                    for (indexEyesReaded = 0; indexEyesReaded < nListEyes.getLength(); indexEyesReaded++){
                        Node nNode4 = nListEyes.item(indexEyesReaded);
                        if (nNode4.getNodeType() == Node.ELEMENT_NODE) {
                            NamedNodeMap attributes2 = nNode4.getAttributes();
                            Node nValue2 = attributes2.getNamedItem("path");
                            String pathEyesReaded = nValue2.getNodeValue();
                            //Print the data readed
                            System.out.println("Eyes "+indexEyesReaded+": " + pathEyesReaded);
                            //Save the path of these eyes
                            arrayEyes[indexEyesReaded] = pathEyesReaded;
                        }
                    }
                    model.setNumEyes(numEyes);
                    model.setArrayEyes(arrayEyes);

                    NodeList nListTShirts = eElement2.getElementsByTagName("tshirt");

                    int numTShirts = nListTShirts.getLength();
                    String [] arrayTShirtColor = new String[numTShirts];
                    String [] arrayTShirtShadow = new String[numTShirts];

                    int indexTShirtReaded;
                    for (indexTShirtReaded = 0; indexTShirtReaded < nListTShirts.getLength(); indexTShirtReaded++){
                        Node nNode5 = nListTShirts.item(indexTShirtReaded);
                        if (nNode5.getNodeType() == Node.ELEMENT_NODE) {
                            NamedNodeMap attributes3 = nNode5.getAttributes();
                            Node nValue3 = attributes3.getNamedItem("pathColor");
                            String pathTShirtColorReaded = nValue3.getNodeValue();
                            //Print the data readed
                            System.out.println("TShirt "+indexTShirtReaded+": " + pathTShirtColorReaded);
                            //Save the path of this tshirt
                            arrayTShirtColor[indexTShirtReaded] = pathTShirtColorReaded;

                            NamedNodeMap attributes4 = nNode5.getAttributes();
                            Node nValue4 = attributes4.getNamedItem("pathShadow");
                            String pathTShirtShadowReaded = nValue4.getNodeValue();
                            //Print the data readed
                            System.out.println("TShirt "+indexTShirtReaded+": " + pathTShirtShadowReaded);
                            //Save the path of this tshirt
                            arrayTShirtShadow[indexTShirtReaded] = pathTShirtShadowReaded;
                        }
                    }
                    model.setNumTShirts(numSkins);
                    model.setArrayTShirtColor(arrayTShirtColor);
                    model.setArrayTShirtShadow(arrayTShirtShadow);

                    NodeList nListTrousers = eElement2.getElementsByTagName("trouser");

                    int numTrousers = nListTrousers.getLength();
                    String [] arrayTrouserColor = new String[numTrousers];
                    String [] arrayTrouserShadow = new String[numTrousers];

                    int indexTrouserReaded;
                    for (indexTrouserReaded = 0; indexTrouserReaded < nListTrousers.getLength(); indexTrouserReaded++){
                        Node nNode6 = nListTrousers.item(indexTrouserReaded);
                        if (nNode6.getNodeType() == Node.ELEMENT_NODE) {
                            NamedNodeMap attributes5 = nNode6.getAttributes();
                            Node nValue5 = attributes5.getNamedItem("pathColor");
                            String pathTrouserColorReaded = nValue5.getNodeValue();
                            //Print the data readed
                            System.out.println("Trouser "+indexTrouserReaded+": " + pathTrouserColorReaded);
                            //Save the path of this trouser
                            arrayTrouserColor[indexTrouserReaded] = pathTrouserColorReaded;

                            NamedNodeMap attributes6 = nNode6.getAttributes();
                            Node nValue6 = attributes6.getNamedItem("pathShadow");
                            String pathTrouserShadowReaded = nValue6.getNodeValue();
                            //Print the data readed
                            System.out.println("Trouser "+indexTrouserReaded+": " + pathTrouserShadowReaded);
                            //Save the path of this tshirt
                            arrayTrouserShadow[indexTrouserReaded] = pathTrouserShadowReaded;
                        }
                    }
                    model.setNumTrousers(numTrousers);
                    model.setArrayTrouserColor(arrayTrouserColor);
                    model.setArrayTrouserShadow(arrayTrouserShadow);

                    NodeList nListShoes = eElement2.getElementsByTagName("shoes");

                    int numShoes = nListShoes.getLength();
                    String [] arrayShoesColor = new String[numShoes];
                    String [] arrayShoesShadow = new String[numShoes];

                    int indexShoesReaded;
                    for (indexShoesReaded = 0; indexShoesReaded < nListShoes.getLength(); indexShoesReaded++){
                        Node nNode7 = nListShoes.item(indexShoesReaded);
                        if (nNode7.getNodeType() == Node.ELEMENT_NODE) {
                            NamedNodeMap attributes7 = nNode7.getAttributes();
                            Node nValue7 = attributes7.getNamedItem("pathColor");
                            String pathShoesColorReaded = nValue7.getNodeValue();
                            //Print the data readed
                            System.out.println("Shoes "+indexShoesReaded+": " + pathShoesColorReaded);
                            //Save the path of this trouser
                            arrayShoesColor[indexShoesReaded] = pathShoesColorReaded;

                            NamedNodeMap attributes8 = nNode7.getAttributes();
                            Node nValue8 = attributes8.getNamedItem("pathShadow");
                            String pathShoesShadowReaded = nValue8.getNodeValue();
                            //Print the data readed
                            System.out.println("Shoes "+indexShoesReaded+": " + pathShoesShadowReaded);
                            //Save the path of this tshirt
                            arrayShoesShadow[indexShoesReaded] = pathShoesShadowReaded;
                        }
                    }
                    model.setNumShoes(numShoes);
                    model.setArrayShoesColor(arrayShoesColor);
                    model.setArrayShoesShadow(arrayShoesShadow);
                  }
                  NodeList nListAnimations = eElement.getElementsByTagName("model");        
                  Node nNode8 = nListAnimations.item(0);
                    if (nNode8.getNodeType() == Node.ELEMENT_NODE) {
                        NamedNodeMap attributes9 = nNode8.getAttributes();
                        Node nValue9 = attributes9.getNamedItem("path");
                        String pathAnimationReaded = nValue9.getNodeValue();
                        //Print the data readed
                        System.out.println("Animation : " + pathAnimationReaded);
                        //Save the path of this skin
                        String modelPath = pathAnimationReaded;
                        model.setModelPath(modelPath);
                    }                      
               }
            }
        } catch (Exception e) {
              e.printStackTrace();
        }
    }
}