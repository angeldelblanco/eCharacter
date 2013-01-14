/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package XML;

import java.io.File;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

/**
 *
 * @author Sergio
 */
public class Leer {
    public static void main(String argv[]) { 
        try {
            //Assing the file to the DOM doc.
            File xmlFile = new File("assets/XML Configuration/configuration.xml");
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(xmlFile);
            doc.getDocumentElement().normalize();
            
            //Read the doc.
            //Read all nodes with name man
            NodeList nListMan = doc.getElementsByTagName("man");
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
                    int numSkin;
                    for (numSkin = 0; numSkin < nListSkins.getLength(); numSkin++){
                        Node nNode3 = nListSkins.item(numSkin);    
                        if (nNode3.getNodeType() == Node.ELEMENT_NODE) {
                            NamedNodeMap attributes = nNode3.getAttributes();
                            Node nValue = attributes.getNamedItem("path");
                            String pathSkinReaded = nValue.getNodeValue();
                            //Print the data readed
                            System.out.println("Skin "+numSkin+": " + pathSkinReaded);
                            
                            nValue = attributes.getNamedItem("pathIcon");
                            String pathIconSkinReaded = nValue.getNodeValue();
                            //Print the data readed
                            System.out.println("Skin "+numSkin+": " + pathIconSkinReaded);
                        }
                    }
                    NodeList nListEyes = eElement2.getElementsByTagName("eyes");
                    int numEyes;
                    for (numEyes = 0; numEyes < nListEyes.getLength(); numEyes++){
                        Node nNode3 = nListEyes.item(numEyes);
                        if (nNode3.getNodeType() == Node.ELEMENT_NODE) {
                            NamedNodeMap attributes = nNode3.getAttributes();
                            Node nValue = attributes.getNamedItem("path");
                            String pathEyesReaded = nValue.getNodeValue();
                            //Print the data readed
                            System.out.println("Eyes "+numEyes+": " + pathEyesReaded);
                            
                            nValue = attributes.getNamedItem("pathIcon");
                            String pathIconEyesReaded = nValue.getNodeValue();
                            //Print the data readed
                            System.out.println("Eyes "+numEyes+": " + pathIconEyesReaded);
                        }
                    }
                    NodeList nListTShirts = eElement2.getElementsByTagName("tshirt");
                    int numTShirt;
                    for (numTShirt = 0; numTShirt < nListTShirts.getLength(); numTShirt++){
                        Node nNode3 = nListTShirts.item(numTShirt);
                        if (nNode3.getNodeType() == Node.ELEMENT_NODE) {
                            NamedNodeMap attributes = nNode3.getAttributes();
                            Node nValue = attributes.getNamedItem("pathColor");
                            String pathTShirtColorReaded = nValue.getNodeValue();
                            //Print the data readed
                            System.out.println("TShirt "+numTShirt+": " + pathTShirtColorReaded);
                            
                            nValue = attributes.getNamedItem("pathShadow");
                            String pathTShirtShadowReaded = nValue.getNodeValue();
                            //Print the data readed
                            System.out.println("TShirt "+numTShirt+": " + pathTShirtShadowReaded);
                            
                            nValue = attributes.getNamedItem("pathIcon");
                            String pathIconTShirtReaded = nValue.getNodeValue();
                            //Print the data readed
                            System.out.println("TShirt "+numTShirt+": " + pathIconTShirtReaded);
                        }
                    }
                    NodeList nListTrousers = eElement2.getElementsByTagName("trouser");
                    int numTrouser;
                    for (numTrouser = 0; numTrouser < nListTrousers.getLength(); numTrouser++){
                        Node nNode3 = nListTrousers.item(numTrouser);
                        if (nNode3.getNodeType() == Node.ELEMENT_NODE) {
                            NamedNodeMap attributes = nNode3.getAttributes();
                            Node nValue = attributes.getNamedItem("pathColor");
                            String pathTrouserColorReaded = nValue.getNodeValue();
                            //Print the data readed
                            System.out.println("Trouser "+numTrouser+": " + pathTrouserColorReaded);
                            
                            nValue = attributes.getNamedItem("pathShadow");
                            String pathTrouserShadowReaded = nValue.getNodeValue();
                            //Print the data readed
                            System.out.println("Trouser "+numTrouser+": " + pathTrouserShadowReaded);
                            
                            nValue = attributes.getNamedItem("pathIcon");
                            String pathIconTrouserReaded = nValue.getNodeValue();
                            //Print the data readed
                            System.out.println("Trouser "+numTrouser+": " + pathIconTrouserReaded);
                        }
                    }
                    NodeList nListShoes = eElement2.getElementsByTagName("shoes");
                    int numShoes;
                    for (numShoes = 0; numShoes < nListShoes.getLength(); numShoes++){
                        Node nNode3 = nListShoes.item(numShoes);
                        if (nNode3.getNodeType() == Node.ELEMENT_NODE) {
                            NamedNodeMap attributes = nNode3.getAttributes();
                            Node nValue = attributes.getNamedItem("pathColor");
                            String pathShoesColorReaded = nValue.getNodeValue();
                            //Print the data readed
                            System.out.println("Shoes "+numShoes+": " + pathShoesColorReaded);
                            
                            nValue = attributes.getNamedItem("pathShadow");
                            String pathShoesShadowReaded = nValue.getNodeValue();
                            //Print the data readed
                            System.out.println("Shoes "+numShoes+": " + pathShoesShadowReaded);
                            
                            nValue = attributes.getNamedItem("pathIcon");
                            String pathIconShoesReaded = nValue.getNodeValue();
                            //Print the data readed
                            System.out.println("Shoes "+numShoes+": " + pathIconShoesReaded);
                        }
                    }
                  }
                  NodeList nListAnimations = eElement.getElementsByTagName("model");

                    int indexAnimationReaded;
                    for (indexAnimationReaded = 0; indexAnimationReaded < nListAnimations.getLength(); indexAnimationReaded++){
                          Node nNode3 = nListAnimations.item(indexAnimationReaded);
                          if (nNode3.getNodeType() == Node.ELEMENT_NODE) {
                              NamedNodeMap attributes = nNode3.getAttributes();
                              Node nValue = attributes.getNamedItem("path");
                              String pathAnimationReaded = nValue.getNodeValue();
                              //Print the data readed
                              System.out.println("Model "+indexAnimationReaded+": " + pathAnimationReaded);
                              //Save the path of this 
                          }
                    }
               }
            }
        } catch (Exception e) {
              e.printStackTrace();
        }
    }
    
    private boolean pathOK (String path){
        File file = new File(path);
        if (file.exists())
            return true;
        else
            return false;
    }
}