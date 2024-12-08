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

        ArrayList<Integer> my_list = new ArrayList<>(); //do this to make it 1d from 2d.
        for (int row = 0; row < ROWS; row++) {
            for (int col = 0; col < COLS; col++) {
                my_list.add(row * COLS + col);
            }
        }
        Collections.shuffle(my_list);
        for (int i = 0; i < NUM_MINES; i++) {
            int linearIndex = my_list.get(i);
            int row = linearIndex / COLS; // Compute row from linear index
            int col = linearIndex % COLS; // Compute column from linear index
            ms_board[row][col].setCellType(SlSpot.CELL_TYPE.MINE);
        }
        setNextNearestPoints();
    }

    public int getRows() {
        return ROWS;
    }
    public int getCols() {
        return COLS;
    }
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
    public void setNextNearestPoints() {
        for (int row = 0; row < ROWS; row++) {
            for (int col = 0; col < COLS; col++) {
                if (ms_board[row][col].getCellType() == SlSpot.CELL_TYPE.MINE) {
                    ms_board[row][col].cell_score = 0;
                    continue;
                }
                int numMines = 0;
                int numGold = 0;

                for (int i = -1; i <= 1; i++) {
                    for (int j = -1; j <= 1; j++) {
                        if (i == 0 && j == 0) continue;

                        int neighborRow = (row + i + ROWS) % ROWS;
                        int neighborCol = (col + j + COLS) % COLS;

                        if (ms_board[neighborRow][neighborCol].getCellType() == SlSpot.CELL_TYPE.MINE) {
                            numMines++;
                        } else {
                            numGold++;
                        }
                    }
                }
                ms_board[row][col].cell_score = (numMines * 10) + (numGold * 5);
            }
        }
    }
    public SlSpot.CELL_TYPE getCellType(int row, int col) {
        if (row >= 0 && row < ROWS && col >= 0 && col < COLS) {
            return ms_board[row][col].getCellType();
        } else {
            throw new IndexOutOfBoundsException("Row or column index is out of bounds.");
        }
    }
    public SlSpot.CELL_STATUS getCellStatus(int row, int col) {
        if (row >= 0 && row < ROWS && col >= 0 && col < COLS) {
            return ms_board[row][col].getCellStatus();
        } else {
            throw new IndexOutOfBoundsException("Row or column index is out of bounds.");
        }
    }
    public int getScore(int row, int col) {
        return ms_board[row][col].cell_score;
    }







    public boolean isGameActive() {
        return gameActive;
    }
    public void printCellScores() {
        for (int i = 0; i < ROWS; i++) {
            for (int j = 0; j < COLS; j++) {
                System.out.print(ms_board[i][j].cell_score + " ");
            }
            System.out.println();
        }
    }
    public CellData[][] getBoard() {
        return ms_board;
    }





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
        public SlSpot.CELL_STATUS getCellStatus() {
            return status;
        }

        public void setCellType(SlSpot.CELL_TYPE type) {
            this.type = type;
        }

        public void setCellStatus(int score) {
            this.cell_score = score;
        }
    }
}
