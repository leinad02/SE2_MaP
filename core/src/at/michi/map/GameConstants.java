package at.michi.map;

import com.badlogic.gdx.Gdx;

/**
 * Created by Michi on 06.04.17.
 */

/*
* Klasse, die den Zugriff auf die Koordinaten des Spiels vereinfachen soll
* Sie berechnet automatisch die Breite und Höhe des Screens, zudem die Mitte des Screens
* und die Höhe und Breite der Zeilen und Spalten
* @Daniel, Engin: das könnt ihr dann für euren Player und das Spielbrett nutzen,
* damit die Darstellung unabhängig vom Device ist*/

public class GameConstants {
    public static final String skin = "skin/glassy-ui.json";
    public static final String skinAtlas = "skin/glassy-ui.atlas";
    public static final int screenWidth = Gdx.graphics.getWidth();
    public static final int screenHeight = Gdx.graphics.getHeight();
    public static final int centerX = screenWidth/2;
    public static final int centerY = screenHeight/2;
    public static final int col_width = screenWidth/8;
    public static final int row_height = screenHeight/8;
}
