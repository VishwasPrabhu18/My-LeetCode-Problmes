class Solution {
    static class Node {
        int product;
        int[] count;

        Node(int k) {
            count = new int[k];
        }
    }

    private int n;
    private int k;
    private Node[] tree;

    public int[] resultArray(int[] nums, int k, int[][] queries) {
        this.n = nums.length;
        this.k = k;

        tree = new Node[4 * n];

        build(1, 0, n - 1, nums);

        int[] result = new int[queries.length];

        for (int q = 0; q < queries.length; q++) {

            int index = queries[q][0];
            int value = queries[q][1];
            int start = queries[q][2];
            int x = queries[q][3];

            // Persistent update
            update(1, 0, n - 1, index, value);

            // Query [start, n - 1]
            Node node = query(1, 0, n - 1, start, n - 1);

            result[q] = node.count[x];
        }

        return result;
    }

    // Build segment tree
    private void build(int node, int left, int right, int[] nums) {

        if (left == right) {

            tree[node] = new Node(k);

            int value = nums[left] % k;

            tree[node].product = value;

            // The only non-empty prefix is the element itself.
            tree[node].count[value] = 1;

            return;
        }

        int mid = left + (right - left) / 2;

        build(node * 2, left, mid, nums);
        build(node * 2 + 1, mid + 1, right, nums);

        tree[node] = merge(tree[node * 2], tree[node * 2 + 1]);
    }

    // Update one position
    private void update(
      int node,
      int left,
      int right,
      int index,
      int value) {

        if (left == right) {

            tree[node] = new Node(k);

            int remainder = value % k;

            tree[node].product = remainder;
            tree[node].count[remainder] = 1;

            return;
        }

        int mid = left + (right - left) / 2;

        if (index <= mid) {
            update(node * 2, left, mid, index, value);
        } else {
            update(node * 2 + 1, mid + 1, right, index, value);
        }

        tree[node] = merge(tree[node * 2], tree[node * 2 + 1]);
    }

    // Query a range
    private Node query(
      int node,
      int left,
      int right,
      int queryLeft,
      int queryRight) {

        if (queryLeft <= left && right <= queryRight) {
            return tree[node];
        }

        int mid = left + (right - left) / 2;

        if (queryRight <= mid) {
            return query(
              node * 2,
              left,
              mid,
              queryLeft,
              queryRight
            );
        }

        if (queryLeft > mid) {
            return query(
              node * 2 + 1,
              mid + 1,
              right,
              queryLeft,
              queryRight
            );
        }

        Node leftNode = query(
          node * 2,
          left,
          mid,
          queryLeft,
          queryRight
        );

        Node rightNode = query(
          node * 2 + 1,
          mid + 1,
          right,
          queryLeft,
          queryRight
        );

        return merge(leftNode, rightNode);
    }

    // Merge two consecutive segments
    private Node merge(Node left, Node right) {

        Node result = new Node(k);

        // Prefixes completely inside the left segment
        for (int r = 0; r < k; r++) {
            result.count[r] += left.count[r];
        }

        // Prefixes that extend into the right segment
        for (int r = 0; r < k; r++) {

            if (right.count[r] == 0) {
                continue;
            }

            int newRemainder = (left.product * r) % k;

            result.count[newRemainder] += right.count[r];
        }

        // Product of the entire merged segment
        result.product = (left.product * right.product) % k;

        return result;
    }
}