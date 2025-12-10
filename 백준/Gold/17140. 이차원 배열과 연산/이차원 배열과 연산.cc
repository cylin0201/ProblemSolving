#include <bits/stdc++.h>
#define MAX 100  // 연산 범위는 문제 기준 최대 100까지
using namespace std;

struct MyPair {
    int num, cnt;

    bool operator<(const MyPair& p) const {
        if (cnt == p.cnt) return num < p.num;
        return cnt < p.cnt;
    }
};

int r, c, k, t;
vector<vector<int>> A(201, vector<int>(201, 0));

void reverseMatrix() {
    vector<vector<int>> temp(201, vector<int>(201, 0));

    for (int i = 1; i <= 100; i++)
        for (int j = 1; j <= 100; j++)
            temp[j][i] = A[i][j];

    A = temp;
}

void R() {
    vector<vector<int>> newA(201, vector<int>(201, 0));

    for (int i = 1; i <= 100; i++) {
        unordered_map<int, int> um;
        for (int j = 1; j <= 100; j++) {
            if (A[i][j] == 0) continue;
            um[A[i][j]]++;
        }

        vector<MyPair> v;
        for (auto& p : um)
            v.push_back({ p.first, p.second });

        sort(v.begin(), v.end());

        int idx = 1;
        for (auto& p : v) {
            if (idx > 100) break;
            newA[i][idx] = p.num; idx++;
            if (idx > 100) break;
            newA[i][idx] = p.cnt; idx++;
        }
    }
    A = newA;
}

void C() {
    reverseMatrix();
    R();
    reverseMatrix();
}

pair<int, int> getRowColLen() {
    int maxX = 0, maxY = 0;
    for (int i = 1; i <= 100; i++) {
        for (int j = 1; j <= 100; j++) {
            if (A[i][j] == 0) continue;
            maxX = max(maxX, i);
            maxY = max(maxY, j);
        }
    }
    return { maxX, maxY };
}

int main() {
    cin >> r >> c >> k;

    for (int i = 1; i <= 3; i++)
        for (int j = 1; j <= 3; j++)
            cin >> A[i][j];

    while (true) {
        if (A[r][c] == k) {
            cout << t << '\n';
            return 0;
        }

        auto [rowLen, colLen] = getRowColLen();

        if (rowLen >= colLen) R();
        else C();

        t++;

        if (t > 100) {
            cout << -1 << '\n';
            return 0;
        }
    }

    return 0;
}
