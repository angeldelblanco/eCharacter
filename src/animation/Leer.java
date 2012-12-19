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
            NodeList nList = doc.getElementsByTagName("man");
            for (int temp = 0; temp < nList.getLength(); temp++) {
               Node nNode = nList.item(temp); 
               if (nNode.getNodeType() == Node.ELEMENT_NODE) {
                  //Here, we have the element man 
                  Element eElement = (Element) nNode;
                  NodeList nList2 = eElement.getElementsByTagName("objects");
                  Node nNode2 = (Node) nList2.item(0);
                  if (nNode2.getNodeType() == Node.ELEMENT_NODE) {
                    //In eElement2, we have the node objects
                    Element eElement2 = (Element) nNode2;
                    NodeList nList3 = eElement2.getElementsByTagName("skin");
                    int numSkin;
                    for (numSkin = 0; numSkin < nList3.getLength(); numSkin++){
                        Node nNode3 = nList3.item(numSkin);
                        if (nNode3.getNodeType() == Node.ELEMENT_NODE) {
                            //In element3, we have the node skin
                            Element eElement3 = (Element) nNode3;
                            NodeList nList4 = eElement3.getElementsByTagName("path").item(0).getChildNodes();
                            Node nValue = (Node) nList4.item(0);
                            String pathSkinReaded = nValue.getNodeValue();
                            //Print the data readed
                            System.out.println("Skin "+numSkin+": " + pathSkinReaded);
                        }
                    }
                  }
               }
            }
        } catch (Exception e) {
              e.printStackTrace();
        }
    }
}