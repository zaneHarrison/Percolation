import java.util.*;

public class PercolationDFSFast extends PercolationDFS {
    public PercolationDFSFast(int size) {
        super(size);
    }
    @Override
    protected void updateOnOpen(int row, int col) {
        if (row == 0) {
            dfs(row, col);
        }
        else if (inBounds(row-1, col) && isFull(row-1, col)) {
            dfs(row, col);
        }
        else if (inBounds(row+1, col) && isFull(row+1, col)) {
            dfs(row, col);
        }
        else if (inBounds(row, col-1) && isFull(row, col-1)) {
            dfs(row, col);
        }
        else if (inBounds(row, col+1) && isFull(row, col+1)) {
            dfs(row, col);
        }
    }
}
