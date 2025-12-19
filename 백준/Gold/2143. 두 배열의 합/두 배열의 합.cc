#include <bits/stdc++.h>
using namespace std;

long long T, N, M, res;
vector<long long> A, B;
unordered_map<long long, long long> Sb;
vector<long long> Sa;

int main() {
    cin >> T;
    cin >> N;
    A.assign(N + 1, 0);
    for (long long i = 1; i <= N; i++)
        cin >> A[i];
    
    for (long long i = 1; i <= N; i++) {
        long long sum = A[i];
        Sa.push_back(sum);
        for (long long j = i + 1; j <= N; j++) {
            sum += A[j];
            Sa.push_back(sum);
        }
    }

    cin >> M;
    B.assign(M + 1, 0);
    for (long long i = 1; i <= M; i++)
        cin >> B[i];

    for (long long i = 1; i <= M; i++) {
        long long sum = B[i];
        Sb[sum]++;
        for (long long j = i + 1; j <= M; j++) {
            sum += B[j];
            Sb[sum]++;
        }
    }
    
    for (long long i = 0; i < Sa.size(); i++) {
        long long tar = T - Sa[i];
        res += Sb[tar];
    }

    cout << res << '\n';

    return 0;
}