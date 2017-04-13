package at.michi.map;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import at.michi.map.managers.MyAssetManager;
import at.michi.map.screens.ServerScreen;
import at.michi.map.screens.MenuScreen;
import at.michi.map.screens.GameScreen;
import at.michi.map.screens.ClientScreen;

/**
 * Created by Michi on 07.04.17.
 */
public class Main extends Game {

	private SpriteBatch batch;
	public Viewport screenPort;
	public MyAssetManager myAssetManager = new MyAssetManager();


	@Override
	public void create () {
		batch = new SpriteBatch();
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

	public void gotoServerScreen(){
		ServerScreen serverScreen = new ServerScreen(this);
		setScreen(serverScreen);
	}

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
