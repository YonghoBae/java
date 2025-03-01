import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BattleField {
    static int H;
    static int W;
    static char[][] map;

    static int[] dR = { -1, 1, 0, 0 };
    static int[] dC = { 0, 0, -1, 1 };
    static char[] dir = { '^', 'v', '<', '>' };
    static char[] command = { 'U', 'D', 'L', 'R', 'S' };

    public static void main(String[] args) throws NumberFormatException, IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

        int T = Integer.parseInt(in.readLine());

        StringBuilder sb =new StringBuilder();
        for (int t = 1; t <= T; ++t) {
            StringTokenizer st = new StringTokenizer(in.readLine());
            H = Integer.parseInt(st.nextToken());
            W = Integer.parseInt(st.nextToken());

            map = new char[H][W];
            int r = 0, c = 0;
            for (int i = 0; i < H; ++i) {
                String mapLine = in.readLine();
                for (int j = 0; j < W; ++j) {
                    map[i][j] = mapLine.charAt(j);
                    for (int d = 0; d < 4; ++d) {
                        if (dir[d] == map[i][j]) {
                            r = i;
                            c = j;
                        }
                    }
                }
            }

            int N = Integer.parseInt(in.readLine());
            String orderLine = in.readLine();
            for (int i = 0; i < N; ++i) {
                char order = orderLine.charAt(i);

                for (int ci = 0; ci < 4; ++ci) {
                    // 이동
                    if (command[ci] == order) {
                        // 요소에 따라 움직일지 판단
                        // 현재 위치 방향을 일단 바꿈
                        // 이동하는 곳이 가능하면 전진 아니면 
                        int nR = r + dR[ci];
                        int nC = c + dC[ci];
                        map[r][c] = dir[ci];

                        if(nR < 0 || nR >= H || nC < 0 || nC >= W) break;

                        if (map[nR][nC] == '.') {
                            map[nR][nC] = dir[ci];
                            map[r][c] = '.';
                            r=nR;
                            c=nC;
                        }
                        break;
                    }
                }

                // 포탄 발사
                if (order == command[4]) {
                    int curDir=-1;
                    for(int di=0;di<4;++di) {
                        if(map[r][c]==dir[di]) {
                            curDir=di;
                        }
                    }

                    int distance=1;
                    while (true) {
                        int nR = r + dR[curDir]*distance;
                        int nC = c + dC[curDir]*distance;

                        if(nR < 0 || nR >= H || nC < 0 || nC >= W) break;

                        if(map[nR][nC]=='#') break;

                        if(map[nR][nC]=='*') {
                            map[nR][nC]='.';
                            break;
                        }

                        distance++;
                    }
                }
            }


            sb.append("#").append(t).append(" ");
            for(int i=0;i<H;++i) {
                for(int j=0;j<W;++j) {
                    sb.append(map[i][j]);
                }
                sb.append("\n");
            }

        }
        System.out.println(sb.toString());

    }
}