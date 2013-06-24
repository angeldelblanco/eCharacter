/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package es.eucm.echaracter.api;

import java.util.Properties;

/**
 *
 * @author Alex
 */
public class mainExterno {
    
    public static void main(String[] args){
        Properties p = new Properties();
        p.put("lang", "es_ES");
        p.put("defaultExportPath", "C:/Users/Alex/Desktop");
        eCharacter.eCharacter(p);
    }
    
}
