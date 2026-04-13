import java.util.*;
import java.io.*;

class Main{
    static int N;
    static String[] arr;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        arr = new String[N];

        for (int i = 0 ; i < N; i++){
            arr[i] = br.readLine();
        }

        Arrays.sort(arr, (o1, o2) -> {
            int i = 0, j = 0;

            while (i < o1.length() && j < o2.length()) {
                char c1 = o1.charAt(i);
                char c2 = o2.charAt(j);

                if (Character.isDigit(c1) && Character.isDigit(c2)) {
                    int s1 = i;
                    while (s1 < o1.length() && o1.charAt(s1) == '0') s1++;

                    int s2 = j;
                    while (s2 < o2.length() && o2.charAt(s2) == '0') s2++;

                    int e1 = s1;
                    while (e1 < o1.length() && Character.isDigit(o1.charAt(e1))) e1++;

                    int e2 = s2;
                    while (e2 < o2.length() && Character.isDigit(o2.charAt(e2))) e2++;

                    int len1 = e1 - s1;
                    int len2 = e2 - s2;

                    if (len1 != len2) return len1 - len2;

                    for (int k = 0; k < len1; k++) {
                        if (o1.charAt(s1 + k) != o2.charAt(s2 + k))
                            return o1.charAt(s1 + k) - o2.charAt(s2 + k);
                    }

                    int zero1 = s1 - i;
                    int zero2 = s2 - j;
                    if (zero1 != zero2) return zero1 - zero2;

                    i = e1;
                    j = e2;
                }
                else {
                    if (Character.isDigit(c1)) return -1;
                    if (Character.isDigit(c2)) return 1;

                    if (Character.toLowerCase(c1) != Character.toLowerCase(c2)) {
                        return Character.toLowerCase(c1) - Character.toLowerCase(c2);
                    }

                    if (c1 != c2) return c1 - c2;

                    i++; j++;
                }
            }

            return o1.length() - o2.length();
        });

        for (int i = 0 ; i < N; i++)
            System.out.println(arr[i]);
    }
}