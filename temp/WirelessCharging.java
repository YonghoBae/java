import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class WirelessCharging {
    static int[] dr = {0, -1, 0, 1, 0};
    static int[] dc = {0, 0, 1, 0, -1};

    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

        int T = Integer.parseInt(in.readLine());
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;
        for (int t = 1; t <= T; ++t) {
            st = new StringTokenizer(in.readLine());

            int M = Integer.parseInt(st.nextToken());
            int A = Integer.parseInt(st.nextToken());

            User[] userA = new User[M + 1];
            userA[0] = new User(0, 0);
            st = new StringTokenizer(in.readLine());
            for (int i = 1; i < M + 1; ++i) {
                int dir = Integer.parseInt(st.nextToken());
                userA[i] = new User(userA[i - 1].r + dr[dir], userA[i - 1].c + dc[dir]);
            }

            User[] userB = new User[M + 1];
            userB[0] = new User(9, 9);
            st = new StringTokenizer(in.readLine());
            for (int i = 1; i < M + 1; ++i) {
                int dir = Integer.parseInt(st.nextToken());
                userB[i] = new User(userB[i - 1].r + dr[dir], userB[i - 1].c + dc[dir]);
            }

            AP[] aps = new AP[A];
            for (int i = 0; i < A; ++i) {
                st = new StringTokenizer(in.readLine());
                int c = Integer.parseInt(st.nextToken())-1;
                int r = Integer.parseInt(st.nextToken())-1;
                int C = Integer.parseInt(st.nextToken());
                int P = Integer.parseInt(st.nextToken());

                aps[i] = new AP(r, c, C, P);
            }

            //시간 순서대로
            int energy = 0;
            for (int m = 0; m < M + 1; ++m) {
                int maxInStep = 0; // 이 시간대의 최댓값 저장용
                for (int i = 0; i < A; ++i) {
                    for (int j = 0; j < A; ++j) {
                        int currentSum = 0;
                        int resA = aps[i].in(userA[m]) ? aps[i].P : 0;
                        int resB = aps[j].in(userB[m]) ? aps[j].P : 0;

                        if (i != j) currentSum = resA + resB;
                        else currentSum = Math.max(resA, resB);

                        // 현재 조합의 합이 이전까지의 최댓값보다 크면 갱신
                        maxInStep = Math.max(maxInStep, currentSum);
                    }
                }
                energy += maxInStep;
            }

            sb.append("#").append(t).append(" ").append(energy).append("\n");
        }
        System.out.println(sb.toString());
    }

    public static class User {
        public int r, c;

        public User(int r, int c) {
            this.r = r;
            this.c = c;
        }
    }

    public static class AP {
        public int r, c, C, P;

        public AP(int r, int c, int c1, int p) {
            this.r = r;
            this.c = c;
            C = c1;
            P = p;
        }

        public boolean in(User user) {
            return C >= Math.abs(r - user.r) + Math.abs(c - user.c);
        }
    }
}
