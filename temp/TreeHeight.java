import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class TreeHeight {
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int T = Integer.parseInt(in.readLine().trim());
        for (int t = 1; t <= T; ++t) {
            int N = Integer.parseInt(in.readLine().trim());
            int[] h = new int[N];
            int maxH = 0;

            StringTokenizer st = new StringTokenizer(in.readLine());
            for (int i = 0; i < N; ++i) {
                h[i] = Integer.parseInt(st.nextToken());
                if (h[i] > maxH) maxH = h[i]; // 최대 높이 찾기
            }

            int minDays = Integer.MAX_VALUE;

            // 목표 높이를 maxH, maxH + 1 두 가지 케이스 조사
            for (int target = maxH; target <= maxH + 1; target++) {
                int odd = 0, even = 0;

                for (int i = 0; i < N; i++) {
                    int diff = target - h[i];
                    even += diff / 2;
                    odd += diff % 2;
                }

                // 2를 1+1로 쪼개서 균형 맞추기 (while문이 직관적이라 유지)
                if (even > odd) {
                    while (even - odd > 1) {
                        even--;
                        odd += 2;
                    }
                }

                // 날짜 계산 공식
                int res = (odd > even) ? (odd * 2 - 1) : (even * 2);
                if (res < minDays) minDays = res;
            }

            sb.append("#").append(t).append(" ").append(minDays).append("\n");
        }
        System.out.print(sb);
    }
}