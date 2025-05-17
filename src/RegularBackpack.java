//12865번 평범한 배낭
//방법은 유사하게 접근한거같은데 확신이 없었고 코딩을 실패.

import java.util.*;

public class RegularBackpack {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int n = sc.nextInt();  // 물건 수
        int k = sc.nextInt();  // 최대 무게

        int[] w = new int[n + 1];
        int[] v = new int[n + 1];

        for (int i = 1; i <= n; i++) {
            w[i] = sc.nextInt();
            v[i] = sc.nextInt();
        }

        int[] dp = new int[k + 1];

        for (int i = 1; i <= n; i++) {
            for (int j = k; j >= w[i]; j--) {
                dp[j] = Math.max(dp[j], dp[j - w[i]] + v[i]);
            }
        }

        System.out.println(dp[k]);
    }
}


//무게 w, 가치 v
//k만큼 무게 배낭
//배낭에 넣을 수 있는 최대 가치

//dp[k] = dp[k-1]+w[1];
//dp[k] = dp[k-w]+w