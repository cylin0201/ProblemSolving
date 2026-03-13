import java.util.*;
import java.io.*;

public class Main{

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        char[] charArr = br.readLine().toCharArray();
        int[] arr = new int[charArr.length];

        for (int i = 0 ; i < charArr.length; i++){
            arr[i] = charArr[i] - '0';
        }

        Arrays.sort(arr);

        for (int i = arr.length - 1; i >= 0; i--)
            System.out.print(arr[i]);
    }
}