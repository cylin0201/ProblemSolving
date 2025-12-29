#include <bits/stdc++.h>
using namespace std;

struct Edge {
    int a, b, d;

    bool operator<(const Edge& e) const{
        return d < e.d;
    }
};

int N, M;
vector<Edge> v;
vector<int> parent;
priority_queue<Edge, vector<Edge>> pq;

int getParent(int x) {
    if (x == parent[x]) return x;
    return parent[x] = getParent(parent[x]);
}

bool isUnion(int a, int b) {
    return getParent(a) == getParent(b);
}

void unionParent(int a, int b) {
    a = getParent(a), b = getParent(b);

    if (a < b) parent[a] = b;
    else parent[b] = a;
}

int main() {
    cin >> N >> M;
    parent.assign(N + 1, 0);

    for (int i = 1; i <= N; i++)
        parent[i] = i;

    int a, b, d;
    for (int i = 0; i < M; i++) {
        cin >> a >> b >> d; 
        pq.push({ a, b, d });
    }

    int x, y; cin >> x >> y; 
    
    int answer = 0;

    while (!pq.empty()) {
        int cur = pq.top().a, to = pq.top().b, w = pq.top().d; pq.pop();
        if (isUnion(cur, to)) continue;
        unionParent(cur, to);

        if (isUnion(x, y)) {
            answer = w;
            break;
        }
    }

    cout << answer << '\n';

    return 0;
}