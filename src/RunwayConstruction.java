import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class RunwayConstruction {
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

        int T = Integer.parseInt(in.readLine());
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();
        for(int t=1;t<=T;++t){
            st = new StringTokenizer(in.readLine());
            int N = Integer.parseInt(st.nextToken());
            int X = Integer.parseInt(st.nextToken());

            int[][] grid = new int[N][N];
            for(int i=0;i<N;++i){
                st = new StringTokenizer(in.readLine());
                grid[i][0] = Integer.parseInt(st.nextToken());
                for(int j=1;j<N;++j){
                    grid[i][j] = Integer.parseInt(st.nextToken());
                }
            }


            List<List<Road>> roads = new ArrayList<>();
            List<Road> road = new ArrayList<>();
            int cnt=0;
            for(int i=0;i<N;++i){
                cnt=0;
                for(int j=0;j<N-1;++j){
                    cnt++;
                    if(grid[i][j]!=grid[i][j+1]){
                        road.add(new Road(cnt,grid[i][j]));
                        cnt=0;
                    }
                }
                road.add(new Road(++cnt,grid[i][N-1]));

                roads.add(road);
                road = new ArrayList<>();
            }

            int antiCnt=0;
            for(List<Road> roa: roads){
                int size = roa.size();
                for(int i=0;i<size-1;++i){
                    Road curr = roa.get(i);
                    Road next = roa.get(i+1);

                    if(curr.h<next.h){
                        if(curr.len<X){
                            antiCnt++;
                            break;
                        }
                    }else if(curr.h>next.h){
                        if(next.len<X){
                            antiCnt++;
                            break;
                        }
                    }
                }
            }

            sb.append("#").append(t).append(" ").append(N+N-antiCnt).append("\n");
        }
        System.out.println(sb.toString());
    }

    public static class Road{
        int len, h;

        public Road(int len, int h) {
            this.len = len;
            this.h = h;
        }
    }
}
