import java.util.*;

public class PercolationUF implements IPercolate {
    private IUnionFind myFinder;
    private boolean[][] myGrid;
    private int myOpenCount;
    private final int VTOP;
    private final int VBOTTOM;

    public PercolationUF(IUnionFind finder, int size) {
        finder.initialize(size * size + 2);
        myFinder = finder;
        myOpenCount = 0;
        myGrid = new boolean[size][size];
        VTOP = size * size;
        VBOTTOM = size * size + 1;
    }

    protected boolean inBounds(int row, int col) {
        if (row < 0 || row >= myGrid.length) return false;
        if (col < 0 || col >= myGrid[0].length) return false;
        return true;
    }

    @Override
    public boolean isOpen(int row, int col) {
        if (!inBounds(row, col)) {
            throw new IndexOutOfBoundsException(
                    String.format("(%d,%d) not in bounds", row, col));
        }
        return myGrid[row][col];
    }

    @Override
    public boolean isFull(int row, int col) {
        if (!inBounds(row, col)) {
            throw new IndexOutOfBoundsException(
                    String.format("(%d,%d) not in bounds", row, col));
        }
        return myFinder.connected(row * myGrid.length + col, VTOP);
    }

    @Override
    public boolean percolates() {
        return myFinder.connected(VTOP, VBOTTOM);
    }

    @Override
    public int numberOfOpenSites() {
        return myOpenCount;
    }

    public int cellID(int row, int col) {
        return row * myGrid.length + col;
    }

    @Override
    public void open(int row, int col) {
        if (!inBounds(row, col)) {
            throw new IndexOutOfBoundsException(
                    String.format("(%d,%d) not in bounds", row, col));
        }
        if (isOpen(row, col)) {
            return;
        } else {
            myGrid[row][col] = true;
            if (inBounds(row - 1, col) && isOpen(row - 1, col)) {
                myFinder.union(cellID(row, col), cellID(row - 1, col));
            }
            if (inBounds(row + 1, col) && isOpen(row + 1, col)) {
                myFinder.union(cellID(row, col), cellID(row + 1, col));
            }
            if (inBounds(row, col - 1) && isOpen(row, col - 1)) {
                myFinder.union(cellID(row, col), cellID(row, col - 1));
            }
            if (inBounds(row, col + 1) && isOpen(row, col + 1)) {
                myFinder.union(cellID(row, col), cellID(row, col + 1));
            }
            if (row == 0) {
                myFinder.union(cellID(row, col), VTOP);
            }
            if (row == myGrid.length - 1) {
                myFinder.union(cellID(row, col), VBOTTOM);
            }
        }
    }
}