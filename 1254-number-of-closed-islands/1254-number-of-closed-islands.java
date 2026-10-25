class Solution {

    private int m, n;
    private boolean closed;

    public int closedIsland(int[][] grid) {
        m = grid.length;
        n = grid[0].length;

        int count = 0;

        for(int i=0; i<m; i++) {
            for(int j=0; j<n; j++) {
                if(grid[i][j] == 0) {
                    closed = true;
                    dfs(grid, i, j);
                    if(closed) {
                        count++;
                    }
                }
            }
        }

        return count;
    }

    private void dfs(int[][] grid, int r, int c) {
        if(r < 0 || r >= m || c < 0 || c >= n) {
            return;
        }

        if(grid[r][c] == 1) {
            return;
        }

        grid[r][c] = 1;

        if(r == 0 || r >= m - 1 || c == 0 || c >= n-1) {
            closed = false;
        }

        dfs(grid, r - 1, c);
        dfs(grid, r + 1, c);
        dfs(grid, r, c - 1);
        dfs(grid, r, c + 1);
    }
}