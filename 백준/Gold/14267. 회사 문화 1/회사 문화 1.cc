#include <bits/stdc++.h>
using namespace std;

int N, M;
vector<vector<int>> graph;
vector<int> result;

void dfs(int cur) {
    for (int next : graph[cur]) {
        result[next] += result[cur];
        dfs(next);
    }
}

int main() {
    cin >> N >> M;
    result.assign(N + 1, 0);
    graph.assign(N + 1, {});

    int root = -1;
    for (int i = 1; i <= N; i++) {
        int p; cin >> p;
        if (p == -1) root = i;
        else graph[p].push_back(i);
    }

    // 각자 자신이 받은 칭찬만 기록
    for (int i = 0; i < M; i++) {
        int a, b; cin >> a >> b;
        result[a] += b;
    }

    // 루트에서부터 전체 누적치 DFS 전파
    dfs(root);

    for (int i = 1; i <= N; i++)
        cout << result[i] << " ";

    return 0;
}
