import java.io.*;
import java.util.*;

class Main {
    static int[] parent, size;

    static class Edge {
        int u, v, w;
        Edge(int u, int v, int w) {
            this.u = u;
            this.v = v;
            this.w = w;
        }
    }

    // 쿼리 (원래 순서 복원 위해 idx 포함)
    static class Query {
        int k, v, idx;
        Query(int k, int v, int idx) {
            this.k = k;
            this.v = v;
            this.idx = idx;
        }
    }

    static int find(int x) {
        if (parent[x] == x) return x;
        return parent[x] = find(parent[x]);
    }

    // Union (size 기준으로 합치기)
    static void union(int a, int b) {
        a = find(a);
        b = find(b);

        if (a != b) {
            if (size[a] < size[b]) {
                int temp = a;
                a = b;
                b = temp;
            }
            parent[b] = a;
            size[a] += size[b];
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int Q = Integer.parseInt(st.nextToken());

        Edge[] edges = new Edge[N - 1];
        for (int i = 0; i < N - 1; i++) {
            st = new StringTokenizer(br.readLine());
            int p = Integer.parseInt(st.nextToken());
            int q = Integer.parseInt(st.nextToken());
            int r = Integer.parseInt(st.nextToken());
            edges[i] = new Edge(p, q, r);
        }

        Query[] queries = new Query[Q];
        for (int i = 0; i < Q; i++) {
            st = new StringTokenizer(br.readLine());
            int k = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            queries[i] = new Query(k, v, i); // idx 저장
        }

        // 간선 내림차순 정렬 (큰 weight부터 사용)
        Arrays.sort(edges, (a, b) -> Integer.compare(b.w, a.w));

        // 쿼리도 K 기준 내림차순
        Arrays.sort(queries, (a, b) -> Integer.compare(b.k, a.k));

        // DSU 초기화
        parent = new int[N + 1];
        size = new int[N + 1];
        for (int i = 1; i <= N; i++) {
            parent[i] = i;
            size[i] = 1;
        }

        int[] answer = new int[Q];

        int edgeIdx = 0;

        // 큰 K부터 처리 (간선 점진적으로 추가)
        for (Query q : queries) {
            // 현재 K 이상인 간선들을 모두 union
            while (edgeIdx < N - 1 && edges[edgeIdx].w >= q.k) {
                union(edges[edgeIdx].u, edges[edgeIdx].v);
                edgeIdx++;
            }

            // 현재 v가 속한 컴포넌트 크기 - 1
            answer[q.idx] = size[find(q.v)] - 1;
        }

        // 출력 (원래 순서대로)
        StringBuilder sb = new StringBuilder();
        for (int x : answer) {
            sb.append(x).append('\n');
        }
        System.out.print(sb);
    }
}