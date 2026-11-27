class Solution {
    public int maximumSaleItems(int[][] items, int budget) {
        int n = items.length;

        // Step 1: Calculate free copies for every item type.
        int[] free = new int[n];

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (i != j &&
                  items[j][0] % items[i][0] == 0) {
                    free[i]++;
                }
            }
        }

        // Step 2: Knapsack DP.
        int NEG = -1_000_000_000;

        int[] dp = new int[budget + 1];
        Arrays.fill(dp, NEG);
        dp[0] = 0;

        for (int i = 0; i < n; i++) {
            int price = items[i][1];
            int bonus = free[i];

            int[] next = dp.clone();

            // Group spending amounts by remainder modulo price.
            for (int r = 0; r < price && r <= budget; r++) {
                int best = NEG;

                for (int b = r, q = 0;
                     b <= budget;
                     b += price, q++) {

                    // Add the previous spending state,
                    // which allows at least one copy of this item.
                    if (b >= price && dp[b - price] != NEG) {
                        best = Math.max(
                          best,
                          dp[b - price] - (q - 1)
                        );
                    }

                    if (best != NEG) {
                        next[b] = Math.max(
                          next[b],
                          bonus + q + best
                        );
                    }
                }
            }

            dp = next;
        }

        // Step 3: We can spend at most budget.
        int answer = 0;

        for (int b = 0; b <= budget; b++) {
            answer = Math.max(answer, dp[b]);
        }

        return answer;
    }
}