package at.michi.map.screens;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
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
import at.michi.map.client.MyClient;
import at.michi.map.server.MyServer;

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

    public GameScreen(final Main game, Object object, String servername, String clientname){
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

        Button serverClose = new TextButton("Server schliessen",mySkin,"default");
        serverClose.setSize(GameConstants.col_width*2.2f,GameConstants.row_height);
        serverClose.setPosition(GameConstants.centerX - serverClose.getWidth()/2,GameConstants.centerY);

        Button clientClose = new TextButton("Disconnect",mySkin,"default");
        clientClose.setSize(GameConstants.col_width*2,GameConstants.row_height);
        clientClose.setPosition(GameConstants.centerX - clientClose.getWidth()/2,GameConstants.centerY);

        Label playerOne = new Label("Player 1:",mySkin,"big");
        playerOne.setSize(GameConstants.col_width*2,GameConstants.row_height*2);
        playerOne.setPosition(GameConstants.centerX - playerOne.getWidth()*2,GameConstants.centerY + GameConstants.row_height*1.5f);
        playerOne.setAlignment(Align.center);
        playerOne.setColor(Color.RED);
        stage.addActor(playerOne);

        Label namePlayerServer = new Label(servername,mySkin,"big");
        namePlayerServer.setSize(GameConstants.col_width*2,GameConstants.row_height*2);
        namePlayerServer.setPosition(GameConstants.centerX - namePlayerServer.getWidth()*2,GameConstants.centerY + GameConstants.row_height);
        namePlayerServer.setAlignment(Align.center);

        Label playerTwo = new Label("Player 2:",mySkin,"big");
        playerTwo.setSize(GameConstants.col_width*2,GameConstants.row_height*2);
        playerTwo.setPosition(GameConstants.centerX*2 - playerTwo.getWidth(),GameConstants.centerY + GameConstants.row_height*1.5f);
        playerTwo.setAlignment(Align.center);
        playerTwo.setColor(Color.RED);
        stage.addActor(playerTwo);

        Label namePlayerClient = new Label(clientname,mySkin,"big");
        namePlayerClient.setSize(GameConstants.col_width*2,GameConstants.row_height*2);
        namePlayerClient.setPosition(GameConstants.centerX*2 - namePlayerClient.getWidth(),GameConstants.centerY + GameConstants.row_height);
        namePlayerClient.setAlignment(Align.center);

        if(object instanceof MyServer){
            final MyServer server = (MyServer) object;
            serverClose.addListener(new InputListener(){
                @Override
                public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                    server.stopServer();
                    return true;
                }

                @Override
                public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
                    super.touchUp(event, x, y, pointer, button);
                }
            });
            stage.addActor(serverClose);


        } else if(object instanceof MyClient){
            final MyClient client = (MyClient) object;
            client.sendWelcomeMessage();
            clientClose.addListener(new InputListener(){
                @Override
                public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                    client.disconnect();
                    return true;
                }

                @Override
                public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
                    super.touchUp(event, x, y, pointer, button);
                }
            });
            stage.addActor(clientClose);
        }

        stage.addActor(namePlayerServer);
        stage.addActor(namePlayerClient);

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
