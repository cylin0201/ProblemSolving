import java.util.*;
import java.io.*;

class Main{
    static int m, n;
    static Edge[] edges;
    static int[] parent;

    static class Edge{
        int from, to; long d;
        Edge(int from, int to, long d){
            this.from = from;
            this.to = to;
            this.d = d;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;


        while (true) {
            st = new StringTokenizer(br.readLine());
            m = Integer.parseInt(st.nextToken());
            n = Integer.parseInt(st.nextToken());

            if (n == 0 && m == 0) return ;

            edges = new Edge[n];
            parent = new int[m];
            for (int i = 0; i < m; i++)
                parent[i] = i;

            long sum = 0;
            for (int i = 0; i < n; i++) {
                st = new StringTokenizer(br.readLine());
                int a, b;
                long d;
                a = Integer.parseInt(st.nextToken());
                b = Integer.parseInt(st.nextToken());
                d = Long.parseLong(st.nextToken());

                edges[i] = new Edge(a, b, d);
                sum += d;
            }

            Arrays.sort(edges, (o1, o2) -> Long.compare(o1.d, o2.d));

            long res = 0;
            for (Edge edge : edges) {
                if (!isUnion(edge.from, edge.to)) {
                    unionParent(edge.from, edge.to);
                    res += edge.d;
                }
            }

            System.out.println(sum - res);
        }
    }

    static int getParent(int x){
        if (x == parent[x]) return x;
        return parent[x] = getParent(parent[x]);
    }

    static boolean isUnion(int a, int b){
        return getParent(a) == getParent(b);
    }

    static void unionParent(int a, int b){
        a = getParent(a); b = getParent(b);

        if (a > b) parent[a] = b;
        else parent[b] = a;
    }
}