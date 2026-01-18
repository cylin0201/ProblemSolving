#include <bits/stdc++.h>
using namespace std;

int n, m;
vector<bool> v;
vector<vector<int>> graph;

void BFS(int node) {
    v[node] = true;
    queue<pair<int, int>> q;
    q.push({ node, 0 });

    while (!q.empty()) {
        auto [cur, step] = q.front(); q.pop();

        if (step > 2) break;
        v[cur] = true;

        for (auto next : graph[cur]) {
            q.push({ next, step + 1 });
        }
    }
}


int main() {
    cin >> n >> m;
    v.assign(n + 1, false);
    graph.assign(n + 1, {});

    for (int i = 0; i < m; i++) {
        int a, b; cin >> a >> b;
        graph[a].push_back(b);
        graph[b].push_back(a);
    }

    BFS(1);

    int res = 0;
    for (int i = 2; i <= n; i++)
        if (v[i]) res++;

    cout << res << '\n';

    return 0;
}