package at.michi.map.screens;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.utils.Align;

import at.michi.map.GameConstants;
import at.michi.map.Main;

/**
 * Created by SW on 14.04.2017.
 */

/*In diesen Screen sollte man im Optimalfall erst gelangen, wenn der Client sich verbunden hat
@Michael: darum wolltest du dich kümmern ;)
Dieser Screen ist nur vorläufig und zum Testen für den verbindungsaufbau gedacht*/

public class GameScreen implements Screen {
    final Main game;
    private Stage stage;
    private Skin mySkin;

    public GameScreen(final Main game){
        this.game = game;
        stage = new Stage(game.screenPort);
        mySkin = new Skin(Gdx.files.internal(GameConstants.skin));
        Gdx.input.setInputProcessor(stage);

        ////Überschrift Welcome in the Game und deren Positionierung
        Label netTitle = new Label("Welcome in the Game!",mySkin,"big");
        netTitle.setSize(GameConstants.col_width*2,GameConstants.row_height*2);
        netTitle.setPosition(GameConstants.centerX - netTitle.getWidth()/2,GameConstants.centerY + GameConstants.row_height);
        netTitle.setAlignment(Align.center);

        stage.addActor(netTitle);
    }

    @Override
    public void show() {

    }

    //Farbe des Bildschirms und "zeichnen" der Stage
    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(135/255f, 206/255f, 235/255f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        stage.act();
        stage.draw();

    }

    //View für Perspektive auf das Spiel wird festgelegt --> Anpassen an die Größe des Devices
    @Override
    public void resize(int width, int height) {
        game.screenPort.update(width,height);

    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {
        mySkin.dispose();
        stage.dispose();

    }

}
