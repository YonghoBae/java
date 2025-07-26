import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class FineDust {
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(in.readLine());
        int R = Integer.parseInt(st.nextToken());
        int C = Integer.parseInt(st.nextToken());
        int T = Integer.parseInt(st.nextToken());

        int[][] A = new int[R][C];
        int[][] add = new int[R][C];
        int[] air = new int[2]; // 수정: 배열 크기 할당
        int index = 0;

        for (int i = 0; i < R; ++i) {
            st = new StringTokenizer(in.readLine());
            for (int j = 0; j < C; ++j) {
                A[i][j] = Integer.parseInt(st.nextToken());
                if (A[i][j] == -1) {
                    // 공기청정기는 항상 1열에 두 칸 차지하므로 행 값만 저장
                    if (j == 0) {
                        if (index < 2 && (index == 0 || air[index-1] != i)) {
                            air[index++] = i;
                        }
                    }
                }
            }
        }

        int[] dr = {-1, 0, 1, 0};
        int[] dc = {0, 1, 0, -1};

        for (int t = 0; t < T; ++t) {
            // 1. 먼지 확산 전 add 배열 초기화
            for (int i = 0; i < R; ++i) {
                Arrays.fill(add[i], 0);
            }

            // 먼지 확산
            for (int r = 0; r < R; ++r) {
                for (int c = 0; c < C; ++c) {
                    if (A[r][c] > 0) {
                        int cnt = 0;
                        for (int d = 0; d < 4; ++d) {
                            int nr = r + dr[d];
                            int nc = c + dc[d];
                            if (nr < 0 || nr >= R || nc < 0 || nc >= C) continue;
                            if (A[nr][nc] == -1) continue;
                            cnt++;
                            add[nr][nc] += A[r][c] / 5;
                        }
                        A[r][c] -= (A[r][c] / 5) * cnt;
                    }
                }
            }

            // 확산 결과 합치기
            for (int r = 0; r < R; ++r) {
                for (int c = 0; c < C; ++c) {
                    A[r][c] += add[r][c];
                }
            }

            // 2. 공기청정기 작동
            // 위쪽 (반시계) - 데이터를 당겨오는 순서로 구현
            int top = air[0];
            for (int i = top - 1; i > 0; i--) A[i][0] = A[i - 1][0]; // 아래로
            for (int i = 0; i < C - 1; i++) A[0][i] = A[0][i + 1]; // 왼쪽으로
            for (int i = 0; i < top; i++) A[i][C - 1] = A[i + 1][C - 1]; // 위로
            for (int i = C - 1; i > 1; i--) A[top][i] = A[top][i - 1]; // 오른쪽으로
            A[top][1] = 0; // 공기청정기에서 나가는 공기는 0

            // 아래쪽 (시계)
            int bottom = air[1];
            for (int i = bottom + 1; i < R - 1; i++) A[i][0] = A[i + 1][0]; // 위로
            for (int i = 0; i < C - 1; i++) A[R - 1][i] = A[R - 1][i + 1]; // 왼쪽으로
            for (int i = R - 1; i > bottom; i--) A[i][C - 1] = A[i - 1][C - 1]; // 아래로
            for (int i = C - 1; i > 1; i--) A[bottom][i] = A[bottom][i - 1]; // 오른쪽으로
            A[bottom][1] = 0;
        }

        // 3. 결과 출력 (남은 먼지 합계)
        int ans = 0;
        for (int i = 0; i < R; i++) {
            for (int j = 0; j < C; j++) {
                if (A[i][j] > 0) ans += A[i][j];
            }
        }
        System.out.println(ans);
    }
}