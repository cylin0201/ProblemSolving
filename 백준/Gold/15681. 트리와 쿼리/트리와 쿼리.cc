#include <bits/stdc++.h>
using namespace std;

int N, R, Q;
vector<vector<int>> tree;
vector<int> dp;
vector<bool> visited;

void DFS(int node, int prev) {
    visited[node] = true;

    for (auto next : tree[node]) {
        if (!visited[next]) {
            DFS(next, node);
        }
    }

    dp[prev] += dp[node];
}

int main() {
    cin.tie(0)->sync_with_stdio(0);
    cin >> N >> R >> Q;

    dp.assign(N + 1, 1);
    tree.assign(N + 1, {});
    visited.assign(N + 1, false);

    for (int i = 0; i < N - 1; i++) {
        int a, b; cin >> a >> b;
        tree[a].push_back(b);
        tree[b].push_back(a);
    }
    
    DFS(R, 0);

    for (int i = 0; i < Q; i++) {
        int node; cin >> node;
        cout << dp[node] << '\n';
    }
        
    return 0;
}