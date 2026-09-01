class Solution {
    public int minMoves(String[] classroom, int energy) {
        int m = classroom.length;
        int n = classroom[0].length();

        int startR = 0;
        int startC = 0;

        // Assign an ID to every litter cell
        int[][] litterId = new int[m][n];
        for (int[] row : litterId) {
            Arrays.fill(row, -1);
        }

        int litterCount = 0;

        for (int r = 0; r < m; r++) {
            for (int c = 0; c < n; c++) {

                char ch = classroom[r].charAt(c);

                if (ch == 'S') {
                    startR = r;
                    startC = c;
                }

                if (ch == 'L') {
                    litterId[r][c] = litterCount++;
                }
            }
        }

        // No litter to collect
        if (litterCount == 0) {
            return 0;
        }

        int targetMask = (1 << litterCount) - 1;

        /*
         * visited[row][col][energy][mask]
         *
         * energy ranges from 0 to energy
         * mask ranges from 0 to 2^litterCount - 1
         */
        boolean[][][][] visited =
          new boolean[m][n][energy + 1][1 << litterCount];

        // BFS state:
        // [row, col, remainingEnergy, mask]
        Queue<int[]> queue = new ArrayDeque<>();

        queue.offer(new int[]{
          startR,
          startC,
          energy,
          0
        });

        visited[startR][startC][energy][0] = true;

        int[][] directions = {
          {1, 0},
          {-1, 0},
          {0, 1},
          {0, -1}
        };

        int moves = 0;

        while (!queue.isEmpty()) {

            int size = queue.size();

            // Process one BFS level
            while (size-- > 0) {

                int[] state = queue.poll();

                int r = state[0];
                int c = state[1];
                int currentEnergy = state[2];
                int mask = state[3];

                if (mask == targetMask) {
                    return moves;
                }

                // If energy is 0, we cannot make another move.
                // We could only continue if we were standing on R,
                // but entering R already resets energy to full.
                if (currentEnergy == 0) {
                    continue;
                }

                for (int[] dir : directions) {

                    int nr = r + dir[0];
                    int nc = c + dir[1];

                    // Outside grid
                    if (nr < 0 || nr >= m || nc < 0 || nc >= n) {
                        continue;
                    }

                    // Obstacle
                    if (classroom[nr].charAt(nc) == 'X') {
                        continue;
                    }

                    // Moving costs 1 energy
                    int newEnergy = currentEnergy - 1;

                    // Reset energy if we enter R
                    if (classroom[nr].charAt(nc) == 'R') {
                        newEnergy = energy;
                    }

                    // Collect litter if present
                    int newMask = mask;

                    if (classroom[nr].charAt(nc) == 'L') {
                        int id = litterId[nr][nc];
                        newMask |= (1 << id);
                    }

                    if (!visited[nr][nc][newEnergy][newMask]) {

                        visited[nr][nc][newEnergy][newMask] = true;

                        queue.offer(new int[]{
                          nr,
                          nc,
                          newEnergy,
                          newMask
                        });
                    }
                }
            }

            moves++;
        }

        return -1;
    }
}