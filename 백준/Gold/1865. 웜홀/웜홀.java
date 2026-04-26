import java.util.*;
import java.io.*;

class Main {
    static int TC, N, M, W;

    static class Edge {
        int from, to, weight;
        Edge(int from, int to, int weight) {
            this.from = from;
            this.to = to;
            this.weight = weight;
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        TC = Integer.parseInt(br.readLine());

        while (TC-- > 0) {
            st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            M = Integer.parseInt(st.nextToken());
            W = Integer.parseInt(st.nextToken());

            List<Edge> edges = new ArrayList<>();

            for (int i = 0; i < M; i++) {
                st = new StringTokenizer(br.readLine());
                int S = Integer.parseInt(st.nextToken());
                int E = Integer.parseInt(st.nextToken());
                int T = Integer.parseInt(st.nextToken());

                edges.add(new Edge(S, E, T));
                edges.add(new Edge(E, S, T));
            }

            for (int i = 0; i < W; i++) {
                st = new StringTokenizer(br.readLine());
                int S = Integer.parseInt(st.nextToken());
                int E = Integer.parseInt(st.nextToken());
                int T = Integer.parseInt(st.nextToken());

                edges.add(new Edge(S, E, -T));
            }

            if (hasNegativeCycle(edges)) {
                System.out.println("YES");
            } else {
                System.out.println("NO");
            }
        }
    }

    static boolean hasNegativeCycle(List<Edge> edges) {
        int[] dist = new int[N + 1];

        Arrays.fill(dist, 0);

        for (int i = 1; i <= N; i++) {
            for (Edge e : edges) {
                if (dist[e.to] > dist[e.from] + e.weight) {
                    dist[e.to] = dist[e.from] + e.weight;

                    if (i == N) return true;
                }
            }
        }

        return false;
    }
}