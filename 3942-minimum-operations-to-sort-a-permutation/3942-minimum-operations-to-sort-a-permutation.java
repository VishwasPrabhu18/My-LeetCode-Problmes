class Solution {
    public int minOperations(int[] nums) {
        int n = nums.length;

        boolean increasing = true;
        boolean decreasing = true;

        for (int i = 0; i < n; i++) {
            if (nums[i] != (nums[0] + i) % n) {
                increasing = false;
            }

            if (nums[i] != (nums[0] - i + n) % n) {
                decreasing = false;
            }
        }

        int ans = Integer.MAX_VALUE;

        if (increasing) {
            int rotations = (n - nums[0]) % n;
            ans = Math.min(rotations, n - rotations + 2);
        }

        if (decreasing) {
            int rotations = (n - nums[n - 1]) % n;
            ans = Math.min(ans, 1 + Math.min(rotations, n - rotations));
        }

        return ans == Integer.MAX_VALUE ? -1 : ans;
    }
}