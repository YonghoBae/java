import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Hamster {
    static int[] result;
    static int[] cages;
    static int N, X, M;
    static int[] L, R, S;
    static int maxSum;

    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(in.readLine());
        StringBuilder sb = new StringBuilder();

        for (int t = 1; t <= T; ++t) {
            StringTokenizer st = new StringTokenizer(in.readLine());
            N = Integer.parseInt(st.nextToken());
            X = Integer.parseInt(st.nextToken());
            M = Integer.parseInt(st.nextToken());

            cages = new int[N];
            result = null;
            maxSum = -1;

            L = new int[M];
            R = new int[M];
            S = new int[M];

            for (int j = 0; j < M; ++j) {
                st = new StringTokenizer(in.readLine());
                L[j] = Integer.parseInt(st.nextToken()) - 1;
                R[j] = Integer.parseInt(st.nextToken()) - 1;
                S[j] = Integer.parseInt(st.nextToken());
            }

            findCases(0);

            sb.append("#").append(t).append(" ");
            if (maxSum == -1) {
                sb.append("-1");
            } else {
                for (int i = 0; i < N; ++i) {
                    sb.append(result[i]).append(" ");
                }
            }
            sb.append("\n");
        }
        System.out.print(sb.toString());
    }

    public static void findCases(int cnt) {
        if (cnt == N) {
            if (checkCase()) {
                int currentSum = 0;
                for (int val : cages) currentSum += val;

                if (currentSum > maxSum) {
                    maxSum = currentSum;
                    result = cages.clone();
                }
            }
            return;
        }

        for (int i = 0; i <= X; ++i) {
            cages[cnt] = i;
            findCases(cnt + 1);
        }
    }

    public static boolean checkCase() {
        for (int i = 0; i < M; ++i) {
            int sum = 0;
            for (int j = L[i]; j <= R[i]; ++j) {
                sum += cages[j];
            }
            if (sum != S[i]) return false;
        }
        return true;
    }
}