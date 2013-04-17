package es.eucm.character.export;

import com.jme3.app.Application;
import com.jme3.app.state.AppStateManager;
import com.jme3.app.state.ScreenshotAppState;
import com.jme3.input.InputManager;
import com.jme3.input.KeyInput;
import com.jme3.input.controls.KeyTrigger;
import com.jme3.renderer.Camera;
import com.jme3.renderer.RenderManager;
import com.jme3.renderer.Renderer;
import com.jme3.renderer.ViewPort;
import com.jme3.renderer.queue.RenderQueue;
import com.jme3.system.JmeSystem;
import com.jme3.texture.FrameBuffer;
import com.jme3.util.BufferUtils;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

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
    
    private ArrayList<ByteBuffer> list;

    /**
     * Using this constructor, the screenshot files will be written sequentially to the system
     * default storage folder.
     */
    public ScreenshotMyAppState() {
        this(null);
        list = new ArrayList<ByteBuffer>();
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
            InputManager inputManager = app.getInputManager();
            inputManager.addMapping("ScreenShot", new KeyTrigger(KeyInput.KEY_SYSRQ));
            inputManager.addListener(this, "ScreenShot");

            List<ViewPort> vps = app.getRenderManager().getPostViews();
            ViewPort last = vps.get(vps.size()-1);
            last.addProcessor(this);

            appName = app.getClass().getSimpleName();
        }

        super.initialize(stateManager, app);
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

            Camera curCamera = rm.getCurrentCamera();
            int viewX = (int) (curCamera.getViewPortLeft() * curCamera.getWidth());
            int viewY = (int) (curCamera.getViewPortBottom() * curCamera.getHeight());
            int viewWidth = (int) ((curCamera.getViewPortRight() - curCamera.getViewPortLeft()) * curCamera.getWidth());
            int viewHeight = (int) ((curCamera.getViewPortTop() - curCamera.getViewPortBottom()) * curCamera.getHeight());

            renderer.setViewPort(0, 0, width, height);
            renderer.readFrameBuffer(out, outBuf);
            renderer.setViewPort(viewX, viewY, viewWidth, viewHeight);
            
            list.add(outBuf);
            System.out.println("Añadido Nº "+shotIndex);
            System.out.println("Tamaño actual "+list.size());
        }
    }
    
    public void writeFiles(){
        int cont = 1;
        System.out.println("Tamaño del list: "+list.size());
        Iterator<ByteBuffer> it = list.iterator();
        while(it.hasNext()){
            ByteBuffer byteBuffer = it.next();
            File file;
            file = new File("assets"+File.separator+"Textures"+File.separator+"screenshots"+File.separator+"Prueba "+cont+".png");

            OutputStream outStream = null;
            try {
                outStream = new FileOutputStream(file);
                JmeSystem.writeImageFile(outStream, "png", byteBuffer, width, height);
            } catch (IOException ex) {
                logger.log(Level.SEVERE, "Error while saving screenshot", ex);
            } finally {
                if (outStream != null){
                    try {
                        outStream.close();
                    } catch (IOException ex) {
                        logger.log(Level.SEVERE, "Error while saving screenshot", ex);
                    }
                }
            }
            cont++;
        }
    }
}

