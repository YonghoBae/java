import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class AppleEatGame {
    // 0:동, 1:남, 2:서, 3:북 (시계방향)
    static int[][] rotate = {
            {1, 2, 3, 3}, // 현재 동(0)일 때 각 사분면(Q0, Q1, Q2, Q3)으로 가기 위한 회전수
            {3, 1, 2, 3}, // 현재 남(1)일 때
            {3, 3, 1, 2}, // 현재 서(2)일 때
            {2, 3, 3, 1}  // 현재 북(3)일 때
    };

    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

        int T = Integer.parseInt(in.readLine());
        for (int t = 1; t <= T; ++t) {
            int N = Integer.parseInt(in.readLine());

            List<int[]> apples = new ArrayList<>();

            for (int i = 0; i < N; ++i) {
                String line = in.readLine();
                for (int j = 0; j < N; ++j) {
                    int input = line.charAt(j) - '0';
                    if (input > 0) {
                        // i: 행, j: 열, input: 사과 번호
                        apples.add(new int[]{i, j, input});
                    }
                }
            }

            // 사과 번호 순서대로 정렬
            apples.sort((a1, a2) -> a1[2] - a2[2]);

            int currR = 0; // 시작점 (1,1) -> 0인덱스 기준 (0,0)
            int currC = 0;
            int currD = 0; // 초기 방향: 동쪽(0)

            int cnt = 0;
            for (int[] apple : apples) {
                int turn = 0;

                // 사분면 판단 및 회전수 결정
                if (currR < apple[0] && currC < apple[1]) {
                    // 오른쪽 아래 (South & East 필요)
                    turn = rotate[currD][0];
                } else if (currR < apple[0] && currC > apple[1]) {
                    // 왼쪽 아래 (South & West 필요)
                    turn = rotate[currD][1];
                } else if (currR > apple[0] && currC > apple[1]) {
                    // 왼쪽 위 (North & West 필요)
                    turn = rotate[currD][2];
                } else if (currR > apple[0] && currC < apple[1]) {
                    // 오른쪽 위 (North & East 필요)
                    turn = rotate[currD][3];
                }

                cnt += turn;
                // [핵심 수정] 방향 갱신은 고정값이 아니라 내가 회전한 turn만큼 더해야 함!
                currD = (currD + turn) % 4;

                // 위치 갱신
                currR = apple[0];
                currC = apple[1];
            }
            System.out.println(cnt);
        }
    }
}