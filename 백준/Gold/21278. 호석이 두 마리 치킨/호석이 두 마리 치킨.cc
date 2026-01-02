#include <bits/stdc++.h>
using namespace std;
#define MAX 1e9

int N, M;
vector<vector<int>> graph;
vector<int> dist;
vector<bool> visited;

int n1, n2, minSum = MAX;

void BFS(int node) {
    visited.assign(N + 1, false);
    queue<pair<int, int>> q;
    q.push({ node, 0 });
    visited[node] = true;

    while (!q.empty()) {
        auto [cur, d] = q.front(); q.pop();

        dist[cur] = min(dist[cur], d);

        for (int next : graph[cur]) {
            if (visited[next]) continue;
            visited[next] = true;
            q.push({ next, d + 1 });
        }
    }
}

int main() {
    cin >> N >> M;
    graph.assign(N + 1, {});

    for (int i = 0; i < M; i++) {
        int a, b; cin >> a >> b;
        graph[a].push_back(b);
        graph[b].push_back(a);
    }

    // 치킨집 조합 (i번, j번)마다 BFS로 최단거리 구해서 합하기
    for (int i = 1; i <= N; i++) {
        for (int j = i + 1; j <= N; j++) {
            dist.assign(N + 1, MAX); //최단 거리 배열 초기화
            BFS(i);  BFS(j);

            int sum = 0;
            for (int node = 1; node <= N; node++) {
                if (dist[node] != MAX) sum += dist[node];
            }
            if (minSum > sum) {
                minSum = sum;
                n1 = i; n2 = j;
            }
        }
    }

    cout << n1 << ' ' << n2 << ' ' << minSum * 2 << '\n';

    return 0;
}