/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package es.eucm.echaracter.api;

import java.io.File;
import java.util.Properties;
import javax.swing.JOptionPane;


public class Invocador implements Callback{

    public int returnFailed() {
        return -1;
    }

    public int returnSuccess() {
        return 1;
    }  
    
    public static void main(String[] args){
        try {
            Invocador inv = new Invocador();
            Properties p = new Properties();
            p.put("lang", "fr_FR");
            /*p.put("defaultFamily","assets"+File.separator+"Families"+File.separator+"Cubix Studio.xml");
            p.put("defaultModel","assets"+File.separator+"Families"+File.separator+"Cubix Studio"
                    +File.separator+"XML models"+File.separator+"Male.xml");*/
            p.put("defaultFamily","assets"+File.separator+"Families"+File.separator+"eAdventure.xml");
            p.put("defaultModel","assets"+File.separator+"Families"+File.separator+"eAdventure"
                    +File.separator+"XML models"+File.separator+"Man.xml");
            p.put("defaultExportPath", "C:/Users/Alex/Desktop");
            eCharacter eC = new eCharacter();
            eC.eCharacter(p,inv);
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage());
        }
    }
}
