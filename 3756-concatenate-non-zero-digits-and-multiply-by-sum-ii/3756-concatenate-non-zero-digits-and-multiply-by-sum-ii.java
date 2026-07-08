class Solution {
    static final int MOD = 1_000_000_007;
    
    public int[] sumAndMultiply(String s, int[][] queries) {
        List<Integer> pos = new ArrayList<>();
        List<Integer> digit = new ArrayList<>();

        for (int i = 0; i < s.length(); i++) {
            int d = s.charAt(i) - '0';
            if (d != 0) {
                pos.add(i);
                digit.add(d);
            }
        }

        int k = digit.size();

        long[] prefixNum = new long[k + 1];
        int[] prefixSum = new int[k + 1];
        long[] pow10 = new long[k + 1];

        pow10[0] = 1;
        for (int i = 1; i <= k; i++)
            pow10[i] = (pow10[i - 1] * 10) % MOD;

        for (int i = 0; i < k; i++) {
            prefixNum[i + 1] = (prefixNum[i] * 10 + digit.get(i)) % MOD;
            prefixSum[i + 1] = prefixSum[i] + digit.get(i);
        }

        int[] ans = new int[queries.length];

        for (int q = 0; q < queries.length; q++) {

            int l = queries[q][0];
            int r = queries[q][1];

            int left = lowerBound(pos, l);
            int right = upperBound(pos, r) - 1;

            if (left > right) {
                ans[q] = 0;
                continue;
            }

            int len = right - left + 1;

            long x = (prefixNum[right + 1]
              - prefixNum[left] * pow10[len] % MOD + MOD) % MOD;

            long sum = prefixSum[right + 1] - prefixSum[left];

            ans[q] = (int) ((x * sum) % MOD);
        }

        return ans;
    }

    private int lowerBound(List<Integer> list, int target) {
        int l = 0, r = list.size();
        while (l < r) {
            int m = (l + r) / 2;
            if (list.get(m) < target)
                l = m + 1;
            else
                r = m;
        }
        return l;
    }

    private int upperBound(List<Integer> list, int target) {
        int l = 0, r = list.size();
        while (l < r) {
            int m = (l + r) / 2;
            if (list.get(m) <= target)
                l = m + 1;
            else
                r = m;
        }
        return l;
    }
}