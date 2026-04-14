import java.util.*;
import java.io.*;

class Main{
    static int V, E;
    static int a, b, c;
    static List<Edge>[] graph;
    static int[][] dist;
    static int INF = (int)1e9;

    static class Edge{
        int to, d;
        Edge(int to, int d){
            this.to = to;
            this.d = d;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        V = Integer.parseInt(st.nextToken());
        E = Integer.parseInt(st.nextToken());

        graph = new List[V + 1];
        for (int i = 1; i <= V; i++)
            graph[i] = new ArrayList<>();

        for (int i = 0 ; i < E; i++){
            st = new StringTokenizer(br.readLine());
            a = Integer.parseInt(st.nextToken());
            b = Integer.parseInt(st.nextToken());
            c = Integer.parseInt(st.nextToken());

            graph[a].add(new Edge(b, c));
        }

        dist = new int[V + 1][V + 1];
        for (int i = 1; i <= V; i++)
            Arrays.fill(dist[i], INF);

        for (int i = 1; i <= V; i++){
            dijkstra(i);
        }

        int minDist = INF;

        for (int i = 1; i <= V; i++){
            for (int j = 1; j <= V; j++){
                if (i == j) continue;
                minDist = Math.min(minDist, dist[i][j] + dist[j][i]);
            }
        }
        if (minDist == INF) minDist = -1;
        System.out.println(minDist);
    }

    static void dijkstra(int start){
        dist[start][start] = 0;
        PriorityQueue<Edge> pq = new PriorityQueue<>((o1, o2)-> Integer.compare(o1.d, o2.d));
        pq.offer(new Edge(start, 0));

        while (!pq.isEmpty()){
            Edge c = pq.poll();
            int distance = c.d;

            if (distance > dist[start][c.to]) continue;

            for (Edge n: graph[c.to]){
                int nextDistance = distance + n.d;

                if (nextDistance < dist[start][n.to]){
                    dist[start][n.to] = nextDistance;
                    pq.offer(new Edge(n.to, nextDistance));
                }
            }
        }
    }
}