package at.michi.map.Board;

/**
 * Created by SW on 19.04.2017.
 */

public class Field {
    //um welche Art von Feld es sich hier handelt
    private int art;

    public Field(){
        this.art = 0;
    }
    public void setArt (int id) {
    this.art=id;
    }

     public int getArt(){
     return this.art;
     }
}
