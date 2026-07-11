class Solution {

    List<Integer>[] graph;
    boolean[] visited;

    public int countCompleteComponents(int n, int[][] edges) {
        graph = new ArrayList[n];
        visited = new boolean[n];

        for(int i=0; i<n; i++) {
            graph[i] = new ArrayList<>();
        }

        // Building adjacency list
        for(int[] edge: edges) {
            int u = edge[0], v = edge[1];
            graph[u].add(v);
            graph[v].add(u);
        }

        int completeComponents = 0;

        for(int i=0; i<n; i++) {
            if(!visited[i]) {
                int[] info = dfs(i);
                int vertices = info[0];
                int edgeInComponent = info[1] / 2;

                if(edgeInComponent == vertices * (vertices - 1) / 2) {
                    completeComponents++;
                }
            }
        }
        return completeComponents;
    }

    private int[] dfs(int u) {
        visited[u] = true;

        int vertices = 1;
        int edgeInCount = graph[u].size();

        for(int neighbour: graph[u]) {
            if(!visited[neighbour]) {
                int[] res = dfs(neighbour);
                vertices += res[0];
                edgeInCount += res[1];
            }
        }

        return new int[] {vertices, edgeInCount};
    }
}