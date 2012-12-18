package window_color;

import com.jme3.renderer.ViewPort;
import gui_nifty.Gui;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JButton;
import javax.swing.JColorChooser;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class ColorChooser extends JFrame implements ChangeListener, ActionListener
{
    
    private JColorChooser chooserColor;
    private JButton buttonOk, buttonCancel;
    private Gui gui;
    public ViewPort guiViewPort;
    
    public ColorChooser(Gui gui, ViewPort guiViewPort)
    {
        this.gui = gui;
        this.guiViewPort = guiViewPort;
        
        setEnabled(true);
        setVisible(true);
        setSize(new Dimension(800,600));
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        
        chooserColor = new JColorChooser();
        chooserColor.getSelectionModel().addChangeListener(this);
        
        JPanel panelButtons = new JPanel();
        buttonOk = new JButton("OK"); 
        buttonOk.addActionListener(this);
        buttonOk.setActionCommand("ok");
        buttonCancel = new JButton("Cancel");
        buttonCancel.addActionListener(this);
        buttonCancel.setActionCommand("cancel");
        chooserColor.setMaximumSize(new Dimension(100,100));
        setLayout(new BorderLayout());
        panelButtons.add(buttonOk);
        panelButtons.add(buttonCancel);
        add(chooserColor, BorderLayout.CENTER);
        add(panelButtons, BorderLayout.SOUTH);
        pack();
    }

    public void actionPerformed(ActionEvent e) 
    {
        if(e.getActionCommand().equals("ok"))
        {
            this.dispose();
            try {
                gui.changeColor(chooserColor.getColor().getRGB());
                guiViewPort.setEnabled(true);
            } catch (IOException ex) {
                Logger.getLogger(ColorChooser.class.getName()).log(Level.SEVERE, null, ex);
            }
        } 
        if(e.getActionCommand().equals("cancel"))
        {
            this.dispose();   
            guiViewPort.setEnabled(true);
        } 
    }

    public void stateChanged(ChangeEvent e) {}
}
