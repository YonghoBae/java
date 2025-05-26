import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class NQueen {
    static int[][] board;
    static int N, cnt;
    static int[] dr = { 1, 1, -1, -1, 0, 0, 1, -1 };
    static int[] dc = { 1, -1, -1, 1, 1, -1, 0, 0 };

    public static void main(String[] args) throws NumberFormatException, IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int T = Integer.parseInt(br.readLine());

        StringBuilder sb = new StringBuilder();
        for (int t = 1; t <= T; ++t) {
            N = Integer.parseInt(br.readLine());
            board = new int[N][N];
            cnt = 0;
            dfs(0, 0);
            sb.append("#").append(t).append(" ").append(cnt).append("\n");
        }
        System.out.println(sb.toString());
    }

    private static void dfs(int r, int depth) {
        if (depth == N) {
            cnt++;
            return;
        }

        for (int c = 0; c < N; ++c) {
            if (board[r][c] == 0) {
                saveQueen(r,c);
                dfs(r + 1, depth + 1);
                rollbackQueen(r,c);
            }
        }
    }

    private static void saveQueen(int r, int c) {
        board[r][c] = -1;
        for (int i = 0; i < 8; ++i) {
            int nr = r + dr[i];
            int nc = c + dc[i];

            while (nr >= 0 && nr < N && nc >= 0 && nc < N) {
                board[nr][nc]++;
                nr += dr[i];
                nc += dc[i];
            }
        }
    }

    private static void rollbackQueen(int r, int c) {
        board[r][c] = 0;
        for (int i = 0; i < 8; ++i) {
            int nr = r + dr[i];
            int nc = c + dc[i];

            while (nr >= 0 && nr < N && nc >= 0 && nc < N) {
                board[nr][nc]--;
                nr += dr[i];
                nc += dc[i];
            }
        }
    }
}