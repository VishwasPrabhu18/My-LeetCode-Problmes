class Solution {
    public long[] resultArray(int[] nums, int k) {
        long[] result = new long[k];

        // dp[r] = number of subarrays ending at the
        // previous index with product % k == r
        long[] dp = new long[k];

        for (int num : nums) {

            int value = num % k;

            long[] current = new long[k];

            // Start a new subarray with nums[i]
            current[value]++;

            // Extend all previous subarrays
            for (int r = 0; r < k; r++) {

                if (dp[r] == 0) {
                    continue;
                }

                int newRemainder = (r * value) % k;

                current[newRemainder] += dp[r];
            }

            // Every subarray ending here contributes
            // to the final answer.
            for (int r = 0; r < k; r++) {
                result[r] += current[r];
            }

            dp = current;
        }

        return result;
    }
}