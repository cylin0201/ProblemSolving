import java.util.*;
import java.io.*;

class Main{
    static int N;
    static char[][] map;
    static String input;
    static boolean[][] visited;

    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};

    static int res1, res2;
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        map = new char[N][N];


        for (int i = 0 ; i < N; i++){
            input = br.readLine();
            for (int j = 0 ; j < input.length(); j++)
                map[i][j] = input.charAt(j);
        }

        visited = new boolean[N][N];
        for (int i = 0 ; i < N; i++){
            for (int j = 0 ; j < N; j++){
                if (!visited[i][j]) {
                    res1++;
                    DFS(i, j);
                }
            }
        }

        for (int i = 0 ; i < N; i++){
            for (int j = 0 ; j < N; j++){
                if (map[i][j] == 'G') map[i][j] = 'R';
            }
        }

        visited = new boolean[N][N];
        for (int i = 0 ; i < N; i++){
            for (int j = 0 ; j < N; j++){
                if (!visited[i][j]) {
                    res2++;
                    DFS(i, j);
                }
            }
        }

        System.out.println(res1 + " " + res2);
    }

    static void DFS(int x, int y){
        if (visited[x][y]) return ;
        char curColor = map[x][y];
        visited[x][y] = true;

        for (int i = 0; i < 4; i++){
            int nx = x + dx[i], ny = y + dy[i];

            if (nx < 0 || nx >= N || ny < 0 || ny >= N)
                continue;
            if (curColor != map[nx][ny] || visited[nx][ny])
                continue;

            DFS(nx, ny);
        }
    }
}