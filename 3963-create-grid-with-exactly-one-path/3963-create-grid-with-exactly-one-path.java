class Solution {
    public String[] createGrid(int m, int n) {
        char[][] grid = new char[m][n];

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                grid[i][j] = '#';
            }
        }

        for (int i = 0; i < n; i++) {
            grid[0][i] = '.';
        }

        for(int i=0; i<m; i++) {
            grid[i][n-1] = '.';
        }

        String[] result = new String[m];

        for (int i=0; i<m; i++) {
            result[i] = new String(grid[i]);
        }
        
        return result;
    }
}