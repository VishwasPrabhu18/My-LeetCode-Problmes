class Solution {
    public long minimumCost(String source, String target, char[] original, char[] changed, int[] cost) {
        int n = 26;
        long INF = (long) 1e18;

        long[][] dist = new long[n][n];

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                dist[i][j] = (i == j) ? 0 : INF;
            }
        }

        // Keep the minimum direct conversion cost
        for (int i = 0; i < cost.length; i++) {
            int u = original[i] - 'a';
            int v = changed[i] - 'a';

            dist[u][v] = Math.min(dist[u][v], cost[i]);
        }

        // Floyd-Warshall
        for (int k = 0; k < n; k++) {
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    if (dist[i][k] == INF || dist[k][j] == INF) {
                        continue;
                    }

                    dist[i][j] = Math.min(dist[i][j], dist[i][k] + dist[k][j]);
                }
            }
        }

        long ans = 0;

        for (int i = 0; i < source.length(); i++) {
            char s = source.charAt(i);
            char t = target.charAt(i);

            if (s == t) {
                continue;
            }

            long c = dist[s - 'a'][t - 'a'];

            if (c == INF) {
                return -1;
            }

            ans += c;
        }

        return ans;
    }
}