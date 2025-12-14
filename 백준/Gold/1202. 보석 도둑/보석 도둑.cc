#include <bits/stdc++.h>
using namespace std;

struct Treasure {
    int w, v;
};

struct cmpW {
    bool operator()(const Treasure& a, const Treasure& b) {
        return a.w > b.w;
    }
};

struct cmpV {
    bool operator()(const Treasure& a, const Treasure& b) {
        return a.v < b.v;
    }
};


int N, K;
long long int result;

vector<int> c;
int main() {
    cin.tie(0)->sync_with_stdio(0);

    cin >> N >> K;
    c.assign(K, 0);

    priority_queue<Treasure, vector<Treasure>, cmpV> v_maxHeap;
    priority_queue<Treasure, vector<Treasure>, cmpW> w_minHeap;

    for (int i = 1; i <= N; i++) {
        int w, v; cin >> w >> v;
        w_minHeap.push({ w, v });
    }

    for (int i = 0; i < K; i++)
        cin >> c[i];

    sort(c.begin(), c.end());

    for (int i = 0; i < c.size(); i++) {
        vector<Treasure> temp;
        while (!w_minHeap.empty() && w_minHeap.top().w <= c[i]) {
            v_maxHeap.push(w_minHeap.top());
            w_minHeap.pop();
        }

        if (!v_maxHeap.empty()) {
            result += v_maxHeap.top().v;
            v_maxHeap.pop();
        }
    }

    cout << result << '\n';

    return 0;
}