#include <bits/stdc++.h>
using namespace std;

//1. 좌표(i, j)의 가장 위에 있는 블록을 제거하여 인벤토리에 넣는다.                 -> 2초
//2. 인벤토리에서 블록 하나를 꺼내어 좌표(i, j)의 가장 위에 있는 블록 위에 놓는다.  -> 1초

int N, M, B;
int maxH, minH = 256;
vector<vector<int>> g;
pair<int, int> result = {1e9, 0};

void calculate(int height) {
    int inben = B, t = 0;
    for (int i = 0; i < N; i++) {
        for (int j = 0; j < M; j++) {
            
            if (g[i][j] > height) { //1번
                inben += (g[i][j] - height);
                t += 2 * (g[i][j] - height);
            }
            else if (g[i][j] < height) {     //2번
                inben -= (height - g[i][j]);
                t += (height - g[i][j]);
            }
        }
    }
    if (inben < 0)
        return;

    if (result.first >= t)
        result = { t, height };
}

int main() {
    cin >> N >> M >> B;
    g.assign(N, vector<int>(M, 0));
    for (int i = 0; i < N; i++) {
        for (int j = 0; j < M; j++) {
            cin >> g[i][j];
            maxH = max(maxH, g[i][j]);
            minH = min(minH, g[i][j]);
        }
    }
    for (int h = minH; h <= maxH; h++)
        calculate(h);

    cout << result.first << ' ' << result.second << '\n';

    return 0;
}