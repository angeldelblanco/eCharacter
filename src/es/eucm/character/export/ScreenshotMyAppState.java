/********************************************************************************
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

import com.jme3.app.Application;
import com.jme3.app.state.AppStateManager;
import com.jme3.app.state.ScreenshotAppState;
import com.jme3.input.InputManager;
import com.jme3.renderer.RenderManager;
import com.jme3.renderer.Renderer;
import com.jme3.renderer.ViewPort;
import com.jme3.renderer.queue.RenderQueue;
import com.jme3.texture.FrameBuffer;
import com.jme3.util.BufferUtils;
import com.jme3.util.Screenshots;
import es.eucm.character.imageprocessing.filter.TransparentColorFilter;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;

public class ScreenshotMyAppState extends ScreenshotAppState{
    private static final Logger logger = Logger.getLogger(ScreenshotAppState.class.getName());
    private String filePath = null;
    private boolean capture = false;
    private Renderer renderer;
    private RenderManager rm;
    private ByteBuffer outBuf;
    private String appName;
    private int shotIndex = 0;
    private int width, height;
    
    private ScreenshotThread st;
    
    private TransparentColorFilter t;
    
    private String nameAnimation;
    
    private int cutWmin = Integer.MAX_VALUE;
    private int cutHmin = Integer.MAX_VALUE;
    private int cutWmax = 0;
    private int cutHmax = 0;
    
    private boolean first = false;
    /**
     * Using this constructor, the screenshot files will be written sequentially to the system
     * default storage folder.
     */
    public ScreenshotMyAppState() {
        this(null);
    }

    /**
     * This constructor allows you to specify the output file path of the screenshot.
     * Include the seperator at the end of the path.
     * Use an emptry string to use the application folder. Use NULL to use the system
     * default storage folder.
     * @param file The screenshot file path to use. Include the seperator at the end of the path.
     */
    public ScreenshotMyAppState(String filePath) {
        this.filePath = filePath;
    }

    /**
     * Set the file path to store the screenshot.
     * Include the seperator at the end of the path.
     * Use an emptry string to use the application folder. Use NULL to use the system
     * default storage folder.
     * @param file File path to use to store the screenshot. Include the seperator at the end of the path.
     */
    @Override
    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    @Override
    public void initialize(AppStateManager stateManager, Application app) {
        if (!super.isInitialized()){
            /*InputManager inputManager = app.getInputManager();
            inputManager.addMapping("ScreenShot", new KeyTrigger(KeyInput.KEY_SYSRQ));
            inputManager.addListener(this, "ScreenShot");*/

            List<ViewPort> vps = app.getRenderManager().getPostViews();
            ViewPort last = vps.get(vps.size()-1);
            
            last.addProcessor(this);
            appName = app.getClass().getSimpleName();
        }
        
        super.initialize(stateManager, app);
        //Delete the mapping for the KeyInput.KEY_SYSRQ key.
        InputManager inputManager = app.getInputManager();
        inputManager.deleteMapping("ScreenShot");
    }

    @Override
    public void onAction(String name, boolean value, float tpf) {
        if (value){
            capture = true;
        }
    }

    @Override
    public void takeScreenshot() {
        capture = true;
    }
    
    public void takeScreenshot(ScreenshotThread st) {
        this.st = st;
        capture = true;
    }

    @Override
    public void initialize(RenderManager rm, ViewPort vp) {
        renderer = rm.getRenderer();
        this.rm = rm;
        reshape(vp, vp.getCamera().getWidth(), vp.getCamera().getHeight());
    }

    @Override
    public boolean isInitialized() {
        return super.isInitialized() && renderer != null;
    }

    @Override
    public void reshape(ViewPort vp, int w, int h) {
        outBuf = BufferUtils.createByteBuffer(w * h * 4);
        width = w;
        height = h;
    }

    @Override
    public void preFrame(float tpf) {
    }

    @Override
    public void postQueue(RenderQueue rq) {
    }

    @Override
    public void postFrame(FrameBuffer out) {
        if (capture){
            capture = false;
            shotIndex++;

            /*Camera curCamera = rm.getCurrentCamera();
            int viewX = (int) (curCamera.getViewPortLeft() * curCamera.getWidth());
            int viewY = (int) (curCamera.getViewPortBottom() * curCamera.getHeight());
            int viewWidth = (int) ((curCamera.getViewPortRight() - curCamera.getViewPortLeft()) * curCamera.getWidth());
            int viewHeight = (int) ((curCamera.getViewPortTop() - curCamera.getViewPortBottom()) * curCamera.getHeight());

            //if (!viewPortInit){
                renderer.setViewPort(0, 0, width, height);*/
            //}
            renderer.readFrameBuffer(out, outBuf);
            
            //Transform ByteBuffer to BufferedImage
            BufferedImage bufferedImage = new BufferedImage(width, height, BufferedImage.TYPE_4BYTE_ABGR);
            Screenshots.convertScreenShot(outBuf, bufferedImage);
            
            t = new TransparentColorFilter(true, 15);
            t.setActive(true);
            t.transform(bufferedImage, width-1, height-1);

            getMinMaxImage(bufferedImage);
            
            outBuf = null;
            //System.gc();
                        
            //if (!viewPortInit){
                //renderer.setViewPort(viewX, viewY, viewWidth, viewHeight);
                //viewPortInit=true;
            //}
            //ScreenshotData data = new ScreenshotData(nameAnimation+shotIndex, bufferedImage, bufferedImage.getWidth(), bufferedImage.getHeight());
            File file = new File(filePath+File.separator+nameAnimation+shotIndex+".png");
            try {
                ImageIO.write(bufferedImage, "png", file);
            }
            catch (IOException ex) {
                logger.log(Level.SEVERE, "Error while saving screenshot", ex);
            }
            
            int cont = 0;
            int maxTime = 50;
            try {
                while((!file.exists() || file.length()==0) && cont<maxTime){
                    Thread.sleep(100);
                    cont++;
                }
            } catch (InterruptedException ex) {
                    Logger.getLogger(ScreenshotMyAppState.class.getName()).log(Level.SEVERE, null, ex);
            }
                    
            System.out.println("Añadido Nº "+shotIndex);;
            System.out.println();

            synchronized(st){
                st.notify();
            }
        }
    }

    public void setAnimationName(String nameAnimation) {
        this.nameAnimation = nameAnimation;
    }

    public void resetShotIndex() {
        this.shotIndex = 0;
    }
    
    public void resetMinMaxImage() {
        this.cutWmin = Integer.MAX_VALUE;
        this.cutHmin = Integer.MAX_VALUE;
        this.cutWmax = 0;
        this.cutHmax = 0;
    }
    
    private void getMinMaxImage(BufferedImage bi){
        int w = bi.getWidth();
        int h = bi.getHeight();
        
        /*for (int i = 0; i<w; i++){
            for (int j = 0; j<h; j++){
                //110 es el nivel de transparencia hasta el que se quiere borrar el color
                //Si el color de fondo se construye con 0.4 de alpha, float(0.4*255 = 102). Redondeando, 110
                if((bi.getRGB(i,j)&0xFF000000) > 110){
                    bi.setRGB(i, j, new Color(0,0,0,0).getRGB()); //Asigna un nuevo color, por ejemplo el negro
                }
            }
        }*/

        //Cut horizontally
        int current_cutWmin = 0;
        salida:
        for (int i = 0; i<w; i++){
            for (int j = 0; j<h; j++){
                //if the pixel isn't transparent
                if (bi.getRGB(i, j) < 0){
                    if (i!= 0){
                        current_cutWmin = i-1;
                    }
                    else{
                        current_cutWmin = i;
                    }
                    break salida;
                }
            }
        }
        
        int current_cutWmax = 0;
        salida:
        for (int i = w-1; i>=0; i--){
            for (int j = h-1; j>=0; j--){
                //if the pixel isn't transparent
                if (bi.getRGB(i, j) < 0){
                    if (i!= w-1){
                        current_cutWmax = i+1;
                    }
                    else{
                        current_cutWmax = i;
                    }
                    break salida;
                }
            }
        }
        
        //Cut vertically
        int current_cutHmin = 0;
        salida:
        for (int i = 0; i<h; i++){
            for (int j = 0; j<w; j++){
                //if the pixel isn't transparent
                if (bi.getRGB(j, i) < 0){
                    if (i!=0){
                        current_cutHmin = i-1;
                    }
                    else{
                        current_cutHmin = i;
                    }
                    break salida;
                }
            }
        }
        
        int current_cutHmax = 0;
        salida:
        for (int i = h-1; i>=0; i--){
            for (int j = w-1; j>=0; j--){
                //if the pixel isn't transparent
                if (bi.getRGB(j, i) < 0){
                    if (i!=h-1){
                        current_cutHmax = i+1;
                    }
                    else{
                        current_cutHmax = i;
                    }
                    break salida;
                }
            }
        }
        if (current_cutWmin < cutWmin){
            cutWmin = current_cutWmin;
        }
        if (current_cutWmax > cutWmax){
            cutWmax = current_cutWmax;
        }
        if (current_cutHmin < cutHmin){
            cutHmin = current_cutHmin;
        }
        if (current_cutHmax > cutHmax){
            cutHmax = current_cutHmax;
        }
        
        System.out.println("Xmin: "+cutWmin);
        System.out.println("Xmax: "+cutWmax);
        System.out.println("Ymin: "+cutHmin);
        System.out.println("Ymax: "+cutHmax);
    }

    public int getCutWmin() {
        return cutWmin;
    }

    public int getCutHmin() {
        return cutHmin;
    }

    public int getCutWmax() {
        return cutWmax;
    }

    public int getCutHmax() {
        return cutHmax;
    }
}