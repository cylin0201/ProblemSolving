#include <bits/stdc++.h>
using namespace std;

int main() {
    unordered_map <int, int> um;
    int input;
    for (int i = 0; i < 10; i++) {
        cin >> input;
        um[input % 42]++;
    }
    cout << um.size() << '\n';

    return 0;
}