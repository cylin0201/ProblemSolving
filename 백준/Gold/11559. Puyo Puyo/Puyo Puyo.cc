#include <bits/stdc++.h>
using namespace std;

int dx[] = { -1, 1, 0, 0 };
int dy[] = { 0, 0, -1, 1 };

const int N = 12, M = 6;
int res;
bool boombed = true;
vector<string> v;
vector<vector<bool>> visited;

void drop() {
    for (int i = 0; i < M; i++) {
        string temp;
        for (int j = N - 1; j >= 0; j--) {
            if (v[j][i] == '.') continue;
            temp.push_back(v[j][i]);
            v[j][i] = '.';
        }
        for (int j = 0, idx = N - 1; j < temp.size(); j++, idx--)
            v[idx][i] = temp[j];
    }
}

bool BFS(int x, int y) {
    visited[x][y] = true;
    char tar = v[x][y];
    queue<pair<int, int>> q;
    vector <pair<int, int>> temp;
    q.push({ x, y });
    temp.push_back({ x, y });

    while (!q.empty()) {
        auto [cx, cy] = q.front(); q.pop();

        for (int i = 0; i < 4; i++) {
            int nx = cx + dx[i], ny = cy + dy[i];
            if (nx < 0 || nx >= N || ny < 0 || ny >= M || v[nx][ny] != tar)
                continue;
            if (visited[nx][ny]) continue;
            
            visited[nx][ny] = true;
            temp.push_back({ nx, ny });
            q.push({ nx, ny });
        }
    }
    
    if (temp.size() < 4) return false;

    for (int i = 0; i < temp.size(); i++)
        v[temp[i].first][temp[i].second] = '.';
    
    return true;
}

int main() {
    v.assign(N, "");
    for (int i = 0; i < N; i++)
        cin >> v[i];

    while (true) {
        visited.assign(N, vector<bool>(M, false));
        bool exploded = false;

        // 1. 터질 수 있는 그룹 탐색
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (v[i][j] == '.' || visited[i][j]) continue;
                if (BFS(i, j)) exploded = true;
            }
        }

        // 2. 더 이상 터질 게 없으면 종료
        if (!exploded) break;

        // 3. 떨어뜨리기
        drop();

        // 4. 연쇄 증가
        res++;
    }

    cout << res << '\n';

    return 0;
}