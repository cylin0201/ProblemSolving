#include <bits/stdc++.h>
using namespace std;

int N;
vector<int> v;
vector<vector<int>> rev;
vector<int> dist;

int main() {
    ios::sync_with_stdio(false);
    cin.tie(nullptr);

    cin >> N;
    v.resize(N + 1);
    rev.assign(N + 1, {});
    dist.assign(N + 1, -1);

    for (int i = 1; i <= N; i++) {
        cin >> v[i];
        rev[v[i]].push_back(i);  // 역방향 간선
    }

    queue<int> q;
    q.push(N);
    dist[N] = 0;

    while (!q.empty()) {
        int cur = q.front(); q.pop();
        for (int next : rev[cur]) {
            if (dist[next] != -1) continue;
            dist[next] = dist[cur] + 1;
            q.push(next);
        }
    }

    for (int i = 1; i <= N; i++)
        cout << dist[i] << '\n';

    return 0;
}
