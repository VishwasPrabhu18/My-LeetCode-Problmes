class Solution {
    public List<Integer> busiestServers(int k, int[] arrival, int[] load) {
        // Available Servers
        TreeSet<Integer> availServers = new TreeSet<>();
        for(int i=0; i<k; i++) {
            availServers.add(i);
        }

        // Busy Server: [finishTime, serverId]
        PriorityQueue<long[]> busyServer = new PriorityQueue<>(
          (a, b) -> Long.compare(a[0], b[0])
        );

        // Number of request handled by server
        int[] count = new int[k];

        // Process requests
        for(int i=0; i<arrival.length; i++) {
            // 1. Release server whose request are completes
            while(!busyServer.isEmpty() && busyServer.peek()[0] <= arrival[i]) {
                long[] server = busyServer.poll();
                availServers.add((int) server[1]);
            }

            // 2. All servers are busy then drop the request
            if(availServers.isEmpty()) {
                continue;
            }

            // 3. Find next available server
            int start = i % k;

            Integer server = availServers.ceiling(start);
            if(server == null) {
                server = availServers.first();
            }

            // 4. Assign request to selected server
            availServers.remove(server);
            count[server]++;

            long finishTime = (long) arrival[i] + load[i];

            busyServer.offer(new long[]{finishTime, server});
        }

        // 5. Find maximum requests handled
        int maxRequests = 0;

        for (int c : count) {
            maxRequests = Math.max(maxRequests, c);
        }

        // 5. Collect busiest servers
        List<Integer> result = new ArrayList<>();

        for (int i = 0; i < k; i++) {
            if (count[i] == maxRequests) {
                result.add(i);
            }
        }

        return result;
    }
}