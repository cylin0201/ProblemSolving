#include <bits/stdc++.h>
#define N 5
using namespace std;

int dx[] = { -1, 1, 0, 0 };
int dy[] = { 0, 0, -1, 1 };

vector<string> v;
vector<vector<bool>> visited;
vector<pair<int, int>> r;
set<vector<pair<int, int>>> s;
int res;

void DFS(int x, int y, int cnt, int sCnt) {
    if (cnt == 7) {
        if (sCnt >= 4) {
            auto temp = r;
            sort(temp.begin(), temp.end());
            s.insert(temp);
        }
        return;
    }

    if (sCnt + (7 - cnt) < 4) //가지치기
        return;

    for (auto [cx, cy] : r) {
        for (int i = 0; i < 4; i++) {
            int nx = cx + dx[i], ny = cy + dy[i];
            if (nx < 0 || nx >= N || ny < 0 || ny >= N) continue;
            if (visited[nx][ny]) continue;

            r.push_back({ nx, ny });
            visited[nx][ny] = true;

            if (v[nx][ny] == 'S') DFS(nx, ny, cnt + 1, sCnt + 1);
            else DFS(nx, ny, cnt + 1, sCnt);

            r.pop_back();
            visited[nx][ny] = false;
        }
    }
}

int main() {
    v.assign(N, "");

    for (int i = 0; i < N; i++)
        cin >> v[i];

    visited.assign(N, vector<bool>(N, false));
    for (int i = 0; i < N; i++) {
        for (int j = 0; j < N; j++) {
            visited[i][j] = true;
            r.push_back({ i, j });

            if (v[i][j] == 'S') DFS(i, j, 1, 1);
            else DFS(i, j, 1, 0);

            visited[i][j] = false;
            r.pop_back();
        }
    }

    cout << s.size() << '\n';

    return 0;
}