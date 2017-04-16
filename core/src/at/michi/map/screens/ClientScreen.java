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
import at.michi.map.client.MyClient;
import de.tomgrill.gdxdialogs.core.GDXDialogs;
import de.tomgrill.gdxdialogs.core.GDXDialogsSystem;
import de.tomgrill.gdxdialogs.core.dialogs.GDXButtonDialog;
import de.tomgrill.gdxdialogs.core.listener.ButtonClickListener;

/**
 * Created by SW on 14.04.2017.
 */

//In diesen Screen gelangt man, nachdem man auf dem MenuScreen den Button Client gedrückt hat

public class ClientScreen implements Screen {

    final Main game;
    private Skin mySkin;
    private Stage stage;
    MyClient client;  //@Michael, schon mal eine Verbindung zu deinem Client vorbereitet
    GDXDialogs dialogs = GDXDialogsSystem.install();

    public ClientScreen(final Main game){
        this.game = game;
        mySkin = new Skin(Gdx.files.internal(GameConstants.skin));
        stage = new Stage(game.screenPort);
        Gdx.input.setInputProcessor(stage);

        Label LnameClient = new Label("Bitte Namen eingeben:",mySkin,"big");
        LnameClient.setSize(GameConstants.col_width*2,GameConstants.row_height*2);
        LnameClient.setPosition(GameConstants.centerX - LnameClient.getWidth()/2,GameConstants.centerY + GameConstants.row_height);
        LnameClient.setAlignment(Align.center);

        final TextField textclientname = new TextField("",mySkin);
        mySkin.getFont("font").getData().setScale(2);
        textclientname.setSize(GameConstants.col_width*2,GameConstants.row_height);
        textclientname.setPosition(GameConstants.centerX - textclientname.getWidth()/2,GameConstants.centerY/1.2f + GameConstants.row_height);

        stage.addActor(textclientname);
        stage.addActor(LnameClient);

        //Überschrift bzw. Aufforderung und deren Positionierung
        final Label ipTitle = new Label("Bitte geben Sie hier die IP-Adresse des Hosts ein:",mySkin, "big");
        ipTitle.setSize(GameConstants.col_width*2,GameConstants.row_height*2);
        ipTitle.setPosition(GameConstants.centerX - ipTitle.getWidth()/2,GameConstants.centerY/1.9f + GameConstants.row_height/1.5f);
        ipTitle.setAlignment(Align.center);
        stage.addActor(ipTitle);

        // Textfeld um die IP einzugeben, wird unten bei TouchDown wieder aufgerufen
        final TextField ipfield = new TextField("", mySkin, "default");
        mySkin.getFont("font").getData().setScale(2);
        ipfield.setSize(GameConstants.col_width*2,GameConstants.row_height);
        ipfield.setPosition(GameConstants.centerX - ipfield.getWidth()/2,GameConstants.centerY/2.1f + GameConstants.row_height/2);
        stage.addActor(ipfield);

        final GDXButtonDialog bDialog = dialogs.newDialog(GDXButtonDialog.class);
        bDialog.setTitle("Error");
        bDialog.setMessage("Sie müssen eine IP-Adresse eingeben!");

        bDialog.setClickListener(new ButtonClickListener() {

            @Override
            public void click(int button) {
            }
        });

        bDialog.addButton("OK");

        Button connectBtn = new TextButton("Connect",mySkin, "default");
        connectBtn.setSize(GameConstants.col_width,GameConstants.row_height);
        connectBtn.setPosition(GameConstants.centerX - connectBtn.getWidth()/2,GameConstants.centerY/2.5f + GameConstants.row_height);
        connectBtn.addListener(new InputListener(){

            /*Button Connect: hier wird ein Client erstellt und die Verbindungsmethode
            * des Clients aufgerufen, als Parameter wird die IP des Hosts/Servers übergeben
            * */

            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                if(ipfield.getText().isEmpty()){
                    bDialog.build().show();
                    return true;
                }
                /* @Michael: wie besprochen hier eine Vorbereitung für deinen Client,
                    bitte ersetze X und Y noch durch deinen gewählten UDP und TCP Port*/
                //Ports hinzugefügt von Michi
                client = new MyClient(55555, 55556, 5000);
                client.connect(client, game, ipfield.getText(), textclientname.getText());
                return true;
            }

            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
                super.touchUp(event, x, y, pointer, button);
            }
        });

        stage.addActor(connectBtn);

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
