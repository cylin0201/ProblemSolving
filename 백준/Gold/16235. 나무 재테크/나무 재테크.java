import java.io.*;
import java.util.*;

public class Main {
    static int N, M, K;
    static int[][] m, A;
    static List<Integer>[][] trees;

    static int[] dx = {-1,-1,-1,0,0,1,1,1};
    static int[] dy = {-1,0,1,-1,1,-1,0,1};

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        m = new int[N][N];
        A = new int[N][N];
        trees = new ArrayList[N][N];

        // 초기 양분 5
        for(int i = 0; i < N; i++) {
            Arrays.fill(m[i], 5);
            for(int j = 0; j < N; j++) {
                trees[i][j] = new ArrayList<>();
            }
        }

        // 겨울 양분
        for(int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j < N; j++) {
                A[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        // 초기 나무
        for(int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken()) - 1;
            int y = Integer.parseInt(st.nextToken()) - 1;
            int age = Integer.parseInt(st.nextToken());

            trees[x][y].add(age);
        }

        // K년 시뮬레이션
        for(int year = 0; year < K; year++) {
            springAndSummer();
            fall();
            winter();
        }

        int answer = 0;
        for(int i = 0; i < N; i++) {
            for(int j = 0; j < N; j++) {
                answer += trees[i][j].size();
            }
        }

        System.out.println(answer);
    }

    static void springAndSummer() {
        for(int i = 0; i < N; i++) {
            for(int j = 0; j < N; j++) {
                if(trees[i][j].isEmpty()) continue;

                Collections.sort(trees[i][j]); // 나이순

                List<Integer> alive = new ArrayList<>();
                int deadEnergy = 0;

                for(int age : trees[i][j]) {
                    if(m[i][j] >= age) {
                        m[i][j] -= age;
                        alive.add(age + 1);
                    } else {
                        deadEnergy += age / 2;
                    }
                }

                m[i][j] += deadEnergy;
                trees[i][j] = alive;
            }
        }
    }

    static void fall() {
        List<Integer>[][] breeding = new ArrayList[N][N];

        for(int i = 0; i < N; i++) {
            for(int j = 0; j < N; j++) {
                breeding[i][j] = new ArrayList<>();
            }
        }

        for(int i = 0; i < N; i++) {
            for(int j = 0; j < N; j++) {
                for(int age : trees[i][j]) {
                    if(age % 5 != 0) continue;

                    for(int d = 0; d < 8; d++) {
                        int nx = i + dx[d];
                        int ny = j + dy[d];

                        if(nx < 0 || ny < 0 || nx >= N || ny >= N) continue;

                        breeding[nx][ny].add(1);
                    }
                }
            }
        }

        // 번식 적용
        for(int i = 0; i < N; i++) {
            for(int j = 0; j < N; j++) {
                if(!breeding[i][j].isEmpty()) {
                    // 어린 나무가 먼저여야 하므로 앞에 추가
                    breeding[i][j].addAll(trees[i][j]);
                    trees[i][j] = breeding[i][j];
                }
            }
        }
    }

    static void winter() {
        for(int i = 0; i < N; i++) {
            for(int j = 0; j < N; j++) {
                m[i][j] += A[i][j];
            }
        }
    }
}