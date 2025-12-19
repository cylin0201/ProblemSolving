#include <bits/stdc++.h>
#define INF 1e9
using namespace std;

int N, M;
vector<vector<int>> beads;

//beads[x][y]는 x번이 y번보다 무겁다라는 의미
//즉 플로이드-워셜을 하면 x번 보다 y가 무겁다라는 것과 y번보다 z번이 더 무겁다는 것에서 x번보다 z번이 더 무겁다(2)로 나올 수 있음. 이는 x
int main() {
    cin >> N >> M;
    beads.assign(N + 1, vector<int>(N + 1, 1e9));
    for (int i = 1; i <= N; i++)
        beads[i][i] = 0;

    for (int i = 0; i < M; i++) {
        int a, b; cin >> a >> b;
        beads[a][b] = 1;
    }

    for (int k = 1; k <= N; k++) {
        for (int i = 1; i <= N; i++) {
            for (int j = 1; j <= N; j++) {
                beads[i][j] = min(beads[i][j], beads[i][k] + beads[k][j]);
            }
        }
    }
    
    int answer = 0;

    // 각 구슬 검사
    for (int i = 1; i <= N; i++) {
        int heavier = 0; // 나보다 무거운 구슬
        int lighter = 0; // 나보다 가벼운 구슬

        for (int j = 1; j <= N; j++) {
            if (i == j) continue;

            if (beads[j][i] < INF) heavier++; // j가 i보다 무거움
            if (beads[i][j] < INF) lighter++; // i가 j보다 무거움
        }

        // 중앙값이 될 수 없는 경우
        if (heavier > N / 2 || lighter > N / 2) {
            answer++;
        }
    }

    cout << answer << '\n';
    return 0;
}