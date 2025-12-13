#include <bits/stdc++.h>
using namespace std;

int result;
int arr[10];

int main() {
    int A, B, C; cin >> A >> B >> C;
    string str = to_string(A * B * C);

    for (int i = 0; i < str.size(); i++)
        arr[str[i] - '0']++;

    for (int i = 0; i < 10; i++)
        cout << arr[i] << '\n';

    return 0;
}