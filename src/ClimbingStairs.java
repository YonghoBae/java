import java.util.Scanner;

public class ClimbingStairs {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int[] stairs = new int[n];
        int[] dp = new int[301];

        for(int i=0;i<n;++i){
            stairs[i] = sc.nextInt();
        }

        if (n == 1) {
            System.out.println(stairs[0]);
            return;
        }

        dp[0] = stairs[0];
        dp[1] = stairs[0]+stairs[1];
        if (n > 2) {
            dp[2] = Math.max(stairs[0] + stairs[2], stairs[1] + stairs[2]);
        }


        for(int i=3;i<n;++i){
            dp[i] = Math.max(dp[i-2]+stairs[i],dp[i-3]+stairs[i]+stairs[i-1]);
        }

        System.out.println(dp[n-1]);
    }
}

//dp[n] = dp[n-3]+score[n]+score[n-1]

//dp[n] = dp[n-2]+score[n]
