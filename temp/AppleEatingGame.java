import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class AppleEatingGame {
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(in.readLine());
        StringBuilder sb = new StringBuilder();

        for (int t = 1; t <= T; ++t) {
            int N = Integer.parseInt(in.readLine());
            int[][] apples = new int[11][2]; // 1~9번 사과 좌표
            int maxM = 0;

            for (int i = 0; i < N; ++i) {
                String line = in.readLine();
                for (int j = 0; j < N; ++j) {
                    int num = line.charAt(j) - '0';
                    if (num > 0) {
                        apples[num][0] = i;
                        apples[num][1] = j;
                        maxM = Math.max(maxM, num);
                    }
                }
            }

            // 시작: (0,0), 방향: 동쪽(3) -> 하(0), 좌(1), 상(2), 우(3) 순서
            User user = new User(0, 0, 3);
            int totalResult = 0;
            for (int i = 1; i <= maxM; ++i) {
                totalResult += user.rotateCnt(apples[i]);
            }

            sb.append(totalResult).append("\n");
        }
        System.out.print(sb.toString());
    }

    public static class User {
        int r, c, dir;

        public User(int r, int c, int dir) {
            this.r = r;
            this.c = c;
            this.dir = dir;
        }

        public int rotateCnt(int[] apple) {
            int tr = apple[0], tc = apple[1];
            int turns = 0;

            // 현재 방향(dir)에 따른 타겟 사분면 판별 및 회전수 계산
            if (dir == 3) { // 동쪽 보고 있음
                if (tr > r && tc > c) turns = 1;      // 우하단
                else if (tr > r && tc < c) turns = 2; // 좌하단
                else turns = 3;                       // 좌상단 or 우상단
            } else if (dir == 0) { // 남쪽 보고 있음
                if (tr > r && tc < c) turns = 1;      // 좌하단
                else if (tr < r && tc < c) turns = 2; // 좌상단
                else turns = 3;                       // 우상단 or 우하단
            } else if (dir == 1) { // 서쪽 보고 있음
                if (tr < r && tc < c) turns = 1;      // 좌상단
                else if (tr < r && tc > c) turns = 2; // 우상단
                else turns = 3;                       // 우하단 or 좌하단
            } else if (dir == 2) { // 북쪽 보고 있음
                if (tr < r && tc > c) turns = 1;      // 우상단
                else if (tr > r && tc > c) turns = 2; // 우하단
                else turns = 3;                       // 좌하단 or 좌상단
            }

            // 상태 업데이트
            this.dir = (this.dir + turns) % 4; // 시계방향 회전 업데이트
            this.r = tr;
            this.c = tc;
            return turns;
        }
    }
}