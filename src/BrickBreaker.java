import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BrickBreaker {
    static int N, W, H, minBricks;
    static int[] dr = {-1, 1, 0, 0};
    static int[] dc = {0, 0, -1, 1};

    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(in.readLine());

        for (int t = 1; t <= T; ++t) {
            StringTokenizer st = new StringTokenizer(in.readLine());
            N = Integer.parseInt(st.nextToken());
            W = Integer.parseInt(st.nextToken());
            H = Integer.parseInt(st.nextToken());

            int[][] grid = new int[H][W];
            for (int i = 0; i < H; ++i) {
                st = new StringTokenizer(in.readLine());
                for (int j = 0; j < W; ++j) {
                    grid[i][j] = Integer.parseInt(st.nextToken());
                }
            }

            minBricks = Integer.MAX_VALUE;
            select(0, grid);
            System.out.println("#" + t + " " + minBricks);
        }
    }

    // 1. DFS: N번 공 던지기 모든 경우의 수
    public static void select(int depth, int[][] currentGrid) {
        if (depth == N) {
            minBricks = Math.min(minBricks, countBricks(currentGrid));
            return;
        }

        for (int i = 0; i < W; ++i) {
            // 현재 상태 복사 (깊은 복사)
            int[][] nextGrid = copyGrid(currentGrid);

            // 위에서부터 첫 벽돌 찾기
            int targetR = -1;
            for (int j = 0; j < H; ++j) {
                if (nextGrid[j][i] > 0) {
                    targetR = j;
                    break;
                }
            }

            if (targetR != -1) {
                // 터뜨리기 (방문 체크 대신 바로 0으로 만드는 방식이 편함)
                isBreak(targetR, i, nextGrid);
                // 중력 적용
                excuteBreak(nextGrid);
                // 다음 공 던지기
                select(depth + 1, nextGrid);
            } else {
                // 해당 열에 벽돌이 없으면 그냥 다음 단계로
                select(depth + 1, nextGrid);
            }
        }
    }

    // 2. 재귀적 연쇄 폭발
    public static void isBreak(int r, int c, int[][] map) {
        int size = map[r][c];
        map[r][c] = 0; // 자기 자신 터뜨림

        if (size == 1) return;

        for (int d = 0; d < 4; ++d) {
            for (int s = 1; s < size; ++s) { // 1부터 size-1 거리까지
                int nr = r + dr[d] * s;
                int nc = c + dc[d] * s;

                if (nr >= 0 && nr < H && nc >= 0 && nc < W && map[nr][nc] > 0) {
                    isBreak(nr, nc, map);
                }
            }
        }
    }

    // 3. 중력 (벽돌 내리기)
    public static void excuteBreak(int[][] map) {
        for (int j = 0; j < W; ++j) {
            int targetRow = H - 1;
            for (int i = H - 1; i >= 0; --i) {
                if (map[i][j] > 0) {
                    int temp = map[i][j];
                    map[i][j] = 0;
                    map[targetRow--][j] = temp;
                }
            }
        }
    }

    // 보조 함수들
    public static int[][] copyGrid(int[][] origin) {
        int[][] res = new int[H][W];
        for (int i = 0; i < H; ++i) res[i] = origin[i].clone();
        return res;
    }

    public static int countBricks(int[][] map) {
        int res = 0;
        for (int i = 0; i < H; ++i) {
            for (int j = 0; j < W; ++j) {
                if (map[i][j] > 0) res++;
            }
        }
        return res;
    }
}