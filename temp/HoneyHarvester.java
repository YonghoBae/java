import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class HoneyHarvester {
    static int[][] hives;
    static boolean[][] selectedHives;
    static int N, M, C;
    static int maxSum, subMax;
    static List<Integer> numbers;

    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        int T_cases = Integer.parseInt(in.readLine());
        StringBuilder sb = new StringBuilder();

        for (int t = 1; t <= T_cases; ++t) {
            StringTokenizer st = new StringTokenizer(in.readLine());
            N = Integer.parseInt(st.nextToken());
            M = Integer.parseInt(st.nextToken());
            C = Integer.parseInt(st.nextToken());

            hives = new int[N][N];
            selectedHives = new boolean[N][N];
            for (int i = 0; i < N; ++i) {
                st = new StringTokenizer(in.readLine());
                for (int j = 0; j < N; ++j) {
                    hives[i][j] = Integer.parseInt(st.nextToken());
                }
            }

            maxSum = 0;
            findBest(0, 0, 0);
            sb.append("#").append(t).append(" ").append(maxSum).append("\n");
        }
        System.out.print(sb.toString());
    }

    static void findBest(int depth, int startR, int startC) {
        if (depth == 2) {
            int totalSum = 0;
            for (int i = 0; i < N; ++i) {
                for (int j = 0; j <= N - M; ++j) {
                    if (selectedHives[i][j]) {
                        numbers = new ArrayList<>();
                        for (int k = j; k < j + M; ++k) {
                            numbers.add(hives[i][k]);
                        }
                        subMax = 0;
                        findMaxBenefitCombination(0, 0, 0, 0);
                        totalSum += subMax;
                    }
                }
            }
            maxSum = Math.max(maxSum, totalSum);
        } else {
            for (int i = startR; i < N; ++i) {
                int jStart = (i == startR) ? startC : 0;
                for (int j = jStart; j <= N - M; ++j) {
                    selectedHives[i][j] = true;
                    if (j + M > N - M) {
                        findBest(depth + 1, i + 1, 0);
                    } else {
                        findBest(depth + 1, i, j + M);
                    }
                    selectedHives[i][j] = false;
                }
            }
        }
    }

    static void findMaxBenefitCombination(int idx, int start, int currentSum, int currentPowSum) {
        if (currentSum > C) return;

        if (subMax < currentPowSum) {
            subMax = currentPowSum;
        }

        if (idx == M) return;

        for (int i = start; i < M; ++i) {
            int val = numbers.get(i);
            findMaxBenefitCombination(idx + 1, i + 1, currentSum + val, currentPowSum + (val * val));
        }
    }
}