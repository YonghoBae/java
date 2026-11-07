package implementation;

// [Bronze II] 분해합 - 2231
// https://www.acmicpc.net/problem/2231
// 분류: 브루트포스 알고리즘
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class B2231 {
    static int N;
    public static void main(String[] args) throws IOException {
        BufferedReader br= new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());

        int minN = 0;
        for(int n=N-1;n>=1;--n){
            int sum = n;
            String nText = String.valueOf(n);
            int size = nText.length();

            for(int i=0;i<size;++i){
                sum+= nText.charAt(i)-'0';
            }

            if(sum==N) minN = n;
        }

        System.out.println(minN);
    }
}
