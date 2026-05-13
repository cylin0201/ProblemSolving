import java.util.*;
import java.io.*;

class Solution {
    static char[] input;
    static int cnt;
    static int res;
    static Set<String>[] visited;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st;

        int T = Integer.parseInt(br.readLine());

        for (int t = 1; t <= T; t++) {
            st = new StringTokenizer(br.readLine());
            input = st.nextToken().toCharArray();
            cnt = Integer.parseInt(st.nextToken());

            res = 0;
            visited = new HashSet[cnt + 1];

            for (int i = 0; i <= cnt; i++) {
                visited[i] = new HashSet<>();
            }

            dfs(0);

            System.out.println("#" + t + " " + res);
        }
    }

    static void dfs(int depth) {
        String current = String.valueOf(input);

        if (visited[depth].contains(current)) {
            return;
        }

        visited[depth].add(current);

        if (depth == cnt) {
            res = Math.max(res, Integer.parseInt(current));
            return;
        }

        for (int i = 0; i < input.length; i++) {
            for (int j = i + 1; j < input.length; j++) {
                swap(i, j);
                dfs(depth + 1);
                swap(i, j);
            }
        }
    }

    static void swap(int a, int b) {
        char temp = input[a];
        input[a] = input[b];
        input[b] = temp;
    }
}