import java.util.*;
import java.io.*;

class Main{
    static int N, M;
    static Edge[] arr;
    static int a, b, c;
    static int[] parent;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine()); M = Integer.parseInt(br.readLine());
        arr = new Edge[M];
        parent = new int[N + 1];
        for (int i = 1; i<= N; i++)
            parent[i] = i;

        for (int i = 0 ; i < M; i++){
            st = new StringTokenizer(br.readLine());
            a = Integer.parseInt(st.nextToken());
            b = Integer.parseInt(st.nextToken());
            c = Integer.parseInt(st.nextToken());

            arr[i] = new Edge(a, b, c);
        }

        Arrays.sort(arr, (o1, o2) -> Integer.compare(o1.dist, o2.dist));

        int sum = 0;
        for (int i = 0 ; i < arr.length; i++){
            Edge edge = arr[i];
            if (!isUnion(edge.from, edge.to)){
                Main.unionParent(edge.from, edge.to);
                sum += edge.dist;
            }
        }
        System.out.println(sum);
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

        if (a > b) parent[b] = a;
        else parent[a] = b;
    }


    static class Edge{
        int from, to, dist;
        Edge(int from, int to, int dist){
            this.from = from;
            this.to = to;
            this.dist = dist;
        }
    }
}