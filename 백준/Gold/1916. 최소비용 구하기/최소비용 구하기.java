import java.util.*;
import java.io.*;

class Main{
    static int N, M;
    static int INF = Integer.MAX_VALUE;
    static List<Edge>[] graph;
    static int[] dist;
    static int f,t,c;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        N = Integer.parseInt(br.readLine()); M = Integer.parseInt(br.readLine());
        dist = new int[N + 1];
        Arrays.fill(dist, INF);

        graph = new ArrayList[N + 1];
        for (int i = 1; i <= N; i++)
            graph[i] = new ArrayList<>();

        for (int i = 0 ; i < M; i++){
            st = new StringTokenizer(br.readLine());
            f = Integer.parseInt(st.nextToken());
            t = Integer.parseInt(st.nextToken());
            c = Integer.parseInt(st.nextToken());

            graph[f].add(new Edge(t, c));
        }

        st = new StringTokenizer(br.readLine());
        int start = Integer.parseInt(st.nextToken()), end = Integer.parseInt(st.nextToken());
        dijkstra(start, end);

        System.out.println(dist[end]);

    }

    static void dijkstra(int start, int end){
        PriorityQueue<Edge> pq = new PriorityQueue<>((o1, o2) -> Integer.compare(o1.cost, o2.cost));
        dist[start] = 0;

        pq.offer(new Edge(start, 0));

        while (!pq.isEmpty()){
            int cur = pq.peek().to, cost = pq.peek().cost;
            pq.poll();

            if (dist[cur] < cost) continue;

            for (Edge edge: graph[cur]){
                int next = edge.to, nextCost = edge.cost + cost;

                if (dist[next] > nextCost){
                    dist[next] = nextCost;
                    pq.offer(new Edge(next, nextCost));
                }
            }
        }
    }

    static class Edge{
        int to, cost;
        Edge(int to, int cost){
            this.to = to;
            this.cost = cost;
        }
    }
}