#include <bits/stdc++.h>
using namespace std;

int N, R, gigaLength,gigaNode, longestLength;
vector<vector<pair<int, int>>> tree;
vector<bool> visited;
vector<int> dist;

void getGigaNode(int node) {
    if (tree[node].size() >= 3) {
        gigaNode = node;
        return;
    }
    
    visited[node] = true;
    
    for (auto next : tree[node]) {
        if (!visited[next.first]) {
            gigaLength += next.second;
            getGigaNode(next.first);
        }
    }
}

void getBranchLength(int node) {
    visited[node] = true;

    for (auto next : tree[node]) {
        if (visited[next.first]) continue;

        dist[next.first] = dist[node] + next.second;
        getBranchLength(next.first);
    }
}

int main() {
    cin >> N >> R;
    tree.assign(N + 1, {});
    visited.assign(N + 1, false);
    dist.assign(N + 1, 0);

    for (int i = 0; i < N - 1; i++) {
        int a, b, d; cin >> a >> b >> d;
        tree[a].push_back({ b, d });
        tree[b].push_back({ a, d });
    }

    if (tree[R].size() != 1)
        gigaNode = R;
    else getGigaNode(R);

    getBranchLength(gigaNode);
    longestLength = *max_element(dist.begin(), dist.end());

    cout << gigaLength << ' ' << longestLength << '\n';

    return 0;
}