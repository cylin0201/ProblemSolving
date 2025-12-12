#include <bits/stdc++.h>
using namespace std;

int N, K;
vector<vector<int>> dp;
vector<int> weight, value;

int main() {
    cin >> N >> K;
    dp.assign(K + 1, vector<int>(N + 1, 0));
    weight.assign(K + 1, 0);
    value.assign(K + 1, 0);

    for (int i = 1; i <= K; i++)
        cin >> value[i] >> weight[i];
    
    for (int i = 1; i <= K; i++) {
        for (int j = 0; j <= N; j++) {
            if (weight[i] > j) dp[i][j] = dp[i - 1][j];
            else dp[i][j] = max(dp[i - 1][j], dp[i - 1][j - weight[i]] + value[i]);
        }
    }
    
    cout << dp[K][N] << '\n';

    return 0;
}