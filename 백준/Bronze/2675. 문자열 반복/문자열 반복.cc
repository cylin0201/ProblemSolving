#include <bits/stdc++.h>
using namespace std;

int main() {
    int N; cin >> N;
    for (int i = 0; i < N; i++) {
        int a; string str;
        cin >> a >> str;
        for (char c : str) {
            for (int i = 0; i < a; i++)
                cout << c;
        }
        cout << '\n';
    }

}