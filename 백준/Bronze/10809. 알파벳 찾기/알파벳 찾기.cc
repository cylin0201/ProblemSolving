#include <bits/stdc++.h>
using namespace std;

int main() {
    vector<int> arr(26, -1);
    string str; cin >> str;

    for (int i = 0; i < str.size(); i++) {
        if (arr[str[i] - 'a'] != -1) continue;
        arr[str[i] - 'a'] = i;
    }

    for (int i = 0; i < arr.size(); i++)
        cout << arr[i] << ' ';

    return 0;
}