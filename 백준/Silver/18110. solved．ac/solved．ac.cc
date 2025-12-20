#include <bits/stdc++.h>
using namespace std;

int n;
vector<int> v;

int main() {
    cin >> n;
    v.resize(n);

    for (int i = 0; i < n; i++)
        cin >> v[i];

    if (n == 0) {
        cout << 0 << '\n';
        return 0;
    }

    sort(v.begin(), v.end());

    int temp = (int)(n * 0.15 + 0.5);

    long long sum = 0;
    for (int i = temp; i < n - temp; i++)
        sum += v[i];

    cout << (int)((double)sum / (n - 2 * temp) + 0.5) << '\n';
    return 0;
}
