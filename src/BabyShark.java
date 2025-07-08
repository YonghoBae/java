import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;
import java.util.StringTokenizer;

public class BabyShark {
    static int[][] grid;
    static boolean[][] v;
    static Shark shark;
    static Deque<int[]> q= new ArrayDeque<>();
    static int total,N;

    static int[] dr = {-1,0,0,1};
    static int[] dc = {0,-1,1,0};
    public static void main(String[] args) throws IOException {
        BufferedReader in =new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(in.readLine());
        grid = new int[N][N];
        v = new boolean[N][N];
        for(int i=0;i<N;++i){
            StringTokenizer st =new StringTokenizer(in.readLine());
            for(int j=0;j<N;++j){
                grid[i][j] = Integer.parseInt(st.nextToken());
                if(grid[i][j]==9){
                    shark = new Shark(i,j);
                }
            }
        }
        total=0;
        while(bfs()){
            q.clear();
            for(int i=0;i<N;++i){
                Arrays.fill(v[i],false);
            }
        }

        System.out.println(total);
    }


    static boolean bfs() {
        q.offer(new int[]{shark.r, shark.c,0});
        v[shark.r][shark.c] = true;
        Fish fish = new Fish(Integer.MAX_VALUE,Integer.MAX_VALUE,Integer.MAX_VALUE);

        while(!q.isEmpty()){
            int[] curr = q.poll();
            int r=  curr[0];
            int c= curr[1];
            int time = curr[2];
            if (time >= fish.time) break;

            for(int d=0;d<4;++d){
                int nr= r+dr[d];
                int nc= c+dc[d];
                if(nr<0||nr>=N||nc<0||nc>=N) continue;

                if(!v[nr][nc]){
                    v[nr][nc] = true;

                    if(grid[nr][nc]==0||grid[nr][nc]==shark.size) {
                        q.offer(new int[]{nr, nc, time+1});
                    }else if(shark.size>grid[nr][nc]){
                        if(fish.time>time+1){
                            fish.r=nr;
                            fish.c=nc;
                            fish.time=time+1;
                        }else if(fish.time==time+1){
                            if(fish.r>nr){
                                fish.r=nr;
                                fish.c=nc;
                                fish.time=time+1;
                            }else if(fish.r==nr&&fish.c>nc){
                                fish.r=nr;
                                fish.c=nc;
                                fish.time=time+1;
                            }
                        }
                    }
                }
            }
        }

        if(fish.time==Integer.MAX_VALUE){
            return false;
        }

        grid[fish.r][fish.c] = 0;
        grid[shark.r][shark.c] =0;
        total += fish.time;

        shark.r = fish.r;
        shark.c = fish.c;
        shark.eat++;
        if(shark.eat== shark.size){
            shark.eat=0;
            shark.size++;
        }

        return true;
    }

    static class Shark {
        int r,c,size,eat;

        public Shark(int r, int c) {
            this.r = r;
            this.c = c;
            this.size = 2;
            this.eat = 0;
        }
    }
    static class Fish {
        int r,c,time;

        public Fish(int r, int c,int time) {
            this.r = r;
            this.c = c;
            this.time = time;
        }
    }
}
