import java.util.LinkedList;
import java.util.*;

public class PercolationBFS extends PercolationDFSFast{
    public PercolationBFS(int size) {
        super(size);
    }
    @Override
    protected void dfs(int row, int col) {
        //Filling current cell, creating queue, adding current cell
        myGrid[row][col] = FULL;
        Queue<int[]> q = new LinkedList<>();
        q.add(new int[] {row, col});

        //Loop
        while (!q.isEmpty()) {
            int[] current = q.remove();
            int rrow = current[0];
            int ccol = current[1];
            //Checking cell above
            if (inBounds(rrow-1, ccol) && !isFull(rrow-1, ccol) && isOpen(rrow-1, ccol)) {
                myGrid[rrow-1][ccol] = FULL;
                q.add(new int[] {rrow-1, ccol});
            }
            //Checking cell below
            if (inBounds(rrow+1, ccol) && !isFull(rrow+1, ccol) && isOpen(rrow+1, ccol)) {
                myGrid[rrow+1][ccol] = FULL;
                q.add(new int[] {rrow+1, ccol});
            }
            //Checking cell to the left
            if (inBounds(rrow, ccol-1) && !isFull(rrow, ccol-1) && isOpen(rrow, ccol-1)) {
                myGrid[rrow][ccol-1] = FULL;
                q.add(new int[] {rrow, ccol-1});
            }
            //Checking cell to the right
            if (inBounds(rrow, ccol+1) && !isFull(rrow, ccol+1) && isOpen(rrow, ccol+1)) {
                myGrid[rrow][ccol+1] = FULL;
                q.add(new int[] {rrow, ccol+1});
            }
        }
    }
}
