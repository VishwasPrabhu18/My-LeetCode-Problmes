class Solution {
    static class Node {
        char leftChar;
        char rightChar;

        int len;
        int leftLen;
        int rightLen;
        int maxLen;

        Node(char c) {
            leftChar = c;
            rightChar = c;
            len = 1;
            leftLen = 1;
            rightLen = 1;
            maxLen = 1;
        }
    }

    Node[] tree;
    char[] s;

    public int[] longestRepeating(String s, String queryCharacters, int[] queryIndices) {
        int n = s.length();

        this.s = s.toCharArray();
        tree = new Node[4 * n];

        build(1, 0, n - 1);

        int k = queryIndices.length;
        int[] ans = new int[k];

        for (int i = 0; i < k; i++) {

            int index = queryIndices[i];
            char ch = queryCharacters.charAt(i);

            this.s[index] = ch;

            update(1, 0, n - 1, index, ch);

            ans[i] = tree[1].maxLen;
        }

        return ans;
    }

    private void build(int node, int left, int right) {

        if (left == right) {
            tree[node] = new Node(s[left]);
            return;
        }

        int mid = left + (right - left) / 2;

        build(node * 2, left, mid);
        build(node * 2 + 1, mid + 1, right);

        tree[node] = merge(tree[node * 2], tree[node * 2 + 1]);
    }

    private void update(
      int node,
      int left,
      int right,
      int index,
      char ch) {

        if (left == right) {
            tree[node] = new Node(ch);
            return;
        }

        int mid = left + (right - left) / 2;

        if (index <= mid) {
            update(node * 2, left, mid, index, ch);
        } else {
            update(node * 2 + 1, mid + 1, right, index, ch);
        }

        tree[node] = merge(tree[node * 2], tree[node * 2 + 1]);
    }

    private Node merge(Node a, Node b) {

        Node res = new Node(a.leftChar);

        res.len = a.len + b.len;

        res.leftChar = a.leftChar;
        res.rightChar = b.rightChar;

        // Prefix
        res.leftLen = a.leftLen;

        if (a.leftLen == a.len &&
          a.rightChar == b.leftChar) {

            res.leftLen = a.len + b.leftLen;
        }

        // Suffix
        res.rightLen = b.rightLen;

        if (b.rightLen == b.len &&
          a.rightChar == b.leftChar) {

            res.rightLen = b.len + a.rightLen;
        }

        // Best answer inside either side
        res.maxLen = Math.max(a.maxLen, b.maxLen);

        // Combine suffix of left + prefix of right
        if (a.rightChar == b.leftChar) {
            res.maxLen = Math.max(
              res.maxLen,
              a.rightLen + b.leftLen
            );
        }

        return res;
    }
}