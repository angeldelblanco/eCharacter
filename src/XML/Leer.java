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
        File directorio = new File("assets/XML Configuration/families");
        File[] ficheros = directorio.listFiles();
        for (int x=0;x<ficheros.length;x++)
        {
            File file = ficheros[x];
            if (! file.isDirectory() && ! file.getName().equals("family.xsd")) {
               read(file);
            }
        }
    }
    
    private static void read (File xmlFile){
        try {
            System.out.println(xmlFile.getName());
            //Assing the file to the DOM doc.
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(xmlFile);
            doc.getDocumentElement().normalize();
            
            //Read the doc.
            //Read all nodes with name family
            NodeList nListFamily = doc.getElementsByTagName("family");
            for (int temp = 0; temp < nListFamily.getLength(); temp++) {
               Node nNode = nListFamily.item(temp); 
               if (nNode.getNodeType() == Node.ELEMENT_NODE) {
                  //Here, we have the element family 
                  Element eElement = (Element) nNode;
                  NodeList nListMetadata = eElement.getElementsByTagName("metadata");
                  Node nNode2 = (Node) nListMetadata.item(0);
                  if (nNode2.getNodeType() == Node.ELEMENT_NODE) {
                        //In eElement2, we have the node metadata
                        Element eElement2 = (Element) nNode2;
                        NamedNodeMap attributes = nNode2.getAttributes();
                        Node nValue = attributes.getNamedItem("name");
                        String nameReaded = nValue.getNodeValue();
                        //Print the data readed
                        System.out.println("Nombre de la familia: " + nameReaded);
                        
                        nValue = attributes.getNamedItem("description");
                        String descriptionReaded = nValue.getNodeValue();
                        //Print the data readed
                        System.out.println("Descripción de la familia: " + descriptionReaded);
                          
                        nValue = attributes.getNamedItem("author");
                        String authorReaded = nValue.getNodeValue();
                        //Print the data readed
                        System.out.println("Autor de la familia: " + authorReaded);
                        
                        nValue = attributes.getNamedItem("URL");
                        if (nValue != null){
                            String urlReaded = nValue.getNodeValue();
                            //Print the data readed
                            System.out.println("URL de la familia: " + urlReaded);
                        }
                  }
                  /*
                   * 
                   * FALTA LEER EL ELEMENTO PROPERTIES
                   * 
                   */
                                              
                  NodeList nListModels = eElement.getElementsByTagName("models");
                  Node nNode4 = (Node) nListModels.item(0);
                  if (nNode4.getNodeType() == Node.ELEMENT_NODE) {
                        //In eElement2, we have the node models
                        Element eElement2 = (Element) nNode4;
                        NodeList nListModel = eElement2.getElementsByTagName("model");
                        int numModels;
                        for (numModels = 0; numModels < nListModel.getLength(); numModels++){
                            Node nNode5 = nListModel.item(numModels);
                            if (nNode5.getNodeType() == Node.ELEMENT_NODE) {
                                NamedNodeMap attributes = nNode5.getAttributes();
                                
                                Node nValue = attributes.getNamedItem("nameModel");
                                String nameModelReaded = nValue.getNodeValue();
                                //Print the data readed
                                System.out.println("Nombre del modelo "+numModels+": "+nameModelReaded);

                                nValue = attributes.getNamedItem("iconPath");
                                String iconPathReaded = nValue.getNodeValue();
                                //Print the data readed
                                System.out.println("Path del icono del modelo "+numModels+": "+iconPathReaded);

                                nValue = attributes.getNamedItem("modelPath");
                                String modelPathReaded = nValue.getNodeValue();
                                //Print the data readed
                                System.out.println("Path del modelo "+numModels+": "+modelPathReaded);
                            }
                        }
                  }
               }
               System.out.println();
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