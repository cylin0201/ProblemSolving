#include <bits/stdc++.h>
using namespace std;

int N, T;
unordered_map<int, vector<int>> um;

int main() {
    cin >> N >> T;

    for (int i = 0; i < N; i++) {
        int x, y; cin >> x >> y;
        um[y].push_back(x);
    }

    queue<pair<int, int>> q;
    q.push({ 0, 0 });

    int step = 0;

    while (!q.empty()) {
        int sz = q.size();
        while (sz--) {
            auto [cx, cy] = q.front(); q.pop();

            if (cy == T) {
                cout << step << '\n';
                return 0;
            }

            for (int ny = cy - 2; ny <= cy + 2; ny++) {
                if (!um.count(ny)) continue;

                vector<int> temp;

                for (int& nx : um[ny]) {
                    if (abs(cx - nx) <= 2) q.push({ nx, ny });
                    else temp.push_back(nx);
                }

                if (temp.empty()) um.erase(ny);
                else um[ny] = temp;
            }
        }
        step++;
    }

    cout << -1 << '\n';
    return 0;
}