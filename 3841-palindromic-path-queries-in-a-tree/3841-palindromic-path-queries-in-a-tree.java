class Solution {
    private List<Integer>[] adj;
    private int[][] up;
    private int[] parent, depth, tin, tout;
    private int[] prefix, order;
    private int timer, LOG;
    private Fenwick fenwick;

    public List<Boolean> palindromePath(int n, int[][] edges, String s, String[] queries) {
        adj = new ArrayList[n];
        for (int i = 0; i < n; i++) {
            adj[i] = new ArrayList<>();
        }

        for (int[] edge : edges) {
            adj[edge[0]].add(edge[1]);
            adj[edge[1]].add(edge[0]);
        }

        LOG = 1;
        while ((1 << LOG) <= n) {
            LOG++;
        }

        parent = new int[n];
        depth = new int[n];
        tin = new int[n];
        tout = new int[n];
        prefix = new int[n];
        order = new int[n];
        up = new int[LOG][n];

        buildTree(n, s);

        for (int j = 0; j < LOG; j++) {
            for (int u = 0; u < n; u++) {
                if (j == 0) {
                    up[j][u] = parent[u];
                } else {
                    up[j][u] = up[j - 1][up[j - 1][u]];
                }
            }
        }

        fenwick = new Fenwick(n);

        char[] chars = s.toCharArray();
        List<Boolean> results = new ArrayList<>();

        for (String query : queries) {
            String[] parts = query.split(" ");

            if (parts[0].equals("update")) {
                int u = Integer.parseInt(parts[1]);
                char c = parts[2].charAt(0);

                int oldMask = 1 << (chars[u] - 'a');
                int newMask = 1 << (c - 'a');
                int delta = oldMask ^ newMask;

                if (delta != 0) {
                    fenwick.rangeXor(tin[u], tout[u], delta);
                    chars[u] = c;
                }
            } else {
                int u = Integer.parseInt(parts[1]);
                int v = Integer.parseInt(parts[2]);

                int ancestor = lca(u, v);

                int prefixU = prefix[u] ^ fenwick.query(tin[u]);
                int prefixV = prefix[v] ^ fenwick.query(tin[v]);
                int ancestorMask = 1 << (chars[ancestor] - 'a');

                int mask = prefixU ^ prefixV ^ ancestorMask;

                results.add((mask & (mask - 1)) == 0);
            }
        }

        return results;
    }

    private void buildTree(int n, String s) {
        int[] stack = new int[n];
        int top = 0;

        parent[0] = 0;
        depth[0] = 0;
        prefix[0] = 1 << (s.charAt(0) - 'a');

        stack[top++] = 0;
        int count = 0;

        while (top > 0) {
            int u = stack[--top];

            tin[u] = count;
            order[count++] = u;

            for (int v : adj[u]) {
                if (v == parent[u] && u != 0) {
                    continue;
                }
                if (v == 0 || v == parent[u]) {
                    continue;
                }

                parent[v] = u;
                depth[v] = depth[u] + 1;
                prefix[v] = prefix[u] ^ (1 << (s.charAt(v) - 'a'));
                stack[top++] = v;
            }
        }

        int[] size = new int[n];
        Arrays.fill(size, 1);

        for (int i = n - 1; i >= 0; i--) {
            int u = order[i];

            if (u != 0) {
                size[parent[u]] += size[u];
            }
            tout[u] = tin[u] + size[u] - 1;
        }
    }

    private int lca(int u, int v) {
        if (depth[u] < depth[v]) {
            int temp = u;
            u = v;
            v = temp;
        }

        int diff = depth[u] - depth[v];

        for (int j = 0; j < LOG; j++) {
            if ((diff & (1 << j)) != 0) {
                u = up[j][u];
            }
        }

        if (u == v) {
            return u;
        }

        for (int j = LOG - 1; j >= 0; j--) {
            if (up[j][u] != up[j][v]) {
                u = up[j][u];
                v = up[j][v];
            }
        }

        return parent[u];
    }

    private static class Fenwick {
        private final int[] bit;

        Fenwick(int n) {
            bit = new int[n + 2];
        }

        private void add(int index, int value) {
            for (int i = index; i < bit.length; i += i & -i) {
                bit[i] ^= value;
            }
        }

        void rangeXor(int left, int right, int value) {
            add(left + 1, value);
            add(right + 2, value);
        }

        int query(int index) {
            int result = 0;

            for (int i = index + 1; i > 0; i -= i & -i) {
                result ^= bit[i];
            }

            return result;
        }
    }
}