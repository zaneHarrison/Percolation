import java.util.LinkedList;
import java.util.*;

public class PercolationBFS extends PercolationDFSFast{
    public PercolationBFS(int size) {
        super(size);
    }
    @Override
    protected void dfs(int row, int col) {
        myGrid[row][col] = FULL;
        Queue<int[]> q = new LinkedList<>();
        q.add(new int[] {row, col});
        while (!q.isEmpty()) {
            int[] current = q.remove();
            int r = current[0];
            int c = current[1];
            if (inBounds(r-1, c) && !isFull(r-1, c) && isOpen(r-1, c)) {
                myGrid[r-1][c] = FULL;
                q.add(new int[] {r-1, c});
            }
            if (inBounds(r+1, c) && !isFull(r+1, c) && isOpen(r+1, c)) {
                myGrid[r+1][c] = FULL;
                q.add(new int[] {r+1, c});
            }
            if (inBounds(r, c-1) && !isFull(r, c-1) && isOpen(r, c-1)) {
                myGrid[r][c-1] = FULL;
                q.add(new int[] {r, c-1});
            }
            if (inBounds(r, c+1) && !isFull(r, c+1) && isOpen(r, c+1)) {
                myGrid[r][c+1] = FULL;
                q.add(new int[] {r, c+1});
            }
        }
    }
}
