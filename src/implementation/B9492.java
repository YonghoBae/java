package implementation;

// [Silver V] Perfect Shuffle - 9492
// https://www.acmicpc.net/problem/9492
// 분류: 수학, 구현, 문자열, 사칙연산, 홀짝성
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.StringTokenizer;

public class B9492 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st = null;

        while (true) {
            String line = br.readLine();
            if (line == null || line.trim().isEmpty()) continue;
            
            int N = Integer.parseInt(line.trim());
            if (N == 0) break; // 0 입력 시 종료

            Deque<String> deque1 = new ArrayDeque<>();
            Deque<String> deque2 = new ArrayDeque<>();

            // N개의 단어를 읽어서 두 덱에 분할 저장
            for (int n = 0; n < N; n++) {
                while (st == null || !st.hasMoreTokens()) {
                    String nextLine = br.readLine();
                    if (nextLine == null) break;
                    st = new StringTokenizer(nextLine);
                }
                
                if (n <= (N - 1) / 2) {
                    deque1.addLast(st.nextToken());
                } else {
                    deque2.addLast(st.nextToken());
                }
            }

            // 셔플 결과 빌드: 각 단어 뒤에 줄바꿈(\n)만 추가
            for (int n = 0; n < N; n++) {
                if (n % 2 == 0) {
                    sb.append(deque1.pollFirst()).append("\n");
                } else {
                    sb.append(deque2.pollFirst()).append("\n");
                }
            }
            // 기존에 있던 sb.append("\n") 제거 (빈 줄 방지)
        }

        // 최종 결과 출력 (마지막 불필요한 공백 제거 후 출력 가능)
        System.out.print(sb.toString().trim());
    }
}