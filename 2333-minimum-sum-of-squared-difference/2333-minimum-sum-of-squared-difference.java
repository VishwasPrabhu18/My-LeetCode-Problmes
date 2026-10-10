class Solution {
    public long minSumSquareDiff(int[] nums1, int[] nums2, int k1, int k2) {
        int n = nums1.length;
        int[] diff = new int[n];

        long k = (long) k1 + k2;
        long totalDiff = 0;
        int maxDiff = 0;

        for (int i = 0; i < n; i++) {
            diff[i] = Math.abs(nums1[i] - nums2[i]);
            totalDiff += diff[i];
            maxDiff = Math.max(maxDiff, diff[i]);
        }

        if (totalDiff <= k) {
            return 0;
        }

        int low = 0;
        int high = maxDiff;

        while (low < high) {
            int mid = low + (high - low) / 2;
            long cost = 0;

            for (int d : diff) {
                if (d > mid) {
                    cost += d - mid;
                }
            }

            if (cost <= k) {
                high = mid;
            } else {
                low = mid + 1;
            }
        }

        int level = low;
        long used = 0;

        for (int d : diff) {
            if (d > level) {
                used += d - level;
            }
        }

        long remaining = k - used;
        long answer = 0;

        for (int d : diff) {
            int finalDiff = Math.min(d, level);

            if (finalDiff == level && remaining > 0) {
                finalDiff--;
                remaining--;
            }

            answer += (long) finalDiff * finalDiff;
        }

        return answer;
    }
}