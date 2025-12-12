#include <bits/stdc++.h>
#define INF 1e9
using namespace std;

int main() {
    int C, N;
    cin >> C >> N;

    vector<pair<int, int>> ad(N + 1);
    for (int i = 1; i <= N; i++)
        cin >> ad[i].first >> ad[i].second;
    
    int MAX = C + 100;

    vector<vector<int>> dp(N + 1, vector<int>(MAX + 1, INF));
    dp[0][0] = 0;

    for (int i = 1; i <= N; i++) {
        int cost = ad[i].first;
        int customer = ad[i].second;

        for (int j = 0; j <= MAX; j++) {
            // 1) 이 도시 광고를 사용하지 않은 경우
            dp[i][j] = dp[i - 1][j];

            // 2) 이 도시 광고를 하나 이상 사용하는 경우 (무한 배낭)
            if (j >= customer) {
                dp[i][j] = min(dp[i][j], dp[i][j - customer] + cost);
            }
        }
    }

    int answer = INF;
    for (int j = C; j <= MAX; j++)
        answer = min(answer, dp[N][j]);

    cout << answer << "\n";
    return 0;
}
