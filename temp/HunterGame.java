import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class HunterGame {
    static int[][] grid;
    static int N,M,minTime;
    static Monster[] monster;
    static Home[] home;
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

        int T = Integer.parseInt(in.readLine());

        StringTokenizer st;
        StringBuilder sb=new StringBuilder();
        for (int t = 1; t <= T; ++t) {
            N = Integer.parseInt(in.readLine());

            monster = new Monster[6];
            home = new Home[6];
            grid = new int[N][N];
            M=0;
            for(int i=0;i<N;++i){
                st = new StringTokenizer(in.readLine());
                for(int j=0;j<N;++j){
                    grid[i][j] = Integer.parseInt(st.nextToken());
                    if(grid[i][j]>0){
                        M = Math.max(M,grid[i][j]);
                        monster[grid[i][j]] = new Monster(i,j);
                    }else if(grid[i][j]<0){
                        home[-grid[i][j]] = new Home(i,j);
                    }
                }
            }

            minTime=Integer.MAX_VALUE;
            dfs(0,0,0);

            System.out.println(minTime);
        }
    }

    public static void dfs(int time,int r,int c) {
        if (time >= minTime) return;

        boolean flag = false;
        for(int i=1;i<=M;++i){
            if (!home[i].v) {
                flag = true;
                break;
            }
        }

        if(!flag) {
            minTime = Math.min(minTime,time);

            return;
        }


        int distance=0;
        for(int i=1;i<=M;++i){
            //해당번호 몬스터가 잡혔으면 그 Home도 이동가능함
            Monster m = monster[i];
            if(!m.v){
                distance = Math.abs(m.r-r) + Math.abs(m.c-c);
                m.v=true;
                dfs(time+distance,m.r,m.c);
                m.v=false;
            }

            Home h = home[i];
            if(m.v&&!h.v){
                distance = Math.abs(h.r-r)+Math.abs(h.c-c);
                h.v=true;
                dfs(time+distance,h.r,h.c);
                h.v=false;
            }
        }
    }

    public static class Monster{
        public int r,c;
        public boolean v;

        public Monster(int r, int c) {
            this.r = r;
            this.c = c;
            this.v = false;
        }
    }

    public static class Home{
        public int r,c;
        public boolean v;

        public Home(int r, int c) {
            this.r = r;
            this.c = c;
            this.v = false;
        }
    }
}
