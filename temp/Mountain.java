import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Mountain { // 클래스명 Solution 준수
    static StringTokenizer st;
    static BufferedReader in;

    public static void main(String[] args) throws IOException {
        in = new BufferedReader(new InputStreamReader(System.in));

        // T 읽기: StringTokenizer를 통해 첫 번째 토큰을 가져옴
        int T = Integer.parseInt(next());

        StringBuilder sb = new StringBuilder();
        for (int t = 1; t <= T; ++t) {
            // N 읽기: N이 같은 줄에 있든 다음 줄에 있든 안전하게 가져옴
            int N = Integer.parseInt(next());

            int[] h = new int[N];
            for (int i = 0; i < N; ++i) {
                h[i] = Integer.parseInt(next());
            }

            int totalCnt = 0; // 누적 합은 long 권장
            for (int i = 1; i < N - 1; ++i) {
                // 봉우리 확인
                if (h[i] > h[i - 1] && h[i] > h[i + 1]) {
                    int left = i - 1;
                    int right = i + 1;
                    int leftCnt = 1;  // 이미 h[i] > h[i-1] 확인했으므로 1
                    int rightCnt = 1; // 이미 h[i] > h[i+1] 확인했으므로 1

                    // 왼쪽 확인: 봉우리에서 왼쪽으로 내려가며 연속성 체크
                    while (left > 0 && h[left] > h[left - 1]) {
                        leftCnt++;
                        left--;
                    }

                    // 오른쪽 확인: 봉우리에서 오른쪽으로 내려가며 연속성 체크
                    while (right < N - 1 && h[right] > h[right + 1]) {
                        rightCnt++;
                        right++;
                    }

                    totalCnt += leftCnt * rightCnt;
                }
            }
            sb.append("#").append(t).append(" ").append(totalCnt).append("\n");
        }
        System.out.print(sb.toString());
    }

    // [중요] 기존 StringTokenizer를 재사용하여 줄바꿈과 공백을 무시하는 메서드
    static String next() throws IOException {
        while (st == null || !st.hasMoreTokens()) {
            String line = in.readLine();
            if (line == null) return null;
            st = new StringTokenizer(line);
        }
        return st.nextToken();
    }
}