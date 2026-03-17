import java.util.*;
import java.io.*;

public class Main{
    static int n, m;
    static int[][] arr;

    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken()); m = Integer.parseInt(st.nextToken());
        arr = new int[n][m];

        for (int i = 0 ; i < n; i++){
            String input = br.readLine();
            for (int j = 0 ; j < input.length(); j++){
                arr[i][j] += input.charAt(j) - '0';
            }
        }

        BFS(0, 0);

        System.out.println(arr[n - 1][m - 1]);
    }

    static void BFS(int x, int y){
        Queue<int[]> q = new ArrayDeque<>();
        boolean[][] visited = new boolean[n][m];

        q.offer(new int[]{x, y});
        visited[x][y] = true;

        while (!q.isEmpty()){
            int[] cur = q.peek(); q.poll();

            for (int i = 0 ; i < 4; i++){
                int[] next = new int[] {cur[0] + dx[i], cur[1] + dy[i]};

                if (next[0] < 0 || next[0] >= n || next[1] < 0 || next[1] >= m)
                    continue;

                if (visited[next[0]][next[1]] || arr[next[0]][next[1]] != 1)
                    continue;

                visited[next[0]][next[1]] = true;
                arr[next[0]][next[1]] = arr[cur[0]][cur[1]] + 1;
                q.offer(next);
            }
        }
    }
}