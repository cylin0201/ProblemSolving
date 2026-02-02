#include <bits/stdc++.h>
using namespace std;

int main() {
    int N;
    cin >> N;

    int num = 666;
    int cnt = 0;

    while (true) {
        string s = to_string(num);

        if (s.find("666") != string::npos) {
            cnt++;
            if (cnt == N) {
                cout << num << '\n';
                break;
            }
        }
        num++;
    }
}
