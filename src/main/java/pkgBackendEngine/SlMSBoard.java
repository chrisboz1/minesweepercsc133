package pkgBackendEngine;

public class SlMSBoard {
    private int current_score;
//    private CellData[][] ms_board;
    private boolean gameActive;
    private int ROWS;
    private int NUM_MINES;
    private int COLS;

    public SlMSBoard(int rows, int numMines, int cols) {
        this.ROWS = rows;
        this.NUM_MINES = numMines;
        this.COLS = cols;
    }


    public void printBoard() {

    }

    public boolean isGameActive() {
        return gameActive;
    }
    private void printCellScores() {}


}
