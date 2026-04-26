import java.util.*;
import java.io.*;

class Main {
    static int V;
    static List<Node>[] graph;
    static boolean[] visited;
    static int maxDist = 0;
    static int farNode = 0;

    static class Node {
        int to, weight;
        Node(int to, int weight) {
            this.to = to;
            this.weight = weight;
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        V = Integer.parseInt(br.readLine());

        graph = new ArrayList[V + 1];
        for (int i = 1; i <= V; i++)
            graph[i] = new ArrayList<>();

        for (int i = 0; i < V; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int from = Integer.parseInt(st.nextToken());

            while (true) {
                int to = Integer.parseInt(st.nextToken());
                if (to == -1) break;
                int weight = Integer.parseInt(st.nextToken());
                graph[from].add(new Node(to, weight));
            }
        }

        visited = new boolean[V + 1];
        DFS(1, 0);

        visited = new boolean[V + 1];
        maxDist = 0;
        DFS(farNode, 0);

        System.out.println(maxDist);
    }

    static void DFS(int node, int dist) {
        visited[node] = true;

        if (dist > maxDist) {
            maxDist = dist;
            farNode = node;
        }

        for (Node next : graph[node]) {
            if (!visited[next.to]) {
                DFS(next.to, dist + next.weight);
            }
        }
    }
}