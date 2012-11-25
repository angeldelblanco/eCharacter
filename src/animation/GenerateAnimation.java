package animation;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;

public class GenerateAnimation 
{
	String[] imagesNames;
	String animationPath;
	String nameAnimation;

	public GenerateAnimation(String folderPath,String nameAnimation)
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
		PrintWriter f = null;
		try
		{
			f = new PrintWriter(path);
			f.print("<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"no\"?>");
			f.println("<!DOCTYPE animation SYSTEM \"animation.dtd\">");
			f.print("<animation id=\"" + this.nameAnimation + "\" slides=\"no\" usetransitions=\"no\">");
			for (int i = 0; i < imagesNames.length; i++ )
			{
				f.print("<transition time=\"0\" type=\"none\"/>");
				f.print("<frame maxSoundTime=\"1000\" soundUri=\"\" time=\"200\" type=\"image\" uri=assets/animation/" + imagesNames[i] + "\" waitforclick=\"no\"/>");
			}
			f.print("</animation>");			
		}
		catch (FileNotFoundException e)
		{
			System.out.println("Failed saving animation");
		}
		f.close();
	}
	
	/*public static void main(String[] args)
	{
		GenerateAnimation aux = new GenerateAnimation("C:/Users/Alex/Dropbox/Sistemas Inform�ticos/eAdventure/Projects/New project/assets/animation/capturas","animacionSaludo");
	}*/
}

