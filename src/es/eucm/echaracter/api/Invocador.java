/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package es.eucm.echaracter.api;

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
            p.put("lang", "en_EN");
            p.put("defaultExportPath", "C:/Users/Alex/Desktop");
            eCharacter eC = new eCharacter();
            eC.eCharacter(p,inv);
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage());
        }
    }
}
