#include <bits/stdc++.h>
using namespace std;

int N, M, L, K;
vector<pair<int, int>> v;
int res;

int main() {
    cin >> N >> M >> L >> K; //N이 가로, M이 세로

    for (int i = 0; i < K; i++) {
        int x, y; cin >> x >> y;
        v.push_back({ x, y });
    }

    for (int i = 0; i < K; i++) {
        for (int k = 0; k < K; k++) {
            auto [sx, sy] = make_pair(v[i].first, v[k].second);
            auto [ex, ey] = make_pair(sx + L, sy + L);
            int cnt = 0;

            for (int j = 0; j < K; j++) {
                if (sx <= v[j].first && v[j].first <= ex && sy <= v[j].second && v[j].second <= ey)
                    cnt++;
            }
            res = max(res, cnt);
        }
    }

    cout << K - res << '\n';

    return 0;
}