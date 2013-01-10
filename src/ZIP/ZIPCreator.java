package ZIP;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class ZIPCreator {
    
    private int BUFFER = 1024;
    
    public ZIPCreator(){}
    
    public void createZIP(String filename, String folder)
    {
        try {
            //Nuestro InputStream
            BufferedInputStream origin = null;
            //Declaramos el FileOutputStream para guardar el archivo
            FileOutputStream dest = new FileOutputStream(filename);
            //Indicamos que será un archivo ZIP
            ZipOutputStream out = new ZipOutputStream(new BufferedOutputStream(dest));
            //Indicamos que el archivo tendrá compresión
            out.setMethod(ZipOutputStream.DEFLATED);
                //Indicamos que el archivo NO tendrá compresión
                //out.setMethod(ZipOutputStream.STORED);
            byte data[] = new byte[BUFFER];
            // Creamos la referencia de la carpeta a leer
            File f = new File(folder);
            // Guarda los nombres de los archivos de la carpeta a leer
            String files[] = f.list();
            // Muestra el listado de archivos de la carpeta a leer
            for (int i=0; i<files.length; i++) {
                //Es para no guardar en el ZIP el fichero Thumbs.db
                if (!files[i].equals("Thumbs.db")){
                    System.out.println("Agregando al ZIP: "+files[i]);
                    //Creamos el objeto a guardar para cada uno de los elementos del listado
                    FileInputStream fi = new FileInputStream(folder+"/"+files[i]);
                    origin = new BufferedInputStream(fi, BUFFER);
                    ZipEntry entry = new ZipEntry(files[i]);
                    //Guardamos el objeto en el ZIP
                    out.putNextEntry(entry);
                    int count;
                    //Escribimos el objeto en el ZIP
                    while((count = origin.read(data, 0,BUFFER)) != -1) {
                        out.write(data, 0, count);
                    }
                    origin.close();
                }
            }
            out.close();
        } 
        catch(Exception e) {
            e.printStackTrace();
        }
    }
}
