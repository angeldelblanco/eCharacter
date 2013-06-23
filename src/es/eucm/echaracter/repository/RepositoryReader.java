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
 *          For more info please visit:  <http://character.e-ucm.es>, 
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

import es.eucm.echaracter.loader.Configuration;
import es.eucm.echaracter.loader.XMLReader;
import es.eucm.echaracter.repository.data.Repository;
import es.eucm.echaracter.repository.data.Repository.Family;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

public class RepositoryReader {
    
    private final String url = "https://dl.dropboxusercontent.com/u/29636278/eCharacter/repo.xml";
    private final String downloadFolder = Configuration.USER_PATH+File.separator+"eCharacter"+File.separator+"repository"+File.separator;
    private final String fileName = "families.xml";
    
    private ArrayList<Family> listFamilies;
    
    public RepositoryReader(){
        if (FamiliesDownloader.download(url, downloadFolder, fileName)){
            XMLReader xmlReader = new XMLReader<Repository>(downloadFolder);
            ArrayList<Repository> list = xmlReader.readXML(Repository.class);
            if (list.size() == 1){
                listFamilies = (ArrayList<Family>) list.get(0).getFamily();
            }
        }
        else{
            System.out.println("Error al obtener los datos del repositorio.");
        }
    }
    
    public int getNumFamilies(){
        return listFamilies.size();
    }
    
    public ArrayList<String> getFamiliesID(){
        ArrayList<String> listID = new ArrayList<String>();
        Iterator<Family> it = listFamilies.iterator();
        while(it.hasNext()){
            Family family = it.next();
            listID.add(family.getUrl());
        }
        return listID;
    }
    
    public String getIconPathFamily(String familyID){
        Iterator<Family> it = listFamilies.iterator();
        while(it.hasNext()){
            Family family = it.next();
            if (family.getUrl().equals(familyID)){
                return family.getIconPath();
            }
        }
        return null;
    }
    
    public String getDescriptionFamily(String familyID){
        Iterator<Family> it = listFamilies.iterator();
        while(it.hasNext()){
            Family family = it.next();
            if (family.getUrl().equals(familyID)){
                return family.getDescription();
            }
        }
        return null;
    }
    
    //Devuelve true en caso de acierto
    public boolean downloadFamily(String familyID){
        String familyFileName = getFileName(familyID);
        if (FamiliesDownloader.download(familyID, downloadFolder, familyFileName)){
            installFamily(downloadFolder+familyFileName);
            return true;
        }
        else{
            return false;
        }
    }
    
    private String getFileName(String url){
        File f = new File(url);
        return f.getName();
    }
    
    private void installFamily(String fileName){
        // TO DO
        try {
            ZipInputStream zis = new ZipInputStream(new FileInputStream(fileName));
            ZipEntry entry;
            while (null != (entry = zis.getNextEntry()) ){
                System.out.println(entry.getName());
                if (entry.isDirectory()){
                    File dir = new File(entry.getName());
                    if (!dir.exists()){
                        if (!dir.mkdir()){
                            return; // no se pudo crear la carpeta de destino
                        }
                    }
                }
                else{
                    FileOutputStream fos = new FileOutputStream(entry.getName());
                    int readed;
                    byte [] buffer = new byte[1024];
                    while (0<(readed=zis.read(buffer))){
                        fos.write(buffer,0,readed);
                    }
                    fos.close();
                    zis.closeEntry();
                }
            }
            zis.close();       
        } catch (IOException ex) {
            Logger.getLogger(RepositoryReader.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    
    
    public static void main(String[] args){
        RepositoryReader r = new RepositoryReader();
        ArrayList<String> l = r.getFamiliesID();
        r.downloadFamily(l.get(0));
    }
}
