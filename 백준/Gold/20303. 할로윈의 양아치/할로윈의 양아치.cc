#include <bits/stdc++.h>
using namespace std;

int N, M, K;
vector<int> parent, sz, candy;

int getParent(int x) {
    if (parent[x] == x) return x;
    return parent[x] = getParent(parent[x]);
}

void unionParent(int a, int b) {
    a = getParent(a);
    b = getParent(b);
    if (a == b) return;

    if (a < b) {
        parent[b] = a;
        sz[a] += sz[b];
        candy[a] += candy[b];
    }
    else {
        parent[a] = b;
        sz[b] += sz[a];
        candy[b] += candy[a];
    }
}

int main() {
    ios::sync_with_stdio(false);
    cin.tie(nullptr);

    cin >> N >> M >> K;

    parent.assign(N + 1, 0);
    sz.assign(N + 1, 1);
    candy.resize(N + 1);

    for (int i = 1; i <= N; i++) {
        cin >> candy[i];
        parent[i] = i;
    }

    for (int i = 0; i < M; i++) {
        int a, b;
        cin >> a >> b;
        unionParent(a, b);
    }

    vector<pair<int, int>> groups;
    for (int i = 1; i <= N; i++) {
        if (getParent(i) == i) {
            groups.push_back({ sz[i], candy[i] });
        }
    }

    int G = groups.size();     // 그룹 개수
    vector<vector<int>> dp(G + 1, vector<int>(K, 0));

    for (int i = 1; i <= G; i++) {
        int w = groups[i - 1].first;   // 인원수
        int v = groups[i - 1].second;  // 사탕수

        for (int j = 0; j < K; j++) {
            // 해당 그룹을 선택하지 않는 경우
            dp[i][j] = dp[i - 1][j];

            // 해당 그룹을 선택하는 경우 (단 j >= w)
            if (j >= w) {
                dp[i][j] = max(dp[i][j], dp[i - 1][j - w] + v);
            }
        }
    }
    cout << dp[G][K - 1] << "\n";

    return 0;
}
