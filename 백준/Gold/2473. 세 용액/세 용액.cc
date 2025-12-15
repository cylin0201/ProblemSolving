#include <bits/stdc++.h>
using namespace std;

int N;
long long minSum = LLONG_MAX;
int x, y, z;
vector<long long> v;

int main() {
    cin >> N;
    v.resize(N);
    for (int i = 0; i < N; i++) cin >> v[i];

    sort(v.begin(), v.end());

    for (int i = 0; i < N - 2; i++) {
        int s = i + 1;
        int e = N - 1;

        while (s < e) {
            long long sum = v[i] + v[s] + v[e];

            if (llabs(sum) < minSum) {
                minSum = llabs(sum);
                x = v[i];
                y = v[s];
                z = v[e];
            }

            if (sum > 0) e--;
            else s++;
        }
    }

    cout << x << ' ' << y << ' ' << z << '\n';
    return 0;
}
