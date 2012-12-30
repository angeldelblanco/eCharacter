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
    private String selection;
    
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
    
    public void changeScreen(String param){
        selection = param;
        nifty.gotoScreen("changeScreen");
        if(param.equals("skin")){nifty.gotoScreen("skinScreen");}
        if(param.equals("eyes")){nifty.gotoScreen("eyesScreen");}
        if(param.equals("tshirt")){nifty.gotoScreen("tshirtScreen");}
        if(param.equals("trousers")){nifty.gotoScreen("trousersScreen");}
        if(param.equals("shoes")){nifty.gotoScreen("shoesScreen");}
        if(param.equals("accesories")){nifty.gotoScreen("accesoriesScreen");}
        if(param.equals("bones")){nifty.gotoScreen("bonesScreen");}
    }
    
    public String getMenu(String param){
        if(param.equals("yes")){
            return "Interface/MenuRojo.png";
        }
        else{
            return "Interface/MenuAzul.png";
        }
    }
    
    public void changeTexture(String steep){
        int i;
        if(steep.equals("+")){i = 1;}
        else{i = -1;}
        if(selection.equals("skin")){gui.changeSkin(i);}
        if(selection.equals("eyes")){gui.changeEyes(i);}
        if(selection.equals("tshirt")){gui.changeTShirt(i);}
        if(selection.equals("trousers")){gui.changeTrousers(i);}
        if(selection.equals("shoes")){}
        if(selection.equals("accesories")){}
    }
    
    public void showWindowChangeColor() throws InterruptedException{
        if(selection.equals("skin")){}
        if(selection.equals("eyes")){gui.showWindowChangeColorShoes();}
        if(selection.equals("tshirt")){gui.showWindowChangeColorTShirt();}
        if(selection.equals("trousers")){gui.showWindowChangeColorTrouser();}
        if(selection.equals("shoes")){}
        if(selection.equals("accesories")){}
    }
    
    public void screenshot() 
    {
        gui.screenshot();
    }
}