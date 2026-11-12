package bfs_dfs;

// [Gold V] 토마토 - 7569
// https://www.acmicpc.net/problem/7569
// 분류: 너비 우선 탐색, 그래프 이론, 그래프 탐색, 격자 그래프, 최단 경로
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.StringTokenizer;

public class B7569 {
    static int M, N, H, zCnt, counter,result;
    static int[][][] grid;
    static boolean[][][] v;
    static Deque<int[]> q = new ArrayDeque<>();
    static int[] dr = {-1, 1, 0, 0, 0, 0};
    static int[] dc = {0, 0, -1, 1, 0, 0};
    static int[] dh = {0, 0, 0, 0, -1, 1};

    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(in.readLine());

        M = Integer.parseInt(st.nextToken());
        N = Integer.parseInt(st.nextToken());
        H = Integer.parseInt(st.nextToken());
        grid = new int[N][M][H];
        v = new boolean[N][M][H];

        zCnt=0;
        for (int h = 0; h < H; ++h) {
            for (int i = 0; i < N; ++i) {
                st = new StringTokenizer(in.readLine());
                for (int j = 0; j < M; ++j) {
                    grid[i][j][h] = Integer.parseInt(st.nextToken());
                    if(grid[i][j][h] == 0){
                        zCnt++;
                    }
                }
            }
        }
        if(zCnt==0){
            System.out.println(0);
            return;
        }
        counter=0;
        result = -1;

        bfs();
        System.out.println(result);
    }

    static void bfs() {
        for (int h = 0; h < H; ++h) {
            for (int i = 0; i < N; ++i) {
                for (int j = 0; j < M; ++j) {
                    if(grid[i][j][h]==1){
                        q.offer(new int[]{i, j, h,0});
                        v[i][j][h] = true;
                    }
                }
            }
        }

        while (!q.isEmpty()) {
            int[] curr = q.poll();
            int r = curr[0];
            int c = curr[1];
            int h = curr[2];
            int cnt = curr[3];

            for (int d = 0; d < 6; ++d) {
                int nr = r + dr[d];
                int nc = c + dc[d];
                int nh = h + dh[d];
                if (nr < 0||nr>=N||nc<0||nc>=M||nh<0||nh>=H) continue;


                if(!v[nr][nc][nh]){
                    v[nr][nc][nh] = true;
                    if(grid[nr][nc][nh]==0){
                        q.offer(new int[]{nr,nc,nh,cnt+1});
                        counter++;
                        if(counter == zCnt) result=cnt+1;
                    }
                }
            }
        }
    }
}
