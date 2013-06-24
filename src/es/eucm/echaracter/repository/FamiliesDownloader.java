/*******************************************************************************
 * <eCharacter> is a research project of the <e-UCM>
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
 *          For more info please visit:  <http://echaracter.e-ucm.es>, 
 *          <http://e-adventure.e-ucm.es> or <http://www.e-ucm.es>
 *  
 *  ****************************************************************************
 *      <eCharacter> is free software: you can 
 *      redistribute it and/or modify it under the terms of the GNU Lesser 
 *      General Public License as published by the Free Software Foundation, 
 *      either version 3 of the License, or (at your option) any later version.
 *  
 *      <eCharacter> is distributed in the hope that it 
 *      will be useful, but WITHOUT ANY WARRANTY; without even the implied 
 *      warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  
 *      See the GNU Lesser General Public License for more details.
 *  
 *      You should have received a copy of the GNU Lesser General Public License
 *      along with <eCharacter>. If not, 
 *      see <http://www.gnu.org/licenses/>.
 ******************************************************************************/

package es.eucm.echaracter.repository;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

public class FamiliesDownloader {
    //Devuelve true si exito. False en caso contrario.
    public static boolean download(String url, String downloadFolder, String fileName){
        //Crea el directorio de destino en caso de que no exista
        File dir = new File(downloadFolder);
        if (!dir.exists()){
            if (!dir.mkdir()){
                return false; // no se pudo crear la carpeta de destino
            }
        }
        URLConnection conn = null;
        try {
            conn = new URL(url).openConnection();
            conn.connect();
            System .out.println("\nEmpezando descarga: \n");
            System .out.println(">> URL: " + url);
            System .out.println(">> Nombre: " + fileName);
            System .out.println(">> tamaño: " + conn.getContentLength() + " bytes");

            InputStream  in = conn.getInputStream();
            OutputStream out = new FileOutputStream (downloadFolder + fileName);
            
            byte[] array = new byte[1000]; // buffer temporal de lectura.
            int readed = in.read(array);
            while (readed > 0) {
                    out.write(array, 0, readed);
                    readed = in.read(array);
            }
            in.close();
            out.close();

            return true;
        } catch (MalformedURLException e1) {
            // TODO Auto-generated catch block
            e1.printStackTrace();
        } catch (IOException e1) {
            // TODO Auto-generated catch block
            e1.printStackTrace();
        }
        return false;
    }
}
