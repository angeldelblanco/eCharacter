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
    
    public void changeEyes(String walk)
    {
        if(walk.contains("+")){
            gui.changeEyes(1);
        }
        else{
            gui.changeEyes(-1);
        }
    }
    
    public void changeTShirt(String walk)
    {
        if(walk.contains("+")){
            gui.changeTShirt(1);
        }
        else{
            gui.changeTShirt(-1);
        }
    }
    
    public void changeTrousers(String walk)
    {
        if(walk.contains("+")){
            gui.changeTrousers(1);
        }
        else{
            gui.changeTrousers(-1);
        }
    }
    
    public void changeShoes()
    {
        gui.changeShoes();
    }
    
    public void changeSkin(String walk)
    {
        if(walk.contains("+")){
            gui.changeSkin(1);
        }
        else{
            gui.changeSkin(-1);
        }
    }
    
    public void eyesScreen()
    {
        nifty.gotoScreen("eyesScreen");
    }
    
    public void tShirtScreen()
    {
        nifty.gotoScreen("tshirtScreen");
    }
    
    public void trousersScreen()
    {
        nifty.gotoScreen("trousersScreen");
    }
    
    public void shoesScreen()
    {
        nifty.gotoScreen("shoesScreen");
    }
    
    public void skinScreen()
    {
        nifty.gotoScreen("skinScreen");
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