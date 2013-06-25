/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package es.eucm.echaracter.api;

import java.io.File;
import java.util.Properties;
import javax.swing.JOptionPane;


public class Invocador implements Callback{
    
    private static int flag = 0;

    public void returnFailed() {
        flag = -1;
    }

    public void returnSuccess() {
        flag = 1;
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
                    +File.separator+"XML models"+File.separator+"YoungBoy.xml");
            //p.put("defaultStage","idMultiStageLabel8");
            //p.put("defaultExportPath", "C:/Users/Alex/Desktop");
            eCharacter eC = new eCharacter();
            eC.eCharacter(p,inv);
            /*while (Invocador.flag == 0);
            System.out.println("fin");*/
            
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage());
        }
    }
}
