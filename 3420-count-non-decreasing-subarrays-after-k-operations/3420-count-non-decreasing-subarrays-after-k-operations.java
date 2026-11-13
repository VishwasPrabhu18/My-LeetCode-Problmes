class Solution {
    public long countNonDecreasingSubarrays(int[] nums, int k) {
        int n = nums.length;

        // Prefix sums
        long[] prefix = new long[n + 1];

        for (int i = 0; i < n; i++) {
            prefix[i + 1] = prefix[i] + nums[i];
        }

        // Next strictly greater element
        int[] ng = new int[n];
        Arrays.fill(ng, n);

        Deque<Integer> stack = new ArrayDeque<>();

        for (int i = n - 1; i >= 0; i--) {
            while (!stack.isEmpty() &&
                   nums[stack.peek()] <= nums[i]) {
                stack.pop();
            }

            if (!stack.isEmpty()) {
                ng[i] = stack.peek();
            }

            stack.push(i);
        }

        // Binary lifting
        int LOG = 1;
        while ((1 << LOG) <= n) {
            LOG++;
        }

        int[][] jump = new int[LOG][n + 1];
        long[][] sum = new long[LOG][n + 1];

        for (int i = 0; i < n; i++) {
            jump[0][i] = ng[i];
            sum[0][i] = (long) nums[i] * (ng[i] - i);
        }

        jump[0][n] = n;

        for (int p = 1; p < LOG; p++) {
            for (int i = 0; i <= n; i++) {
                int mid = jump[p - 1][i];

                jump[p][i] = jump[p - 1][mid];

                sum[p][i] = sum[p - 1][i]
                          + sum[p - 1][mid];
            }
        }

        // Count valid subarrays
        long answer = 0;

        for (int r = 0; r < n; r++) {
            int low = 0;
            int high = r;

            while (low < high) {
                int mid = low + (high - low) / 2;

                long cost = getCost(
                    mid, r, nums, prefix, jump, sum, LOG
                );

                if (cost <= k) {
                    high = mid;
                } else {
                    low = mid + 1;
                }
            }

            answer += r - low + 1;
        }

        return answer;
    }

    private long getCost(
        int l, int r,
        int[] nums,
        long[] prefix,
        int[][] jump,
        long[][] sum,
        int LOG
    ) {
        int cur = l;
        long maxSum = 0;

        // Calculate sum of prefix maximums
        for (int p = LOG - 1; p >= 0; p--) {
            int nxt = jump[p][cur];

            if (nxt <= r + 1) {
                maxSum += sum[p][cur];
                cur = nxt;
            }
        }

        if (cur <= r) {
            maxSum += (long) nums[cur] * (r - cur + 1);
        }

        long originalSum = prefix[r + 1] - prefix[l];

        return maxSum - originalSum;
    }
}