#include <bits/stdc++.h>
using namespace std;

int dx[] = { -1, 1, 0, 0 };
int dy[] = { 0, 0, -1, 1 };

int N, M;
int minTime = 1e9;
vector<vector<int>> m, t;
vector<pair<int, int>> v;
vector<vector<bool>> visited;
vector<int> activated;

void BFS() {
    t.assign(N, vector<int>(N, 1e9));
    visited.assign(N, vector<bool>(N, false));
    queue<pair<int, int>> q;

    for (int idx : activated) {
        auto [x, y] = v[idx];
        q.push({ x, y });
        visited[x][y] = true;
        t[x][y] = 0;
    }

    while (!q.empty()) {
        auto [cx, cy] = q.front(); q.pop();

        for (int d = 0; d < 4; d++) {
            int nx = cx + dx[d], ny = cy + dy[d];

            if (nx < 0 || nx >= N || ny < 0 || ny >= N) continue;
            if (visited[nx][ny] || m[nx][ny] == 1) continue;

            visited[nx][ny] = true;
            t[nx][ny] = t[cx][cy] + 1;
            q.push({ nx, ny });
        }
    }

    int maxTime = 0;
    for (int i = 0; i < N; i++) {
        for (int j = 0; j < N; j++) {
            if (m[i][j] == 0) {
                if (t[i][j] == 1e9) return;
                maxTime = max(maxTime, t[i][j]);
            }
        }
    }

    minTime = min(minTime, maxTime);
}


void combVirus(int n, int cnt) {
    if (cnt == M) {
        BFS();
        return;
    }

    for (int i = n + 1; i < v.size(); i++) {
        activated.push_back(i);
        combVirus(i, cnt + 1);
        activated.pop_back();
    }
}

int main() {
    cin >> N >> M;
    m.assign(N, vector<int>(N, 0));

    for (int i = 0; i < N; i++) {
        for (int j = 0; j < N; j++) {
            cin >> m[i][j];
        
            if (m[i][j] == 2) v.push_back({ i, j });
        }
    }
    
    combVirus(-1, 0);

    if (minTime == 1e9) cout << -1 << '\n';
    else cout << minTime << '\n';

    return 0;
}