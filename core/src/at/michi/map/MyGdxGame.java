package at.michi.map;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class MyGdxGame extends ApplicationAdapter {
	//SpriteBatch batch;
	//Texture img;

	private SpriteBatch sBatch;
	private TextureAtlas portalAtlas;
	//um Animationsdaten zu speichern, welche Bilder und wie lange sie gezeigt werden sollen etc.
	private Animation animation;
	//Zeitpunkt für den Start der Animation
	private float timePassed = 0;
	
	@Override
	public void create () {
		//batch = new SpriteBatch();
		//img = new Texture("badlogic.jpg");
		sBatch = new SpriteBatch();
		portalAtlas = new TextureAtlas(Gdx.files.internal("portalsAtlas.atlas"));
		//angeben wie viele und was für Bilder in einer Sekunde gezeigt werden sollen und wo "getRegions()"
		animation = new Animation(1/5f,portalAtlas.getRegions());
	}

	@Override
	public void dispose () {
		//batch.dispose();
		//img.dispose();
		sBatch.dispose();
		portalAtlas.dispose();
	}


	@Override
	public void render () {
		/*Gdx.gl.glClearColor(1, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		batch.begin();
		batch.draw(img, 0, 0);
		batch.end();*/

		Gdx.gl.glClearColor(1,1,1,1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

		sBatch.begin();
		timePassed += Gdx.graphics.getDeltaTime();
		//true ist die Loop für die Animation
		sBatch.draw((TextureRegion) animation.getKeyFrame(timePassed,true),100,200);
		sBatch.end();
	}
	

}
