#include <bits/stdc++.h>
using namespace std;

int N, M, t = 1;
vector<string> m;
queue<pair<int, int>> f, jihoon;
vector<vector<bool>> burned, visited;

int dx[] = { -1, 1, 0, 0 };
int dy[] = { 0, 0, -1, 1 };

int main() {
    ios::sync_with_stdio(false);
    cin.tie(nullptr);

    cin >> N >> M;
    m.assign(N, "");
    burned.assign(N, vector<bool>(M, false));
    visited.assign(N, vector<bool>(M, false));

    for (int i = 0; i < N; i++) {
        cin >> m[i];
        for (int j = 0; j < M; j++) {
            if (m[i][j] == 'J') {
                jihoon.push({ i, j });
                visited[i][j] = true;
            }
            if (m[i][j] == 'F') {
                f.push({ i, j });
                burned[i][j] = true;
            }
        }
    }

    while (!jihoon.empty()) {
        int fsz = f.size();
        while (fsz--) {
            auto [cx, cy] = f.front(); f.pop();
            for (int d = 0; d < 4; d++) {
                int nx = cx + dx[d], ny = cy + dy[d];
                if (nx < 0 || nx >= N || ny < 0 || ny >= M) continue;
                if (burned[nx][ny] || m[nx][ny] == '#') continue;
                burned[nx][ny] = true;
                f.push({ nx, ny });
            }
        }

        int jsz = jihoon.size();
        while (jsz--) {
            auto [cx, cy] = jihoon.front(); jihoon.pop();

            if (cx == 0 || cx == N - 1 || cy == 0 || cy == M - 1) {
                cout << t << '\n';
                return 0;
            }

            for (int d = 0; d < 4; d++) {
                int nx = cx + dx[d], ny = cy + dy[d];
                if (nx < 0 || nx >= N || ny < 0 || ny >= M) continue;
                if (visited[nx][ny] || burned[nx][ny] || m[nx][ny] == '#') continue;
                visited[nx][ny] = true;
                jihoon.push({ nx, ny });
            }
        }

        t++;
    }

    cout << "IMPOSSIBLE\n";
    return 0;
}
