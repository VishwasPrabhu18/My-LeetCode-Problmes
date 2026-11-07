class Solution {
    public int[] assignTasks(int[] servers, int[] tasks) {
        int n = servers.length;
        int m = tasks.length;

        int[] ans = new int[m];

        // Available servers: weight, index
        PriorityQueue<int[]> available = new PriorityQueue<>(
            (a, b) -> {
                if (a[0] != b[0]) {
                    return Integer.compare(a[0], b[0]);
                }
                return Integer.compare(a[1], b[1]);
            }
        );

        // Busy servers: freeTime, weight, index
        PriorityQueue<long[]> busy = new PriorityQueue<>(
            (a, b) -> {
                if (a[0] != b[0]) {
                    return Long.compare(a[0], b[0]);
                }
                if (a[1] != b[1]) {
                    return Long.compare(a[1], b[1]);
                }
                return Long.compare(a[2], b[2]);
            }
        );

        for (int i = 0; i < n; i++) {
            available.offer(new int[]{servers[i], i});
        }

        long time = 0;
        int taskIndex = 0;

        while (taskIndex < m) {

            // Release all servers that are free now
            while (!busy.isEmpty() && busy.peek()[0] <= time) {
                long[] s = busy.poll();

                available.offer(new int[]{
                    (int) s[1],
                    (int) s[2]
                });
            }

            // If a task has arrived and a server is available
            if (taskIndex <= time && !available.isEmpty()) {

                int[] server = available.poll();

                int weight = server[0];
                int index = server[1];

                ans[taskIndex] = index;

                busy.offer(new long[]{
                    time + tasks[taskIndex],
                    weight,
                    index
                });

                taskIndex++;

            } else if (available.isEmpty()) {

                // Wait until the earliest server becomes free
                time = busy.peek()[0];

            } else {

                // Wait for the next task to arrive
                time++;
            }
        }

        return ans;
    }
}