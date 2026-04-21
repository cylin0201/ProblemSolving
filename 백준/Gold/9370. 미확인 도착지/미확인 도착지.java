import java.util.*;
import java.io.*;

class Main{
    static int n, m, t;
    static int s, g, h;
    static int a, b, d;
    static int[] dst;
    static int INF = (int)1e9;

    static int[] dist;
    static int[] distG, distH;
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

        int T = Integer.parseInt(br.readLine());
        while (T-- > 0) {
            st = new StringTokenizer(br.readLine());
            n = Integer.parseInt(st.nextToken());
            m = Integer.parseInt(st.nextToken());
            t = Integer.parseInt(st.nextToken());

            graph = new List[n + 1];
            for (int i = 1; i <= n; i++)
                graph[i] = new ArrayList<>();

            dst = new int[t];
            dist = new int[n + 1];
            distG = new int[n + 1];
            distH = new int[n + 1];

            Arrays.fill(dist, INF);
            Arrays.fill(distG, INF);
            Arrays.fill(distH, INF);

            List<Integer> res = new ArrayList<>();

            st = new StringTokenizer(br.readLine());
            s = Integer.parseInt(st.nextToken());
            g = Integer.parseInt(st.nextToken());
            h = Integer.parseInt(st.nextToken());

            int ghDist = 0;

            for (int i = 0; i < m; i++) {
                st = new StringTokenizer(br.readLine());
                a = Integer.parseInt(st.nextToken());
                b = Integer.parseInt(st.nextToken());
                d = Integer.parseInt(st.nextToken());

                graph[a].add(new Edge(b, d));
                graph[b].add(new Edge(a, d));

                if ((a == g && b == h) || (a == h && b == g)) {
                    ghDist = d;
                }
            }

            for (int i = 0; i < t; i++) {
                dst[i] = Integer.parseInt(br.readLine());
            }

            dijkstra(s, dist);
            dijkstra(g, distG);
            dijkstra(h, distH);

            for (int i = 0; i < dst.length; i++) {
                int d1 = dist[g] + ghDist + distH[dst[i]];
                int d2 = dist[h] + ghDist + distG[dst[i]];

                if (dist[dst[i]] == d1 || dist[dst[i]] == d2) {
                    res.add(dst[i]);
                }
            }

            Collections.sort(res);

            for (int i = 0; i < res.size(); i++)
                System.out.print(res.get(i) + " ");
            System.out.println();
        }
    }

    static void dijkstra(int start, int[] D){
        PriorityQueue<Edge> pq = new PriorityQueue<>((o1 , o2) -> Integer.compare(o1.d, o2.d));

        D[start] = 0;
        pq.offer(new Edge(start, 0));

        while (!pq.isEmpty()){
            Edge c = pq.poll();
            if (D[c.to] < c.d) continue;

            for (Edge n: graph[c.to]){
                int next = n.to, nextDistance = c.d + n.d;
                if (D[next] > nextDistance){
                    D[next] = nextDistance;
                    pq.offer(new Edge(next, nextDistance));
                }
            }
        }
    }
}