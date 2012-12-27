/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package animation;

import java.io.File;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
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
                            //In element3, we have the node skin
                            Element eElement3 = (Element) nNode3;
                            NodeList nListPathSkin = eElement3.getElementsByTagName("path").item(0).getChildNodes();
                            Node nValue = (Node) nListPathSkin.item(0);
                            String pathSkinReaded = nValue.getNodeValue();
                            //Print the data readed
                            System.out.println("Skin "+numSkin+": " + pathSkinReaded);
                        }
                    }
                    NodeList nListEyes = eElement2.getElementsByTagName("eyes");
                    int numEyes;
                    for (numEyes = 0; numEyes < nListEyes.getLength(); numEyes++){
                        Node nNode4 = nListEyes.item(numEyes);
                        if (nNode4.getNodeType() == Node.ELEMENT_NODE) {
                            //In element4, we have the node eyes
                            Element eElement4 = (Element) nNode4;
                            NodeList nListPathEyes = eElement4.getElementsByTagName("path").item(0).getChildNodes();
                            Node nValue2 = (Node) nListPathEyes.item(0);
                            String pathEyesReaded = nValue2.getNodeValue();
                            //Print the data readed
                            System.out.println("Eyes "+numEyes+": " + pathEyesReaded);
                        }
                    }
                    NodeList nListTShirts = eElement2.getElementsByTagName("tshirt");
                    int numTShirt;
                    for (numTShirt = 0; numTShirt < nListTShirts.getLength(); numTShirt++){
                        Node nNode5 = nListTShirts.item(numTShirt);
                        if (nNode5.getNodeType() == Node.ELEMENT_NODE) {
                            //In element5, we have the node tshirt
                            Element eElement5 = (Element) nNode5;
                            NodeList nListPathColorTshirt = eElement5.getElementsByTagName("pathColor").item(0).getChildNodes();
                            Node nValue3 = (Node) nListPathColorTshirt.item(0);
                            String pathTShirtColorReaded = nValue3.getNodeValue();
                            //Print the data readed
                            System.out.println("TShirt "+numTShirt+": " + pathTShirtColorReaded);
                            
                            NodeList nListPathShadowTshirt = eElement5.getElementsByTagName("pathShadow").item(0).getChildNodes();
                            Node nValue4 = (Node) nListPathShadowTshirt.item(0);
                            String pathTShirtShadowReaded = nValue4.getNodeValue();
                            //Print the data readed
                            System.out.println("TShirt "+numTShirt+": " + pathTShirtShadowReaded);
                        }
                    }
                    NodeList nListTrousers = eElement2.getElementsByTagName("trouser");
                    int numTrouser;
                    for (numTrouser = 0; numTrouser < nListTrousers.getLength(); numTrouser++){
                        Node nNode6 = nListTrousers.item(numTrouser);
                        if (nNode6.getNodeType() == Node.ELEMENT_NODE) {
                            //In element6, we have the node trouser
                            Element eElement6 = (Element) nNode6;
                            NodeList nListPathColorTrouser = eElement6.getElementsByTagName("pathColor").item(0).getChildNodes();
                            Node nValue5 = (Node) nListPathColorTrouser.item(0);
                            String pathTrouserColorReaded = nValue5.getNodeValue();
                            //Print the data readed
                            System.out.println("Trouser "+numTrouser+": " + pathTrouserColorReaded);
                            
                            NodeList nListPathShadowTrouser = eElement6.getElementsByTagName("pathShadow").item(0).getChildNodes();
                            Node nValue6 = (Node) nListPathShadowTrouser.item(0);
                            String pathTrouserShadowReaded = nValue6.getNodeValue();
                            //Print the data readed
                            System.out.println("Trouser "+numTrouser+": " + pathTrouserShadowReaded);
                        }
                    }
                    NodeList nListShoes = eElement2.getElementsByTagName("shoes");
                    int numShoes;
                    for (numShoes = 0; numShoes < nListShoes.getLength(); numShoes++){
                        Node nNode7 = nListShoes.item(numShoes);
                        if (nNode7.getNodeType() == Node.ELEMENT_NODE) {
                            //In element7, we have the node shoes
                            Element eElement7 = (Element) nNode7;
                            NodeList nListPathColorShoes = eElement7.getElementsByTagName("pathColor").item(0).getChildNodes();
                            Node nValue7 = (Node) nListPathColorShoes.item(0);
                            String pathShoesColorReaded = nValue7.getNodeValue();
                            //Print the data readed
                            System.out.println("Eyes "+numShoes+": " + pathShoesColorReaded);
                            
                            NodeList nListPathShadowShoes = eElement7.getElementsByTagName("pathShadow").item(0).getChildNodes();
                            Node nValue8 = (Node) nListPathShadowShoes.item(0);
                            String pathShoesShadowReaded = nValue8.getNodeValue();
                            //Print the data readed
                            System.out.println("Eyes "+numShoes+": " + pathShoesShadowReaded);
                        }
                    }
                  }
                  NodeList nListAnimations = eElement.getElementsByTagName("animation");

                    int indexAnimationReaded;
                    for (indexAnimationReaded = 0; indexAnimationReaded < nListAnimations.getLength(); indexAnimationReaded++){
                          Node nNode8 = nListAnimations.item(indexAnimationReaded);
                          if (nNode8.getNodeType() == Node.ELEMENT_NODE) {
                              //In element8, we have the node animation
                              Element eElement8 = (Element) nNode8;

                              NodeList nListPathAnimation = eElement8.getElementsByTagName("path").item(0).getChildNodes();
                              Node nValue9 = (Node) nListPathAnimation.item(0);
                              String pathAnimationReaded = nValue9.getNodeValue();
                              //Print the data readed
                              System.out.println("Animation "+indexAnimationReaded+": " + pathAnimationReaded);
                              //Save the path of this 
                          }
                    }
                  
               }
            }
        } catch (Exception e) {
              e.printStackTrace();
        }
    }
}