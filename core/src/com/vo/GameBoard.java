package com.vo;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class GameBoard {

    public static final int North = 0, NorthEast = 1, East = 2,
            SouthEast = 3, South = 4, SouthWest = 5,
            West = 6, NorthWest = 7;

    int[][] board = new int[16][30];

    int numsBombs;
    private int numFlags=0;

    int xoffset;
    int yoffset;

    private Texture emptyTile;
    private Texture emptyFloorTile;

    private Texture oneTile;
    private Texture twoTile;
    private Texture threeTile;
    private Texture fourTile;
    private Texture fiveTile;
    private Texture sixTile;
    private Texture sevenTile;
    private Texture eightTile;
    private Texture bombTile;
    private Texture flagTile;


    //+10 to any tile means it has been "Flagged"
    //we will know the value of that tile is just tile - 10
    public static final int EMPTYTILE = 0, BOMB = -1, EMPTYFLOOR = 10, FLAG = 10;


    public GameBoard() {
        board = new int[16][30];
        numsBombs = 50;
        numFlags = numsBombs;
        loadGraphics();
        xoffset = (int) (MyGdxGame.World_Width - 30 * 25) / 2;
        yoffset = (int) ((MyGdxGame.World_Height - 16 * 25) / 2) + (25 * 16);
        placeBombs();
        setNumberOnBoard();
    }

    public GameBoard(int numRows, int numCols, int numsBombs) {
        board = new int[numRows][numCols];
        this.numsBombs = numsBombs;
        numFlags = numsBombs;
        loadGraphics();
        xoffset = (int) (MyGdxGame.World_Width - numCols * 25) / 2;
        yoffset = (int) ((MyGdxGame.World_Height - numRows * 25) / 2) + (25 * numRows);
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
        flagTile = new Texture("flagTile.jpg");
    }

    public void setNumberOnBoard() {
        for (int row = 0; row < board.length; row++) {
            for (int col = 0; col < board[row].length; col++) {
                if (board[row][col] == EMPTYTILE) {
                    board[row][col] = getEightNeighbors(row, col);


                }
            }
        }
    }


    public Location getTileAt(int mouse_x, int mouse_y) {
        int col;
        int row;
        int reverseYOffset = (int) (MyGdxGame.World_Height - yoffset);
        int rowdif = mouse_y - reverseYOffset + 25;
        if (mouse_x - xoffset < 0 || rowdif < 0)
            return null;
        col = (mouse_x - xoffset) / 25;
        row = (int) (rowdif) / 25;

        if (isValid(new Location(row, col)))
            return new Location(row, col);
        else
            return null;


    }

    public int getEightNeighbors(int i, int j) {
        int counter = 0;
        Location tempLoc1 = new Location(i, j);
        if (isValid(tempLoc1) == true) {
            if (board[i][j] == EMPTYTILE) {
                for (int row = i - 1; row <= i + 1; row++) {
                    for (int col = j - 1; col <= j + 1; col++) {
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


    public boolean isValid(Location loc) {
        return loc.getRow() >= 0 && loc.getRow() < board.length && loc.getCol() >= 0 && loc.getCol() < board[(loc.getRow())].length;
    }


    //draw the bombs
    public void draw(SpriteBatch spriteBatch) {
        for (int row = 0; row < board.length; row++) {
            for (int col = 0; col < board[row].length; col++) {
                if (board[row][col] < 9) {
                    spriteBatch.draw(emptyTile, xoffset + (col * 25), yoffset + (row * -25));
                } else if (board[row][col] == 10){//if tile click +10
                    spriteBatch.draw(emptyFloorTile, xoffset + (col * 25), yoffset + (row * -25));
                } else if (board[row][col] == 9) {
                    spriteBatch.draw(bombTile, xoffset + (col * 25), yoffset + (row * -25));
                } else if (board[row][col] == 11) {
                    spriteBatch.draw(oneTile, xoffset + (col * 25), yoffset + (row * -25));
                } else if (board[row][col] == 12) {
                    spriteBatch.draw(twoTile, xoffset + (col * 25), yoffset + (row * -25));
                } else if (board[row][col] == 13) {
                    spriteBatch.draw(threeTile, xoffset + (col * 25), yoffset + (row * -25));
                } else if (board[row][col] == 14) {
                    spriteBatch.draw(fourTile, xoffset + (col * 25), yoffset + (row * -25));
                } else if (board[row][col] == 15) {
                    spriteBatch.draw(fiveTile, xoffset + (col * 25), yoffset + (row * -25));
                } else if (board[row][col] == 16) {
                    spriteBatch.draw(sixTile, xoffset + (col * 25), yoffset + (row * -25));
                } else if (board[row][col] == 17) {
                    spriteBatch.draw(sevenTile, xoffset + (col * 25), yoffset + (row * -25));
                } else if (board[row][col] == 18) {
                    spriteBatch.draw(eightTile, xoffset + (col * 25), yoffset + (row * -25));
                }
                else if (board[row][col] >= 19) {
                    spriteBatch.draw(flagTile, xoffset + (col * 25), yoffset + (row * -25));
                }
            }
        }
    }


    //randomly place bombs
    private void placeBombs() {
        int bombPlaced = 0;

        while (bombPlaced < numsBombs) {
            int randomRow = (int) (Math.random() * board.length);
            int randomCol = (int) (Math.random() * board[0].length);
            if (board[randomRow][randomCol] == EMPTYTILE) {
                bombPlaced++;
                board[randomRow][randomCol] = BOMB;
            }
        }
    }


    public void leftClicked(int x, int y) {
        Location clickLoc = getTileAt(x, y);


        //calid and on uncoverd tile
        if (clickLoc != null && board[clickLoc.getRow()][clickLoc.getCol()] < 9) {
            //uncover the tile
            board[clickLoc.getRow()][clickLoc.getCol()] += 10;
            //if we uncover an empty floor, initiate recrusive uncover method
             if( board[clickLoc.getRow()][clickLoc.getCol()] == EMPTYFLOOR){
                uncoverArea(clickLoc);
            }
        }
    }
    // recursive method that "propogates" through the board uncovering an area of empty tiles, and the surrounding numbers
    private void uncoverArea(Location loc){
        //get the surrounding of the loc
        for (int row = loc.getRow() - 1; row <= loc.getRow() + 1; row++) {
            for (int col = loc.getCol() - 1; col <= loc.getCol() + 1; col++) {
                Location tempLoc = new Location(row, col);
                if (isValid(tempLoc) == true) {
                    if (tempLoc != null && (board[row][col] == EMPTYTILE) ) {
                        board[row][col] += 10;
                        uncoverArea(tempLoc);
                        for (int row1 = loc.getRow() - 1; row <= loc.getRow() + 1; row++) {
                            for (int col1 = loc.getCol() - 1; col <= loc.getCol() + 1; col++) {

                            }
                        }
                    }
                }

            }
        }






    }

    public void rightClicked(int x, int y) {
        Location clickLoc = getTileAt(x, y);
        if (clickLoc != null && board[clickLoc.getRow()][clickLoc.getCol()] < 9) {
            board[clickLoc.getRow()][clickLoc.getCol()] += 20;
            numFlags--;
        } else if (clickLoc != null && board[clickLoc.getRow()][clickLoc.getCol()] >= 19) {
            board[clickLoc.getRow()][clickLoc.getCol()] -= 20;
            numFlags++;
        }
    }

    public int getNumFlags(){
        return numFlags;
    }
    public int getNumsBomb() {
        return numsBombs;
    }
}
