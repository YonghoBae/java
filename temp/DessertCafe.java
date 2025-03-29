import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class DessertCafe {
    static int N, maxDessert;
    static int[][] grid;
    static boolean[] visited;

    static int[] dr = {1, 1, -1, -1};
    static int[] dc = {1, -1, -1, 1};

    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(in.readLine());

        StringBuilder sb= new StringBuilder();
        for (int t = 1; t <= T; ++t) {
            N = Integer.parseInt(in.readLine());
            grid = new int[N][N];
            maxDessert = -1;

            for (int i = 0; i < N; ++i) {
                StringTokenizer st = new StringTokenizer(in.readLine());
                for (int j = 0; j < N; ++j) {
                    grid[i][j] = Integer.parseInt(st.nextToken());
                }
            }

            for (int r = 0; r < N - 2; r++) {
                for (int c = 1; c < N - 1; c++) {

                    for (int w1 = 1; w1 < N; w1++) {
                        for (int w2 = 1; w2 < N; w2++) {

                            if (r + w1 + w2 < N && c - w2 >= 0 && c + w1 < N) {
                                simulate(r, c, w1, w2);
                            }
                        }
                    }
                }
            }

            sb.append("#").append(t).append(" ").append(maxDessert).append("\n");
        }
        System.out.println(sb.toString());
    }

    static void simulate(int r, int c, int w1, int w2) {
        visited = new boolean[101];
        int count = 0;
        int curR = r;
        int curC = c;

        int[] moves = {w1, w2, w1, w2};

        for (int d = 0; d < 4; d++) {
            for (int step = 0; step < moves[d]; step++) {
                curR += dr[d];
                curC += dc[d];

                int dessert = grid[curR][curC];

                if (visited[dessert]) return;

                visited[dessert] = true;
                count++;
            }
        }

        // 모든 경로에 중복이 없었다면 최댓값 갱신
        maxDessert = Math.max(maxDessert, count);
    }
}