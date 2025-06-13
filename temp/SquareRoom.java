import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class SquareRoom {
    static int N, maxDist, startRoom;
    static int[][] A;
    static int[] dr = { -1, 1, 0, 0 };
    static int[] dc = { 0, 0, -1, 1 };

    public static void main(String[] args) throws NumberFormatException, IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int TC = Integer.parseInt(in.readLine());

        for (int t = 1; t <= TC; t++) {
            N = Integer.parseInt(in.readLine());
            A = new int[N][N];
            for (int i = 0; i < N; ++i) {
                StringTokenizer st = new StringTokenizer(in.readLine());
                for (int j = 0; j < N; ++j) {
                    A[i][j] = Integer.parseInt(st.nextToken());
                }
            }

            maxDist = 0;
            startRoom = Integer.MAX_VALUE;

            for (int i = 0; i < N; ++i) {
                for (int j = 0; j < N; ++j) {
                    dfs(1, i, j, A[i][j]);
                }
            }

            sb.append("#").append(t).append(" ").append(startRoom).append(" ").append(maxDist).append("\n");
        }
        System.out.println(sb.toString());
    }

    private static void dfs(int depth, int r, int c, int firstNum) {
        if (depth > maxDist) {
            maxDist = depth;
            startRoom = firstNum;
        }
        else if (depth == maxDist) {
            startRoom = Math.min(startRoom, firstNum);
        }

        for (int i = 0; i < 4; ++i) {
            int nr = r + dr[i];
            int nc = c + dc[i];

            if (nr < 0 || nr >= N || nc < 0 || nc >= N) continue;

            if (A[nr][nc] == A[r][c] + 1) {
                dfs(depth + 1, nr, nc, firstNum);
                break;
            }
        }
    }
}