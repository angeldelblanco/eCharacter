/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import com.jme3.renderer.ViewPort;
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
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import loader.LoadModel;

public class Gui extends JFrame implements ActionListener
{
    private Component previewWindow;
    private JFrame frame;
    private LoadModel app;
    
    
    private ViewPort view;
    
    public Gui()
    {
        frame = new JFrame("Avatar Configuration");
        
        frame.setJMenuBar(getMenu());
        
        JPanel panel = new JPanel();
        frame.setContentPane(panel);
        panel.setLayout(new BorderLayout());
        panel.add(getButtonPanel(),BorderLayout.LINE_START);
        
        app = new LoadModel();
        
        app.createCanvas();
        JmeCanvasContext ctx = (JmeCanvasContext)app.getContext();
        previewWindow = ctx.getCanvas();
        previewWindow.setEnabled(false);
        previewWindow.setSize(new Dimension(640,480));
        panel.add(previewWindow,BorderLayout.LINE_END);
        
        frame.setEnabled(true);
        frame.setVisible(true);
        frame.setSize(new Dimension(800,600));
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        /*app.setShowSettings(false);
        app.start();*/
    }
    
    private JMenuBar getMenu()
    {
        JMenuBar menuBar = new JMenuBar();
        
        JMenu importMenu = new JMenu("Import");
        
        JMenuItem importAnimation = new JMenuItem("Import Animation");
	importAnimation.addActionListener(this);
	importAnimation.setActionCommand("import");
        importMenu.add(importAnimation);
        importMenu.setBorderPainted(true);
        
        menuBar.add(importMenu);
        menuBar.setBackground(Color.LIGHT_GRAY);
        return menuBar;        
    }
    
    private JPanel getButtonPanel()
    {
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.Y_AXIS));
        
        JButton skinButton = new JButton("Skin");
        skinButton.addActionListener(this);
        skinButton.setActionCommand("skin");
        skinButton.setAlignmentY(Component.CENTER_ALIGNMENT);
        buttonPanel.add(skinButton);
        
        JButton eyesButton = new JButton("Eyes");
        eyesButton.addActionListener(this);
        eyesButton.setActionCommand("eyes");
        eyesButton.setAlignmentY(Component.CENTER_ALIGNMENT);
        buttonPanel.add(eyesButton);   
                
        JButton tShirtButton = new JButton("TShirt");
        tShirtButton.addActionListener(this);
        tShirtButton.setActionCommand("tshirt");
        tShirtButton.setAlignmentY(Component.CENTER_ALIGNMENT);
        buttonPanel.add(tShirtButton);
        
        JButton trousersButton = new JButton("Trousers");
        trousersButton.addActionListener(this);
        trousersButton.setActionCommand("trousers");
        trousersButton.setAlignmentY(Component.CENTER_ALIGNMENT);
        buttonPanel.add(trousersButton);
        
        JButton shoesButton = new JButton("Shoes");
        shoesButton.addActionListener(this);
        shoesButton.setActionCommand("shoes");
        shoesButton.setAlignmentY(Component.CENTER_ALIGNMENT);
        buttonPanel.add(shoesButton);
        
        return buttonPanel;
    }
    
    public void actionPerformed(ActionEvent e) 
    {
        if(e.getActionCommand().equals("skin"))
        {
            app.changeSkin();
            previewWindow.repaint();
                                 
        }
        if(e.getActionCommand().equals("eyes"))
        {            
            app.changeEyes();
            previewWindow.repaint();          
        }
        if(e.getActionCommand().equals("tshirt"))
        {
            app.changeTShirt();
            previewWindow.repaint();          
        }
        if(e.getActionCommand().equals("trousers"))
        {
            app.changeTrousers();
            previewWindow.repaint();           
        }
        if(e.getActionCommand().equals("shoes"))
        {
            app.changeShoes();
            previewWindow.repaint();           
        }
        if(e.getActionCommand().equals("import"))
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
}