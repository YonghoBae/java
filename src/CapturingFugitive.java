import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.StringTokenizer;

public class CapturingFugitive {
    static int[][] grid;
    static int[] dr = {-1, 1, 0, 0};
    static int[] dc = {0, 0, -1, 1};

    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

        int T = Integer.parseInt(in.readLine());

        StringBuilder sb = new StringBuilder();
        for(int t=1;t<=T;++t){
            StringTokenizer st = new StringTokenizer(in.readLine());
            int N = Integer.parseInt(st.nextToken());
            int M = Integer.parseInt(st.nextToken());
            int R = Integer.parseInt(st.nextToken());
            int C = Integer.parseInt(st.nextToken());
            int L = Integer.parseInt(st.nextToken());

            grid = new int[N][M];
            boolean[][] v = new boolean[N][M];

            for (int i = 0; i < N; ++i) {
                st = new StringTokenizer(in.readLine());
                for (int j = 0; j < M; ++j) {
                    grid[i][j] = Integer.parseInt(st.nextToken());
                }
            }

            Deque<int[]> q = new ArrayDeque<>();
            q.offer(new int[]{R, C});
            v[R][C] = true;
            int cnt=1, time=0;
            while (!q.isEmpty()) {
                time++;
                int size = q.size();
                if(time==L){
                    break;
                }
                for(int s=0;s<size;++s){
                    int[] curr = q.poll();
                    int r = curr[0];
                    int c = curr[1];
                    boolean[] isNexts = findDir(r,c);

                    for (int i = 0; i < 4; ++i) {
                        int nr = r + dr[i];
                        int nc = c + dc[i];
                        if (nr < 0 || nr >= N || nc < 0 || nc >= M) continue;
                        boolean[] nextIsNexts = findDir(nr,nc);
                        int nextI=0;
                        if(i==0){
                            nextI=1;
                        }else if(i==3){
                            nextI=2;
                        }else if(i==2){
                            nextI=3;
                        }
                        if(!v[nr][nc]&&isNexts[i]&&nextIsNexts[nextI]&& !(grid[nr][nc] ==0)){
                            v[nr][nc] = true;
                            q.offer(new int[]{nr,nc});
                            cnt++;
                        }
                    }
                }
            }

            sb.append("#").append(t).append(" ").append(cnt).append("\n");
        }
        System.out.println(sb.toString());
    }

    public static boolean[] findDir(int r, int c) {
        if (grid[r][c] == 0) {
            return new boolean[]{false, false, false, false};
        } else if (grid[r][c] == 1) {
            return new boolean[]{true, true, true, true};
        } else if (grid[r][c] == 2) {
            return new boolean[]{true, true, false, false};
        } else if (grid[r][c] == 3) {
            return new boolean[]{false, false, true, true};
        } else if (grid[r][c] == 4) {
            return new boolean[]{true, false, false, true};
        } else if (grid[r][c] == 5) {
            return new boolean[]{false, true, false, true};
        } else if (grid[r][c] == 6) {
            return new boolean[]{false, true, true, false};
        } else if (grid[r][c] == 7) {
            return new boolean[]{true, false, true, false};
        }

        return new boolean[]{false, false, false, false};
    }
}
