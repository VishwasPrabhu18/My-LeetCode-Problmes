class Solution {
    public int maxPalindromes(String s, int k) {
        int n = s.length();

        boolean[][] palindrome = new boolean[n][n];

        // Precompute palindromes
        for (int len = 1; len <= n; len++) {

            for (int left = 0; left + len - 1 < n; left++) {

                int right = left + len - 1;

                if (s.charAt(left) == s.charAt(right)
                        && (len <= 2 || palindrome[left + 1][right - 1])) {

                    palindrome[left][right] = true;
                }
            }
        }

        // dp[i] = maximum number using first i characters
        int[] dp = new int[n + 1];

        for (int i = 1; i <= n; i++) {

            // Don't take a palindrome ending at i - 1
            dp[i] = dp[i - 1];

            // Try every palindrome ending at i - 1
            for (int j = 0; j < i; j++) {

                int length = i - j;

                if (length >= k && palindrome[j][i - 1]) {
                    dp[i] = Math.max(dp[i], dp[j] + 1);
                }
            }
        }

        return dp[n];
    }
}