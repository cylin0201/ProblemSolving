import java.util.*;
import java.io.*;

class Main{
    static int INF = Integer.MAX_VALUE;
    static int V, E, K;
    static int u, v, w;

    static List<Pair>[] graph;
    static int[] dist;

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        V = Integer.parseInt(st.nextToken()); E = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(br.readLine());

        dist = new int[V + 1];
        Arrays.fill(dist, INF);

        graph = new ArrayList[V + 1];
        for (int i = 1; i <= V; i++)
            graph[i] = new ArrayList<>();

        for (int i = 0 ; i < E; i++){
            st = new StringTokenizer(br.readLine());
            u = Integer.parseInt(st.nextToken());
            v = Integer.parseInt(st.nextToken());
            w = Integer.parseInt(st.nextToken());

            graph[u].add(new Pair(v, w));
        }

        dijkstra(K);

        for (int i = 1; i<= V; i++) {
            if (dist[i] == INF) System.out.println("INF");
            else System.out.println(dist[i]);
        }
    }

    static void dijkstra(int start){
        dist[start] = 0;

        PriorityQueue<Pair> pq = new PriorityQueue<>((o1, o2) -> Integer.compare(o1.distance, o2.distance));
        pq.offer(new Pair(start, 0));

        while (!pq.isEmpty()){
            int cur = pq.peek().to, distance = pq.peek().distance;
            pq.poll();

            if (dist[cur] < distance) continue;

            for (int i = 0 ; i < graph[cur].size(); i++){
                int next = graph[cur].get(i).to, nextDistance = graph[cur].get(i).distance + distance;

                if (dist[next] > nextDistance){
                    dist[next] = nextDistance;
                    pq.offer(new Pair(next, nextDistance));
                }
            }
        }
    }

    static class Pair{
        int to, distance;
        Pair(int to, int distance){
            this.to = to;
            this.distance = distance;
        }
    }
}