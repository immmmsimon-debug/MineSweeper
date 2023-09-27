package com.vo;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class GameBoard {

    public static final int North=0, NorthEast=1,East=2,
            SouthEast=3,South=4,SouthWest=5,
            West=6,NorthWest=7;

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
    Texture bombTile;


    //+10 to any tile means it has been "Flagged"
    //we will know the value of that tile is just tile - 10
    public static final int EMPTYTILE = 0, BOMB = -1, EMPTYFLOOR = -2, FLAG = 10;



        public GameBoard() {
            board = new int[16][30];
            numsBombs = 99;
            loadGraphics();
            xoffset = (int) (MyGdxGame.World_Width - 30 * 25) / 2;
            yoffset = (int) ((MyGdxGame.World_Height - 16 * 25) / 2) + (25*16);
            placeBombs();
            setNumberOnBoard();
        }

        public GameBoard(int numRows, int numCols, int numsBombs){
            board = new int[numRows][numCols];
            this.numsBombs = numsBombs;
            loadGraphics();
            xoffset = (int)(MyGdxGame.World_Width-numCols*25)/2;
            yoffset = (int)((MyGdxGame.World_Height -numRows * 25) / 2) + (25*numRows);
            placeBombs();
            setNumberOnBoard();

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
        bombTile = new Texture("bomb.jpg");
    }

    public void setNumberOnBoard() {
        for (int row = 0; row < board.length; row++) {
            for (int col = 0; col < board[row].length; col++) {
                if (board[row][col] == EMPTYTILE){
                    board[row][col] = getEightNeighbors(row, col);

                }
            }
        }
    }

    public Location getTileAt(int mouse_x, int mouse_y){
            int col = 0;
            int row = 0;
            int reverseYOffset = (int)(yoffset - MyGdxGame.World_Height);
          col = (mouse_x - xoffset)/25;
          row = (mouse_y + reverseYOffset)/25;
          if(isValid(new Location(row,col)))
              return new Location(row,col);
          else
              return  null;






    }

    public int getEightNeighbors(int i , int j) {
        int counter = 0;
        Location tempLoc1 = new Location(i, j);
        if (isValid(tempLoc1) == true) {
            if (board[i][j] == EMPTYTILE) {
                for (int row = i-1; row <= i+1; row++) {
                    for (int col = j-1; col <= j+1 ; col++) {
                        Location tempLoc = new Location(row, col);
                        if (isValid(tempLoc) == true) {
                            if (board[row][col] == BOMB) {
                                counter++;
                            }
                        }
                    }
                }

            }

        }

        return counter;
    }


    public boolean isValid(Location loc){
        return loc.getRow() >= 0 && loc.getRow() < board.length && loc.getCol() >= 0 && loc.getCol() < board[(loc.getRow())].length;
    }




    //draw the bombs
    public void draw(SpriteBatch spriteBatch){
            for(int row=0; row< board.length; row++){
                for(int col=0; col<board[row].length; col++){
                    if(board[row][col] == EMPTYTILE) {
                        spriteBatch.draw(emptyTile, xoffset + (col * 25), yoffset + (row * -25));
                    }
                    else if(board[row][col] == BOMB) {
                        spriteBatch.draw(bombTile, xoffset + (col * 25), yoffset + (row * -25));
                    }
                    else if(board[row][col] == 1) {
                        spriteBatch.draw(oneTile, xoffset + (col * 25), yoffset + (row * -25));
                    }
                    else if(board[row][col] == 2) {
                        spriteBatch.draw(twoTile, xoffset + (col * 25), yoffset + (row * -25));
                    }
                    else if(board[row][col] == 3) {
                        spriteBatch.draw(threeTile, xoffset + (col * 25), yoffset + (row * -25));
                    }
                    else if(board[row][col] == 4) {
                        spriteBatch.draw(fourTile, xoffset + (col * 25), yoffset + (row * -25));
                    }
                    else if(board[row][col] == 5) {
                        spriteBatch.draw(fiveTile, xoffset + (col * 25), yoffset + (row * -25));
                    }
                    else if(board[row][col] == 6) {
                        spriteBatch.draw(sixTile, xoffset + (col * 25), yoffset + (row * -25));
                    }
                    else if(board[row][col] == 7) {
                        spriteBatch.draw(sevenTile, xoffset + (col * 25), yoffset + (row * -25));
                    }
                    else if(board[row][col] == 8) {
                        spriteBatch.draw(eightTile, xoffset + (col * 25), yoffset + (row * -25));
                    }
                }
            }
    }



    //randomly place bombs
    private void placeBombs(){
        int bombPlaced = 0;

        while (bombPlaced < numsBombs){
            int randomRow = (int)(Math.random()* board.length);
            int randomCol = (int)(Math.random()* board[0].length);
                if(board[randomRow][randomCol] == EMPTYTILE){
                bombPlaced++;
                board[randomRow][randomCol] = BOMB;
                }
        }
    }



}
