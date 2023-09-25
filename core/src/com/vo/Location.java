package com.vo;

public class Location {
    private int row;
    private int col;


    public static final int North=0, NorthEast=1,East=2,
            SouthEast=3,South=4,SouthWest=5,
            West=6,NorthWest=7;

    public Location(int row, int col) {
        this.row = row;
        this.col = col;
    }
    public Location getLocInDirection( int dir){

        switch(dir){
            case North:
                return getNorth();
            case South:
                return getSouth();
            case West:
                return getWest();
            case East:
                return getEast();
            case NorthWest:
                return getNorthWest();
            case NorthEast:
                return getNorthEast();
            case SouthWest:
                return getSouthWest();
            case SouthEast:
                return getSouthEast();
            default:
                return null;
        }


    }
    public Location getNorth(){
        return new Location(row-1, col);
    }
    public Location getSouth(){
        return new Location(row+1,col);
    }

    public Location getWest(){
        return new Location(row, col-1);
    }

    public Location getEast(){
        return new Location(row, col+1);
    }
    public Location getNorthEast() {
        return new Location(row - 1, col + 1);
    }
    public Location getSouthEast(){
        return new Location(row+1,col+1);
    }
    public Location getSouthWest(){
        return new Location(row+1, col-1);
    }
    public Location getNorthWest(){
        return new Location(row-1, col-1);
    }

    public boolean equals(Location other){
        return row == other.row && col== other.col;
    }
    public int getRow() {
        return row;
    }

    public void setRow(int row) {
        this.row = row;
    }

    public int getCol() {
        return col;
    }

    public void setCol(int col) {
        this.col = col;
    }



    //eightWay -> true, method return direction using 8 naughticol
    //         -> false, method returns direction using 4 naughticol
















    @Override
    public String toString() {
        return "Location{" +
                "row=" + row +
                ", col=" + col +
                '}';
    }
}






