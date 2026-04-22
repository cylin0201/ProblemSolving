import java.util.*;
import java.io.*;

class Main{
    static int N, M;
    static int x, y;
    static double res;
    static class Pos{
        int x, y;
        Pos(int x, int y){
            this.x = x;
            this.y = y;
        }
    }
    static class Edge{
        int from, to; double dist;
        Edge(int from, int to, double dist){
            this.from = from;
            this.to = to;
            this.dist = dist;
        }
    }

    static int[] parent, size;
    static Pos[] poses;
    static List<Edge> edges = new ArrayList<>();
    static boolean[][] connected;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        poses = new Pos[N + 1];
        parent = new int[N + 1];
        size = new int[N + 1];
        for (int i = 1; i<= N; i++) {
            parent[i] = i;
            size[i] = 1;
        }
        connected = new boolean[N + 1][N + 1];

        for (int i = 1 ; i <= N; i++){
            st = new StringTokenizer(br.readLine());
            x = Integer.parseInt(st.nextToken());
            y = Integer.parseInt(st.nextToken());

            poses[i] = new Pos(x, y);
        }

        for (int i  = 0 ; i < M; i++){
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            connected[a][b] = true;
            connected[b][a] = true;
        }

        for (int i = 1; i <= N; i++){
            for (int j = i + 1; j <= N; j++){
                double dist =  getDistance(poses[i], poses[j]);
                if (connected[i][j]){
                    union(i, j);
                    continue;
                }
                edges.add(new Edge(i, j, dist));
            }
        }
        Collections.sort(edges, (o1, o2) -> Double.compare(o1.dist, o2.dist));

        for (Edge e: edges){
            if (!find(e.from, e.to)){
                union(e.from, e.to);
                res += e.dist;
            }
        }

        System.out.printf("%.2f\n", res);
    }
    static double getDistance(Pos o1, Pos o2){
        return Math.sqrt(Math.pow((o1.x - o2.x), 2) + Math.pow((o1.y - o2.y), 2));
    }

    static int getParent(int x){
        if (x == parent[x]) return x;
        return parent[x] = getParent(parent[x]);
    }

    static boolean find(int a, int b){
        return getParent(a) == getParent(b);
    }

    static void union(int a, int b){
        a =  getParent(a);
        b =  getParent(b);

        if (size[a] > size[b]){
            size[a] += size[b];
            parent[b] = a;
        }
        else {
            size[b] += size[a];
            parent[a] = b;
        }

    }
}