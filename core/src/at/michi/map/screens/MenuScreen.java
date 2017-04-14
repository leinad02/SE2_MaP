package at.michi.map.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.utils.Align;

import at.michi.map.GameConstants;
import at.michi.map.Main;

/**
 * Created by SW on 14.04.2017.
 */

/*
* Dies stellt quasi den "Starbildschirm" dar*/

public class MenuScreen implements Screen{

    final Main game;
    /*private Texture badlogic;*/
    private Skin mySkin;
    private Stage stage;

    public MenuScreen(final Main game) {
        this.game = game;
        game.myAssetManager.queueAddSkin();
        game.myAssetManager.manager.finishLoading();
        mySkin = game.myAssetManager.manager.get(GameConstants.skin);
        /*badlogic = new Texture(Gdx.files.internal("badlogic.jpg"));*/
        /*mySkin = new Skin(Gdx.files.internal(GameConstants.skin));*/
        stage = new Stage(game.screenPort);
        Gdx.input.setInputProcessor(stage);


        //Überschrift Game Menu und deren Positionierung
        Label gameTitle = new Label("GAME MENU", mySkin, "big");
        gameTitle.setSize(GameConstants.col_width * 2, GameConstants.row_height * 2);
        gameTitle.setPosition(GameConstants.centerX - gameTitle.getWidth() / 2, GameConstants.centerY + GameConstants.row_height);
        gameTitle.setAlignment(Align.center);

        //Button Server und dessen Positionierung
        Button startBtn = new TextButton("SERVER", mySkin, "default");
        startBtn.setSize(GameConstants.col_width * 2, GameConstants.row_height);
        startBtn.setPosition(GameConstants.centerX - startBtn.getWidth() / 2, GameConstants.centerY);
        startBtn.addListener(new InputListener() {      //Inputlister, um auf die Eingaben des Users zu reagieren

            //Beim Drücken des Buttons geht es zum ServerScreen, @Jasmin, das habe ich hier schonmal für dich vorbereitet
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                game.gotoServerScreen();
                return true;
            }

            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
                super.touchUp(event, x, y, pointer, button);
            }
        });

        //Button Client und dessen Positionierung
        Button settingsBtn = new TextButton("CLIENT", mySkin, "default");
        settingsBtn.setSize(GameConstants.col_width * 2, GameConstants.row_height);
        settingsBtn.setPosition(GameConstants.centerX - settingsBtn.getWidth() / 2, startBtn.getY() - GameConstants.row_height - 15);
        settingsBtn.addListener(new InputListener() {

            //Übergang zum ClientScreen
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                game.gotoClientScreen();
                return true;
            }

            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
                super.touchUp(event, x, y, pointer, button);
            }
        });

        //Buttons werden der Stage zugefügt
        stage.addActor(gameTitle);
        stage.addActor(startBtn);
        stage.addActor(settingsBtn);


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

        /*game.batch.begin();
        game.batch.draw(badlogic,0,0);
        game.batch.end();*/
    }

    //Anpassen an die Größe des Devices
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


    //Screen wird "gelöscht" um Speicher frei zu geben
    @Override
    public void dispose() {
         /*badlogic.dispose();*/
        /*game.batch.dispose();*/
        mySkin.dispose();
        stage.dispose();
    }

}
