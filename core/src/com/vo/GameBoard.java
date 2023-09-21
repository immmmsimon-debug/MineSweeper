package com.vo;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class GameBoard {



        int[][] board = new int[16][30];

        int numsBombs;

        int xoffset = 50;
        int yoffset = 100;

    Texture emptyTile;
    Texture emptyFloorTile;

    Texture oneTile;
    Texture twoTile;
    Texture threeTile;
    Texture fourTile;
    Texture fiveTile;
    Texture sixTile;
    Texture sevenTile;
    Texture eightTile;

    //+10 to any tile means it has been "Flagged"
    //we will know the value of that tile is just tile - 10
    public static final int EMPTYTILE = 0, BOMB = -1, EMPTYFLOOR = -2, FLAG = 10;



        public GameBoard(){
            board = new int[16][30];
            numsBombs = 99;
            loadGraphics();
        }

        public GameBoard(int numRows, int numCols, int numsBombs){
            board = new int[numRows][numCols];
            this.numsBombs = numsBombs;
            loadGraphics();
        }


        private void loadGraphics() {
        emptyTile = new Texture("emptyTile.jpg");
        emptyFloorTile = new Texture("empty floor.jpg");
        oneTile = new Texture("oneTile.jpg");
        twoTile = new Texture("twoTile.jpg");
        threeTile = new Texture("threeTile.jpg");
        fourTile = new Texture("fourTile.jpg");
        fiveTile = new Texture("fiveTile.jpg");
        sixTile = new Texture("fiveTile.jpg");
        sevenTile = new Texture("sevenTile.jpg");
        eightTile = new Texture("eightTile.jpg");
    }
    //draw the bombs
    public void draw(SpriteBatch spriteBatch){
            for(int row=0; row< board.length; row++){
                for(int col=0; col<board[row].length; col++){
                    spriteBatch.draw(emptyTile,xoffset+(col*25),yoffset+(row*25));
                }
            }
    }


    //randomly place bombs
    private void placeBombs(){

    }



}
