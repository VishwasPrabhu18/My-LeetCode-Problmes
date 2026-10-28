class Solution {
    public int minCost(int[][] grid) {
        int m = grid.length;
        int n = grid[0].length;
        int B = 1024;

        boolean[][] prev = new boolean[n][B];

        for (int i = 0; i < m; i++) {
            boolean[][] curr = new boolean[n][B];

            for (int j = 0; j < n; j++) {
                int val = grid[i][j];

                if (i == 0 && j == 0) {
                    curr[j][val] = true;
                    continue;
                }

                if (i > 0) {
                    for (int x = 0; x < B; x++) {
                        if (prev[j][x]) {
                            curr[j][x ^ val] = true;
                        }
                    }
                }

                if (j > 0) {
                    for (int x = 0; x < B; x++) {
                        if (curr[j - 1][x]) {
                            curr[j][x ^ val] = true;
                        }
                    }
                }
            }

            prev = curr;
        }

        for (int x = 0; x < B; x++) {
            if (prev[n - 1][x]) {
                return x;
            }
        }

        return -1;
    }
}