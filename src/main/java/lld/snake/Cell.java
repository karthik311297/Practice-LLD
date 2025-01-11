package lld.snake;

public class Cell {
    private int row;
    private int col;
    private CellEntity cellEntity;

    public Cell(int row, int col) {
        this.row = row;
        this.col = col;
    }

    public int getRow() {
        return row;
    }

    public int getCol() {
        return col;
    }

    public CellEntity getCellEntity() {
        return cellEntity;
    }

    public void setCellEntity(CellEntity cellEntity) {
        this.cellEntity = cellEntity;
    }
}
