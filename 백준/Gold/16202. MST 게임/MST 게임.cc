#include <bits/stdc++.h>
using namespace std;

struct Edge {
    int a, b, d;

    bool operator< (const Edge& e) {
        return d < e.d;
    }
};

int N, M, K;
vector<int> parent, res;
vector<Edge> v, selected;

int getParent(int x) {
    if (x == parent[x]) return x;
    return parent[x] = getParent(parent[x]);
}

bool isUnion(int a, int b) {
    return getParent(a) == getParent(b);
}

void unionParent(int a, int b) {
    a = getParent(a); b = getParent(b);

    if (a < b) parent[b] = a;
    else parent[a] = b;
}

int main() {
    cin >> N >> M >> K;
    parent.assign(N + 1, 0);

    for (int i = 1; i <= N; i++)
        parent[i] = i;

    for (int i = 1; i <= M; i++) {
        int a, b; cin >> a >> b;
        v.push_back({ a, b, i });
    }

    sort(v.begin(), v.end());

    //각 턴마다 MST의 간선 제거하고, 비용 구하기
    for (int i = 0; i < K; i++) {
        int tmp = 0, cnt = 0;
        for (int j = 1; j <= N; j++)
            parent[j] = j;

        for (int j = 0; j < v.size(); j++) {
            if (i > v[j].d - 1) continue;
            if (isUnion(v[j].a, v[j].b)) continue;

            unionParent(v[j].a, v[j].b);
            tmp += v[j].d;
            cnt++;
        }

        if (cnt == N - 1) cout << tmp << ' ';
        else cout << 0 << ' ';
    }

    return 0;
}