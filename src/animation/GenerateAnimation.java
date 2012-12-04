package animation;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

public class GenerateAnimation 
{
	String[] imagesNames;
	String animationPath;
	String nameAnimation;

	public GenerateAnimation(String folderPath, String nameAnimation)
	{
		this.nameAnimation = nameAnimation;
		this.animationPath = folderPath + "/" + nameAnimation + ".eaa";
		//Opening the folder for get the name of images
		File folder = new File(folderPath);
		this.imagesNames = folder.list();
		if(this.imagesNames == null)
		{
			System.out.println("No files in folder");
		}
		else
		{
			createAnimation(this.animationPath);
		}
	}
	
	private void createAnimation(String path) 
	{
            try
            {
                DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance( );
                TransformerFactory tf = TransformerFactory.newInstance( );
                DocumentBuilder db = dbf.newDocumentBuilder( );
                Document doc = db.newDocument( );
                Transformer transformer = null;
                OutputStream fout = null;
                OutputStreamWriter writeFile = null;
                //Creat the main node
                Element mainNode = doc.createElement( "animation");
                mainNode.setAttribute("id", this.nameAnimation);
                mainNode.setAttribute("usetransitions", "no");
        	mainNode.setAttribute("slides", "no");
                
                for (int i = 0; i < imagesNames.length; i++ )
		{
                    //Creat the node "transition"
                    Element transitionNode = doc.createElement("transition");
                    transitionNode.setAttribute("type", "none");
                    transitionNode.setAttribute("time", "0");
                    //Creat the node "frame"
                    Element frameNode = doc.createElement("frame");
                    frameNode.setAttribute("maxSoundTime", "1000");
                    frameNode.setAttribute("soundUri", "");
                    frameNode.setAttribute("time", "200");
                    frameNode.setAttribute("type", "image");
                    frameNode.setAttribute("uri", "assets/animation/" + imagesNames[i]);
                    frameNode.setAttribute("waitforclick", "no");
                    //Add de nodes to main node
                    mainNode.appendChild(transitionNode);
                    mainNode.appendChild(frameNode);
		}            
                doc.adoptNode(mainNode);
                doc.appendChild(mainNode);
                transformer = tf.newTransformer();
                transformer.setOutputProperty(OutputKeys.DOCTYPE_SYSTEM, "animation.dtd");
                try {
                    fout = new FileOutputStream(path);
                }
                catch( FileNotFoundException e ) {
                    System.out.println("error");
                }
                writeFile = new OutputStreamWriter(fout, "UTF-8");
                transformer.transform(new DOMSource(doc), new StreamResult(writeFile));
                writeFile.close();
                fout.close();
            }
            catch( Exception e ) {
                System.out.println("error");
            }
        }
	
	public static void main(String[] args)
	{
		GenerateAnimation aux = new GenerateAnimation("C:/Users/Sergio/Desktop/prueba/capturas", "chico_sentado");
	}
}