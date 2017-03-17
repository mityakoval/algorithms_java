
/**
 * 
 */

import edu.princeton.cs.algs4.WeightedQuickUnionUF;

/**
 * @author mityakoval
 *
 */
public class Percolation {

    private Site[][] grid = null;
    private int virtualTopId;
    private int virtualBottomId;
    private int openSites = 0;
    private WeightedQuickUnionUF wqu = null;

    public Percolation(int n) {
        if (n <= 0)
            throw new IllegalArgumentException("n should be greater than 0");
        grid = new Site[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                int index = ((n - 1) * i) + j + i;
                Site site = new Site(index);
                grid[i][j] = site;
            }

        }

        virtualTopId = n * n;
        virtualBottomId = n * n + 1;
        wqu = new WeightedQuickUnionUF(n * n + 2);
        for (int i = 0; i < n; i++) {
            wqu.union(i, virtualTopId);
            wqu.union((n * n - n + i), virtualBottomId);
        }
    }

    public void open(int row, int col) {
        row--;
        col--;
        if ((row < 0 || row >= grid.length) || (col < 0 || col >= grid.length)) {
            throw new IndexOutOfBoundsException("Coordinates are out of the bounds of the grid");
        }
        Site site = grid[row][col];
        if (site.isOpen())
            return;
        site.open();
        openSites++;
        connectNeighbour(site, row - 1, col);
        connectNeighbour(site, row + 1, col);
        connectNeighbour(site, row, col - 1);
        connectNeighbour(site, row, col + 1);
    }

    public boolean isOpen(int row, int col) {
        row--;
        col--;
        if ((row < 0 || row >= grid.length) || (col < 0 || col >= grid.length)) {
            throw new IndexOutOfBoundsException("Coordinates are out of the bounds of the grid");
        }
        return grid[row][col].isOpen();
    }

    public boolean isFull(int row, int col) {
        row--;
        col--;
        if ((row < 0 || row >= grid.length) || (col < 0 || col >= grid.length)) {
            throw new IndexOutOfBoundsException("Coordinates are out of the bounds of the grid");
        }
        Site site = grid[row][col];
        return (site.isOpen() && wqu.connected(site.getIndex(), virtualTopId));
    }

    public int numberOfOpenSites() {
        return openSites;
    }

    public boolean percolates() {
        return wqu.connected(virtualTopId, virtualBottomId);
    }

    private void connectNeighbour(Site site, int row, int col) {
        if ((row >= 0 && row < grid.length) && (col >= 0 && col < grid.length)) {
            Site nbSite = grid[row][col];
            if (nbSite.isOpen()) {
                wqu.union(site.getIndex(), nbSite.getIndex());
            }
        }
    }

    /**
     * @param args
     */
    public static void main(String[] args) {
        // TODO Auto-generated method stub
    }
    
    private class Site {
        private int index;
        private boolean open;

        public Site(int index) {
            this.index = index;
            this.open = false;
        }

        public boolean isOpen() {
            return this.open;
        }

        public void open() {
            this.open = true;
        }

        public int getIndex() {
            return index;
        }
    }
}
