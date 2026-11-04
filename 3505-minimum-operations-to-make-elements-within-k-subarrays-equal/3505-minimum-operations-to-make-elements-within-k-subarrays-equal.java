class Solution {
    class Fenwick {
        int n;
        long[] tree;

        Fenwick(int n) {
            this.n = n;
            tree = new long[n + 1];
        }

        void update(int index, long value) {
            while (index <= n) {
                tree[index] += value;
                index += index & -index;
            }
        }

        long query(int index) {
            long sum = 0;

            while (index > 0) {
                sum += tree[index];
                index -= index & -index;
            }

            return sum;
        }

        // Find the smallest index whose prefix sum >= k.
        int findKth(long k) {
            int index = 0;

            int bit = Integer.highestOneBit(n);

            for (; bit > 0; bit >>= 1) {
                int next = index + bit;

                if (next <= n && tree[next] < k) {
                    index = next;
                    k -= tree[next];
                }
            }

            return index + 1;
        }
    }

    public long minOperations(int[] nums, int x, int k) {
        int n = nums.length;

        // Step 1: Coordinate compression.
        int[] sorted = nums.clone();
        Arrays.sort(sorted);

        int[] unique = Arrays.stream(sorted)
          .distinct()
          .toArray();

        int size = unique.length;

        int[] rank = new int[n];

        for (int i = 0; i < n; i++) {
            rank[i] = Arrays.binarySearch(unique, nums[i]) + 1;
        }

        // Step 2: Fenwick Trees.
        Fenwick countTree = new Fenwick(size);
        Fenwick sumTree = new Fenwick(size);

        long windowSum = 0;

        // Number of possible windows.
        int totalWindows = n - x + 1;

        long[] cost = new long[totalWindows];

        // Step 3: Calculate the cost of the first window.
        for (int i = 0; i < x; i++) {
            countTree.update(rank[i], 1);
            sumTree.update(rank[i], nums[i]);

            windowSum += nums[i];
        }

        // Step 4: Calculate costs for all sliding windows.
        for (int start = 0; start < totalWindows; start++) {

            // Lower median.
            int medianRank = countTree.findKth((x + 1L) / 2);

            long median = unique[medianRank - 1];

            // Count and sum of elements <= median.
            long countLeft = countTree.query(medianRank);
            long sumLeft = sumTree.query(medianRank);

            // Count and sum of elements > median.
            long countRight = x - countLeft;
            long sumRight = windowSum - sumLeft;

            // Minimum operations to make this window equal.
            cost[start] =
              median * countLeft - sumLeft
                + sumRight - median * countRight;

            // Slide the window.
            if (start + x < n) {

                // Remove outgoing element.
                int outgoing = start;

                countTree.update(rank[outgoing], -1);
                sumTree.update(rank[outgoing], -nums[outgoing]);

                windowSum -= nums[outgoing];

                // Add incoming element.
                int incoming = start + x;

                countTree.update(rank[incoming], 1);
                sumTree.update(rank[incoming], nums[incoming]);

                windowSum += nums[incoming];
            }
        }

        // Step 5: Dynamic Programming.
        long INF = Long.MAX_VALUE / 4;

        // prev[i] = minimum cost to select t-1 windows
        // from the first i elements.
        long[] prev = new long[n + 1];
        long[] curr = new long[n + 1];

        // Select exactly k windows.
        for (int t = 1; t <= k; t++) {

            Arrays.fill(curr, INF);

            for (int i = 1; i <= n; i++) {

                // Option 1: Skip the current element.
                curr[i] = curr[i - 1];

                // Option 2: Select a window of length x.
                if (i >= x && prev[i - x] != INF) {

                    long candidate =
                      prev[i - x] + cost[i - x];

                    curr[i] = Math.min(curr[i], candidate);
                }
            }

            long[] temp = prev;
            prev = curr;
            curr = temp;
        }

        return prev[n];
    }
}