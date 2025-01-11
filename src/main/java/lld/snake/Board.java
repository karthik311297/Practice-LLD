package lld.snake;

import java.util.Arrays;
import java.util.List;

public class Board {
    private final int rowSize;
    private final int colSize;
    private Cell[][] cells;

    public Board(int rowSize, int colSize, List<int[]> foodCellsIndex) {
        this.rowSize = rowSize;
        this.colSize = colSize;
        this.cells = new Cell[rowSize][colSize];
        initializeBoard(foodCellsIndex);
    }

    private void initializeBoard(List<int[]> foodCellsIndex) {
        for (int i = 0; i < rowSize; i++) {
            for (int j = 0; j < colSize; j++) {
                cells[i][j] = new Cell(i, j);
            }
        }
        for (int[] fci : foodCellsIndex) {
            int r = fci[0];
            int c = fci[1];
            Cell foodCell = new Cell(r, c);
            foodCell.setCellEntity(CellEntity.FOOD);
            cells[r][c] = foodCell;
        }
    }

    public void print() {
        for (int i = 0; i < rowSize; i++) {
            for (int j = 0; j < colSize; j++) {
                if (cells[i][j].getCellEntity() == CellEntity.SNAKE_BODY) {
                    System.out.print("*");
                } else if (cells[i][j].getCellEntity() == CellEntity.FOOD) {
                    System.out.print("o");
                } else {
                    System.out.print(".");
                }
            }
            System.out.println("");
        }
    }

    public Cell get(int row, int col) {
        return cells[row][col];
    }

    public void updateCell(int row, int col, CellEntity cellEntity) {
        Cell c = get(row, col);
        c.setCellEntity(cellEntity);
    }
}
