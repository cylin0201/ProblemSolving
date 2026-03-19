import java.util.*;
import java.io.*;

public class Main{
    static int n;
    static int[][] arr;
    static boolean[][] visited;
    static List<Integer> res = new ArrayList<>();
    static int cnt;

    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        arr = new int[n][n]; visited = new boolean[n][n];

        for (int i = 0 ; i < n; i++){
            String input = br.readLine();
            for (int j = 0 ; j < n; j++){
                arr[i][j] = input.charAt(j) - '0';
            }
        }

        for (int i = 0; i < n; i++){
            for (int j = 0 ; j < n; j++){
                cnt = 0;
                if (arr[i][j] == 1 && !visited[i][j]) DFS(i, j);

                if (cnt > 0) res.add(cnt);
            }
        }
        Collections.sort(res);

        System.out.println(res.size());
        for (int c: res)
            System.out.println(c);
    }

    static void DFS(int x, int y){
        visited[x][y] = true;
        cnt++;

        for (int i = 0; i < 4; i++){
            int nx = x + dx[i] , ny = y + dy[i];

            if (nx < 0 || nx >= n || ny < 0 || ny >= n)
                continue;
            if (arr[nx][ny] != 1 || visited[nx][ny])
                continue;

            DFS(nx, ny);
        }
    }
}