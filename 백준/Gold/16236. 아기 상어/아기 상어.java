import java.io.*;
import java.util.*;

public class Main {

    static int N;
    static int[][] map;
    static int sx, sy;
    static int size = 2;
    static int eat = 0;
    static int time = 0;

    static int[] dx = {-1, 0, 0, 1};
    static int[] dy = {0, -1, 1, 0};

    static class Node {
        int x, y, dist;
        Node(int x, int y, int dist) {
            this.x = x;
            this.y = y;
            this.dist = dist;
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        map = new int[N][N];

        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
                if (map[i][j] == 9) {
                    sx = i;
                    sy = j;
                    map[i][j] = 0;
                }
            }
        }

        while (true) {
            Node target = bfs();

            if (target == null) break;

            sx = target.x;
            sy = target.y;
            time += target.dist;

            map[sx][sy] = 0;
            eat++;

            if (eat == size) {
                size++;
                eat = 0;
            }
        }

        System.out.println(time);
    }

    static Node bfs() {
        boolean[][] visited = new boolean[N][N];
        Queue<Node> q = new ArrayDeque<>();

        q.offer(new Node(sx, sy, 0));
        visited[sx][sy] = true;

        List<Node> candidates = new ArrayList<>();

        while (!q.isEmpty()) {
            Node cur = q.poll();

            for (int i = 0; i < 4; i++) {
                int nx = cur.x + dx[i];
                int ny = cur.y + dy[i];

                if (nx < 0 || ny < 0 || nx >= N || ny >= N) continue;
                if (visited[nx][ny]) continue;
                if (map[nx][ny] > size) continue; // 못 지나감

                visited[nx][ny] = true;
                q.offer(new Node(nx, ny, cur.dist + 1));

                // 먹을 수 있는 물고기
                if (map[nx][ny] > 0 && map[nx][ny] < size) {
                    candidates.add(new Node(nx, ny, cur.dist + 1));
                }
            }
        }

        if (candidates.isEmpty()) return null;

        // 우선순위: 거리 → 위 → 왼쪽
        Collections.sort(candidates,(a, b) -> {
            if (a.dist != b.dist) return a.dist - b.dist;
            if (a.x != b.x) return a.x - b.x;
            return a.y - b.y;
        });

        return candidates.get(0);
    }
}