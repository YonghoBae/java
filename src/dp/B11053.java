package dp;

// [Silver II] 가장 긴 증가하는 부분 수열 - 11053
// https://www.acmicpc.net/problem/11053
// 분류: 다이나믹 프로그래밍, 가장 긴 증가하는 부분 수열 문제
import java.util.Scanner;

public class B11053 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();

        int[] a = new int[n];
        int[] dp = new int[n];

        for (int i = 0; i < n; i++) {
            a[i] = sc.nextInt();
            dp[i] = 1; // 최소는 자기 자신만 포함한 수열
        }

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < i; j++) {
                if (a[j] < a[i]) {
                    dp[i] = Math.max(dp[i], dp[j] + 1);
                }
            }
        }

        int max = 0;
        for (int i = 0; i < n; i++) {
            max = Math.max(max, dp[i]);
        }

        System.out.println(max);
    }
}
