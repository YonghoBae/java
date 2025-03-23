import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class CounterGame {
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(in.readLine());
        StringBuilder sb = new StringBuilder();

        for (int t = 0; t < T; t++) {
            StringTokenizer st = new StringTokenizer(in.readLine());
            long N = Long.parseLong(st.nextToken());
            long M = Long.parseLong(st.nextToken());
            long K = Long.parseLong(st.nextToken());

            // 1. 오답으로 최대한 보호할 수 있는 정답 수 (Safe)
            long G = N - M;
            long safeCapacity = G * (K - 1);

            // 2. 어쩔 수 없이 K개 연속 정답이 되어 더블링이 발생하는 정답 수
            long M_rem = M - safeCapacity;

            long totalScore = 0;

            if (M_rem < K) {
                // 더블링이 한 번도 발생하지 않는 경우
                totalScore = M;
            } else {
                // 3. 더블링 구간을 맨 앞에 배치하여 점수 뻥튀기 최소화
                long q = M_rem / K; // 2배 연산 횟수
                long r = M_rem % K; // 더블링 블록에 못 들어간 나머지

                long score = 0;
                for (int i = 0; i < q; i++) {
                    // (이전 점수 + K개 정답) * 2
                    score = (score + K) * 2;
                }

                // 4. (더블링 된 점수) + (더블링 안 된 나머지들) + (오답 사이사이의 안전한 점수들)
                totalScore = score + r + safeCapacity;
            }
            sb.append(totalScore).append("\n");
        }
        System.out.print(sb.toString());
    }
}