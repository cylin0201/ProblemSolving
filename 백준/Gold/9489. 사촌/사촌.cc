#include <bits/stdc++.h>
using namespace std;

int main() {
    while (true) {
        int N, M; cin >> N >> M;
        if (N == 0 && M == 0) break;

        vector<int> v(N);
        for (int i = 0; i < N; i++) cin >> v[i];

        vector<int> parent(N, -1), depth(N, 0);

        // BFS용 큐 (부모 후보들)
        queue<int> q;
        q.push(0);            // root
        parent[0] = -1;
        depth[0] = 0;

        int idx = 1;          // 현재 처리 중인 노드 인덱스

        while (idx < N) {
            int curParent = q.front();
            q.pop();

            // curParent의 자식들 처리
            q.push(idx);
            parent[idx] = curParent;
            depth[idx] = depth[curParent] + 1;
            idx++;

            // 형제 구간 처리 (연속된 값)
            while (idx < N && v[idx] - v[idx - 1] == 1) {
                q.push(idx);
                parent[idx] = curParent;
                depth[idx] = depth[curParent] + 1;
                idx++;
            }
        }

        // M의 인덱스 찾기
        int mIdx = -1;
        for (int i = 0; i < N; i++) {
            if (v[i] == M) {
                mIdx = i;
                break;
            }
        }

        // 루트이거나 조부모가 없으면 사촌 없음
        if (mIdx == 0 || parent[mIdx] == -1 || parent[parent[mIdx]] == -1) {
            cout << 0 << '\n';
            continue;
        }

        int mParent = parent[mIdx];
        int mGrandParent = parent[mParent];
        int mDepth = depth[mIdx];

        int cnt = 0;
        for (int i = 0; i < N; i++) {
            if (i == mIdx) continue;
            if (depth[i] == mDepth &&
                parent[i] != mParent &&
                parent[i] != -1 &&
                parent[parent[i]] == mGrandParent) {
                cnt++;
            }
        }

        cout << cnt << '\n';
    }

    return 0;
}
