import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class HomeSecureService {
    static int[][] grid;
    static int[][] s;
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

        int T = Integer.parseInt(in.readLine());

        StringTokenizer st;
        StringBuilder sb = new StringBuilder();
        for(int t=1;t<=T;++t){
            st = new StringTokenizer(in.readLine());
            int N = Integer.parseInt(st.nextToken());
            int M = Integer.parseInt(st.nextToken());

            grid =  new int[N][N];
            s = new int[N][N];
            for(int i=0; i<N; ++i){
                st = new StringTokenizer(in.readLine());
                for(int j=0; j<N; ++j){
                    grid[i][j] = Integer.parseInt(st.nextToken());
                    // 줄 단위(Row-wise) 누적합
                    if(j == 0) s[i][j] = grid[i][j];
                    else s[i][j] = s[i][j-1] + grid[i][j];
                }
            }

            int maxHouses = 0;
            for (int K = 1; K <= N + 1; K++) {
                int cost = K * K + (K - 1) * (K - 1);

                for (int r = 0; r < N; r++) {
                    for (int c = 0; c < N; c++) {
                        int houseCount = calcProfit(K, r, c, N);

                        if (houseCount * M >= cost) {
                            maxHouses = Math.max(maxHouses, houseCount);
                        }
                    }
                }
            }

            sb.append("#").append(t).append(" ").append(maxHouses).append("\n");
        }
        System.out.println(sb.toString());
    }

    public static int calcProfit(int K, int r, int c, int N) {
        int count = 0;

        for(int i = -(K-1); i <= K-1; ++i){
            int currR = r + i;

            if(currR < 0 || currR >= N) continue;

            int spread = (K-1) - Math.abs(i);
            int startC = c - spread;
            int endC = c + spread;

            int sc = Math.max(0, startC);
            int ec = Math.min(N-1, endC);

            // 현재 행(currR)의 sc~ec 구간의 집 개수만 더함
            if(sc == 0) count += s[currR][ec];
            else count += s[currR][ec] - s[currR][sc-1];
        }

        return count; // "집의 개수"만 반환!
    }
}
