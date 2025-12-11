#include <bits/stdc++.h>
using namespace std;

int dx[] = { -1 ,1, 0, 0 }; // {U, D, L, R}
int dy[] = { 0, 0, -1, 1 }; //

int cycle;
int N, M;
vector<string> m;
vector<vector<int>> state; //0이면 미방문, 1이면 사이클 도는 중, 2이면 이미 방문한 곳
vector<pair<int, int>> nodes;

void simulate(int x, int y) {
    state[x][y] = 1;
    nodes.push_back({ x,y });
    int dir;

    if (m[x][y] == 'U') dir = 0;
    else if (m[x][y] == 'D') dir = 1;
    else if (m[x][y] == 'L') dir = 2;
    else dir = 3;

    int nx = x + dx[dir], ny = y + dy[dir];

    if (state[nx][ny] == 1) { //사이클 발견
        for (auto elem : nodes) state[elem.first][elem.second] = 2;
        cycle++;
        return;
    }
    else if (state[nx][ny] == 2) {
        state[x][y] = 2;
        return;
    }

    simulate(nx, ny);
    state[x][y] = 2;
}

int main() {
    cin >> N >> M;
    m.assign(N, "");
    state.assign(N, vector<int>(M, 0));

    for (int i = 0; i < N; i++)
        cin >> m[i];

    for (int i = 0; i < N; i++) {
        for (int j = 0; j < M; j++) {
            if (state[i][j]) continue;
            nodes.clear();
            simulate(i, j);
        }
    }
    cout << cycle << '\n';

    return 0;
}