package com.mygdx.game.gameboard;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.mygdx.game.GameConstants;


/**
 * Created by Daniel on 10.04.2017.
 */

public class PlayerListener implements ApplicationListener {

    private String name;
    private Position position;
    private Color color;
    private int numPlayers = 2;

    private static final int FRAME_COLS = 8, FRAME_ROWS = 8; //ev. durch GameConstants ersetzen


    Animation<TextureRegion> walkAnimation; // frame type deklarieren (TextureRegion)
    Texture walkSheet;
    SpriteBatch spriteBatch;


    float stateTime;

    public PlayerListener(Color color, String name){
        this.name = name;
        position = new Position();
        this.color = color;
        //this.numPlayers = numPlayers;
    }

    public String getName(){
        return name;
    }

    public void setName(String n){
        name = n;
    }

    public Position getPosition(){
        return position;
    }

    public void setPosition(Position pos){
        position = pos;
    }

    public Color getColor(){
        return color;
    }

    public int getNumPlayers() {
        return numPlayers;
    }

    public void setNumPlayers(int numPlayers) {
        this.numPlayers = numPlayers;
    }

    public void setColor(Color c){
        color = c;
    }




    @Override
    public void create() {

        // Spritesheet als Texture laden
        walkSheet = new Texture(Gdx.files.internal("")); //gibts noch nicht


        //split utility method um 2D Array von TextureRegions zu erstellen
        TextureRegion[][] tmp = TextureRegion.split(walkSheet,
                GameConstants.col_width, GameConstants.row_height); //zuerst wird der Hintergrund hergenommen und dann die breite durch cols und höhe durch rows

        //versuchsweiser Laufalgorithmus - funktioniert nicht
        TextureRegion[] walkFrames = new TextureRegion[FRAME_COLS * FRAME_ROWS];
        int index = 0;
        while(FRAME_ROWS % 2 == 0) {
            for (int i = 0; i < FRAME_ROWS; i++) {
                for (int j = 0; j < FRAME_COLS; j++) {
                    walkFrames[index++] = tmp[i][j];
                }
            }
        }

        while (FRAME_ROWS % 2 ==1){
            for (int i =0; i < FRAME_ROWS; i++) {
                for (int j = FRAME_COLS; j >= 0; j--) {
                    walkFrames[index++] = tmp[i][j];
                }
            }
        }


        //Animation mit Frameintervall und Array der Frames initialisieren
        walkAnimation = new Animation<TextureRegion>(0.025f, walkFrames);

        spriteBatch = new SpriteBatch();
        stateTime = 0f;
    }

    @Override
    public void resize(int width, int height) {

    }

    @Override
    public void render() {
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        stateTime += Gdx.graphics.getDeltaTime();

        TextureRegion currentFrame = walkAnimation.getKeyFrame(stateTime, true);
        spriteBatch.begin();
        spriteBatch.draw(currentFrame, 50, 50);
        spriteBatch.end();
    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void dispose() { //Spritebatches und Textures müssen disposed werden
        spriteBatch.dispose();
        walkSheet.dispose();
    }
}


