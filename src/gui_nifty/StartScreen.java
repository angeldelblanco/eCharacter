package gui_nifty;

import com.jme3.app.Application;
import com.jme3.app.state.AbstractAppState;
import com.jme3.app.state.AppStateManager;
import de.lessvoid.nifty.Nifty;
import de.lessvoid.nifty.screen.Screen;
import de.lessvoid.nifty.screen.ScreenController;
import tipos.Age;
import tipos.Gender;

public class StartScreen extends AbstractAppState implements ScreenController {
    
    private Nifty nifty;
    private Application app;
    private Screen screen;
    private Gui gui;
    
    public StartScreen(Gui gui){
        this.gui = gui;
    }
    
    public void startGame(String nextScreen) {
        nifty.gotoScreen(nextScreen);  // switch to another screen
    }

    public void quitGame() {
        app.stop();
    }

    public void bind(Nifty nifty, Screen screen) {
        this.nifty = nifty;
        this.screen = screen;
    }

    public void onStartScreen() {}

    public void onEndScreen() {}
    
    @Override
    public void initialize(AppStateManager stateManager, Application app) {
        this.app = app;
    }
    
    public void manSelected(String nextScreen) {
        nifty.gotoScreen(nextScreen);  // switch to another screen
        gui.setGender(Gender.Male);
    }
    
    public void womanSelected(String nextScreen) {
        nifty.gotoScreen(nextScreen);  // switch to another screen
        gui.setGender(Gender.Female);
    }
    
    public void adultSelected(String nextScreen) {
        nifty.gotoScreen(nextScreen);  // switch to another screen
        gui.setAge(Age.Adult);
        gui.loadModel();
    }
    
    public void youngSelected(String nextScreen) {
        nifty.gotoScreen(nextScreen);  // switch to another screen
        gui.setAge(Age.Young);
        gui.loadModel();
    }
    
    public void changeEyes()
    {
        gui.changeEyes();
    }
    
    public void changeTShirt()
    {
        gui.changeTShirt();
    }
    
    public void changeTrousers()
    {
        gui.changeTrousers();
    }
    
    public void changeShoes()
    {
        gui.changeShoes();
    }
    
    public void changeSkin()
    {
        gui.changeSkin();
    }
    
    public void showWindowChangeColorTrouser() throws InterruptedException
    {
        gui.showWindowChangeColorTrouser();
    }
    
    public void showWindowChangeColorTShirt() throws InterruptedException
    {
        gui.showWindowChangeColorTShirt();
    }
    
    public void showWindowChangeColorShoes() throws InterruptedException
    {
        gui.showWindowChangeColorShoes();
    }
    
    public void screenshot() 
    {
        gui.screenshot();
    }
}