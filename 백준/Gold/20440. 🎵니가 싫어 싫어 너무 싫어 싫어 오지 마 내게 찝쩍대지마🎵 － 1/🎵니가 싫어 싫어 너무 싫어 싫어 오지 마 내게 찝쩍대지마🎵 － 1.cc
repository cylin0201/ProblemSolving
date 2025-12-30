#include <bits/stdc++.h>
using namespace std;

int N;
vector<int> v;
unordered_map<int, int> um;

int main() {
    ios::sync_with_stdio(false);
    cin.tie(nullptr);

    cin >> N;

    int start, end;
    for (int i = 0; i < N; i++) {
        cin >> start >> end;
        v.push_back(start);
        v.push_back(end);
        um[start]++;
        um[end]--;
    }

    sort(v.begin(), v.end());
    v.erase(unique(v.begin(), v.end()), v.end());

    int cur = 0;
    int maxCnt = 0;
    int startTime = 0, endTime = 0;
    bool started = false;

    for (const auto& elem : v) {
        cur += um[elem];
        maxCnt = max(maxCnt, cur);
    }

    cur = 0;
    for (const auto& elem : v) {
        cur += um[elem];

        // 최대 인원 갱신
        if (!started && cur == maxCnt) {
            startTime = elem;
            started = true;
        }
        // 최대 구간 종료
        else if (started && cur < maxCnt) {
            endTime = elem;
            break;
        }
    }
    cout << maxCnt << '\n';
    cout << startTime << ' ' << endTime << '\n';

    return 0;
}