class Solution {
    private char[][] grid;
    private int m;
    private int n;
    private Boolean[][][] memo;

    public boolean hasValidPath(char[][] grid) {

        this.grid = grid;
        this.m = grid.length;
        this.n = grid[0].length;

        // A valid parentheses string must have even length
        if ((m + n - 1) % 2 != 0) {
            return false;
        }

        // Must start with '('
        if (grid[0][0] == ')') {
            return false;
        }

        memo = new Boolean[m][n][m + n + 1];

        return dfs(0, 0, 1);
    }

    private boolean dfs(int row, int col, int balance) {

        // Invalid balance
        if (balance < 0) {
            return false;
        }

        // Reached destination
        if (row == m - 1 && col == n - 1) {
            return balance == 0;
        }

        // Already calculated
        if (memo[row][col][balance] != null) {
            return memo[row][col][balance];
        }

        boolean possible = false;

        // Move down
        if (row + 1 < m) {

            int nextBalance = balance;

            if (grid[row + 1][col] == '(') {
                nextBalance++;
            } else {
                nextBalance--;
            }

            possible = dfs(row + 1, col, nextBalance);
        }

        // Move right
        if (!possible && col + 1 < n) {

            int nextBalance = balance;

            if (grid[row][col + 1] == '(') {
                nextBalance++;
            } else {
                nextBalance--;
            }

            possible = dfs(row, col + 1, nextBalance);
        }

        memo[row][col][balance] = possible;

        return possible;
    }
}