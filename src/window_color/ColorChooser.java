/*******************************************************************************
 * <eAdventure Character Configurator> is a research project of the <e-UCM>
 *          research group.
 *
 *    Developed by: Alejandro Muñoz del Rey, Sergio de Luis Nieto and David González
 *    Ledesma.
 *    Under the supervision of Baltasar Fernández-Manjón and Javier Torrente
 * 
 *    Copyright 2012-2013 <e-UCM> research group.
 *  
 *     <e-UCM> is a research group of the Department of Software Engineering
 *          and Artificial Intelligence at the Complutense University of Madrid
 *          (School of Computer Science).
 *  
 *          C Profesor Jose Garcia Santesmases sn,
 *          28040 Madrid (Madrid), Spain.
 *  
 *          For more info please visit:  <http://character.e-ucm.es>, 
 *          <http://e-adventure.e-ucm.es> or <http://www.e-ucm.es>
 *  
 *  ****************************************************************************
 *      <eAdventure Character Configurator> is free software: you can 
 *      redistribute it and/or modify it under the terms of the GNU Lesser 
 *      General Public License as published by the Free Software Foundation, 
 *      either version 3 of the License, or (at your option) any later version.
 *  
 *      <eAdventure Character Configurator> is distributed in the hope that it 
 *      will be useful, but WITHOUT ANY WARRANTY; without even the implied 
 *      warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  
 *      See the GNU Lesser General Public License for more details.
 *  
 *      You should have received a copy of the GNU Lesser General Public License
 *      along with <eAdventure Character Configurator>. If not, 
 *      see <http://www.gnu.org/licenses/>.
 ******************************************************************************/

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
