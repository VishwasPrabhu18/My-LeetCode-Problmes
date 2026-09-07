class Solution {
    public int distinctSubseqII(String s) {
        final long MOD = 1_000_000_007;

        long dp = 1; // empty subsequence

        // last[c] = dp value before the previous occurrence of c
        long[] last = new long[26];

        for (char c : s.toCharArray()) {
            int idx = c - 'a';

            long oldDp = dp;

            // Add subsequences formed by appending c
            dp = (2 * dp) % MOD;

            // Remove duplicates caused by previous occurrence of c
            dp = (dp - last[idx] + MOD) % MOD;

            // Store dp before this occurrence
            last[idx] = oldDp;
        }

        // Remove empty subsequence
        return (int) ((dp - 1 + MOD) % MOD);
    }
}