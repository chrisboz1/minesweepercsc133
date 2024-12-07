package pkgBackendEngine;

import pkgDriver.SlSpot;

import java.util.ArrayList;
import java.util.Collections;

public class SlMSBoard {
    private int current_score;
    private CellData[][] ms_board;
    private boolean gameActive;
    private int ROWS;
    private int NUM_MINES;
    private int COLS;

    public SlMSBoard(int rows, int numMines, int cols) {
        this.ROWS = rows;
        this.NUM_MINES = numMines;
        this.COLS = cols;
        this.current_score = 0;
        this.gameActive = true;

        ms_board = new CellData[ROWS][COLS];
        for (int i = 0; i < ROWS; i++) {
            for (int j = 0; j < COLS; j++) {
                ms_board[i][j] = new CellData(SlSpot.CELL_TYPE.GOLD, SlSpot.CELL_STATUS.NOT_EXPOSED);
            }
        }
        // Step 2: Create a list of linear indices
        ArrayList<Integer> my_list = new ArrayList<>();
        for (int row = 0; row < ROWS; row++) {
            for (int col = 0; col < COLS; col++) {
                my_list.add(row * COLS + col); // Linear index = row * COLS + col
            }
        }

        // Step 3: Shuffle the list
        Collections.shuffle(my_list);

        // Step 4: Place mines in the board
        for (int i = 0; i < NUM_MINES; i++) {
            int linearIndex = my_list.get(i);
            int row = linearIndex / COLS; // Compute row from linear index
            int col = linearIndex % COLS; // Compute column from linear index
            ms_board[row][col].setCellType(SlSpot.CELL_TYPE.MINE);
        }
    }

    // Debugging method to print the board
    public void printBoard() {
        for (int i = 0; i < ROWS; i++) {
            for (int j = 0; j < COLS; j++) {
                if (ms_board[i][j].getCellType() == SlSpot.CELL_TYPE.MINE) {
                    System.out.print("M ");
                } else {
                    System.out.print("G ");
                }
            }
            System.out.println();
        }
    }


    public boolean isGameActive() {
        return gameActive;
    }
    private void printCellScores() {}





    private static class CellData {
        private SlSpot.CELL_STATUS status;
        private SlSpot.CELL_TYPE type;
        private int cell_score;
        public CellData(SlSpot.CELL_TYPE type, SlSpot.CELL_STATUS status) {
            this.type = type;
            this.status = status;
        }

        public SlSpot.CELL_TYPE getCellType() {
            return type;
        }

        public void setCellType(SlSpot.CELL_TYPE type) {
            this.type = type;
        }
    }
}
