import java.util.*;
import java.io.*;

class Main{
    static int n;
    static Star[] stars;
    static List<Edge> list = new ArrayList<>();
    static int[] parent;

    static class Star{
        double x; double y;

        Star(double x, double y) {
            this.x = x;
            this.y = y;
        }
    }

    static class Edge{
        int idx1, idx2; double d;

        Edge(int idx1, int idx2, double d){
            this.idx1 = idx1;
            this.idx2 = idx2;
            this.d = d;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br =new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());

        stars = new Star[n + 1];
        parent = new int[n + 1];
        for (int i = 1; i <= n; i++)
            parent[i] = i;
        StringTokenizer st;

        for (int i = 1 ; i <= n; i++){
            st = new StringTokenizer(br.readLine());
            stars[i] = new Star(Double.parseDouble(st.nextToken()), Double.parseDouble(st.nextToken()));
        }

        for (int i = 1; i < n; i++)
            for (int j = i + 1; j <= n; j++)
                list.add(new Edge(i, j, getDistance(stars[i], stars[j])));

        Collections.sort(list, (o1, o2) -> Double.compare(o1.d, o2.d));

        double res = 0;

        for (int i = 0; i < list.size(); i++){
            Edge edge = list.get(i);
            if (!isUnion(edge.idx1, edge.idx2)){
                unionParent(edge.idx1, edge.idx2);
                res += edge.d;
            }
        }

        System.out.printf("%.2f\n", res);
    }

    static double getDistance(Star s1, Star s2){
        return  Math.sqrt(Math.pow(s1.x - s2.x, 2) +  Math.pow(s1.y - s2.y, 2));
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