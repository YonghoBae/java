import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class BridgeMaking {
    static boolean[][] v;
    static int[][] grid;
    static int N,minDistance;
    static int[] dr = { -1, 1, 0, 0 };
    static int[] dc = { 0, 0, -1, 1 };
    static List<List<int[]>> poss = new ArrayList<List<int[]>>();
    static Deque<int[]> q = new ArrayDeque<int[]>();


    public static void main(String[] args) throws NumberFormatException, IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(in.readLine());
        grid = new int[N][N];
        v = new boolean[N][N];
        for (int i = 0; i < N; ++i) {
            StringTokenizer st = new StringTokenizer(in.readLine());
            for (int j = 0; j < N; ++j) {
                grid[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        int kind=2;
        for (int i = 0; i < N; ++i) {
            for(int j=0;j<N;++j) {
                if(grid[i][j]==1) {
                    grid[i][j] = kind;
                    bfs(i,j,kind);
                    kind++;
                }
            }
        }

        minDistance = Integer.MAX_VALUE;
        for (int i = 0; i < N; ++i) {
            for(int j=0;j<N;++j) {
                if(grid[i][j]!=0) {
                    findRoute(i,j,grid[i][j]);
                    q.clear();
                    for(int s=0;s<N;++s){
                        Arrays.fill(v[s],false);
                    }
                }
            }
        }

        System.out.println(minDistance);
    }

    private static void bfs(int r, int c, int kind) {
        q.offer(new int[] { r, c });

        while (!q.isEmpty()) {
            int[] curr = q.poll();
            int cr = curr[0];
            int cc = curr[1];

            for (int i = 0; i < 4; ++i) {
                int nr = cr + dr[i];
                int nc = cc + dc[i];
                if(nr<0||nr>=N||nc<0||nc>=N) continue;

                if(grid[nr][nc]==1) {
                    q.offer(new int[] {nr,nc});
                    grid[nr][nc] = kind;
                }
            }
        }
    }

    private static void findRoute(int r, int c,int kind) {
        q.offer(new int[] { r, c, 0});
        v[r][c] = true;

        while (!q.isEmpty()) {
            int[] curr = q.poll();
            int cnt = curr[2];
            int cr = curr[0];
            int cc = curr[1];

            for (int i = 0; i < 4; ++i) {
                int nr = cr + dr[i];
                int nc = cc + dc[i];
                if(nr<0||nr>=N||nc<0||nc>=N) continue;

                if(!v[nr][nc]&&grid[nr][nc]==0) {
                    q.offer(new int[] { nr, nc, cnt+1 });
                    v[nr][nc] = true;
                }else if(grid[nr][nc]!=kind&&grid[nr][nc]!=0){
                    minDistance = Math.min(curr[2], minDistance);
                    return;
                }
            }
        }
    }
}
