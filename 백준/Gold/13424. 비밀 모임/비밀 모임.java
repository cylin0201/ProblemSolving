import java.util.*;
import java.io.*;

class Main{
    static int T;
    static int N, M;
    static int a, b, c;
    static int k;
    static int[] rooms;
    static int[] dist;
    static int[] sum;
    static int INF = (int)1e9;
    static List<Edge>[] graph;

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

        T = Integer.parseInt(br.readLine());

        while (T-- > 0) {
            st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            M = Integer.parseInt(st.nextToken());

            dist = new int[N + 1];
            sum = new int[N + 1];

            graph = new ArrayList[N + 1];
            for (int i = 1; i <= N; i++)
                graph[i] = new ArrayList<>();

            for (int i = 0; i < M; i++) {
                st = new StringTokenizer(br.readLine());
                a = Integer.parseInt(st.nextToken());
                b = Integer.parseInt(st.nextToken());
                c = Integer.parseInt(st.nextToken());

                graph[a].add(new Edge(b, c));
                graph[b].add(new Edge(a, c));
            }

            k = Integer.parseInt(br.readLine());
            rooms = new int[k];
            st = new StringTokenizer(br.readLine());

            for (int i = 0; i < k; i++) {
                rooms[i] = Integer.parseInt(st.nextToken());
                Arrays.fill(dist, INF);
                dijkstra(rooms[i]);

                for (int j = 1; j <= N; j++) {
                    if (sum[j] == INF || dist[j] == INF) {
                        sum[j] = INF;
                        continue;
                    }
                    sum[j] += dist[j];
                }
            }
            int res = 1, minDist = INF;
            for (int room = 1; room <= N; room++) {
                if (sum[room] < minDist) {
                    minDist = sum[room];
                    res = room;
                }
            }
            System.out.println(res);
        }
    }

    static void dijkstra(int start){
        dist[start] = 0;
        PriorityQueue<Edge> pq = new PriorityQueue<>((o1, o2) -> Integer.compare(o1.d, o2.d));
        pq.offer(new Edge(start, 0));

        while (!pq.isEmpty()){
            Edge c =  pq.poll();

            if (c.d > dist[c.to]) continue;

            for (Edge edge: graph[c.to]){
                int next = edge.to, nextDistance = c.d + edge.d;

                if (dist[next] > nextDistance){
                    dist[next] = nextDistance;
                    pq.offer(new Edge(next, nextDistance));
                }
            }
        }

    }
}