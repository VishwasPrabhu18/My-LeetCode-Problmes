class Solution {
    public int maxSelectedElements(int[] nums) {
        Arrays.sort(nums);

        Map<Integer, Integer> dp = new HashMap<>();
        int ans = 0;

        for (int x : nums) {
            // Extend a sequence ending at x - 1 using x unchanged
            int unchanged = dp.getOrDefault(x - 1, 0) + 1;

            // Extend a sequence ending at x using x + 1
            // Read this BEFORE updating dp[x].
            int incremented = dp.getOrDefault(x, 0) + 1;

            dp.put(x, Math.max(dp.getOrDefault(x, 0), unchanged));
            dp.put(x + 1, Math.max(dp.getOrDefault(x + 1, 0), incremented));

            ans = Math.max(ans, Math.max(dp.get(x), dp.get(x + 1)));
        }

        return ans;
    }
}