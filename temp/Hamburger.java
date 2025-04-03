import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
public class Hamburger {
    static int maxT;
    static int N;
    static int L;
    static int[] T;
    static int[] K;

    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        int TC = Integer.parseInt(in.readLine());
        StringBuilder sb = new StringBuilder();

        StringTokenizer st;
        for (int t = 1; t <= TC; ++t) {
            sb.append("#").append(t).append(" ");
            st = new StringTokenizer(in.readLine());
            N = Integer.parseInt(st.nextToken());
            L = Integer.parseInt(st.nextToken());

            T = new int[N];
            K = new int[N];
            for (int n = 0; n < N; ++n) {
                st = new StringTokenizer(in.readLine());
                T[n] = Integer.parseInt(st.nextToken());
                K[n] = Integer.parseInt(st.nextToken());
            }

            maxT = 0;
            findBest(0, 0, 0);
            sb.append(maxT).append("\n");
        }
        System.out.println(sb.toString());
    }

    static void findBest(int cnt, int taste, int cal) {
        if (cal > L) return;

        if (cnt == N) {
            maxT = Math.max(maxT, taste);
            return;
        }

        findBest(cnt + 1, taste + T[cnt], cal + K[cnt]);
        findBest(cnt + 1, taste, cal);
    }
}


//햄버거 맛 유지, 정해진 칼로리 넘지 않는
//N 재료의 수
//L 제한 칼로리
//T 맛에 대한 점수
//K 칼로리