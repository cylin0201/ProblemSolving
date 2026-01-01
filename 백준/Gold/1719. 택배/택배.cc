#include <bits/stdc++.h>
using namespace std;

int N, M;
vector<vector<int>> m, res;
int main() {
    cin >> N >> M;
    m.assign(N + 1, vector<int>(N + 1, 1e9));
    res.assign(N + 1, vector<int>(N + 1, 1e9));

    for (int i = 0; i < M; i++) {
        int a, b, d; cin >> a >> b >> d;
        m[a][b] = d;
        m[b][a] = d;

        res[a][b] = b;
        res[b][a] = a;
    }

    for (int i = 1; i <= N; i++)
        m[i][i] = 0;

    for (int k = 1; k <= N; k++){
        for (int i = 1; i <= N; i++) {
            for (int j = 1; j <= N; j++) {
                if (m[i][j] > m[i][k] + m[k][j]) {
                    m[i][j] = m[i][k] + m[k][j];
                    res[i][j] = res[i][k];
                }
            }
        }
    }

    for (int i = 1; i <= N; i++) {
        for (int j = 1; j <= N; j++) {
            if (i != j) cout << res[i][j] << ' ';
            else cout << '-' << ' ';
        }
        cout << '\n';
    }
    return 0;
}
