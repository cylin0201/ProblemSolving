#include <bits/stdc++.h>

using namespace std;

int X, Y, Z;

int getZ(int x, int y) {
    return (long long)y * 100 / x;
}

int main() {
    cin >> X >> Y;
    Z = getZ(X, Y);

    if (Z >= 99) {
        cout << -1 << '\n';
        return 0;
    }

    int s = 1, e = 1000000000, mid;
    while (s < e) {
        mid = (s + e) / 2;

        if (getZ(X + mid, Y + mid) > Z)
            e = mid;
        else
            s = mid + 1;
    }

    cout << s << '\n';
    return 0;
}
