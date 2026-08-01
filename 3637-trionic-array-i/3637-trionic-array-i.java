class Solution {
    public boolean isTrionic(int[] nums) {
        int n = nums.length;
        int i = 0;

        // First increasing segment
        while (i + 1 < n && nums[i] < nums[i + 1]) {
            i++;
        }

        // p must satisfy 0 < p
        if (i == 0) {
            return false;
        }

        // Decreasing segment
        int decStart = i;
        while (i + 1 < n && nums[i] > nums[i + 1]) {
            i++;
        }

        // Must have at least one decreasing step
        if (i == decStart) {
            return false;
        }

        // q must satisfy q < n - 1
        if (i == n - 1) {
            return false;
        }

        // Final increasing segment
        int incStart = i;
        while (i + 1 < n && nums[i] < nums[i + 1]) {
            i++;
        }

        // Must have at least one increasing step
        if (i == incStart) {
            return false;
        }

        return i == n - 1;
    }
}