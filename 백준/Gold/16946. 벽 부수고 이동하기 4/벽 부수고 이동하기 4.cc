#include <bits/stdc++.h>
using namespace std;
int dx[] = { -1, 1, 0, 0 };
int dy[] = { 0, 0, -1, 1 };
int N, M, cnt;
vector<vector<int>> m;
vector<vector<int>> groupId;
vector<vector<bool>> visited;
vector<pair<pair<int, int>, int>> noWalls; //{ { x, y }, 0갯수 }

void DFS(int x, int y, int gid) {
    if (visited[x][y]) return;
    visited[x][y] = true;
    groupId[x][y] = gid;
    cnt++;

    for (int i = 0; i < 4; i++) {
        int nx = x + dx[i], ny = y + dy[i];
        if ((nx < 0 || nx >= N || ny < 0 || ny >= M) || visited[nx][ny])
            continue;
        if (m[nx][ny] == 1) continue;

        DFS(nx, ny, gid);
    }
}

int main() {
    cin >> N >> M;
    m.assign(N, vector<int>(M, 0));
    groupId.assign(N, vector<int>(M, -1));
    visited.assign(N, vector<bool>(M, false));

    for (int i = 0; i < N; i++) {
        string input; cin >> input;
        for (int j = 0; j < input.size(); j++) {
            m[i][j] = input[j] - '0';
        }
    }

    int gid = 0;
    for (int i = 0; i < N; i++) {
        for (int j = 0; j < M; j++) {
            if (m[i][j] == 0) {
                if (!visited[i][j]) {
                    DFS(i, j, gid);
                    noWalls.push_back({ { i, j }, cnt });
                    cnt = 0;
                    gid++;
                }
            }
        }
    }

    for (int i = 0; i < N; i++) {
        for (int j = 0; j < M; j++) {
            if (m[i][j] == 1) {
                set<int> groups;
                for (int k = 0; k < 4; k++) {
                    int nx = i + dx[k], ny = j + dy[k];
                    if (nx < 0 || nx >= N || ny < 0 || ny >= M)
                        continue;
                    if (groupId[nx][ny] != -1)
                        groups.insert(groupId[nx][ny]);
                }

                int result = 1;
                for (int g : groups) {
                    result += noWalls[g].second;
                }
                cout << result % 10;
            }
            else cout << 0;
        }
        cout << '\n';
    }

    return 0;
}