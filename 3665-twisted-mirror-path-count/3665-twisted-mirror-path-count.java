class Solution {
    public int uniquePaths(int[][] grid) {
        int MOD = 1_000_000_007;

        int m = grid.length;
        int n = grid[0].length;

        long[][] dpRight = new long[m][n];
        long[][] dpDown = new long[m][n];

        // Starting position
        dpRight[0][0] = 1;

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {

                long fromLeft = dpRight[i][j];
                long fromTop = dpDown[i][j];

                if (grid[i][j] == 1) {

                    // Mirror entered while moving right:
                    // Redirect downward.
                    if (i + 1 < m) {
                        dpDown[i + 1][j] =
                            (dpDown[i + 1][j] + fromLeft) % MOD;
                    }

                    // Mirror entered while moving down:
                    // Redirect rightward.
                    if (j + 1 < n) {
                        dpRight[i][j + 1] =
                            (dpRight[i][j + 1] + fromTop) % MOD;
                    }

                } else {

                    // Empty cell: robot can choose right or down.
                    long ways = (fromLeft + fromTop) % MOD;

                    if (j + 1 < n) {
                        dpRight[i][j + 1] =
                            (dpRight[i][j + 1] + ways) % MOD;
                    }

                    if (i + 1 < m) {
                        dpDown[i + 1][j] =
                            (dpDown[i + 1][j] + ways) % MOD;
                    }
                }
            }
        }

        return (int) (
            (dpRight[m - 1][n - 1] +
             dpDown[m - 1][n - 1]) % MOD
        );
    }
}