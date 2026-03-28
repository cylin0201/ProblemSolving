import java.util.*;
import java.io.*;

class Main{
    static int N, M;
    static int a, b, c;
    static Edge[] edges;
    static int[] parents;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        edges = new Edge[M];
        parents = new int[N + 1];
        for (int i = 1; i<= N; i++)
            parents[i] = i;


        for (int i = 0; i < M; i++){
            st = new StringTokenizer(br.readLine());
            a = Integer.parseInt(st.nextToken());
            b = Integer.parseInt(st.nextToken());
            c = Integer.parseInt(st.nextToken());

            edges[i] = new Edge(a, b, c);
        }

        Arrays.sort(edges, (o1, o2) -> Integer.compare(o1.cost, o2.cost));

        int res = 0, lastCost = 0;
        for (int i = 0; i < edges.length; i++){
            if (!isUnion(edges[i].from, edges[i].to)){
                unionParent(edges[i].from, edges[i].to);
                lastCost = edges[i].cost;
                res += lastCost;
            }
        }
        System.out.println(res - lastCost);
    }

    static int getParent(int x){
        if (x == parents[x]) return x;
        return parents[x] = getParent(parents[x]);
    }

    static boolean isUnion(int a, int b){
        return getParent(a) == getParent(b);
    }

    static void unionParent(int a,int b){
        a = getParent(a); b = getParent(b);

        if (a > b) parents[a] = b;
        else parents[b] = a;
    }

    static class Edge{
        int from, to , cost;
        Edge(int from, int to, int cost){
            this.from = from;
            this.to = to;
            this.cost = cost;
        }
    }
}