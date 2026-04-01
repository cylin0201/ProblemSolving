import java.util.*;
import java.io.*;

class Main{
    static int n, m, r;
    static int[] t, dist;
    static int a, b, I;
    static List<Edge>[] edges;
    static int res = -1, INF = (int)1e9;

    static class Edge{
        int to, d;
        Edge(int to, int d){
            this.to = to;
            this.d = d;
        }
    }

    public static void main(String[] args)throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        r = Integer.parseInt(st.nextToken());

        edges = new ArrayList[n + 1];
        for (int i = 1; i <= n; i++)
            edges[i] = new ArrayList<>();
        t = new int[n + 1];
        dist = new int[n + 1];

        st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= n; i++)
            t[i] = Integer.parseInt(st.nextToken());

        for (int i = 0 ; i < r; i++){
            st = new StringTokenizer(br.readLine());
            a = Integer.parseInt(st.nextToken());
            b = Integer.parseInt(st.nextToken());
            I = Integer.parseInt(st.nextToken());

            edges[a].add(new Edge(b, I));
            edges[b].add(new Edge(a, I));
        }

        for (int i = 1; i<=n; i++){
            Arrays.fill(dist, INF);
            int temp = 0;
            dijkstra(i);

            for (int j = 1; j<= n; j++){
                if (m >= dist[j]) temp += t[j];
            }
            res = Math.max(res, temp);
        }

        System.out.println(res);
    }

    static void dijkstra(int start){
        dist[start] = 0;
        PriorityQueue<Edge> pq = new PriorityQueue<>((o1, o2) -> Integer.compare(o1.d, o2.d));

        pq.offer(new Edge(start, 0));

        while (!pq.isEmpty()){
            int cur = pq.peek().to, distance = pq.peek().d; pq.poll();

            if (dist[cur] < distance) continue;

            for (Edge edge: edges[cur]){
                int next = edge.to, nextDistance = edge.d + distance;

                if (dist[next] > nextDistance){
                    dist[next] = nextDistance;
                    pq.offer(new Edge(next, nextDistance));
                }
            }
        }
    }
}