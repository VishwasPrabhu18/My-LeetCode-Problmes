class Solution {
    public int minimumChanges(String s, int k) {
        int n = s.length();
        int INF = 1_000_000_000;

        // Step 1: Precompute substring costs
        int[][] cost = new int[n][n];

        for (int i = 0; i < n; i++) {
            for (int len = 2; i + len <= n; len++) {

                int best = INF;

                // Try every proper divisor
                for (int d = 1; d < len; d++) {

                    if (len % d != 0) {
                        continue;
                    }

                    int groupSize = len / d;
                    int changes = 0;

                    // Check palindrome condition for each group
                    for (int x = 0; x < d; x++) {

                        for (int t = 0; t < groupSize / 2; t++) {

                            int left = i + x + t * d;

                            int right = i + x
                              + (groupSize - 1 - t) * d;

                            if (s.charAt(left) != s.charAt(right)) {
                                changes++;
                            }
                        }
                    }

                    best = Math.min(best, changes);
                }

                cost[i][i + len - 1] = best;
            }
        }

        // Step 2: DP
        int[][] dp = new int[k + 1][n + 1];

        for (int[] row : dp) {
            Arrays.fill(row, INF);
        }

        dp[0][0] = 0;

        for (int p = 1; p <= k; p++) {

            for (int i = 2 * p; i <= n; i++) {

                // Previous partition point
                for (int j = 2 * (p - 1); j <= i - 2; j++) {

                    if (dp[p - 1][j] == INF) {
                        continue;
                    }

                    dp[p][i] = Math.min(
                      dp[p][i],
                      dp[p - 1][j] + cost[j][i - 1]
                    );
                }
            }
        }

        return dp[k][n];
    }
}