#include <bits/stdc++.h>
using namespace std;

int N;
vector<int> v;
int main() {
    cin >> N;
    v.assign(N, 0);

    for (int i = 0; i < N; i++)
        cin >> v[i];

    cout << *min_element(v.begin(), v.end()) << ' ' << *max_element(v.begin(), v.end()) << '\n';

    return 0;
}