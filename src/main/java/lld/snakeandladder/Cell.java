package lld.snakeandladder;

public abstract class Cell {
    private final int start;
    private final int end;
    private final CellType cellType;

    public Cell(int start, int end, CellType cellType) {
        this.start = start;
        this.end = end;
        this.cellType = cellType;
    }

    public int getStart() {
        return start;
    }

    public int getEnd() {
        return end;
    }

    public CellType getCellType() {
        return cellType;
    }
}
