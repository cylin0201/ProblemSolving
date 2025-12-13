#include <bits/stdc++.h>
using namespace std;

int N, M;
vector<int> A, S;
int main() {
    cin >> N >> M;
    A.assign(N + 1, 0);
    S.assign(N + 1, 0);

    for (int i = 1; i <= N; i++) {
        cin >> A[i];
        S[i] = A[i] + S[i - 1];
    }

    int ans = INT_MAX;

    for (int s = 1; s <= N; s++) {
        long long target = S[s - 1] + M;

        // S[s] ~ S[N] 중에서 target 이상인 최소 인덱스
        auto it = lower_bound(S.begin() + s, S.end(), target);

        if (it != S.end()) {
            int e = it - S.begin();
            ans = min(ans, e - s + 1);
        }
    }

    if (ans == INT_MAX) cout << 0 << '\n';
    else cout << ans << '\n';

    return 0;
}