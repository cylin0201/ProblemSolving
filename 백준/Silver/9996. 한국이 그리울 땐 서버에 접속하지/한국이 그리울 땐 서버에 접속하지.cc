#include <bits/stdc++.h>
using namespace std;

int main() {
    int N; cin >> N;
    string pattern; cin >> pattern;

    int pos = pattern.find('*');
    string prefix = pattern.substr(0, pos);
    string postfix = pattern.substr(pos + 1);

    while (N--) {
        string s; cin >> s;

        //prefix + postfix 보다 짧으면 절대 불가능
        if (s.size() < prefix.size() + postfix.size()) {
            cout << "NE\n";
            continue;
        }

        // prefix 체크
        bool ok = true;
        for (int i = 0; i < prefix.size(); i++) {
            if (s[i] != prefix[i]) {
                ok = false;
                break;
            }
        }

        // postfix 체크
        for (int i = 0; i < postfix.size(); i++) {
            if (s[s.size() - postfix.size() + i] != postfix[i]) {
                ok = false;
                break;
            }
        }

        cout << (ok ? "DA\n" : "NE\n");
    }
}
