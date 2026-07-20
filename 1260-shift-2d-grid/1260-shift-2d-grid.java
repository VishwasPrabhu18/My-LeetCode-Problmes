class Solution {
    public List<List<Integer>> shiftGrid(int[][] grid, int k) {
        int rows = grid.length;
        int cols = grid[0].length;
        int size = rows * cols;

        k %= size;

        int[][] shifted = new int[rows][cols];

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {

                int index = i * cols + j;
                int newIndex = (index + k) % size;

                int newRow = newIndex / cols;
                int newCol = newIndex % cols;

                shifted[newRow][newCol] = grid[i][j];
            }
        }

        List<List<Integer>> ans = new ArrayList<>();

        for (int i = 0; i < rows; i++) {
            List<Integer> row = new ArrayList<>();
            for (int j = 0; j < cols; j++) {
                row.add(shifted[i][j]);
            }
            ans.add(row);
        }

        return ans;
    }
}