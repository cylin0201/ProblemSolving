#include <bits/stdc++.h>
using namespace std;

struct Node {
    int x, y, dir, cnt;
};

int dx[] = { 1, 0 , -1, 0 }; // 하, 좌, 상, 우
int dy[] = { 0, -1, 0, 1 };

const int INF = 1e9;

int N;
vector<string> m;
vector<vector<vector<int>>> dist;
vector<pair<int, int>> s;

int main() {
    cin >> N;
    m.assign(N, "");
    dist.assign(N, vector<vector<int>>(N, vector<int>(4, INF)));

    for (int i = 0; i < N; i++) {
        cin >> m[i];
        for (int j = 0; j < N; j++) {
            if (m[i][j] == '#') s.push_back({ i, j });
        }
    }

    deque<Node> dq;
    int sx = s[0].first, sy = s[0].second;

    // 시작점: 모든 방향 비용 0
    for (int dir = 0; dir < 4; dir++) {
        dist[sx][sy][dir] = 0;
        dq.push_back({ sx, sy, dir, 0 });
    }

    while (!dq.empty()) {
        auto [cx, cy, cDir, cCnt] = dq.front();
        dq.pop_front();

        if (dist[cx][cy][cDir] < cCnt) continue;

        if (m[cx][cy] == '#' && !(cx == sx && cy == sy)) {
            cout << cCnt << '\n';
            return 0;
        }

        int nx = cx + dx[cDir], ny = cy + dy[cDir];
        if (nx >= 0 && nx < N && ny >= 0 && ny < N && m[nx][ny] != '*') {
            if (dist[nx][ny][cDir] > cCnt) {
                dist[nx][ny][cDir] = cCnt;
                dq.push_front({ nx, ny, cDir, cCnt });
            }
        }

        if (m[cx][cy] == '!') {
            for (int nd : { (cDir + 1) % 4, (cDir + 3) % 4 }) {
                nx = cx + dx[nd];
                ny = cy + dy[nd];
                if (nx >= 0 && nx < N && ny >= 0 && ny < N && m[nx][ny] != '*') {
                    if (dist[nx][ny][nd] > cCnt + 1) {
                        dist[nx][ny][nd] = cCnt + 1;
                        dq.push_back({ nx, ny, nd, cCnt + 1 });
                    }
                }
            }
        }
    }

    return 0;
}
