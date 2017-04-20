package at.michi.map.Board;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.Animation;

import at.michi.map.Main;

import static at.michi.map.GameConstants.centerX;
import static at.michi.map.GameConstants.centerY;


/**
 * Created by SW on 19.04.2017.
 */

public class Portal extends ApplicationAdapter {

    private SpriteBatch sBatch;
    private TextureAtlas portalAtlas;
    //um Animationsdaten zu speichern, welche Bilder und wie lange sie gezeigt werden sollen etc.
    private Animation animation;
    //Zeitpunkt für den Start der Animation
    private float timePassed = 0;

    int position;
    static int portalKey=1;


    //Portal wird an einem übergebenem Feld erstellt
    public Portal(int feld) {
        //this();
        this.position = feld;
    }

    /*
    * public int portalMove (int actPostion){
    * int newPosition = actPostition +((int) (Math.random() * 100) + 1);
    * return newPosition;}
    *
    * */



    @Override
    public void create() {
        sBatch = new SpriteBatch();
       portalAtlas = new TextureAtlas(Gdx.files.internal("Portal.atlas"));
        //angeben wie viele und was für Bilder in einer Sekunde gezeigt werden sollen und wo "getRegions()"
        animation = new Animation(1/30f,portalAtlas.getRegions());
    }

    @Override
    public void dispose() {
        sBatch.dispose();
        portalAtlas.dispose();
    }


    @Override
    public void render() {
       Gdx.gl.glClearColor(1,1,1,1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        sBatch.begin();
        timePassed += Gdx.graphics.getDeltaTime();
        sBatch.draw((Texture) animation.getKeyFrame(timePassed, true),centerX, centerY);
        sBatch.end();

    }
}
