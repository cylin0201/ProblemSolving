#include <bits/stdc++.h>
using namespace std;

struct Planet {
    int x, y, z;
};

struct Node {
    int a, b, d;

    bool operator< (const Node& node) const{
        return d < node.d;
    }
};

int N, result; 
vector<pair<int, Planet>> v;
vector<Node> cases;
vector<int> parent;

int getParent(int x) {
    if (x == parent[x]) return x;
    return parent[x] = getParent(parent[x]);
}

bool isUnion(int a, int b) {
    return getParent(a) == getParent(b);
}

void unionParent(int a, int b) {
    a = getParent(a); b = getParent(b);

    if (a > b) parent[a] = b;
    else parent[b] = a;
}

bool cmpX(const pair<int, Planet>& p1, const pair<int, Planet>& p2) {
    return p1.second.x < p2.second.x;
}

bool cmpY(const pair<int, Planet>& p1, const pair<int, Planet>& p2) {
    return p1.second.y < p2.second.y;
}

bool cmpZ(const pair<int, Planet>& p1, const pair<int, Planet>& p2) {
    return p1.second.z < p2.second.z;
}

int main() {
    cin >> N;
    for (int i = 1; i <= N; i++) {
        int x, y, z; cin >> x >> y >> z;
        v.push_back({ i, { x, y, z } });
    }
    parent.assign(N + 1, 0);
    for (int i = 1; i <= N; i++)
        parent[i] = i;

    //x좌표로 정렬
    sort(v.begin(), v.end(), cmpX);
    //간선 넣기
    for (int i = 0; i < v.size() - 1; i++)
        cases.push_back({ v[i].first, v[i + 1].first, abs(v[i].second.x - v[i + 1].second.x) });

    //y좌표로 정렬
    sort(v.begin(), v.end(), cmpY);
    for (int i = 0; i < v.size() - 1; i++)
        cases.push_back({ v[i].first, v[i + 1].first, abs(v[i].second.y - v[i + 1].second.y) });

    //z좌표로 정렬
    sort(v.begin(), v.end(), cmpZ);
    for (int i = 0; i < v.size() - 1; i++)
        cases.push_back({ v[i].first, v[i + 1].first, abs(v[i].second.z - v[i + 1].second.z) });

    sort(cases.begin(), cases.end());

    for (int i = 0; i < cases.size(); i++) {
        if (isUnion(cases[i].a, cases[i].b))
            continue;
        unionParent(cases[i].a, cases[i].b);
        result += cases[i].d;
    }

    cout << result << '\n';
    return 0;
}