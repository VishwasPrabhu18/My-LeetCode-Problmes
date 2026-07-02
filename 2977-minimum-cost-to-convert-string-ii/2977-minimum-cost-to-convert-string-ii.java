class Solution {
    static final long INF = Long.MAX_VALUE / 4;

    static class TrieNode {
        TrieNode[] next = new TrieNode[26];
        int id = -1;
    }

    public long minimumCost(String source, String target, String[] original, String[] changed, int[] cost) {
        Map<String, Integer> idMap = new HashMap<>();

        for (String s : original)
            idMap.putIfAbsent(s, idMap.size());

        for (String s : changed)
            idMap.putIfAbsent(s, idMap.size());

        int m = idMap.size();

        long[][] dist = new long[m][m];

        for (int i = 0; i < m; i++) {
            Arrays.fill(dist[i], INF);
            dist[i][i] = 0;
        }

        for (int i = 0; i < cost.length; i++) {
            int u = idMap.get(original[i]);
            int v = idMap.get(changed[i]);
            dist[u][v] = Math.min(dist[u][v], cost[i]);
        }

        // Floyd-Warshall
        for (int k = 0; k < m; k++)
            for (int i = 0; i < m; i++)
                if (dist[i][k] != INF)
                    for (int j = 0; j < m; j++)
                        if (dist[k][j] != INF)
                            dist[i][j] = Math.min(dist[i][j],
                              dist[i][k] + dist[k][j]);

        TrieNode root = new TrieNode();

        for (Map.Entry<String, Integer> e : idMap.entrySet()) {
            TrieNode node = root;
            String s = e.getKey();

            for (char c : s.toCharArray()) {
                int idx = c - 'a';
                if (node.next[idx] == null)
                    node.next[idx] = new TrieNode();
                node = node.next[idx];
            }

            node.id = e.getValue();
        }

        int n = source.length();

        long[] dp = new long[n + 1];
        Arrays.fill(dp, INF);
        dp[n] = 0;

        for (int i = n - 1; i >= 0; i--) {

            if (source.charAt(i) == target.charAt(i))
                dp[i] = dp[i + 1];

            TrieNode sNode = root;
            TrieNode tNode = root;

            for (int j = i; j < n; j++) {

                sNode = sNode.next[source.charAt(j) - 'a'];
                tNode = tNode.next[target.charAt(j) - 'a'];

                if (sNode == null || tNode == null)
                    break;

                if (sNode.id != -1 && tNode.id != -1 &&
                  dist[sNode.id][tNode.id] != INF) {

                    dp[i] = Math.min(dp[i],
                      dist[sNode.id][tNode.id] + dp[j + 1]);
                }
            }
        }

        return dp[0] == INF ? -1 : dp[0];
    }
}