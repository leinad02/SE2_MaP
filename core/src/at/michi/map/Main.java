package at.michi.map;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

import at.michi.map.managers.MyAssetManager;
import at.michi.map.screens.ClientScreen;
import at.michi.map.screens.GameScreen;
import at.michi.map.screens.MenuScreen;

/**
 * Created by Michi on 07.04.17.
 */

/*
* Main Methode, erstellt den AssetManager
* und stellt Methoden für die Übergänge zwischen den Screens bereit*/

public class Main extends Game  {

    //Viewport legt fest, wieviel man von der Welt sehen möchte, unterstützt Kamera aber auch OpenGL
    private SpriteBatch batch;
    public Viewport screenPort;
    public MyAssetManager myAssetManager = new MyAssetManager();

    @Override
    public void create() {

        batch = new SpriteBatch();

        /*Orthographische Kamera wird in 2D-Umgebungen verwendet
        * mit dieser kann man sich herumbewegen in der 2D-Umgebung und zoomen */
        OrthographicCamera camera = new OrthographicCamera();
        camera.setToOrtho(false);
        screenPort = new ScreenViewport();
        this.setScreen(new MenuScreen(this));
    }

    public void gotoMenuScreen(){
        MenuScreen menuScreen = new MenuScreen(this);
        setScreen(menuScreen);
    }

    public void gotoClientScreen(){
        ClientScreen clientScreen = new ClientScreen(this);
        setScreen(clientScreen);
    }
/*			schon mal einen Verweis auf deinen Screen Jasmin ;)
	public void gotoServerScreen(){
		ServerScreen serverScreen = new ServerScreen(this);
		setScreen(serverScreen);
	}*/

    public void gotoGameScreen(){
        GameScreen gameScreen = new GameScreen(this);
        setScreen(gameScreen);
    }

    @Override
    public void render () {
        super.render();
    }

    @Override
    public void dispose () {
        super.dispose();
    }


}
