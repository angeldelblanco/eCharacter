/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import com.jme3.system.JmeCanvasContext;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import loader.LoadModel;
import tipos.Objeto;
import window_color.ColorChooser;

public class Gui extends JFrame implements ActionListener 
{
    private Component previewWindow;
    private JFrame frame;
    private LoadModel app;
    
    private Objeto estado;
    
    public Gui()
    {
        frame = new JFrame("Avatar Configuration");
        
        frame.setJMenuBar(getMenu());
        
        JPanel panel = new JPanel();
        frame.setContentPane(panel);
        panel.setLayout(new BorderLayout());
        //panel.add(getButtonPanel(),BorderLayout.LINE_START);
        
        app = new LoadModel();
        
        app.createCanvas();
        JmeCanvasContext ctx = (JmeCanvasContext)app.getContext();
        previewWindow = ctx.getCanvas();
        previewWindow.setEnabled(true);
        previewWindow.setSize(new Dimension(640,480));
        //panel.add(previewWindow,BorderLayout.LINE_END);
        panel.add(previewWindow,BorderLayout.CENTER);
        
        frame.setEnabled(true);
        frame.setVisible(true);
        frame.setSize(new Dimension(800,600));
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //app.start();
    }
    
    private JMenuBar getMenu()
    {
        JMenuBar menuBar = new JMenuBar();
        
        //Menu to skin
        JMenu skinMenu = new JMenu("Skin");
        
        JMenuItem changeSkin = new JMenuItem("Change skin");
	changeSkin.addActionListener(this);
	changeSkin.setActionCommand("changeSkin");
        skinMenu.add(changeSkin);
        
        skinMenu.setBorderPainted(true);
        menuBar.add(skinMenu);
        
        //Menu to eyes
        JMenu eyesMenu = new JMenu("Eyes");
        
        JMenuItem changeEyes = new JMenuItem("Change eyes");
	changeEyes.addActionListener(this);
	changeEyes.setActionCommand("changeEyes");
        eyesMenu.add(changeEyes);
        
        eyesMenu.setBorderPainted(true);
        menuBar.add(eyesMenu);
        
        //Menu to tshirt
        JMenu tshirtMenu = new JMenu("T-Shirt");   
        JMenuItem changeColorTShirt = new JMenuItem("Change color t-shirt");
	changeColorTShirt.addActionListener(this);
	changeColorTShirt.setActionCommand("changeColorTShirt");
        tshirtMenu.add(changeColorTShirt);
        
        JMenuItem changeTShirt = new JMenuItem("Change t-shirt");
	changeTShirt.addActionListener(this);
	changeTShirt.setActionCommand("changeTShirt");
        tshirtMenu.add(changeTShirt);
        
        tshirtMenu.setBorderPainted(true);
        menuBar.add(tshirtMenu);
        
        //Menu to trousers
        JMenu trousersMenu = new JMenu("Trousers");
        JMenuItem changeColorTrouser = new JMenuItem("Change color trouser");
	changeColorTrouser.addActionListener(this);
	changeColorTrouser.setActionCommand("changeColorTrouser");
        trousersMenu.add(changeColorTrouser);
        
        JMenuItem changeTrouser = new JMenuItem("Change trouser");
	changeTrouser.addActionListener(this);
	changeTrouser.setActionCommand("changeTrouser");
        trousersMenu.add(changeTrouser);
        
        trousersMenu.setBorderPainted(true);
        menuBar.add(trousersMenu);
        
        //Menu to shoes
        JMenu shoesMenu = new JMenu("Shoes");
        JMenuItem changeShoes = new JMenuItem("Change shoes");
	changeShoes.addActionListener(this);
	changeShoes.setActionCommand("changeShoes");
        shoesMenu.add(changeShoes);
        
        shoesMenu.setBorderPainted(true);
        menuBar.add(shoesMenu);
        
        //Menu to animation
        JMenu animationMenu = new JMenu("Animation");
        JMenuItem generateAnimation = new JMenuItem("Generate animation");
	generateAnimation.addActionListener(this);
	generateAnimation.setActionCommand("generateAnimation");
        animationMenu.add(generateAnimation);
        
        animationMenu.setBorderPainted(true);
        menuBar.add(animationMenu);
        
        menuBar.setBackground(Color.LIGHT_GRAY);
        return menuBar;        
    }
    
    public void actionPerformed(ActionEvent e) 
    {
        if(e.getActionCommand().equals("changeSkin"))
        {
            app.changeSkin();
            previewWindow.repaint(); 
            
            //app.actionListener.onAction("Capture", false, 0);
            //app.screenshot();
        }
        if(e.getActionCommand().equals("changeEyes"))
        {            
            app.changeEyes();
            previewWindow.repaint();  
        }
        if(e.getActionCommand().equals("changeTShirt"))
        {
            app.changeTShirt();
            previewWindow.repaint();          
        }
        if(e.getActionCommand().equals("changeColorTShirt"))
        {
            estado = Objeto.t_shirt;
            ColorChooser window = new ColorChooser(this);    
            window.setAlwaysOnTop(true);
        }
        if(e.getActionCommand().equals("changeTrouser"))
        {
            app.changeTrousers();
            previewWindow.repaint();           
        }
        if(e.getActionCommand().equals("changeColorTrouser"))
        {
            estado = Objeto.trouser;
            ColorChooser window = new ColorChooser(this);      
            window.setAlwaysOnTop(true);
        }
        if(e.getActionCommand().equals("changeShoes"))
        {
            app.changeShoes();
            previewWindow.repaint();           
        }
        if(e.getActionCommand().equals("generateAnimation"))
        {
            //app.screenshot();
            previewWindow.setEnabled(true);
            screenShot(previewWindow);
            previewWindow.setEnabled(false);
        }
    }
     
    public static void main(String[] args)
    {
        Gui window = new Gui();

    }   
    
    public static void screenShot(Component component) 
    {

        BufferedImage image = new BufferedImage(component.getWidth(),
                component.getHeight(),BufferedImage.TYPE_4BYTE_ABGR);
        // call the Component's paint method, using
        // the Graphics object of the image.
        component.paint( image.getGraphics() );
        try {
            ImageIO.write(image, "png", new File("assets/Textures/screenshots/Captura.png"));
        } catch (IOException ex) {
            System.out.println("Error");
        }
     }
    
    public void changeColor (int color) throws IOException
    {
        app.changeColor(color, estado);          
    }
}