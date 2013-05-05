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
package es.eucm.character.export;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public final class ZIPWritter {
    
    private int BUFFER = 1024;
    private ZipOutputStream out;
    private byte data[];
    
    public ZIPWritter(String filename){
        try {
            //Declaramos el FileOutputStream para guardar el archivo
            FileOutputStream dest = new FileOutputStream(filename);
            //Indicamos que será un archivo ZIP
            out = new ZipOutputStream(new BufferedOutputStream(dest));
            //Indicamos que el archivo tendrá compresión
            out.setMethod(ZipOutputStream.DEFLATED);
                //Indicamos que el archivo NO tendrá compresión
                //out.setMethod(ZipOutputStream.STORED);
            data = new byte[BUFFER];
        } 
        catch(Exception e) {
            e.printStackTrace();
        }
    }
    
    public void saveFile(File file, String name){
        try {
            System.out.println("Agregando al ZIP: "+file.getPath());
            //Creamos el objeto a guardar
            FileInputStream fi = new FileInputStream(file);
            BufferedInputStream origin = new BufferedInputStream(fi, BUFFER);
            ZipEntry entry = new ZipEntry(name);
            //Guardamos el objeto en el ZIP
            out.putNextEntry(entry);
            int count;
            //Escribimos el objeto en el ZIP
            while((count = origin.read(data, 0,BUFFER)) != -1) {
                out.write(data, 0, count);
            }
            origin.close();
        }
        catch(Exception e) {
            e.printStackTrace();
        }
    }
    
    public void saveFile(String filePath, String fileName){
        try {
            System.out.println("Agregando al ZIP: "+filePath);
            //Creamos el objeto a guardar
            FileInputStream fi = new FileInputStream(filePath);
            BufferedInputStream origin = new BufferedInputStream(fi, BUFFER);
            ZipEntry entry = new ZipEntry(fileName);
            //Guardamos el objeto en el ZIP
            out.putNextEntry(entry);
            int count;
            //Escribimos el objeto en el ZIP
            while((count = origin.read(data, 0,BUFFER)) != -1) {
                out.write(data, 0, count);
            }
            origin.close();
        }
        catch(Exception e) {
            e.printStackTrace();
        }
    }
    
    public void closeZip(){
        try {
            out.close();
        } catch (IOException ex) {
            Logger.getLogger(ZIPWritter.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
