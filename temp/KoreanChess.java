import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class KoreanChess {
    static int N, result;
    static int[][] grid;
    static boolean[][] captured;


    static int[] dr = {1, -1, 0, 0};
    static int[] dc = {0, 0, 1, -1};

    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(in.readLine());

        StringTokenizer st;
        StringBuilder sb = new StringBuilder();
        for (int t = 1; t <= T; ++t) {
            N = Integer.parseInt(in.readLine());
            grid = new int[N][N];
            captured = new boolean[N][N];
            int[] start = new int[2];
            for (int i = 0; i < N; ++i) {
                st = new StringTokenizer(in.readLine());
                for (int j = 0; j < N; ++j) {
                    grid[i][j] = Integer.parseInt(st.nextToken());
                    if (grid[i][j] == 2) {
                        start[0] = i;
                        start[1] = j;
                    }
                }
            }

            result = 0;
            dfs(0, start[0], start[1]);

            System.out.println(result);
        }
    }


    public static void dfs(int depth, int r, int c) {
        if(depth==3){
            return;
        }

        for(int d=0;d<4;++d){
            int brideR=-1, brideC=-1;
            int nr = r+dr[d];
            int nc = c+dc[d];


            while(nr>=0&&nr<N&&nc>=0&&nc<N){
                if(grid[nr][nc]==1){
                    brideR=nr;
                    brideC=nc;
                    break;
                }
                nr += dr[d];
                nc += dc[d];
            }

            if(brideR == -1){
                continue;
            }


            nr+=dr[d];
            nc+=dc[d];
            while(nr>=0&&nr<N&&nc>=0&&nc<N){
                if(grid[nr][nc]==1){
                    if(!captured[nr][nc]){
                        captured[nr][nc] = true;
                        result++;
                    }

                    grid[nr][nc] = 0;
                    dfs(depth+1,nr,nc);
                    grid[nr][nc]=1;

                    break;
                }else{
                    dfs(depth+1,nr,nc);
                }

                nr+=dr[d];
                nc+=dc[d];
            }
        }
    }
}
