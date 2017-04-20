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
import com.badlogic.gdx.scenes.scene2d.ui.TextField;
import com.badlogic.gdx.utils.Align;
import at.michi.map.GameConstants;
import at.michi.map.Main;
import at.michi.map.server.MyServer;
import de.tomgrill.gdxdialogs.core.GDXDialogs;
import de.tomgrill.gdxdialogs.core.GDXDialogsSystem;
import de.tomgrill.gdxdialogs.core.dialogs.GDXButtonDialog;
import de.tomgrill.gdxdialogs.core.dialogs.GDXProgressDialog;
import de.tomgrill.gdxdialogs.core.listener.ButtonClickListener;

/**
 * Created by Jasmin on 09.04.17.
 */
public class ServerScreen implements Screen {

    final Main game;
    private Stage stage;
    private Skin mySkin;
    MyServer server;
    private TextField textservername;
    GDXDialogs dialogs = GDXDialogsSystem.install();
    GDXProgressDialog progressDialog;

    public ServerScreen(final Main game){
        this.game = game;
        stage = new Stage(game.screenPort);
        mySkin = new Skin(Gdx.files.internal(GameConstants.skin));
        Gdx.input.setInputProcessor(stage);

        progressDialog = dialogs.newDialog(GDXProgressDialog.class);
        progressDialog.setTitle("Laden");
        progressDialog.setMessage("Bitte warten bis der Gegner beigetreten ist...");

        Label LnameServer = new Label("Bitte Namen eingeben:",mySkin,"big");
        LnameServer.setSize(GameConstants.col_width*2,GameConstants.row_height*2);
        LnameServer.setPosition(GameConstants.centerX - LnameServer.getWidth()/2,GameConstants.centerY + GameConstants.row_height);
        LnameServer.setAlignment(Align.center);

        textservername = new TextField("",mySkin);
        mySkin.getFont("font").getData().setScale(2);
        textservername.setSize(GameConstants.col_width*2,GameConstants.row_height);
        textservername.setPosition(GameConstants.centerX - textservername.getWidth()/2,GameConstants.centerY/1.2f + GameConstants.row_height);

        stage.addActor(textservername);
        stage.addActor(LnameServer);

        final GDXButtonDialog bDialogName = dialogs.newDialog(GDXButtonDialog.class);
        bDialogName.setTitle("Error");
        bDialogName.setMessage("Sie müssen einen Namen eingeben!");

        bDialogName.setClickListener(new ButtonClickListener() {

            @Override
            public void click(int button) {
            }
        });

        bDialogName.addButton("OK");

        //

        Button createServer = new TextButton("Create Server",mySkin,"default");
        createServer.setSize(GameConstants.col_width*2,GameConstants.row_height);
        createServer.setPosition(GameConstants.centerX - createServer.getWidth()/2,GameConstants.centerY/1.9f + GameConstants.row_height);
        createServer.addListener(new InputListener(){
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                if(textservername.getText().isEmpty()){
                    bDialogName.build().show();
                    return true;
                }
                server = new MyServer(55555, 55556);
                Gdx.app.postRunnable(new Runnable() {
                    @Override
                    public void run() {
                        progressDialog.build().show();
                    }
                });

                Gdx.app.postRunnable(new Runnable() {
                    @Override
                    public void run() {
                        server.startServer(server, game, textservername.getText());
                        progressDialog.dismiss();
                    }
                });
                return true;
            }

            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
                super.touchUp(event, x, y, pointer, button);
            }
        });

        stage.addActor(createServer);

        Button homeBtn = new TextButton("HOME",mySkin,"default");
        homeBtn.setSize(GameConstants.col_width,GameConstants.row_height);
        homeBtn.setPosition(GameConstants.centerX - homeBtn.getWidth()/2,GameConstants.centerY/4.1f + GameConstants.row_height);
        homeBtn.addListener(new InputListener(){
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                game.gotoMenuScreen(); //MenuScreen
                return true;
            }

            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
                super.touchUp(event, x, y, pointer, button);
            }
        });

        stage.addActor(homeBtn);

    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(135/255f, 206/255f, 235/255f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        stage.act();
        stage.draw();

    }

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
