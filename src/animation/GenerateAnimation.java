package animation;

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

public class GenerateAnimation 
{
	private ArrayList<String> imagesNames;
	private String animationPath;
	private String nameAnimation;

	public GenerateAnimation(String folderPath, String nameAnimation, ArrayList<String> imagesNames)
	{
		this.nameAnimation = nameAnimation;
		this.animationPath = folderPath + "/" + nameAnimation + ".eaa";
                this.imagesNames = imagesNames;
		//Opening the folder for get the name of images
		/*File folder = new File(folderPath);
		this.imagesNames = folder.list();
		if(this.imagesNames == null)
		{
			System.out.println("No files in folder");
		}
		else
		{
			createAnimation(this.animationPath);
		}*/
                createAnimation();
	}
	
	private void createAnimation() 
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
                
                Iterator<String> it = imagesNames.iterator();              
                //for (int i = 0; i < imagesNames.size(); i++ )
                while (it.hasNext())
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
                    frameNode.setAttribute("uri", "assets/animation/" + it.next());
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
                    fout = new FileOutputStream(this.animationPath);
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
}