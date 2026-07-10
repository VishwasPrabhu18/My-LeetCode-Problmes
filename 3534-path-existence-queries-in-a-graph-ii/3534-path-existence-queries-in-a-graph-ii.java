class Solution {
    public int[] pathExistenceQueries(int n, int[] nums, int maxDiff, int[][] queries) {
        int[][] arr = new int[n][2];
        for (int i = 0; i < n; i++) {
            arr[i][0] = nums[i];
            arr[i][1] = i;
        }

        Arrays.sort(arr, Comparator.comparingInt(a -> a[0]));

        int[] sortedVals = new int[n];
        int[] pos = new int[n];
        int[] comp = new int[n];

        for (int i = 0; i < n; i++) {
            sortedVals[i] = arr[i][0];
            pos[arr[i][1]] = i;
        }

        int cid = 0;
        comp[0] = 0;
        for (int i = 1; i < n; i++) {
            if (sortedVals[i] - sortedVals[i - 1] > maxDiff) {
                cid++;
            }
            comp[i] = cid;
        }

        int[] far = new int[n];
        int j = 0;
        for (int i = 0; i < n; i++) {
            while (j + 1 < n && sortedVals[j + 1] - sortedVals[i] <= maxDiff) {
                j++;
            }
            far[i] = j;
        }

        int LOG = 18; // 2^17 > 1e5
        int[][] up = new int[LOG][n];
        up[0] = far;

        for (int k = 1; k < LOG; k++) {
            for (int i = 0; i < n; i++) {
                up[k][i] = up[k - 1][up[k - 1][i]];
            }
        }

        int[] ans = new int[queries.length];

        for (int qi = 0; qi < queries.length; qi++) {
            int u = queries[qi][0];
            int v = queries[qi][1];

            if (u == v) {
                ans[qi] = 0;
                continue;
            }

            int pu = pos[u];
            int pv = pos[v];

            if (comp[pu] != comp[pv]) {
                ans[qi] = -1;
                continue;
            }

            int l = Math.min(pu, pv);
            int r = Math.max(pu, pv);

            int steps = 0;
            int cur = l;

            for (int k = LOG - 1; k >= 0; k--) {
                if (up[k][cur] < r) {
                    cur = up[k][cur];
                    steps += 1 << k;
                }
            }

            ans[qi] = steps + 1;
        }

        return ans;
    }
}