import java.util.*;
import java.io.*;

class Main {
    static int N;
    static int maxVal = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine().trim());
        int[][] board = new int[N][N];
        
        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                board[i][j] = Integer.parseInt(st.nextToken());
                maxVal = Math.max(maxVal, board[i][j]); // 초기 보드에서의 최댓값 갱신
            }
        }

        // 백트래킹(DFS) 시작: 깊이 0, 초기 상태 보드
        dfs(0, board);
        System.out.println(maxVal);
    }

    static void dfs(int depth, int[][] board) {
        // 최대 5번 이동했으면 최댓값을 찾고 종료
        if (depth == 5) {
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) {
                    maxVal = Math.max(maxVal, board[i][j]);
                }
            }
            return;
        }

        // 상(0), 하(1), 좌(2), 우(3) 4방향으로 탐색
        for (int dir = 0; dir < 4; dir++) {
            int[][] nextBoard = move(dir, board);
            dfs(depth + 1, nextBoard);
        }
    }

    // 보드를 특정 방향으로 기울였을 때의 새로운 보드를 반환하는 함수
    static int[][] move(int dir, int[][] board) {
        int[][] nextBoard = new int[N][N];

        if (dir == 0) { // UP (위로 밀기)
            for (int c = 0; c < N; c++) {
                int idx = 0;     // 값이 채워질 위치
                int prev = 0;    // 이전에 읽은 블록 값
                for (int r = 0; r < N; r++) {
                    if (board[r][c] == 0) continue; // 빈 칸은 패스
                    
                    if (prev == 0) {
                        prev = board[r][c]; // 첫 블록을 만났을 때
                    } else if (prev == board[r][c]) {
                        nextBoard[idx++][c] = prev * 2; // 같은 블록이면 합침
                        prev = 0; // 한 번 합쳐진 블록은 다시 합쳐질 수 없으므로 0으로 초기화
                    } else {
                        nextBoard[idx++][c] = prev; // 다른 블록이면 이전 블록을 확정하고
                        prev = board[r][c]; // 현재 블록을 이전 블록으로 갱신
                    }
                }
                if (prev != 0) nextBoard[idx][c] = prev; // 마지막에 남은 블록 처리
            }
        } else if (dir == 1) { // DOWN (아래로 밀기)
            for (int c = 0; c < N; c++) {
                int idx = N - 1;
                int prev = 0;
                for (int r = N - 1; r >= 0; r--) {
                    if (board[r][c] == 0) continue;
                    
                    if (prev == 0) {
                        prev = board[r][c];
                    } else if (prev == board[r][c]) {
                        nextBoard[idx--][c] = prev * 2;
                        prev = 0;
                    } else {
                        nextBoard[idx--][c] = prev;
                        prev = board[r][c];
                    }
                }
                if (prev != 0) nextBoard[idx][c] = prev;
            }
        } else if (dir == 2) { // LEFT (왼쪽으로 밀기)
            for (int r = 0; r < N; r++) {
                int idx = 0;
                int prev = 0;
                for (int c = 0; c < N; c++) {
                    if (board[r][c] == 0) continue;
                    
                    if (prev == 0) {
                        prev = board[r][c];
                    } else if (prev == board[r][c]) {
                        nextBoard[r][idx++] = prev * 2;
                        prev = 0;
                    } else {
                        nextBoard[r][idx++] = prev;
                        prev = board[r][c];
                    }
                }
                if (prev != 0) nextBoard[r][idx] = prev;
            }
        } else if (dir == 3) { // RIGHT (오른쪽으로 밀기)
            for (int r = 0; r < N; r++) {
                int idx = N - 1;
                int prev = 0;
                for (int c = N - 1; c >= 0; c--) {
                    if (board[r][c] == 0) continue;
                    
                    if (prev == 0) {
                        prev = board[r][c];
                    } else if (prev == board[r][c]) {
                        nextBoard[r][idx--] = prev * 2;
                        prev = 0;
                    } else {
                        nextBoard[r][idx--] = prev;
                        prev = board[r][c];
                    }
                }
                if (prev != 0) nextBoard[r][idx] = prev;
            }
        }
        
        return nextBoard;
    }
}