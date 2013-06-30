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

    public void exportFailed() {
        flag = -1;
    }

    public void exportSuccess() {
        flag = 1;
    }  
    
    private static final int TEMP_DIR_ATTEMPTS = 10000;
    
    public static File createTempDir() {
        File baseDir = new File(System.getProperty("java.io.tmpdir"));
        String baseName = System.currentTimeMillis() + "-";

        for (int counter = 0; counter < TEMP_DIR_ATTEMPTS; counter++) {
          File tempDir = new File(baseDir, baseName + counter);
          if (tempDir.mkdir()) {
            return tempDir;
          }
        }
        throw new IllegalStateException("Failed to create directory within "
            + TEMP_DIR_ATTEMPTS + " attempts (tried "
            + baseName + "0 to " + baseName + (TEMP_DIR_ATTEMPTS - 1) + ')');
      }
    
    public static void main(String[] args){
        try {
            Invocador inv = new Invocador();
            Properties p = new Properties();
                    
        
            p.put("lang", "en_EN.xml");    
        
            
        
        String exportPath = createTempDir().getAbsolutePath( );
        new File(exportPath).mkdir( );
        p.put( "defaultExportPath", exportPath);
        
        p.put( "defaultCamera", "Front|Back|Left|Right" );
        p.put( "defaultAnimations", "##all##" );
        p.put( "defaultQuality", "5" );
            eCharacter eC = new eCharacter();
            eC.eCharacter(p,inv);
            
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage());
        }
    }
}
