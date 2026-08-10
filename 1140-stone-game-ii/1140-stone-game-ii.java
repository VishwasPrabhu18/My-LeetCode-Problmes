class Solution {
    private int[][][] dp;
    public int stoneGameII(int[] piles) {
        int n = piles.length;

        dp = new int[2][n][n + 1];
        for(int[][] layer: dp) {
            for(int[] row: layer) {
                Arrays.fill(row, -1);
            }
        }
        
        return dfs(1, 0, 1, piles);
    }
    
    private int dfs(int alice, int i, int M, int[] piles) {
        if(i == piles.length) return 0;
        if(dp[alice][i][M] != -1) return dp[alice][i][M];
        
        int res = alice == 1 ? 0 : Integer.MAX_VALUE;
        int total = 0;
        
        for(int X=1; X<=2 * M; X++) {
            if(i+X > piles.length) break;
            
            total += piles[i + X - 1];
            if(alice == 1) {
                res = Math.max(res, total + dfs(0, i + X, Math.max(M, X), piles));
            } else {
                res = Math.min(res, dfs(1, i+X, Math.max(M, X), piles));
            }
        }
        
        dp[alice][i][M] = res;
        return res;
    }
}