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

import java.util.Random;

import at.michi.map.GameConstants;
import at.michi.map.Main;

import static java.lang.String.valueOf;

/**
 * Created by Jasmin on 20.04.2017.
 */

public class EventfieldScreen implements Screen {


    final Main game;
    private Stage stage;
    private Skin mySkin;
    Random random = new Random();
    int number = (random.nextInt(10) + 1);
    //Randomzahl zwischen 1 und 10
    String num = valueOf(number);

    public EventfieldScreen(final Main game){
        this.game = game;
        stage = new Stage(game.screenPort);
        mySkin = new Skin(Gdx.files.internal(GameConstants.skin));
        Gdx.input.setInputProcessor(stage);

        Label QuestionEvent = new Label("Ist die Würfelanzahl gerade oder ungerade?",mySkin,"big");
        QuestionEvent.setSize(GameConstants.col_width*2,GameConstants.row_height*2);
        QuestionEvent.setPosition(GameConstants.centerX - QuestionEvent.getWidth()/2,GameConstants.centerY + GameConstants.row_height);
        QuestionEvent.setAlignment(Align.center);

        stage.addActor(QuestionEvent);

        //man muss entscheiden, ob gerade oder ungerade Zahl
        Label RolltheDice = new Label(num,mySkin,"big");
        RolltheDice.setSize(GameConstants.col_width*2,GameConstants.row_height*2);
        RolltheDice.setPosition(GameConstants.centerX - QuestionEvent.getWidth()/2,GameConstants.centerY/1.2f + GameConstants.row_height);
        //number ausgeben in dem Label

        stage.addActor(RolltheDice);

        Button oddNumber = new TextButton("ungerade Zahl",mySkin,"default");
        oddNumber.setSize(GameConstants.col_width*2,GameConstants.row_height);
        oddNumber.setPosition(GameConstants.centerX - oddNumber.getWidth()/2,GameConstants.centerY/1.9f + GameConstants.row_height);
        oddNumber.addListener(new InputListener(){
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                do {
                    number = number % 2;
                }while(number>1);

                if(number==1){
                    //game.gotoGameScreen();
                    //Man müsste dann wieder zum GameScreen zurück und den Spieler nun um ein 4 Felder nach vor bringen
                }else{
                    //game.gotoGameScreen
                }
                return true;
            }

            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
                super.touchUp(event, x, y, pointer, button);
            }
        });

        stage.addActor(oddNumber);

        Button evenNumber = new TextButton("gerade Zahl",mySkin,"default");
        evenNumber.setSize(GameConstants.col_width*2,GameConstants.row_height);
        evenNumber.setPosition(GameConstants.centerX - evenNumber.getWidth()/2,GameConstants.centerY/4.1f + GameConstants.row_height);
        evenNumber.addListener(new InputListener(){
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                do {
                    number = number % 2;
                }while(number>1);

                if(number==0){
                    //game.gotoGameScreen();
                    //Man müsste dann wieder zum GameScreen zurück und den Spieler nun um ein 4 Felder nach vor bringen
                }else{
                    //game.gotoGameScreen
                }
                return true;
            }

            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
                super.touchUp(event, x, y, pointer, button);
            }
        });

        stage.addActor(evenNumber);

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
