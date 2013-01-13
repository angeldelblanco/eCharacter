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
                    String [] arraySkinIcon = new String[numSkins];

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
                            
                            nValue = attributes.getNamedItem("pathIcon");
                            String pathIconSkinReaded = nValue.getNodeValue();
                            //Print the data readed
                            System.out.println("Skin "+indexSkinReaded+": " + pathIconSkinReaded);
                            //Save the path of this skin's icon
                            arraySkinIcon[indexSkinReaded] = pathIconSkinReaded;
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
                            //Print the data readed
                            System.out.println("Eyes "+indexEyesReaded+": " + pathEyesReaded);
                            //Save the path of these eyes
                            arrayEyes[indexEyesReaded] = pathEyesReaded;
                            
                            nValue = attributes.getNamedItem("pathIcon");
                            String pathIconEyesReaded = nValue.getNodeValue();
                            //Print the data readed
                            System.out.println("Eyes "+indexEyesReaded+": " + pathIconEyesReaded);           
                            //Save the path of these eyes
                            arrayEyesIcon[indexEyesReaded] = pathIconEyesReaded;
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
                            //Print the data readed
                            System.out.println("TShirt "+indexTShirtReaded+": " + pathTShirtColorReaded);
                            //Save the path of this tshirt
                            arrayTShirtColor[indexTShirtReaded] = pathTShirtColorReaded;

                            nValue = attributes.getNamedItem("pathShadow");
                            String pathTShirtShadowReaded = nValue.getNodeValue();
                            //Print the data readed
                            System.out.println("TShirt "+indexTShirtReaded+": " + pathTShirtShadowReaded);
                            //Save the path of this tshirt
                            arrayTShirtShadow[indexTShirtReaded] = pathTShirtShadowReaded;
                            
                            nValue = attributes.getNamedItem("pathIcon");
                            String pathIconTShirtReaded = nValue.getNodeValue();
                            //Print the data readed
                            System.out.println("TShirt "+indexTShirtReaded+": " + pathIconTShirtReaded);
                            //Save the path of this tshirt
                            arrayTShirtIcon[indexTShirtReaded] = pathIconTShirtReaded;
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
                            //Print the data readed
                            System.out.println("Trouser "+indexTrouserReaded+": " + pathTrouserColorReaded);
                            //Save the path of this trouser
                            arrayTrouserColor[indexTrouserReaded] = pathTrouserColorReaded;

                            nValue = attributes.getNamedItem("pathShadow");
                            String pathTrouserShadowReaded = nValue.getNodeValue();
                            //Print the data readed
                            System.out.println("Trouser "+indexTrouserReaded+": " + pathTrouserShadowReaded);
                            //Save the path of this trouser
                            arrayTrouserShadow[indexTrouserReaded] = pathTrouserShadowReaded;
                            
                            nValue = attributes.getNamedItem("pathIcon");
                            String pathIconTrouserReaded = nValue.getNodeValue();
                            //Print the data readed
                            System.out.println("Trouser "+indexTrouserReaded+": " + pathIconTrouserReaded);
                            //Save the path of this trouser
                            arrayTrouserIcon[indexTrouserReaded] = pathIconTrouserReaded;
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
                            //Print the data readed
                            System.out.println("Shoes "+indexShoesReaded+": " + pathShoesColorReaded);
                            //Save the path of this shoe
                            arrayShoesColor[indexShoesReaded] = pathShoesColorReaded;

                            nValue = attributes.getNamedItem("pathShadow");
                            String pathShoesShadowReaded = nValue.getNodeValue();
                            //Print the data readed
                            System.out.println("Shoes "+indexShoesReaded+": " + pathShoesShadowReaded);
                            //Save the path of this shoe
                            arrayShoesShadow[indexShoesReaded] = pathShoesShadowReaded;
                            
                            nValue = attributes.getNamedItem("pathIcon");
                            String pathIconShoesReaded = nValue.getNodeValue();
                            //Print the data readed
                            System.out.println("Shoes "+indexShoesReaded+": " + pathIconShoesReaded);
                            //Save the path of this shoe
                            arrayShoesIcon[indexShoesReaded] = pathIconShoesReaded;
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