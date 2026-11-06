class Solution {
    public int[] countServers(int n, int[][] logs, int x, int[] queries) {
        int q = queries.length;
        int[] result = new int[q];

        // Sort logs by request time.
        Arrays.sort(logs, (a, b) -> Integer.compare(a[1], b[1]));

        // Store query time and original index.
        int[][] sortedQueries = new int[q][2];

        for (int i = 0; i < q; i++) {
            sortedQueries[i][0] = queries[i];
            sortedQueries[i][1] = i;
        }

        // Sort queries by time.
        Arrays.sort(sortedQueries,
          (a, b) -> Integer.compare(a[0], b[0]));

        // Server ID -> number of active logs.
        Map<Integer, Integer> map = new HashMap<>();

        int left = 0;
        int right = 0;

        for (int[] query : sortedQueries) {

            int time = query[0];
            int index = query[1];

            int startTime = time - x;

            // Add logs with time <= current query time.
            while (right < logs.length &&
              logs[right][1] <= time) {

                int serverId = logs[right][0];

                map.put(serverId,
                  map.getOrDefault(serverId, 0) + 1);

                right++;
            }

            // Remove logs with time < startTime.
            while (left < right &&
              logs[left][1] < startTime) {

                int serverId = logs[left][0];

                int count = map.get(serverId);

                if (count == 1) {
                    map.remove(serverId);
                } else {
                    map.put(serverId, count - 1);
                }

                left++;
            }

            // Servers without requests.
            result[index] = n - map.size();
        }

        return result;
    }
}