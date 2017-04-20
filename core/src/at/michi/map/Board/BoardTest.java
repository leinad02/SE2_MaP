package at.michi.map.Board;

import static at.michi.map.GameConstants.screenHeight;
import static at.michi.map.GameConstants.screenWidth;

/**
 * Created by SW on 19.04.2017.
 */

public class BoardTest {
    private Field[][] board;
    private int width;
    private int height;

    public BoardTest() {
        this.width = screenWidth;
        this.height = screenHeight;
        this.board = new Field[this.width][this.height];
        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                board[i][j] = new Field();
            }
        }
    }
}
