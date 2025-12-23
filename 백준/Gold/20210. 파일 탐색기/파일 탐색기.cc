#include <bits/stdc++.h>
using namespace std;

int N;
vector<string> v;
unordered_map<char, int> um;

bool isAlpha(char c) {
    if (('a' <= c && c <= 'z') || ('A' <= c && c <= 'Z'))
        return true;
    return false;
}

bool cmp(const string& s1, const string& s2) {
    int i = 0, n1 = s1.size(), n2 = s2.size();

    while (i < n1 && i < n2) {
        char c1 = s1[i], c2 = s2[i];

        // 알파벳 vs 알파벳
        if (isAlpha(c1) && isAlpha(c2)) {
            if (um[c1] != um[c2])
                return um[c1] < um[c2];
            i++;
        }
        // 숫자 vs 알파벳
        else if (!isAlpha(c1) && isAlpha(c2))
            return true;
        else if (isAlpha(c1) && !isAlpha(c2))
            return false;

        // 숫자 vs 숫자
        else {
            int idx1 = i, idx2 = i;
            int z1 = 0, z2 = 0;

            while (idx1 < n1 && s1[idx1] == '0') { z1++; idx1++; }
            while (idx1 < n1 && isdigit(s1[idx1])) idx1++;

            while (idx2 < n2 && s2[idx2] == '0') { z2++; idx2++; }
            while (idx2 < n2 && isdigit(s2[idx2])) idx2++;

            int len1 = idx1 - i - z1;
            int len2 = idx2 - i - z2;

            if (len1 != len2)
                return len1 < len2;

            // 문자열 생성 없이 직접 비교
            for (int k = 0; k < len1; k++) {
                char a = s1[i + z1 + k];
                char b = s2[i + z2 + k];
                if (a != b)
                    return a < b;
            }

            if (z1 != z2)
                return z1 < z2;

            i = max(idx1, idx2);
        }
    }
    return n1 < n2;
}

int main() {
    cin.tie(nullptr)->sync_with_stdio(false);

    cin >> N;
    v.assign(N, "");

    for (int i = 0; i < N; i++)
        cin >> v[i];
    
    int idx = 1;
    for (char c = 'A'; c <= 'Z'; c++) {
        um[c] += idx;
        idx += 2;
    }
    idx = 2;
    for (char c = 'a'; c <= 'z'; c++) {
        um[c] += idx;
        idx += 2;
    }
    
    sort(v.begin(), v.end(), cmp);

    for (auto elem : v)
        cout << elem << '\n';
    return 0;
}