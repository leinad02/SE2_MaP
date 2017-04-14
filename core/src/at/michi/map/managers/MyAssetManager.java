package at.michi.map.managers;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.assets.loaders.SkinLoader;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;

import at.michi.map.GameConstants;

/**
 * Created by SW on 14.04.2017.
 */

/*
* hier ist der AssetManager der beim Verwalten der Assets und der Atlanten behilflich sein wird
 * @ Engin: dies wird vor allem beim Erdstellen der Animationen hilfreich sein*/

public class MyAssetManager {
    public final AssetManager manager = new AssetManager();

    public void queueAddSkin(){
        SkinLoader.SkinParameter parameter = new SkinLoader.SkinParameter(GameConstants.skinAtlas);
        manager.load(GameConstants.skin,Skin.class,parameter);
    }
}
