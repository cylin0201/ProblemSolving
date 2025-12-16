#include <bits/stdc++.h>
using namespace std;

int n, m, t;
int s, g, h;
int a, b, d;
int x;

vector<vector<pair<int, int>>> graph;
vector<int> dist, v;

void dijkstra(int start) {
    dist.assign(n + 1, 1e9);
    priority_queue<pair<int, int>> pq;

    dist[start] = 0;
    pq.push({ 0, start });

    while (!pq.empty()) {
        int cur = pq.top().second;
        int curDist = -pq.top().first;
        pq.pop();

        if (dist[cur] < curDist) continue;

        for (auto elem : graph[cur]) {
            int next = elem.second;
            int nextDist = curDist + elem.first;

            if (dist[next] > nextDist) {
                dist[next] = nextDist;
                pq.push({ -nextDist, next });
            }
        }
    }
}

int main() {
    int T; cin >> T;
    while (T --> 0) {
        cin >> n >> m >> t;
        cin >> s >> g >> h;
        graph.assign(n + 1, {});
        v.clear();

        for (int i = 0; i < m; i++) {
            cin >> a >> b >> d;

            // g-h 간선만 홀수로 만듦
            if ((a == g && b == h) || (a == h && b == g)) {
                graph[a].push_back({ 2 * d - 1, b });
                graph[b].push_back({ 2 * d - 1, a });
            }
            else {
                graph[a].push_back({ 2 * d, b });
                graph[b].push_back({ 2 * d, a });
            }
        }

        dijkstra(s);

        for (int i = 0; i < t; i++) {
            cin >> x;
            if (dist[x] != 1e9 && dist[x] % 2 == 1) {
                v.push_back(x);
            }
        }

        sort(v.begin(), v.end());
        for (int e : v)
            cout << e << " ";
        cout << '\n';

    }
    return 0;
}
