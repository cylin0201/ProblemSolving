#include <bits/stdc++.h>
using namespace std;

priority_queue<int> maxHeap;
priority_queue<int, vector<int>, greater<int>> minHeap;
int N;
vector<int> result;

int main() {
    int mid, input;
    cin >> N;

    cin >> input;
    mid = input;
    result.push_back(mid);

    for (int i = 2; i <= N; i++) {
        cin >> input;

        if (mid >= input) maxHeap.push(input);
        else minHeap.push(input);

        if (maxHeap.size() > minHeap.size()) {
            minHeap.push(mid);
            mid = maxHeap.top();
            maxHeap.pop();
        }
        else if (minHeap.size() > maxHeap.size() + 1) {
            maxHeap.push(mid);
            mid = minHeap.top();
            minHeap.pop();
        }

        result.push_back(mid);
    }

    for (auto elem : result)
        cout << elem << ' ';
    return 0;
}
