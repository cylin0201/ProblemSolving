#include <bits/stdc++.h>
using namespace std;

int N, K;
vector<int> v;
unordered_map<long long, int> um;
long long cnt;

int main() {
    cin.tie(nullptr)->sync_with_stdio(false);

    cin >> N >> K;
    v.assign(N + 1, 0);

    um[0] = 1;
    
    int num;
    for (int i = 1; i <= N; i++) {
        cin >> num;
        v[i] = v[i - 1] + num;

        cnt += um[v[i] - K];

        um[v[i]]++;
    }

    cout << cnt << '\n';
    return 0;
}