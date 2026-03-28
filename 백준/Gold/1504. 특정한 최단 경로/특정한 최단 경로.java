import java.util.*;
import java.io.*;

class Main{
    static int N, E;
    static int a, b, c;
    static int v1, v2;
    static int INF = (int)1e9;

    static List<Edge>[] graph;
    static int[] dist;

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        E = Integer.parseInt(st.nextToken());

        graph = new ArrayList[N + 1];
        for (int i = 1; i<= N; i++)
            graph[i] = new ArrayList<>();

        for (int i = 0 ; i < E; i++){
            st = new StringTokenizer(br.readLine());
            a = Integer.parseInt(st.nextToken());
            b = Integer.parseInt(st.nextToken());
            c = Integer.parseInt(st.nextToken());

            graph[a].add(new Edge(b, c));
            graph[b].add(new Edge(a, c));
        }
        st = new StringTokenizer(br.readLine());
        v1 = Integer.parseInt(st.nextToken());
        v2 = Integer.parseInt(st.nextToken());

        dijkstra(1);
        int d1v1 = dist[v1];
        int d1v2 = dist[v2];

        dijkstra(v1);
        int dv1v2 = dist[v2];

        dijkstra(v2);
        int dv2v1 = dist[v1];

        dijkstra(N);
        int dv1N = dist[v1];
        int dv2N = dist[v2];

        int path1 = d1v1 + dv1v2 + dv2N; // 1 → v1 → v2 → N
        int path2 = d1v2 + dv2v1 + dv1N; // 1 → v2 → v1 → N

        if (d1v1 >= INF || dv1v2 >= INF || dv2N >= INF) path1 = INF;
        if (d1v2 >= INF || dv2v1 >= INF || dv1N >= INF) path2 = INF;

        int result = Math.min(path1, path2);
        System.out.println(result >= INF ? -1 : result);
    }

    static void dijkstra(int start){
        dist = new int[N + 1];
        Arrays.fill(dist, INF);

        dist[start] = 0;
        PriorityQueue<Edge> pq = new PriorityQueue<>((o1, o2) -> Integer.compare(o1.d, o2.d));

        pq.offer(new Edge(start, 0));

        while (!pq.isEmpty()){
            int cur = pq.peek().to, distance = pq.peek().d; pq.poll();

            if (dist[cur] < distance) continue;

            for (Edge edge: graph[cur]){
                int next = edge.to, nextDistance = edge.d + distance;

                if (dist[next] > nextDistance){
                    dist[next] = nextDistance;
                    pq.offer(new Edge(next, nextDistance));
                }
            }
        }
    }

    static class Edge{
        int to, d;
        Edge(int to, int d){
            this.to = to;
            this.d = d;
        }
    }
}