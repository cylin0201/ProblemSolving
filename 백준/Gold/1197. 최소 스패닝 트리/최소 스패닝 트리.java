import java.util.*;
import java.io.*;

class Main{
    static int[] parent;
    static int V, E;
    static int a, b, c;
    static Edge[] arr;


    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        V = Integer.parseInt(st.nextToken()); E = Integer.parseInt(st.nextToken());

        arr = new Edge[E];
        parent = new int[V + 1];
        for (int i = 1; i<= V; i++)
            parent[i] = i;

        for (int i = 0 ; i < E; i++){
            st = new StringTokenizer(br.readLine());
            a = Integer.parseInt(st.nextToken());
            b = Integer.parseInt(st.nextToken());
            c = Integer.parseInt(st.nextToken());

            arr[i] = new Edge(a, b, c);
        }

        Arrays.sort(arr, (o1, o2)-> Integer.compare(o1.dist, o2.dist));

        int sum = 0;
        for (int i = 0 ; i < arr.length; i++){
            Edge edge = arr[i];

            if (!isUnion(edge.from, edge.to)){
                unionParent(edge.from, edge.to);
                sum += edge.dist;
            }
        }
        System.out.println(sum);
    }

    static int getParent(int x){
        if (x == parent[x]) return x;
        return parent[x] = getParent(parent[x]);
    }

    static void unionParent(int a, int b){
        a = getParent(a); b = getParent(b);

        if (a > b) parent[a] = b;
        else parent[b] = a;
    }

    static boolean isUnion(int a, int b){
        return getParent(a) == getParent(b);
    }

    static class Edge{
        int from, to, dist;
        Edge(int from,int to, int dist){
            this.from = from;
            this.to = to;
            this.dist = dist;
        }
    }
}