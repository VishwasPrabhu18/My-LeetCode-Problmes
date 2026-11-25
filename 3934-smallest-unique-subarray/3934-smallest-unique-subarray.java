class Solution {
    public int smallestUniqueSubarray(int[] nums) {
        int n = nums.length;

        int[] sa = buildSuffixArray(nums);
        int[] rank = new int[n];

        for (int i = 0; i < n; i++) {
            rank[sa[i]] = i;
        }

        int[] lcp = new int[n];
        int h = 0;

        for (int i = 0; i < n; i++) {
            int r = rank[i];

            if (r == n - 1) {
                h = 0;
                continue;
            }

            int j = sa[r + 1];

            while (i + h < n && j + h < n &&
                   nums[i + h] == nums[j + h]) {
                h++;
            }

            lcp[r] = h;

            if (h > 0) {
                h--;
            }
        }

        int ans = n;

        for (int i = 0; i < n; i++) {
            int maxLcp = 0;

            if (i > 0) {
                maxLcp = Math.max(maxLcp, lcp[i - 1]);
            }

            if (i < n - 1) {
                maxLcp = Math.max(maxLcp, lcp[i]);
            }

            int suffixLength = n - sa[i];

            int uniqueLength = Math.min(suffixLength, maxLcp + 1);

            if (uniqueLength > maxLcp) {
                ans = Math.min(ans, uniqueLength);
            }
        }

        return ans;
    }

    private int[] buildSuffixArray(int[] nums) {
        int n = nums.length;

        Integer[] order = new Integer[n];

        for (int i = 0; i < n; i++) {
            order[i] = i;
        }

        Arrays.sort(order, (a, b) -> Integer.compare(nums[a], nums[b]));

        int[] rank = new int[n];

        for (int i = 0; i < n; i++) {
            if (i > 0 &&
                nums[order[i]] != nums[order[i - 1]]) {
                rank[order[i]] = rank[order[i - 1]] + 1;
            } else if (i > 0) {
                rank[order[i]] = rank[order[i - 1]];
            }
        }

        int[] sa = new int[n];

        for (int i = 0; i < n; i++) {
            sa[i] = order[i];
        }

        int[] temp = new int[n];

        for (int k = 1; k < n; k *= 2) {
            final int len = k;

            Arrays.sort(order, (a, b) -> {
                if (rank[a] != rank[b]) {
                    return Integer.compare(rank[a], rank[b]);
                }

                int ra = a + len < n ? rank[a + len] : -1;
                int rb = b + len < n ? rank[b + len] : -1;

                return Integer.compare(ra, rb);
            });

            temp[order[0]] = 0;

            for (int i = 1; i < n; i++) {
                int a = order[i - 1];
                int b = order[i];

                int a2 = a + len < n ? rank[a + len] : -1;
                int b2 = b + len < n ? rank[b + len] : -1;

                temp[b] = temp[a] +
                    ((rank[a] != rank[b] || a2 != b2) ? 1 : 0);
            }

            for (int i = 0; i < n; i++) {
                rank[i] = temp[i];
                sa[i] = order[i];
            }

            if (rank[sa[n - 1]] == n - 1) {
                break;
            }
        }

        return sa;
    }
}