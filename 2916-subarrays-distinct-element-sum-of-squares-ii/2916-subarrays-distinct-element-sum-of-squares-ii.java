class Solution {
    private static final long MOD = 1_000_000_007L;
    
    public int sumCounts(int[] nums) {
        int n = nums.length;

        int[] last = new int[100001];
        Arrays.fill(last, -1);

        SegmentTree tree = new SegmentTree(n);
        long answer = 0;

        for (int i = 0; i < n; i++) {
            int x = nums[i];

            tree.update(last[x] + 1, i, 1);
            last[x] = i;

            answer = (answer + tree.getSumSquares()) % MOD;
        }

        return (int) answer;
    }

    private static class SegmentTree {
        long[] sum;
        long[] squareSum;
        long[] lazy;
        int n;

        SegmentTree(int n) {
            this.n = n;
            sum = new long[4 * n];
            squareSum = new long[4 * n];
            lazy = new long[4 * n];
        }

        void apply(int node, int left, int right, long delta) {
            long len = right - left + 1L;

            squareSum[node] = (
                squareSum[node]
                + 2 * delta * sum[node]
                + delta * delta % MOD * len
            ) % MOD;

            sum[node] = (sum[node] + delta * len) % MOD;
            lazy[node] += delta;
        }

        void push(int node, int left, int right) {
            if (lazy[node] == 0 || left == right) {
                return;
            }

            int mid = left + (right - left) / 2;

            apply(node * 2, left, mid, lazy[node]);
            apply(node * 2 + 1, mid + 1, right, lazy[node]);

            lazy[node] = 0;
        }

        void update(int ql, int qr, long delta) {
            update(1, 0, n - 1, ql, qr, delta);
        }

        void update(int node, int left, int right,
                    int ql, int qr, long delta) {
            if (ql <= left && right <= qr) {
                apply(node, left, right, delta);
                return;
            }

            push(node, left, right);

            int mid = left + (right - left) / 2;

            if (ql <= mid) {
                update(node * 2, left, mid, ql, qr, delta);
            }

            if (qr > mid) {
                update(node * 2 + 1, mid + 1, right,
                       ql, qr, delta);
            }

            sum[node] = (sum[node * 2] + sum[node * 2 + 1]) % MOD;

            squareSum[node] = (
                squareSum[node * 2] + squareSum[node * 2 + 1]
            ) % MOD;
        }

        long getSumSquares() {
            return squareSum[1];
        }
    }
}