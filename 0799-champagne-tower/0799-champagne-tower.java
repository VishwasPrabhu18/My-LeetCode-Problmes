class Solution {
    public double champagneTower(int poured, int query_row, int query_glass) {
        double[][] dp = new double[100][100];

        dp[0][0] = poured;

        for (int row = 0; row < query_row; row++) {

            for (int glass = 0; glass <= row; glass++) {

                if (dp[row][glass] > 1) {

                    double overflow = (dp[row][glass] - 1) / 2.0;

                    dp[row + 1][glass] += overflow;
                    dp[row + 1][glass + 1] += overflow;
                }
            }
        }

        return Math.min(1.0, dp[query_row][query_glass]);
    }
}