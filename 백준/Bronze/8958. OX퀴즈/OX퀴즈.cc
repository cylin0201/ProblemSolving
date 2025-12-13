#include <bits/stdc++.h>
using namespace std;

int getPoint(const string& str) {
    int con = 1, result = 0;
    for (int i = 0; i < str.size(); i++) {
        if (str[i] == 'O') {
            result += con;
            con++;
        }
        else con = 1;
    }
    return result;
}

int main() {
    int N; cin >> N;
    string str;
    for (int i = 0; i < N; i++) {
        cin >> str;
        cout << getPoint(str) << '\n';
    }

    return 0;
}