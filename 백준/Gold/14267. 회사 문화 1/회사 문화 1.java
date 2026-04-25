import java.util.*;
import java.io.*;

class Main{
    static int n, m;
    static int[] a;
    static int x, y;
    static List<Integer>[] graph;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        a = new int[n + 1];
        graph = new List[n + 1];
        for (int i = 1; i <= n; i++)
            graph[i] = new ArrayList<>();

        st = new StringTokenizer(br.readLine());
        for (int i = 1 ; i <= n; i++) {
            int input = Integer.parseInt(st.nextToken());
            if (input == -1) continue;
            graph[input].add(i);
        }

        for (int i = 0 ; i < m; i++){
            st = new StringTokenizer(br.readLine());
            x = Integer.parseInt(st.nextToken());
            y = Integer.parseInt(st.nextToken());

            a[x] += y;
        }

        for (int i = 1; i < n; i++){
            for (int elem: graph[i]){
                a[elem] += a[i];
            }
        }

        for (int i = 1; i <= n; i++)
            System.out.print(a[i] + " ");
    }
}