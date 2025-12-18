#include <bits/stdc++.h>
using namespace std;

int main() {
    ios::sync_with_stdio(false);
    cin.tie(nullptr);

    int T;
    cin >> T;

    while (T--) {
        int N;
        cin >> N;

        unordered_map<int, int> cnt;
        priority_queue<int> maxHeap;
        priority_queue<int, vector<int>, greater<int>> minHeap;

        while (N--) {
            char cmd;
            int x;
            cin >> cmd >> x;

            if (cmd == 'I') {
                maxHeap.push(x);
                minHeap.push(x);
                cnt[x]++;
            }
            else { // D
                if (x == 1) {  // delete max
                    while (!maxHeap.empty() && cnt[maxHeap.top()] == 0)
                        maxHeap.pop();

                    if (!maxHeap.empty()) {
                        cnt[maxHeap.top()]--;
                        maxHeap.pop();
                    }
                }
                else {         // delete min
                    while (!minHeap.empty() && cnt[minHeap.top()] == 0)
                        minHeap.pop();

                    if (!minHeap.empty()) {
                        cnt[minHeap.top()]--;
                        minHeap.pop();
                    }
                }
            }
        }

        // 최종 정리
        while (!maxHeap.empty() && cnt[maxHeap.top()] == 0)
            maxHeap.pop();
        while (!minHeap.empty() && cnt[minHeap.top()] == 0)
            minHeap.pop();

        if (maxHeap.empty() || minHeap.empty()) {
            cout << "EMPTY\n";
        }
        else {
            cout << maxHeap.top() << ' ' << minHeap.top() << '\n';
        }
    }

    return 0;
}
