package ca.cmpt276.assignment3.model;

/*
The class Game is where the logic of game has been spread out and all the functions connected to the main game
are present here.
 */

import java.util.Random;

public class Game {
    private int row;
    private int col;
    private int numFighters;
    private int fightersFound;
    private static Game instance;
    private int scanCounter;

    private int[][] grid= new int[getRow()][getCol()];

    private Game() {
    }

    public static Game getInstance() {
        if (instance == null) {
            instance = new Game();
        }
        return instance;
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

    public int getScanCounter() { return scanCounter;}

    public void resetScanCounter() { scanCounter = 0;}

    public void resetFightersFound() { fightersFound = 0;}

    public int getFightersFound() { return fightersFound;}

    public void updateFightersFound() { fightersFound++;}

    public int getNumFighters() {
        return numFighters;
    }

    public void setNumFighters(int numFighters) {
        this.numFighters = numFighters;
    }

    public void initializeFighterLocations(){
        grid = new int[getRow()][getCol()];
        int row2;
        int col2;
        int placeFighters = numFighters;


        Random random = new Random();
        while(placeFighters>0){
            //we constraint the random number here, nextInt was learnt from stackOverFlow
            row2 = random.nextInt(row);
            col2 = random.nextInt(col);

            if(grid[row2][col2] != 1){
                grid[row2][col2] = 1;
                placeFighters--;
            }
        }
    }


    public int radar(int row, int col){
        int fightersOnRadar = 0;

        for( int i =0; i<col; i++){
            if(grid[row][i]==1){
                fightersOnRadar++;
            }
        }
        for(int j = 0; j<row; j++){
            if(grid[j][col]==1){
                fightersOnRadar++;
            }
        }

        return fightersOnRadar;
    }

    public boolean checkTile(int row, int col){
        if(grid[row][col] == 1){
            grid[row][col] = 0;
            updateFightersFound();
            return true;
        } else {
            grid[row][col] = 2;
            return false;
        }
    }

    public boolean hasBeenRevealed(int i, int j) {
        return grid[i][j] == 2;
    }

    public void incrementScanCounter() {
        scanCounter++;
    }
}
