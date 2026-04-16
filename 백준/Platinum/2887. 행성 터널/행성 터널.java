import java.util.*;
import java.io.*;

class Main {
    static int N;
    static Planet[] planets;
    static List<Edge> edges;
    static int[] parent;
    static int[] size;

    static class Planet {
        int idx, x, y, z;

        Planet(int idx, int x, int y, int z) {
            this.idx = idx;
            this.x = x;
            this.y = y;
            this.z = z;
        }
    }

    static class Edge {
        int from, to, d;

        Edge(int from, int to, int d) {
            this.from = from;
            this.to = to;
            this.d = d;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());

        planets = new Planet[N];
        parent = new int[N];
        size = new int[N];
        edges = new ArrayList<>();

        // 입력 + DSU 초기화
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            int z = Integer.parseInt(st.nextToken());

            planets[i] = new Planet(i, x, y, z);

            parent[i] = i;
            size[i] = 1;
        }

        Arrays.sort(planets, (a, b) -> a.x - b.x);
        for (int i = 0; i < N - 1; i++) {
            Planet p1 = planets[i];
            Planet p2 = planets[i + 1];
            edges.add(new Edge(p1.idx, p2.idx, Math.abs(p1.x - p2.x)));
        }

        Arrays.sort(planets, (a, b) -> a.y - b.y);
        for (int i = 0; i < N - 1; i++) {
            Planet p1 = planets[i];
            Planet p2 = planets[i + 1];
            edges.add(new Edge(p1.idx, p2.idx, Math.abs(p1.y - p2.y)));
        }

        Arrays.sort(planets, (a, b) -> a.z - b.z);
        for (int i = 0; i < N - 1; i++) {
            Planet p1 = planets[i];
            Planet p2 = planets[i + 1];
            edges.add(new Edge(p1.idx, p2.idx, Math.abs(p1.z - p2.z)));
        }

        // 간선 정렬
        Collections.sort(edges, (a, b) -> a.d - b.d);

        long res = 0;
        int cnt = 0;

        // 크루스칼
        for (Edge e : edges) {
            if (!find(e.from, e.to)) {
                union(e.from, e.to);
                res += e.d;
                cnt++;
            }
            if (cnt == N - 1) break;
        }

        System.out.println(res);
    }

    static int getParent(int x) {
        if (parent[x] == x) return x;
        return parent[x] = getParent(parent[x]);
    }

    static boolean find(int a, int b) {
        return getParent(a) == getParent(b);
    }

    static void union(int a, int b) {
        a = getParent(a);
        b = getParent(b);

        if (a == b) return;

        if (size[a] > size[b]) {
            parent[b] = a;
            size[a] += size[b];
        } else {
            parent[a] = b;
            size[b] += size[a];
        }
    }
}