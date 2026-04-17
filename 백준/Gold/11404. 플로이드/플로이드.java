import java.util.*;
import java.io.*;

class Main{
    static int n, m;
    static int[][] costs;
    static int INF = (int)1e9;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        n = Integer.parseInt(br.readLine());
        m = Integer.parseInt(br.readLine());

        costs = new int[n + 1][n + 1];
        for (int i = 1 ;i <= n; i++){
            Arrays.fill(costs[i], INF);
            costs[i][i] = 0;
        }

        for (int i = 0 ; i < m; i++){
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());

            costs[a][b] = Math.min(costs[a][b], c);
        }

        for (int k = 1; k <= n; k++) {
            for (int i = 1; i <= n; i++) {
                for (int j = 1; j <= n; j++) {
                    costs[i][j] = Math.min(costs[i][j], costs[i][k] + costs[k][j]);
                }
            }
        }
        for (int i = 1;  i<= n; i++){
            for (int j = 1; j <= n; j++){
                if (costs[i][j] == INF) System.out.print("0 ");
                else System.out.print(costs[i][j] + " ");
            }
            System.out.println();
        }

    }
}
