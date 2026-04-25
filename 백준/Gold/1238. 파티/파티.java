import java.util.*;
import java.io.*;

class Main{
    static int N, M, X;
    static int a, b, c;
    static int INF = (int)1e9;
    static int res = 0;

    static int[][] dist;
    static List<Edge>[] graph;

    static class Edge{
        int to, d;
        Edge(int to, int d){
            this.to  = to;
            this.d = d;
        }
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        X = Integer.parseInt(st.nextToken());

        dist = new int[N + 1][N + 1];
        for (int i = 1; i<= N; i++)
            Arrays.fill(dist[i], INF);
        graph = new List[N + 1];
        for (int i = 1; i <= N; i++)
            graph[i] = new ArrayList<>();

        for (int i = 0 ; i < M; i++){
            st = new StringTokenizer(br.readLine());
            a = Integer.parseInt(st.nextToken());
            b = Integer.parseInt(st.nextToken());
            c = Integer.parseInt(st.nextToken());

            graph[a].add(new Edge(b, c));
        }

        for (int i = 1; i <= N; i++){
            dijkstra(i);
        }

        for (int i = 1; i <= N; i++){
            if (dist[i][X] == INF || dist[X][i] == INF) continue;
            res = Math.max(res, dist[i][X] + dist[X][i]);
        }

        System.out.println(res);
    }

    static void dijkstra(int start){
        dist[start][start] = 0;
        PriorityQueue<Edge> pq = new PriorityQueue<>((o1, o2) -> o1.d - o2.d);
        pq.offer(new Edge(start, 0));

        while (!pq.isEmpty()){
            Edge c = pq.poll();
            if (dist[start][c.to] < c.d) continue;

            for (Edge n: graph[c.to]){
                int next = n.to, nextDistance = dist[start][c.to] + n.d;
                if (dist[start][next] > nextDistance){
                    dist[start][next] = nextDistance;
                    pq.offer(new Edge(next, nextDistance));
                }
            }
        }
    }
}